package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jingyuyan on 2018/12/14.
 */

public class TallNut extends Plant {

    public TallNut() {
        super("plant/TallNut/high/Frame%02d.png", 14);
        setPrice(125);
        setHP(1200);
    }

    @Override
    public void hurtCompute(int hurt) {
        super.hurtCompute(hurt);
        if (getHP()>=400 && getHP()<=800){
            stopAllActions();
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        "plant/TallNut/middle/Frame%02d.png",i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.2f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            runAction(ccRepeatForever);
        }

        if (getHP()<400){
            stopAllActions();
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        "plant/TallNut/low/Frame%02d.png",i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.2f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            runAction(ccRepeatForever);
        }

    }
}
