---
name: change-hexagonal-module
description: Use ONLY when creating or changing a Cedif backend module that must preserve its application, domain, and infrastructure hexagonal architecture.
---

# Change Hexagonal Module

Use this skill only under `business-domain/cedif/`. Do not apply feature-layered templates from other business services.

## Gate

Require a concrete change request, model or module, and the persistence source when creating a capability. Inspect the nearest Cedif implementation across `application`, `domain`, and `infrastructure` before editing. Ask for a complete DDL or stored-procedure body when the change needs one.

Ask before touching `shared-data`, another Cedif module, inbound or outbound contracts used by other modules, or broad architectural replacement.

## Rules

- Preserve domain models and ports in `domain`, use cases in `application`, and adapters in `infrastructure`.
- Select the existing Cedif persistence approach from evidence; do not assume JPA or `BaseOracleRepository`.
- Keep framework annotations and transport concerns out of the domain layer.
- Change ports and adapters together only when the inspected contract requires it.
- Follow the closest controller, DTO, error-handling, and mapping conventions in Cedif.

## Summary

Report the inspected reference, files changed by layer, ports or adapters affected, contracts preserved, external impact requiring confirmation, risks, and verification not run. Do not run tests, builds, compilations, services, or Git actions.
