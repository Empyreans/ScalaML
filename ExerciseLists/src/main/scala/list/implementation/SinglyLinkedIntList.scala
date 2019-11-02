package list.implementation

import list.traits.IntList

/**
  * A companion object for the singly linked list.
  * This enables creating lists list this: val list = SinglyLinkedIntList(1,2,3)
  * which results in Cons(1,Cons(2,Cons(3,Empty))))
  */
object SinglyLinkedIntList {

  /** The apply function is a special function in scala.
    * It can be invoked with SinglyLinkedIntList.apply(args) or simply SinglyLinkedIntList(args).
    * This particular implementation of it is also a variadic function, i.e.
    * a function which accepts one or more arguments of the same type (integers) as parameters.
    */
  //inside this method xs is of type Seq[int]
  def apply(xs: Int*): SinglyLinkedIntList = xs match {
    case Seq() => Empty
    //: _* results in the sequence being passed as multiple parameters - (1,2,3) instead of Seq[Int]{1,2,3}
    case _ => Cons(xs.head, SinglyLinkedIntList(xs.tail: _*))
  }
}

abstract class SinglyLinkedIntList extends IntList {

  override def size: Int = ???

  override def reverse: SinglyLinkedIntList = ???

  /** ------------------------------------------
    *
    * Exercise 5
    *
    * ------------------------------------------ */

  override def map(mapFunc: Int => Int): IntList = ???

  override def filter(filterFunc: Int => Boolean): IntList = ???

  override def foldLeft(initial: Int)(reduceFunc: (Int, Int) => Int): Int = ???

  override def reduceLeft(reduceFunc: (Int, Int) => Int): Int = ???

  /** ------------------------------------------
    *
    * Assignment 1
    *
    * ------------------------------------------ */

  //TODO
  override def forAll(predicateFunc: Int => Boolean): Boolean = ???

  //TODO
  override def foldRight(initial: Int)(reduceFunc: (Int, Int) => Int): Int = ???

  //TODO
  override def reduceRight(reduceFunc: (Int, Int) => Int): Int = ???

  //TODO
  override def insertSorted(elem: Int): IntList = ???

  //TODO
  override def insertionSort: IntList = ???

  //TODO
  override def foldLeft[A](initial: A)(reduceFunc: (A, Int) => A): A = ???
}