# AGENTS.md - SIGEIF Backend

Java 21 Spring Boot microservices (Maven). Two independent multi-module domains with no root POM.

## OpenCode Backend Workflow

Backend work uses the built-in OpenCode primaries and project subagents. There is no custom primary `backend` agent and no project backend commands.

- `Plan` must delegate every backend technical analysis to `backend-change-analyst`; it must not perform the analysis or edit files itself.
- `Build` must delegate every backend implementation to the specialist selected by the analyst; it must not load backend skills or edit files itself.
- Specialists own their allowlisted skills and must not delegate or use another backend workflow.
- Do not use legacy global `/be-*` commands in this repository. They remain global until their later, separately approved removal.
- Before implementation, require the analyst's contract or ask only for the contract's missing mandatory inputs.

| Route | Specialist | Skills |
|---|---|---|
| Analysis | `backend-change-analyst` | `analyze-backend-change` |
| Shared-data and business modules | `backend-module-implementer` | `create-shared-entity`, `create-layered-module`, `maintain-layered-module`, `change-hexagonal-module` |
| Procedure-only modules | `oracle-procedure-builder` | `create-procedure-module`, `maintain-procedure-module` |
| HTTP Excel reports | `backend-reporting-api-builder` | `create-excel-report`, `maintain-excel-report` |
| Scheduled reports | `backend-scheduling-builder` | `create-scheduled-report` |

## Operational Safety

- Do not run tests, builds, compilations, package installation, development servers, schedulers, deployments, commits, pushes, staging, rebases, resets, or amend operations unless the user explicitly requests that exact action.
- Smoke tests load Spring contexts against Oracle-backed profiles; they are not safe automatic verification. Scheduling tests can activate jobs.
- Never copy, echo, log, or reproduce credentials, datasource URLs with secrets, keystore details, JWT secrets, or `.env` content.
- Prefer the smallest correct change. Ask before changing `shared-data`, another module, transversal infrastructure, or an existing public contract outside the approved scope.

## Project structure

```
architecture-domain/          # infra services
  config/                     # Spring Cloud Config Server (port 8888)
  eureka/                     # Eureka Server (port 8761)
  gateway/                    # Spring Cloud Gateway (port 4000)

business-domain/              # business services
  shared-data/                # Library: entities, DTOs, mappers, base classes
  sigesu/                     # Port 4004, default profile prod
  punche/                     # Port 4001, default profile prod
  cedif/                      # Port 4002, default profile prod
  educalle/                   # Port 4005, default profile prod
  scheduling/                 # Port 5000, default profile prod
```

**Critical:** `shared-data` is a plain JAR, not a Spring Boot app. Business apps depend on it.

## Build (Only On Explicit Request)

No root POM exists. Build each domain separately:

```bash
# From architecture-domain/
mvn clean install

# From business-domain/
mvn clean install
```

JARs are written to `../dist` (`output.dir` property). `shared-data` skips install via `maven-install-plugin` config but still compiles.

## Run A Single Service (Only On Explicit Request)

From the module directory:

```bash
mvn spring-boot:run
```

Or from the domain parent with `-pl`:

```bash
mvn spring-boot:run -pl sigesu
```

## Tests (Only On Explicit Request)

Only six modules contain minimal `contextLoads()` smoke tests. They use the configured Oracle-backed profiles; no H2 or Testcontainers isolation is present. Do not run them automatically.

```bash
mvn test
```

There are no integration tests or test infrastructure (H2, Testcontainers).

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
- **Business module styles:** `sigesu`, `punche`, and `educalle` use feature-layered modules. `cedif` uses application/domain/infrastructure hexagonal organization. `scheduling` uses properties, use cases, infrastructure implementations, and schedulers. Detect the style before selecting a template.

## Database

- Oracle (ojdbc11) with `OracleDialect`.
- `spring.jpa.hibernate.ddl-auto: validate` — schema must already exist.
- Default datasources by profile are baked into `application-{dev,test,prod}.yml`.

## Environment / profiles

Default `spring.profiles.active` varies by service:

| Service | Default profile |
|---------|-----------------|
| sigesu  | `prod`          |
| punche  | `prod`          |
| cedif   | `prod`          |
| educalle | `prod`         |
| scheduling | `prod`       |
| gateway | `dev`           |

Override with `PROFILE` env var or `-Dspring.profiles.active=`.

## Security

Gateway contains a JWT filter (`JwtAuthenticationWebFilter`) and a `SecurityWebConfig`, but the Spring Security wiring is commented out. The filter bean itself loads but does not intercept requests. Gateway prod enables SSL via PKCS12 keystore.

## VS Code launch configs

`.vscode/launch.json` (both root and domain levels) defines Java launch targets for all services and references an `.env` file that does not exist in the repo.
