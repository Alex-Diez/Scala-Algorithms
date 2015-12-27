package org.algorithms

import scala.collection.mutable
import scala.io.Source._

class DistanceCalc {
    val paths = mutable.Map[String, mutable.PriorityQueue[Neighbor]]()

    def add(source: String) = {
        val data = source.split(" to | = ")
        val dist = data(2).toInt
        updatePath(data(0), data(1), dist)
        updatePath(data(1), data(0), dist)
    }

    private def updatePath(v: String, w: String, dist: Int) = {
        paths.getOrElseUpdate(v, mutable.PriorityQueue()) += new Neighbor(w, dist)
    }

    def shortestDistance(): Int = {
        def nearestNeighborsDist(v: String, marked: Map[String, Boolean]): Int = {
            if (marked.size < paths.size) {
                val neighbors = paths(v).iterator
                var n = neighbors.next
                while(marked.getOrElse(n.name, false)) {
                    n = neighbors.next
                }
                val r = n.dist + nearestNeighborsDist(n.name, marked + (n.name -> true))
                r
            }
            else 0
        }
        val allDistances = new mutable.PriorityQueue[Int]()
        for (v <- paths.keysIterator) {
            val dist = nearestNeighborsDist(v, Map(v -> true))
            allDistances += dist
        }
        allDistances.reverse.head
    }

    class Neighbor(val name: String, val dist: Int) extends Ordered[Neighbor] {
        override def compare(that: Neighbor): Int = that.dist - dist
    }
}

object DistanceCalc {

    def main(args: Array[String]) = {
        val sdc = new DistanceCalc
        fromFile("advent-code/src/main/resources/distances").getLines().foreach(l => sdc.add(l))
        println(sdc.shortestDistance())
    }
}
