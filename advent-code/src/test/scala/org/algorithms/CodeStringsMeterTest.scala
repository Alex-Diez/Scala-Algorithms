package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class CodeStringsMeterTest extends FunSuite with Matchers {

    val csm = new CodeStringsMeter

    test("length of double quote is 2 and code length is 0") {
        csm.stringLength("\"\"") shouldBe 2
        csm.codeStringLength("\"\"") shouldBe 0
    }

    test("strings with characters") {
        csm.stringLength("\"abs\"") shouldBe 5
        csm.codeStringLength("\"abs\"") shouldBe 3

        csm.stringLength("\"bnvjg\"") shouldBe 7
        csm.codeStringLength("\"bnvjg\"") shouldBe 5

        csm.stringLength("\"1\"") shouldBe 3
        csm.codeStringLength("\"1\"") shouldBe 1

        csm.stringLength("\"bgdf\"") shouldBe 6
        csm.codeStringLength("\"bgdf\"") shouldBe 4

        csm.stringLength("\"injmkemz\"") shouldBe 10
        csm.codeStringLength("\"injmkemz\"") shouldBe 8
    }

    test("strings with escape symbols") {
        csm.stringLength("\"\\\\\"") shouldBe 4
        csm.codeStringLength("\"\\\\\"") shouldBe 1

        csm.stringLength("\"\\\"\"") shouldBe 4
        csm.codeStringLength("\"\\\"\"") shouldBe 1

        csm.stringLength("\"\\\'\"") shouldBe 4
        csm.codeStringLength("\"\\\'\"") shouldBe 1
    }

    test("hex representation of char") {
        csm.stringLength("\"v\\xfb\\\"lgs\\\"kvjfywmut\\x9cr\"") shouldBe 28
        csm.codeStringLength("\"v\\xfb\\\"lgs\\\"kvjfywmut\\x9cr\"") shouldBe 18
    }

    test("escape strings") {
        csm.escape("\"\"") shouldBe "\"\\\"\\\"\""

        csm.escape("\"abc\"") shouldBe "\"\\\"abc\\\"\""

        csm.escape("\"aaa\\\"aaa\"") shouldBe "\"\\\"aaa\\\\\\\"aaa\\\"\""

        csm.escape("\"\\x27\"") shouldBe "\"\\\"\\\\x27\\\"\""
    }

    test("length of escape strings") {
        csm.stringLength(csm.escape("\"\"")) shouldBe 6

        csm.stringLength(csm.escape("\"abc\"")) shouldBe 9

        csm.stringLength(csm.escape("\"aaa\\\"aaa\"")) shouldBe 16

        csm.stringLength(csm.escape("\"\\x27\"")) shouldBe 11
    }
}
