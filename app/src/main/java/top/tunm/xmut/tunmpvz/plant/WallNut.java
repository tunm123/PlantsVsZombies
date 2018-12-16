package top.tunm.xmut.tunmpvz.plant;


import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;

import java.util.ArrayList;
import java.util.Locale;

public class WallNut extends Plant{
    public WallNut() {
        super("plant/WallNut/high/Frame%02d.png", 16);
        setPrice(50);
        setHP(600);
    }

    @Override
    public void hurtCompute(int hurt) {
        super.hurtCompute(hurt);
        if (getHP()>=200 && getHP()<=400){
            stopAllActions();
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < 11; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        "plant/WallNut/middle/Frame%02d.png",i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.2f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            runAction(ccRepeatForever);
        }

        if (getHP()<200){
            stopAllActions();
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        "plant/WallNut/low/Frame%02d.png",i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.2f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            runAction(ccRepeatForever);
        }

    }
}
