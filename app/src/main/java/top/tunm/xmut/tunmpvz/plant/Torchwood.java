package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import java.util.ArrayList;
import java.util.Iterator;

import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;

/**
 * Created by jingyuyan on 2018/12/6.
 */

// 火盆

public class Torchwood extends Plant{

    private boolean isFire = false;

    private int hurt = 60;

    public Torchwood() {
        super("plant/Torchwood/Frame%02d.png", 9);
        setPrice(175);
    }

    public void fire(ArrayList<Zombie> zombies) {
        isFire = true;
        AEffect aEffect = new AEffect("eff/fireBig/Frame%02d.png" , 8);
        aEffect.setPosition(ccp(getPosition().x+180,getPosition().y-80));
        getParent().addChild(aEffect,6);

        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()){
            Zombie zombie = zombieIterator.next();
            zombie.hurtCompute(hurt);
            if (zombie.getHP()==0){
                zombie.death(1);
                zombie.removeSelf();
                zombieIterator.remove();
            }
        }

        CCDelayTime ccDelayTime = CCDelayTime.action(10);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"isFire");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

    }

    public void openFire(){
        isFire = false;
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }
}
