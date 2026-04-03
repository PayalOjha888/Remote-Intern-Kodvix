### **Day 16 Learning (02/04/2026)** ###

**\#SPRING BOOT ACTUATOR:**

**What is Spring Boot Actuator?** Spring Boot Actuator is a library that helps you monitor and manage your application when it is running in production. It provides "endpoints" (URLs) that give you information about the app's health, metrics, and internal working.

**1\. Adding the Dependency** To use Actuator, add this to your pom.xml file:

* **Group ID:** org.springframework.boot  
* **Artifact ID:** spring-boot-starter-actuator

**2\. Important Endpoints** Endpoints are URLs you visit to see data. Common ones include:

* **/health:** Tells you if the app is UP or DOWN.  
* **/metrics:** Shows memory usage, CPU usage, and HTTP request counts.  
* **/env:** Shows the environment properties the app is using.  
* **/beans:** Shows a list of all Spring beans created in your app.  
* **/loggers:** Lets you see and change log levels while the app is running.  
* **/threaddump:** Shows what all the threads are doing.

**3\. How to Enable Endpoints** By default, most endpoints are hidden for security. You must enable them in your application.properties file:

* **To show everything:** management.endpoints.web.exposure.include=\*  
* **To show specific ones:** management.endpoints.web.exposure.include=health,info,metrics

**4\. Health Checks** The /health endpoint is the most important for tools like Kubernetes.

* **Liveness:** Is the app alive?  
* **Readiness:** Is the app ready to take traffic?  
* **Custom Health:** You can write a Java class that implements "HealthIndicator" to check if your specific database or external API is working.

**5\. Metrics and Micrometer** Actuator uses a tool called Micrometer. It acts like a bridge between your app and monitoring tools.

* You can send your app’s data to tools like **Prometheus** or **Grafana** to see charts and graphs of your app's performance.

**6\. Creating Your Own Endpoint** If you need a custom URL to show specific data, you can create a class and use the **@Endpoint** annotation.

* Use **@ReadOperation** for GET requests.  
* Use **@WriteOperation** for POST requests.

**7\. Security Best Practices** Because Actuator shows sensitive info (like environment variables), you must secure it:

* Never expose all endpoints to the public internet.  
* Use **Spring Security** to restrict access so only users with the "ADMIN" role can see /actuator URLs.  
* Change the default path from /actuator to something secret using: management.endpoints.web.base-path=/my-secret-path

**\#PROFILING IN SPRING BOOT:**

**What is Profiling?** Profiling is a way to separate your application configuration for different environments. For example, you might want your app to connect to a "test" database while you are developing, but a "production" database when the app is live. Profiles allow you to switch these settings easily without changing your code.

**1\. Why do we need Profiles?**

* **Environment Specifics:** Different databases for Dev, QA, and Production.  
* **Feature Toggling:** Enabling certain features (like Swagger or Debug Logging) only in Development.  
* **Security:** Using mock security for testing and real security for the live site.

**2\. How to Create Profile Files** Spring Boot looks for a specific naming pattern for your property files: application-{profile}.properties.

* **application.properties:** This is the default file. It contains settings used by all profiles.  
* **application-dev.properties:** Settings specifically for the Development environment.  
* **application-prod.properties:** Settings specifically for the Production environment.

**3\. How to Activate a Profile** You can tell Spring Boot which profile to use in three main ways:

* **In application.properties:** spring.profiles.active=dev  
* **Using Command Line (when running the JAR):** java \-jar myapp.jar \--spring.profiles.active=prod  
* **Using Environment Variables:** Set SPRING\_PROFILES\_ACTIVE=prod in your OS or Docker container.

**4\. Using @Profile in Code** You can tell Spring to only load certain Beans (classes) when a specific profile is active using the **@Profile** annotation.

* **Example:** @Configuration @Profile("dev") public class DevDatabaseConfig { ... } (This class will only be used if the "dev" profile is active).

**5\. Profile Groups** In modern Spring Boot versions, you can group multiple profiles together. For example, you can create a group called "production" that automatically includes "database-prod", "security-prod", and "logging-prod".

* **Setting it up:** spring.profiles.group.production=db-prod,sec-prod

**6\. The "Default" Profile** If you don't activate any profile, Spring Boot uses the profile named default. You can create an application-default.properties file to define settings that run when no other environment is specified.

**7\. Best Practices**

* **Keep it Simple:** Don't create too many profiles or it becomes hard to manage.  
* **Default Values:** Keep common settings in the main application.properties and only put differences in the profile-specific files.  
* **Sensitive Data:** Never store real production passwords in application-prod.properties. Use environment variables or a Secret Manager for that.

**\#SPRING AOP (ASPECT ORIENTED PROGRAMMING):**

**What is Spring AOP?** Aspect Oriented Programming (AOP) is a way to separate "cross-cutting concerns" from your main business logic. Cross-cutting concerns are tasks that you need to do in many different parts of your app, such as **Logging, Security, or Transaction Management**. Instead of writing the same logging code in every method, AOP allows you to write it once and "inject" it wherever needed.

**1\. Key Concepts (The Vocabulary)** To understand AOP, you must know these terms:

* **Aspect:** The actual module or class that contains the extra logic (e.g., a Logging class).  
* **Advice:** The action taken by the Aspect. It defines "What" to do and "When" to do it (e.g., "log the message before the method runs").  
* **Join Point:** A point in the execution of your program, like the execution of a method.  
* **Pointcut:** A set of rules that matches Join Points. It defines "Where" the advice should be applied (e.g., "apply this to all methods in the Service layer").  
* **Target Object:** The object being advised (your actual business class).  
* **AOP Proxy:** A special object created by Spring to implement the Aspect contract.

**2\. Types of Advice (The "When")** Spring AOP provides different types of advice based on when you want the code to run:

* **@Before:** Runs before the target method is called.  
* **@After:** Runs after the method finishes (regardless of the outcome).  
* **@AfterReturning:** Runs only if the method completes successfully.  
* **@AfterThrowing:** Runs only if the method throws an exception.  
* **@Around:** The most powerful. It can run code both before and after the method and can even decide whether the method should run at all.

**3\. Dependency Required** To use AOP in your Spring Boot project, add this to your pom.xml:

* **Group ID:** org.springframework.boot  
* **Artifact ID:** spring-boot-starter-aop

**4\. How to Create an Aspect** You define an Aspect using the **@Aspect** and **@Component** annotations.

* **Example:**   
  @Aspect   
  @Component   
  public class LoggingAspect {  
   @Before("execution(\* com.example.service.\*.\*(..))")   
  public void logBefore() {   
  System.out.println("Method is about to start...");   
  }   
  }

**5\. Pointcut Expressions** This is how you tell Spring exactly where to apply your code.

* *execution( ...):*\* Matches method execution.  
* **within(...):** Limits matching to join points within certain types.  
* **@annotation(...):** Matches methods that have a specific custom annotation.

**6\. Benefits of using AOP**

* **Clean Code:** Your main business logic is not cluttered with logging or security checks.  
* **DRY (Don't Repeat Yourself):** You write the common logic in one place.  
* **Easy Maintenance:** If you need to change how you log data, you only change it in one Aspect class.

**7\. Best Practices**

* **Don't Overuse:** Use AOP for infrastructure tasks (logging, security), not for hidden business logic that makes the code hard to follow.  
* **Keep Pointcuts Specific:** Narrow down your Pointcuts to only the classes that truly need them to save performance.

