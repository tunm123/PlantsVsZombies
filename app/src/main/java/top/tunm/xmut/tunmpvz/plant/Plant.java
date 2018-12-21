package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.zombies.Zombie;


public class Plant extends CCSprite {
    private int HP=100;
    private int price;

    private int currerCol;
    private boolean isRemove;

    private boolean dontAttack;


    public Plant(String format,int number){
        super(String.format(Locale.CHINA,format,0));
        setAnchorPoint(0.5f,0);
        ArrayList<CCSpriteFrame> frames=new ArrayList<>();
        for (int i = 0; i <number ; i++) {
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,format,i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,0.2f);
        CCAnimate ccAnimate= CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever= CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

    }

    public void death(Zombie zombie){

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

    public void safe(ArrayList<Zombie> zombies){
        for (Zombie zombie:zombies){
            if (CGPoint.ccpDistance(zombie.getPosition(),getPosition())<=40){
                zombie.move();
            }
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCurrerCol() {
        return currerCol;
    }

    public void setCurrerCol(int currerCol) {
        this.currerCol = currerCol;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }

    public boolean isDontAttack() {
        return dontAttack;
    }

    public void setDontAttack(boolean dontAttack) {
        this.dontAttack = dontAttack;
    }
}
