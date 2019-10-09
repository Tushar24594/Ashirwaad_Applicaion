package in.tagglabs.ashirwad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class usersDetails extends AppCompatActivity {
    TextView nameText, genderText, ageText, heightText, weightText, yrsText, ftText, inchText, kgText, spin, v;
    EditText nameEdit, ageEdit, ftEdit, inchEdit, weightEdit, error;
    RadioGroup radioGroup;
    RadioButton radioButton, male, female;
    int SelectedID;
    Button submit, home;
    boolean doubleBackToExitPressedOnce = false;
    String userName, userGender, userAge, userHeightFT, userHeightINCH, userWieght;
    FileWriter fileWriter = null;
    File filename;
    private static final String FILE_HEADER = "Name,Gender,Age,Height(Ft),Height(Inch),Weight,Date";
    BufferedWriter bufferedWriter;
    public static final String TAG = "Users.csv";
    Spinner ageSpinner;
    String list[] = {"Select Age", "2-3 Years", "4-6 Years", "7-9 Years", "10-12 Years", "13-15 Years", "16-18 Years"};

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_users_details);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bubblegum.ttf");
        nameText = findViewById(R.id.nameText);
        nameText.setTypeface(typeface);
        error = findViewById(R.id.error);
        genderText = findViewById(R.id.genderText);
        genderText.setTypeface(typeface);
        ageText = findViewById(R.id.ageText);
        ageText.setTypeface(typeface);
        heightText = findViewById(R.id.heightText);
        heightText.setTypeface(typeface);
        weightText = findViewById(R.id.weightText);
        weightText.setTypeface(typeface);
        yrsText = findViewById(R.id.yrsText);
        yrsText.setTypeface(typeface);
        ftText = findViewById(R.id.ftText);
        ftText.setTypeface(typeface);
        inchText = findViewById(R.id.inchText);
        inchText.setTypeface(typeface);
        kgText = findViewById(R.id.kgText);
        kgText.setTypeface(typeface);
        nameEdit = findViewById(R.id.nameEditText);
        nameEdit.setTypeface(typeface);
        ageSpinner = findViewById(R.id.ageEditText);
        home = findViewById(R.id.goHome1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                spin = (TextView) super.getView(position, convertView, parent);
                spin.setTypeface(typeface);
                spin.setTextColor(Color.WHITE);
                spin.setTextSize(30);
                return spin;
            }

            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                v = (TextView) super.getView(position, convertView, parent);
                v.setTypeface(typeface);
                v.setTextColor(Color.WHITE);
                v.setTextSize(30);
                return v;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ageSpinner.setAdapter(arrayAdapter);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                error.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                error.setVisibility(View.VISIBLE);
            }
        });
        ftEdit = findViewById(R.id.ftEditText);
        ftEdit.setTypeface(typeface);
        inchEdit = findViewById(R.id.inchEditText);
        inchEdit.setTypeface(typeface);
        weightEdit = findViewById(R.id.weightEditText);
        weightEdit.setTypeface(typeface);
        radioGroup = findViewById(R.id.radio);
        submit = findViewById(R.id.submitData);
        submit.setTypeface(typeface);
        radioButton = findViewById(SelectedID);
        male = findViewById(R.id.male);
        male.setTypeface(typeface);
        female = findViewById(R.id.female);
        female.setTypeface(typeface);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(SelectedID);
                userName = nameEdit.getText().toString().trim();
                userAge = String.valueOf(ageSpinner.getSelectedItem());
                userGender = radioButton.getText().toString().trim();
                userHeightFT = ftEdit.getText().toString().trim();
                userHeightINCH = inchEdit.getText().toString().trim();
                userWieght = weightEdit.getText().toString().trim();
                if (userName.isEmpty()) {
                    nameEdit.setError("Please Enter Your Name");
                    nameEdit.requestFocus();
                    return;
                } else if (userGender.isEmpty()) {
                    radioButton.setError("Please Select Your Gender");
                    radioButton.requestFocus();
                    return;
                } else if (userAge.equals("Select Age")) {
                    Log.e("--Age--", userAge);
                    error.setVisibility(View.VISIBLE);
                    error.setError("Please Enter Your Age");
                    error.requestFocus();
                    return;
                } else if (userHeightFT.isEmpty()) {
                    ftEdit.setError("Please Enter Your Height");
                    ftEdit.requestFocus();
                    return;
                } else if (userHeightINCH.isEmpty()) {
                    inchEdit.setError("Please Enter Your Height");
                    inchEdit.requestFocus();
                    return;
                } else if (userWieght.isEmpty()) {
                    weightEdit.setError("Please Enter Your Weight");
                    weightEdit.requestFocus();
                    return;
                } else {

                    if (userName != null && !userName.isEmpty() && userGender != null && !userGender.isEmpty() && userAge != null && !userAge.isEmpty() && !userAge.equals("Select Age") && userHeightFT != null && !userHeightFT.isEmpty() && userHeightINCH != null && !userHeightINCH.isEmpty() && userWieght != null && !userWieght.isEmpty()) {
                        Log.e("Name :", userName);
                        Log.e("Gender :", userGender);
                        Log.e("Age :", userAge);
                        Log.e("Height FT :", userHeightFT);
                        Log.e("Height INCH :", userHeightINCH);
                        Log.e("Weight :", userWieght);

                        try {
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat mdformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
                            final String strDate = mdformat.format(calendar.getTime());
                            filename = new File(Environment.getExternalStorageDirectory() + "/Users.csv");
                            fileWriter = new FileWriter(filename.getAbsoluteFile(), true);
                            bufferedWriter = new BufferedWriter(fileWriter);
                            int i = (int) filename.length();
                            if (i != 0) {
                                bufferedWriter.write(userName + "," + userGender + "," + userAge + "," + userHeightFT + "," + userHeightINCH + "," + userWieght + "," + strDate + "\n");
                                Intent intent = new Intent(getApplicationContext(), nutrient.class);
                                intent.putExtra("gender", userGender);
                                intent.putExtra("age", userAge);
                                startActivity(intent);
                                finish();
                            } else if (i == 0) {
                                bufferedWriter.write(FILE_HEADER);
                                bufferedWriter.write("\n");
                                bufferedWriter.write(userName + "," + userGender + "," + userAge + "," + userHeightFT + "," + userHeightINCH + "," + userWieght + "," + strDate + "\n");
                                Intent intent = new Intent(getApplicationContext(), nutrient.class);
                                intent.putExtra("gender", userGender);
                                intent.putExtra("age", userAge);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Data Not Saved :" + e);
                            e.printStackTrace();
                        } finally {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e) {
                                Log.e(TAG, "Writer did'nt close:" + e);
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Log.e("--Else--", "Data not saved" + userAge);
                    }
                }
            }
        });
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
