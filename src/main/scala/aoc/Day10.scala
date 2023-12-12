package aoc.day10

import scala.collection.mutable

enum Direction:
  case Left, Right, Up, Down, Center

case class Position(row: Int, column: Int) {}

class Graph(map: Array[Array[Char]]) {
  def start(): Position =
    map.zipWithIndex
      .map((row, rowNum) =>
        row.zipWithIndex
          .map((cell, colNum) => (cell, rowNum, colNum))
      )
      .flatten
      .filter((cell, rowNum, colNum) => cell == 'S')
      .map((cell, rowNum, colNum) => Position(rowNum, colNum))
      .head

  def charAt(pos: Position): Char =
    map(pos.row)(pos.column)

  def go(pos: Position, dir: Direction): (Position, Direction) =
    dir match {
      case Direction.Left =>
        (Position(pos.row, pos.column - 1), Direction.Right)
      case Direction.Right =>
        (Position(pos.row, pos.column + 1), Direction.Left)
      case Direction.Up   => (Position(pos.row - 1, pos.column), Direction.Down)
      case Direction.Down => (Position(pos.row + 1, pos.column), Direction.Up)
      case Direction.Center => (pos, Direction.Center)
    }

  def next(pos: Position, dir: Direction): (Position, Direction) = {
    (charAt(pos), dir) match {
      case ('|', Direction.Up)    => go(pos, Direction.Down)
      case ('|', Direction.Down)  => go(pos, Direction.Up)
      case ('-', Direction.Left)  => go(pos, Direction.Right)
      case ('-', Direction.Right) => go(pos, Direction.Left)
      case ('L', Direction.Up)    => go(pos, Direction.Right)
      case ('L', Direction.Right) => go(pos, Direction.Up)
      case ('J', Direction.Up)    => go(pos, Direction.Left)
      case ('J', Direction.Left)  => go(pos, Direction.Up)
      case ('7', Direction.Down)  => go(pos, Direction.Left)
      case ('7', Direction.Left)  => go(pos, Direction.Down)
      case ('F', Direction.Down)  => go(pos, Direction.Right)
      case ('F', Direction.Right) => go(pos, Direction.Down)
      case _                      => (pos, Direction.Center)
    }
  }
}

object Graph {
  def parse(input: String): Graph =
    Graph(
      input
        .split("\n")
        .map(_.toArray)
    )
}

def part1(input: String): Int = {
  val graph = Graph.parse(input)
  LazyList
    .iterate((graph.go(graph.start(), Direction.Right), 0))((posDir, count) =>
      (graph.next(posDir._1, posDir._2), count + 1)
    )
    .find((posDir, count) => posDir._2 == Direction.Center)
    .get
    ._2 / 2
}

def part2(input: String): Int = {
  val graph = Graph.parse(input)
  val cycle: List[(Position, Direction)] = LazyList
    .iterate(graph.go(graph.start(), Direction.Right))(
      (pos: Position, dir: Direction) => graph.next(pos, dir)
    )
    .takeWhile((pos, dir) => dir != Direction.Center)
    .toList
  val dirs: Map[Direction, Int] = cycle
    .map(_._2)
    .sliding(2)
    .collect {
      case List(Direction.Up, Direction.Left) |
          List(Direction.Left, Direction.Down) |
          List(Direction.Down, Direction.Right) |
          List(Direction.Right, Direction.Up) =>
        Direction.Left
      case List(Direction.Up, Direction.Right) |
          List(Direction.Right, Direction.Down) |
          List(Direction.Down, Direction.Left) |
          List(Direction.Left, Direction.Up) =>
        Direction.Right
    }
    .toList
    .groupBy(x => x)
    .view
    .mapValues(_.length)
    .toMap
  val todo: mutable.Stack[Position] = mutable.Stack(
    cycle
      .sliding(2)
      .scanLeft(
        (
          List.empty[Position],
          if (
            dirs.getOrElse(Direction.Left, 0) > dirs
              .getOrElse(Direction.Right, 0)
          )
            Direction.Up
          else Direction.Down
        )
      ) {
        case ((_, d), List((p1, d1), (_, d2))) => {
          val nextDir = (d, d1, d2) match {
            case (d, d1, d2) if d1 == d2 => d
            case (d, Direction.Right, Direction.Up) =>
              if (d == Direction.Up) Direction.Left else Direction.Right
            case (d, Direction.Right, Direction.Down) =>
              if (d == Direction.Down) Direction.Left else Direction.Right
            case (d, Direction.Left, Direction.Up) =>
              if (d == Direction.Down) Direction.Left else Direction.Right
            case (d, Direction.Left, Direction.Down) =>
              if (d == Direction.Up) Direction.Left else Direction.Right
            case (d, Direction.Up, Direction.Left) =>
              if (d == Direction.Left) Direction.Down else Direction.Up
            case (d, Direction.Up, Direction.Right) =>
              if (d == Direction.Right) Direction.Down else Direction.Up
            case (d, Direction.Down, Direction.Left) =>
              if (d == Direction.Right) Direction.Down else Direction.Up
            case (d, Direction.Down, Direction.Right) =>
              if (d == Direction.Left) Direction.Down else Direction.Up
          }
          (
            Set(d, nextDir)
              .map(graph.go(p1, _)._1)
              .toList,
            nextDir
          )
        }
      }
      .map(_._1)
      .flatten
      .toList: _*
  )
  val impossible: mutable.Set[Position] = mutable.Set(cycle.map(_._1): _*)
  val inside: mutable.Set[Position] = mutable.Set()
  while (!todo.isEmpty) {
    val pos = todo.pop()
    if (!impossible.contains(pos)) {
      inside.addOne(pos)
      impossible.addOne(pos)
      todo.pushAll(
        List(Direction.Up, Direction.Down, Direction.Left, Direction.Right)
          .map(graph.go(pos, _)._1)
      )
    }
  }
  inside.size
}
