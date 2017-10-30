package com.example.sj.addit;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    EditText firstNumber;
    EditText secondNumber;
    public static TextView addResult;
    Button btnAdd;
    NotificationCompat.Builder mBuilder;
    NotificationManager manager;

    int num1,num2,sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNumber = (EditText)findViewById(R.id.txtNumber1);
        secondNumber = (EditText)findViewById(R.id.txtNumber2);
        addResult = (TextView)findViewById(R.id.txtResult);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                addIt(v);
            }
        });
    }

    public void addIt(View v){
        if(!firstNumber.getText().toString().equals("") && !secondNumber.getText().toString().equals("")){
            num1 = parseInt(firstNumber.getText().toString());
            num2 = parseInt(secondNumber.getText().toString());
            sum = num1 + num2;
            addResult.setText(Integer.toString(sum));
        }else{
            mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.drawable.logo);
            mBuilder.setContentTitle("Please enter a value!").setAutoCancel(true);;
            mBuilder.setContentText("Enter some values in the fields");
            Intent resultIntent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(contentIntent);
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, mBuilder.build());
            Toast.makeText(getApplicationContext(), "Please enter a value", Toast.LENGTH_SHORT).show();
        }
    }

}

