package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class StringsTypeResolverTest extends FunSuite with Matchers {

    val str = new StringsTypeResolver

    test("string with length 3 all vowels should be nice") {
        str.isNice("aei") shouldBe true
        str.isNice("aeo") shouldBe true
        str.isNice("aeu") shouldBe true
        str.isNice("aio") shouldBe true
        str.isNice("aiu") shouldBe true
        str.isNice("aie") shouldBe true
        str.isNice("aue") shouldBe true
    }

    test("string 'ugknbfddgicrmopn' should be nice") {
        str.isNice("ugknbfddgicrmopn") shouldBe true
    }

    test("string 'aaa' should be nice") {
        str.isNice("aaa") shouldBe true
    }

    test("string 'jchzalrnumimnmhp' should not be nice") {
        str.isNice("jchzalrnumimnmhp") shouldBe false
    }

    ignore("string 'haegwjzuvuyypxyu' should not be nice") {
        str.isNice("haegwjzuvuyypxyu") shouldBe false
    }

    ignore("string 'dvszwmarrgswjxmb' should not be nice") {
        str.isNice("dvszwmarrgswjxmb") shouldBe false
    }
}
