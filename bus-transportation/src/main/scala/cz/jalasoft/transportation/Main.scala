package cz.jalasoft.transportation

object Main {

  def main(args : Array[String]) = {
    val number : Int = 34
    println("Tak jsem tady: " + number.plusOne())
  }

  implicit class PlusOne(number : Int) {

    def plusOne() = {
      number + 1
    }
  }
}


