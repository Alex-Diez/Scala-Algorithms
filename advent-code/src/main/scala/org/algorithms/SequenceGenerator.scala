package org.algorithms

class SequenceGenerator {
    def generate(sequence: String, numberOfIteration: Int) = {
        def generate(sequence: String, index: Int, builder: StringBuilder): String = {
            if (index < sequence.length) {
                val p = sequence.charAt(index)
                var counter = 1
                while(index + counter < sequence.length && sequence.charAt(index + counter) == p) {
                    counter += 1
                }
                generate(sequence, index + counter, builder.append(counter).append(p))
            }
            else builder.toString
        }
        var data = sequence
        var counter = 0
        while (counter < numberOfIteration) {
            data = generate(data, 0, new StringBuilder)
            counter += 1
        }
        data
    }
}

object SequenceGenerator {

    def main(args: Array[String]) = {
        val sg =  new SequenceGenerator
        val generate: String = sg.generate("3113322113", 40)
        println(generate)
        println(generate.length)
    }
}
