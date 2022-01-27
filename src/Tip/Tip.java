package Tip;

import User.User;

public class Tip {

    private User author;
    private String id;
    private String address;
    private String description;

    public Tip(User author, String id, String address, String description) {
        this.author = author;
        this.id = id;
        this.address = address;
        this.description = description;
    }

    public User getAuthor() { return author; }

    public String getId() { return id; }

    public String getAddress() { return address; }

    public String getDescription() { return description; }
}
