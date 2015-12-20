package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class PathResolverTest extends FunSuite with Matchers {

    test("it should create a path resolver") {
        new PathResolver
    }

    test("default number of house is 1") {
        val pr = new PathResolver

        pr.visitedHouse("") shouldBe 1
    }

    test("Visited house should be 2") {
        val pr = new PathResolver

        pr.visitedHouse(">") shouldBe 2
        pr.visitedHouse("<") shouldBe 2
        pr.visitedHouse("^") shouldBe 2
        pr.visitedHouse("v") shouldBe 2
    }

    test("revisited house should not be taken into account 2") {
        val pr = new PathResolver

        pr.visitedHouse("><") shouldBe 2
        pr.visitedHouse("<>") shouldBe 2
        pr.visitedHouse("^v") shouldBe 2
        pr.visitedHouse("v^") shouldBe 2
    }

    test("Visited house should be 3") {
        val pr = new PathResolver

        pr.visitedHouse(">>") shouldBe 3
        pr.visitedHouse(">^") shouldBe 3
        pr.visitedHouse(">v") shouldBe 3
        pr.visitedHouse("<<") shouldBe 3
        pr.visitedHouse("<v") shouldBe 3
        pr.visitedHouse("<^") shouldBe 3
        pr.visitedHouse("^>") shouldBe 3
        pr.visitedHouse("^<") shouldBe 3
        pr.visitedHouse("^v") shouldBe 3
        pr.visitedHouse("v>") shouldBe 3
        pr.visitedHouse("v<") shouldBe 3
        pr.visitedHouse("v^") shouldBe 3
    }
}
