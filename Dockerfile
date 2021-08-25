# Pull MySQL from Docker Hub (if not already in local Docker registry).
FROM mysql:5.7.19

# MySQL will run on port 3306 within the container.
EXPOSE 3306

# Set an environment variable, which MySQL will look for.
ENV MYSQL_ROOT_PASSWORD=root

# Copy a SQL script into the container.
COPY manager.sql /docker-entrypoint-initdb.d


FROM maven:3.6.3-openjdk-11 AS compile
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn -Dmaven.test.skip=false clean package

FROM openjdk:11
RUN wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.24-1debian9_all.deb -O /tmp/mysql-connector.deb
RUN dpkg -i /tmp/mysql-connector.deb
COPY --from=compile /usr/src/mymaven/target/fundmanager-0.0.1-SNAPSHOT.jar app.jar
#COPY src/main/resources/application-docker.properties application.properties

EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
