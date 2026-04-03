## **Day 12 Learning (26/03/2026)**

**\#SPRING DATA JPA** 

**1\. WHAT IS SPRING DATA JPA?** 

Spring Data JPA is a part of the larger Spring Data family. It makes it easy to implement JPA (Jakarta Persistence API) based repositories. It reduces the amount of "boilerplate" code (repeated code) required to interact with a database. Instead of writing long SQL queries for basic tasks, Spring Data JPA allows you to focus on business logic.

**2\. KEY TERMINOLOGIES**

* **ORM (Object-Relational Mapping):** A technique to map Java Objects to Database Tables.  
* **JPA (Jakarta Persistence API):** A specification (set of rules) for ORM in Java.  
* **Hibernate:** The most popular "provider" or implementation of the JPA specification. Spring Data JPA uses Hibernate by default.

**3\. CORE ANNOTATIONS (MAPPING JAVA TO DATABASE)** 

These annotations are used on top of your Java classes (Entities).

* **@Entity:** Tells Spring that this class should be mapped to a database table.  
* **@Table(name \= "table\_name"):** Used to specify the exact name of the table in the database.  
* **@Id:** Marks a specific field as the Primary Key.  
* **@GeneratedValue:** Defines how the Primary Key is generated (e.g., `GenerationType.IDENTITY` for auto-increment).  
* **@Column:** Used to define column properties like `name`, `length`, `nullable` (true/false), and `unique`.  
* **@Transient:** Tells JPA to ignore this field; it will not be created as a column in the database.  
* **@Enumerated:** Used when you want to store Enum values in the database (use `EnumType.STRING` to store the name).

**4\. REPOSITORY LAYER (THE MAGIC)** 

In Spring Data JPA, you don't write implementation classes. You create an **Interface** that extends **JpaRepository**.

* **Syntax:** `public interface UserRepository extends JpaRepository<User, Long> {}`  
* **What you get for free:** You automatically get methods like `save()`, `findAll()`, `findById()`, `deleteById()`, and `count()` without writing a single line of code.

**5\. QUERY METHODS (DERIVED QUERIES)** 

You can create database queries just by naming your methods in the interface. Spring parses the method name to create the SQL.

* **Example:** `findByEmail(String email)` \-\> Runs `SELECT * FROM users WHERE email = ?`  
* **Example:** `findByNameAndAge(String name, int age)` \-\> Uses 'AND' logic.  
* **Example:** `findByNameContaining(String keyword)` \-\> Uses 'LIKE' logic.

**6\. CUSTOM QUERIES WITH @QUERY** 

For complex logic where method names become too long, use the `@Query` annotation.

* **JPQL (Java Persistence Query Language):** Focuses on Java classes/entities instead of table names.  
  * *Example:* `@Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")`  
* **Native Query:** Pure SQL that runs directly on your specific database (MySQL, PostgreSQL, etc.).  
  * *Example:* `@Query(value = "SELECT * FROM users", nativeQuery = true)`

**7\. TABLE RELATIONSHIPS**

* **@OneToOne:** One record in Table A relates to exactly one record in Table B.  
* **@OneToMany / @ManyToOne:** The most common relationship (e.g., one Department has many Employees).  
* **@ManyToMany:** Requires a "Join Table" to link multiple records on both sides.  
* **@JoinColumn:** Defines the physical "Foreign Key" column in the table.

**8\. ADVANCED FEATURES (MINOR THINGS YOU MUST KNOW)**

* **Pagination:** Use `Pageable` and `PageRequest` to fetch data in small chunks (e.g., 10 records per page) instead of loading 10,000 records at once.  
* **Sorting:** Use the `Sort` object to order your data by specific columns.  
* **@Transactional:** Use this on Service methods. It ensures that if one part of a database operation fails, the whole process "rolls back" so your data doesn't get corrupted.  
* **Auditing:** Use `@CreatedDate` and `@LastModifiedDate` to automatically track when a record was created or changed. You must add `@EntityListeners(AuditingEntityListener.class)` to your entity for this to work.  
* **Lombok Integration:** While not part of JPA, developers usually use Lombok annotations like `@Data`, `@NoArgsConstructor`, and `@AllArgsConstructor` to keep Entity classes clean by removing manual Getters and Setters.

**9\. THE WORKFLOW**

1. **Dependency:** Add `spring-boot-starter-data-jpa` in your `pom.xml`.  
2. **Configuration:** Define your DB URL, username, and password in `application.properties`.  
3. **Entity:** Create your class with `@Entity`.  
4. **Repository:** Create an interface extending `JpaRepository`.  
5. **Service:** Inject the Repository and use it to perform operations.

