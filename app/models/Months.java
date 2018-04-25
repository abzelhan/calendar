package models;

/**
 * Created by abzalsahitov@gmail.com  on 4/20/18.
 */
public enum Months {

    JANUARY(1, 31,"Январь","January"),
    FEBRUARY(2,28,"Февраль","February"),
    MARCH(3, 31,"Март","March"),
    APRIL(4, 30,"Апрель","April"),
    MAY(5, 31,"Май","May"),
    JUNE(6, 30,"Июнь","June"),
    JULY(7, 31,"Июль","July"),
    AUGUST(8,31,"Август","August"),
    SEPTEMBER(9,30,"Сентябрь","September"),
    OCTOBER(10,31,"Октябрь","October"),
    NOVEMBER(11,30,"Ноябрь","November"),
    DECEMBER(12,31,"Декабрь","Docember");


    private final int monthNumber;
    private final int days;
    private final String nameRu;
    private final String nameEng;

    Months(int monthNumber, int days, String nameRu, String nameEng) {
        this.monthNumber = monthNumber;
        this.days = days;
        this.nameRu = nameRu;
        this.nameEng = nameEng;
    }

    public static Months getMonthByMonthNumber(int monthNumber){
        for (Months month : values()) {
            if(month.monthNumber==monthNumber){
                return month;
            }
        }
        return null;
    }


    public int getMonthNumber() {
        return monthNumber;
    }

    public int getDays() {
        return days;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getNameEng() {
        return nameEng;
    }
}
