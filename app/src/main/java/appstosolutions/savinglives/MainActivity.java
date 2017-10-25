package appstosolutions.savinglives;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener  {

    MediaPlayer mp;
    private Button boton_activar;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

        boton_activar = (Button) findViewById(R.id.button);
        int i = View.SOUND_EFFECTS_ENABLED;
        boton_activar.playSoundEffect(i);
        boton_activar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        //getDrawable(R.drawable.corazon_1)
        if (findViewById(R.id.Fondoblanco).getVisibility() == View.VISIBLE) {
            Animation slideUpIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);

            TextView tv = (TextView) findViewById(R.id.button);
            slideUpIn.setDuration(400);
            tv.startAnimation(slideUpIn);


            findViewById(R.id.Fondoblanco).setVisibility(View.INVISIBLE);
            findViewById(R.id.button).setBackground(getDrawable(R.drawable.corazon_invertido));
        } else {
            Animation slideUpIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);

            TextView tv = (TextView) findViewById(R.id.button);
            slideUpIn.setDuration(400);
            tv.startAnimation(slideUpIn);

            findViewById(R.id.Fondoblanco).setVisibility(View.VISIBLE);
            findViewById(R.id.button).setBackground(getDrawable(R.drawable.corazon_1));

        }
        MediaPlayer mp = MediaPlayer.create(this, R.raw.latido);
        mp.start();


    }
}







