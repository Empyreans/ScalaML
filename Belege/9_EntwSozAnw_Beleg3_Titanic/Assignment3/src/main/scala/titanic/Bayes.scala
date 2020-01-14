package titanic

class Bayes(train: List[Map[String, Any]], test: List[Map[String, Any]]) {

  // TODO generalisieren?
  val allValidAges=test.map(_.getOrElse("age",0.0)).filter(_!=0.0).map(_.toString.toFloat)
  val agesMean=Math.round((allValidAges.reduce
  (_+_)/allValidAges.size)/10)*10

  def extraction(attributes: List[String], target: String): Map[String, (Double, Map[String, Map[String, Double]])] = {

    // preformatting of datastructure for better extraction of necessary values
    val a = train.map(
      pas =>
        (
          pas.getOrElse(target, "").toString,
          attributes.map(attr => Map(attr -> valueHandling(pas, attr))).reduce(_ ++ _)
        )
    ).filter(entry => entry._1 != "")

    // TODO Tuple-Zugriff ersetzen durch Pattern Matching
    // create table by counting classes and attributes
    val b = a.foldLeft(Map[String, (Double, Map[String, Map[String, Double]])]())((acc, a) => a match {
      case (curClass, curAttributes) => acc.get(curClass) match {
        case Some((accClassCounter, accAttributesForClass)) =>
          acc ++ Map(curClass -> (accClassCounter + 1.0, curAttributes.map(attr => accAttributesForClass.get(attr._1) match {
            case Some(thisAttrGroup) =>
              thisAttrGroup.get(attr._2) match {
                case Some(thisAttrCount) =>
                  Map(attr._1 -> (thisAttrGroup ++ Map(attr._2 -> (1.0 + thisAttrCount))))
                case None =>
                  Map(attr._1 -> (thisAttrGroup ++ Map(attr._2 -> 1.0)))
              }
            case None =>
              Map(attr._1 -> Map(attr._2 -> 1.0))
          }).reduce(_ ++ _)))
        case None =>
          acc ++ Map(curClass -> (1.0, curAttributes.flatMap(attr => Map(attr._1 -> Map(attr._2 -> 1.0)))))

      }
    })

    // prepare values of table for classification by calculating probabilities
    val c = b.map {case (target, (freqTarget, probTable)) => (target, (freqTarget / train.size, probTable.mapValues(m => m.mapValues(count => (count + 1) / (freqTarget + m.size) ))))} // Add-One-Smoothing

    c

  }

  def classify(attributes:List[String], probabilities: Map[String, (Double, Map[String, Map[String, Double]])]): List[(String, String)] = {

    val a = test.map(pas => (pas.getOrElse("passengerID", "").toString, attributes.map(att => (att, valueHandling(pas, att))))).filter(e => e._1 != "")

    // calculates probabilities for each class given attributes of entry
    val b = a.map {
      case (pasId, pasAttr) => (pasId, probabilities.map {
        case (target, (prioProbTarget, probs)) => (target, prioProbTarget * pasAttr.map {
          case (attrKey, attrVal) => probs.get(attrKey) match {
              case Some(e) => e.getOrElse(attrVal, 0.0)
              case None => 0.0
          }
        }.product)
      })
    }

    // predicts class based on highest calculated probability of each class
    // TODO Tidy
    val c = b.map(x => (x._1, x._2.foldLeft(("", 0.0))((acc, a) => if (a._2 > acc._2) a else acc)))

    // constructs result with relevant values
    val res = c.map {case (passengerId, (prediction, _)) => (passengerId, prediction)}

    res
  }

  def valueHandling(passengerAttributes: Map[String, Any], attribute: String) = {
    passengerAttributes.get(attribute) match {
      case Some(value) => categoryAllocationByAttributeAndValue(attribute, value.toString)
      case None => categoryAllocationByAttributeAndValue(attribute, replacementForEmptyValueByAttribute(attribute))
    }
  }

  def categoryAllocationByAttributeAndValue(attr: String, value:String): String = {
    attr match {
      case "age" => value.toFloat.toInt match {
        case v if v < 2               => "infant"
        case v if v >= 2  && v <= 12  => "child"
        case v if v >= 13 && v <= 18  => "adolescent"
        case v if v >= 19 && v <= 24  => "young adult"
        case v if v >= 25 && v <= 40  => "adult"
        case v if v >= 41 && v <= 59  => "middle aged"
        case v if v >= 60             => "senior"
        case _                        => throw new Exception("wrong input for age")
      }
      case _ => value
    }
  }

  def replacementForEmptyValueByAttribute(attr: String): String = {
    attr match {
      case "age" => agesMean.toString
      case _ => throw new Exception("Attribute with empty value encountered where no replacement strategy is available.")
    }
  }

}
