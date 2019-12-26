package mapReduce

// From Beleg 2
object WordHelper {
  def getWords(line: String): List[String] = {
    line match {
      case "" => Nil // empty string returns empty list TODO richtiger Ort für Error Handling?, warum wird hier eine List returned?
      case _ => line.replaceAll("[^A-Za-z ]", " ").trim.replaceAll(" +", " ").toLowerCase.split(" ").toList
    }

  }

  def countWords(l: List[String]): List[(String, Int)] = {
    l.groupBy(identity).view.mapValues(s => s.size).toList
  }
}
