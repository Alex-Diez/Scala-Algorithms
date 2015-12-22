package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class StringsTypeResolverTest extends FunSuite with Matchers {

    val str = new StringsTypeResolver

    test("string 'ugknbfddgicrmopn' should be nice") {
        str.isNice("ugknbfddgicrmopn") shouldBe true
    }

    test("string 'aaa' should be nice") {
        str.isNice("aaa") shouldBe true
    }

    test("string 'jchzalrnumimnmhp' should not be nice") {
        str.isNice("jchzalrnummnmhp") shouldBe false
    }

    test("string 'haegwjzuvuyypxyu' should not be nice") {
        str.isNice("haegwjzuvuyypxyu") shouldBe false
    }

    test("string 'dvszwmarrgswjxmb' should not be nice") {
        str.isNice("dvszwmarrgswjxmb") shouldBe false
    }
}
