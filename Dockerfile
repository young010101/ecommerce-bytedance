# 第一阶段：构建应用
FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app

# 复制 pom.xml 并预先下载依赖，利用缓存加速构建
COPY pom.xml .
COPY sky-common/pom.xml sky-common/
COPY sky-pojo/pom.xml sky-pojo/
COPY sky-server/pom.xml sky-server/
RUN mvn dependency:go-offline

# 复制源代码
COPY sky-common/src sky-common/src
COPY sky-pojo/src sky-pojo/src
COPY sky-server/src sky-server/src

# 构建应用
RUN mvn clean package -DskipTests

# 第二阶段：运行应用
FROM openjdk:17-slim
WORKDIR /app

# 从构建阶段复制打包好的 jar 文件
COPY --from=build /app/sky-server/target/sky-server-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用默认端口
EXPOSE 8080

# 运行 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]
