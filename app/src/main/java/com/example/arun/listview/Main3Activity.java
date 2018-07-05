package com.example.arun.listview;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends Activity {

    EditText enteredExpense;
    EditText enteredCost;
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        String name = intent.getStringExtra("ExpenseName");
        String cost = intent.getStringExtra("ExpenseCost");
        enteredExpense=findViewById(R.id.enteredExpense);
        enteredCost=findViewById(R.id.enteredCost);
        enteredExpense.setText(name);
        position = intent.getStringExtra("position");
        enteredCost.setText(cost);

    }



    public void submit(View view)
    {
        String expenseName = enteredExpense.getText().toString();
        String expenseCost = enteredCost.getText().toString();

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("ExpenseName",expenseName);
        intent.putExtra("ExpenseCost",expenseCost);
        intent.putExtra("position",position);

//        startActivity(intent);
// ;
        setResult(4,intent);
        finish();
    }

}
