---
name: maintain-procedure-module
description: Use ONLY when fixing or making a small change to an existing SIGEIF module that persists exclusively through Oracle stored procedures.
---

# Maintain Procedure Module

Do not regenerate the module. Require microservice, model or module, and a concrete change request. Verify the module exists and inspect controller, service, repository implementation, and relevant stored-procedure mapping before editing.

## Rules

- Preserve `BaseOracleRepository` persistence and do not add JPA repositories, shared-data entities, or entity mappers as a replacement.
- Preserve `List<Map<String, Object>>` for flexible query outputs unless the approved request explicitly changes the contract.
- Infer selectors, cursor names, and operation values from the existing implementation or complete procedure body; never assume their names.
- Preserve multipart upload, separate download, and response safety for BLOB fields.
- Ask before modifying shared-data, unrelated modules, the stored procedure, or broad architecture.

## Summary

Report files edited, functional behavior, procedure mapping changes, compatibility risks, unresolved questions, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
