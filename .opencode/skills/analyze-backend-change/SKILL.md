---
name: analyze-backend-change
description: Use ONLY when analyzing a SIGEIF backend creation, maintenance, procedure, reporting, scheduling, shared-data, or Cedif change before implementation.
---

# Analyze Backend Change

Use this skill only for technical analysis. Do not edit files or choose implementation details not supported by the inspected code.

## Required Analysis

1. Inspect the requested microservice, module, nearest equivalent implementation, and relevant shared-data types.
2. Classify one primary architecture:
   - Layered JPA module in `sigesu`, `punche`, or `educalle`.
   - Hexagonal module in `cedif`.
   - Procedure-only module using `BaseOracleRepository`.
   - HTTP Excel reporting in an existing business module.
   - Scheduled report under `business-domain/scheduling/`.
3. Ask only for missing mandatory inputs. A database change requires complete DDL; a stored-procedure flow requires its exact name and complete body, except scheduled reporting may instead use a complete IN parameter list.
4. Identify cross-module, shared-data, controller, and configuration impact. Require confirmation for each impact outside the target module.
5. Do not expose datasource credentials, JWT secrets, or other sensitive configuration values.

## Routing

| Architecture | Implementation specialist | Skill |
|---|---|---|
| Shared-data entity | `backend-module-implementer` | `create-shared-entity` |
| Layered creation | `backend-module-implementer` | `create-layered-module` |
| Layered maintenance or explicit controller convention | `backend-module-implementer` | `maintain-layered-module` |
| Cedif change | `backend-module-implementer` | `change-hexagonal-module` |
| Procedure-only creation | `oracle-procedure-builder` | `create-procedure-module` |
| Procedure-only maintenance | `oracle-procedure-builder` | `maintain-procedure-module` |
| HTTP Excel reporting | `backend-reporting-api-builder` | `create-excel-report` or `maintain-excel-report` |
| Scheduled report | `backend-scheduling-builder` | `create-scheduled-report` |

## Output Contract

Return exactly these sections:

1. `Intent and route`: requested outcome, detected architecture, specialist, and skill.
2. `Confirmed inputs`: values supplied and mandatory values still missing.
3. `Evidence inspected`: files and nearby patterns used as references.
4. `Implementation scope`: target files, expected files, and explicit exclusions.
5. `Contracts to preserve`: API, persistence, mapping, reporting, and configuration contracts.
6. `Required confirmations`: shared-data, unrelated modules, controller inheritance, physical delete, cron, or other cross-cutting changes.
7. `Risks and verification`: risks, secrets redacted, and checks that must not run automatically.

Do not return an implementation plan until all mandatory inputs are confirmed.
