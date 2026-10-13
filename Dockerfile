FROM eclipse-temurin:25-jdk-jammy AS build

WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/
COPY src/ src/


RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    JAR_NAME="$(./mvnw help:evaluate \
        -Dexpression=project.build.finalName -q -DforceStdout)" && \
    mv "target/${JAR_NAME}.jar" target/app.jar

FROM build AS extract

WORKDIR /build

RUN java -Djarmode=tools -jar target/app.jar \
    extract --layers --destination target/extracted


FROM eclipse-temurin:25-jre-jammy AS runtime

WORKDIR /app

RUN groupadd --system app && \
    useradd --system --gid app --no-create-home app

COPY --from=extract --chown=app:app /build/target/extracted/dependencies/ ./
COPY --from=extract --chown=app:app /build/target/extracted/spring-boot-loader/ ./
COPY --from=extract --chown=app:app /build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract --chown=app:app /build/target/extracted/application/ ./

USER app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
