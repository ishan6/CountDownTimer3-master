package com.example.countdowntimer;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private  int  seccounter = 00;
    private  int mincounter = 00;
    private  int milicounter = 00;
    boolean milicountertest = true;
    boolean checkstopornot = false;
    private static final String CURRENT_COUNTER = "seccounter";
    private static final String CURRENT_COUNTER1 = "mincounter";
    private static final String CURRENT_COUNTER2 = "milicounter";
    private static final String CHECKSTOPORNOT = "test";
 //   private boolean wasrunning = false;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            seccounter = savedInstanceState.getInt(CURRENT_COUNTER);
            mincounter = savedInstanceState.getInt(CURRENT_COUNTER1);
            milicounter = savedInstanceState.getInt(CURRENT_COUNTER2);
            checkstopornot = savedInstanceState.getBoolean(CHECKSTOPORNOT);
        }
            running = true;
            countDown();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(CURRENT_COUNTER,seccounter);
        savedInstanceState.putInt(CURRENT_COUNTER1,mincounter);
        savedInstanceState.putInt(CURRENT_COUNTER2,milicounter);
        savedInstanceState.putBoolean(CHECKSTOPORNOT,checkstopornot);
    }

    private void countDown() {
        final TextView textView = findViewById(R.id.textView3);
        final TextView textView2 = findViewById(R.id.textView4);
        final TextView textView3 = findViewById(R.id.textView);

        final Handler handler = new Handler();
     //   final Handler handler1 = new Handler();

        handler.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void run() {
                textView.setText(Integer.toString(seccounter));
                textView2.setText(Integer.toString(mincounter));
                textView3.setText(Integer.toString(milicounter));

                if(checkstopornot && running){
                    milicounter++;
                }

                handler.postDelayed(this, 100);
          //      handler1.postDelayed(this, milicounter, 100);

                if(milicounter == 10){
                    seccounter++;
                    milicounter = 0;
                }

                if(seccounter == 60){
                    seccounter = 00;
                    mincounter++;
                }
            }
        });
    }

    public  void countDownStop(View view){
        checkstopornot = false;
        Toast.makeText(this, " Timer Stoped!",Toast.LENGTH_LONG).show();
    }

    public void countDownStart(View view){
        checkstopornot = true;
        Toast.makeText(this, " Timer Started!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        running = true;
    }

    @Override
    public void onStop(){
        super.onStop();
    //   wasrunning = true;
        running = false;
    }
/*
    public void countdounStop(View view) {
        test = false;
    }

    public void countdounStart(View view) {
        test = true;
        int test;
    }
*/

}
