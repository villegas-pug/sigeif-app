---
name: create-layered-business-module
description: Use ONLY when creating a layered business feature module under business-domain/<microservice>/src/main/java/microservice/<microservice>/ using an existing module as architecture template.
---

# Create Layered Business Module

Use this skill only to create a modular layered business feature under a business microservice:

`business-domain/<microservice>/src/main/java/microservice/<microservice>/<module>/`

The reference architecture template is:

`business-domain/punche/src/main/java/microservice/punche/familiaintegrante/`

Use `familiaintegrante` as a mirror for structure, layers, class responsibilities, method style, repository flow, mapper flow, and `ApiResponse` controller responses. Do not copy its inconsistent class naming when it conflicts with the model-based naming rules below.

## Mandatory Inputs

Before creating or editing any file, request and validate all mandatory inputs:

1. Microservice name, for example `punche`.
2. Model name in PascalCase, for example `Persona` or `PersonaNatural`.
3. Either an existing entity name from `shared-data` or a complete database table structure.

Argument `$3` may contain either:

- An entity name such as `PersonaEntity` or `Persona`.
- A full DDL/table structure such as `CREATE TABLE ...`.

At least one source for the model body is required: existing shared-data entity or table structure.

If any mandatory input is missing, empty, incomplete, or ambiguous, stop and ask for the missing value. Do not create directories, do not create files, and do not infer the whole module until the mandatory gate is satisfied.

## Mandatory Gate

Apply this gate before any file change:

- If microservice is missing, empty, or ambiguous, stop and ask for the microservice name.
- If model name is missing, empty, or ambiguous, stop and ask for the model name.
- If both entity and table structure are missing, empty, or ambiguous, stop and ask for either an existing `shared-data` entity or the complete table structure.
- If `business-domain/<microservice>/` does not exist, stop and ask for a valid microservice.
- If `$3` looks like an entity name but no matching entity exists in `shared-data/entities/`, stop and ask whether to provide a DDL/table structure or the correct entity name.
- If `$3` looks like DDL/table structure but is incomplete, stop and ask for the complete table structure.
- If the target module already exists, stop and ask whether to complete missing files, overwrite specific files, or cancel. Do not overwrite existing files without confirmation.

## Naming Rules

Always derive names from the model name. The model name is the source of truth for class, interface, DTO, mapper, repository, service, controller, and method names.

For model `PersonaNatural` and microservice `punche`:

- Model: `PersonaNatural`.
- Module directory: `personanatural`.
- Package base: `microservice.punche.personanatural`.
- Controller: `PersonaNaturalController`.
- Service interface: `PersonaNaturalService`.
- Service implementation: `PersonaNaturalServiceImpl`.
- Repository interface: `PersonaNaturalRepository`.
- Repository implementation: `PersonaNaturalRepositoryImpl`.
- JPA repository: `PersonaNaturalJpaRepository`.
- Entity mapper: `PersonaNaturalEntityMapper`.
- Create mapper: `PersonaNaturalCreateMapper`.
- Update mapper: `PersonaNaturalUpdateMapper`.
- Create DTO: `CreatePersonaNaturalRequest`.
- Update DTO: `UpdatePersonaNaturalRequest`.
- Response DTO: `PersonaNaturalResponse`.

The module directory must be the model name concatenated and lowercased. Do not use hyphens, underscores, or package separators.

## Method And Endpoint Names

Use method names derived from the model:

- `create<Model>`.
- `update<Model>`.
- `find<Model>ById`.
- `findAll<Model>`.
- `delete<Model>ById`.
- Repository save method: `save`.

Use endpoint paths following the current microservice style:

- `/create<Model>`.
- `/update<Model>`.
- `/find<Model>ById`.
- `/findAll<Model>`.
- `/delete<Model>ById`.

If the model contains a binary/attachment field, also add:

- `GET /download<Model>` (or `GET /download<Field><Model>`) returning the file as `application/octet-stream`.

If the existing target microservice has a stronger nearby convention for route naming, follow the nearest matching feature and report the deviation.

## Files To Create

For each module, create the layered structure below unless the user asks for a smaller scope:

```text
business-domain/<microservice>/src/main/java/microservice/<microservice>/<module>/
  controller/<Model>Controller.java
  dtos/Create<Model>Request.java
  dtos/Update<Model>Request.java
  dtos/<Model>Response.java
  mappers/<Model>CreateMapper.java
  mappers/<Model>UpdateMapper.java
  mappers/<Model>EntityMapper.java
  model/<Model>.java
  repository/<Model>JpaRepository.java
  repository/<Model>Repository.java
  repository/<Model>RepositoryImpl.java
  service/<Model>Service.java
  service/<Model>ServiceImpl.java
```

## Source Of Truth For Model Body

Build the model body from this priority order:

1. Existing entity in `business-domain/shared-data/src/main/java/microservice/shared_data/entities/`.
2. Complete table structure provided in `$3`.

If both are available, use the entity as the primary source and the table structure only to validate table/column intent.

When using an entity:

- Read the entity fields, primary key, scalar columns, relationships, lifecycle/audit fields, Lombok style, and id types.
- Convert scalar entity fields to model fields with the same Java type and field name.
- Include `id...`, `estado`, `eliminado`, `usuRegistra`, `fecRegistra`, `usuActualiza`, and `fecActualiza` fields when present.
- For relationship fields, include the model type only when an equivalent model exists in the target microservice or is clearly needed by the architecture.
- Also include `id<Relation>` fields when the entity relationship is commonly written from request DTOs using IDs.
- Do not include JPA annotations in business models or DTOs.

When using DDL/table structure:

- Map column names to Java camelCase fields.
- SQL integer-like IDs should usually be `Long` for primary keys.
- SQL integer-like flags, status fields, and user IDs should usually be `Integer`.
- Character types should be `String`.
- SQL `DATE` should be `LocalDate` unless timestamp/time precision is explicit.
- Decimal or money types require confirmation unless precision and Java type are obvious from neighboring modules.
- Infer relationships from explicit foreign keys only when the referenced entity exists; otherwise keep scalar ID fields and report uncertainty.

If the user provides only DDL and the shared-data entity does not exist, ask whether they want to create the entity with the `create-shared-data-entity` skill before or after creating this module. Do not invoke another skill automatically unless the user requests it.

## Layer Patterns

Follow the `familiaintegrante` architecture, normalized by model name:

- Controller uses `@RestController`, `@AllArgsConstructor`, validation annotations, and returns `ResponseEntity<ApiResponse<...>>`.
- If the model has a binary/attachment field, the controller must accept `@RequestParam MultipartFile` for create/update and provide a separate `ResponseEntity<byte[]>` download endpoint with `Content-Disposition` headers.
- Service interface exposes model-derived methods.
- Service implementation uses `@Service`, `@AllArgsConstructor`, and `@Transactional` for create, update, delete, and read operations where existing modules do so.
- Repository interface hides JPA details from service.
- Repository implementation uses `@Repository`, `@AllArgsConstructor`, a JPA repository, and an entity mapper.
- JPA repository extends `JpaRepository<<Entity>, <IdType>>`.
- Create/update mappers convert request DTOs to the business model.
- Entity mapper converts between business model and shared-data entity.

Prefer the smallest complete CRUD module matching the architecture. Do not add custom queries unless the user asks or the table/entity requires a parent-filter method similar to a nearby module.

## Mapper Rules

Use MapStruct with the shared config:

```java
@Mapper(config = BaseMapStructConfig.class)
```

Create mapper:

- `<Model> toModel(Create<Model>Request create);`
- `List<<Model>> toModels(List<Create<Model>Request> creates);`

Update mapper:

- `<Model> toModel(Update<Model>Request update);`
- `List<<Model>> toModels(List<Update<Model>Request> updates);`

Entity mapper:

- `void fromModelToEntity(<Model> source, @MappingTarget <Entity> target);`
- `<Entity> toEntity(<Model> source);` only when useful and safe.
- `<Model> toModel(<Entity> source);`
- `List<<Model>> toModels(List<<Entity>> source);` when list endpoints are generated.
- `Set<<Model>> toModels(Set<<Entity>> source);` when entity uses sets.

## Recursion Control

Prevent mapper recursion aggressively.

Default behavior for `EntityMapper.toModel`:

- Ignore `@OneToMany` collections unless the user explicitly asks for nested collections.
- Ignore bidirectional relationship fields when they can point back to the current model.
- Ignore large attachment/blob fields in responses if existing patterns do so.
- Map `@ManyToOne` and `@OneToOne` only when the related business model exists and does not create cycles.
- Prefer scalar `id<Relation>` fields for write paths.

For each ignored relation, report it in the summary as intentionally ignored to prevent recursion.

When mapping IDs into relationship entities in `fromModelToEntity`, use `@Named` helper methods and builders when the target entity has a clear id field, for example:

```java
@Named("idToFamiliaEntity")
default PotencialFamiliaEntity mapIdToPotencialFamiliaEntity(Long id) {
   return id != null ? PotencialFamiliaEntity.builder().idFamilia(id).build() : null;
}
```

If the target entity lacks a builder or the id field is unclear, ask before generating the helper.

## DTO Rules

Generate DTO fields from the model/source entity with these defaults:

- Create DTO should include writable fields, relationship IDs, and registration user fields.
- Update DTO should include the primary key, writable fields, relationship IDs, and update user fields.
- Response DTO should include safe scalar fields and safe related models only.
- Add `jakarta.validation` annotations only when the source table has `NOT NULL`, the entity has validation, or the reference module pattern strongly supports it.
- Do not add validations based on guesses.

## Binary / Attachment Field Rules

When the source entity or table contains a binary column (`byte[]`, `Blob`, `BLOB`, `@Lob`, etc.), apply these rules:

1. **Do not expose the binary field in JSON DTOs by default.**
   - The Response DTO and list endpoints must return metadata only (id, filename, registration date, state, soft-delete flag, etc.).
   - Do not include the `byte[]` field in `Create...Request` or `Update...Request` when the controller will handle upload via `MultipartFile`.

2. **Upload via multipart/form-data in the controller.**
   - Use `@RequestParam MultipartFile <field>` in `create` and `update` endpoints.
   - Convert the file to bytes with `<field>.getBytes()` and pass them to the service / model.
   - If the entity/table also has a filename column, fill it with `<field>.getOriginalFilename()`.

3. **Add a separate download endpoint.**
   - Endpoint: `GET /download<Model>` or `GET /download<Field><Model>`.
   - Return `ResponseEntity<byte[]>` with headers:
     - `Content-Type: application/octet-stream`
     - `Content-Disposition: attachment; filename="<nombreArchivo>"`
   - Load the binary from the repository/service and stream it directly.

4. **MapStruct guidance for binary fields.**
   - In `EntityMapper.toModel`, ignore the binary field when mapping to the business model if the model is only used for list/response metadata.
   - In `EntityMapper.fromModelToEntity`, map the `byte[]` normally when the model carries the full payload (create/update flow).

5. **Repository / service handling.**
   - Save the `byte[]` through the JPA repository just like any other scalar field.
   - For logical delete, set `eliminado = 1` without touching the binary content.

## Repository Rules

Repository implementation should follow this flow:

- If primary key is null, create a new entity.
- If primary key exists, load the existing entity with `jpaRepository.findById(id).get()` following the current project style unless a nearby module uses explicit exception handling.
- Use `mapper.fromModelToEntity(model, entity)`.
- Save through JPA repository.
- Return `mapper.toModel(savedEntity)`.

Delete should be logical when the entity/model has an `eliminado` field:

- Load entity by id.
- Set `eliminado` to `1`.
- Save if the nearest pattern requires explicit save; otherwise follow the closest existing module style.
- Return mapped model.

If there is no `eliminado` field, ask before creating a physical delete.

## Relationship Reporting

Always report relationships in this format:

- Created relationship: field, source, target model/entity, mapper behavior.
- Mapped by id only: field/id field, reason.
- Ignored to prevent recursion: field, reason.
- Uncertain relationship: reason and question for the user.

Do not modify existing related modules or shared-data entities to add inverse relationships unless the user explicitly confirms.

## Execution Checklist

1. Confirm microservice is present and exists under `business-domain/`.
2. Confirm model name is present and PascalCase.
3. Confirm `$3` provides either an existing shared-data entity name or a complete table structure.
4. Resolve target package and module directory from the microservice and model.
5. Stop and ask if the target module already exists.
6. Inspect `familiaintegrante` and the nearest modules in the same microservice for style.
7. Inspect the shared-data entity when available.
8. Infer primary key field and id type.
9. Infer scalar model fields, DTO fields, and safe relationships.
10. Detect any binary/attachment fields (`byte[]`, `Blob`, `BLOB`, `@Lob`). If present, define multipart upload endpoints and a separate download endpoint. Exclude the binary field from JSON response DTOs.
11. Generate the layered files using model-derived names.
12. Add mapper recursion controls.
13. Ask before modifying existing files outside the new module.
14. Do not run tests, builds, compilations, commits, or `git push` automatically.
15. Summarize files created, fields inferred, relationships created/ignored, and any unresolved questions.
