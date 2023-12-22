FROM ghcr.io/graalvm/graalvm-community:21 AS build

RUN microdnf update -y && \
    microdnf install -y maven && \
    microdnf clean all

WORKDIR /usr

COPY . .

RUN mvn -Pnative -DskipTests native:compile

FROM ghcr.io/graalvm/jdk-community:21

COPY --from=build /usr/target/messenger.jar .

ENTRYPOINT ["java", "-jar","messenger.jar"]
