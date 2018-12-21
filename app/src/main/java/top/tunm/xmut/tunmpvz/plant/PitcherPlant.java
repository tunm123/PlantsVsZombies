package top.tunm.xmut.tunmpvz.plant;

import android.view.animation.RotateAnimation;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.bullet.PitcherButllet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/17.
 */


public abstract class PitcherPlant extends Plant {

    private CGPoint intentPoint;
    private boolean noAttack;
    private PitcherButllet bullet;
    private Zombie zombie;
    private int hurt;
    private String format;
    private String attPath;
    private String buPath;
    private int attInt;
    private int number;
    private boolean isRotate;


    public PitcherPlant(String format, int number, String attPath, int attInt, String buPath) {
        super(format, number);
        this.attInt = attInt;
        this.attPath = attPath;
        this.format = format;
        this.number = number;
        this.buPath = buPath;
    }


    public void ready(Zombie zombie) {
        if (!noAttack) {
            noAttack = true;
            this.intentPoint = zombie.getPosition();
            this.zombie = zombie;
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < attInt; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        attPath, i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.1f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            runAction(ccRepeatForever);

            CCDelayTime ccDelayTime = CCDelayTime.action((attInt * 0.1f)/2);
            CCCallFunc ccCallFunc = CCCallFunc.action(this, "launch");
            CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
            runAction(ccSequence);
        }
    }

    public void launch() {
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    format, i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.2f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

        bullet = new PitcherButllet(buPath,0.85f);
        bullet.setPosition(getPosition().x - 10, getPosition().y + 40);
        getParent().addChild(bullet);
        CCJumpTo jumpTo = CCJumpTo.action(1, intentPoint, 300, 1);
        bullet.runAction(jumpTo);
        if (isRotate) {
            bullet.runAction(CCRepeatForever.action(CCRotateTo.action(1,720)));
        }

        CCDelayTime ccDelayTime = CCDelayTime.action(0.85f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "launchEnd");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        runAction(ccSequence);
    }

    abstract public void launchEnd();

    public void noAtt() {
        noAttack = false;
    }

    public CGPoint getIntentPoint() {
        return intentPoint;
    }

    public void setIntentPoint(CGPoint intentPoint) {
        this.intentPoint = intentPoint;
    }

    public boolean isNoAttack() {
        return noAttack;
    }

    public void setNoAttack(boolean noAttack) {
        this.noAttack = noAttack;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }

    public int getHurt() {
        return hurt;
    }

    public void setHurt(int hurt) {
        this.hurt = hurt;
    }

    public PitcherButllet getBullet() {
        return bullet;
    }

    public void setBullet(PitcherButllet bullet) {
        this.bullet = bullet;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAttPath() {
        return attPath;
    }

    public void setAttPath(String attPath) {
        this.attPath = attPath;
    }

    public String getBuPath() {
        return buPath;
    }

    public void setBuPath(String buPath) {
        this.buPath = buPath;
    }

    public int getAttInt() {
        return attInt;
    }

    public void setAttInt(int attInt) {
        this.attInt = attInt;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isRotate() {
        return isRotate;
    }

    public void setRotate(boolean rotate) {
        isRotate = rotate;
    }
}
