package edu.cvtc.ewackwitz.debuggingandtesting;

public class Calculator {

  public enum Operator {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION}

  public int addition(int numberOne, int numberTwo) {
    return numberOne + numberTwo;
  }

  public int subtraction(int numberOne, int numberTwo) {
    return numberOne - numberTwo;
  }

  public int multiplication(int numberOne, int numberTwo) {
    return numberOne * numberTwo;
  }

  public int division(int numberOne, int numberTwo) {
    return numberOne / numberTwo;
  }
}
