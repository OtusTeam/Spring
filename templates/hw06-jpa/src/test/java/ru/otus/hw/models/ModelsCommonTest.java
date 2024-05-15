package ru.otus.hw.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;

class ModelsCommonTest {

    private static Set<Class<?>> entitiesClasses;

    @BeforeAll
    static void setUpAll() {
        /*
        Чтобы работало подключить
        <properties>
            <reflections.version>0.10.2</reflections.version>
        </properties>

        <dependency>
            <groupId>org.reflections</groupId>
            <artifactId>reflections</artifactId>
            <version>${reflections.version}</version>
        </dependency>
         */
        var reflections = new Reflections("ru.otus.hw.models");
        entitiesClasses = reflections.getTypesAnnotatedWith(Entity.class);

    }

    @ParameterizedTest
    @MethodSource("getEntities")
    void shouldBeNoOneToOneRelationshipsInModelClasses(Class<?> entityClass) {

        var oneToOneRelationshipExists = Arrays.stream(entityClass.getDeclaredFields())
                .anyMatch(f -> f.isAnnotationPresent(OneToOne.class));
        assertThat(oneToOneRelationshipExists)
                .withFailMessage("В доменной модели ДЗ не предусмотрены связи OneToOne")
                .isFalse();
    }

    @ParameterizedTest
    @MethodSource("getEntities")
    void shouldBeNoEagerRelationshipsInModelClasses(Class<?> entityClass) {
        boolean eagerFetchExists = Arrays.stream(entityClass.getDeclaredFields())
                .map(f -> getRelationAnnotationArgumentValue(f, "fetch", FetchType.class))
                .filter(Objects::nonNull)
                .anyMatch(fetchType -> fetchType.equals(FetchType.EAGER));
        assertThat(eagerFetchExists)
                .withFailMessage("Лучше все связи сделать LAZY")
                .isFalse();
    }

    @ParameterizedTest
    @MethodSource("getEntities")
    void shouldMappedForBidirectionalRelationshipsInModelClasses(Class<?> entityClass) {
        var relationsEntries = findAllRelationsEntry(entityClass);
        var hasBidirectionalRelationshipsWithoutMappedBy = relationsEntries.entrySet().stream()
                .anyMatch(relationEntry -> {
                    var reverseRelations = findAllRelationsEntry(relationEntry.getKey());
                    var reverseRelationField = reverseRelations.get(entityClass);
                    if (isNull(reverseRelationField)) {
                        return false;
                    }
                    var relationFieldName = relationEntry.getValue().getName();
                    var reverseRelationFieldName = reverseRelationField.getName();
                    var mappedByValue = getRelationAnnotationArgumentValue(relationEntry.getValue(),
                            "mappedBy", String.class);
                    var reverseMappedByValue = getRelationAnnotationArgumentValue(reverseRelationField,
                            "mappedBy", String.class);

                    return !reverseRelationFieldName.equals(mappedByValue) &&
                            !relationFieldName.equals(reverseMappedByValue);
                });
        assertThat(hasBidirectionalRelationshipsWithoutMappedBy)
                .withFailMessage("Двунаправленные связи должны быть настроены с помощью mappedBy")
                .isFalse();

    }

    private static Stream<Arguments> getEntities() {
        return entitiesClasses.stream().map(Arguments::of);
    }

    private <T> T getRelationAnnotationArgumentValue(Field field, String argumentName, Class<T> returnType) {
        return Arrays.stream(field.getAnnotations())
                .flatMap(a -> Arrays.stream(a.getClass().getDeclaredMethods()).map(m -> Map.entry(m, a)))
                .filter(e -> e.getKey().getName().equals(argumentName))
                .map(e -> ReflectionUtils.invoke(e.getKey(), e.getValue()))
                .map(returnType::cast)
                .findFirst().orElse(null);
    }

    private Map<? extends Class<?>, Field> findAllRelationsEntry(Class<?> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.getType().isPrimitive())
                .map(f -> Map.entry(f, fieldToClass(f)))
                .filter(e -> entitiesClasses.contains(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    private Class<?> fieldToClass(Field field) {
        var className = field.getType().getName();
        if (Collection.class.isAssignableFrom(field.getType())) {
            className = field.getGenericType().getTypeName()
                    .split("<")[1].split(">")[0];

        }
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}