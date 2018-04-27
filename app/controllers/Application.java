package controllers;

import models.Day;
import models.Months;
import models.Notification;
import play.i18n.Lang;
import play.mvc.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Application extends Controller {



    private static void putIntoMap(HashMap<String, Day> week, Calendar instance, boolean today) {
        Day day = new Day();//some comment
        day.setDayNum(instance.get(Calendar.DAY_OF_MONTH));
        day.setToday(today);
        day.setNotifications(
                Notification.find("monthNum=:monthNum and monthDay=:monthDay and yearNum=:yearNum")
                        .setParameter("monthNum", instance.get(Calendar.MONTH) + 1)
                        .setParameter("monthDay", instance.get(Calendar.DAY_OF_MONTH))
                        .setParameter("yearNum",instance.get(Calendar.YEAR))
                        .<Notification>fetch()
                        .stream().map(notification -> notification.getJson()).collect(Collectors.toList())
        );


        week.put(new SimpleDateFormat("EEEE").format(instance.getTime()), day);
    }

    public static void monthView(int year, int month) {
        if (month == 0) {
            month = 4;
        }
        if (year == 0) {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }

        Calendar calendar = Calendar.getInstance();
        int currentDay= calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        List<String> weekDayNames = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        List<Map<String, Day>> weeks = new ArrayList<>();
        int weekNum = 1;
        for (int dayOfMonth = 1; dayOfMonth <= Months.getMonthByMonthNumber(month).getDays(); dayOfMonth++) {
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if (weekNum > weeks.size()) {
                HashMap<String, Day> week = new HashMap<>();
                weeks.add(week);
            }


            putIntoMap((HashMap<String, Day>) weeks.get(weekNum - 1), calendar, currentDay == dayOfMonth && currentYear == calendar.get(Calendar.YEAR) && currentMonth == calendar.get(Calendar.MONTH));
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                weekNum++;
            }
        }
        int weeksAmount = weeks.size() - 1;
        String monthName = new SimpleDateFormat("MMMM").format(calendar.getTime()) ;
        render(month,year,monthName, weeksAmount, weekDayNames, weeks);
    }

    public static void index() {
        Calendar instance = Calendar.getInstance();
        monthView(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH) + 1);
    }



    public static void notificationForm() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(instance.getTime());
        currentTime = currentTime.replaceAll(" ","T");
        render(currentTime);
    }

    public static void saveNotification(String title, String description, String dateTime) throws ParseException {
        //yyyy-MM-dd hh:mm:ss
        System.out.println(title);
        System.out.println(description);
        if (dateTime != null) {
            dateTime = dateTime.replaceAll("T", " ");
            if (title == null ||(title!=null && title.length()==0)) {
                title = "Not presented";
            }
            if (description == null || (description!=null && description.length()==0)) {
                description = "Not presented";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = sdf.parse(dateTime);
            Calendar currentTime = Calendar.getInstance();
            currentTime.setTimeInMillis(date.getTime());
            new Notification()
                    .setTitle(title)
                    .setDescription(description)
                    .setMonthNum(currentTime.get(Calendar.MONTH) + 1)
                    .setMonthDay(currentTime.get(Calendar.DAY_OF_MONTH))
                    .setYearNum(currentTime.get(Calendar.YEAR))
                    .setTime(currentTime).save();

        }
        index();
    }

    public static void removeNotification(long id){
        Notification notification = Notification.findById(id);
        if(notification!=null){
            notification.delete();
        }
        index();
    }


}
