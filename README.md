# SpringBootApp

A small multi-module Spring Boot app with separate student, course, grade, and gateway services.

## Setup

1. Install Java 17 and Maven.
2. Build/test all modules:
   ```bash
   mvn test
   ```
3. Run services (separately), for example:
   ```bash
   mvn -pl student-service spring-boot:run
   mvn -pl course-service spring-boot:run
   mvn -pl grade-service spring-boot:run
   mvn -pl gateway-service spring-boot:run
   ```
4. Default ports/endpoints:
   - `student-service` on `:8081` (`/students`)
   - `course-service` on `:8082` (`/courses`)
   - `grade-service` on `:8083` (`/grades`)
   - `gateway-service` on `:8080` (`/gateway`)
