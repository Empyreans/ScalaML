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

  // Todo warum geht folgender Code nicht?
  // Muss ich Pattern Matching verwenden?
/*  override def size: Int = {
    if (tail == Empty) 0
    else 1 + tail.size
  }*/

  override def size: Int = this match {
    case Cons(h, t) => 1 + t.size
    case Empty => 0
  }

  // TODO besser verstehen
  override def reverse: IntList = {
    @scala.annotation.tailrec
    def reverse(oldList: IntList, newList: IntList): IntList = oldList match {
      case Empty => newList
      case Cons(head, tail) => reverse(tail, newList.prepend(head))
    }

    reverse(this, Empty)
  }


  /** ------------------------------------------
    *
    * Exercise 5
    *
    * ------------------------------------------ */

  override def map(mapFunc: Int => Int): IntList = this match {
    case Cons(h, t) => Cons(mapFunc(h), t.map(mapFunc))
    case Empty => Empty
  }

  override def filter(filterFunc: Int => Boolean): IntList = this match {
    case Cons(h, t) if filterFunc(h) => Cons(h, t.filter(filterFunc))
    case Cons(h, t) if !filterFunc(h) => t.filter(filterFunc)
    case Empty => Empty
  }

  // TODO angucken
  override def foldLeft(initial: Int)(reduceFunc: (Int, Int) => Int): Int = this match {
    case Cons(h, t) => t.foldLeft(reduceFunc(initial, h))(reduceFunc)
    case Empty => initial
  }

  override def foldLeft[A](initial: A)(reduceFunc: (A, Int) => A): A = this match {
    case Cons(h, t) => t.foldLeft(reduceFunc(initial, h))(reduceFunc)
    case Empty => initial
  }

  override def reduceLeft(reduceFunc: (Int, Int) => Int): Int = this match {
    // TODO warum falsch? : case Cons(h, t) => reduceFunc(h, t.reduceLeft(reduceFunc))
    case Cons(h, t) => t.foldLeft(h)(reduceFunc)
    case Empty => throw new Exception("lol")
  }
  /** ------------------------------------------
    *
    * Assignment 1
    *
    * ------------------------------------------ */

  //TODO bessere Lösung?
  override def forAll(predicateFunc: Int => Boolean): Boolean = this match {
    case Cons(h, t) => predicateFunc(h) match {
      case true => t.forAll(predicateFunc)
      case false => false
    }
    case Empty => true
  }

  // ich gebe "initial" bis ans Ende mit -> EBEN NICHT
  override def foldRight(initial: Int)(reduceFunc: (Int, Int) => Int): Int = this match {
    case Cons(h, t) => reduceFunc(initial, t.foldRight(h)(reduceFunc))
    case Empty => initial
  }

  override def reduceRight(reduceFunc: (Int, Int) => Int): Int = this match {
    case Cons(h, t) => t.foldRight(h)(reduceFunc)
    // case Cons(h, t) => reduceFunc(h, t.foldRight(h)(reduceFunc))
    case Empty => throw new Exception("lol")
  }

  override def insertSorted(elem: Int): IntList = this match {
    case Cons(h, t) => (elem < h) match {
      case true => Cons(elem, this) // Fehler war hier, dass ich (elem, t) zurückgegeben habe
      case false => Cons(h, t.insertSorted(elem))
  }
    case Empty => Cons(elem, Empty)
  }

  override def insertionSort: IntList = {
      def helper(oldList: IntList, newList: IntList): IntList = oldList match {
        case Cons(h, t) => helper(t, newList.insertSorted(h))
        case Empty => newList
      }

    helper(this, Empty)
  }

}