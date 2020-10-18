import com.alibaba.fastjson.JSON;
import com.tcl.uf.common.base.dto.token.TokenAppUserInfo;
import com.tcl.uf.tangram.TangramApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhongfk on 2020/8/27.
 * @version 1.0
 */
@SpringBootTest(classes = TangramApplication.class)
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    public RedisTemplate redisTemplate;

    @Test
    public void testRedis() {

        TokenAppUserInfo userInfo = new TokenAppUserInfo();
        userInfo.setToken("LOCALTOKEN_TEST");
        userInfo.setNickname("郗");
        userInfo.setUsername("令牌壹");
        userInfo.setAccountId((long)1756888);

        redisTemplate.opsForValue().set("LOCALTOKEN", JSON.toJSONString(userInfo));
        redisTemplate.expire("LOCALTOKEN", 3, TimeUnit.DAYS);
        Object o = redisTemplate.opsForValue().get("LOCALTOKEN");
        System.out.println("________________________" + o);
        System.out.println("__________________________======" + redisTemplate.opsForValue().get("LOCALTOKEN"));


    }


    //    public static void main(String[] args) {
//        JSONObject object = JSON.parseObject(pointTask);
//        List<PointTask> taskList = (List<PointTask>) object.get("rows");
//        System.out.println(taskList.toString());
//
//    }
    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(getWeekOfDate(date));

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
