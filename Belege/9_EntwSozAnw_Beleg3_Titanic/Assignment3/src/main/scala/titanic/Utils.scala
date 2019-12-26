package titanic

import java.io.PrintWriter

import scala.util.Try


object Utils {

  // Regular Expressions for extracting the information
  val DATA_ACCESS_PATTERN_test = """(\d+),(\d),"(.+)",(male|female),([0-9]*\.[0-9]+|[0-9]+|d*),(\d*),(\d*),(.*),([0-9]*\.[0-9]+|[0-9]+|d*),(.*),(\w*)""".r
  val DATA_ACCESS_PATTERN_train = """(\d+),(\d),(\d),"(.+)",(male|female),([0-9]*\.[0-9]+|[0-9]+|d*),(\d*),(\d*),(.*),([0-9]*\.[0-9]+|[0-9]+|d*),(.*),(\w*)""".r

  // Reading text file
  // Stores the information in a map consisting of a property name (key) and its value
  def loadDataCSV(filename: String): List[Map[String, Any]] = {

    val stream = getClass.getResourceAsStream("/" + filename)
    val src = scala.io.Source.fromInputStream(stream)
    val iter = src.getLines().drop(1) //skip first line (property names)

    val result = (for (row <- iter) yield readData(row)).toList

    src.close
    result.flatMap(_ match { case p: Option[Map[String, Any]] => p })
  }


  // Extracting all information storing it into a Map[String,Any]
  def readData(line: String): Option[Map[String, Any]] = {

    def toInt(key: String, s: String): Option[(String, Int)] = Try(s.toInt).toOption.map((key, _))

    def toFloat(key: String, s: String): Option[(String, Float)] = Try(s.toFloat).toOption.map((key, _))

    def toString(key: String, s: String): Option[(String, String)] =
      if (s.nonEmpty) Some((key, s)) else None

    def createPassengerMap(t1: String, t2: String, t3: String, t4: String, t5: String, t6: String, t7: String,
                           t8: String, t9: String, t10: String, t11: String, t12: String): Option[Map[String, Any]] = {

      val l = List(
        toInt("passengerID", t1),
        toInt("survived", t2),
        toInt("pclass", t3),
        toString("name", t4),
        toString("sex", t5),
        toFloat("age", t6),
        toInt("sibsp", t7),
        toInt("parch", t8),
        toString("ticket", t9),
        toFloat("fare", t10),
        toString("cabin", t11),
        {
          if (t12.length > 0) Some(("embarked", t12(0))) else None
        })
      Some(l.flatMap(_ match { case p: Option[(String, Any)] => p }).toMap)
    }

    val result = line match {
      case DATA_ACCESS_PATTERN_test(t1, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) =>
        createPassengerMap(t1, "-1", t3, t4, t5, t6, t7, t8, t9, t10, t11, t12)

      case DATA_ACCESS_PATTERN_train(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) => {
        createPassengerMap(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12)
      }

    }
    result
  }

  // Method for printing a passenger in a readable manner
  def printPassenger(p: Map[String, Any]): Unit = {

    println("\n---------------------------------------------------------------------")
    println("passengerID:" + p.getOrElse("passengerID", -1))
    println("survived:" + p.getOrElse("survived", -1))
    println("pclass:" + p.getOrElse("pclass", -1))
    println("name:" + p.getOrElse("name", "-"))
    println("sex:" + p.getOrElse("sex", "-"))
    println("age:" + p.getOrElse("age", -1))
    println("sibsp:" + p.getOrElse("sibsp", -1))
    println("parch:" + p.getOrElse("parch", -1))
    println("ticket:" + p.getOrElse("ticket", "-"))
    println("fare:" + p.getOrElse("fare", -1))
    println("cabin:" + p.getOrElse("cabin", -1))
    println("embarked:" + p.getOrElse("embarked", '-'))
    println("---------------------------------------------------------------------\n")
  }

  /** : Map[String, Int]
   * Returns all missing values by attribute
   *
   * @param passengers list of passengers attribute maps
   * @param attList    A mapping from attributes to missing values count
   * @return
   */


  //  LÖSUNG
  //  TODO einfacher?  gleich folden?
  def countAllMissingValues(passengers: List[Map[String, Any]], attList: List[String]) =
    passengers.flatMap(passenger => attList.map(att => if (passenger.contains(att)) null else att).filter(_ != null))
    .foldLeft(Map[String, Int]())((acc, a) => acc.get(a) match {
      case Some(e) => acc + (a -> (1 + e))
      case None => acc + (a -> 1)
    })

  // TODO Anton fragen, siehe Zettel, wie lösen?
/*  def countAllMissingValues(passengers: List[Map[String, Any]], attList: List[String]) =
    passengers.foldLeft(Map[String, Integer]())((acc, a) => attList.filter(att => !a.contains(att)) )*/

  // NAIVE BAYES
  // TODO neue Attribute einbeziehen
  // TODO Missing Values
  // TODO Kaggle Export

  // for missing age values
  def getMeanAge(passengers: List[Map[String, Any]], attribute: String): String = {
    ???
  }

  def getAgeClasses(age: String): String = {
    // 0-2    infant
    // 2-12   child
    // 13-18  adolescent
    // 19-24  young adult
    // 25-40  adult
    // 40-60  middle aged
    // 60+    aged
    ???
  }

  def extraction(passengers: List[Map[String, Any]], attributes: List[String], target: String) = {

    // preformatting of datastrucutre for better extraction of necessary values
    val a = passengers.map(pas => (pas.get(target), attributes.map(attr => Map(attr -> pas.get(attr))).reduce(_ ++ _)))
    println(a)
    
    // resolving of empty values
/*    val b = a.map(entry => entry._1 match {
      case Some(e) => (e.toString, entry._2.map(e => e.getOrElse("").toString).filter(e => e != "")) // missing attribute values are simply filtered out
      case None => null // if the class key is not found in the passenger entry, the entry can be ignored
    }).filter(entry => entry._1 != null) // filter out the entry with missing class key*/

/*    val b = a.map(entry => entry._1 match {
      case Some(e) => (e.toString, entry._2 match {
        case attributeList => attributeList.map(ent => ent.getOrElse(""))
      })
      case None => null
    }).filter(entry => entry._1 != null)*/

    // create table by counting classes and attributes
/*    val c = b.foldLeft(Map[String, (Double, Map[String, Double])]())((acc, a: (String, List[String])) => a match {
      case (curClassValue, curAttributeValues) => acc.get(curClassValue) match {
        case Some((classCounter, exisAttributesForClass)) => curAttributeValues.flatMap(attr => exisAttributesForClass.get(attr) match { // TODO warum funktioniert es mit flatMap
          case Some(attributeCounter) => acc ++ Map(curClassValue -> (classCounter + 1, exisAttributesForClass ++ Map(attr -> (1.0 + attributeCounter))))
          case None                   => acc ++ Map(curClassValue -> (classCounter + 1, exisAttributesForClass ++ Map(attr ->  1.0)))
        }).toMap
        case None                                         => curAttributeValues.flatMap(attr => acc ++ Map(curClassValue -> (1.0,Map(attr -> 1.0)))).toMap
      }
    })

    // prepare values of table for calculations
    val d = c.map(entry => entry match {
      case (clazz, (relFreqClass, probTable)) => (clazz, (relFreqClass / passengers.size, probTable.mapValues(prob => prob / relFreqClass)))
    })

    d*/
  }

  def classify(passengers: List[Map[String, Any]], attributes:List[String], probabilities:  Map[String, (Double, Map[String, Double])]) = {
    // TODO description
    val a = passengers.map(pas => attributes.map(att => pas.get(att) match {
      case Some(e) => e.toString
      case None    => ""
    }).filter(e => e != null)) // TODO ""

    // calculates probabilities for each class given attributes of entry
    // TODO Tidy up
    val b = a.map(attr => probabilities.map(prob => (prob._1, prob._2._1 * attr.map(a => prob._2._2.getOrElse(a, 0.0)).product)))

    // predicts/decides class based on prior calculations
    // TODO Tidy up
    val c = b.map(x => x.foldLeft(("", 0.0))((acc, a) => if (a._2 > acc._2) a else acc))

    c
  }

  //produces sometimes an missing argument list error - can be ignored
  def applyModel[CLASS, ID](model: (Map[String, Any], String) => (ID, CLASS),
                            testdata: Seq[Map[String, Any]], idKey: String): Seq[(ID, CLASS)] = {

    testdata.map(d => model(d, idKey))
  }

  def createSubmitFile[ID, CLASS](filename: String, data: Seq[(ID, CLASS)], header: String): Unit = {
    val pw = new PrintWriter(filename)
    pw.println(header)
    data.foreach(e => pw.println(e._1.toString + "," + e._2.toString))
    pw.close()
  }
} 
  