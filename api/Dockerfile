FROM ghcr.io/graalvm/graalvm-community:21 AS build

RUN microdnf update -y && \
    microdnf install -y maven && \
    microdnf clean all

WORKDIR /usr

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

RUN mvn -Pnative native:compile

FROM ghcr.io/graalvm/jdk-community:21

COPY --from=build /usr/target/cash-api.jar .

ENTRYPOINT ["java", "-jar","cash-api.jar"]
