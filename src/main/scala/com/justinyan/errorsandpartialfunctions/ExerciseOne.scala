package com.justinyan.errorsandpartialfunctions

/**
  * This exercise is intended to familiarize you with the idea of Pattern Matching.
  */
object ExerciseOne {

  /**
    * Run `sbt ~console` on your command line.
    *   import com.justinyan.errorsandpartialfunctions.ExerciseOne
    *   ExerciseOne.exampleSwitch(1)
    *   ExerciseOne.exampleSwitch(4)
    */
  def exampleSwitch(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  /**
    * This is not that interesting, it does nothing more than a basic switch statement.  Let's amp it up a bit.
    *   Any is Scala's catchall type, meaning literally anything you pass in will typecheck.
    *   More importantly, we can bind a *variable on the left* that we can then use on the *right*.
    *
    *   Try replacing the `???` on the right with `tx.`, and then press ctrl+space to see what methods you can autocomplete.
    *   For which cases does `tx + 1` on the right side compile?
    */
  def exampleTypeConstraint(x: Any): Int = x match {
    case tx: String => tx.length
    case tx: Int => ???
    case tx => ???
  }

  /**
    * There are lots of really fun things you can do with pattern matching that go beyond the scope of this workshop.
    * We're going to cover one more that will illustrate an important concept for us.
    *
    * To assist us here, I've created a small class hierarchy at the bottom of this file - don't worry too much about the keywords.
    *
    * When you match on a value of a particular type, you can specifically match to its subtypes and then *destructure* them
    *   by binding a variable to one of the attributes.
    *
    * You'll even be prevented from adding nonsensical cases - try adding `case tx: String => ???` and see what the compiler says.
    *
    * One of the reasons this kind of pattern-match destructuring is so useful is that it gives you a sensible way for
    *   dealing with Sum Types - in this case, a value `x: Base` could be *either* SubOne *or* SubTwo.  However, `Base` itself
    *   has no actual values or methods on it - indeed, if you add a case `case tx => tx.` - you'll see that auto-complete gives
    *   you virtually nothing.
    *
    * Explicit Sum Types, of course, are extremely useful because they are a *principled way* to represent errors - unlike
    *   Java where every single type is implicitly a Sum Type with `null` (an Integer could be an Integer *or* null), an explicit
    *   Sum Type `Integer | null` will have the compiler *error* if you attempt to access any methods of an Integer (such as `+`)
    *   unless you first *destructure* => when used with Errors, this is extremely powerful because it *forces* people to handle
    *   the error branch since they can't even get to their "real" value without first destructuring.
    */
  def exampleDestructuring(x: Base): Int = x match {
    case SubOne(tx) => tx.length  // this is myval
    case SubTwo(tx) => tx         // this is yourval
  }

  /**
    * Now it's your turn.  Scala's Option[A] destructures to Some(A) or None.  Use a pattern match to give me the output
    *   of the protected string or otherwise return "It was None".
    *
    * Run the following to test:
    * sbt "testOnly **ExerciseOne**"
    */
  def destructureOption(x: Option[String]): String = ???

}

sealed trait Base
case class SubOne(myval: String) extends Base
case class SubTwo(yourval: Int) extends Base
