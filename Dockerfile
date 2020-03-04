FROM store/oracle/serverjre:8
COPY target/delivery-0.0.1-SNAPSHOT.jar delivery.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/delivery.jar"]