package com.example.arun.listview;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Main2Activity extends Activity  {

    EditText enteredExpense;
    EditText enteredCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        enteredExpense=findViewById(R.id.enteredExpense);
        enteredCost=findViewById(R.id.enteredCost);
    }

    public void submit(View view)
    {
        String expenseName = enteredExpense.getText().toString();
        String expenseCost = enteredCost.getText().toString();

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("ExpenseName",expenseName);
        intent.putExtra("ExpenseCost",expenseCost);

        startActivity(intent);
    }


}
