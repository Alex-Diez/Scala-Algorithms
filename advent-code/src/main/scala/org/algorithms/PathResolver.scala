package org.algorithms

import scala.io.Source._

class PathResolver {
    def visitedHouse(path: String): Int = {
        var current = BigInt(0)
        val width = Int.MaxValue
        var houses = Set(current)
        for(c <- path) {
            c match {
                case 'v' =>
                    current = current - width
                    houses = houses + current
                case '^' =>
                    current = current + width
                    houses = houses + current
                case '>' =>
                    current = current + 1
                    houses = houses + current
                case '<' =>
                    current = current - 1
                    houses = houses + current
            }
        }
        houses.size
    }

    def visitedHouseWithRobot(path: String): Int = {
        var currentSanta = BigInt(0)
        var currentRobot = BigInt(0)
        val width = Int.MaxValue
        var santaHouses = Set(currentSanta)
        var robotHouses = Set(currentRobot)
        def visitHouse(path: String, index: Int): Unit = {
            if (index < path.length) {
                path.charAt(index) match {
                    case 'v' =>
                        if(index % 2 == 0) {
                            currentSanta = currentSanta - width
                            santaHouses = santaHouses + currentSanta
                        }
                        else {
                            currentRobot = currentRobot - width
                            robotHouses = robotHouses + currentRobot
                        }
                    case '^' =>
                        if(index % 2 == 0) {
                            currentSanta = currentSanta + width
                            santaHouses = santaHouses + currentSanta
                        }
                        else {
                            currentRobot = currentRobot + width
                            robotHouses = robotHouses + currentRobot
                        }
                    case '>' =>
                        if(index % 2 == 0) {
                            currentSanta = currentSanta - 1
                            santaHouses = santaHouses + currentSanta
                        }
                        else {
                            currentRobot = currentRobot - 1
                            robotHouses = robotHouses + currentRobot
                        }
                    case '<' =>
                        if(index % 2 == 0) {
                            currentSanta = currentSanta + 1
                            santaHouses = santaHouses + currentSanta
                        }
                        else {
                            currentRobot = currentRobot + 1
                            robotHouses = robotHouses + currentRobot
                        }
                }
                visitHouse(path, index + 1)
            }
        }
        visitHouse(path, 0)
        (robotHouses union santaHouses).size
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
