package User;

import Tip.Tip;

import java.util.Iterator;

public interface UserIF {

    String getName();

    int getAge();

    int getNumOfTips();

    int getLevel();

    String getID();

    int getType();

    void updateLevel();

    void addTip(Tip tip);

    Iterator<Tip> tipsByUser();
}
