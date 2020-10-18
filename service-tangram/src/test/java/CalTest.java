import com.tcl.uf.common.base.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
public class CalTest {

    public static void main(String[] args) {

        String today = "2020-09-09 12:09:09";
        SimpleDateFormat smYearMonth = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat smDay = new SimpleDateFormat("MM");
        SimpleDateFormat smTime = new SimpleDateFormat("HH:mm");

        System.out.println(smYearMonth.format(TimeUtils.parseDate(today,"yyyy-MM")));
        System.out.println(smDay.format(TimeUtils.parseDate(today,"MM"))+ "/"+TimeUtils.getWeekOfDate(TimeUtils.conversionDate(today)));
        System.out.println((smTime.format(TimeUtils.parseDate(today,"yyyy-MM-dd HH:mm:ss"))));
        


    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}

