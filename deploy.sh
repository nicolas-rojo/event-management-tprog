#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MAVEN_CMD="$SCRIPT_DIR/../apache-maven-3.9.11/bin/mvn"

cd "$SCRIPT_DIR/tarea1"
"$MAVEN_CMD" clean package

cd "$SCRIPT_DIR/tarea2"
"$MAVEN_CMD" clean package

cd "$SCRIPT_DIR/tarea3"
"$MAVEN_CMD" clean package

cd "$SCRIPT_DIR"
