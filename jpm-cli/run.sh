#!/bin/bash

echo pwd
../.graldew fatjar -q
java -jar jpm-cli/build/libs/CLI.jar "$@"
