package Tip;

import Establishment.Establishment;

public class Tip implements TipIF{

    private String authorID;
    private String id;
    private Establishment shop;
    private String description;

    public Tip(String authorID, String id, Establishment shop, String description) {
        this.authorID = authorID;
        this.id = id;
        this.shop = shop;
        this.description = description;
    }

    @Override
    public String getAuthorID() { return authorID; }

    @Override
    public String getId() { return id; }

    @Override
    public Establishment getShop() { return shop; }

    @Override
    public String getDescription() { return description; }
}
