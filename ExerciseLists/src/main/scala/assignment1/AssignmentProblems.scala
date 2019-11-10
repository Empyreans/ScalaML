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
  def kadane(input: IntList): Int = input.foldLeft(Tuple2[Int, Int](0,0))((acc, r) => {
    val locMax = math.max(r, r + acc._2)

    if ( locMax > acc._1 ) (locMax, locMax)
    else                   (acc._2, locMax)
  })._1

  /**
    * Implement a function that yields(returns) every N-th even number in a given sequence using foldLeft.
    *
    * @example takeNthEven(SinglyLinkedIntList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15,18,5,3), 3)
    *          should be SinglyLinkedIntList(4, 10) (INSTEAD OF (4, 10, 18)
    */
  def takeNthEven(input: IntList, n: Int): IntList = input.foldLeft(SinglyLinkedIntList(): IntList, n-1)((acc, r) => {
    if (n <= 0) throw new Exception("get your index right dude, from 1 upwards")

    else if (acc._2 == 0 && (r % 2 == 0)) (acc._1.append(r), n-1)
    else                                  (acc._1,           acc._2-1)
  })._1

  /**
    * For each character in the string, compute it's corresponding ascii value
    * and return only the even values.
    *
    * @example encode("abcdef") should be SinglyLinkedIntList(98, 100, 102)
    * @note use map and filter
    */
  def encode(s: String): IntList = s.toList.map(x => x.toInt).filter(y => y % 2 == 0).foldLeft(SinglyLinkedIntList(): IntList)((acc, r) => acc.append(r))

}