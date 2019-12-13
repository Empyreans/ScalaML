package list.implementation

import list.traits.IntList

case class Cons(head: Int, tail: IntList) extends SinglyLinkedIntList {
  override def isEmpty = false

  override def get(index: Int): Int = {
    if (index == 0) head
    else tail.get(index-1)
  }

  override def append(elem: Int): IntList = Cons(head ,tail.append(elem))

  override def contains(elem: Int): Boolean = {
    if (head == elem) true
    else tail.contains(elem)
  }

  override def prepend(elem: Int): IntList = Cons(elem, Cons(head, tail))

  override def delete(elem: Int): IntList = {
    if (elem == head) tail
    else Cons(head, tail.delete(elem))
  }

  // TODO besser verstehen
  override def deleteAll(elem: Int): IntList = {
    if (elem == head) tail.deleteAll(elem)
    else Cons(head, tail.deleteAll(elem))
  }

  // TODO besser verstehen
  override def prefix(other: IntList): IntList = {
    if (other != Empty) Cons(other.head, prefix(other.tail))
    else this
  }
}