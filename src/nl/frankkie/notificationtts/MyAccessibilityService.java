package nl.frankkie.notificationtts;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 *
 * @author FrankkieNL
 */
public class MyAccessibilityService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        //api 16+
        //getServiceInfo().eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        Log.v("NotificationTTS", "Connected");
        
        //http://stackoverflow.com/questions/13853304/accessibility-events-not-recognized-by-accessibility-service-in-android-2-3gb
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 0;
        info.flags = AccessibilityServiceInfo.DEFAULT;
        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent arg0) {
        if (arg0 == null) {
            return;
        }
        if (arg0.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            return;
        }

        //Process Notification
        Util.processNotification(this, arg0);
    }

    @Override
    public void onInterrupt() {
        //do nothin'
    }

    @Override
    public void onDestroy() {
        //super.onDestroy(); //To change body of generated methods, choose Tools | Templates.
        if (Util.textToSpeech != null) {
            //Kill
            Util.textToSpeech.shutdown();
            Util.textToSpeech = null;
        }
    }

}
