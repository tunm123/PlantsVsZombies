package top.tunm.xmut.tunmpvz;

import org.cocos2d.nodes.CCSprite;

import java.util.Locale;


public class PlantCard {
    private int id;
    private CCSprite light;
    private CCSprite dark;

    public PlantCard(int id){
        this.id = id;
        light= CCSprite.sprite(String.format(Locale.CHINA,"choose/p%02d.png",id));
        dark= CCSprite.sprite(String.format(Locale.CHINA,"choose/p%02d.png",id));
        dark.setOpacity(100);
    }

    public int getId() {
        return id;
    }

    public CCSprite getLight() {
        return light;
    }

    public CCSprite getDark() {
        return dark;
    }
}
