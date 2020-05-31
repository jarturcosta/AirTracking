FROM adoptopenjdk/openjdk8-openj9:alpine-slim
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
LABEL "name"="airtrackingwebapp"
EXPOSE 8080
