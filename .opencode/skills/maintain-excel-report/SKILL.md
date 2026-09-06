---
name: maintain-excel-report
description: Use ONLY when fixing or making a small change to an existing SIGEIF HTTP Excel report backed by Oracle stored procedures.
---

# Maintain Excel Report

Require microservice, module, and concrete change request. Verify that the module already contains the reporting flow, then inspect its controller, reporting service, module service, repository, and spreadsheet generation before editing. If it does not exist, stop and use creation.

## Rules

- Make minimal changes only: dataset columns, endpoint, filename, report method, procedure parameters, mapping, empty-dataset behavior, or similarly scoped reporting behavior.
- Preserve `BaseOracleRepository`, `List<Map<String, Object>>`, `BaseRestController`, and the existing reporting base pattern unless the approved scope explicitly changes one.
- Keep `NotFoundException()` for empty datasets unless the request explicitly changes the policy.
- Ask before changes outside the reporting area or any broad architecture rewrite.

## Summary

Report files edited, behavior fixed or added, procedure mapping changes, endpoint and filename changes, empty-dataset policy, risks, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
