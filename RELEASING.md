# Releasing tmdb-java to Maven Central

## Local testing

```bash
./mwnw clean install -P release,heyuwe-sign
```

## Preparing a release and deploying it to Maven Central

```bash
./mvnw release:prepare

./mvnw release:perform
```
