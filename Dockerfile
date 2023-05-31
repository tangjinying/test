FROM java:11

MAINTAINER tjy

ADD test-1.0-SNAPSHOT.jar test111.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/test111.jar"]