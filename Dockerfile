FROM tomcat:jdk21-openjdk-slim-bookworm
LABEL version="1.0.0" description="Hub Educacional" maintainer="Márcio Santana" email="marcio.santana@adventistas.org"
COPY ./target/hubeducacional-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]