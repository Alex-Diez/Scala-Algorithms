package org.algorithms

import scala.annotation.tailrec
import scala.io.Source._

class StringsTypeResolver {
    def isNice(string: String): Boolean = {
        @tailrec
        def hasThreeVowels(string: String, index: Int, vowels: List[Char], allowedVowels: Set[Char]): Boolean = {
            if (vowels.size == 3) true
            else if (index < string.length) {
                val c = string.charAt(index)
                if (allowedVowels.contains(c)) hasThreeVowels(string, index + 1, c :: vowels, allowedVowels)
                else hasThreeVowels(string, index + 1, vowels, allowedVowels)
            }
            else false
        }
        @tailrec
        def hasDoubles(string: String, index: Int): Boolean = {
            if (index < string.length && (index + 1) < string.length) {
                val c = string.charAt(index)
                val next = string.charAt(index + 1)
                if (c == next) {
                    if (string.indexOf(string.substring(index, index + 1), index + 1) != -1) true
                    else hasDoubles(string, index + 1)
                }
                else hasDoubles(string, index + 1)
            }
            else false
        }
        if (Set("ab", "cd", "pq", "xy").forall(s => !string.contains(s)))
            hasDoubles(string, 0) && hasThreeVowels(string, 0, List(), Set('a', 'e', 'i', 'o', 'u'))
        else false
    }

    def isSuperNice(string: String): Boolean = {
        @tailrec
        def hasCharsPair(string: String, index: Int): Boolean = {
            if (index + 2 < string.length) {
                val sub = string.substring(index, index + 2)
                if (index + 2 < string.length) {
                    if (string.indexOf(sub, index + 2) != -1) true
                    else hasCharsPair(string, index + 1)
                }
                else false
            }
            else false
        }
        @tailrec
        def hasLetterBetweenDouble(string: String, index: Int): Boolean = {
            if (index < string.length) {
                val c = string.charAt(index)
                if (index + 2 < string.length) {
                    if (c == string.charAt(index + 2)) true
                    else hasLetterBetweenDouble(string, index + 1)
                }
                else false
            }
            else false
        }
        hasCharsPair(string, 0) && hasLetterBetweenDouble(string, 0)
    }
}

object StringsTypeResolver {

    def main (args: Array[String]) {
        val str = new StringsTypeResolver
        println(fromFile("advent-code/src/main/resources/strings").getLines().count(s => str.isNice(s)))
        println(fromFile("advent-code/src/main/resources/strings").getLines().count(s => str.isSuperNice(s)))
    }
}
