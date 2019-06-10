FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD parser-0.0.1-SNAPSHOT.jar app.jar
COPY jieba.dict jieba.dict
COPY test.json test.json
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 9200
