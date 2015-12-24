package org.algorithms

class WiresConnectionManager {
    var signals = Map[String, () => Short]()

    private def defaultOperation(): () => Short = () => -1

    private def noOperationFunction(value: Short): () => Short = () => value

    private def andOperationOnWires(w1: String, w2: String): () => Short =
        () => (execWireFunc(w1) & execWireFunc(w2)).toShort

    private def orOperationOnWires(w1: String, w2: String): () => Short =
        () => (execWireFunc(w1) | execWireFunc(w2)).toShort

    private def lShiftOnWires(w1: String, shiftFactor: Short): () => Short =
        () => (execWireFunc(w1) << shiftFactor).toShort

    private def rShiftOnWires(w1: String, shiftFactor: Short): () => Short =
        () => (execWireFunc(w1) >> shiftFactor).toShort

    private def notOperationOnWires(wire: String): () => Short =
        () => (~execWireFunc(wire)).toShort

    private def execWireFunc(wire: String): Short = signals.getOrElse(wire, defaultOperation())()

    def connect(string: String) = {
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
                case "LSHIFT" => signals = signals + (parts(1) -> lShiftOnWires(operations(0), operations(2) toShort))
                case "RSHIFT" => signals = signals + (parts(1) -> rShiftOnWires(operations(0), operations(2) toShort))
            }
        }
    }

    def input(string: String) = {
        val parts = string.split(" -> ")
        signals = signals + (parts(1) -> noOperationFunction(parts(0).toShort))
    }

    def signalOn(wire: String): Short = execWireFunc(wire).toShort
}
