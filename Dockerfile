FROM openjdk:11
ADD target/*.jar meta.agil-1.0.3.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "meta.agil-1.0.3.jar"]