FROM openjdk:17-jdk-alpine
ADD ./target/SpringBoot_Thymeleaf_Demo-0.0.1-SNAPSHOT.jar SpringBoot_Thymeleaf_Demo.jar
ENTRYPOINT ["java", "-jar", "SpringBoot_Thymeleaf_Demo.jar"]