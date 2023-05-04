package com.project.calculator;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    EditText result;
    Button one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.editTextNumber);
        one = findViewById(R.id.one);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result.setShowSoftInputOnFocus(false);
        }



    }
    

    private void updateText(String strToAdd){
        String oldStr = result.getText().toString();
        int cursorPos = result.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if(getString(R.string.result).equals(result.getText().toString())){
            result.setText((strToAdd));
            result.setSelection(cursorPos + 1);
        }
        else{
            result.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            result.setSelection(cursorPos + 1);
        }

    }
    public void zeroBtn(View view){
        updateText("0");
    }

    public void oneBtn(View view){
        updateText("1");
    }

    public void twoBtn(View view){
        updateText("2");
    }

    public void threeBtn(View view){
        updateText("3");
    }

    public void fourBtn(View view){
        updateText("4");
    }

    public void fiveBtn(View view){
        updateText("5");
    }

    public void sixBtn(View view){
        updateText("6");
    }

    public void sevenBtn(View view){
        updateText("7");
    }

    public void eightBtn(View view){
        updateText("8");
    }

    public void nineBtn(View view){
        updateText("9");
    }

    public void clearBtn(View view){
       result.setText(" ");
    }

    public void addBtn(View view){
        updateText("+");
    }

    public void minusBtn(View view){
        updateText("-");
    }

    public void multiplyBtn(View view){
        updateText("×");
    }

    public void divisionBtn(View view){
        updateText("÷");
    }

    public void moduloBtn(View view){
        updateText("%");
    }

    public void equalBtn(View view){

        String userExp = result.getText().toString();
        userExp = userExp.replaceAll("÷" , "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);
        String res = String.valueOf(exp.calculate());

        result.setText(res);
        result.setSelection(res.length());


    }

    public void dotBtn(View view){
        updateText(".");
    }

    public void backspaceBtn(View view){
        int cursorPos = result.getSelectionStart();
        int textLen = result.getText().length();
        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) result.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            result.setText(selection);
            result.setSelection(cursorPos - 1);
        }
    }

    public void OCParenthesis(View view){
        int cursorPos = result.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = result.getText().length();
        for(int i =0; i < cursorPos; i++){
            if(result.getText().toString().substring(i, i+1).equals("(")){
                openPar +=  1;

            }
            if(result.getText().toString().substring(i, i+1).equals(")")){
                closePar +=  1;

            }
        }
        if(openPar == closePar || result.getText().toString().substring(textLen - 1, textLen).equals("(")){
            updateText("(");
        }
        else if(closePar < openPar && !result.getText().toString().substring(textLen - 1, textLen).equals(")")){
            updateText(")");
        }
        result.setSelection(cursorPos + 1);

    }





}