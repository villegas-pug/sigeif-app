---
description: Create a layered business module using the project skill. Requires $1 microservice, $2 model, and $3 entity name or table structure.
---

Use the create-layered-business-module skill.

Mandatory inputs from command arguments:

1. Microservice name: `$1`
2. Model name: `$2`
3. Existing shared-data entity name or complete database table structure: `$3`

Mandatory gate before any file change:

- If `$1` is missing, empty, or ambiguous, stop and ask for the microservice name.
- If `$2` is missing, empty, or ambiguous, stop and ask for the model name.
- If `$3` is missing, empty, ambiguous, or incomplete, stop and ask for an existing `shared-data` entity name or the complete table structure.
- Do not create directories, do not create files, and do not infer the whole module until all mandatory inputs are provided.

Use `$ARGUMENTS` only as a raw reference to diagnose ambiguous input. `$1`, `$2`, and `$3` remain the mandatory inputs.

When inputs are complete:

- Create the module under `business-domain/$1/src/main/java/microservice/$1/<module>/`.
- Derive `<module>` from `$2` by concatenating the model name and lowercasing it.
- Use `business-domain/punche/src/main/java/microservice/punche/familiaintegrante/` as the architecture template.
- Infer class, interface, DTO, mapper, repository, service, controller, method, and endpoint names from `$2`.
- Use `$3` as either the shared-data entity reference or the table-structure source for the model body.
- Always control mapper recursion by ignoring cyclic relationship fields unless explicitly requested.
- Ask before modifying existing modules/entities outside the new module.
- Do not run tests, builds, compilations, commits, or `git push` automatically.

Full raw command arguments, for reference only:

`$ARGUMENTS`
