package com.justinyan.errorsandpartialfunctions

import org.scalatest.{FlatSpec, Matchers}

class ExerciseTwoSpec extends FlatSpec with Matchers {

  "ExerciseTwo" should "handle Try properly" in {
    assert("Oops".equals(ExerciseTwo.tryExercise(100, 0, 0)))
    assert("Oops".equals(ExerciseTwo.tryExercise(100, 10, 0)))
    assert("2".equals(ExerciseTwo.tryExercise(100, 10, 10)))
    assert("1".equals(ExerciseTwo.tryExercise(100, 51, 2)))
  }

}


