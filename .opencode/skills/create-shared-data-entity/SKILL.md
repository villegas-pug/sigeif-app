---
name: create-shared-data-entity
description: Use ONLY when creating Java JPA Entities under business-domain/shared-data/src/main/java/microservice/shared_data/entities from a database table structure.
---

# Create Shared Data Entity

Use this skill only to create or update Java JPA entities in:

`business-domain/shared-data/src/main/java/microservice/shared_data/entities/`

The package must be:

`microservice.shared_data.entities`

## Mandatory Inputs

Before creating or editing any entity, request and validate both required inputs:

1. Java entity name.
2. Complete database table structure to use as the source of truth.

If either input is missing, incomplete, or ambiguous, stop and ask for it. Do not create files, do not infer the whole table, and do not continue until both mandatory inputs are provided.

The table structure should include, when available:

- Table name.
- Column names.
- SQL data types.
- Primary key.
- Foreign keys.
- Nullable or not-null constraints.
- Identity, autoincrement, sequence, or default value details.
- Relevant unique constraints or indexes if they affect relationships.

## Optional Inputs

Optionally ask for related existing entities when the table has `1 a *`, `* a 1`, or `1 a 1` relationships.

If related entities are not provided, inspect existing files in `shared_data/entities/` and infer relationships from:

- Foreign key column names.
- Existing `@Table(name = "...")` annotations.
- Existing entity field naming patterns.
- Existing `@JoinColumn(name = "...")` annotations.

Always notify the user which relationships were inferred and whether they should be created.

## Current Entity Conventions

Follow the existing conventions in `shared_data/entities/`:

- Use `jakarta.persistence` imports.
- Use Lombok annotations already common in the module: `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, and `@EqualsAndHashCode(of = { "id..." })` when an entity has a clear primary key field.
- Use `@Entity` and `@Table(name = "TABLE_NAME")`.
- Use `@Id` and `@Column(name = "COLUMN_NAME")` for the primary key.
- Use `@GeneratedValue(strategy = GenerationType.IDENTITY)` only when the provided table structure indicates identity, autoincrement, or the existing matching pattern clearly requires it.
- Do not extend `AuditableEntity`; it is currently empty and not used as the active pattern.
- Keep SQL table and column names exactly as provided.
- Use Java camelCase field names.
- Keep the class name exactly as requested by the user, adding `Entity` only if the requested name or existing project naming pattern requires it.

## Type Mapping Guidance

Use the current codebase style when mapping SQL types:

- SQL integer-like IDs to `Long` for primary keys.
- SQL integer-like flags, status fields, and user IDs to `Integer` unless the table clearly requires another type.
- Character types to `String`.
- SQL `DATE` to `LocalDate`.
- SQL timestamp/date-time types to the closest existing project convention; ask if unsure.
- Decimal or money types require confirmation unless precision and desired Java type are obvious from neighboring entities.

## Relationship Rules

Prefer the existing relationship patterns:

- Foreign keys from the new table to another table are usually `@ManyToOne(fetch = FetchType.EAGER)` with `@JoinColumn(name = "FK_COLUMN")`.
- Inverse collections are usually `@OneToMany(mappedBy = "fieldName", fetch = FetchType.LAZY)`.
- Catalog or single-reference tables may use `@OneToOne` when existing entities use that pattern for similar references.
- Add `@JsonIgnoreProperties` on relationships to prevent serialization cycles, following the existing style.
- Use `Set<...>` or `List<...>` for inverse collections based on the nearest matching existing entity pattern.
- Do not create `@ManyToMany` unless the table structure explicitly supports it and the existing model has a clear matching pattern.

Before modifying any existing entity to add an inverse relationship, explicitly notify the user and ask for confirmation. Creating the new entity itself does not require extra confirmation once mandatory inputs are available.

When relationships are inferred, report them in this format before or along with the edit summary:

- Created relationship: field, annotation, join column, target entity.
- Recommended but not created: inverse relationship requiring modification to an existing entity.
- Uncertain relationship: reason and question for the user.

## Audit And Lifecycle Fields

If the table includes audit/status columns that match existing conventions, use the same style:

- Registration user: `usuRegistra`.
- Registration date: `fecRegistra` or `fechaRegistra`, matching the column prefix and nearest entity pattern.
- Update user: `usuActualiza`.
- Update date: `fecActualiza` or `fechaActualiza`, matching the column prefix and nearest entity pattern.
- State: `estado`.
- Soft delete flag: `eliminado`.

Add `@PrePersist` and `@PreUpdate` only when the table fields and neighboring entity patterns support default assignment. Do not invent lifecycle defaults if the table structure does not imply them.

## Imports And Formatting

Keep imports minimal and only include imports used by the generated entity.

Common imports include:

```java
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
```

Do not include unused imports.

## Execution Checklist

1. Confirm the Java entity name is present.
2. Confirm the full table structure is present.
3. Inspect existing `shared_data/entities/` files for matching table, naming, lifecycle, and relationship patterns.
4. Identify the target file path under `business-domain/shared-data/src/main/java/microservice/shared_data/entities/`.
5. Generate the smallest correct entity that matches the provided table structure.
6. Use `@JsonIgnoreProperties` on relationships when needed to avoid JSON serialization cycles.
7. Ask before editing existing entities for inverse relationships.
8. Do not run tests, builds, or compilations automatically.
9. Do not commit changes.
10. Summarize created fields, inferred relationships, and any relationships intentionally not created.
