package com.example.androidappdevelopment_task3_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tv_display);

        setNumericListeners();
        setOperatorListeners();

        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            expression.setLength(0);
            tvDisplay.setText("");
        });

        findViewById(R.id.btn_backspace).setOnClickListener(v -> {
            if (expression.length() > 0) {
                expression.deleteCharAt(expression.length() - 1);
                tvDisplay.setText(expression.toString());
            }
        });

        findViewById(R.id.btn_equal).setOnClickListener(v -> {
            calculateResult();
        });
    }

    private void setNumericListeners() {
        int[] numericButtons = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_dot
        };

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            expression.append(button.getText().toString());
            tvDisplay.setText(expression.toString());
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorListeners() {
        int[] operatorButtons = {
                R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide
        };

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String op = button.getText().toString();
            // Simple check to avoid double operators
            if (expression.length() > 0) {
                char lastChar = expression.charAt(expression.length() - 1);
                if (isOperator(lastChar)) {
                    expression.setLength(expression.length() - 1);
                }
                expression.append(op);
                tvDisplay.setText(expression.toString());
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '−' || c == '×' || c == '÷';
    }

    private void calculateResult() {
        String input = expression.toString();
        if (input.isEmpty()) return;

        try {
            double result = evaluate(input);
            String resultStr = formatResult(result);
            tvDisplay.setText(resultStr);
            expression.setLength(0);
            expression.append(resultStr);
        } catch (ArithmeticException e) {
            tvDisplay.setText("Error");
            expression.setLength(0);
        } catch (Exception e) {
            tvDisplay.setText("Error");
            expression.setLength(0);
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }

    // A simple Shunting-yard algorithm implementation for evaluation
    private double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (isOperator(tokens[i])) {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '×' || op1 == '÷') && (op2 == '+' || op2 == '−')) {
            return false;
        } else {
            return true;
        }
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '−':
                return a - b;
            case '×':
                return a * b;
            case '÷':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
        }
        return 0;
    }
}