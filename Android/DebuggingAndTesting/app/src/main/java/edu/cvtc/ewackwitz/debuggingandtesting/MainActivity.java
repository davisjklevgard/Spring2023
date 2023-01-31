package edu.cvtc.ewackwitz.debuggingandtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = ".MainActivity.DEBUGGING";
  private Calculator mCalculator;
  private EditText mEditTextNumberOne;
  private EditText mEditTextNumberTwo;
  private TextView mTextViewResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mCalculator = new Calculator();
    mEditTextNumberOne = findViewById(R.id.editText_numberOne);
    mEditTextNumberTwo = findViewById(R.id.editText_numberTwo);
    mTextViewResult = findViewById(R.id.textView_result);
  }

  public void onAddition(View view) {
    computeResult(Calculator.Operator.ADDITION);
  }

  public void onSubtraction(View view) {
    computeResult(Calculator.Operator.SUBTRACTION);
  }

  public void onMultiplication(View view) {
    computeResult(Calculator.Operator.MULTIPLICATION);
  }

  public void onDivision(View view) {
    try {
      computeResult(Calculator.Operator.DIVISION);
    } catch (ArithmeticException e) {
      Log.e(TAG, "ArithmeticException", e);
      mTextViewResult.setText("Error");
    }
  }

  private void computeResult(Calculator.Operator operator) {

    int numberOne, numberTwo;
    String result;

    try {
      numberOne = getNumber(mEditTextNumberOne);
      numberTwo = getNumber(mEditTextNumberTwo);
    } catch (NumberFormatException e) {
      Log.e(TAG, "NumberFormatException", e);
      mTextViewResult.setText("Error");
      return;
    }

    switch (operator) {
      case ADDITION:
        result = String.valueOf(mCalculator.addition(numberOne, numberTwo));
        break;

      case SUBTRACTION:
        result = String.valueOf(mCalculator.subtraction(numberOne, numberTwo));
        break;

      case MULTIPLICATION:
        result = String.valueOf(mCalculator.multiplication(numberOne, numberTwo));
        break;

      case DIVISION:
        result = String.valueOf(mCalculator.division(numberOne, numberTwo));
        break;

      default:
        result = "Error";
        break;
    }

    mTextViewResult.setText(result);
  }

  private static int getNumber(EditText editTextNumber) {
    return Integer.valueOf(getText(editTextNumber));
  }

  private static String getText(EditText editTextNumber) {
    return editTextNumber.getText().toString();
  }
}