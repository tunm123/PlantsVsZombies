package top.tunm.xmut.tunmpvz.bullet;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.plant.ShooterPlant;


public abstract class Bullet extends CCSprite {

    private int speed=300;
    private int attack=20;
    private ShooterPlant shooterPlant;
    private boolean isLeft;

    // 是否为火焰弹
    private boolean isFire = false;

    public Bullet(String filepath, ShooterPlant shooterPlant) {
        super(filepath);
        this.shooterPlant = shooterPlant;
        setPosition(shooterPlant.getPosition().x+20,
                shooterPlant.getPosition().y+50);
        shooterPlant.getParent().addChild(this,6);
        shooterPlant.getBullets().add(this);
        move();
    }

    public Bullet(String filepath, ShooterPlant shooterPlant,boolean isLeft) {
        super(filepath);
        this.shooterPlant = shooterPlant;
        setPosition(shooterPlant.getPosition().x+20,
                shooterPlant.getPosition().y+50);
        shooterPlant.getParent().addChild(this,6);
        shooterPlant.getBullets().add(this);
        this.isLeft = isLeft;
        move();
    }



    public void fire(){
        isFire = true;
        attack = 45;
        speed = 250;
        String path;
        if (isLeft){
            path = "bullet/PB11/bullet%02d.png";
        }else {
            path = "bullet/FireButtle/bullet%02d.png";
        }
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i< 2;i++){
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    path,i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        runAction(ccRepeatForever);

    }

    public void move(){
        CGPoint end=ccp(1400,getPosition().y);
        if (isLeft){
            end = ccp(-1400,getPosition().y);
        }
        float t= CGPointUtil.distance(getPosition(),end)/speed;
        CCMoveTo ccMoveTo=CCMoveTo.action(t,end);
        CCCallFunc ccCallFunc=CCCallFunc.action(this,"end");
        CCSequence ccSequence=CCSequence.actions(ccMoveTo,ccCallFunc);
        runAction(ccSequence);
    }

    public void end(){
        removeSelf();
        shooterPlant.getBullets().remove(this);

    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public abstract void showBulletBlast(Zombie zombie);//显示子弹的爆炸效果的抽象方法

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ShooterPlant getShooterPlant() {
        return shooterPlant;
    }

    public void setShooterPlant(ShooterPlant shooterPlant) {
        this.shooterPlant = shooterPlant;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }
}
