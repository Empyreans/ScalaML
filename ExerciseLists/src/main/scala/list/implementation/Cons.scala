package list.implementation

import list.traits.IntList

case class Cons(head: Int, tail: IntList) extends SinglyLinkedIntList {
  override def isEmpty = false

  override def get(index: Int): Int = {
    if (index != 0) get(index-1)
    else head
  }

  override def append(elem: Int): IntList = ???

  override def contains(elem: Int): Boolean = ???

  override def prepend(elem: Int): IntList = ???

  override def delete(elem: Int): IntList = ???

  override def deleteAll(elem: Int): IntList = ???

  override def prefix(other: IntList): IntList = ???
}