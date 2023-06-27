FROM tomcat:9.0.8-jre8-alpine

COPY ./hubeducacional-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
EXPOSE 8080
