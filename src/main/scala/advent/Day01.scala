package advent

import scala.io.Source

object Day01 extends App {

  private val digits: List[(String, String)] = List(
    "one" -> "1", "two" -> "2", "three" -> "3", "four" -> "4", "five" -> "5", "six" -> "6", "seven" -> "7", "eight" -> "8", "nine" -> "9"
  )

  private def readLines: List[String] = {
    val file = Source.fromResource("input.txt")
    val lines = file.getLines().toList
    file.close()
    lines
  }

  private val result = readLines.map(line => {
    val map = digits.flatMap {
      case (word, digit) => word.r.findAllMatchIn(line).map(_.start -> digit)
    }.toMap

    val list = for ((item, index) <- line.zipWithIndex if item.isDigit || map.contains(index)) yield {
      if (item.isDigit) item.toString else map(index)
    }

    s"${list(0)}${list(list.length - 1)}".toInt
  }).sum

  println(result)
}