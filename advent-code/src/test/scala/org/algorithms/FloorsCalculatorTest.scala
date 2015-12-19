package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class FloorsCalculatorTest extends FunSuite with Matchers {

    test("evaluate first floor") {
        FloorsCalculator("(").evaluate shouldBe 1
        FloorsCalculator("()(").evaluate shouldBe 1
        FloorsCalculator("()()(").evaluate shouldBe 1
    }

    test("evaluate ten floor") {
        FloorsCalculator("(((((((" +
                "(((").evaluate shouldBe 10
        FloorsCalculator("()(((((((" +
                "(((").evaluate shouldBe 10
        FloorsCalculator("()()" +
                "((((((((((").evaluate shouldBe 10

    }

    test("test from site :)") {
        FloorsCalculator("(())").evaluate shouldBe 0
        FloorsCalculator("()()").evaluate shouldBe 0
        FloorsCalculator("(((").evaluate shouldBe 3
        FloorsCalculator("(()(()(").evaluate shouldBe 3
        FloorsCalculator("))(((((").evaluate shouldBe 3
        FloorsCalculator("())").evaluate shouldBe -1
        FloorsCalculator("))(").evaluate shouldBe -1
        FloorsCalculator(")))").evaluate shouldBe -3
        FloorsCalculator(")())())").evaluate shouldBe -3
    }
}
