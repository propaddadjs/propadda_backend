FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/prop-*.jar app.jar
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
EXPOSE 8080
CMD ["sh","-c","java $JAVA_OPTS -jar app.jar"]
