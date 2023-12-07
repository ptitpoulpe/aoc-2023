package aoc.day07

import scala.collection.mutable
import scala.math.pow

def parse(input: String): Array[(List[Char], Int)] =
  input
    .split("\n")
    .collect { case s"$cards $bid" =>
      (cards.toList.map(c => c), bid.toInt)
    }

val possibleCards1 = Array(
  '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'
)

def subScore1(chars: List[Char]): Int =
  chars.reverse.zipWithIndex
    .map((char, index) =>
      pow(16, index).toInt * (possibleCards1.indexOf(char) + 1)
    )
    .sum

def score1(hand: List[Char]): Double =
  subScore1(hand) + pow(16, 5) * (
    hand
      .groupBy(x => x)
      .toList
      .map(x => (x._2.size, x._1))
      .sorted match {
      case List((5, _))                         => 9
      case List((1, _), (4, _))                 => 8
      case List((2, _), (3, _))                 => 7
      case List((1, _), (1, _), (3, _))         => 6
      case List((1, _), (2, _), (2, _))         => 5
      case List((1, _), (1, _), (1, _), (2, _)) => 4
      case _                                    => 3
    }
  )

def part1(input: String): Int =
  parse(input)
    .map((hand, bid) => (score1(hand), bid))
    .sorted
    .zipWithIndex
    .map((scoreBid, index) => scoreBid._2 * (index + 1))
    .sum

val possibleCards2 = Array(
  'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'
)

def subScore2(chars: List[Char]): Int =
  chars.reverse.zipWithIndex
    .map((char, index) =>
      pow(16, index).toInt * (possibleCards2.indexOf(char) + 1)
    )
    .sum

def score2(hand: List[Char]): Double = {
  val cards = mutable.Map(
    hand
      .groupBy(x => x)
      .view
      .mapValues(v => v.size)
      .toList*
  )

  val jokers = cards.getOrElse('J', 0)
  cards.remove('J')
  var cardsList = cards.toList
    .map((x, y) => (y, x))
    .sorted
    .toArray
  if (cardsList.length == 0) {
    cardsList = Array((jokers, possibleCards2.last))
  } else {
    cardsList(cardsList.length - 1) = (
      cardsList.last._1 + jokers,
      cardsList.last._2
    )
  }

  subScore2(hand) + pow(16, 5) * (
    cardsList match {
      case Array((5, x))                         => 9
      case Array((1, x), (4, y))                 => 8
      case Array((2, x), (3, y))                 => 7
      case Array((1, x), (1, y), (3, z))         => 6
      case Array((1, x), (2, y), (2, z))         => 5
      case Array((1, x), (1, y), (1, z), (2, t)) => 4
      case x                                     => 3
    }
  )
}

def part2(input: String): Int =
  parse(input)
    .map((hand, bid) => (score2(hand), bid))
    .sorted
    .zipWithIndex
    .map((scoreBid, index) => scoreBid._2 * (index + 1))
    .sum
