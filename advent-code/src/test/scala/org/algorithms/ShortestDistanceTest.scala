package org.algorithms

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class ShortestDistanceTest extends FunSuite with Matchers with BeforeAndAfter {

    var sdc = new ShortestDistanceCalc

    before {
        sdc = new ShortestDistanceCalc
    }

    test("calculate distance a -> b -> c") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")

        sdc.distance() shouldBe 30
    }

    ignore("calculate shortest path a -> d") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")
        sdc.add("a to d = 5")
        sdc.add("d to c = 10")

        sdc.distance() shouldBe 25
    }

    ignore("distance between cities") {
        sdc.add("London to Dublin = 464")
        sdc.add("London to Belfast = 518")
        sdc.add("Dublin to Belfast = 141")

        sdc.distance() shouldBe 605
    }
}
