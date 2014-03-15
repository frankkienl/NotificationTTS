package nl.frankkie.notificationtts.db;

/**
 *
 * @author FrankkieNL
 */
public class NotificationHistory {
    public static final String TABLE_NAME = "notification_history";
    
    public String packagename;
    public String appname;
    public String text;
    public long time;
    public int flags;

    public NotificationHistory(){
    }

}
