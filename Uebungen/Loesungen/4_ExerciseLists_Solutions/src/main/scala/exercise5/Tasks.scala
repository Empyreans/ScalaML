package exercise5

import list.traits.IntList

/**
  * Complete the following exercises to practice the usage of higher order functions.
  */
object Tasks {

  /**
    * multiplyAndFilterEven should multiply all elements of the IntList by
    * the factor x and filter all element that are even
    */
  def multiplyAndFilterEven(l: IntList, x: Int): IntList = l.map(_ * x).filter(_ % 2 == 0)

  /**
    * findMin should find the smallest element of a list
    */
  def findMin(l: IntList): Int = l.foldLeft(Int.MaxValue) { (acc, c) => if (acc > c) c else acc }

  /**
    * sumOddNumbers should sum up all odd numbers of a list
    */
  def sumOddNumbers(l: IntList): Int = l.filter(_ % 2 != 0).foldLeft(0)(_ + _)

  /**
    * countEvenNumbers should count all even numbers of a list
    */
  def countEvenNumbers(l: IntList): Int = l.filter(_ % 2 == 0).map(_ => 1).foldLeft(0)(_ + _)
}