package com.example.jaina.emergencyassist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.annotation.SuppressLint;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.content.pm.PackageManager;
import android.widget.Toast;
import java.security.Policy;

/**
 * Created by Jaina on 11/15/2017.
 */

public class Setting extends AppCompatActivity {
       Button goToContacts;

    private Button btnSwitch;

    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    private Parameters params;
    private MediaPlayer mp;

        @Override
        protected  void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.settings);

            goToContacts = (Button) findViewById(R.id.contactsBtn);




        // INCLUDE THE CORRECT NAME FOR addContacts class
           /* goToContacts.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent myIntent = new Intent(Setting.this, AddContacts.class);
                    Setting.this.startActivity(myIntent);
                }
            });*/



            // flash switch button
            btnSwitch = (Button) findViewById(R.id.btnSwitch);

            if (!hasFlash) {
                // device doesn't support flash
                // Show alert message and close the application
                AlertDialog alert = new AlertDialog.Builder(Setting.this)
                        .create();
                alert.setTitle("Error");
                alert.setMessage("Sorry, your device doesn't support flash light!");

                return;
            }

            // get the camera
            getCamera();

        /*
		 * Switch button click event to toggle flash on/off
		 */
            btnSwitch.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (isFlashOn) {
                        Toast.makeText(getApplicationContext(),"Flash Off",Toast.LENGTH_LONG);
                        // turn off flash
                        turnOffFlash();

                    } else {
                        Toast.makeText(getApplicationContext(),"Flash On",Toast.LENGTH_LONG);
                        // turn on flash
                        turnOnFlash();


                    }
                }
            });
        }
    /*
         * Get the camera
         */
    @SuppressLint("LongLogTag")
    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
            }
        }
    }

    /*
     * Turning On flash
     */
    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound
            //playSound();

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;

            // changing button/switch image
            //toggleButtonImage();
        }

    }

    /*
     * Turning Off flash
     */
    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound
            // playSound();

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;

            // changing button/switch image
            //toggleButtonImage();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // on pause turn off the flash
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // on resume turn on the flash
        if (hasFlash)
            turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

}

