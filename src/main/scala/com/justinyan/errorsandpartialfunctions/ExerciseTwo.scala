package com.justinyan.errorsandpartialfunctions

import scala.util.{Failure, Success, Try}


/**
  * This exercise is intended to familiarize you with how to use Scala's Try Monad.
  * Try is extremely similar to Option, and largely the same core API, so we'll leave it as a further exercise to learn
  *   how to use Option effectively.
  *
  * Try[A] destructures to Success(v: A) and Failure(t: Throwable)
  */
object ExerciseTwo {

  /**
    * The core idea is that if something might fail, you can wrap it in a Try and deal with Exceptions as *values*.
    * Run `sbt ~console` on your command line.
    *   import com.justinyan.errorsandpartialfunctions.ExerciseTwo
    *   ExerciseTwo.exampleDivide(1)    // 100
    *   ExerciseTwo.exampleDivide(0)    // Throws an ArithmeticException
    *   ExerciseTwo.exampleDivide2(1)   // 100
    *   ExerciseTwo.exampleDivide2(0)   // 240842084
    *
    * The core idea here is that you can evaluate an expression, and then *capture* any exceptions into a container that
    *   the compiler will then force someone to destructure, forcing them to handle the failure branch as well.
    */
  def exampleDivide(x: Int): Int = 100 / x
  def exampleDivide2(x: Int): Int = Try(100 / x) match {
    case Success(x) => x
    case Failure(_) => 240842084
  }

  /**
    * While pretty, this is just a rigor and syntactic improvement over `try/catch` clauses.  The real power lies in the
    *   utility methods available to us.  The tendency for those with imperative backgrounds is to use `isSuccess` and `isFailure`
    *   as part of if/else statements and thereby construct janky versions of try/catch clauses.
    *
    * There are better ways to do things.  Consider `getOrElse` which allows you to set a default in the event of failure.
    */
  def exampleGetOrElse(x: Int): Int = Try(100 / x).getOrElse(240842084) // Equivalent to exampleDivide2

  /**
    * Consider `map`, which applies the provided function to the contained element given it is `Success`.
    *   If you're having trouble wrapping your head around this, think of Success ~ List and Failure ~ Empty List
    */
  def exampleMap(x: Int): Int = Try(100 / x).map(x => x + 2).getOrElse(240842084)

  /**
    * Consider `flatMap`, which allows you to apply a function that returns a Try to your Success value.
    *   The idea here is to end up with a Try[Int] instead of a Try[Try[Int]] after the second function.
    */
  def exampleFlatMap(x: Int, y: Int): Int = Try(100 / x).flatMap(x => Try(x / y)).getOrElse(240842084)


  /**
    * Now it's your turn again.  Using the unsafeDivide function I've provided below, Try, and the above methods,
    *
    * I want you to:
    *
    * 1. unsafe divide
    * 2. double the result
    * 3. unsafe divide again
    * 4. convert the result to a string
    * 5. return that string or "Oops" if there was a failure at any point
    *
    * Run the following to test:
    * sbt "testOnly **ExerciseTwo**"
    */
  def tryExercise(num: Int, denom: Int, denom2: Int): String = ???
  def unsafeDivide(num: Int, denom: Int): Int = num / denom

  /**
    * When you compare this implementation to what it would be like for `try/catch`, you get a big win in conciseness, expressivity, and compositionality.
    *
    * This happens for two main reasons:
    *
    * - The first is that `map` and `getOrElse` play extremely well together.
    *   The values you would like to *default* to in the event of an error often don't make any sense in the context of the intermediate processing you would like to do in the event of a *success*.
    *   By allowing yourself to safely manipulate values while *carrying* the error forward to be handled later, you allow error handling to live closer to the end result.
    * - The second is that we often want to perform multiple unsafe operations, where the later actions operations on the earlier operations.
    *   In these situations, there typically isn't a default for the first operation that will result in correct overall behavior when input into the later operations, which makes nested try/catches fairly complex.
    *   On top of this error handling in a sequence of unsafe operations is often to *get the first failure* and return it, which means that `Failure` is *flattenable* (a key aspect of being monadic)
    */

}
