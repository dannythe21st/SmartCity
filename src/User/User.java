package User;

import Tip.Tip;

import java.util.HashMap;
import java.util.Map;

public class User implements UserIF{

    private String id;
    private String name;
    private int age;
    private int numOfTips;
    private int level;
    private Map<String, Tip> tips;

    public User(String id, String name, int age){
        this.id = id;
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

    @Override
    public String getID() {
        return id;
    }


}
