package org.algorithms

import java.math.BigInteger
import java.security.MessageDigest

class CoinsMiningMachine {
    val md = MessageDigest.getInstance("MD5")

    def mine(code: String) = {
        def hexCodeFor(s: String, n: Int): String = {
            val data = code + n
            val dataByte = data.getBytes()
            md.reset()
            md.update(dataByte)
            val b = new BigInteger(1, md.digest)
            b.toString(16)
        }
        var iter = 1
        while (hexCodeFor(code, iter).length != 27) {
            iter += 1
        }
        iter
    }
}

object CoinsMiningMachine {
    def apply() = new CoinsMiningMachine

    def main(args: Array[String]): Unit = {
        println(CoinsMiningMachine().mine("yzbqklnj"))
    }
}
