# PFM — Personal Finance Manager

Aplicación para gestión de finanzas personales.

## Stack

- Java 21 · Spring Boot 3.x
- PostgreSQL
- Liquibase (migraciones)
- Testcontainers (tests de integración)

## Requisitos previos

- JDK 21
- Docker (Docker Desktop o Rancher Desktop)

## Arrancar en local

```bash
# 1. Levantar la base de datos
docker compose -f finance/resources/docker-compose-local.yaml up -d

# 2. Arrancar la aplicación
./mvnw spring-boot:run -pl finance-app
```

La app queda disponible en `http://localhost:8080`.

## Tests

```bash
mvn test      # unitarios (rápido, sin Docker)
mvn verify    # unitarios + integración (requiere Docker)
```

## Variables de entorno

| Variable      | Por defecto                                    | Descripción         |
|---------------|------------------------------------------------|---------------------|
| `DB_URL`      | `jdbc:postgresql://localhost:5432/pfm_finance` | URL del datasource  |
| `DB_USERNAME` | `pfm`                                          | Usuario de la BD    |
| `DB_PASSWORD` | `pfm`                                          | Contraseña de la BD |
| `SERVER_PORT` | `8080`                                         | Puerto HTTP         |
