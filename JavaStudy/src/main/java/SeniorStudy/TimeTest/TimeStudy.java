package SeniorStudy.TimeTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @ClassName TimeStudy
 * @Description
 * @Author SDY
 * @Date 2023/8/13 22:08
 **/
public class TimeStudy {
    public static void main(String[] args) {
        // ZoneId: 类中包含了所有的时区信息
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("==========ZoneId=========");
        for (String availableZoneId : availableZoneIds) {
            System.out.println(availableZoneId);
        }
        System.out.println("==========LocalDateTime=========");
        // 获取指定时区的日期时间
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(localDateTime);

        // ZonedDateTime: 带时区的日期时间
        System.out.println("==========ZonedDateTime=========");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        // 获取指定时区的ZonedDateTime 对象

        System.out.println("==========ZonedDateTime=========");
        ZonedDateTime zo = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        System.out.println(zo);
    }
}
