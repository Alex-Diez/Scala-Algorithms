package org.algorithms

import java.util.regex.Pattern

class PasswordIncrementor {

    val str = new StringsTypeResolver

    def increment(password: String): String = {
        def increment(password: String, index: Int, inc: Int, builder: StringBuilder): String = {
            if(index > -1) {
                val c = password.charAt(index)
                if(c == 'z') increment(password, index - 1, 1, builder.append('a'))
                else increment(password, index - 1, 0, builder.append((c + inc).toChar))
            }
            else {
                if(inc == 1) {
                    builder.append('a')
                }
                builder.reverse.toString
            }
        }
        increment(password, password.length - 1, 1, new StringBuilder)
//        java.lang.Long.toString(java.lang.Long.parseLong(password, 36) + 1, 36).replace('0', 'a')
    }

    def checkPassword(password: String): Boolean = {
        def hasDoublesPair(string: String): Boolean = {
//            var m:String = null
            val matcher = Pattern.compile("([a-z])\\1").matcher(string)
            matcher.find && matcher.find
        }
        hasDoublesPair(password) &&
                password.matches(".*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*") &&
                !password.matches(".*[iol].*")
    }
}

object PasswordIncrementor {

    def main(args: Array[String]) = {
        val pi = new PasswordIncrementor
        var password = "hepxcrrq"
        while(!pi.checkPassword(password)) {
            password = pi.increment(password)
        }
        println(password)
        password = pi.increment(password)
        while(!pi.checkPassword(password)) {
            password = pi.increment(password)
        }
        println(password)
    }
}
