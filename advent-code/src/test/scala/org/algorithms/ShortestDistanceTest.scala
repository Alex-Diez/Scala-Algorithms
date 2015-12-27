package org.algorithms

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class ShortestDistanceTest extends FunSuite with Matchers with BeforeAndAfter {

    var sdc = new DistanceCalc

    before {
        sdc = new DistanceCalc
    }

    test("calculate distance a -> b -> c") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")

        sdc.shortestDistance() shouldBe 30
    }

    test("calculate shortest path a -> d") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")
        sdc.add("a to d = 5")
        sdc.add("d to c = 10")

        sdc.shortestDistance() shouldBe 25
    }

    test("distance between cities") {
        sdc.add("London to Dublin = 464")
        sdc.add("London to Belfast = 518")
        sdc.add("Dublin to Belfast = 141")

        sdc.shortestDistance() shouldBe 605
    }
}
