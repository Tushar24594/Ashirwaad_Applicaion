package in.tagglabs.ashirwad;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class secondAlgo extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    Button home;
    RelativeLayout second;
    CountDownTimer cTimer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_algo);
        home = findViewById(R.id.goHome1);
        second=findViewById(R.id.second);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), last.class);
                startActivity(intent);
                finish();
            }
        });
        startTimer();
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    void startTimer() {
        Log.e("start","Timer");
        cTimer = new CountDownTimer(20*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                Log.e("start","second");
            }
            public void onFinish() {
                Log.e("finish","second");
                Intent intent=new Intent(getApplicationContext(),last.class);
                        startActivity(intent);
                        secondAlgo.this.finish();
            }
        };
        cTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Destroy","second");
        if(cTimer!=null)
            cTimer.cancel();
    }
}
