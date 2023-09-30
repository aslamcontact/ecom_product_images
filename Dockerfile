FROM eclipse-temurin:20-jre-alpine
WORKDIR /api
COPY ./target/*SNAPSHOT.jar api.jar
EXPOSE 3306
EXPOSE 1433
ENTRYPOINT ["java","-jar","api.jar"]
