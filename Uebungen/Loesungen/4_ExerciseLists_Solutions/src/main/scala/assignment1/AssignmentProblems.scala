package assignment1

import list.implementation.SinglyLinkedIntList
import list.traits.IntList

object AssignmentProblems {
  /**
    * Implement Kadane's algorithm for maximum subarray sum using foldLeft.
    *
    * @see https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
    * @example kadane(SinglyLinkedIntList(-1,2,3,4,-6,-4,5,7,6)) should be 18 (5+7+6)
    */
  def kadane(input: IntList): Int = ???

  /**
    * Implement a function that yields(returns) every N-th even number in a given sequence using foldLeft.
    *
    * @example takeNthEven(SinglyLinkedIntList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15,18,5,3), 3)
    *          should be SinglyLinkedIntList(4, 10,18)
    */
  def takeNthEven(input: IntList, n: Int): IntList = ???

  /**
    * For each character in the string, compute it's corresponding ascii value
    * and return only the even values.
    *
    * @example encode("abcdef") should be IntList(98, 100, 102)
    * @note use map and filter
    */
  def encode(s: String): IntList = ???
}