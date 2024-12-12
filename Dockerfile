FROM openjdk:23-jdk-oracle AS builder

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# RUN chmod a+x ./mvnw
RUN ./mvnw clean package -Dmaven.skip.tests=true

# ENV SERVER_PORT=3000

# EXPOSE ${SERVER_PORT}

# ENTRYPOINT ./mvnw spring-boot:RUN
# ENTRYPOINT ["java", "-jar", "target/vttp5-ssf-day18l-0.0.1-SNAPSHOT.jar"]

# stage 2
FROM openjdk:23-jdk-oracle

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=builder /code_folder/target/vttp5-ssf-rev-0.0.1-SNAPSHOT.jar vttp5-ssf-rev.jar
COPY customers-100.csv .

ENV SERVER_PORT=3000
ENV MY_API_PASS_KEY=xyz789
ENV CSV_FILE_PATH=/app/customers-100.csv

EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 CMD curl -s -f http://localhost:3000/health || exit 1

ENTRYPOINT ["java", "-jar", "vttp5-ssf-rev.jar"]
# ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar