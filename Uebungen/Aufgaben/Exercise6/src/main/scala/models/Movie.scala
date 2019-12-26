package models

/**
 *
 * @param title             The title of the movie
 * @param rating            The rating of the movie (0-10)
 * @param director          The director of the movie
 * @param staring           Popular actors acting in the movie
 * @param grossInMillions   Income in millions
 * @param genre             Genres of the movie
 * @param durationInMinutes Duration of the movie in Minutes
 * @param year              Year of release
 */
@SerialVersionUID(1L)
case class Movie(title: String,
                 rating: Double,
                 director: String,
                 staring: List[String],
                 grossInMillions: Option[Double],
                 genre: List[String],
                 durationInMinutes: Int,
                 year: Int)
