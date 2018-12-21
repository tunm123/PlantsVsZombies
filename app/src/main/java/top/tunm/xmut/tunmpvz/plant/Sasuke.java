package top.tunm.xmut.tunmpvz.plant;


import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/20.
 */

public class Sasuke extends Plant{

    private ArrayList<Zombie> zombies;

    public Sasuke(){
        super("plant/sasukeII/stand%02d.png",1);
        setPrice(20);
        setScale(2);
    }

    public void start(ArrayList<Zombie> zombies){
        this.zombies = zombies;
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 4;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/sasukeII/spell%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.25f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        CCDelayTime ccDelayTime = CCDelayTime.action(1.0f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"hold");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void hold(){
        ToolsSet.effectSound(R.raw.ele);
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 10;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/sasukeII/attack%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.15f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        CCDelayTime ccDelayTime = CCDelayTime.action(1.2f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"ele");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);



    }

    public void ele(){
        AEffect aEffect = new AEffect("plant/sasukeII/effect%02d.png",3,0.45f,0.176f);
        aEffect.setScale(2);
        getParent().addChild(aEffect,6);
        aEffect.setPosition(getPosition().x + 360,getPosition().y - 50);

        CCDelayTime ccDelayTime = CCDelayTime.action(0.15f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"stop");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void stop(){
        stopAllActions();

        CCDelayTime ccDelayTime = CCDelayTime.action(0.5f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"end");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            float dis = zombie.getPosition().x - getPosition().x;
            if (dis >= 0 && Math.abs(dis)<=500 ) {
                AEffect head = new AEffect("eff/Ele02/Frame%02d.png",4,4*0.1f,0.1f);
                head.setPosition(ccp(zombie.getPosition().x+20,zombie.getPosition().y-30));
                getParent().addChild(head,6);
                zombie.hurtCompute(999);
                if (zombie.getHP() == 0) {
                    zombie.death(0);
                    zombie.removeSelf();
                }
            }
        }
    }

    public void end(){
        AEffect aEffect = new AEffect("eff/show/Frame%02d.png", 5);
        aEffect.setPosition(getPosition().x,getPosition().y);
        getParent().addChild(aEffect,6);
        removeSelf();
    }

}
