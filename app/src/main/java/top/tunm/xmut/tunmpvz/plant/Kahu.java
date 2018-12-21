package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/20.
 */

public class Kahu extends Plant {
    private ArrayList<Zombie> zombies;
    private CGPoint cgPoint;
    public Kahu(){
        super("plant/kahu/stand%02d.png",1);
        setPrice(20);
        setScale(2);
    }

    public void start(Zombie zombie,ArrayList<Zombie> zombies){
        this.zombies = zombies;
        this.cgPoint = zombie.getPosition();
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 8;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/kahu/sepll%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.125f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        CCDelayTime ccDelayTime = CCDelayTime.action(0.5f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"hold");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void hold(){
//        setPosition(getPosition().x - 300,getPosition().y);
        AEffect aEffect = new AEffect("plant/kahu/effect%02d.png",11,2,2f/11f);
        aEffect.setPosition(cgPoint);
        getParent().addChild(aEffect,6);

        setPosition(getPosition().x - 200,getPosition().y);
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            float dis = zombie.getPosition().x - cgPoint.x;
            if (Math.abs(dis)<=500 ) {
                zombie.stop("none",2);
                CCJumpTo jumpTo = CCJumpTo.action(1f, zombie.getPosition(), 100, 1);
                zombie.runAction(jumpTo);
            }
        }
        CCDelayTime ccDelayTime = CCDelayTime.action(0.5f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"eff");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void eff(){
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            float dis = zombie.getPosition().x - cgPoint.x;
            if (Math.abs(dis)<=500 ) {
                zombie.hurtCompute(999);
                if (zombie.getHP() == 0) {
                    zombie.death(0);
                    zombie.removeSelf();
                }
            }
        }

        AEffect aEffect = new AEffect("eff/show/Frame%02d.png", 5);
        aEffect.setPosition(getPosition().x,getPosition().y);
        getParent().addChild(aEffect,6);
        removeSelf();
    }


    public void removemine(){
        removeSelf();
    }


}
