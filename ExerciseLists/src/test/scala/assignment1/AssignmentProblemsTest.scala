package assignment1

import list.implementation.SinglyLinkedIntList
import org.scalatest.FunSuite

class AssignmentProblemsTest extends FunSuite {
  test("testKadane") {
    assert(AssignmentProblems.kadane(SinglyLinkedIntList(-1,2,3,4,-6,-4,5,7,6)) === 18)
  }

  test("testTakeNthEven") {
    assert(AssignmentProblems.takeNthEven(SinglyLinkedIntList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15,18,5,3), 3) === SinglyLinkedIntList(4, 10))
  }

  test("testEncode") {
    assert(AssignmentProblems.encode("abcdef") === SinglyLinkedIntList(98, 100, 102))
  }

}
