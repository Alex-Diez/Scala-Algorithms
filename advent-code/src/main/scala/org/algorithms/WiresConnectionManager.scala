package org.algorithms

import scala.io.Source._

class WiresConnectionManager {
    val ops = Set("AND", "OR", "NOT", "LSHIFT", "RSHIFT")
    val digs = '0' until '9' toSet
    var signals = Map[String, () => Int]()

    private def defaultOperation(): () => Int = () => -1

    private def noOperationFunction(value: Int): () => Int = () => value

    private def andOperationOnWires(w1: String, w2: String): () => Int =
        () => ((execWireFunc(w1) & execWireFunc(w2)) << 16) >> 16

    private def orOperationOnWires(w1: String, w2: String): () => Int =
        () => ((execWireFunc(w1) | execWireFunc(w2)) << 16) >> 16

    private def lShiftOnWires(w1: String, shiftFactor: Int): () => Int =
        () => ((execWireFunc(w1) << shiftFactor) << 16) >> 16

    private def rShiftOnWires(w1: String, shiftFactor: Int): () => Int =
        () => ((execWireFunc(w1) >> shiftFactor) << 16) >> 16

    private def notOperationOnWires(wire: String): () => Int =
        () =>  ~execWireFunc(wire) & 65535

    private def execWireFunc(wire: String): Int = signals.getOrElse(wire, defaultOperation())()

    private def connect(string: String) = {
        val parts = string.split(" -> ")
        if (parts(0).startsWith("NOT")) {
            val operations = parts(0).split(" ")
            signals = signals + (parts(1) -> notOperationOnWires(operations(1)))
        }
        else {
            val operations = parts(0).split(" ")
            operations(1) match {
                case "AND"    => signals = signals + (parts(1) -> andOperationOnWires(operations(0), operations(2)))
                case "OR"     => signals = signals + (parts(1) -> orOperationOnWires(operations(0), operations(2)))
                case "LSHIFT" => signals = signals + (parts(1) -> lShiftOnWires(operations(0), operations(2) toInt))
                case "RSHIFT" => signals = signals + (parts(1) -> rShiftOnWires(operations(0), operations(2) toInt))
            }
        }
    }

    private def addSource(string: String) = {
        val parts = string.split(" -> ")
        if (digs.exists(c => parts(0).contains(c))) signals = signals + (parts(1) -> noOperationFunction(parts(0).toInt))
        else signals = signals + (parts(1) -> signals.getOrElse(parts(0), defaultOperation()))
    }

    def input(string: String) = {
        if (!ops.exists(op => string.contains(op))) addSource(string)
        else connect(string)
    }

    def signalOn(wire: String): Int = execWireFunc(wire)
}

object WiresConnectionManager {

    def main(args: Array[String]) = {
        val wcm = new WiresConnectionManager
        fromFile("advent-code/src/main/resources/bitwise").getLines().foreach(l => wcm.input(l))
        println(wcm.signalOn("a"))
    }
}
