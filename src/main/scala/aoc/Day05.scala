package aoc.day05

import scala.collection.mutable
import scala.collection.mutable.HashMap

def parseMaps(
    input: Seq[String]
): Map[String, (String, Seq[(Long, Long, Long)])] =
  input
    .map(s => {
      s.split("\n") match {
        case Array(s"$s-to-$d map:", xs*) =>
          (
            s,
            (
              d,
              xs.collect { case s"$x $y $z" =>
                (x.toLong, y.toLong, z.toLong)
              }
            )
          )
      }
    })
    .toMap

def parseSeed(input: String): Array[Long] =
  input match {
    case s"seeds: $x" => x.split(" ").map(_.toLong)
  }

def parse(
    input: String
): (Array[Long], Map[String, (String, Seq[(Long, Long, Long)])]) =
  input.split("\n\n") match {
    case Array(x, xs*) => (parseSeed(x), parseMaps(xs))
  }

def part1(input: String): Long = {
  val (seeds, maps) = parse(input)
  seeds
    .map(x => {
      var currentKey = "seed"
      var currentVal = x
      while (maps.contains(currentKey)) {
        val rules = maps(currentKey)._2
        currentKey = maps(currentKey)._1
        currentVal = rules
          .find((d, s, l) => s <= currentVal & currentVal < s + l)
          .collect((d, s, l) => d + (currentVal - s))
          .getOrElse(currentVal)
      }
      currentVal
    })
    .min
}

def part2(input: String): Long = {
  val (seeds, maps) = parse(input)
  val rangeSeed = mutable.Queue(
    seeds
      .sliding(2, 2)
      .collect { case Array(s, l) => ("seed", s, l) }
      .toArray: _*
  )
  var res = List[Long]()
  while (!rangeSeed.isEmpty) {
    val (currentKey, source, length) = rangeSeed.dequeue()
    if (maps.contains(currentKey)) {
      val rules = maps(currentKey)._2
      val newKey = maps(currentKey)._1
      if (
        rules
          .find((d, s, l) => {
            val min = List(source, s).max
            val max = List(source + length - 1, s + l - 1).min
            if (min <= max) {
              if (source < min) {
                rangeSeed.enqueue((currentKey, source, s - source))
              }
              if (source + length - 1 > max) {
                rangeSeed
                  .enqueue((currentKey, max + 1, source + length - (max + 1)))
              }
              rangeSeed
                .enqueue((newKey, d - s + min, d - s + max - (d - s + min) + 1))
            }
            (min <= max) // found an intersection
          })
          .isEmpty
      ) {
        rangeSeed.enqueue((newKey, source, length))
      }
    } else {
      res = source :: res
    }
  }
  res.min
}
