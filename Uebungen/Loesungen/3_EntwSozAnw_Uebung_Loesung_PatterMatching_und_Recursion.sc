/* ******************************************** *\
 *                                              *
 *                   Task 1                     *
 *                  Palindrome                  *
 *                                              *
 * ******************************************** */

/**
 * Returns true if the given string is a palindrome
 * (read the same way from left to right and right to left).
 *
 * @param s the string to check
 * @example level, Anna, kayak, radar
 **/
@scala.annotation.tailrec
//easiest solution: s == s.reverse
def isPalindrome(s: String): Boolean = {
  s match {
    case _ if s.length <= 1 => true
    case _ if s.head == s.last => isPalindrome(s.substring(1, s.length - 1))
    case _ => false
  }
}

/* ******************************************** *\
 *                                              *
 *                   Task 2                     *
 *             Balanced Parentheses             *
 *                                              *
 * ******************************************** */

/** Implement a function, that given a string of parenthesis
 * returns the difference between the opening and closing parentheses in that string.
 *
 * @example             "{} {{}}" =>  0    //none, i.e. is balanced
 *                      "{{{ }}"  =>  1    // 1 more opening than closing
 *                "{{}}}{ }}" => -2  // 2 less opening that closing
 */
def parenthesesDifference(s: String): Int = {
  @scala.annotation.tailrec
  def inner(s: String, value: Int): Int = {
    if (s.isEmpty)
      value
    else
      s.head match {
        case '{' => inner(s, value + 1)
        case '}' => inner(s, value - 1)
        case _ => throw new IllegalArgumentException()
      }
  }

  inner(s, 0)
}


/* ******************************************** *\
 *                                              *
 *                   Task 3                     *
 *                  Is Prime                    *
 *                                              *
 * ******************************************** */

/** Returns true if the given number is a prime number.
 *
 * @param x the number to check
 * @return true if prime
 */
def isPrime(x: Int): Boolean = {
  @scala.annotation.tailrec
  def calcPrim(x: Int, i: Int, max: Int): Boolean = {
    i match {
      case _ if i >= max => true
      case _ if x % i == 0 => false
      case _ => calcPrim(x, i + 1, max)
    }
  }

  calcPrim(x, 2, math.sqrt(x).toInt + 1)
}
/* ******************************************** *\
 *                                              *
 *                   Task 4                     *
 *            Goldbach's conjecture             *
 *                                              *
 * ******************************************** */

/**
 *
 * Goldbach's conjecture is one of the oldest and best-known unsolved problems in number theory and all of mathematics.
 * It states:
 * "Every even integer greater than 2 can be expressed as the sum of two primes."
 * The conjecture has been shown to hold for all integers less than 4 × 10**18,
 * but remains unproven despite considerable effort.
 * [[https://en.wikipedia.org/wiki/Goldbach%27s_conjecture Wikipedia]]
 */

/**
 * Returns the 2 prime pairs, that when added are equal to the provided number.
 *
 * @param evenX the number
 * @return 2 primes, which when summed equal to the number
 */
/*
* Note that this solution has a complexity of ϴ(n*n) and works as long as the first prime is not so big.
* For an actual solution( ϴ(n) ) one needs to store the prime numbers
* in an array/list and reuse them. See also [[https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes Sieve]], which is even better.
* */
def goldbach(evenX: Int): (Int, Int) = {
  require(evenX % 2 == 0)

  @scala.annotation.tailrec
  def inner(a: Int): (Int, Int) = {
    val b = evenX - a
    if (isPrime(a) && isPrime(b))
      (a, b)
    else
      inner(a + 2)
  }

  inner(1)
}
goldbach(Int.MaxValue - 1)

/* ******************************************** *\
 *                                              *
 *                   Task 5                     *
 *                 Prime Sum                    *
 *                                              *
 * ******************************************** */

/**
 * The sum of all prime numbers smaller than 10 is 2 + 3 + 5 + 7 = 17.
 * Write a function that computes the sum of all prime numbers smaller than 2 million.
 *
 * @note use the above function isPrime
 *       call [[primeSum]] recursively decrementing maxNumber at
 *       each step (using it as a running variable)
 **/

def primeSum(maxNumber: Int): Long = {
  @scala.annotation.tailrec
  def primeSum(maxNumber: Int, sum: Long): Long = {
    maxNumber match {
      case 2 => sum + 2
      case x => if (isPrime(x)) primeSum(maxNumber - 1, sum + x) else primeSum(maxNumber - 1, sum)
    }
  }

  primeSum(maxNumber, 0)
}
primeSum(2000000)

/* ******************************************** *\
 *                                              *
 *                   Task 6                     *
 *                  Palindrome                  *
 *                                              *
 * ******************************************** */

/**
 * 2520 is the smallest number, that can be divided by all numbers between 1 and 10 without remainder.
 * What is the smallest number, that can be divided by all numbers 1 to 20 without remainder?
 *
 * @note write a helper function, that checks if a given number is divisible by all numbers up to maxDivisor
 *       call this function recursively while incrementing a counter
 **/
def projectEulerFive(maxDivisor: Int): Int = {
  @scala.annotation.tailrec
  def isDivisible(x: Int, maxDivisor: Int): Boolean = {
    maxDivisor match {
      case 1 => true
      case _ => if (x % maxDivisor == 0) isDivisible(x, maxDivisor - 1) else false
    }
  }

  @scala.annotation.tailrec
  def projectEulerFive(x: Int, maxDivisor: Int): Int = {
    if (isDivisible(x, maxDivisor))
      x
    else
      projectEulerFive(x + 1, maxDivisor)
  }

  projectEulerFive(2, maxDivisor)
}

projectEulerFive(20)