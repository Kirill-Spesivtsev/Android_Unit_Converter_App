package com.example.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private Spinner spinnerFrom, spinnerTo;
    private EditText enterValue;
    private TextView resultValue;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UiSetup();
    }

    public void UiSetup()
    {
        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);
        enterValue = findViewById(R.id.enter_value);
        resultValue = findViewById(R.id.result_value);

        String[] spinnerArray = new String[]{"Километры", "Метры", "Сантиметры","Миллиметры"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,spinnerArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter1);
        spinnerTo.setAdapter(adapter1);
        spinnerTo.setSelection(1);
        enterValue.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Calculate();
            }
        });
        spinnerFrom.setOnItemSelectedListener(selector);
        spinnerTo.setOnItemSelectedListener(selector);
    }

    private AdapterView.OnItemSelectedListener selector = new AdapterView.OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            Calculate();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    };


    public void Calculate()
    {
        int fromIndex = spinnerFrom.getSelectedItemPosition();
        int toIndex = spinnerTo.getSelectedItemPosition();
        String text = enterValue.getText().toString();
        double initValue = !text.equals("") ? Double.parseDouble(text) : 0.0 ;
        double factor = 1;

        if (fromIndex != toIndex)
        {
            if(fromIndex==0 && toIndex==1)
                factor=1000;
        }
        else
        {

        }
        double result = initValue * factor;
        resultValue.setText(Double.toString(result));
    }

}