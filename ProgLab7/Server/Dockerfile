FROM openjdk:17
ENV MYFILE=/server-app/CSVFile.csv
WORKDIR /server-app
COPY Server.jar /server-app/Server.jar
COPY CSVFile.csv /server-app/CSVFile.csv
EXPOSE 45000
CMD ["java", "-jar", "Server.jar"]
