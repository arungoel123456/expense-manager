package com.example.arun.listview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//
        //  Message work
//        Bundle data  = intent.getExtras();
//        String messageBody;
//        Object[] pdus = (Object[]) data.get("pdus");
//
//        for(int i=0;i<pdus.length;i++) {
//            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
//
//            String sender = smsMessage.getDisplayOriginatingAddress();
//            messageBody = smsMessage.getMessageBody();
//            Toast.makeText(context,messageBody ,Toast.LENGTH_LONG).show();
//
//        }
        //Toast.makeText(context,messageBody ,Toast.LENGTH_LONG).show();

        Toast toast = Toast.makeText(context, "hue", Toast.LENGTH_LONG);
        toast.show();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("mychannelid","first channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"mychannelid");
        builder.setContentTitle("new");
        builder.setContentText("recieved");
        builder.setSmallIcon(R.drawable.ic_launcher_background);

        Intent intent1= new Intent(context, MainActivity.class);

        PendingIntent pending = PendingIntent.getActivity(context,2, intent1,0);

        builder.setContentIntent(pending);
        Notification notification = builder.build();
        manager.notify(1, notification);

    }
}
