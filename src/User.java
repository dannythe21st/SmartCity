import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int age;
    private int numOfTips;
    private int level;
    private Map<String, Tip> tips;

    public User(String name, int age){
        this.name = name;
        this.age = age;
        this.numOfTips = 0;
        this.level = 0;
        this.tips = new HashMap<>();
    }

    //Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getNumOfTips() { return numOfTips; }
    public int getLevel() { return level; }

    public void levelUp(){
        level = level++;
    }


}
