FROM java:11

MAINTAINER tjy

ADD test111.jar test111.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/test111.jar"]