package appstosolutions.savinglives;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
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



public class MainActivity extends AppCompatActivity implements OnClickListener  {

    MediaPlayer mp;
    private Button boton_activar;
    public static  int Escucha=1;
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_ALL);

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
            Escucha=2;
            changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_NONE);
        } else {
            Animation slideUpIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);

            TextView tv = (TextView) findViewById(R.id.button);
            slideUpIn.setDuration(400);
            tv.startAnimation(slideUpIn);

            findViewById(R.id.Fondoblanco).setVisibility(View.VISIBLE);
            findViewById(R.id.button).setBackground(getDrawable(R.drawable.corazon_1));
            Escucha=1;
            changeInterruptionFiler(NotificationManager.INTERRUPTION_FILTER_ALL);
        }
        MediaPlayer mp = MediaPlayer.create(this, R.raw.latido);
        mp.start();


    }

    protected void changeInterruptionFiler(int interruptionFilter){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){ // If api level minimum 23
            /*
                boolean isNotificationPolicyAccessGranted ()
                    Checks the ability to read/modify notification policy for the calling package.
                    Returns true if the calling package can read/modify notification policy.
                    Request policy access by sending the user to the activity that matches the
                    system intent action ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS.

                    Use ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED to listen for
                    user grant or denial of this access.

                Returns
                    boolean

            */
            // If notification policy access granted for this package
            if(mNotificationManager.isNotificationPolicyAccessGranted()){
                /*
                    void setInterruptionFilter (int interruptionFilter)
                        Sets the current notification interruption filter.

                        The interruption filter defines which notifications are allowed to interrupt
                        the user (e.g. via sound & vibration) and is applied globally.

                        Only available if policy access is granted to this package.

                    Parameters
                        interruptionFilter : int
                        Value is INTERRUPTION_FILTER_NONE, INTERRUPTION_FILTER_PRIORITY,
                        INTERRUPTION_FILTER_ALARMS, INTERRUPTION_FILTER_ALL
                        or INTERRUPTION_FILTER_UNKNOWN.
                */

                // Set the interruption filter
                mNotificationManager.setInterruptionFilter(interruptionFilter);

                if(interruptionFilter == NotificationManager.INTERRUPTION_FILTER_NONE) {
                    //mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                }
                if(interruptionFilter == NotificationManager.INTERRUPTION_FILTER_ALL){
                    //mNotification.flags = Notification.FLAG_INSISTENT;
                }

            }else {
                /*
                    String ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
                        Activity Action : Show Do Not Disturb access settings.
                        Users can grant and deny access to Do Not Disturb configuration from here.

                    Input : Nothing.
                    Output : Nothing.
                    Constant Value : "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"
                */
                // If notification policy access not granted for this package
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }
    }

}







