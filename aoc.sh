#!/usr/bin/env bash

set -euo pipefail

help() {
    PROG_NAME=$(basename "$0")
    cat <<EOH
Usage:
  $PROG_NAME COMMAND [COMMAND_OPTIONS]

Commands:
  init <DAY>: download puzzle and input and create classes
  help: show this message
EOH
    exit 0
}

init() {
  aoc download -i src/test/resources/input_$1.txt -p src/puzzles/puzzle_$1.md -d $1
  cp src/main/scala/aoc/Day00.scala src/main/scala/aoc/Day$1.scala
  cp src/test/scala/aoc/Day00.scala src/test/scala/aoc/Day$1.scala
  sed -i "s/00/$1/g" src/main/scala/aoc/Day$1.scala
  sed -i "s/00/$1/g" src/test/scala/aoc/Day$1.scala
}

part2() {
  DAY="$1"
  aoc download -o -P -p src/puzzles/puzzle_$DAY.md -d $DAY
}

test() {
  DAY="$1"
  echo "Testing Day${DAY}"
  sbt "testOnly *Day${DAY}*"
}

submit() {
  DAY="$1"
  PART="$2"
  ANSWER="$3"
  aoc submit -d $DAY $PART $ANSWER
}

lint() {
  sbt scalafmt
}

# Main
COMMAND="${1:-help}"
shift || true


case $COMMAND in
  init|part2|test|submit|lint)
    "$COMMAND" "$@";
    ;;
  help|-h|--help)
    help
    exit
    ;;
esac