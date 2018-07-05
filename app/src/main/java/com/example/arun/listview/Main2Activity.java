package com.example.arun.listview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Main2Activity extends Activity implements DatePickerDialog.OnDateSetListener {

    EditText enteredExpense;
    EditText enteredCost;
    EditText showDate;
    EditText showTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        enteredExpense=findViewById(R.id.enteredExpense);
        enteredCost=findViewById(R.id.enteredCost);
        showDate=findViewById(R.id.showDate);
        showTime=findViewById(R.id.showTime);
    }

    public void submit(View view)
    {
        String expenseName = enteredExpense.getText().toString();
        String expenseCost = enteredCost.getText().toString();
        String date = showDate.getText().toString();
        String time = showTime.getText().toString();

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("ExpenseName",expenseName);
        intent.putExtra("ExpenseCost",expenseCost);
        intent.putExtra("date",date);
        intent.putExtra("time", time);

//        startActivity(intent);
// ;
        setResult(2,intent);
        finish();
    }

    public void setDate(View view)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, this, year, month, day);
        dialog.show();
    }


    public void setTime(View view)
    {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                showTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);

        mTimePicker.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        showDate.setText(i + "/" + i1 + "/" + i2);

    }
}
