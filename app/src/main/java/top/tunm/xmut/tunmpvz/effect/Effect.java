package top.tunm.xmut.tunmpvz.effect;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;

import java.util.ArrayList;
import java.util.Locale;


public class Effect extends CCSprite {
    private int HP=100;
    private int price;


    public Effect(String format, int number){
        super(String.format(Locale.CHINA,format,0));
        setAnchorPoint(0.5f,0);
        ArrayList<CCSpriteFrame> frames=new ArrayList<>();
        for (int i = 0; i <number ; i++) {
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,format,i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,0.2f);
        CCAnimate ccAnimate= CCAnimate.action(ccAnimation,false);
        CCRepeatForever ccRepeatForever= CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

    }

    public Effect(String format, int number,float delay){
        super(String.format(Locale.CHINA,format,0));
        setAnchorPoint(0.5f,0);
        ArrayList<CCSpriteFrame> frames=new ArrayList<>();
        for (int i = 0; i <number ; i++) {
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,format,i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,delay);
        CCAnimate ccAnimate= CCAnimate.action(ccAnimation,false);
        CCRepeatForever ccRepeatForever= CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

    }





    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void hurtCompute(int hurt){
        HP -= hurt;
        if (HP<0){
            HP=0;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
