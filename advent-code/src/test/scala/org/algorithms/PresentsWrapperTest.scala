package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class PresentsWrapperTest extends FunSuite with Matchers {

    val pw = PresentsWrapper()

    test("calculate paper for 1x1x1 box") {
        pw.squareFor("1x1x1") shouldBe 2 * (1 * 1) + 2 * (1 * 1) + 2 * (1 * 1) + 1
    }

    test("calculate ribbon length for 1x1x1 box") {
        pw.ribbonLength("1x1x1") shouldBe 2 * (1 + 1) + (1 * 1 * 1)
    }

    test("calculate paper for 2x1x1 box") {
        pw.squareFor("2x1x1") shouldBe 2 * (1 * 1) + 2 * (2 * 1) + 2 * (2 * 1) + 1
    }

    test("calculate ribbon length for 2x1x1 box") {
        pw.ribbonLength("2x1x1") shouldBe 2 * (1 + 1) + (2 * 1 * 1)
    }

    test("site tests :)") {
        pw.squareFor("2x3x4") shouldBe 2 * (2 * 3) + 2 * (3 * 4) + 2 * (2 * 4) + 2 * 3
        pw.squareFor("1x1x10") shouldBe 2 * (1 * 1) + 2 * (1 * 10) + 2 * (10 * 1) + 1 * 1

        pw.ribbonLength("2x3x4") shouldBe 2 * (2 + 3) + (2 * 3 * 4)
        pw.ribbonLength("1x1x10") shouldBe 2 * (1 + 1) + (1 * 1 * 10)
    }
}
