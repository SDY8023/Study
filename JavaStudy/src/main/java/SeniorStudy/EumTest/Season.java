package SeniorStudy.EumTest;

/**
 * @ClassName Season
 * @Description
 * @Author SDY
 * @Date 2023/8/20 16:43
 **/
public class Season {
    private final String SEASONNAME;
    private final String SEASONDESC;

    public Season(String seasonName, String seasonDesc) {
        this.SEASONNAME = seasonName;
        this.SEASONDESC = seasonDesc;
    }

    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","白雪皑皑");
}
