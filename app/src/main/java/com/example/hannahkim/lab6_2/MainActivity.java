package com.example.hannahkim.lab6_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText studentNum, studentName;
    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;
    Button callBtn, saveBtn, clearBtn;
    String userNum, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentNum = (EditText)findViewById(R.id.sNum);
        studentName= (EditText)findViewById(R.id.sName);
        callBtn = (Button)findViewById(R.id.callBtn);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        clearBtn = (Button)findViewById(R.id.clearBtn);

        saveBtn.setOnClickListener(new Button.OnClickListener() { //save data
            public void onClick(View view) {
                userNum = studentNum.getText().toString();
                userName = studentName.getText().toString();

                sharedPreferences();
            }
        });

        clearBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                studentNum.setText("");
                studentName.setText("");
            }
        });

        callBtn.setOnClickListener(new Button.OnClickListener() { //bring data
            public void onClick(View view) {
                applySharedPreference();
            }
        });
    }

    public void sharedPreferences() { //save data
        sh_Pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        toEdit.putString("UserNum", userNum);
        toEdit.putString("UserName", userName);
        toEdit.commit();
    }

    public void applySharedPreference() { //call data
        sh_Pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        if(sh_Pref != null && sh_Pref.contains("UserNum") && sh_Pref.contains("UserName")) {
            String num = sh_Pref.getString("UserNum", "noNum");
            studentNum.setText(num);

            String name = sh_Pref.getString("UserName", "noName");
            studentName.setText(name);
        }
    }

}