FROM tomcat:jdk21-openjdk-slim-bookworm

COPY ./target/hubeducacional-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]