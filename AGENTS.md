# AGENTS.md — SIGEIF Backend

Java 21 Spring Boot microservices (Maven). Two independent multi-module domains with no root POM.

## Project structure

```
architecture-domain/          # infra services
  config/                     # Spring Cloud Config Server (port 8888)
  eureka/                     # Eureka Server (port 8761)
  gateway/                    # Spring Cloud Gateway (port 4000)

business-domain/              # business services
  shared-data/                # Library: entities, DTOs, mappers, base classes
  sigesu/                     # Port 4004, default profile dev
  punche/                     # Port 4001, default profile prod
  cedif/                      # Port 4002, default profile prod
  scheduling/                 # Port 5000, default profile test
```

**Critical:** `shared-data` is a plain JAR, not a Spring Boot app. Business apps depend on it.

## Build

No root POM exists. Build each domain separately:

```bash
# From architecture-domain/
mvn clean install

# From business-domain/
mvn clean install
```

JARs are written to `../dist` (`output.dir` property). `shared-data` skips install via `maven-install-plugin` config but still compiles.

## Run a single service

From the module directory:

```bash
mvn spring-boot:run
```

Or from the domain parent with `-pl`:

```bash
mvn spring-boot:run -pl sigesu
```

## Tests

All tests are minimal `contextLoads()` smoke tests. Run per module:

```bash
mvn test
```

No integration tests or test infrastructure (H2, Testcontainers) are present.

## Codegen / annotations

- **MapStruct + Lombok:** The `maven-compiler-plugin` annotation processor order is enforced: Lombok first, then `lombok-mapstruct-binding`, then MapStruct processor. Do not change this order or mappers will fail to generate.
- **MapStruct config:** Use `@Mapper(config = BaseMapStructConfig.class)` to inherit spring component model and null-value-ignore policies.

## Architecture quirks

- **Entity scanning:** Because JPA entities live in `shared-data`, every business app must scan them explicitly:
  ```java
  @EntityScan(value = { "microservice.shared_data.entities" })
  ```
  Example: `SigesuApplication`.
- **Component scanning:** Some apps use `SharedComponentScanConfig` to scan both `microservice.<app>` and `microservice.shared_data` packages.
- **Gateway routing:** Routes in `gateway` use hardcoded `http://` URIs, **not** Eureka service discovery. The Eureka client dependency is commented out in all services.
- **Config server:** Uses `native` profile pointing to `file:${config.path:./config}`. The `config-server-repo/` at repo root contains the property files.
- **Flyway:** Dependency and `FlywayConfig` exist but are fully commented out. Database migrations are NOT active.

## Database

- Oracle (ojdbc11) with `OracleDialect`.
- `spring.jpa.hibernate.ddl-auto: validate` — schema must already exist.
- Default datasources by profile are baked into `application-{dev,test,prod}.yml`.

## Environment / profiles

Default `spring.profiles.active` varies by service:

| Service | Default profile |
|---------|-----------------|
| sigesu  | `dev`           |
| punche  | `prod`          |
| cedif   | `prod`          |
| scheduling | `test`       |
| gateway | `dev`           |

Override with `PROFILE` env var or `-Dspring.profiles.active=`.

## Security

Gateway contains a JWT filter (`JwtAuthenticationWebFilter`) and a `SecurityWebConfig`, but the Spring Security wiring is commented out. The filter bean itself loads but does not intercept requests. Gateway prod enables SSL via PKCS12 keystore.

## VS Code launch configs

`.vscode/launch.json` (both root and domain levels) defines Java launch targets for all services and references an `.env` file that does not exist in the repo.
