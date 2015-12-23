package org.algorithms

class LightsManager {
    val lights = Array.ofDim[Int](1000, 1000)

    def turnOn(xBegin: Int, yBegin:Int, xEnd:Int, yEnd:Int) = {
        for(i <- yBegin until yEnd) {
            for (j <- xBegin until  xEnd) {
                lights(i)(j) = 1
            }
        }
    }
}
