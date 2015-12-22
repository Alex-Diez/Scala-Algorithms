package org.algorithms

import scala.io.Source._

class StringsTypeResolver {
    def isNice(string: String): Boolean = {
        if (!string.contains("ab") && !string.contains("cd") && !string.contains("pq") && !string.contains("xy")) {
            var previous = ' '
            var vowels = List[Char]()
            var hasDoubles = false
            val vowelsSet = Set('a', 'e', 'i', 'o', 'u')
            val iter = string.iterator
            while (iter.hasNext) {
                val c = iter.next
                if (!hasDoubles && c == previous) {
                    hasDoubles = true
                }
                if (vowelsSet.contains(c)) {
                    vowels = c :: vowels
                }
                previous = c
            }
            hasDoubles && vowels.size > 2
        }
        else {
            false
        }
    }
}

object StringsTypeResolver {

    def main (args: Array[String]) {
        val str = new StringsTypeResolver
        val data = fromFile("advent-code/src/main/resources/strings")
                .getLines().count(s => str.isNice(s))
        println(data)
    }
}
