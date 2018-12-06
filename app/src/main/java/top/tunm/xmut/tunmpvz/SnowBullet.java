package top.tunm.xmut.tunmpvz;


import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.effect.AEffect;

public class SnowBullet extends Bullet {

    public SnowBullet(ShooterPlant shooterPlant) {
        super("bullet/bullet2.png", shooterPlant);
        setAttack(10);//攻击力小
    }

    @Override
    public void showBulletBlast(Zombie zombie) {
        zombie.slow();

        AEffect aEffect = new AEffect("eff/SnowEff/Frame%02d.png" , 6);
        aEffect.setPosition(ccp(zombie.getPosition().x,zombie.getPosition().y));
        getParent().addChild(aEffect,6);
        CCDelayTime ccDelayTime = CCDelayTime.action(1.0f);
        CCHide ccHide = CCHide.action();
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccHide);
        aEffect.runAction(ccSequence);
    }
}
