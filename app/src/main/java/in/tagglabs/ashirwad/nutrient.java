package in.tagglabs.ashirwad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class nutrient extends AppCompatActivity {
    String age, gender;
    RelativeLayout relativeLayout;
    Button home, next;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nutrient);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bubblegum.ttf");
        age = getIntent().getStringExtra("age");
        gender = getIntent().getStringExtra("gender");
        home = findViewById(R.id.goHome);
        next = findViewById(R.id.next);
        next.setTypeface(typeface);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        relativeLayout = findViewById(R.id.relative);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), secondAlgo.class);
                startActivity(intent);
                finish();
            }
        });
//        Toast.makeText(this, "Age :" + age + " Sex :" + gender, Toast.LENGTH_SHORT).show();
//        if (age.equals("6-12 Months")) {
//            relativeLayout.setBackgroundResource(R.drawable.aa);
//        } else
        if (age.equals("2-3 Years")) {
            relativeLayout.setBackgroundResource(R.drawable.b);
        } else if (age.equals("4-6 Years")) {
            relativeLayout.setBackgroundResource(R.drawable.c);
        } else if (age.equals("7-9 Years")) {
            relativeLayout.setBackgroundResource(R.drawable.d);
        } else if (age.equals("10-12 Years") && gender.equals("Boy")) {
            relativeLayout.setBackgroundResource(R.drawable.eb);
        } else if (age.equals("10-12 Years") && gender.equals("Girl")) {
            relativeLayout.setBackgroundResource(R.drawable.eg);
        } else if (age.equals("13-15 Years") && gender.equals("Boy")) {
            relativeLayout.setBackgroundResource(R.drawable.fb);
        } else if (age.equals("13-15 Years") && gender.equals("Girl")) {
            relativeLayout.setBackgroundResource(R.drawable.fg);
        } else if (age.equals("16-18 Years") && gender.equals("Boy")) {
            relativeLayout.setBackgroundResource(R.drawable.gb);
        } else if (age.equals("16-18 Years") && gender.equals("Girl")) {
            relativeLayout.setBackgroundResource(R.drawable.gg);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.b);
        }
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
}
