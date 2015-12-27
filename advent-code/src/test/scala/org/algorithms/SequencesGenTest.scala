package org.algorithms

import org.scalatest.{Matchers, FunSuite}

class SequencesGenTest extends FunSuite with Matchers {

    val sg = new SequenceGenerator

    test("one iteration on '1' sequence") {
        sg.generate("1", 1) shouldBe "11"
    }

    test("one iteration on '2' sequence") {
        sg.generate("2", 1) shouldBe "12"
    }

    test("fife iteration on '1' sequence") {
        sg.generate("1", 5) shouldBe "312211"
    }
}
