package appstosolutions.savinglives;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by aldo on 22/09/2017.
 */
public class CallBarring extends BroadcastReceiver {

    // This String will hold the incoming phone number
    private String number;

    @Override
    public void onReceive(Context context, Intent intent) {
        // If, the received action is not a type of "Phone_State", ignore it

        if (MainActivity.Escucha == 2) {
            try {
                ITelephony telephonyService;
                TelephonyManager telephony = (TelephonyManager)
                        context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Class c = Class.forName(telephony.getClass().getName());
                    Method m = c.getDeclaredMethod("getITelephony");
                    m.setAccessible(true);
                    telephonyService = (ITelephony) m.invoke(telephony);
                    telephonyService.endCall();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                Log.e("ExceptionCallBarring: ", e.getMessage());
            }
        }
    }
    // Method to disconnect phone automatically and programmatically
    // Keep this method as it is
   /*  @SuppressWarnings({"rawtypes", "unchecked"})
   private void disconnectPhoneItelephony(Context context) {
        ITelephony telephonyService;
        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (ITelephony) m.invoke(telephony);
            telephonyService.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
}
