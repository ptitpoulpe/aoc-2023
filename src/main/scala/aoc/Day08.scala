package aoc.day08

def parse(input: String): (List[Char], Map[String, (String, String)]) =
  input
    .split("\n\n") match {
    case Array(directions, graph) =>
      (
        directions.toList,
        graph
          .split("\n")
          .collect { case s"$parent = ($left, $right)" =>
            (parent, (left, right))
          }
          .toMap
      )
  }

def process(
    directions: List[Char],
    graph: Map[String, (String, String)],
    source: String,
    destinations: Set[String]
): LazyList[(Int, String)] =
  LazyList
    .continually(directions)
    .flatten
    .scanLeft((0, source)) { case ((i, position), direction) =>
      (i + 1, if (direction == 'L') graph(position)._1 else graph(position)._2)
    }
    .filter((i, position) => destinations.contains(position))

def part1(input: String): Int = {
  val (directions, graph) = parse(input)
  process(directions, graph, "AAA", Set("ZZZ")).head._1
}

def lcm(list: Seq[BigInt]): BigInt =
  list.foldLeft(1: BigInt) { (a, b) =>
    b * a /
      LazyList
        .iterate((a, b)) { case (x, y) => (y, x % y) }
        .dropWhile(_._2 != 0)
        .head
        ._1
        .abs
  }

def part2(input: String): BigInt = {
  val (directions, graph) = parse(input)
  val sources = graph.keys.filter(position => position(2) == 'A').toList
  val destinations = graph.keys.filter(position => position(2) == 'Z').toSet
  println(sources)
  println(destinations)
  lcm(
    sources
      .map(source =>
        process(
          directions,
          graph,
          source,
          destinations
        ).head._1 // all loop finish at start of direcitons
      )
  )
}
