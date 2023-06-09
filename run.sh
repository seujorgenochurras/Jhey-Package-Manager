#!/bin/bash

./gradlew fatjar -q

java -jar build/libs/CLI.jar "$@"
