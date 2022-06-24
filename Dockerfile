FROM openjdk:17-jdk-alpine
WORKDIR /app
ADD ./target/api.orders-0.0.1-SNAPSHOT.jar /app
ADD ./newrelic/newrelic.jar  /app
ADD ./newrelic/newrelic.yml  /app 
ENV NEW_RELIC_APP_NAME="order-api"
ENV NEW_RELIC_LICENSE_KEY="eu01xxa1dbd47e3aaa0283e9058f5d5be184NRAL"
ENTRYPOINT ["java","-javaagent:/app/newrelic.jar","-jar","/app/api.orders-0.0.1-SNAPSHOT.jar"]