package org.algorithms

import scala.annotation.tailrec
import scala.io.Source._

class CodeStringsMeter {
    val escapedChars = Set("\\\'", "\\\"", "\\\\")

    def stringLength(codeString: String) = codeString.length

    def codeStringLength(codeString: String): Int = {
        @tailrec
        def parseChar(string: String, index: Int, counter: Int): Int = {
            if(index < string.length)
                if(index + 2 < string.length && escapedChars.contains(string.substring(index, index + 2)))
                    parseChar(string, index + 2, counter + 1)
                else if(index + 2 < string.length && string.substring(index, index + 2) == "\\x")
                         parseChar(string, index + 4, counter + 1)
                else parseChar(string, index + 1, counter + 1)
            else counter
        }
        parseChar(codeString, 0, 0) - 2
    }

    def escape(string: String): String = {
        @tailrec
        def escape(string: String, builder: StringBuilder, index: Int): String = {
            if(index < string.length) {
                val c = string.charAt(index)
                if(c == '\"' || c == '\\' || c == '\'') {
                    escape(string, builder.append('\\').append(c), index + 1)
                }
                else escape(string, builder.append(c), index + 1)
            }
            else builder.append('\"').toString
        }
        escape(string, new StringBuilder("\""), 0)
    }
}

object CodeStringsMeter {

    def main(args: Array[String]): Unit = {
        val csm = new CodeStringsMeter
        println(fromFile("advent-code/src/main/resources/code_strings").getLines.map[Int](s => csm.stringLength(s) - csm
                .codeStringLength(s)).sum)
        println(fromFile("advent-code/src/main/resources/code_strings").getLines.map[Int](s =>  csm.stringLength(csm.escape(s)) - csm.stringLength(s)).sum)
    }
}
