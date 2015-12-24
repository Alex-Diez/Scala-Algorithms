package org.algorithms

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class LightsManagerTest extends FunSuite with Matchers with BeforeAndAfter {

    var lm5x5 = LightsManager(5, 5)

    before {
        lm5x5 = LightsManager(5, 5)
    }

    test("it should be light through 0, 0 to 0, 4") {
        lm5x5.turnOn(0, 0, 0, 4)

        lm5x5.lights(0) should contain only true
    }

    test("it should be light through 1, 1 to 2, 2") {
        lm5x5.turnOn(1, 1, 2, 2)

        lm5x5.lights(1)(1) shouldBe true
        lm5x5.lights(1)(2) shouldBe true
        lm5x5.lights(2)(1) shouldBe true
        lm5x5.lights(2)(2) shouldBe true
    }

    test("it should not be light through 0,0 to 0, 4") {
        lm5x5.turnOn(0, 0, 0, 4)

        lm5x5.turnOff(0, 0, 0, 4)
        lm5x5.lights(0) should contain only false
    }

    test("it should not be light through 1, 1 to 2, 2") {
        lm5x5.turnOn(1, 1, 2, 2)

        lm5x5.turnOff(1, 1, 2, 2)

        lm5x5.lights(1)(1) shouldBe false
        lm5x5.lights(1)(2) shouldBe false
        lm5x5.lights(2)(1) shouldBe false
        lm5x5.lights(2)(2) shouldBe false
    }

    test("it should toggle through 1,1 to 2,2") {
        lm5x5.turnOn(0, 0, 4, 4)

        lm5x5.toggle(1, 1, 2, 2)

        lm5x5.lights(1)(1) shouldBe false
        lm5x5.lights(1)(2) shouldBe false
        lm5x5.lights(2)(1) shouldBe false
        lm5x5.lights(2)(2) shouldBe false
    }
}
