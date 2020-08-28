package com.example.numbertester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    class tester
    {

        public int number;

        public boolean isTriangular(int x)
        {
            int next = 1;

            int triangularnumber = 1;

            while (triangularnumber<x)
            {
                next++;

                triangularnumber = triangularnumber + next;
            }

            if (x==triangularnumber)
                return true;
            else
                return false;
        }

        public boolean isSquare(int y)
        {
            double number2= Math.sqrt(y);

            if(number2==Math.floor(number2))
                return true;

            else
                return false;
        }

    }

    public void testNumber(View view) {

        Log.i("Info", "button pressed");

        EditText editText = (EditText) findViewById(R.id.editText);

        String message;

        if (editText.getText().toString().isEmpty()) {

            message = "Please enter a number";

        }
        else
            {

            tester myNumber = new tester();

            myNumber.number = Integer.parseInt(editText.getText().toString());

            message = editText.getText().toString();

            if (myNumber.isSquare(myNumber.number) && myNumber.isTriangular(myNumber.number)) {

                message += " is square and triangular!";

            } else if (myNumber.isSquare(myNumber.number)) {

                message += " is square but not triangular.";

            } else if (myNumber.isTriangular(myNumber.number)) {

                message += " is triangular but not square.";

            } else {

                message += " is neither triangular nor square.";

            }

        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

