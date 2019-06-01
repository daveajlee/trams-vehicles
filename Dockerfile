FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/trams_vehicles.jar trams_vehicles.jar
ENV JAVA_OPTS=""
ENV EUREKA_SERVICE_URL http://docker.for.mac.localhost:8761
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /trams_vehicles.jar" ]