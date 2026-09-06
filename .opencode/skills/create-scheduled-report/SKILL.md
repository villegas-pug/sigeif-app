---
name: create-scheduled-report
description: Use ONLY when creating a SIGEIF scheduling module that runs an Oracle stored procedure on cron and saves an Excel file.
---

# Create Scheduled Report

Use this skill only under `business-domain/scheduling/`. It is separate from HTTP reporting.

## Gate

Require a kebab-case module name, kebab-case report name, exact stored-procedure name, and either a complete procedure body or a complete IN parameter list. Confirm the cron expression; suggest `0 0 8 * * *` only as a default. Verify the module and report do not already exist.

The OUT cursor is `p_resultado`. Do not infer a different cursor name.

## Rules

- Follow the existing scheduling structure: properties, domain use case, infrastructure implementation, scheduler, and module exception.
- Use `BaseRepository`, `BaseReportingService`, and `BaseScheduler`; do not replace them or modify transversal scheduling infrastructure.
- Generate the Excel file from `List<Map<String, Object>>`. Throw the module-specific `NotFoundException` for an empty dataset.
- Add only the required block to `business-domain/scheduling/src/main/resources/application.yml`; do not modify profile files.
- Ask before editing `shared/`, existing scheduler configuration outside the new block, or another module.
- Never start the scheduling service or trigger schedulers as verification.

## Summary

Report module and report names, procedure inputs, cron and output configuration, files created or updated, empty-dataset policy, pending ambiguities, and verification not run. Do not run tests, builds, compilations, services, schedulers, or Git actions.
