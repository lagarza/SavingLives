package appstosolutions.savinglives;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by algarciaa on 06/09/17.
 */
public class SMSReceiver extends BroadcastReceiver {

    String idMensaje = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            Bundle b = intent.getExtras();
            if (b != null) {
                Object[] pdus = (Object[]) b.get("pdus");

                SmsMessage[] mensajes = new SmsMessage[pdus.length];

                for (int i = 0; i < mensajes.length; i++) {
                    mensajes[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    idMensaje = mensajes[i].getOriginatingAddress();
//                String textoMensaje = mensajes[i].getMessageBody();
//                Log.i("ReceptorSMS", "Remitente: " + idMensaje);
//                Log.i("ReceptorSMS", "Mensaje: " + textoMensaje);
//                Toast.makeText(context, idMensaje, Toast.LENGTH_SHORT).show();
                }

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(idMensaje, null, "1El contacto con el que te intentas comunicar está salvando vidas", null, null);
            }

        }catch (Exception e){
            Log.e("ExceptionSMSReceiver: ", e.getMessage());
        }

    }
}

