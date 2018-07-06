package com.example.arun.listview;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static Class add_content;
    ListView listview;
    SqlititeOpenHelper openHelper;
    SQLiteDatabase database;
    ArrayList<Expense> expenses= new ArrayList<>();
    ExpenseAdapter adapter;

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        Intent intent = new Intent(this, MyReciever.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent,0);
//        long currentTime = System.currentTimeMillis();
//        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime+5000 , pendingIntent);





        listview= findViewById(R.id.listview1);
        openHelper = new SqlititeOpenHelper(this);

        database = openHelper.getWritableDatabase();
        Cursor cursor = database.query(SqlititeOpenHelper.table_name, null,null,null, null, null, null);
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("expense"));
            String cost = cursor.getString(cursor.getColumnIndex("cost"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            int id  = cursor.getInt(cursor.getColumnIndex("id"));

            long currentTime = System.currentTimeMillis();
            String hour= new String();
            String minutes= new String();
            int i=0;
            while(i<time.length() && time.charAt(i)!= ':')
            {
                hour=hour + time.charAt(i);
                i++;
            }
            i++;
            while(i<time.length())
            {
                minutes= minutes+ time.charAt(i);
                i++;
            }
            int expenseTime=0;
            expenseTime = Integer.parseInt(hour) * 3600*1000;
            expenseTime=expenseTime + Integer.parseInt(minutes)*60*1000;
            if(expenseTime == currentTime)
            {
                Intent intent = new Intent(this, MyReciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime+5000 , pendingIntent);
            }




            Expense expense= new Expense(name ,Integer.parseInt(cost) ,date , time );
            expense.setId(id);


            expenses.add(expense);
        }

//        for(int i=0; i<10; i++){
//            Expense expense= new Expense("Expemnse " + i , i );
//            expenses.add(expense);
//        }

        //ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,expenses);
        adapter = new ExpenseAdapter(this , expenses);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
//        add_content();

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
//            startActivity(intent);
            startActivityForResult(intent , 1);
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//        View item =
//        Intent intent = new Intent(this , Main2Activity.class);
//        intent.putExtra("name" , );
//
//        return true;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Intent intent = data;
        String name = intent.getStringExtra("ExpenseName");
        String cost = intent.getStringExtra("ExpenseCost");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        int convertedVal = Integer.parseInt(cost);
        String position = intent.getStringExtra("position");
        if(resultCode == 2)
        {

            if(intent.getExtras() != null) {

                Expense expense = new Expense(name, convertedVal,date, time);
                expenses.add(expense);
                adapter.notifyDataSetChanged();
                ContentValues cv = new ContentValues();
                cv.put("expense", name);
                cv.put("cost", convertedVal);
                database.insert(SqlititeOpenHelper.table_name,null,cv);
            }
        }

        if(resultCode==4)
        {
            Expense expense = new Expense(name, convertedVal, date, time);
            expenses.set(Integer.parseInt(position),expense);
        }



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
               Expense currExpense =  expenses.get(position);
                database.delete("expenses" ,"id = " + currExpense.getId() ,null );
                expenses.remove(position);
                adapter.notifyDataSetChanged();
                // new branch
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
    



    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Expense expense= expenses.get(i);
        Intent intent=new Intent(this ,Main3Activity.class);
        intent.putExtra("ExpenseName",expense.getExpense());
        intent.putExtra("ExpenseCost",expense.getCost()+"");
        intent.putExtra("position", i + "");
        startActivityForResult(intent,2);
        return true;
    }
}