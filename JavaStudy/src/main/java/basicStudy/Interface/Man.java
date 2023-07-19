package basicStudy.Interface;

/**
 * @ClassName Man
 * @Description
 * @Author SDY
 * @Date 2023/7/19 22:26
 **/
public class Man implements Filial,Spoony{
    public static void main(String[] args) {
        Man man = new Man();
        man.help();
    }
    @Override
    public void help() {
        System.out.println("他好");
        Filial.super.help();
        Spoony.super.help();
    }
}
