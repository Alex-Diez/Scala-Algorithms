package org.algorithms

import scala.collection.mutable

class ShortestDistanceCalc {
    val g = new Graph
    def distance():Int = {
        new ShortestPath(g).edges().map[Int](e => e.weight).sum
    }

    def add(datum: String):Unit = {
        val data = datum.split(" ")
        g.addEdge(new Edge(data(0), data(2), data(4).toInt))
    }

    class Edge(val v: String, val w: String, val weight: Int) extends Ordered[Edge] {
        override def compare(that: Edge): Int = weight - that.weight
    }

    class Graph {
        val adjacent = mutable.Map[String, List[Edge]]()
        val edges = mutable.ArrayBuffer[Edge]()

        def addEdge(edge: Edge) = {
            updateVertex(edge.v, edge)
            updateVertex(edge.w, edge)
            edges += edge
        }

        private def updateVertex(v: String, edge: Edge) = {
            val vList = adjacent.getOrElse(v, List())
            adjacent.update(v, edge :: vList)
        }
    }

    class ShortestPath(private val g: Graph) {
        val queue = new mutable.PriorityQueue[Edge]()
        val mst = new mutable.Queue[Edge]()
        queue ++= g.edges
        val marked = mutable.Map[String, Boolean]()
        visit(g, g.adjacent.iterator.next._1)

        while (queue.nonEmpty && mst.size < g.adjacent.size - 1) {
            val e = queue.dequeue()
            val v = e.v
            val w = e.w
            if (!marked.getOrElseUpdate(v, false) || !marked.getOrElseUpdate(w, false)) {
                mst.enqueue(e)
            }
            if (!marked(v)) visit(g, v)
            if (!marked(w)) visit(g, w)
        }

        def edges():Iterator[Edge] = {
            mst.iterator
        }

        private def visit(g: Graph, v: String) = {
            marked.update(v, true)
            for (e <- g.adjacent(v)) {
                if(!marked.getOrElseUpdate(e.w, false)) {
                    queue += e
                }
            }
        }
    }
}
