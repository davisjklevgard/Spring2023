package edu.cvtc.dklevgard5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OddEvenResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odd_even_result);

        Intent in = this.getIntent();

        Integer value = in.getIntExtra(MainActivity.ODD_EVEN_MESSAGE, 0);

        String oddEven = value % 2 == 0 ? "Even" : "Odd";

        TextView textView = this.findViewById(R.id.textViewOddEvenResult);

        textView.setText("The number " + value.toString() + " is " + oddEven + "!");
    }
}