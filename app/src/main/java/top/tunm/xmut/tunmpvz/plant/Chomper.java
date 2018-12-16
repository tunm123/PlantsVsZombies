package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.zombies.NewspaperZombie;
import top.tunm.xmut.tunmpvz.zombies.PoleVaultingZombie;
import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;

public class Chomper extends Plant {

    // 是否有在吃饭
    private boolean isEating = false;

    // 当前正在吃的饭
    private Zombie zombie;

    public Chomper() {
        super("plant/Chomper/Frame%02d.png", 13);
        setPrice(150);
    }

    public void readyAttack(Zombie zombie){
        this.zombie = zombie;
        isEating = true;
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 9;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/ChomperAttack/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        // 播放完后开始吃僵尸
        CCDelayTime ccDelayTime = CCDelayTime.action(0.8f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"Eating");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void Eating(){
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 6;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/ChomperDigest/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        // 杀死僵尸
        // 头掉了
        String headStr;
        int fps;
        if (zombie instanceof NewspaperZombie){
            headStr = "zombies/zombies_1/NewspaperZombie/head%02d.png";
            fps = 10;
        } else if(zombie instanceof PoleVaultingZombie){
            headStr = "zombies/zombies_1/PoleVaultingZombie/head%02d.png";
            fps = 8;
        }
        else {
            headStr = "zombies/zombies_1/ZombieHead/Frame%02d.png";
            fps = 12;
        }
        AEffect head = new AEffect(headStr,fps,fps*0.1f,0.1f);
        head.setPosition(ccp(zombie.getPosition().x+20,zombie.getPosition().y-30));
        getParent().addChild(head,6);
        zombie.hurtCompute(99999);
        zombie.removeSelf();
        ToolsSet.effectSound(R.raw.fly);
        // 结束
        CCDelayTime ccDelayTime = CCDelayTime.action(10.0f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"endEat");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

    }

    // 结束吃饭
    public void endEat(){
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 13;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/Chomper/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.2f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);
        isEating = false;
        zombie = null;
    }

    public boolean isEating() {
        return isEating;
    }

    public void setEating(boolean eating) {
        isEating = eating;
    }
}
