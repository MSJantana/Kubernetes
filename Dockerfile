FROM tomcat:9.0.8-jre8-alpine

ADD ./webapp /usr/local/tomcat/webapps/webapp
EXPOSE 8080
CMD ["catalina.sh", "run"]