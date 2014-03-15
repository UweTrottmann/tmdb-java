Write-Host "mvn clean verify -DskipTests=true -P sonatype-oss-release"
mvn clean verify -DskipTests=true -P sonatype-oss-release

Write-Host "Packaging into bundle.jar..."
cd ./target
jar -cvf bundle.jar *.pom *.jar *.asc
Write-Host "Packaging into bundle.jar...DONE"

Read-Host "Press any key to exit..."