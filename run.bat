
@echo off

call gradlew fatjar -q
cd build/libs
java -jar CLI-0.1.0.jar %*



