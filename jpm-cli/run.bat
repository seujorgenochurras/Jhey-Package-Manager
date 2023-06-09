
@echo off

call gradlew fatjar -q
java -jar build/libs/CLI.jar %*
