package appstosolutions.savinglives;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by algarciaa on 06/09/17.
 */
public class CallReceiver extends BroadcastReceiver {

    String NumberPhonbe = "";
    int contador = 0;

    @Override
    public void onReceive(final Context context, Intent intent) {
        if(MainActivity.Escucha==2) {
            try {

                TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                telephony.listen(new PhoneStateListener() {
                    @Override
                    public void onCallStateChanged(int state, String incomingNumber) {
                        super.onCallStateChanged(state, incomingNumber);
                        // System.out.println("incomingNumber : " + incomingNumber);
                        //Toast.makeText(context, incomingNumber, Toast.LENGTH_LONG).show();

                        Log.e("*****phoneNumber:", incomingNumber);
                        NumberPhonbe = incomingNumber;

                        if (contador == 0) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(incomingNumber, null, "2El contacto con el que te intentas comunicar está salvando vidas", null, null);
                        }

                        contador++;

                    }
                }, PhoneStateListener.LISTEN_CALL_STATE);

            } catch (Exception e) {
                Log.e("ExceptionCallReceiver: ", e.getMessage());
            }
        }
    }
}
