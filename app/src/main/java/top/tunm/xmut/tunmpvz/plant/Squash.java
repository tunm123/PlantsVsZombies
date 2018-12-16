package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.util.CGPointUtil;

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

public class Squash extends Plant {

    // 判断窝瓜是否攻击
    private boolean isAttack = false;
    // 窝瓜的伤害
    private int hurt = 2000;
    // 僵尸群
    private ArrayList<Zombie> zombies;
    public Squash() {
        super("plant/Squash/Frame%02d.png", 17);
        setPrice(50);
    }

    public void attack(Zombie zombie,ArrayList<Zombie> zombies){
        this.zombies = zombies;
        setAttack(true);
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 8;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/SquashAttack/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        CCMoveTo ccMoveTo= CCMoveTo.action(0.8f,zombie.getPosition());
        runAction(ccMoveTo);

        // 延迟操作
        CCDelayTime ccDelayTime = CCDelayTime.action(0.8f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"push");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void push(){
        AEffect aEffect = new AEffect("eff/SetEff/Frame%02d.png" , 4);
        aEffect.setPosition(ccp(getPosition().x,getPosition().y-20));
        getParent().addChild(aEffect,8);

        // 群体伤害
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()){
            Zombie zombie = zombieIterator.next();
            if (CGPointUtil.distance(zombie.getPosition(),getPosition())<=90){
                zombie.hurtCompute(hurt);
                if (zombie.getHP()==0){
                    zombie.death(0);
                    zombie.removeSelf();
                    zombieIterator.remove();
                }
            }
        }

        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 7;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "eff/sui/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        CCDelayTime ccDelayTime = CCDelayTime.action(1.0f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"remove");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
        ToolsSet.effectSound(R.raw.tu);

    }

    public void remove(){
        removeSelf();
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public int getHurt() {
        return hurt;
    }

    public void setHurt(int hurt) {
        this.hurt = hurt;
    }
}
