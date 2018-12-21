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

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/19.
 */

public class RockLee extends Plant {

    private Zombie zombie;

    public RockLee(){
        super("plant/rocklee/stand_%02d.png",3);
        setScale(2);
        setFlipX(true);
    }

    public void start(Zombie zombie){
        this.zombie = zombie;
        CCDelayTime ccDelayTime = CCDelayTime.action(1.4f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"spell");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
        if(zombie==null || zombie.getHP()==0){
            removeSelf();
        }
    }

    public void spell(){
        ToolsSet.effectSound(R.raw.up);
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 7;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/rocklee/spell%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);


        CCDelayTime ccDelayTime = CCDelayTime.action(0.6f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"stork");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
        if(zombie==null || zombie.getHP()==0){
            removeSelf();
        }
    }

    public void stork(){
        setPosition(zombie.getPosition().x - 30,zombie.getPosition().y);
        AEffect aEffect = new AEffect("plant/rocklee/stork_%02d.png",3,0.3f,0.1f);
        aEffect.setPosition(zombie.getPosition());
        aEffect.setScale(2);
        getParent().addChild(aEffect);

        CCJumpTo ccJumpTo = CCJumpTo.action(0.5f,ccp(zombie.getPosition().x + 300,zombie.getPosition().y),100,1);
        zombie.runAction(ccJumpTo);

        CCDelayTime ccDelayTime = CCDelayTime.action(0.5f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"removeme");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
        if(zombie==null || zombie.getHP()==0){
            removeme();
        }

    }

    public void removeme(){
        if(zombie==null || zombie.getHP()==0){
            removeSelf();
        }
        AEffect aEffect = new AEffect("eff/show/Frame%02d.png", 5);
        aEffect.setPosition(getPosition().x,getPosition().y);
        getParent().addChild(aEffect,6);

        AEffect aEffect1 = new AEffect("eff/SetEff/Frame%02d.png" , 4);
        aEffect1.setPosition(zombie.getPosition());
        getParent().addChild(aEffect1,8);
        aEffect1.setScale(1.5f);

        zombie.hurtCompute(9999);
        if (zombie.getHP() == 0) {
            zombie.death(0);
            zombie.removeSelf();
        }


        removeSelf();

    }


}
