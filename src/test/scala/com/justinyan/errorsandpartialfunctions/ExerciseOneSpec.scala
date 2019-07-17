package com.justinyan.errorsandpartialfunctions

import org.scalatest.{FlatSpec, Matchers}

class ExerciseOneSpec extends FlatSpec with Matchers {

  "ExerciseOne" should "handle options properly" in {
    assert("It was None".equals(ExerciseOne.destructureOption(Option.empty)))
    assert("teststring".equals(ExerciseOne.destructureOption(Option("teststring"))))
  }

}


