/* ******************************************** *\
 *                                              *
 *                   Task 1                     *
 *                  Palindrome                  *
 *                                              *
 * ******************************************** */

/**
 * Returns true if the given string is a palindrome
 * (read the same way from left to right and right to left ).
 *
 * @param s the string to check
 * @example level, anna, kayak, radar
 **/
def isPalindrome(s: String): Boolean = ???

/* ******************************************** *\
 *                                              *
 *                   Task 2                     *
 *             Balanced Parentheses             *
 *                                              *
 * ******************************************** */

/**
 *  Implement a function, that given a string of parenthesis
 * returns the difference between the opening and closing parentheses in that string.
 *
 * @example       "{} {{}}" =>  0    //none, i.e. is balanced
 *                "{{{ }}"  =>  1    // 1 more opening than closing
 *                "{{}}}{ }}" => -2  // 2 less opening that closing
 */
def parenthesesDifference(s: String): Int = ???

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
def isPrime(x: Int): Boolean = ???




/* ******************************************** *\
 *                                              *
 *                   Task 4                     *
 *            Goldbach's conjecture             *
 *                                              *
 * ******************************************** */

/**
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
def goldbach(evenX: Int): (Int, Int) = ???

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

def primeSum(maxNumber: Int): Long = ???

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
def projectEulerFive(maxDivisor: Int): Int = ???