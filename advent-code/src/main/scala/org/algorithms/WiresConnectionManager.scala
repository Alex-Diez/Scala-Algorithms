package org.algorithms

import scala.io.Source._
import scala.collection.mutable

class WiresConnectionManager {
    var signals = Map[String, String]()
    var values = mutable.Map[String, Int]()

    private def parseArg(arg: String): Int = {
        if(arg.matches("[\\d]+")) arg.toInt else signalOn(arg)
    }

    def input(string: String) = {
        val parts = string.split(" -> ")
        signals = signals + (parts(1) -> parts(0))
    }

    def signalOn(wire: String): Int = {
        def analyseExpr(expr: String): Int = {
            if(expr.contains("AND")) evalExpr(expr, "AND", (fArg: String, sArg: String) => parseArg(fArg) & parseArg(sArg))
            else if(expr.contains("OR")) evalExpr(expr, "OR", (fArg: String, sArg: String) => parseArg(fArg) | parseArg(sArg))
            else if(expr.contains("LSHIFT")) evalExpr(expr, "LSHIFT", (fArg: String, sArg: String) => parseArg(fArg) << parseArg(sArg))
            else if(expr.contains("RSHIFT")) evalExpr(expr, "RSHIFT", (fArg: String, sArg: String) => parseArg(fArg) >> parseArg(sArg))
            else if(expr.contains("NOT")) ~parseArg(expr.substring(4))
            else parseArg(expr)
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
