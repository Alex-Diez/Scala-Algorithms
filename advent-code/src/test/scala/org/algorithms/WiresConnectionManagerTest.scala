package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class WiresConnectionManagerTest extends FunSuite with Matchers {

    val wcm = new WiresConnectionManager

    test("provide signal on wires") {
        wcm.input("23 -> x")

        wcm.signalOn("x") shouldBe (23 & 65535)

        wcm.input("43 -> y")

        wcm.signalOn("y") shouldBe (43 & 65535)
    }

    test("provide AND computation of signal") {
        wcm.input("23 -> x")
        wcm.input("46 -> y")

        wcm.input("x AND y -> z")

        wcm.signalOn("z") shouldBe (23 & 46 & 65535)
    }

    test("provide OR computation of signal") {
        wcm.input("23 -> x")
        wcm.input("46 -> y")

        wcm.input("x OR y -> z")

        wcm.signalOn("z") shouldBe (23 | 46 & 65535)
    }

    test("provide LSHIFT computation of signal") {
        wcm.input("23 -> x")

        wcm.input("x LSHIFT 2 -> z")

        wcm.signalOn("z") shouldBe (23 << 2 & 65535)
    }

    test("provide RSHIFT computation of signal") {
        wcm.input("23 -> x")

        wcm.input("x RSHIFT 2 -> z")

        wcm.signalOn("z") shouldBe (23 >> 2 & 65535)
    }

    test("provide NOT computation of signal") {
        wcm.input("23 -> x")

        wcm.input("NOT x -> z")

        wcm.signalOn("z") shouldBe (~23 & 65535)
    }

    test("complex computation") {
        wcm.input("123 -> x")
        wcm.input("456 -> y")
        wcm.input("x AND y -> d")
        wcm.input("x OR y -> e")
        wcm.input("x LSHIFT 2 -> f")
        wcm.input("y RSHIFT 2 -> g")
        wcm.input("NOT x -> h")
        wcm.input("NOT y -> i")

        wcm.signalOn("d") shouldBe 72
        wcm.signalOn("e") shouldBe 507
        wcm.signalOn("f") shouldBe 492
        wcm.signalOn("g") shouldBe 114
        wcm.signalOn("h") shouldBe 65412
        wcm.signalOn("i") shouldBe 65079
        wcm.signalOn("x") shouldBe 123
        wcm.signalOn("y") shouldBe 456
    }
}
