# PFM — Personal Finance Manager

Personal Finance Management application.

## Stack

- Java 21 · Spring Boot 3.x
- PostgreSQL
- Liquibase (migrations)
- Testcontainers (integration tests)
- Cucumber (acceptance tests / BDD)
- Spring Cloud OpenFeign (HTTP client)
- OpenAPI / Swagger (API specification)
- Lombok (boilerplate reduction)
- MapStruct (DTO mapping)
- Mountebank (service mocking in tests)
- DataFaker (test data generation)

## Prerequisites

- JDK 21
- Docker (Docker Desktop, Rancher Desktop or similar)
- Maven (not required, the Maven Wrapper `mvnw` downloads it automatically)

## Running locally

```bash
# 1. Start the database
docker compose -f finance/resources/docker-compose-local.yaml up -d

# 2. Start the application
./finance/mvnw spring-boot:run -pl finance-app -f finance/pom.xml
```

The app will be available at `http://localhost:8080`.

## Tests

```bash
./finance/mvnw test -pl finance-app -f finance/pom.xml          # unit tests (fast, no Docker)
./finance/mvnw verify -f finance/pom.xml                        # unit + integration + acceptance tests (requires Docker)
```

## Architecture

### Module structure

```
finance/
├── finance-app/              # Main application
└── finance-acceptance-test/  # BDD acceptance tests (Cucumber)
```

### Hexagonal Architecture (Ports & Adapters)

Each domain is organized into three layers:

```
com.afpr.pfm.finance.{domain}/
├── domain/           # Business logic — no framework dependencies
│   ├── DomainClass.java
│   └── Interface.java  (port/interface)
├── application/      # Use cases — orchestrates domain
│   └── Service.java
└── infrastructure/   # Adapters — framework-specific implementations
    ├── http/         # Inbound: REST controllers + mappers
    └── persistence/  # Outbound: JPA repositories + entities + mappers
```

Cross-cutting concerns (`shared/`): custom validation, exception handling, global REST error mapping.

### API-first

OpenAPI spec (`spec/openapi.yaml`) is defined before coding. Controllers implement the generated interface.

### Testing layers

| Layer       | Technology                      | Scope                              |
|-------------|---------------------------------|------------------------------------|
| Unit        | JUnit 5 + Mockito               | Services, mappers, validators      |
| Integration | Testcontainers (PostgreSQL)     | Repository implementations         |
| Acceptance  | Cucumber + Mountebank           | Full HTTP flows against running app |

## Environment Variables

| Variable      | Default                                        | Description         |
|---------------|------------------------------------------------|---------------------|
| `DB_URL`      | `jdbc:postgresql://localhost:5432/pfm_finance` | Datasource URL      |
| `DB_USERNAME` | `pfm`                                          | Database user       |
| `DB_PASSWORD` | `pfm`                                          | Database password   |
| `SERVER_PORT` | `8080`                                         | HTTP port           |

## CI/CD — GitHub Actions with Self-Hosted Runner

The project uses **GitHub Actions** with a **self-hosted runner** on your local machine to build, test, and deploy to PRE/PROD without consuming GitHub Actions minutes.

### Prerequisites

- A **self-hosted runner configured and linked to this GitHub repository**. See [GitHub Actions Runner Setup](https://docs.github.com/en/actions/hosting-your-own-runners) for instructions.
- PowerShell 5.1+ installed on the runner machine
- Docker installed and running on the runner machine

### Initial Setup (one-time)

**1. Create system environment variable `PFMREPO`:**

In PowerShell as administrator:

```powershell
setx PFMREPO "C:\path\to\pfm\repository"
```

Then **restart any terminal or IDE** for the variable to be available.

**2. Configure environment-specific variables:**

Create `.env.pre` and `.env.prod` in `finance/resources/env_config/` (excluded from git via `.gitignore`):

```powershell
# Example .env.pre
ENVIRONMENT=pre
DB_NAME=pfm_test
DB_USERNAME=pfm
DB_PASSWORD=your_secure_password
DB_PORT=5432
APP_PORT=8080
```

### Pipeline

**Automatic trigger:**
1. `git push` to `main` with changes in `finance/**` or `.github/workflows/build.yml`
2. `build.yml` compiles, runs tests, generates versioned JAR saved in releases folder
3. `deploy.yml` triggers automatically, builds Docker image, deploys with `docker-compose`

**Manual trigger:**
- Go to GitHub Actions → `Deploy Finance App` → `Run workflow`
- Select `version` and `environment` (pre or prod)
