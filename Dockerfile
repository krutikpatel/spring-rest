FROM openjdk:oraclelinux7
VOLUME /tmp
EXPOSE 5000
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

#sample run command
#docker run -d --publish=9999:5000 12653b725371
#9999 is host port, 5000 is port in container where springboot server is listening
