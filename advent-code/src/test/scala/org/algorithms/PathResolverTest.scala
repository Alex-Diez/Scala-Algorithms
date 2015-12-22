package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class PathResolverTest extends FunSuite with Matchers {

    val pr = PathResolver()

    test("default number of house is 1") {
        pr.visitedHouse("") shouldBe 1
    }

    test("Visited house should be 2") {
        pr.visitedHouse(">") shouldBe 2
        pr.visitedHouse("<") shouldBe 2
        pr.visitedHouse("^") shouldBe 2
        pr.visitedHouse("v") shouldBe 2
    }

    test("revisited house should not be taken into account 2") {
        pr.visitedHouse("><") shouldBe 2
        pr.visitedHouse("<>") shouldBe 2
        pr.visitedHouse("^v") shouldBe 2
        pr.visitedHouse("v^") shouldBe 2
    }

    test("number of visited house should be 3") {
        pr.visitedHouse(">>") shouldBe 3
        pr.visitedHouse(">^") shouldBe 3
        pr.visitedHouse(">v") shouldBe 3
        pr.visitedHouse("<<") shouldBe 3
        pr.visitedHouse("<v") shouldBe 3
        pr.visitedHouse("<^") shouldBe 3
        pr.visitedHouse("^>") shouldBe 3
        pr.visitedHouse("^<") shouldBe 3
        pr.visitedHouse("^^") shouldBe 3
        pr.visitedHouse("v>") shouldBe 3
        pr.visitedHouse("v<") shouldBe 3
        pr.visitedHouse("vv") shouldBe 3
    }

    test("number of visited house should be 4") {
        pr.visitedHouse("^>v<^") shouldBe 4
        pr.visitedHouse(">v<^>") shouldBe 4
        pr.visitedHouse("v<^>v") shouldBe 4
        pr.visitedHouse("<^>v<") shouldBe 4
    }

    test("tests from site :)") {
        pr.visitedHouse(">") shouldBe 2
        pr.visitedHouse("^>v<") shouldBe 4
        pr.visitedHouse("^v^v^v^v^v") shouldBe 2

        pr.visitedHouseWithRobot("^>") shouldBe 3
        pr.visitedHouseWithRobot("^>v<") shouldBe 3
        pr.visitedHouseWithRobot("^v^v^v^v^v") shouldBe 11
    }
}
