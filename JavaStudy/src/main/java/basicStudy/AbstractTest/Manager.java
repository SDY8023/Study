package basicStudy.AbstractTest;

/**
 * @ClassName Manager
 * @Description
 * @Author SDY
 * @Date 2023/7/19 20:28
 **/
public class Manager extends Employee{
    private int bonus;

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public String work() {
        return "aaaa";
    }
}
