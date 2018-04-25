package models;

import java.util.List;

/**
 * Created by abzalsahitov@gmail.com  on 4/20/18.
 */
public class Day {

    int dayNum;
    boolean today;
    List<Notification.NotificationJson> notifications;

    public int getDayNum() {
        return dayNum;
    }

    public Day setDayNum(int dayNum) {
        this.dayNum = dayNum;
        return this;
    }

    public boolean isToday() {
        return today;
    }

    public Day setToday(boolean today) {
        this.today = today;
        return this;
    }

    public List<Notification.NotificationJson> getNotifications() {
        return notifications;
    }

    public Day setNotifications(List<Notification.NotificationJson> notifications) {
        this.notifications = notifications;
        return this;
    }
}
