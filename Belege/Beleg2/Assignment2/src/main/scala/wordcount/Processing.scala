package wordcount

import scala.util.{Failure, Success, Using}

class Processing {

  /** ********************************************************************************************
   *
   *                                    Aufgabe 1
   *
   * ********************************************************************************************
   */
  def getWords(line: String): List[String] = {
    /*
     * Extracts all words from a line
     * 
     * 1. Removes all characters which are not letters (A-Z or a-z)
     * 2. Shifts all words to lower case
     * 3. Extracts all words and put them into a list of strings
     */
    line match {
      case "" => Nil // empty string returns empty list TODO richtiger Ort für Error Handling?, warum wird hier eine List returned?
      case _ => line.replaceAll("[^A-Za-z ]", " ").trim.replaceAll(" +", " ").toLowerCase.split(" ").toList
    }

  }

  def getWordsNoLowercase(line: String): List[String] = {
    line match {
      case "" => Nil // empty string returns empty list TODO richtiger Ort für Error Handling?, warum wird hier eine List returned?
      case _ => line.replaceAll("[^A-Za-z ]", " ").trim.replaceAll(" +", " ").split(" ").toList
    }
  }

  def getAllWords(l: List[(Int, String)]): List[String] = {
    /*
     * Extracts all words from a List containing line number and line tuples
     * The words should be in the same order as they occur in the source document
     * 
     * Hint: Use the flatMap function
     */
    l.flatMap(s => getWords(s._2))
  }

  def countWords(l: List[String]): List[(String, Int)] = {
    /*
     *  Gets a list of words and counts the occurrences of the individual words
     */
    l.groupBy(identity).view.mapValues(s => s.size).toList
  }


  /** ********************************************************************************************
   *
   *                                Aufgabe 2
   *
   * ********************************************************************************************
   */

  def getAllWordsWithIndex(l: List[(Int, String)]): List[(Int, String)] = {
    l.flatMap(x => getWords(x._2).map(s => (x._1, s))) // TODO flatMap besser nachvollziehen
  }

  def createInverseIndex(l: List[(Int, String)]): Map[String, List[Int]] = {
    l.foldLeft(Map[String, List[Int]]())((acc, a) => acc.get(a._2) match {
      case Some(e) => acc + (a._2 -> (e ++ List(a._1)))
      case None => acc ++ Map(a._2 -> List(a._1))
    })
  }

  def orConjunction(words: List[String], invInd: Map[String, List[Int]]): List[Int]= {
    words.foldLeft(List[Int]())((acc, a) => invInd.get(a) match {
      case Some(e) => acc ++ e
      case None => acc
    })
  }

  def andConjunction(words: List[String], invInd: Map[String, List[Int]]) = {

    // experimental, just to see how I would implement contains myself
    def containsHelper(l: List[Int], elem: Int): Boolean = {
      l.foldLeft(false)((acc, a) => if (elem == a) true else acc)
    }

    // list of indizes for every word
    val allInd = words.map(x => invInd.get(x) match {
      case Some(e) => e
      case None => Nil
    })

    // distinct indizes
    val distInd = allInd.flatten.foldLeft(List[Int]())((acc, a) => if (containsHelper(acc, a)) acc else acc ++ List(a))

    println(allInd)
    println(distInd)

    distInd.filter(x => allInd.forall(y => y.contains(x)))

  }
}

object Processing {

  def getData(filename: String): List[(Int, String)] = {
    Using(scala.io.Source.fromResource(filename)) {
      src => src.getLines().zipWithIndex.map { case (nr, line) => (line, nr) }.toList
    } match {
      case Success(value) => value
      case Failure(exception) => throw exception
    }
  }
}