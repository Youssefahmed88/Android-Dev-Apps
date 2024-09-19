package com.example.calculatorproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private double firstNum;
    private String operation = ""; // Initialize 'operation' to avoid uninitialized errors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button del = findViewById(R.id.del);
        Button ac = findViewById(R.id.ac);
        Button div = findViewById(R.id.div);
        Button times = findViewById(R.id.times);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button dot = findViewById(R.id.dot);
        Button equal = findViewById(R.id.equal);

        TextView screen = findViewById(R.id.screen);

        off.setOnClickListener(v -> screen.setVisibility(View.GONE));

        on.setOnClickListener(v -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
        });

        ac.setOnClickListener(v -> {
            firstNum = 0;
            screen.setText("0");
        });

        ArrayList<Button> numButtons = new ArrayList<>();
        numButtons.add(num0);
        numButtons.add(num1);
        numButtons.add(num2);
        numButtons.add(num3);
        numButtons.add(num4);
        numButtons.add(num5);
        numButtons.add(num6);
        numButtons.add(num7);
        numButtons.add(num8);
        numButtons.add(num9);

        for (Button b : numButtons) {
            b.setOnClickListener(v -> {
                if (!screen.getText().toString().equals("0")) {
                    screen.setText(screen.getText().toString() + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> oprButtons = new ArrayList<>();
        oprButtons.add(div);
        oprButtons.add(times);
        oprButtons.add(plus);
        oprButtons.add(minus);

        for (Button b : oprButtons) {
            b.setOnClickListener(v -> {
                firstNum = Double.parseDouble(screen.getText().toString());
                operation = b.getText().toString();
                screen.setText("0");
            });
        }

        // delete button
        del.setOnClickListener(v -> {
            String number = screen.getText().toString();
            if (number.length() > 1) {
                screen.setText(number.substring(0, number.length() - 1));
            } else if (number.length() == 1 && !number.equals("0")) {
                screen.setText("0");
            }
        });

        // point button
        dot.setOnClickListener(v -> {
            if (!screen.getText().toString().contains(".")) {
                screen.setText(screen.getText().toString() + ".");
            }
        });

        // equal button
        equal.setOnClickListener(v -> {
            double secondNum = Double.parseDouble(screen.getText().toString());
            double result = 0;

            switch (operation) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "X":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
                default:
                    Toast.makeText(this, "Please choose an Operator", Toast.LENGTH_SHORT).show();
            }

            screen.setText(String.valueOf(result));
            firstNum = result;
        });
    }
}
