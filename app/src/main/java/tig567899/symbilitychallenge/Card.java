package tig567899.symbilitychallenge;

import com.google.gson.annotations.SerializedName;

public class Card {
    @SerializedName("name")
    private String name;

    @SerializedName("img")
    private String imgurl;

    @SerializedName("type")
    private String type;

    @SerializedName("playerClass")
    private String playerClass;

    public String getName()
    {
        return name;
    }
    public String getImgurl()
    {
        return imgurl;
    }

    public String getType() {
        return type;
    }

    public String getPlayerClass() {
        return playerClass;
    }
}
