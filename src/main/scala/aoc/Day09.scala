package aoc.day09

def parse(input: String): Array[Array[Int]] =
  input
    .split("\n")
    .map(_.split(" ").map(_.toInt))

def part1(input: String): Int =
  parse(input)
    .map(sequence => {
      var sequences = List(sequence)
      while (!sequences(0).forall(_ == 0)) {
        sequences = (
          sequences(0)
            .sliding(2)
            .map(s => s(1) - s(0))
            .toArray
        ) :: sequences
      }
      sequences
        .map(_.last)
        .sum
    })
    .sum

def part2(input: String): Int =
  parse(input)
    .map(sequence => {
      var sequences = List(sequence)
      while (!sequences(0).forall(_ == 0)) {
        sequences = (
          sequences(0)
            .sliding(2)
            .map(s => s(1) - s(0))
            .toArray
        ) :: sequences
      }
      sequences
        .foldLeft(0)((a, seq) => seq.head - a)
    })
    .sum
