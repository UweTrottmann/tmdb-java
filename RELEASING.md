# Releasing tmdb-java to Maven Central

## Local testing

```bash
./mwnw clean install -P release,heyuwe-sign
```

## Preparing a release and deploying it to Maven Central
       
- Finalize [changelog](CHANGELOG.md)
- Update versions in [README](README.md)

```bash
git commit --all --message "Prepare for release 2.12.0"
```

Then from IDE run tasks (TODO: figure out how to use GUI pin entry from Git Bash):

- release:prepare
- release:perform
