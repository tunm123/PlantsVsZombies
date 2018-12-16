package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;

/**
 * Created by jingyuyan on 2018/12/7.
 */

public class Jalapeno extends Plant {
    private ArrayList<Zombie> zombies;

    private int BombHurt = 2000;

    public Jalapeno(){
        super("plant/Jalapeno/Frame%02d.png", 8);
        setHP(1);
        setPrice(150);
    }

    public void bomb(ArrayList<Zombie> zombies){
        this.zombies = zombies;
        CCDelayTime ccDelayTime = CCDelayTime.action(1);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"rangeHurt");
        CCDelayTime ccDelayTime2 = CCDelayTime.action(1.0f);
        CCHide ccHide = CCHide.action();
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc,ccDelayTime2,ccHide);
        ToolsSet.effectSound(R.raw.boom3);
        runAction(ccSequence);
    }

    public void rangeHurt(){
        // 延迟性伤害
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()){
            Zombie zombie = zombieIterator.next();
            zombie.hurtCompute(getBombHurt());
            if (zombie.getHP()==0){
                zombie.death(1);
                zombie.removeSelf();
                zombieIterator.remove();
            }
        }

        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 4;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "eff/bomb3/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.25f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        System.out.println("Jalapeno boom!");
        AEffect aEffect = new AEffect("plant/JalapenoAttack/Frame%02d.png" , 8);
        aEffect.setPosition(ccp(getPosition().x,getPosition().y-20));
        getParent().addChild(aEffect,6);

        CCDelayTime ccDelayTime = CCDelayTime.action(1f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"remove");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

    }

    public void remove(){
        removeSelf();
    }


    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public int getBombHurt() {
        return BombHurt;
    }

    public void setBombHurt(int bombHurt) {
        BombHurt = bombHurt;
    }
}
