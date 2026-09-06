---
name: create-shared-entity
description: Use ONLY when creating or updating a Java JPA entity in shared-data from a complete SIGEIF Oracle table definition.
---

# Create Shared Entity

Create or update entities only under `business-domain/shared-data/src/main/java/microservice/shared_data/entities/` in package `microservice.shared_data.entities`.

## Gate

Require a Java entity name and complete table definition: table, columns, SQL types, primary key, foreign keys, nullability, and known identity, sequence, default, or unique details. Stop for missing or ambiguous information. Inspect existing entities before editing.

Ask for confirmation before modifying an existing entity, including adding an inverse relationship. Do not invent identity strategy, lifecycle defaults, decimal mappings, or relationships.

## Implementation Rules

- Follow the nearest entity's Jakarta Persistence and Lombok conventions.
- Keep SQL names exact and use Java camelCase fields.
- Use `@Entity`, `@Table`, `@Id`, and `@Column` as supported by the source definition.
- Use `@GeneratedValue(strategy = GenerationType.IDENTITY)` only when the source or nearest matching entity proves that strategy.
- Map IDs, dates, audit fields, status, and soft delete from existing conventions; ask when type precision is unclear.
- Infer relationships only from explicit foreign keys and existing target entities. Use `@JsonIgnoreProperties` where the local pattern needs cycle protection.
- Do not use `AuditableEntity` as an active base class.

## Summary

Report the entity file, mapped fields, created relationships, inverse relationships requiring confirmation, uncertainties, and verification not run. Do not run tests, builds, compilations, or Git actions.
