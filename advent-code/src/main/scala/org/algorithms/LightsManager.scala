package org.algorithms

import scala.io.Source._

class LightsManager(rows: Int, cols: Int) {
    val lights = Array.ofDim[Int](rows, cols)

    private def forRectangle(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int, func: (Int) => Int) = {
        for(i <- xBegin to xEnd) {
            for(j <- yBegin to yEnd) {
                lights(i)(j) = func(lights(i)(j))
            }
        }
    }

    def turnOn(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int) = {
        forRectangle(xBegin, yBegin, xEnd, yEnd, (b) => 1)
    }

    def turnOff(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int) = {
        forRectangle(xBegin, yBegin, xEnd, yEnd, (b) => 0)
    }

    def toggle(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int) = {
        forRectangle(xBegin, yBegin, xEnd, yEnd, (b) => if(b == 0) 1 else 0)
    }

    def turnBrightOn(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int) = {
        forRectangle(xBegin, yBegin, xEnd, yEnd, (b) => b + 1)
    }

    def turnBrightOff(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int) = {
        forRectangle(xBegin, yBegin, xEnd, yEnd, (b) => if (b > 0) b - 1 else 0)
    }

    def toggleBright(xBegin: Int, yBegin: Int, xEnd: Int, yEnd: Int) = {
        forRectangle(xBegin, yBegin, xEnd, yEnd, (b) => b + 2)
    }
}

object LightsManager {
    def apply(rows: Int, cols: Int) = new LightsManager(rows, cols)

    def main(args: Array[String]) = {
        {
            val lm = LightsManager(1000, 1000)
            fromFile("advent-code/src/main/resources/lights").getLines().foreach(s => {
                val parts = s.split(" ")
                val command = parts(0)
                val datas = parts(1).split(",").map(s => s toInt)
                if(command.equals("turnOn")) lm.turnOn(datas(0), datas(1), datas(2), datas(3))
                else if(command.equals("turnOff")) lm.turnOff(datas(0), datas(1), datas(2), datas(3))
                else lm.toggle(datas(0), datas(1), datas(2), datas(3))
            })
            println(lm.lights.map(a => a.sum).sum)
        }
        {
            val lm = LightsManager(1000, 1000)
            fromFile("advent-code/src/main/resources/lights").getLines().foreach(s => {
                val parts = s.split(" ")
                val command = parts(0)
                val datas = parts(1).split(",").map(s => s toInt)
                if(command.equals("turnOn")) lm.turnBrightOn(datas(0), datas(1), datas(2), datas(3))
                else if(command.equals("turnOff")) lm.turnBrightOff(datas(0), datas(1), datas(2), datas(3))
                else lm.toggleBright(datas(0), datas(1), datas(2), datas(3))
            })
            println(lm.lights.map(a => a.sum).sum)
        }
    }
}
