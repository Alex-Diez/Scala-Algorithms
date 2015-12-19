package org.algorithms

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}

class PresentsWrapperTest extends FunSuite with Matchers {

    val pw = PresentsWrapper()

    test("calculate paper for 1x1x1 box") {
        pw.squareFor("1x1x1") shouldBe 7
    }

    test("calculate paper for 2x1x1 box") {
        pw.squareFor("2x1x1") shouldBe 11
    }

    test("site tests :)") {
        pw.squareFor("2x3x4") shouldBe 58
        pw.squareFor("1x1x10") shouldBe 43
    }
}
