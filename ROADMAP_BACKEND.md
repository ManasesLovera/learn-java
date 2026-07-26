# Java 21 Backend & Enterprise Mastery Roadmap

## 1. Apache Maven & Project Lifecycle Management

- **Maven Architecture & Fundamentals:**

  - Project Object Model (`pom.xml`) anatomy: `groupId`, `artifactId`, `version` (GAV coordinates).
  - Dependency Management: Declaring `<dependencies>`, managing transitives, and understanding dependency scopes (`compile`, `provided`, `runtime`, `test`).
  - Maven Central Repository & Local Repository (`~/.m2/repository`).

- **Maven Build Lifecycle & CLI Commands:**

  - The 3 standard lifecycles: `default` (build & deploy), `clean` (cleanup), `site` (documentation generation).
  - Key Build Phases: `validate` -> `compile` -> `test` -> `package` -> `verify` -> `install` -> `deploy`.
  - Common CLI Commands: `mvn clean package`, `mvn test`, `mvn dependency:tree` (debugging conflicts), `mvn compile`.

- **Plugins & Wrapper:**

  - Configuring plugins in `<build><plugins>` (e.g., `maven-compiler-plugin` targeting Java 21).
  - Maven Wrapper (`mvnw` / `mvnw.cmd`): Ensuring reproducible builds across environments without requiring globally installed Maven.

## 2. Cryptography & Security Fundamentals

- **Hashing & Salting:**

  - One-Way Hash Functions: SHA-256 vs. cryptographically secure password hashing algorithms (BCrypt, Argon2, PBKDF2).
  - Salt & Pepper mechanics to defend against rainbow table attacks.

- **Symmetric & Asymmetric Encryption:**

  - **Symmetric Encryption:** Single key encryption using AES-GCM (Authenticated Encryption) via `javax.crypto.Cipher`.
  - **Asymmetric Encryption:** Public/Private key pairs using RSA or ECC (Elliptic Curve Cryptography) for key exchange and digital signatures (`java.security.KeyPairGenerator`).

- **Java Security API & SSL/TLS:**

  - Working with `java.security.KeyStore` for storing keys, secrets, and X.509 certificates.
  - Generating cryptographically strong random numbers with `java.security.SecureRandom`.
  - Transport Layer Security (TLS/SSL): Sockets, `SSLContext`, TrustManager, and KeyManager.

## 3. Authentication & Authorization Architecture

- **Authentication Models:**

  - **Stateful (Session-based):** Cookie-based session IDs stored in server memory or Redis.
  - **Stateless (Token-based):** JSON Web Tokens (JWT) containing cryptographically signed claims.
  - **OAuth 2.0 & OpenID Connect (OIDC):** Authorization Code Flow with PKCE (Proof Key for Code Exchange), Resource Server, Identity Provider (IdP) integration.

- **Authorization & Access Control:**

  - **Role-Based Access Control (RBAC):** Assigning permissions to roles (e.g., `ROLE_ADMIN`, `ROLE_USER`).
  - **Attribute-Based Access Control (ABAC):** Dynamic authorization policies based on user attributes, resource context, and environmental conditions.
  - **Method Security:** Annotating Java methods to enforce access rules prior to execution.

- **Token Management & Security Best Practices:**

  - JWT Anatomy: Header, Payload (claims), and Signature verification using HMAC-SHA256 or RSA.
  - Token handling strategies: Short-lived Access Tokens + long-lived Refresh Tokens with token revocation mechanisms.

## 4. Software Architecture & Package Structure Patterns

- **Folder & Package Structure Strategies:**

  - **Standard Layered Architecture (Tiered):**

    ```text
    com.example.app/
    ├── config/          # Bean configurations, security setup
    ├── controller/      # REST API Endpoints / Web controllers
    ├── service/         # Business logic layer
    ├── repository/      # Database access interfaces
    ├── model/           # JPA Entities & Domain objects
    └── dto/             # Data Transfer Objects (Records)
    ```

  - **Hexagonal Architecture (Ports & Adapters):**

    ```text
    com.example.app/
    ├── domain/          # Pure core business entities & interfaces (Zero Framework Dependencies)
    ├── application/     # Use cases & port definitions (Inbound/Outbound)
    └── infrastructure/  # Framework code, DB adapters, REST controllers, HTTP clients
    ```

  - **Feature-Based Package Layout (Vertical Slice):**

    ```text
    com.example.app/
    ├── user/            # UserController, UserService, UserRepository, User (Entity)
    ├── order/           # OrderController, OrderService, OrderRepository, Order (Entity)
    └── common/          # Shared utilities, global error handling
    ```

- **Backend Design Patterns in Java:**

  - **Creational:** Factory Method (creating complex objects), Builder Pattern (creating immutable DTOs/Records), Singleton (thread-safe eager/enum implementation).
  - **Structural:** Adapter Pattern (converting external API responses), Strategy Pattern (replacing complex `if/else` logic with dynamic behaviors), Decorator Pattern (wrapping functionality).
  - **Behavioral:** Chain of Responsibility (Filter chains / middleware), Observer Pattern (Domain event listeners), Repository Pattern (abstracting persistence).

## 5. Persistence Layer & Hibernate ORM Best Practices

- **JPA & Hibernate Core Annotations:**

  - Entity mapping: `@Entity`, `@Table`, `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY/SEQUENCE)`.
  - Column configuration: `@Column(nullable = false, unique = true)`, `@Enumerated(EnumType.STRING)`.

- **Entity Relationship Mapping:**

  - Relationship types: `@ManyToOne`, `@OneToMany`, `@OneToOne`, `@ManyToMany`.
  - Ownership side management using `mappedBy` and join tables (`@JoinTable`, `@JoinColumn`).

- **Hibernate Performance & Production Best Practices:**

  - **Fetch Strategies:** ALWAYS prefer `FetchType.LAZY` over `FetchType.EAGER` to prevent unnecessary database hits.
  - **N+1 Query Problem:** Diagnosis and solutions using `JOIN FETCH` queries, Entity Graphs (`@EntityGraph`), or DTO Projections.
  - **DTO Projections:** Selecting only necessary fields into Java 21 Records directly from JPQL/SQL queries instead of loading full managed entities.
  - **Caching:** First-level cache (Persistence Context), Second-level cache (Ehcache/Hazelcast), and Query Cache tuning.

- **Database Migrations:**

  - Automated schema migrations using **Flyway** or **Liquibase**.
  - Writing versioned migration scripts (`V1__create_tables.sql`, `V2__add_indexes.sql`) to keep database structures consistent across dev, staging, and production environments.

## 6. Spring Boot 3 Framework Mastery (JDK 21 Baseline)

- **Spring Core & Inversion of Control (IoC / DI):**

  - Dependency Injection primitives: `@Component`, `@Service`, `@Repository`, `@Bean`, `@Configuration`.
  - Best Practice: Constructor Injection over field injection (`@Autowired`).

- **RESTful API Development & Validation:**

  - Endpoints: `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`.
  - DTO mapping using **Java 21 Records**.
  - Request validation using Jakarta Bean Validation (`@Valid`, `@NotNull`, `@NotBlank`, `@Size`).
  - Centralized Error Handling: `@ControllerAdvice` and `@ExceptionHandler` producing standardized RFC 7807 `ProblemDetail` JSON responses.

- **Spring Data JPA:**

  - Repository interfaces: `JpaRepository<Entity, ID>` or `ListCrudRepository<Entity, ID>`.
  - Derived query methods (`findByEmailAndStatus`), custom JPQL/Native queries with `@Query`.

- **Spring Security 6 & JWT:**

  - Configuring `SecurityFilterChain` bean.
  - Stateless authentication with custom `OncePerRequestFilter` for JWT parsing and authorization.
  - Method-level authorization: `@EnableMethodSecurity` and `@PreAuthorize("hasRole('ADMIN')")`.

- **Java 21 Virtual Threads Integration:**

  - Enabling Virtual Threads in Spring Boot 3.2+:

    ```properties
    spring.threads.virtual.enabled=true
    ```

  - Understanding how Virtual Threads increase request throughput under heavy I/O operations without manual thread pool tuning.

## 7. Quarkus Framework Mastery (Cloud-Native & Reactive)

- **Quarkus Architecture & Concepts:**

  - Supersonic Subatomic Java: Build-time optimization, low memory footprint, fast startup times.
  - Native Executables: Compiling Java code to native binaries using GraalVM (`./mvnw package -Dnative`).

- **Reactive Web APIs with RESTEasy Reactive:**

  - Defining endpoints using JAX-RS / Jakarta REST annotations: `@Path`, `@GET`, `@POST`, `@Produces(MediaType.APPLICATION_JSON)`.
  - Non-blocking reactive programming model support.

- **Persistence with Hibernate ORM with Panache:**

  - **Active Record Pattern (`PanacheEntity`):** Entities containing static query helper methods (`User.find("email", email).firstResult()`).
  - **Repository Pattern (`PanacheRepository<User>`):** Decoupled repository layer for domain separation.

- **Quarkus Security & Developer Experience:**

  - SmallRye JWT integration for securing REST endpoints with `@RolesAllowed`.
  - **Quarkus Dev Mode (`./mvnw quarkus:dev`):** Instant live reload, interactive Dev UI, and Dev Services (automatic test container provisioning for databases).

## Recommended Progression & Milestone Projects

| Phase | Core Focus | Milestone Project |
| --- | --- | --- |
| **Phase 1** | Maven & Cryptography | CLI Password Vault & Hashing Utility (Built with Maven & `javax.crypto`) |
| **Phase 2** | JDBC, HikariCP & Hibernate | Raw JDBC vs. Hibernate ORM Data Access Benchmark with Flyway Migrations |
| **Phase 3** | Spring Boot 3 & JWT | Secure User Management REST API with Spring Boot 3, Spring Security, JWT, & Virtual Threads |
| **Phase 4** | Quarkus & Cloud Native | High-Performance Microservice built with Quarkus Panache & compiled to GraalVM Native Image |
