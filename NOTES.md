## 2026-02-21

- Spring Boot project generated via Spring Initializr (v3.5.0, Java 21)
- Project structure: src/main/java (code), src/main/resources (config), src/test (tests)
- pom.xml defines dependencies and inherits versions from spring-boot-starter-parent
- @RestController marks a class as an HTTP controller that returns data (not HTML)
- @RequestMapping defines the URL prefix for all endpoints in a class
- @GetMapping maps a method to a GET HTTP request on a specific path
- DispatcherServlet is Spring's central request handler — routes requests to controllers
- Tomcat is the embedded HTTP server, runs on port 8080 by default
- application.properties is used to configure the app (datasource, port, etc.)
- Fixed IntelliJ Wayland bug with -Dawt.toolkit.name=WLToolkit in VM options
- ApiInfo class created (doesn't need spring boot annotation, POJO)
- HealthController returns an ApiInfo object, which Jackson serializes to JSON
- spring boot uses Jackson to convert Java to JSON but it needs getters to work
