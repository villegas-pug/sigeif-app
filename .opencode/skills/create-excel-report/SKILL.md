---
name: create-excel-report
description: Use ONLY when adding an HTTP Excel download report to an existing SIGEIF business module using an Oracle stored procedure dataset.
---

# Create Excel Report

Use this skill only for an HTTP download report in an existing business module. Do not use it for scheduled reporting.

## Gate

Require microservice, existing module, exact stored-procedure name, complete procedure body, report method name, endpoint, and filename. Inspect the module's repository, service, reporting service, and controller. If multiple controllers are valid, ask the user to choose.

The destination controller must extend `BaseRestController`; ask before changing inheritance. Ask before changing repository architecture if it does not already use `BaseOracleRepository`.

## Rules

- The procedure already exists; do not create it or a database migration.
- Infer parameters and cursor output from the procedure body.
- Preserve the chain: controller -> reporting service -> module service -> repository -> stored procedure.
- Retrieve datasets through `BaseOracleRepository` as `List<Map<String, Object>>` unless the approved request changes that contract.
- Use the existing Apache POI reporting base pattern and `BaseRestController.buildDownloadResponseEntity(...)`.
- Infer spreadsheet headers and values from dataset rows. Empty datasets must throw `NotFoundException()`.

## Summary

Report selected controller, methods added by layer, procedure mapping, endpoint, filename, empty-dataset policy, files changed, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
