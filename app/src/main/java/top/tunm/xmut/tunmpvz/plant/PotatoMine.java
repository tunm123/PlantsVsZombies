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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;

public class PotatoMine extends Plant {

    // 判断土豆雷是否长出来
    private boolean isUp = false;

    private int BombHurt = 9999;

    public PotatoMine() {
        super("plant/PotatoMine/Stand%02d.png", 2);
        setPrice(25);

        CCDelayTime ccDelayTime = CCDelayTime.action(3.0f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"up");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

    }

    public void up(){
        isUp = true;
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 8;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/PotatoMine/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.2f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);
    }

    public void bomb(ArrayList<Zombie> zombies){
//        AEffect aEffect = new AEffect("eff/Ele03/Frame%02d.png" , 6,1.2f,0.2f);
//        aEffect.setPosition(ccp(getPosition().x,getPosition().y-20));
//        getParent().addChild(aEffect,6);
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            if (CGPoint.ccpDistance(getPosition(),zombie.getPosition())<=90) {
                zombie.hurtCompute(getBombHurt());
                if (zombie.getHP() == 0) {
                    zombie.death(1);
                    zombie.removeSelf();
                }
            }
        }
        ToolsSet.effectSound(R.raw.ele);
        AEffect aEffect2 = new AEffect("eff/pota/eff%02d.png" , 5,0.8f,0.16f);
        aEffect2.setPosition(ccp(getPosition().x,getPosition().y-20));
        getParent().addChild(aEffect2,6);
//
//        AEffect aEffect3 = new AEffect("eff/Ele01/Frame%02d.png" , 4,0.8f,0.2f);
//        aEffect3.setPosition(ccp(zombie.getPosition().x,zombie.getPosition().y-20));
//        getParent().addChild(aEffect3,6);
//
//        AEffect aEffect4 = new AEffect("eff/zep/Frame%02d.png" , 7,1.4f,0.2f);
//        aEffect4.setPosition(ccp(zombie.getPosition().x,zombie.getPosition().y-20));
//        getParent().addChild(aEffect3,6);

        hurtCompute(9999);
        removeSelf();
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public int getBombHurt() {
        return BombHurt;
    }

    public void setBombHurt(int bombHurt) {
        BombHurt = bombHurt;
    }
}
