FROM openjdk:11-jre-slim

COPY target/dotshare.jar /dotshare.jar

EXPOSE 8080

CMD ["java", "-jar", "/dotshare.jar"]