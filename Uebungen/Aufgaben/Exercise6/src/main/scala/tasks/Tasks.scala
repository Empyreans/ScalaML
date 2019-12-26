package tasks

import utils.Data
import models.Movie

object Tasks extends App {

  val movies = Data.readFromResources()
  println("################################ DATA ################################")
  movies.map(println)
  println("################################ END ################################")

  def bradPittMovies(movielist: List[Movie]): List[String] = movielist.filter(f => f.staring.contains("Brad Pitt")).map(m => m.title)

  // without contains
  def bradPittMovies2(movielist: List[Movie]) = movielist.filter(m => m.staring.filter(s => s == "Brad Pitt") match {
    case Nil => false
    case _ => true // non-empty (x :: xs)
  })

  println("bradPittMovies2")
  println(bradPittMovies2(movies).map(m => m.title))

  def shorterThan120(movielist: List[Movie]) = movielist.filter(m => m.durationInMinutes < 120)

  println("shorterThan120")
  println(shorterThan120(movies).map(m => m.title))

  def actTogether(movielist: List[Movie], actorA: String, actorB: String) = movielist.filter(m => m.staring.contains(actorA) && m.staring.contains(actorB))

  println(actTogether(movies, "Kevin Spacey", "Morgan Freeman"))

  def ratingDirectorsMovie(movielist: List[Movie]) = movielist.groupBy(m => m.director).toList.map(x => x match {
    case (director, movies) => (director, movies.map(m => m.rating))
  })

  // ohne groupBy mit Map
  def ratingDirectorsMovie2(movielist: List[Movie]) = movielist.foldLeft(Map[String, List[Double]]())((acc, m) => acc.get(m.director) match {
    case Some(e) => acc + (m.director -> (m.rating :: e))
    case None => acc ++ Map(m.director -> List(m.rating))
  })

  println(ratingDirectorsMovie(movies))
  println(ratingDirectorsMovie2(movies))

  def moviesActorsStarIn(movielist: List[Movie]) = ??? // quasi das Gleiche wie die Aufgabe zuvor

  // def ActorsAtLeastInTwoMovies(movielist: List[Movie]): List[(String, List[Movie])] = movielist.foldLeft(List[(String, List[Movie])]())((acc, a) => )

/*  def actorsAtLeastInTwoMovies(movielist: List[Movie]) = movielist.map(m => m.staring.foldLeft(Map[String, List[Movie]]())((acc, c) => acc.get(c) match {
    case Some(e) => acc + (c -> (m :: e))
    case None => acc ++ Map(c -> List(m))
  }).groupBy(x => x._1))*/

  // def actorsAtLeastInTwoMovies(movielist: List[Movie]) = movielist.foldLeft(List[(String, List[String])]())((acc, a) => acc)

  def actorsAtLeastInTwoMovies2(movielist: List[Movie]) =
    movielist.flatMap(a => a.staring.map(b => (b, a))).groupBy(x => x._1).filter(s => s._2.size > 1).toList.map(d => d._1)

  def actorsAtLeastInTwoMovies(movielist: List[Movie]) =
    movielist.flatMap(a => a.staring.map(b => (b, a.title))).foldLeft(Map[String, List[String]]())((acc,o) => acc.get(o._1) match {
      case Some(e) => acc + (o._1 -> (o._2 :: e))
      case None => acc ++ Map(o._1 -> List(o._2))
    }).filter(x => x._2.size > 1)

  println(actorsAtLeastInTwoMovies(movies))

/*  def directorAtLeastTwoMoviesAtLeast150M(movielist: List[Movie]) =
    movielist.map(m => (m.director, m)).groupBy(d => d._1).toList.filter(r => r._2.size > 1)*/

  def directorAtLeastTwoMoviesAtLeast150M(movielist: List[Movie]) =
    movielist.groupBy(x => x.director).toList.filter(y => y._2.size > 1).filter(y => y._2.foldLeft(true)((acc, o) => if (o.grossInMillions.get < 150) false else acc))

  // (z => z._2.filter(zy => zy.grossInMillions.get > 150))

    // map(z => z._2.filter(zy => zy.grossInMillions.get > 150))

  println(directorAtLeastTwoMoviesAtLeast150M(movies))

  print("debug")

  /**
   * Solve the questions below once using for comprehensions and a second time using higher order
   * functions.
   *
   * 1. In which movies does Brad Pitt star?
   * 2. How many movies are shorter than 120 minutes?
   * 3. Do Morgan Freeman and Kevin Spacey star together in a movie?
   * 4. What ratings did a directors movies get?
   * 5. In which movies do actors star?
   * 6. Who are the actors, that stars in at least two movies?
   * 7. Who is the director, that has at least two movies that grossed at least 150M $ ?
   */
}