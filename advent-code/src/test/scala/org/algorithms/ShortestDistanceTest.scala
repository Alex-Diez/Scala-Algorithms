package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class ShortestDistanceTest extends FunSuite with Matchers {

    val sdc = new ShortestDistanceCalc

    test("calculate distance a -> b -> c") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")

        sdc.distance() shouldBe 30
    }

    test("calculate shortest path a -> d") {
        sdc.add("a to b = 10")
        sdc.add("b to c = 20")
        sdc.add("a to d = 5")
        sdc.add("d to c = 10")

        sdc.distance() shouldBe 25
    }
}
