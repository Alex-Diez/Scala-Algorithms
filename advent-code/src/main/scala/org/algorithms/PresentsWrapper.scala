package org.algorithms

import scala.io.Source.fromFile
import scala.language.postfixOps

class PresentsWrapper() {
    def squareFor(size: String): Int = {
        def createSides(edges: List[Int], sides: List[Int]): List[Int] = {
            if(edges.isEmpty) sides
            else edges.tail.map[Int, List[Int]](v => v * edges.head) ::: createSides(edges.tail, sides)
        }
        def squareFor(box: List[Int]): Int = {
            box.fold[Int](box.min)((acc: Int, v: Int) => acc + 2 * v)
        }
        squareFor(createSides(evalEdges(size), Nil))
    }

    def ribbonLength(size: String): Int = {
        val edges = evalEdges(size)
        val iMax = edges.indexOf(edges.max)
        val minEdges = edges.patch(iMax, List(), 1)
        2 * minEdges.sum + edges.product
    }

    private def evalEdges(size: String): List[Int] = {
        size split "x" map[Int, Array[Int]] (s => s toInt) toList
    }
}

object PresentsWrapper {
    def apply(): PresentsWrapper = new PresentsWrapper

    def main(args: Array[String]): Unit = {
        val pw = PresentsWrapper()
        val linesOne = fromFile("advent-code/src/main/resources/boxes").getLines
        println(linesOne.map((v: String) => pw.squareFor(v)).sum)
        val linesTwo = fromFile("advent-code/src/main/resources/boxes").getLines
        println(linesTwo.map((v: String) => pw.ribbonLength(v)).sum)
    }
}
