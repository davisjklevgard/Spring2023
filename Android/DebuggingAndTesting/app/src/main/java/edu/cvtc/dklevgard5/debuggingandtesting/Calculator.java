package edu.cvtc.dklevgard5.debuggingandtesting;

public class Calculator {

  public enum Operator {ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION}

  public double addition (double numberOne, double numberTwo) {
    return numberOne + numberTwo;
  }

  public double subtraction(double numberOne, double numberTwo) {
    return numberOne - numberTwo;
  }

  public double multiplication(double numberOne, double numberTwo) {
    return numberOne * numberTwo;
  }

  public double division(double numberOne, double numberTwo) {
    return numberOne / numberTwo;
  }
}
