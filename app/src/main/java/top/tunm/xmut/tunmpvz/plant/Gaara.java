package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CCVertex3D;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/19.
 */

public class Gaara extends Plant {

    private Zombie zombie;
    private ArrayList<Zombie> zombies;
    private ArrayList<Zombie> group;
    private AEffect aEffect;

    public Gaara() {
        super("plant/gaara/stand%02d.png", 3);
        setPrice(20);
        setScale(2f);
    }

    public void start(Zombie zombie) {
        this.zombie = zombie;
        this.zombies = ToolsSet.currtCombatLayer.getZombies();
        CCDelayTime ccDelayTime = CCDelayTime.action(1.6f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "spell");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        runAction(ccSequence);
        if (zombie == null || zombie.getHP() == 0) {
            removega();
        }

    }

    public void spell() {
        ToolsSet.effectSound(R.raw.up);
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/gaara/spell%02d.png", i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.2f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        CCDelayTime ccDelayTime = CCDelayTime.action(1.8f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "end");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        runAction(ccSequence);
        aEffect = new AEffect("eff/sand/sand2_%02d.png", 19, 19 * 0.1f, 0.1f);
        aEffect.setPosition(zombie.getPosition());
        aEffect.setScale(1.5);
        getParent().addChild(aEffect, 6);

        group = new ArrayList<>();
        group.add(zombie);
        zombie.stop("none", 4);
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombi = zombieIterator.next();
            if (CGPoint.ccpDistance(zombi.getPosition(), zombie.getPosition()) <= 200) {
                group.add(zombi);
                zombi.stop("none", 4);
            }
        }
        if (zombie == null || zombie.getHP() == 0) {
            removega();
        }

    }

    public void end() {

        CCDelayTime ccDelayTime = CCDelayTime.action(0.6f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "removega");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        runAction(ccSequence);

        ToolsSet.effectSound(R.raw.bomb);


        AEffect aEffect1 = new AEffect("eff/SetEff/Frame%02d.png", 4);
        aEffect1.setPosition(zombie.getPosition());
        getParent().addChild(aEffect1, 8);
        aEffect1.setScale(1.5f);

        zombie.hurtCompute(9999);
        if (zombie.getHP() == 0) {
            zombie.death(0);
            zombie.removeSelf();
        }

//        Iterator<Zombie> zombieIterator = zombies.iterator();
//        while (zombieIterator.hasNext()){
//            Zombie zombi = zombieIterator.next();
//            if (CGPoint.ccpDistance(zombi.getPosition(),zombie.getPosition())<=200){
//                zombi.hurtCompute(9999);
//                if (zombi.getHP() == 0) {
//                    zombi.death(0);
//                    zombi.removeSelf();
//                }
//            }
//        }


        if (zombie == null || zombie.getHP() == 0) {
            removega();
        }


    }

    public void removega() {
        AEffect aEffect = new AEffect("eff/show/Frame%02d.png", 5);
//                        combatLine.addPlant(col,aEffect);
        aEffect.setPosition(getPosition().x, getPosition().y);
        getParent().addChild(aEffect, 6);
        removeSelf();
    }


}
