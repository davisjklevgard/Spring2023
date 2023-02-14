package edu.cvtc.dklevgard5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Year;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Button calculateButton = findViewById(R.id.button_Calculate);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText currentAgeText = ((View)view.getParent()).findViewById(R.id.editText_CurrentAge);

                EditText retireAgeText = ((View)view.getParent()).findViewById(R.id.editText_Retire);

                try {
                    Integer currentAge = Integer.parseInt(currentAgeText.getText().toString());
                    Integer retireAge = Integer.parseInt(retireAgeText.getText().toString());

                    Integer yearsLeft = retireAge - currentAge;
                    Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    Integer retireYear = yearsLeft + currentYear;

                    Toast.makeText(view.getContext(), "You have " + yearsLeft + " years until you can " +
                            "retire. It's " + currentYear + ", so you can retire in " + retireYear, Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(view.getContext(), "Please enter in an integer", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}