package org.algorithms

import scala.collection.mutable
import scala.io.Source._

class ShortestDistanceCalc {
    val paths = mutable.Map[String, mutable.ListBuffer[String]]()
    val distances = mutable.Map[String, Int]()

    def add(source: String) = {
        val data = source.split(" to | = ")
        updatePath(data(0), data(1))
        updatePath(data(1), data(0))
        val dist = data(2).toInt
        updateDist(data(0),data(1), dist)
        updateDist(data(1),data(0), dist)
    }

    private def updateDist(v: String, w: String, dist: Int) = {
        distances += (v + " -> " + w -> dist)
    }

    private def updatePath(v: String, w: String) = {
        paths.getOrElseUpdate(v, mutable.ListBuffer()) += w
    }

    def distance(): Int = {
        val vs = paths.keysIterator
        val queue = mutable.PriorityQueue[Int]()
        for (v <- vs) {
            var dist = 0
            for (w <- paths(v)) {
                dist += distances(v + " -> " + w)
            }
            queue.enqueue(dist)
        }
        queue.reverseIterator.next
    }

}

object ShortestDistanceCalc {

    def main(args: Array[String]) = {
        val sdc = new ShortestDistanceCalc
        fromFile("advent-code/src/main/resources/distances").getLines().foreach(l => sdc.add(l))
        println(sdc.distance())
    }
}
