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
    private String type; //Admin || Regular
    private Map<String, Tip> tips;


    public User(String id, String name, int age, String type){
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.numOfTips = 0;
        this.level = 0;
        this.tips = new HashMap<>();
    }

    //Getters
    @Override
    public String getID() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public int getAge() { return age; }
    @Override
    public int getNumOfTips() { return numOfTips; }
    @Override
    public int getLevel() { return level; }
    @Override
    public String getType() { return type; }

    public void levelUp(){
        level = level++;
    }


}
