# Java Assignments Workspace

This workspace contains two Java assignments. Each assignment has its own `pom.xml`, source code, and tests.

Structure
- `assignment1/` — contains a small producer/consumer example under package `visa.com`.
- `assignment2/` — contains a CSV reading and sales analysis example under package `intuit.com`.

Quick commands (PowerShell)

Run tests for `assignment1`:
```powershell
cd assignment1; mvn test
````

Run tests for `assignment2`:
```powershell
cd assignment2; mvn test
```

Run tests for both (from workspace root):
```powershell
cd D:\Intuit; mvn -pl assignment1,assignment2 test
```

Notes
- Requires Java and Maven installed and on `PATH`.
- Tests and build artifacts are written under each assignment's `target/` directory.
