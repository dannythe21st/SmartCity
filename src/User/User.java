package User;

import Tip.Tip;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class User implements UserIF{

    private String id;
    private String name;
    private int age;
    private int numOfTips;
    private int level;
    private int type; //Admin (1) || Regular (2)
    private Map<String, Tip> tips;


    public User(String id, String name, int age, int type){
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
    public int getNumOfTips() { return tips.size(); }

    @Override
    public int getLevel() { return level; }

    @Override
    public int getType() { return type; }

    @Override
    public void updateLevel(){
        if (numOfTips < 5)
            this.level = 0;
        else{
            this.level = Math.floorDiv(numOfTips,  5);
        }
    }

    public void addTip(Tip tip){
        tips.put(tip.getId(), tip);
    }

    public Iterator<Tip> tipsByUser(){
        return tips.values().iterator();
    }

}
