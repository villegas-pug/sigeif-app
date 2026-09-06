---
name: create-procedure-module
description: Use ONLY when creating a SIGEIF module with persistence exclusively through an existing Oracle stored procedure and no JPA entity.
---

# Create Procedure Module

Use this skill for procedure-only modules. The repository implementation must extend `microservice.shared_data.repositories.BaseOracleRepository`.

## Gate

Require microservice, PascalCase model, exact procedure name, and complete stored-procedure body. Verify the target microservice and ensure the module does not exist. If it exists, stop and use maintenance.

From the procedure body, identify IN, OUT, and IN OUT parameters; Oracle types; cursor output; CRUD selector parameter and values; and binary behavior. Do not assume a selector name or operation value. Stop if list behavior or selectors cannot be inferred.

## Rules

- Do not create a shared-data entity, `JpaRepository`, or entity mapper.
- Use `BaseOracleRepository` methods appropriate to the procedure, including map-based cursor results as `List<Map<String, Object>>` by default.
- Use `SqlLobValue` for BLOB input when required.
- Create the smallest module with controller, request DTOs, model, repository abstraction and implementation, and service interface and implementation. Add response DTOs or request mappers only when useful.
- Follow the nearest procedure-only module for routes, parameter mapping, multipart uploads, and separate binary downloads.

## Summary

Report selector and operation mapping, parameter and cursor handling, files created, binary behavior, unresolved ambiguities, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
