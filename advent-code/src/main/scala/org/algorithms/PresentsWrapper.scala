package org.algorithms

import scala.io.Source.fromFile
import scala.language.postfixOps

class PresentsWrapper() {
    def squareFor(size: String): Int = {
        def evalEdges(size: String): List[Int] = {
            size split "x" map[Int, Array[Int]] (s => s toInt) toList
        }
        def createSides(edges: List[Int], sides: List[Int]): List[Int] = {
            if(edges.isEmpty) sides
            else edges.tail.map[Int, List[Int]](v => v * edges.head) ::: createSides(edges.tail, sides)
        }
        def squareFor(box: List[Int]): Int = {
            box.fold[Int](box.min)((acc: Int, v: Int) => acc + 2 * v)
        }
        squareFor(createSides(evalEdges(size), Nil))
    }
}

object PresentsWrapper {
    def apply(): PresentsWrapper = new PresentsWrapper

    def main(args: Array[String]): Unit = {
        val pw = PresentsWrapper()
        val data = fromFile("advent-code/src/main/resources/boxes")
                .getLines().fold[String]("0")((acc: String, v: String) => (pw.squareFor(v) + acc.toInt).toString)
        println(data)
    }
}
