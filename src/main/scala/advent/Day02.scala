package advent

import scala.io.Source

object Day02 extends App {

  private def readLines: List[String] = {
    val file = Source.fromResource("input_day02.txt")
    val lines = file.getLines().toList
    file.close()
    lines
  }

  case class Reveal(blue: Option[Int], green: Option[Int], red: Option[Int])
  case class Game(id: Int, reveals: List[Reveal])

  val games = readLines.map(line => {
    val Array(game, reveals) = line.split(": ")
    val revealsParsed = reveals.split("; ").map(reveal => {
      val colorMap = reveal.split(", ").map(revealItem => {
        val Array(number, color) = revealItem.split(" ")
        color -> number.toInt
      }).toMap

      Reveal(colorMap.get("blue"), colorMap.get("green"), colorMap.get("red"))
    }).toList

    Game(game.replace("Game ", "").toInt, revealsParsed)
  })

  val result = games.filterNot(game => {
    game.reveals.exists(_.blue.exists(_ > 14)) || game.reveals.exists(_.green.exists(_ > 13)) || game.reveals.exists(_.red.exists(_ > 12))
  }).map(_.id).sum

  println(s"First puzzle: $result")

  val result02 = games.map(game => {
    val minBlue = game.reveals.flatMap(_.blue).max
    val minGreen = game.reveals.flatMap(_.green).max
    val minRed = game.reveals.flatMap(_.red).max
    minBlue * minGreen * minRed
  }).sum

  println(s"Second puzzle: $result02")
}