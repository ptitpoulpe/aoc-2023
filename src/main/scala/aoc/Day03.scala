package aoc.day03

import scala.collection.mutable.HashMap

case class Grid(val values: Array[Array[Char]]) {
  def height(): Int = values.length
  def width(): Int = values(0).length
  def get(x: Int, y: Int): Char =
    if (0 <= x & x < width() & 0 <= y & y < height()) {
      values(y)(x)
    } else {
      '.'
    }
  def isSymbol(x: Int, y: Int): Boolean =
    !get(x, y).isDigit & get(x, y) != '.'
  def isGear(x: Int, y: Int): Boolean =
    get(x, y) == '*'
}

object Grid {
  def parse(input: String): Grid =
    Grid(
      input.split("\n").map(_.toArray)
    )
}

def part1(input: String): Int = {
  val grid = Grid.parse(input)
  var res = 0
  Range
    .Int(0, grid.height(), 1)
    .map(y => {
      var d = 0
      var sx = -1
      Range
        .Int(0, grid.width() + 1, 1)
        .map(x => {
          val c = grid.get(x, y)
          if (c.isDigit) {
            d = d * 10 + c.asDigit
            if (sx == -1) {
              sx = x
            }
          } else if (sx != -1) {
            if (
              Range
                .Int(sx - 1, x + 1, 1)
                .exists(ox =>
                  List(y - 1, y + 1).exists(oy => grid.isSymbol(ox, oy))
                ) | grid.isSymbol(sx - 1, y) | grid.isSymbol(x, y)
            ) {
              res += d
            }
            d = 0
            sx = -1
          }
        })
    })
  res
}

def part2(input: String): Int = {
  val grid = Grid.parse(input)
  val gears = HashMap[(Int, Int), List[Int]]()
  Range
    .Int(0, grid.height(), 1)
    .map(y => {
      var d = 0
      var sx = -1
      Range
        .Int(0, grid.width() + 1, 1)
        .map(x => {
          val c = grid.get(x, y)
          if (c.isDigit) {
            d = d * 10 + c.asDigit
            if (sx == -1) {
              sx = x
            }
          } else if (sx != -1) {
            Range
              .Int(sx - 1, x + 1, 1)
              .foreach(ox =>
                List(y - 1, y + 1)
                  .foreach(oy => {
                    if (grid.isGear(ox, oy)) {
                      gears
                        .put((ox, oy), d :: gears.getOrElse((ox, oy), List()))
                    }
                  })
              )
            List((sx - 1, y), (x, y))
              .foreach((ox, oy) => {
                if (grid.isGear(ox, oy)) {
                  gears.put((ox, oy), d :: gears.getOrElse((ox, oy), List()))
                }
              })
            d = 0
            sx = -1
          }
        })
    })
  gears.values.collect { case List(x, y) =>
    x * y
  }.sum
}
