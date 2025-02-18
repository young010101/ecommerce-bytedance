# 第一阶段：构建应用
FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app

# 复制 pom.xml 并预先下载依赖，利用缓存加速构建
COPY pom.xml .
RUN mvn dependency:go-offline

# 复制源代码并构建应用
COPY src ./src
RUN mvn clean package -DskipTests

# 第二阶段：运行应用
FROM openjdk:17-jre
WORKDIR /app

# 从构建阶段复制打包好的 jar 文件，注意将 target/your-app.jar 替换为实际的 jar 文件名
COPY --from=build /app/target/sky-server-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用默认端口（根据需要修改）
EXPOSE 8080

# 运行 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]
