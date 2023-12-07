package aoc.day06

def parse(input: String): Array[(Int, Int)] =
  input.split("\n") match {
    case Array(s"Time: $time", s"Distance: $distance") => {
      time.trim.split(" +").map(_.toInt) zip distance.trim
        .split(" +")
        .map(_.toInt)
    }
  }

def parse2(input: String): (Long, Long) =
  input.split("\n") match {
    case Array(s"Time: $time", s"Distance: $distance") => {
      (time.filterNot(_ == ' ').toLong, distance.filterNot(_ == ' ').toLong)
    }
  }
def part1(input: String): Int =
  parse(input)
    .map((t, d) =>
      (1 to t)
        .filter(i => i * (t - i) > d)
        .size
    )
    .product

def part2(input: String): Int = {
  val (t, d) = parse2(input)
  (1L to t)
    .filter(i => i * (t - i) > d)
    .size
}
