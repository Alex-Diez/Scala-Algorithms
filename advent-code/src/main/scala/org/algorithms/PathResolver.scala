package org.algorithms

import scala.annotation.tailrec
import scala.io.Source._

class PathResolver {
    @tailrec
    private def visit(path:String, index: Int, current: Int, increment: Int, house: Set[Int]): Set[Int] = {
        val width = Byte.MaxValue
        if (index >= path.length) house
        else path.charAt(index) match {
            case 'v' => visit(path, index + increment, current - width, increment, house + (current - width))
            case '^' => visit(path, index + increment, current + width, increment, house + (current + width))
            case '<' => visit(path, index + increment, current - 1, increment, house + (current - 1))
            case '>' => visit(path, index + increment, current + 1, increment, house + (current + 1))
        }
    }

    def visitedHouse(path: String): Int = {
        visit(path, 0, 0, 1, Set(0)).size
    }

    def visitedHouseWithRobot(path: String): Int = {
        (visit(path, 0, 0, 2, Set(0)) union visit(path, 1, 0, 2, Set(0))).size
    }
}

object PathResolver {
    def apply() = new PathResolver

    def main(args: Array[String]): Unit = {
        val pr = PathResolver()
        val line = fromFile("advent-code/src/main/resources/path").getLines.next
        println(pr.visitedHouse(line))
        println(pr.visitedHouseWithRobot(line))
    }
}
