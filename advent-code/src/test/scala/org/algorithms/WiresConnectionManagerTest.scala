package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class WiresConnectionManagerTest extends FunSuite with Matchers {

    val wcm = new WiresConnectionManager

    test("provide signal on wires") {
        wcm.input("23 -> x")

        wcm.signalOn("x") shouldBe 23

        wcm.input("43 -> y")

        wcm.signalOn("y") shouldBe 43
    }

    test("provide AND computation of signal") {
        wcm.input("23 -> x")
        wcm.input("46 -> y")

        wcm.connect("x AND y -> z")

        wcm.signalOn("z") shouldBe 23 & 46
    }

    test("provide OR computation of signal") {
        wcm.input("23 -> x")
        wcm.input("46 -> y")

        wcm.connect("x OR y -> z")

        wcm.signalOn("z") shouldBe 23 | 46
    }

    test("provide LSHIFT computation of signal") {
        wcm.input("23 -> x")

        wcm.connect("x LSHIFT 2 -> z")

        wcm.signalOn("z") shouldBe 23 << 2
    }

    test("provide RSHIFT computation of signal") {
        wcm.input("23 -> x")

        wcm.connect("x RSHIFT 2 -> z")

        wcm.signalOn("z") shouldBe 23 >> 2
    }

    test("provide NOT computation of signal") {
        wcm.input("23 -> x")

        wcm.connect("NOT x -> z")

        wcm.signalOn("z") shouldBe ~23
    }

    test("complex computation") {
        wcm.input("123 -> x")
        wcm.input("456 -> y")
        wcm.connect("x AND y -> d")
        wcm.connect("x OR y -> e")
        wcm.connect("x LSHIFT 2 -> f")
        wcm.connect("y RSHIFT 2 -> g")
        wcm.connect("NOT x -> h")
        wcm.connect("NOT y -> i")

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
