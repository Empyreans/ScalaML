package titanic

object BayesApp extends App {

  val train = Utils.loadDataCSV("train.csv")
  val test = Utils.loadDataCSV("test.csv")

  val bayes = new Bayes(train, test)

  val target = "survived"
  val attributes = List("sex", "age")

  val probabilities = bayes.extraction(attributes, target)
  val predictions = bayes.classify(attributes, probabilities)

  Utils.createSubmitFile("bayesClassification.csv", predictions, "passengerId,survived")

}
