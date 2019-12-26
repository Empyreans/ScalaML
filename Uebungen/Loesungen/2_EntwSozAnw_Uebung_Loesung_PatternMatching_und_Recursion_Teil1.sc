/*
 *  ______                   _            ___
 * |  ____|                 (_)          |__ \
 * | |__  __  _____ _ __ ___ _ ___  ___     ) |
 * |  __| \ \/ / _ \ '__/ __| / __|/ _ \   / /
 * | |____ >  <  __/ | | (__| \__ \  __/  / /_
 * |______/_/\_\___|_|  \___|_|___/\___| |____|
 *
 * Goals: Introduction to Pattern Matching and recursion.
 *
 * Use only elements of functional programming to solve the tasks below
 *  (i.e. here using recursion and only immutable variables (val) )
 */

/* ******************************************** *\
 *                                              *
 *                   Task 1                     *
 *            Digit Sum and EAP-13              *
 *                                              *
 * ******************************************** */
/**
 * Computes the digit sum of a given number from right to left.
 *
 * @note use pattern matching
 * @param x the number
 * @return the digit sum.
 * @example digitSum(10532) = 1 + (0 + (5 + (3 + 2)))) = 11
 */
def digitSum(x: Int): Int = x match {
  case 0 => 0
  case x => x % 10 + digitSum(x / 10)
}
digitSum(10532)

/**
 * Implement the above in a tail recursive way.
 */

def digitSum2(x: Int): Int = {
  @scala.annotation.tailrec
  def helper(x: Int, c: Int): Int = x match {
    case 0 => c
    case x => helper(x / 10, c + x % 10)
  }

  helper(x, 0)
}

digitSum2(10532)
/**
 * Computes the digit sum of digits in even positions (assuming zero index start)
 * in a given number starting from right to left.
 *
 * @param x the number
 * @return the digit sum of even positions.
 * @example digitSumEven(10532) = 1 + (5 + 2) = 8
 */
def digitSumEven(x: Long): Long = x match {
  case 0 => 0
  case x => x % 10 + digitSumEven(x / 100)
}

digitSumEven(10532)

/**
 * Computes the digit sum of digits in odd positions (assuming zero index start)
 * in a given number starting from right to left.
 *
 * @note implement this in terms of/using [[digitSumEven]]
 * @param x the number
 * @return the digit sum of even positions.
 * @example digitSumOdd(10532) = 0 + 3 = 3
 */
def digitSumOdd(x: Long): Long = digitSumEven(x / 10)

digitSumOdd(10532)

/**
 * Computes the checksum of a given EAN-13 barcode (12 digits).
 * [[https://en.wikipedia.org/wiki/International_Article_Number EAN-13 Wikipedia]]
 *
 * The EAN-13 Checksum is computed in the following way:
 * 1. Compute the odd digit sum of the barcode
 * 2. Multiply it by 3
 * 3. Compute the even digit sum of the barcode
 * 4. Add 2) and 3)
 * 5. take 4) % 10 to obtain m
 * 6. if m is equal to 0, return m, otherwise return 10 - m
 *
 * @note Use [[digitSumEven]] and [[digitSumOdd)]] to implement this.
 *       You can assume that no barcodes starting with a leading zero will be given.
 *       Check your implementation with some things with barcodes, that you have on you
 *       or take one from me
 * @example EAN_13(901234123457) = 5
 *          1. 9 + 1 + 3 + 1 + 3 + 5 = 22
 *          2. 22 * 3 = 66
 *          3. 0 + 2 + 4 + 2 + 4 + 7 = 19
 *          4. 66 + 19 = 85
 *          5. m = 85 % 10 = 5
 *          6. 10 - m = 5
 */
def EAN_13(x: Long): Long = (10 - (digitSumOdd(x) * 3 + digitSumEven(x)) % 10) % 10
EAN_13(901234123457L)

/* ******************************************** *\
 *                                              *
 *                   Task 2                     *
 *            Fibonacci-sequence                *
 *                                              *
 * ******************************************** */
/**
 * Computes the n-th number in the Fibonacci-sequence.
 * In the fibonacci-sequence, each number is defined as the sum of
 * it's predecessors: f_n = f_{n-1} + f_{n-2} and f_0 = 0 and f_1 = 1
 *
 * @see [[https://en.wikipedia.org/wiki/Fibonacci_number Fibonacci-sequence]]
 * @note use pattern matching and recursion
 * @param n n-th Fibonacci number
 * @example fibo(6) = ?
 *          fibo(2) = fibo(0) + fibo(1) = 0 + 1 = 1
 *          fibo(3) = fibo(2) + fibo(1) = 1 + 1 = 2
 *          fibo(4) = fibo(3) + fibo(2) = 2 + 1 = 3
 *          fibo(5) = fibo(4) + fibo(3) = 3 + 2 = 5
 *          fibo(6) = fibo(5) + fibo(4) = 5 + 3 = 8
 */
def fibo(n: Int): Int = {
  n match {
    case 0 => 0
    case 1 => 1
    case x => fibo(x - 2) + fibo(x - 1)
  }
}

fibo(3)
/**
 * Modify fibo2, so fibo2(100) leads to the correct answer
 */
def fibo2(n: Int): Long = {
  @scala.annotation.tailrec
  def helper(f_min_1: Long, f_min_2: Long, i: Int): Long =
    if (i == 0)
      f_min_2
    else
      helper(f_min_1 + f_min_2, f_min_1, i - 1)

  helper(1, 0, n)
}
fibo2(100)

//quick verification, that they are indeed the same
//assert((1 to 12).forall(x => fibo(x) == fibo2(x)))