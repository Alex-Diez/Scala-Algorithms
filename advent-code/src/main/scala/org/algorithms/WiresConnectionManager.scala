package org.algorithms

import scala.io.Source._
import scala.collection.mutable

class WiresConnectionManager {
    var signals = Map[String, String]()
    var values = mutable.Map[String, Int]()

    private def parseArg(arg: String, index:Int): Int = {
        if(arg.matches("[\\d]+")) arg.toInt else signalOn(arg)
    }

    def input(string: String) = {
        val parts = string.split(" -> ")
        signals = signals + (parts(1) -> parts(0))
    }

    private def signalOn(wire: String, index: Int): Int = {
        def analyseExpr(expr: String): Int = {
            if(expr.contains("AND")) evalExpr(expr, "AND", (fArg: String, sArg: String) => parseArg(fArg, index) & parseArg(sArg, index))
            else if(expr.contains("OR")) evalExpr(expr, "OR", (fArg: String, sArg: String) => parseArg(fArg, index) | parseArg(sArg, index))
            else if(expr.contains("LSHIFT")) evalExpr(expr, "LSHIFT", (fArg: String, sArg: String) => parseArg(fArg, index) << parseArg(sArg, index))
            else if(expr.contains("RSHIFT")) evalExpr(expr, "RSHIFT", (fArg: String, sArg: String) => parseArg(fArg, index) >> parseArg(sArg, index))
            else if(expr.contains("NOT")) ~parseArg(expr.substring(4), index)
            else parseArg(expr, index)
        }
        def evalExpr(expr: String, op: String, func: (String, String) => Int): Int = {
            val exprs = expr.split(" " + op + " ")
            func(exprs(0), exprs(1))
        }
        signals.get(wire) match {
            case Some(expr) => values.getOrElseUpdate(wire, analyseExpr(expr) & 65535)
            case None => -1
        }

    }

    def signalOn(wire: String): Int = {
        signalOn(wire, 0)
    }
}

object WiresConnectionManager {

    def main(args: Array[String]) = {
        val wcm = new WiresConnectionManager
        fromFile("advent-code/src/main/resources/bitwise").getLines().foreach(l => wcm.input(l))
        val on: Int = wcm.signalOn("a")
        println(on)
        wcm.values.clear()
        wcm.values.put("b", on)
        println(wcm.signalOn("a"))
    }
}
