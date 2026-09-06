---
name: create-layered-module
description: Use ONLY when creating a SIGEIF layered JPA business module in sigesu, punche, or educalle from an existing shared-data entity or complete table definition.
---

# Create Layered Module

Use this skill only for the feature-layered architecture in `sigesu`, `punche`, or `educalle`. Do not use it for `cedif`, `scheduling`, or procedure-only persistence.

## Gate

Require the microservice, PascalCase model, and either an existing shared-data entity or complete table definition. Verify the microservice and target module. If the module exists, ask whether to switch to maintenance; never overwrite it by default.

When only DDL is supplied and the entity does not exist, ask whether the approved scope includes creating it with `create-shared-entity`. Do not create it implicitly.

## Implementation Rules

- Inspect `familiaintegrante` and the nearest equivalent module in the target service. Use the latter when conventions differ.
- Derive module directory, packages, classes, methods, DTOs, mappers, repositories, services, and controller names from the model.
- Create the smallest complete module required: controller, create/update/response DTOs, business model, MapStruct mappers, JPA repository, repository abstraction and implementation, service interface and implementation.
- Use `@Mapper(config = BaseMapStructConfig.class)` and preserve processor assumptions in Maven.
- Keep JPA details in the repository layer. Do not expose JPA annotations in models or DTOs.
- Keep write relationships as scalar IDs unless safe nested mapping is required. Ignore cyclic collections and unsafe binary fields in response mappings.
- For BLOB fields, use multipart writes, a separate byte-download endpoint, and no binary payload in JSON responses by default.
- Prefer logical delete when `eliminado` exists; otherwise ask before physical delete.
- Follow the target module's controller convention. Do not impose a generic response signature when local evidence differs.

## Summary

Report files created, source entity or DDL used, fields, relationships mapped by ID or ignored for recursion, binary handling, endpoint conventions, unresolved questions, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
