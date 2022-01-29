package Tip;

import User.User;

public class Tip implements TipIF{

    private String authorID;
    private String id;
    private String address;
    private String description;

    public Tip(String authorID, String id, String address, String description) {
        this.authorID = authorID;
        this.id = id;
        this.address = address;
        this.description = description;
    }

    public String getAuthor() { return authorID; }

    public String getId() { return id; }

    public String getAddress() { return address; }

    public String getDescription() { return description; }
}
