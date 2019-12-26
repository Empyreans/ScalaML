package trymonad

/**
 *
 * The MyTry type represents a computation that may either result in an exception, or return a successfully computed value.
 * Instances of MyTry[T], are either an instance of MySuccess[T] or MyFailure[T].
 * For example, MyTry can be used to perform division on a user-defined lyrics.txt,
 * without the need to do explicit exception-handling in all of the places that an exception might occur.
 *
 * An important property of MyTry is its ability to pipeline, or chain, operations, catching exceptions along the way
 */
abstract class MyTry[T] {
  /**
   * Applies the given function to the value of this MySuccess or returns this, if this is a MyFailure.
   *
   * @example MyTry(5).map(x => x * 6).map(x => x * 2) should return MyTry(60)
   *          MyTry(5).map(x => x * 6).map(x => x / 0) should return MyFailure(java.lang.ArithmeticException)
   *          MyTry(5).map(x => x / 0 ).map(x => x * 6) should return MyFailure(java.lang.ArithmeticException)
   */
  def map[U](f: T => U): MyTry[U]

  /**
   * Returns the given function applied to the value from this Success or returns this if this is a Failure.
   *
   * @example MyTry(5).flatMap(x => MyTry("b" + x)) should return MyTry("b5")
   *          MyTry(5).flatMap(x => MyTry("b" + 5)).flatMap(x => MyTry(x(1))) should return 5
   *          MyTry(5).flatMap(x => MyTry("b" + 5)).flatMap(x => MyTry(x(42))) should return MyFailure(java.lang.IndexOutOfBoundsException)
   *
   **/
  def flatMap[U](f: T => MyTry[U]): MyTry[U]

  /**
   * Returns true if the MyTry is a MySuccess, false otherwise.
   */
  def isSuccess: Boolean

  /**
   * Returns the value from this Success or throws the exception if this is a Failure.
   *
   * @example MyTry(5).get should return 5
   *          MyTry(throw new ArgumentException).get should throw an argument exception
   */
  def get: T
}

object MyTry {

  /**
   * Constructs a new try
   *
   * @example MyTry(5).get should return MySuccess(5)
   *          MyTry(throw new ArgumentException) should return MyFailure(java.lang.ArgumentException)
   */
  def apply[T](value: T): MyTry[T] = ???

}

case class MyFailure[T](e: Throwable) extends MyTry[T] {

  override def isSuccess: Boolean = ???

  override def get: T = ???

  override def map[U](f: T => U): MyTry[U] = ???

  override def flatMap[U](f: T => MyTry[U]): MyTry[U] = ???
}

case class MySuccess[T](value: T) extends MyTry[T] {
  override def map[U](f: T => U): MyTry[U] = ???

  override def flatMap[U](f: T => MyTry[U]): MyTry[U] = ???

  override def isSuccess: Boolean = ???

  override def get: T = ???
}