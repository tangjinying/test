FROM java:11

MAINTAINER tjy

ADD test-1.0-SNAPSHOT.jar test.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/test.jar"]