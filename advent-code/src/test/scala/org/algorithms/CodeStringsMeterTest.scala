package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class CodeStringsMeterTest extends FunSuite with Matchers {

    test("create a string meter") {
        new CodeStringsMeter
    }

    test("length of double quote is 2 and code length is 0") {
        val csm = new CodeStringsMeter

        csm.stringLength("\"\"") shouldBe 2
        csm.codeStringLength("\"\"") shouldBe 0
    }

    test("strings with characters") {
        val csm = new CodeStringsMeter

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
        val csm = new CodeStringsMeter

        csm.stringLength("\"\\\\\"") shouldBe 4
        csm.codeStringLength("\"\\\\\"") shouldBe 3

        csm.stringLength("\"\\\"\"") shouldBe 4
        csm.codeStringLength("\"\\\"\"") shouldBe 3

        csm.stringLength("\"\\\'\"") shouldBe 4
        csm.codeStringLength("\"\\\'\"") shouldBe 3
    }
}
