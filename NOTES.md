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

## 2026-02-22

- @PostMapping maps a method to get a POST http request on a specific path
- @RequestBody makes Spring read the HTTP body convert JSON to the object
- Content-Type: application/json tells the server the request body is JSON so Spring knows how to deserialize it.
- Constructor Injection is better than using @Autowire in modern spring
- Constructor Injection can make data immutable and also hides class dependencies (is better)
- Service layer contains business logic, keeping controllers focused on HTTP only (separation of concerns)
- private final "Object" - Guarantees that the object won't be changed in runtime 

## 2026-02-23

- Validation lives in the exact moment where data comes from outside
- Services have to assume that the data they have is valid
- @NotBlank and @Positive are validators for not being blank and for being positive
- Exception handling is necessary to find the root cause of problems without it the problem becomes wider
- forEach in java works like a for loop that runs for every field in a list or array
- Every error or exception should have its own method inside of the GlobalExceptionHandler
- Map in java is a data structure that works in pairs (String, String) meaning the "name" is a string and its value is also a string
- @RestControllerAdvice marks a class as a global exception handler for all controllers

## 2026-02-24
- 2xx HTTP codes: - Sucess: 200(OK); 201(Created); 204(No Content)
- 4xx HTTP codes: - Client error: 400(Bad Request); 401(Unauthorized); 403(Forbidden); 404(Not Found)
- 5xx HTTP codes: - Server error: 500(Internal Server Error)
- ResponseEntity permits the control of a http status code in an object

##2026-02-25
- A java interface is like a class but it only defines the contracts (methods) without implementing them
- When an interface extends to JpaRepository it comes with some methods (save(), findById(), findAll(), deleteById(), etc)
- A JPA (@Entity) is a Java class that represents a table in a database, each insance of the class is a line on a table
- Hibernate creates the tables and does the queries automatically
- JPA needs an empty constructor to recontruct Java objects, first creating an empty instance and then using the setters to set their values
- The Product class has an auto generated id in the db, not a specific id given by the user like in the case of using ProductRequest
- productRepository.save() gives a product stored in the db with a generated id
- .orElseThrow gives an exception when the Optional is empty, meaning the value doesn't exist
- The endpoints on ProductController are simplified becouse ProductRepository has context on Products
- Optional is a container that may have or not a value, in the case of a product not existing in the db
- @PathVariable makes Spring understand that the id comes from the URL
- ResponseStatusException controls the status code of a service 
- ResponseEntity.ok() represents a shortcut for status 200(ok)
