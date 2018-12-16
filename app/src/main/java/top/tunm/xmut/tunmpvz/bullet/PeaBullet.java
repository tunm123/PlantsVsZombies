package top.tunm.xmut.tunmpvz.bullet;

import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.plant.ShooterPlant;


public class PeaBullet extends Bullet {

    public PeaBullet(ShooterPlant shooterPlant) {
        super("bullet/bullet1.png", shooterPlant);
    }

    public PeaBullet(ShooterPlant shooterPlant,String path,Boolean isLeft){
        super(path, shooterPlant,isLeft);
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
}
