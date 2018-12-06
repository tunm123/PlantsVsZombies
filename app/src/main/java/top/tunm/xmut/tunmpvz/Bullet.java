package top.tunm.xmut.tunmpvz;

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


public abstract class Bullet extends CCSprite {

    private int speed=200;
    private int attack=20;
    private ShooterPlant shooterPlant;

    public Bullet(String filepath, ShooterPlant shooterPlant) {
        super(filepath);
        this.shooterPlant = shooterPlant;
        setPosition(shooterPlant.getPosition().x+20,
                shooterPlant.getPosition().y+50);
        shooterPlant.getParent().addChild(this,6);
        shooterPlant.getBullets().add(this);
        move();
    }



    public void move(){
        CGPoint end=ccp(1400,getPosition().y);
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
}
