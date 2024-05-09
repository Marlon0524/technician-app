FROM openjdk:17
VOLUME /tmp
EXPOSE 8020
ADD ./target/technician-0.0.1-SNAPSHOT.jar technician-app.jar
ENTRYPOINT ["java", "-jar", "technician-app.jar"]