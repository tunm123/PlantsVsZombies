package top.tunm.xmut.tunmpvz.bullet;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.plant.ShooterPlant;

/**
 * Created by jingyuyan on 2018/12/7.
 */

public class ThreeBullet extends Bullet {

    private int attRow ;
    private CGPoint intentPoint;


    public ThreeBullet(ShooterPlant shooterPlant,int row) {
        super("bullet/bullet1.png", shooterPlant);
        attRow = row;
        System.out.println("意图 创建成功 "+row);
        move();
    }

    public ThreeBullet(ShooterPlant shooterPlant , CGPoint cgPoint,int row) {
        super("bullet/bullet1.png", shooterPlant);
        this.intentPoint = cgPoint;
        attRow = row;
        System.out.println("意图 创建成功 "+row);
        reAng();
    }

    public void reAng(){
        CGPoint end = ccp(intentPoint.x, intentPoint.y);
        float t = CGPointUtil.distance(getPosition(), end) / getSpeed();
        CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
        runAction(ccMoveTo);

        CCDelayTime ccDelayTime = CCDelayTime.action(t);
        CCCallFunc ccCallFunc1 = CCCallFunc.action(this,"move");
        CCSequence ccSequence1 = CCSequence.actions(ccDelayTime,ccCallFunc1);
        runAction(ccSequence1);
    }




    @Override
    public void showBulletBlast(Zombie zombie) {
        if (isFire()){
            AEffect aEffect = new AEffect("eff/fireBomb/Frame%02d.png", 5,0.8f,0.16f);
            aEffect.setPosition(ccp(zombie.getPosition().x, zombie.getPosition().y-40));
            getParent().addChild(aEffect, 6);
            CCDelayTime ccDelayTime = CCDelayTime.action(0.8f);
            CCHide ccHide = CCHide.action();
            CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccHide);
            aEffect.runAction(ccSequence);
            ToolsSet.effectSound(R.raw.bomb1);
        }else {
            AEffect aEffect = new AEffect("eff/pea/eff%02d.png", 3,0.3f,0.1f);
            aEffect.setPosition(ccp(zombie.getPosition().x, zombie.getPosition().y));
            getParent().addChild(aEffect, 6);
            CCDelayTime ccDelayTime = CCDelayTime.action(1.0f);
            CCHide ccHide = CCHide.action();
            CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccHide);
            aEffect.runAction(ccSequence);
            ToolsSet.effectSound(R.raw.fight);
        }
    }

    public int getAttRow() {
        return attRow;
    }

    public void setAttRow(int attRow) {
        this.attRow = attRow;
    }
}
