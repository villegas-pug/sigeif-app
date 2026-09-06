---
name: maintain-layered-module
description: Use ONLY when fixing or making a small change to an existing SIGEIF layered JPA module in sigesu, punche, or educalle.
---

# Maintain Layered Module

Do not regenerate a module. This skill is only for existing JPA-backed layered modules, not `cedif`, procedure-only modules, reporting flows, or scheduling.

## Gate

Require microservice, model or module, and a concrete change request. Confirm the module exists and inspect its controller, DTOs, mappers, repositories, and services before proposing edits.

Ask before modifying shared-data, unrelated modules, broad architecture, or a physical-delete policy.

## Rules

- Make the smallest targeted change and preserve local package, endpoint, mapper, relationship, multipart, and logical-delete conventions.
- For an explicit controller-convention request, place missing null or empty validation in the service layer, then follow the target module's proven `ApiResponse` and `ResponseEntity` convention. Do not change unrelated endpoints.
- Preserve recursion controls and keep binary data out of JSON list and response DTOs unless explicitly requested.
- Never introduce stored-procedure persistence or JPA changes that conflict with the current architecture.

## Summary

Report files edited, functional behavior, API compatibility, controller-convention changes when requested, risks, unresolved questions, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
