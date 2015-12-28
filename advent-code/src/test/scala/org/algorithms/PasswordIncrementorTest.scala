package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class PasswordIncrementorTest extends FunSuite with Matchers {

    val pi = new PasswordIncrementor

    test("increment one letter password") {
        pi.increment("a") shouldBe "b"
        pi.increment("c") shouldBe "d"
        pi.increment("o") shouldBe "p"
        pi.increment("r") shouldBe "s"

        pi.increment("z") shouldBe "aa"
    }

    test("increment two letters password") {
        pi.increment("aa") shouldBe "ab"

        pi.increment("az") shouldBe "ba"
        pi.increment("zz") shouldBe "aaa"
    }

    test("increment 'abcdfezz'") {
        pi.increment("abcdfezz") shouldBe "abcdffaa"
    }

    test("generate new password") {
        var password = "abcdefgh"
        while (!pi.checkPassword(password)) {
            password = pi.increment(password)
        }

        password shouldBe "abcdffaa"
    }

    test("resulted passwords") {
        var password = "hepxcrrq"
        while (!pi.checkPassword(password)) {
            password = pi.increment(password)
        }
        password shouldBe "hepxxyzz"
        password = pi.increment(password)

        while (!pi.checkPassword(password)) {
            password = pi.increment(password)
        }
        password shouldBe "heqaabcc"
    }
}
