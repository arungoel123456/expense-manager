package com.example.arun.listview;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static Class add_content;
    ListView listview;
    ArrayList<Expense> expenses= new ArrayList<>();
    ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview= findViewById(R.id.listview1);


        for(int i=0; i<20; i++){
            Expense expense= new Expense("Expemnse " + i , i );
            expenses.add(expense);
        }


        

        //ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,expenses);
        adapter = new ExpenseAdapter(this , expenses);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);
        add_content();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add)
        {
            Intent intent = new Intent(this , Main2Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Expense expense = expenses.get(i);


        final int position = i;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setCancelable(false);
        builder.setMessage("Do you really want to delete " + expense.getExpense() + "?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Toast.makeText(MainActivity.this,"Ok Presses",Toast.LENGTH_LONG).show();
                expenses.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        //Toast.makeText(this,expense.getName() + " " + expense.getAmount(),Toast.LENGTH_LONG).show();
    }
    
    public void add_content()
    {
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            String name = intent.getStringExtra("ExpenseName");
            String cost = intent.getStringExtra("ExpenseCost");
            int convertedVal = Integer.parseInt(cost);
            Expense expense = new Expense(name, convertedVal);
            expenses.add(expense);
            adapter.notifyDataSetChanged();
        }
        
    }
}