package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class LightsManagerTest extends FunSuite with Matchers {

    test("it should create a light manager") {
        new LightsManager
    }

    test("it should be light through 0, 0 to 999, 999") {
        val lm = new LightsManager
        lm.turnOn(0, 0, 999, 0)

        lm.lights(0) should contain only 1
    }
}
