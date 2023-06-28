FROM tomcat:9.0.8-jre8-alpine

COPY ./target/hubeducacional-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]