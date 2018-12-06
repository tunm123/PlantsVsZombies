package top.tunm.xmut.tunmpvz;

import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSprite;

import top.tunm.xmut.tunmpvz.effect.AEffect;


public class PeaBullet extends Bullet {

    public PeaBullet(ShooterPlant shooterPlant) {
        super("bullet/bullet1.png", shooterPlant);
    }

    @Override
    public void showBulletBlast(Zombie zombie) {
        AEffect aEffect = new AEffect("eff/GreenEff/Frame%02d.png" , 15);
        aEffect.setPosition(ccp(zombie.getPosition().x,zombie.getPosition().y));
        getParent().addChild(aEffect,6);
        CCDelayTime ccDelayTime = CCDelayTime.action(1.0f);
        CCHide ccHide = CCHide.action();
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccHide);
        aEffect.runAction(ccSequence);
    }
}
