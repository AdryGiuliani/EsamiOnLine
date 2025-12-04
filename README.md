# EsamiOnline Server (gRPC)

EsamiOnline is a distributed Software Engineering project that provides an online exam management system.  
This repository contains the **server‑side implementation** of the **gRPC services** for EsamiOnline.

A main application entry point and JUnit tests are provided, using a mocked gRPC client to validate server behavior.

---

## Project Context

- Course: **Ingegneria del Software** (Software Engineering), A.A. 2023/2024  
- Type: **Distributed** project  
- Author: **Giuliani Adriano**

---

## Overview

The project includes:

- gRPC service definitions and server implementation
- A main class to start the gRPC server
- JUnit tests using a mocked client to test the server logic

Typical responsibilities (adapt to the exact services):

- managing exam sessions and enrollments
- handling exam results and grades
- interacting with a client application (see the EsamiOnLine frontend module)

---

## Tech Stack

- Language: **Java**
- RPC framework: **gRPC**
- Build: **Maven** or **Gradle** (as defined in the project)
- Testing: **JUnit** (and possibly mocking libraries)

---

## Repository Relationship

The client/frontend module is available here:

- **EsamiOnLineClient**: <https://github.com/AdryGiuliani/EsamiOnLineClient>

---

## Development Notes

- gRPC service definitions and implementations are in the main source tree.
- Tests use a mocked client to:
  - verify correct responses
  - test error scenarios and edge cases
  - validate business rules independently from a real client

Check the source under `src/main` and `src/test` for exact package names, services, and test cases.

---

## Original Italian Description

> Implementazione lato server dei servizi gRPC di EsamiOnline.  
> È disponibile un main `App` e i vari test con JUnit effettuati con un mock del client.

---

## License
