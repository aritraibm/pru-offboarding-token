#cmd : docker build -t token-service .
FROM openjdk:8
EXPOSE 9099
ADD target/token-service.jar token-service.jar
ENTRYPOINT ["java","-jar","/token-service.jar"]