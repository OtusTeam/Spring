FROM java:8
LABEL version="work-19"
# Добавляем приложение
ADD /target/work-17-0.0.1-SNAPSHOT.jar /target/work-17-0.0.1-SNAPSHOT.jar
# Отрываем порт
EXPOSE 8086
# Запуск приложения
#CMD ["java","-jar", "-Dspring.profiles.active=dock", "/target/work-17-0.0.1-SNAPSHOT.jar"]