package com.example.jaina.emergencyassist;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    RadioButton cBtn, dBtn;
    TextView mTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final alertMessage messenger = new alertMessage();
        SmsManager smsManager = SmsManager.getDefault();

        cBtn = (RadioButton)findViewById(R.id.cBtn);
        dBtn = (RadioButton)findViewById(R.id.dBtn);
        mTxt = (TextView)findViewById(R.id.mTxt);
        dBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTxt.setVisibility(View.INVISIBLE);
                    cBtn.setChecked(false);

                    //CODE HERE TO TAKE THE DEFAULT MESSAGE AND SET AS SMS MESSAGE

                }
            }
        });
        cBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTxt.setVisibility(View.VISIBLE);
                    dBtn.setChecked(false);

                    //CODE HERE TO TAKE EDIT TEXT INFO AND ADD IT TO MESSAGE
                    String mc = mTxt.getText().toString();
                    messenger.setMsg(mc);

                }
            }
        });



    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflates the menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handles menu options
        switch (item.getItemId()) {
            case R.id.setting:
                   Intent account = new Intent(MainActivity.this,Setting.class);
                startActivity(account);
               break;
            case R.id.add:
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}