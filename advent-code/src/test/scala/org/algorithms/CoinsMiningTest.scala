package org.algorithms

import org.scalatest.{FunSuite, Matchers}

class CoinsMiningTest extends FunSuite with Matchers {

    val cmm = CoinsMiningMachine()

    test("mining hash for abcdef") {
        cmm.mine("abcdef") shouldBe 609043
    }

    test("mining hash for pqrstuv") {
        cmm.mine("pqrstuv") shouldBe 1048970
    }
}
