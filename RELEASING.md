# Releasing tmdb-java to Maven Central

## Local testing

Note: on Windows, avoid Git Bash. It uses its own `gpg`.

```bash
./mvnw clean install -P release,heyuwe-sign -DskipTests
```

```powershell
.\mvnw.cmd clean install -P release,heyuwe-sign -DskipTests
```

## Preparing a release and deploying it to Maven Central
       
- Finalize [changelog](CHANGELOG.md)
- Update versions in [README](README.md)

```bash
git commit --all --message "Prepare for release 2.13.0"
```

Then from IDE run tasks (TODO: figure out how to use GUI pin entry from Git Bash):

- release:prepare
- release:perform
                   
Push to GitHub:
                                                                                      
```bash
git push origin main --tags
```

And [create a GitHub release](https://github.com/UweTrottmann/tmdb-java/releases/new).
