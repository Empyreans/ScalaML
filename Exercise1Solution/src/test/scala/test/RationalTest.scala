package test

import exercise1.Rational
import org.scalatest.FunSuite

//see http://www.scalatest.org/at_a_glance/FunSuite
class RationalTest extends FunSuite {

  test("Rational Inititalisation 1") {
    val x = new Rational(1, 2)
    assert(x.value === 0.5)
  }

  test("Rational Inititalisation 2") {
    val x = new Rational(1, 2)
    assert(x.toString === "1/2")
  }

  test("Test requirement (denominator!=0)") {
    intercept[IllegalArgumentException] {
      new Rational(1, 0)
    }
  }

  test("Test add") {
    val x = new Rational(1, 2)
    val y = new Rational(2, 4)
    val res = x add y
    assert(res.toString === "8/8")
  }

  test("Test sub") {
    val x = new Rational(3, 4)
    val y = new Rational(1, 3)
    val res = x - y
    assert(res.toString === "5/12")
  }
  test("Test mul") {
    val x = new Rational(3, 4)
    val y = new Rational(1, 3)
    val res = x * y // same as x mul y
    assert(res.toString === "3/12")
  }

  test("Test neg") {
    val x = new Rational(3, 4)
    val res = x.neg()
    assert(res.toString === "-3/4")
  }

  test("Test gcd") {
    val x = new Rational(4, 8)
    val res = x.reduce()
    assert(res.toString === "1/2")
  }
}
