package aoc.day15

case class KeyValue(key: String, var value: Int) {}

def parse(input: String): Array[Array[Int]] = Array(Array(0))

def hash(value: String): Int =
  value.foldLeft(0)((acc, char) => ((acc + char.toInt) * 17) % 256)

def part1(input: String): Int =
  input.trim
    .split(",")
    .map(hash(_))
    .sum

def part2(input: String): Int = {
  val hashmap = Range(0, 256).map(_ => List.empty[KeyValue]).toArray
  input.trim
    .split(",")
    .collect {
      case s"$label=$num" => {
        val label_hash = hash(label)
        val keyValue = hashmap(label_hash).find(_.key == label)
        if (keyValue.isDefined) {
          keyValue.get.value = num.toInt
        } else {
          hashmap(label_hash) =
            KeyValue(label, num.toInt) :: hashmap(label_hash)
        }
      }
      case s"$label-" => {
        val label_hash = hash(label)
        hashmap(label_hash) = hashmap(label_hash).filter(_.key != label)
      }
    }
  hashmap.zipWithIndex
    .map((kvs, bi) =>
      kvs.reverse.zipWithIndex
        .map((kv, si) => kv.value * (bi + 1) * (si + 1))
        .sum
    )
    .sum
}
