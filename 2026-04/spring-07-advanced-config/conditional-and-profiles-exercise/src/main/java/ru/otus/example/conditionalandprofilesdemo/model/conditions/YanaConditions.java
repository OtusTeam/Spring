package ru.otus.example.conditionalandprofilesdemo.model.conditions;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class YanaConditions extends AllNestedConditions {

    public YanaConditions() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }


    @ConditionalOnProperty(name = "condition.alexey-exists", havingValue = "false")
    static class AlexeyDoesNotExistsCondition {
    }

    @ConditionalOnProperty(name = "condition.yanis-exists", havingValue = "true")
    static class YanisExistsCondition {
    }
}
