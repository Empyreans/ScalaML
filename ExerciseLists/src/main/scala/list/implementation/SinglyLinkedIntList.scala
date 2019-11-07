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

  // TODO
  override def reverse: IntList = ???
/*  override def reverse: SinglyLinkedIntList = this match {
    case Cons(h, t) if (t == Empty) => this
    case Cons(h, t) => Cons(h, t.tail.reverse)
    case Empty => this
  }*/

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
    // case Cons(h, t) => reduceFunc(h, t.foldRight(initial)(reduceFunc))
    case Cons(h, t) => //TODO
    case Empty => initial
  }

  override def reduceRight(reduceFunc: (Int, Int) => Int): Int = this match {
    case Cons(h, t) => t.foldRight(h)(reduceFunc)
    // case Cons(h, t) => reduceFunc(h, t.foldRight(h)(reduceFunc))
    case Empty => throw new Exception("lol")
  }

  //TODO Prüfung ob sortiert
  override def insertSorted(elem: Int): IntList = this match {
    case Cons(h, t) => (elem < h) match {
      case true => Cons(elem, t)
      case false => Cons(h, t.insertSorted(elem))
  }
    case Empty => Cons(elem, Empty)
  }

  //TODO
  override def insertionSort: IntList = ???

  //TODO
  override def foldLeft[A](initial: A)(reduceFunc: (A, Int) => A): A = ???
}