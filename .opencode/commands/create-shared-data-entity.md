---
description: Create a shared-data Java JPA Entity using the project skill. Requires $1 entity name and $2 table structure.
---

Use the create-shared-data-entity skill.

Mandatory inputs from command arguments:

1. Java entity name: `$1`
2. Complete database table structure: `$2`

Mandatory gate before any file change:

- If `$1` is missing, empty, or ambiguous, stop and ask for the Java entity name.
- If `$2` is missing, empty, or incomplete, stop and ask for the complete database table structure.
- Do not create files, do not infer the whole table, and do not continue until both mandatory inputs are provided.

Use `$ARGUMENTS` only as a raw reference to diagnose ambiguous input. `$1` and `$2` remain the mandatory inputs.

Optional input:

- Ask for related existing entities for `1 a *`, `* a 1`, or `1 a 1` relationships when useful.

When inputs are complete:

- Create the entity named `$1` under `business-domain/shared-data/src/main/java/microservice/shared_data/entities/`.
- Use `$2` as the source of truth for the table structure.
- Follow the current `shared_data/entities/` patterns for imports, Lombok, JPA annotations, naming, lifecycle methods, and relationships.
- Infer relationships from FKs and existing entities.
- Use `@JsonIgnoreProperties` to prevent serialization cycles.
- Notify which relationships were inferred.
- Ask before modifying existing entities for inverse relationships.
- Do not run tests, builds, compilations, commits, or `git push` automatically.

Full raw command arguments, for reference only:

`$ARGUMENTS`
