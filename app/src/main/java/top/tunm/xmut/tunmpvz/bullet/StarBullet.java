package top.tunm.xmut.tunmpvz.bullet;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.plant.StarFruit;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/15.
 */

public class StarBullet extends CCSprite{
    private int speed = 300;
    private int attack = 20;
    private float ang;
    private StarFruit starFruit;
    private CGPoint intentPoint;

    public StarBullet(StarFruit starFruit, float ang) {
        super("plant/Star/Frame00.png");
        this.starFruit = starFruit;
        this.ang = ang;
        setPosition(starFruit.getPosition().x,
                starFruit.getPosition().y );

        intentPoint = CGPoint.ccp(starFruit.getPosition().x + 1400 * Math.cos(ang*(Math.PI/180)),
                starFruit.getPosition().y + 1400 * Math.sin(ang*Math.PI/180));
        starFruit.getParent().addChild(this,6);
        starFruit.getStarBullets().add(this);

        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 3;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "plant/Star/Frame%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);
        move();
        System.out.println("创建子弹并初始化");
    }

    public void move(){
        CGPoint end = intentPoint;
        float t= CGPointUtil.distance(getPosition(),end)/speed;
        CCMoveTo ccMoveTo=CCMoveTo.action(t,end);
        CCCallFunc ccCallFunc=CCCallFunc.action(this,"end");
        CCSequence ccSequence=CCSequence.actions(ccMoveTo,ccCallFunc);
        runAction(ccSequence);
    }

    public void end(){
        removeSelf();
        starFruit.getStarBullets().remove(this);

    }

    public void showBulletBlast(Zombie zombie){
        AEffect aEffect = new AEffect("eff/star/eff%02d.png", 5,0.6f,0.12f);
        aEffect.setPosition(ccp(zombie.getPosition().x, zombie.getPosition().y-40));
        getParent().addChild(aEffect, 6);
        ToolsSet.effectSound(R.raw.bomb1);
        CCDelayTime ccDelayTime = CCDelayTime.action(0.8f);
        CCHide ccHide = CCHide.action();
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccHide);
        aEffect.runAction(ccSequence);

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public float getAng() {
        return ang;
    }

    public void setAng(float ang) {
        this.ang = ang;
    }

    public StarFruit getStarFruit() {
        return starFruit;
    }

    public void setStarFruit(StarFruit starFruit) {
        this.starFruit = starFruit;
    }

    public CGPoint getIntentPoint() {
        return intentPoint;
    }

    public void setIntentPoint(CGPoint intentPoint) {
        this.intentPoint = intentPoint;
    }
}
