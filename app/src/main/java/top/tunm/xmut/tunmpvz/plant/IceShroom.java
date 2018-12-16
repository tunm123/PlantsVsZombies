package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import java.util.ArrayList;
import java.util.Iterator;

import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;

/**
 * Created by jingyuyan on 2018/12/8.
 */

public class IceShroom extends Plant {

    private boolean isBoom = false;
    private int bombHurt = 20;

    private ArrayList<Zombie> zombies;

    public IceShroom(){
        super("plant/IceShroom/Frame%02d.png",9);
        setPrice(75);
        setHP(1);
    }

    public void boom(ArrayList<Zombie> zombies){
        this.zombies = zombies;
        isBoom = true;

        // 播放动画
        AEffect aEffect = new AEffect("eff/ice1/Frame%02d.png" , 7);
        aEffect.setPosition(ccp(getPosition().x,getPosition().y-20));
        getParent().addChild(aEffect,6);

        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()){
            Zombie zombie = zombieIterator.next();
            zombie.hurtCompute(getBombHurt());
            if (zombie.getHP()==0){
                zombie.death(0);
                zombie.removeSelf();
                zombieIterator.remove();
            }else {
                zombie.setSlow(true);
            }
        }


        CCDelayTime ccDelayTime = CCDelayTime.action(4f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"re");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
        removeSelf();
    }

    public void re() {

        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();
            zombie.hurtCompute(getBombHurt());
            zombie.noemal(4);

        }

    }

    public boolean isBoom() {
        return isBoom;
    }

    public void setBoom(boolean boom) {
        isBoom = boom;
    }

    public int getBombHurt() {
        return bombHurt;
    }

    public void setBombHurt(int bombHurt) {
        this.bombHurt = bombHurt;
    }
}
