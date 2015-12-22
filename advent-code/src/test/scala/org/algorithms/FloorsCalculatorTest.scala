package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class FloorsCalculatorTest extends FunSuite with Matchers {
    
    val calc = FloorsCalculator()

    test("evaluate first floor") {
        calc.floorNumber("(") shouldBe 1
        calc.floorNumber("()(") shouldBe 1
        calc.floorNumber("()()(") shouldBe 1
    }

    test("evaluate ten floor") {
        calc.floorNumber("(((((((" +
                "(((") shouldBe 10
        calc.floorNumber("()(((((((" +
                "(((") shouldBe 10
        calc.floorNumber("()()" +
                "((((((((((") shouldBe 10

    }

    test("test from site :)") {
        calc.floorNumber("(())") shouldBe 0
        calc.floorNumber("()()") shouldBe 0
        calc.floorNumber("(((") shouldBe 3
        calc.floorNumber("(()(()(") shouldBe 3
        calc.floorNumber("))(((((") shouldBe 3
        calc.floorNumber("())") shouldBe -1
        calc.floorNumber("))(") shouldBe -1
        calc.floorNumber(")))") shouldBe -3
        calc.floorNumber(")())())") shouldBe -3

        calc.basementIndex(")") shouldBe 1
        calc.basementIndex("()())") shouldBe 5
    }

    test("basement index should be 1") {
        calc.basementIndex(")") shouldBe 1
    }

    test("basement index should be 3") {
        calc.basementIndex("())") shouldBe 3
    }
}
