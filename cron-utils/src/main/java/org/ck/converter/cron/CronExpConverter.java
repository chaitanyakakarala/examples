package org.ck.converter.cron;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronExpression;

/**
 * The Class CronUtil.
 * @Author : Krishna Chaitanya. Kakarala.
 * 
 */
public class CronExpConverter {

    /** The m date. */
    private final Date mdate;

    /** The m cal. */
    private final Calendar mcal;

    /** The m seconds. */
    private final String mseconds = "0";

    /** The m days of week. */
    private String mdaysofweek = "?";

    /** The m mins. */
    private String mmins;

    /** The m hours. */
    private String mhours;

    /** The m days of month. */
    private String mdaysofmonth;

    /** The m months. */
    private String mmonths;

    /** The m years. */
    private String myears;


    /**
     * Instantiates a new cron util.
     *
     * @param pdate the date
     */
    public CronExpConverter(Date pdate) {
        this.mdate = pdate;
        mcal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        this.generateCronExpression();
    }

    /**
     * Generate cron expression.
     */
    private void generateCronExpression() {
        mcal.setTime(mdate);

        String hours = String.valueOf(mcal.get(Calendar.HOUR_OF_DAY));
        this.mhours = hours;

        String mins = String.valueOf(mcal.get(Calendar.MINUTE));
        this.mmins = mins;

        String days = String.valueOf(mcal.get(Calendar.DAY_OF_MONTH));
        this.mdaysofmonth = days;

        String months = new java.text.SimpleDateFormat("MM").format(mcal.getTime());
        this.mmonths = months;

        String years = String.valueOf(mcal.get(Calendar.YEAR));
        this.myears = years;

        String dayOfWeek = String.valueOf(mcal.get(Calendar.DAY_OF_WEEK));
        this.mdaysofweek = dayOfWeek;

    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return mdate;
    }

    /**
     * Gets the seconds.
     *
     * @return the seconds
     */
    public String getSeconds() {
        return mseconds;
    }

    /**
     * Gets the mins.
     *
     * @return the mins
     */
    public String getMins() {
        return mmins;
    }

    /**
     * Gets the days of week.
     *
     * @return the days of week
     */
    public String getDaysOfWeek() {
        String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        System.out.println(mcal.getTime());
        return weekDays[Integer.parseInt(mdaysofweek) - 1];
    }

    /**
     * Gets the hours.
     *
     * @return the hours
     */
    public String getHours() {
        return mhours;
    }

    /**
     * Gets the days of month.
     *
     * @return the days of month
     */
    public String getDaysOfMonth() {
        return mdaysofmonth;
    }

    /**
     * Gets the months.
     *
     * @return the months
     */
    public String getMonths() {
        return mmonths;
    }

    /**
     * Gets the years.
     *
     * @return the years
     */
    public String getYears() {
        return myears;
    }

    /**
     * Builds the expression from array.
     *
     * @param ar the ar
     * @return the string
     */
    public static String buildExpressionFromArray(String[] ar) {
        String exp = "";

        for (int i = 0; i < ar.length; i++) {
            if (i != ar.length - 1) {
                exp = exp + ar[i] + " ";
            } else {
                exp = exp + ar[i];
            }
        }

        return exp;
    }

    /**
     * Apply conversion to get seconds.
     *
     * @param outCronExp   the out cron exp
     * @param secondsValue the seconds value
     * @return the string
     */
    public String applyConversionToGetSeconds(String outCronExp, String secondsValue) {
        return outCronExp + secondsValue;
    }

    /**
     * Apply conversion to get minutes.
     *
     * @param outCronExp    the out cron exp
     * @param inar          the inar
     * @param callHelper    the call helper
     * @param inputTimeZone the input time zone
     * @return the string
     * @throws Exception the exception
     */
    public String applyConversionToGetMinutes(String outCronExp, String[] inar, CronExpConverter callHelper,
    		String inputTimeZone) throws Exception {

        if (inar[1].indexOf(",") > 0) {
            // More than one minute expression with comma separator
            String[] tmp = inar;
            String bkp = inar[1];

            String[] mins = inar[1].split(",");
            for (int i = 0; i < mins.length; i++) {
                tmp[1] = mins[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);
                if (i == 0) {
                    outCronExp = outCronExp + " " + tmpInstance.getMins();
                } else if (i == mins.length - 1) {
                    outCronExp = outCronExp + "," + tmpInstance.getMins() + " ";
                } else {
                    outCronExp = outCronExp + "," + tmpInstance.getMins();
                }
            }
            inar[1] = bkp;
        } else if (inar[1].indexOf("/") > 0) {
            // TimeGap minutes expression like 0/5 or 10/20
            String[] tmp = inar;
            String bkp = inar[1];

            String[] mins = inar[1].split("/");
            for (int i = 0; i < mins.length; i++) {
                tmp[1] = mins[i];
                if (i == 1) {
                    outCronExp = outCronExp + "/" + tmp[i] + " ";
                    continue;
                }
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);
                if (i == 0) {
                    outCronExp = outCronExp + " " + tmpInstance.getMins();
                } else if (i == mins.length - 1) {
                    outCronExp = outCronExp + "/" + tmpInstance.getMins() + " ";
                } else {
                    outCronExp = outCronExp + "/" + tmpInstance.getMins();
                }
            }
            inar[1] = bkp;
        } else if (inar[1].matches("[0-9]*")) {
            // Only minute number provided...Convert the expression and take as
            outCronExp = outCronExp + " " + callHelper.getMins() + " ";
        } else {
            // * provided .. concat as is
            outCronExp = outCronExp + " " + inar[1] + " ";
        }

        return outCronExp;
    }


    /**
     * Apply conversion to get hours.
     *
     * @param inar          the inar
     * @param inputTimeZone the input time zone
     * @param outCronExp    the out cron exp
     * @param calHelper     the cal helper
     * @return the string
     * @throws Exception the exception
     */
    public String applyConversionToGetHours(String[] inar, String inputTimeZone, String outCronExp, CronExpConverter calHelper) throws Exception {

        if (inar[2].indexOf(",") > 0) {
            // More than one hour expression with comma separator
            String[] tmp = inar;
            String bkp = inar[2];

            String[] hrs = inar[2].split(",");
            for (int i = 0; i < hrs.length; i++) {
                tmp[2] = hrs[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);
                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getHours();
                } else if (i == hrs.length - 1) {
                    outCronExp = outCronExp + "," + tmpInstance.getHours() + " ";
                } else {
                    outCronExp = outCronExp + "," + tmpInstance.getHours();
                }
            }
            inar[2] = bkp;
        } else if (inar[2].indexOf("/") > 0) {
            // TimeGap hours expression like 0/5 or 10/20
            String[] tmp = inar;
            String bkp = inar[2];

            String[] hrs = inar[2].split("/");
            for (int i = 0; i < hrs.length; i++) {
                tmp[2] = hrs[i];
                if (i == 1) {
                    outCronExp = outCronExp + "/" + tmp[i] + " ";
                    continue;
                }
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);
                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getHours();
                } /*else if (i == hrs.length - 1) {
                    outCronExp = outCronExp + "/" + tmpInstance.getHours() + " ";
                } else {
                outCronExp = outCronExp + "/" + tmpInstance.getHours();
                }*/
            }
            inar[2] = bkp;
        } else if (inar[2].matches("[0-9]*")) {
            // Only hours number provided...Convert the expression and take as
            outCronExp = outCronExp + calHelper.getHours() + " ";
        } else if (inar[2].indexOf("-") > 0) {
            // Only hours number provided...Convert the expression and take as
            String[] tmp = inar;
            String bkp = inar[2];
            String[] hrs = inar[2].split("-");
            for (int i = 0; i < hrs.length; i++) {
                tmp[2] = hrs[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);
                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getHours();
                } else {
                    outCronExp = outCronExp + "-" + tmpInstance.getHours() + " ";
                }
            }
            inar[2] = bkp;

        } else {
            // * provided .. concat as is
            outCronExp = outCronExp + inar[2] + " ";
        }

        return outCronExp;
    }


    /**
     * Apply conversion to get day of month.
     *
     * @param inar          the inar
     * @param inputTimeZone the input time zone
     * @param outCronExp    the out cron exp
     * @param calHelper     the cal helper
     * @return the string
     * @throws Exception the exception
     */
    public String applyConversionToGetDayOfMonth(String[] inar, String inputTimeZone, String outCronExp, CronExpConverter calHelper) throws Exception {

        if (inar[3].equalsIgnoreCase("?")) {
            // if ? is provided... concat as it is
            outCronExp = outCronExp + inar[3] + " ";

        } else if (inar[3].equalsIgnoreCase("*")) {
            // if * is provided ... concat as it is
            outCronExp = outCronExp + inar[3] + " ";
        } else if (inar[3].indexOf('-') > 0) {

            String[] tmp = inar;

            String[] days = inar[3].split("-"); // if - is provided ... get them from calander object

            for (int i = 0; i < days.length; i++) {
                tmp[3] = days[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);

                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getDaysOfMonth();
                } else if (i == days.length - 1) {
                    outCronExp = outCronExp + "-" + tmpInstance.getDaysOfMonth() + " ";
                } else {
                    outCronExp = outCronExp + "-" + tmpInstance.getDaysOfMonth();
                }
            }

        } else if (inar[3].indexOf('/') > 0) {

            String[] tmp = inar;

            String[] days = inar[3].split("/"); // if - is provided ... get them from calander object

            for (int i = 0; i < days.length; i++) {
                tmp[3] = days[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);

                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getDaysOfMonth();
                } else if (i == days.length - 1) {
                    outCronExp = outCronExp + "/" + tmpInstance.getDaysOfMonth() + " ";
                } else {
                    outCronExp = outCronExp + "/" + tmpInstance.getDaysOfMonth();
                }
            }


        }

        return outCronExp;
    }

    /**
     * Apply conversion toget week day.
     *
     * @param inar          the inar
     * @param inputTimeZone the input time zone
     * @param outCronExp    the out cron exp
     * @param calHelper     the cal helper
     * @return the string
     * @throws Exception the exception
     */
    public String applyConversionTogetWeekDay(String[] inar, String inputTimeZone, String outCronExp, CronExpConverter calHelper) throws Exception {

        //applying the day of week conversion
        if (inar[5].indexOf(",") > 0) {
            String[] tmp = inar;
            String[] days = inar[5].split(",");
            for (int i = 0; i < days.length; i++) {
                tmp[5] = days[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);

                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getDaysOfWeek();
                } else if (i == days.length - 1) {
                    outCronExp = outCronExp + "," + tmpInstance.getDaysOfWeek() + " ";
                } else {
                    outCronExp = outCronExp + "," + tmpInstance.getDaysOfWeek();
                }
            }
        } else if (inar[5].indexOf("-") > 0) {
            String[] tmp = inar;
            String[] days = inar[5].split("-");
            for (int i = 0; i < days.length; i++) {
                tmp[5] = days[i];
                String tmpExp = buildExpressionFromArray(tmp);
                CronExpConverter tmpInstance = buildCronUtil(tmpExp, inputTimeZone);

                if (i == 0) {
                    outCronExp = outCronExp + tmpInstance.getDaysOfWeek();
                } else if (i == days.length - 1) {

                    tmp[2] = getMaxNumberOutOfString(tmp[2]);
                    tmpExp = buildExpressionFromArray(tmp);
                    tmpInstance = buildCronUtil(tmpExp, inputTimeZone);

                    outCronExp = outCronExp + "-" + tmpInstance.getDaysOfWeek() + " ";
                } else {
                    outCronExp = outCronExp + "-" + tmpInstance.getDaysOfWeek();
                }
            }
        } else if (inar[5].equalsIgnoreCase("?")) {
            outCronExp = outCronExp + inar[5] + " ";
        } else if (inar[5].equalsIgnoreCase("*")) {
            outCronExp = outCronExp + inar[5] + " ";
        } else {
            outCronExp = outCronExp + calHelper.getDaysOfWeek() + " ";
        }

        return outCronExp;
    }

    /**
     * Builds the cron util.
     *
     * @param expression the expression
     * @param timeZone   the time zone
     * @return the cron util
     * @throws Exception the exception
     */
    public CronExpConverter buildCronUtil(String expression, String timeZone) throws Exception {

        CronExpression exp = new CronExpression(expression);
        exp.setTimeZone(TimeZone.getTimeZone(timeZone));

        CronExpression.isValidExpression(expression);
        Date date = exp.getNextValidTimeAfter(new Date());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a,zzzz");
        String dt = dateFormat.format(date);

        Date cronDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a,zzzz").parse(dt);

        return new CronExpConverter(cronDate);

    }

    /**
     * Gets the time zone for config.
     *
     * @param providedTimeZone the provided time zone
     * @return the time zone for config
     */
    public static String getTimeZoneForConfig(String providedTimeZone) {

        return providedTimeZone.substring(providedTimeZone.indexOf("(") + 1, providedTimeZone.length() - 1).trim();
    }

    /**
     * Gets the sorted array from string array.
     *
     * @param nums the nums
     * @return the sorted array from string array
     */
    public static Integer[] getSortedArrayFromStringArray(String[] nums) {
        Integer[] sortedArray = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            sortedArray[i] = Integer.parseInt(nums[i].trim());
        }
        Arrays.sort(sortedArray);
        return sortedArray;
    }

    /**
     * Builds the string frm int ary.
     *
     * @param nums      the nums
     * @param separator the separator
     * @return the string
     */
    public static String buildStringFrmIntAry(Integer[] nums, String separator) {
        String exp = "";

        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1) {
                exp = exp + nums[i] + separator;
            } else {
                exp = exp + nums[i];
            }
        }

        return exp;

    }

    /**
     * Sort numerics in string.
     *
     * @param inString the in string
     * @return the string
     */
    public static String sortNumericsInString(String inString) {

        String[] elements = inString.split(" ");

        for (int i = 0; i < elements.length; i++) {
            if ("*".equalsIgnoreCase(elements[i].trim()) || "?".equalsIgnoreCase(elements[i].trim()) || elements[i].matches("[a-zA-Z,]*")) {
                continue;
            } else if (elements[i].indexOf(",") > 0) {
                String[] nums = elements[i].split(",");
                String srtdElmnt = buildStringFrmIntAry(getSortedArrayFromStringArray(nums), ",");
                elements[i] = srtdElmnt;
            } else if (elements[i].indexOf("/") > 0) {
                    /*String[] nums = elements[i].split("/");
                      String srtdElmnt = buildStringFrmIntAry(getSortedArrayFromStringArray(nums),"/");
                      elements[i] = srtdElmnt;*/
            }
        }

        return buildExpressionFromArray(elements);
    }

    /**
     * Gets the max number out of string.
     *
     * @param input the input
     * @return the max number out of string
     */
    public String getMaxNumberOutOfString(String input) {

        if ("*".equalsIgnoreCase(input) || "?".equalsIgnoreCase(input) || input.matches("[0-9]*")) {
            return input;
        } else if (input.indexOf("-") > 0) {
            String[] inar = input.split("-");
            return inar[1].trim();
        } else if (input.indexOf(",") > 0) {
            String[] inar = input.split(",");
            return inar[inar.length - 1].trim();
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        String expression = "1 0,0,0 8,12,21 ? * MON-FRI";
        System.out.println(expression);
        String timeZone = "GMT";
        CronExpression exp = new CronExpression(expression);
        exp.setTimeZone(TimeZone.getTimeZone(timeZone));
        CronExpression.isValidExpression(expression);
        Date d = exp.getNextValidTimeAfter(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a,zzzz");
        String dt = dateFormat.format(d);
        Date cronDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a,zzzz").parse(dt);
        String[] inar = expression.split(" ");
        String outCronExp = "";
        CronExpConverter util = new CronExpConverter(cronDate);
        outCronExp = outCronExp+util.applyConversionToGetSeconds(outCronExp,inar[0]);
        outCronExp = util.applyConversionToGetMinutes(outCronExp,inar,util,timeZone);
        outCronExp = util.applyConversionToGetHours(inar, timeZone, outCronExp, util);
        outCronExp = util.applyConversionToGetDayOfMonth(inar, timeZone, outCronExp, util);
        outCronExp = outCronExp+inar[4]+" ";//Make the day and month
        outCronExp = util.applyConversionTogetWeekDay(inar, timeZone, outCronExp, util);
        if(inar.length==7){
          outCronExp = outCronExp+inar[inar.length-1];
        }
        outCronExp = sortNumericsInString(outCronExp);
        System.out.println(outCronExp);
        System.out.println(CronExpression.isValidExpression(outCronExp));
        }

}
