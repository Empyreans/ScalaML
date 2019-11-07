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
  def multiplyAndFilterEven(l: IntList, x: Int): IntList = l.map(y => y*x).filter(y => y%2 == 0)

  /**
    * findMin should find the smallest element of a list
    */
  // TODO warum braucht reduceLeft 1 statt 0 als Ausgabe?
  def findMin(l: IntList): Int = l.reduceLeft((x, y) => if (x < y) x else y)

  /**
    * sumOddNumbers should sum up all odd numbers of a list
    */
  def sumOddNumbers(l: IntList): Int = l.reduceLeft((x, y) => if (x % 2 == 0) x+y else y)

  /**
    * countEvenNumbers should count all even numbers of a list
    */
  // TODO hier aufgehört 06.11
  // def countEvenNumbers(l: IntList): Int = l.foldLeft(0)((x, y) => if (y % 2 == 0) x+1 else 0)
  def countEvenNumbers(l: IntList): Int = l.foldLeft(0)((x, y) => if (y % 2 == 0) x+1 else x)
}