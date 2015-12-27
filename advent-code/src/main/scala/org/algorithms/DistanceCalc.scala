package org.algorithms

import scala.collection.mutable
import scala.io.Source._

class DistanceCalc {
    val paths = mutable.Map[String, mutable.ListBuffer[Neighbor]]()

    def add(source: String) = {
        val data = source.split(" to | = ")
        val dist = data(2).toInt
        updatePath(data(0), data(1), dist)
        updatePath(data(1), data(0), dist)
    }

    private def updatePath(v: String, w: String, dist: Int) = {
        paths.getOrElseUpdate(v, mutable.ListBuffer()) += new Neighbor(w, dist)
    }

    def shortestDistance(): Int = {
        def nearestNeighborsDist(v: String, marked: Map[String, Boolean]): Int = {
            if(marked.size < paths.size) {
                val neighbors = (new mutable.PriorityQueue[Neighbor]() ++ paths(v)).reverse
                var n = neighbors.dequeue
                while(marked.getOrElse(n.name, false)) {
                    n = neighbors.dequeue
                }
                n.dist + nearestNeighborsDist(n.name, marked + (n.name -> true))
            }
            else 0
        }
        val allDistances = new mutable.PriorityQueue[Int]()
        for(v <- paths.keysIterator) {
            allDistances += nearestNeighborsDist(v, Map(v -> true))
        }
        allDistances.reverse.dequeue
    }

    def longestDistance(): Int = {
        def farthestNeighborsDist(v: String, marked: Map[String, Boolean]): Int = {
            if(marked.size < paths.size) {
                val neighbors = new mutable.PriorityQueue[Neighbor]() ++ paths(v)
                var n = neighbors.dequeue
                while(marked.getOrElse(n.name, false)) {
                    n = neighbors.dequeue
                }
                n.dist + farthestNeighborsDist(n.name, marked + (n.name -> true))
            }
            else 0
        }
        val allDistances = new mutable.PriorityQueue[Int]()
        for(v <- paths.keysIterator) {
            allDistances += farthestNeighborsDist(v, Map(v -> true))
        }
        allDistances.dequeue
    }

    class Neighbor(val name: String, val dist: Int) extends Ordered[Neighbor] {
        override def compare(that: Neighbor): Int = dist - that.dist
    }
}

object DistanceCalc {

    def main(args: Array[String]) = {
        val sdc = new DistanceCalc
        fromFile("advent-code/src/main/resources/distances").getLines().foreach(l => sdc.add(l))
        println(sdc.shortestDistance())
        println(sdc.longestDistance())
    }
}
