package exercise5

import list.implementation.SinglyLinkedIntList
import org.scalatest.FunSuite

class TasksTest extends FunSuite {

  test("testSumOddNumbers") {
    assert(Tasks.sumOddNumbers(SinglyLinkedIntList(1, 2, 3, 4, 5)) === 9)
  }

  test("testCountEvenNumbers") {
    assert(Tasks.countEvenNumbers(SinglyLinkedIntList(1, 2, 3, 4, 5)) === 2)
  }

  test("testMultiplyAndFilterEven") {
    assert(Tasks.multiplyAndFilterEven(SinglyLinkedIntList(1, 2, 3), 3) === SinglyLinkedIntList(6))
  }

  test("testFindMin") {
    assert(Tasks.findMin(SinglyLinkedIntList(3, 5, 1, 2, 3, 4, 5)) === 1)
  }

  test("testAverage") {
    assert(Tasks.average(SinglyLinkedIntList(1, 2, 3, 4)) === 2)
  }
}
