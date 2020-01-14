package test

import org.scalatest.FunSuite
import titanic._

class UtilsTest extends FunSuite {

  // load datsets                    
  val train = Utils.loadDataCSV("train.csv")
  val test = Utils.loadDataCSV("test.csv")
  val all = train ++ test

  test("Test size of the datesets") {
    assert(train.size === 891)
    assert(test.size === 418)
  }

  test("Count missing values test") {
    
    val attList = List("passengerID", "pclass", "survived", "name", "sex", "age", "sibsp", "parch",
      "ticket", "fare", "cabin", "embarked")

    val train_mv = Utils.countAllMissingValues(train, attList)
    val test_mv = Utils.countAllMissingValues(test, attList)
    assert(train_mv("cabin") == 687 && train_mv("age") == 177 && train_mv("embarked") == 2)
    assert(test_mv("cabin") == 327 && test_mv("age") == 86 && test_mv("fare") == 1)

    // val train_mv2 = Utils.countAllMissingValues2(train, attList)
    // assert(train_mv2("cabin") == 687)
  }

  test("Extraction & Classification") {

    val bayes = new Bayes(train, test)
    val target = "survived"
    val attributes = List("sex", "age")

    val probabilities = bayes.extraction(attributes, target)
    val predicitions = bayes.classify(attributes, probabilities)
  }

  test("size") {
    val l = List(("A", "a"), ("A", "b"), ("B", "a"), ("C", "a"), ("C", "b"))

    l.foldLeft(Map[String, List[String]]())((acc, a) => acc.get(a._1) match {
      case Some(list) => acc ++ Map(a._1 -> (list :: List(a._2)))
      case None => acc ++ Map(a._1 -> (List(a._2)))
    })
  }
}