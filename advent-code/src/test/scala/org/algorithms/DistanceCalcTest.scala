package org.algorithms

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.io.Source._

class DistanceCalcTest extends FunSuite with Matchers with BeforeAndAfter {

    var sdc = new DistanceCalc

    before {
        sdc = new DistanceCalc
    }

    test("calculate shortest path a -> d") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")
        sdc.add("a to d = 5")
        sdc.add("d to c = 10")

        sdc.shortestDistance() shouldBe 25
    }

    test("shortest distance between cities") {
        sdc.add("London to Dublin = 464")
        sdc.add("London to Belfast = 518")
        sdc.add("Dublin to Belfast = 141")

        sdc.shortestDistance() shouldBe 605
    }

    test("longest distance between cities") {
        sdc.add("London to Dublin = 464")
        sdc.add("London to Belfast = 518")
        sdc.add("Dublin to Belfast = 141")

        sdc.longestDistance() shouldBe 982
    }

    test("for given data") {
        fromFile("advent-code/src/main/resources/distances").getLines().foreach(l => sdc.add(l))

        sdc.longestDistance() shouldBe 804
    }
}
