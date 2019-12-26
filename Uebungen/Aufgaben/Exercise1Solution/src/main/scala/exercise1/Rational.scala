package exercise1

class Rational(numerator: Int, denominator: Int) {

  def this(denom: Int) = this(1, denom)

  override def toString: String = numerator + "/" + denominator

  require(denominator != 0, "Denominator muss != 0 sein") // throws a IllegalArgumentException if the condition is not met
  println("Eine Rationale Zahl wurde erzeugt....") // is part of the constructor

  def num: Int = numerator // so the numerator is accessible from other classes
  def denom: Int = denominator // so the numerator is accessible from other classes
  def value: Double = num.toDouble / denom // converts to floating point number

  def max(other: Rational): Rational = {
    if (numerator / denominator < other.num / other.denom)
      this
    else
      other
  }

  /**
    * a   c   a*d + c*b
    * - + - = ---------
    * b   d     b * d
    */
  //Note, the parenthesis {} are optional and can be omitted when there is only one row
  def add(other: Rational): Rational = new Rational(num * other.denom + other.num * denom, denom * other.denom)

  /**
    * a   c     a * c
    * - * - = ---------
    * b   d     b * d
    */
  def mul(other: Rational): Rational = new Rational(num * other.num, denom * other.denom)


  /**
    * a   - a       a
    * - =  --- = - ---
    * b     b       b
    */
  def neg(): Rational = new Rational(-num, denom)

  /**
    * a   c   a*d - c*b
    * - - - = ---------
    * b   d     b * d
    */
  def sub(other: Rational): Rational = new Rational(num * other.denom - other.num * denom, denom * other.denom)

  def real: Double = num / denom

  /* Using symbolic definitions */
  def *(other: Rational): Rational = mul(other)

  def +(other: Rational): Rational = add(other)

  def -(other: Rational): Rational = sub(other)

  /* Computes the greatest common divisor of 2 numbers using the euclidean algorithm */
  def gcd(a: Int, b: Int): Int = {
    if (b == 0)
      a
    else
      gcd(b, a % b)
  }

  /* Fraction reduction using the greatest common divisor. */
  def reduce(): Rational = {
    val greatestDivisor = gcd(num, denom)
    new Rational(num / greatestDivisor, denom / greatestDivisor)
  }
}
