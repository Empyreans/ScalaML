package wordcount

import java.awt.{Color, GridLayout}

import org.jfree.chart.{ChartPanel, JFreeChart}
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYDotRenderer
import org.jfree.data.xy.{XYSeries, XYSeriesCollection}
import org.jfree.ui.ApplicationFrame
import org.jfree.util.ShapeUtilities

import scala.io.Source


/**
  * @author hendrik
  * modified by akarakochev
  */
class Sentiments(sentiFile: String) {

  val sentiments: Map[String, Int] = getSentiments(sentiFile)

  val proc = new Processing()

  /** ********************************************************************************************
    *
    * Aufgabe 3
    *
    * ********************************************************************************************
    */

  // own implementation of grouped
  // TODO
/*  def groupedHelper(l: List[String], wordCount: Int): List[List[String]] = {
    l.foldLeft(wordCount, List[List[String]]())((acc, a) => wordCount match {
      case index => if (index == 0)
      case index => (index - 1, l)
    }
  }*/

  def getDocumentGroupedByCounts(filename: String, wordCount: Int): List[(Int, List[String])] = {
    Source.fromFile(getClass.getResource("/"+filename).getPath).getLines().toList
      .flatMap(l => proc.getWords(l))
      .grouped(wordCount)
      .toList
      // zipWithIndex =
      .foldLeft(0: Int, List[(Int, List[String])]())((acc, a) => acc match {
        case (index, list)  => (index + 1, list ++ List((index + 1, a)))
        case _              => (        0,         List((        0, a)))
      })._2
  }

  def getDocumentSplitByPredicate(filename: String, predicate:String=>Boolean): List[(Int, List[String])] = {
    Source.fromFile(getClass.getResource("/"+filename).getPath).getLines()
      .foldLeft((0, Map[Int, List[String]]()))((acc, word) => // acc, word oder acc, line?
        predicate(word) match {
          case true => (acc._1 + 1, acc._2 ++ Map(acc._1 + 1 -> List()))
          case _ => acc._2.get(acc._1) match {
            case Some(e) => (acc._1, acc._2.updated(acc._1, e ++ proc.getWords(word)))
            case None => acc
          }
        }
      )._2.toList
  }

  def getValues(l: List[String]) = {
    val s = l.map(x => sentiments.get(x) match {
      case Some(e) => e
      case None => 0
    })
    s.sum.toDouble / s.count(_ != 0).toDouble
  }

  def getRelFreq(l: List[String]): Double = {
    l.size / l.map(x => sentiments.get(x) match {
      case Some(e) => 1.0
      case None => 0
    }).reduce(_ + _)
  }

  def analyseSentiments(l: List[(Int, List[String])]) = {
    l.foldLeft(List[(Int, Double, Double)]())((acc, a) => {
      val temp = a._2.foldLeft(List[Int]())((ret, r) => sentiments.get(r) match {
        case Some(e) => ret ++ List(e)
        case None => ret
      })
      acc ++ List((a._1, temp.sum.toDouble / temp.size.toDouble, temp.size.toDouble / a._2.size.toDouble))
    })
  }

  /** ********************************************************************************************
    *
    * Helper Functions
    *
    * ********************************************************************************************
    */

  def getSentiments(filename: String): Map[String, Int] = {
    val url = getClass.getResource("/" + filename).getPath
    val src = scala.io.Source.fromFile(url)
    val iter = src.getLines()
    val result: Map[String, Int] = (for (row <- iter) yield {
      val seg = row.split("\t"); (seg(0) -> seg(1).toInt)
    }).toMap
    src.close()
    result
  }

  def createGraph(data: List[(Int, Double, Double)], xlabel:String="Abschnitt", title:String="Sentiment-Analyse"): Unit = {

    //create xy series
    val sentimentsSeries: XYSeries = new XYSeries("Sentiment-Werte")
    data.foreach { case (i, sentimentValue, _) => sentimentsSeries.add(i, sentimentValue) }
    val relWordsSeries: XYSeries = new XYSeries("Relative Haeufigkeit der erkannten Worte")
    data.foreach { case (i, _, relWordsValue) => relWordsSeries.add(i, relWordsValue) }

    //create xy collections
    val sentimentsDataset: XYSeriesCollection = new XYSeriesCollection()
    sentimentsDataset.addSeries(sentimentsSeries)
    val relWordsDataset: XYSeriesCollection = new XYSeriesCollection()
    relWordsDataset.addSeries(relWordsSeries)

    //create renderers
    val relWordsDot: XYDotRenderer = new XYDotRenderer()
    relWordsDot.setDotHeight(5)
    relWordsDot.setDotWidth(5)
    relWordsDot.setSeriesShape(0, ShapeUtilities.createDiagonalCross(3, 1))
    relWordsDot.setSeriesPaint(0, Color.BLUE)

    val sentimentsDot: XYDotRenderer = new XYDotRenderer()
    sentimentsDot.setDotHeight(5)
    sentimentsDot.setDotWidth(5)

    //create xy axis
    val xax: NumberAxis = new NumberAxis(xlabel)
    val y1ax: NumberAxis = new NumberAxis("Sentiment Werte")
    val y2ax: NumberAxis = new NumberAxis("Relative Haeufigfkeit")

    //create plots
    val plot1: XYPlot = new XYPlot(sentimentsDataset, xax, y1ax, sentimentsDot)
    val plot2: XYPlot = new XYPlot(relWordsDataset, xax, y2ax, relWordsDot)

    val chart1: JFreeChart = new JFreeChart(plot1)
    val chart2: JFreeChart = new JFreeChart(plot2)
    val frame: ApplicationFrame = new ApplicationFrame(title)
    frame.setLayout(new GridLayout(2,1))

    val chartPanel1: ChartPanel = new ChartPanel(chart1)
    val chartPanel2: ChartPanel = new ChartPanel(chart2)

    frame.add(chartPanel1)
    frame.add(chartPanel2)
    frame.pack()
    frame.setVisible(true)
  }
}
