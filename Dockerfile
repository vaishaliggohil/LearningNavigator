FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/mysqlproj-0.0.1-SNAPSHOT.jar mysqlproj.jar
EXPOSE 3306
ENTRYPOINT exec java $JAVA_OPTS -jar mysqlproj.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar mysqlproj.jar
