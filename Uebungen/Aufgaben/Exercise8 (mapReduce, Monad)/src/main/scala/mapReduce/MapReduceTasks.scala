package mapReduce

//allows usage of .mapReduce for KeyValue lists, e.g. List.empty[(Int,String)].mapReduce(...)

import MapReduceImplicits._

import scala.io.Source

object MapReduceTasks extends App {

  val data = Source.fromResource("lyrics.txt").getLines().zipWithIndex.map { case (line, i) => (i, line) }.toList
  //Example lines
  println("########## Sample data ##########")
  data.take(3).foreach(println)
  println("############## END ##############")

  println(WordHelper.getWords(data.head._2).size)
  /**
   * Write a function, that counts all words using a single call of mapReduce. Use either the implicit version (data.mapReduce(???)(???))
   * or the direct call BasicOperations.mapReduce(data)(???)(???)
   */

   val wordsByCount = data.mapReduce(x => List((x._1, WordHelper.getWords(x._2).size)))(y => y)
  // println(wordsByCount)

  val matrix = List(
    0 -> List(1, 2, 3),
    1 -> List(4, 5, 6),
    2 -> List(7, 8, 9)
  )
  /**
   * Write a function, that transposes the matrix using a single call of mapReduce.
   * Use either the implicit version (data.mapReduce(???)(???))
   * or the direct call BasicOperations.mapReduce(data)(???)(???)
   */
  val transposedMatrix = ???
  println(transposedMatrix)
  val transposedExpected = List((0, List(1, 4, 7)), (1, List(2, 5, 8)), (2, List(3, 6, 9)))
  assert(transposedExpected == transposedMatrix)
}