package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;
    private CheckBox cbUserid;
    private boolean rememberUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        String userid = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("PREF_USERID", "");
        edUserid.setText(userid);
        cbUserid.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Log.d("CBOX", isChecked+"");
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putBoolean("PREF_REMEMBER_USERID", isChecked)
                        .commit();
            }
        });
        rememberUserid = getSharedPreferences("atm", MODE_PRIVATE)
                .getBoolean("PREF_REMEMBER_USERID", false);
        cbUserid.setChecked(rememberUserid);

    }

    private void findViews() {
        edUserid = (EditText) findViewById(R.id.userid);
        edPasswd = (EditText) findViewById(R.id.passwd);
        cbUserid = (CheckBox) findViewById(R.id.remember_userid);
    }

    public void login(View v){
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        if (userid.equals("jack") && passwd.equals("1234")){
            if (rememberUserid) {
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putString("PREF_USERID", userid)
                        .commit();
            }
            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("USERID", userid);
            intent.putExtra("PASSWD", passwd);
            setResult(RESULT_OK, intent);

            finish();
        }

    }
}
