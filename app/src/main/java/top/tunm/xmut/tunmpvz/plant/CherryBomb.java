package top.tunm.xmut.tunmpvz.plant;


import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import java.util.ArrayList;
import java.util.Iterator;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.zombies.Zombie;
import top.tunm.xmut.tunmpvz.effect.AEffect;

public class CherryBomb extends Plant{

    private ArrayList<Zombie> zombies;

    private int BombHurt = 2000;

    public int getBombHurt() {
        return BombHurt;
    }

    public void setBombHurt(int bombHurt) {
        BombHurt = bombHurt;
    }

    public CherryBomb() {
        super("plant/CherryBomb/Frame%02d.png", 7);
        setHP(1);
        setPrice(150);
        zombies = new ArrayList<>();
    }



    public void bomb(){
        CCDelayTime ccDelayTime = CCDelayTime.action(1);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"rangeHurt");
        CCDelayTime ccDelayTime2 = CCDelayTime.action(1.0f);
        CCHide ccHide = CCHide.action();
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc,ccDelayTime2,ccHide);

        runAction(ccSequence);
    }

    public void rangeHurt(){


        // 延迟性伤害
        Iterator<Zombie> zombieIterator = zombies.iterator();
        while (zombieIterator.hasNext()){
            Zombie zombie = zombieIterator.next();
            zombie.hurtCompute(getBombHurt());
            if (zombie.getHP()==0){
                zombie.death(1);
                zombie.removeSelf();
                zombieIterator.remove();
            }
        }

        System.out.println("cherry boom!");
        AEffect aEffect = new AEffect("eff/bomb/Frame%02d.png" , 5);
        aEffect.setPosition(ccp(getPosition().x,getPosition().y-20));
        getParent().addChild(aEffect,6);
        aEffect.setScale(1.6f);
        ToolsSet.effectSound(R.raw.bomb);
        removeSelf();
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }
}
