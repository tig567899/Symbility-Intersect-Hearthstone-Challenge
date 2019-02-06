package tig567899.symbilitychallenge;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class CardList {

    @SerializedName("Basic")
    private List<Card> basicCards;

    @SerializedName("Classic")
    private List<Card> classicCards;

    @SerializedName("Hall of Fame")
    private List<Card> hofCards;

    @SerializedName("Naxxramas")
    private List<Card> naxCards;

    @SerializedName("Goblins vs Gnomes")
    private List<Card> gvgCards;

    @SerializedName("Blackrock Mountain")
    private List<Card> bmCards;

    @SerializedName("The Grand Tournament")
    private List<Card> tgtCards;

    @SerializedName("The League of Explorers")
    private List<Card> tleCards;

    @SerializedName("Whispers of the Old Gods")
    private List<Card> wogCards;

    @SerializedName("One Night in Karazhan")
    private List<Card> onkCards;

    @SerializedName("Mean Streets of Gadgetzan")
    private List<Card> msgCards;

    @SerializedName("Journey to Un'Goro")
    private List<Card> jugCards;

    @SerializedName("Knights of the Frozen Throne")
    private List<Card> kftCards;

    @SerializedName("Kobolds & Catacombs")
    private List<Card> kcCards;

    @SerializedName("The Witchwood")
    private List<Card> twCards;

    @SerializedName("The Boomsday Project")
    private List<Card> tbpCards;

    @SerializedName("Rastakhan's Rumble")
    private List<Card> rrCards;

    @SerializedName("Tavern Brawl")
    private List<Card> tbCards;

    @SerializedName("Taverns of Time")
    private List<Card> ttCards;

    @SerializedName("Hero Skins")
    private List<Card> hsCards;

    @SerializedName("Missions")
    private List<Card> missionCards;

    @SerializedName("Credits")
    private List<Card> creditCards;

    private ArrayList<Card> amalgamatedCards;

    ArrayList<Card> allCards()
    {
        amalgamatedCards = new ArrayList<>();

        amalgamatedCards.addAll(basicCards);
        amalgamatedCards.addAll(classicCards);
        amalgamatedCards.addAll(hofCards);
        amalgamatedCards.addAll(naxCards);
        amalgamatedCards.addAll(gvgCards);
        amalgamatedCards.addAll(bmCards);
        amalgamatedCards.addAll(tgtCards);
        amalgamatedCards.addAll(tleCards);
        amalgamatedCards.addAll(wogCards);
        amalgamatedCards.addAll(onkCards);
        amalgamatedCards.addAll(msgCards);
        amalgamatedCards.addAll(jugCards);
        amalgamatedCards.addAll(kcCards);
        amalgamatedCards.addAll(kftCards);
        amalgamatedCards.addAll(twCards);
        amalgamatedCards.addAll(tbpCards);
        amalgamatedCards.addAll(rrCards);
        amalgamatedCards.addAll(tbCards);
        amalgamatedCards.addAll(ttCards);
        amalgamatedCards.addAll(hsCards);
        amalgamatedCards.addAll(missionCards);
        amalgamatedCards.addAll(creditCards);

        return amalgamatedCards;
    }
}
