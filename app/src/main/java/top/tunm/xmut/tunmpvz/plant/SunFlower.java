package top.tunm.xmut.tunmpvz.plant;


import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.layer.CombatLayer;
import top.tunm.xmut.tunmpvz.bullet.Sun;

public class SunFlower extends Plant{

    private Sun sun;

    public SunFlower() {
        super("plant/SunFlower/Frame%02d.png", 18);
        CCScheduler.sharedScheduler().schedule("createSun",this,10,false);
        setPrice(50);
    }

    public void createSun(float t){
        sun = new Sun();
        sun.setPosition(getPosition().x - 100 , getPosition().y + 40);
        getParent().getParent().addChild(sun);
        CCJumpTo ccJumpTo = CCJumpTo.action(0.5f,
                ccp(getPosition().x - 100 , getPosition().y ),40,1);
        CCCallFunc ccCallFunc1 = CCCallFunc.action(this,"addSun");
        CCDelayTime ccDelayTime = CCDelayTime.action(5);
        CCCallFunc ccCallFunc2 = CCCallFunc.action(this,"removeSun");
        CCSequence ccSequence = CCSequence.actions(ccJumpTo , ccCallFunc1 , ccDelayTime,ccCallFunc2);
        sun.runAction(ccSequence);
    }

    public void addSun(){
        ((CombatLayer)getParent().getParent()).addSun(sun);
    }

    public void removeSun(){
        ((CombatLayer)getParent().getParent()).removeSun(sun);
        if(!sun.isNowCollect()){
            sun.removeSelf();
        }
    }
}
