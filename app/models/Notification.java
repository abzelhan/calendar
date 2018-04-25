package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by abzalsahitov@gmail.com  on 4/20/18.
 */
@Entity
public class Notification extends Model {
    private int yearNum;
    private int monthNum;
    private int monthDay;
    private Calendar time;
    private String title;
    private String description;
    private boolean expired;

    @Transient
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public NotificationJson getJson(){
        NotificationJson notificationJson = new NotificationJson();
        notificationJson.setId(getId());
        notificationJson.setDescription(description);
        notificationJson.setTitle(title);
        notificationJson.setMonthDay(monthDay);
        notificationJson.setMonthNum(monthNum);
        notificationJson.setExpired(expired);
        notificationJson.setTimeStr(sdf.format(time.getTime()));
        return notificationJson;
    }

    public int getYearNum() {
        return yearNum;
    }

    public Notification setYearNum(int yearNum) {
        this.yearNum = yearNum;
        return this;
    }

    public boolean isExpired() {
        return expired;
    }

    public Notification setExpired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public Notification setMonthNum(int monthNum) {
        this.monthNum = monthNum;
        return this;
    }

    public int getMonthDay() {
        return monthDay;
    }

    public Notification setMonthDay(int monthDay) {
        this.monthDay = monthDay;
        return this;
    }

    public Calendar getTime() {
        return time;
    }

    public Notification setTime(Calendar time) {
        this.time = time;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Notification setDescription(String description) {
        this.description = description;
        return this;
    }

    public static class NotificationJson{
        private long id;
        private int monthNum;
        private int monthDay;
        private String timeStr;
        private String title;
        private String description;
        private boolean expired;


        @Override
        public String toString() {
            return "NotificationJson{" +
                    "id=" + id +
                    ", monthNum=" + monthNum +
                    ", monthDay=" + monthDay +
                    ", timeStr='" + timeStr + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public boolean isExpired() {
            return expired;
        }

        public NotificationJson setExpired(boolean expired) {
            this.expired = expired;
            return this;
        }

        public long getId() {
            return id;
        }

        public NotificationJson setId(long id) {
            this.id = id;
            return this;
        }

        public int getMonthNum() {
            return monthNum;
        }

        public NotificationJson setMonthNum(int monthNum) {
            this.monthNum = monthNum;
            return this;
        }

        public int getMonthDay() {
            return monthDay;
        }

        public NotificationJson setMonthDay(int monthDay) {
            this.monthDay = monthDay;
            return this;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public NotificationJson setTimeStr(String timeStr) {
            this.timeStr = timeStr;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public NotificationJson setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public NotificationJson setDescription(String description) {
            this.description = description;
            return this;
        }
    }

}
