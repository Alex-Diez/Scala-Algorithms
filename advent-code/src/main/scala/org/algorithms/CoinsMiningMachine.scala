package org.algorithms

import java.math.BigInteger
import java.security.MessageDigest

class CoinsMiningMachine {

    private def mine(code: String, zeroPrepend: Int): Int = {
        def mine(code: String, zeroPrepend: Int, next: Int, md: MessageDigest): Int = {
            md.reset()
            md.update((code + next).getBytes)
            if (new BigInteger(1, md.digest).toString(16).length != 32 - zeroPrepend)
                mine(code, zeroPrepend, next+ 1, md)
            else next
        }
        mine(code, zeroPrepend, 1, MessageDigest.getInstance("MD5"))
    }

    def mine(code: String): Int = {
        mine(code, 5)
    }

    def mineHard(code: String):Int = {
        mine(code, 6)
    }
}

object CoinsMiningMachine {
    def apply() = new CoinsMiningMachine

    def main(args: Array[String]): Unit = {
        val machine = CoinsMiningMachine()
        println(machine.mine("yzbqklnj"))
        println(machine.mineHard("yzbqklnj"))
    }
}
