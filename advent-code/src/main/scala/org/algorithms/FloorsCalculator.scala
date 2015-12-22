package org.algorithms

import scala.annotation.tailrec
import scala.io.Source._

class FloorsCalculator() {
    def floorNumber(src: String): Int = {
        @tailrec
        def floorNumber(src: String, index: Int, floor: Int): Int = {
            if(index == src.length) floor
            else if(src.charAt(index) == '(') floorNumber(src, index + 1, floor + 1)
            else floorNumber(src, index + 1, floor - 1)
        }
        floorNumber(src, 0, 0)
    }

    def basementIndex(src: String): Int = {
        @tailrec
        def basementIndex(src: String, index: Int, floor: Int): Int = {
            if(floor == -1) index
            else if(src.charAt(index) == '(') basementIndex(src, index + 1, floor + 1)
            else basementIndex(src, index + 1, floor - 1)
        }
        basementIndex(src, 0, 0)
    }
}

object FloorsCalculator {
    def apply(): org.algorithms.FloorsCalculator = new FloorsCalculator()

    def main(args: Array[String]): Unit = {
        val iterator = fromFile("advent-code/src/main/resources/floors").getLines
        val first = iterator.next
        val second = iterator.next

        val calc = FloorsCalculator()
        println("first part:")
        println(calc.floorNumber(first))
        println(calc.basementIndex(first))

        println("second part:")
        println(calc.floorNumber(second))
        println(calc.basementIndex(second))
    }
}
