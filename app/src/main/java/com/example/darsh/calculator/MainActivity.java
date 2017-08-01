package com.example.darsh.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1=null;

    private String pending ="=";
    public final String Operand = "operand1";
    public final String Pending = "pending";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        final Button buttonNegative = (Button) findViewById(R.id.negative);

        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonMinus  = (Button) findViewById(R.id.buttonMInus);
        Button buttonDevide = (Button) findViewById(R.id.buttonDevide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if(b.equals(buttonNegative))
                {
                    if(newNumber.getText().length() == 0)
                    newNumber.setText("-");

                }else
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);
        buttonNegative.setOnClickListener(listener);

        View.OnClickListener oplistner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try
                {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue,op);
                }catch (NumberFormatException e)
                {
                    newNumber.setText(" ");
                }
                pending=op;
                displayOperation.setText(op);

            }

        };
        buttonDevide.setOnClickListener(oplistner);
        buttonEqual.setOnClickListener(oplistner);
        buttonPlus.setOnClickListener(oplistner);
        buttonMinus.setOnClickListener(oplistner);
        buttonMultiply.setOnClickListener(oplistner);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(Pending,pending);
        if(operand1 != null)
        {
            outState.putDouble(Operand,operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pending = savedInstanceState.getString(Pending);
        operand1 = savedInstanceState.getDouble(Operand);
        displayOperation.setText(pending);
    }

    private void performOperation(Double value, String op)
    {
        if(operand1 == null)
        {
            operand1=value;
        }else {
            if(pending.equals("="))
                pending = op;

            switch (pending)
            {
                case "=":
                    operand1 = value;
                    break;

                case "/":
                    if(value == 0)
                        operand1=0.0;
                    else
                        operand1 /= value;
                    break;

                case "*":
                    operand1 *= value;
                    break;

                case "+":
                    operand1 += value;
                    break;

                case "-":
                    operand1 -= value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
