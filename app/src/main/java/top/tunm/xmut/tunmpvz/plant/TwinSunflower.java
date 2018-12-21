package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.layer.CombatLayer;
import top.tunm.xmut.tunmpvz.bullet.Sun;

/**
 * Created by jingyuyan on 2018/12/8.
 */

public class TwinSunflower extends Plant {
    private Sun sun;

    private int ang = 1;

    public TwinSunflower() {
        super("plant/TwinSunflower/Frame%02d.png", 20);
        CCScheduler.sharedScheduler().schedule("createSun",this,10,false);
        CCScheduler.sharedScheduler().schedule("createSun",this,10.1f,false);
        setPrice(150);

    }



    public void createSun(float t){
        ang = ang * -1;
        sun = new Sun();
        sun.setPosition(getPosition().x - 100 , getPosition().y + 40);
        getParent().getParent().addChild(sun);
        CCJumpTo ccJumpTo = CCJumpTo.action(0.5f,
                ccp(50*ang+(getPosition().x - 100) , getPosition().y ),40,1);
//        CCCallFunc ccCallFunc1 = CCCallFunc.action(this,"addSun");
        ((CombatLayer)getParent().getParent()).addSun(sun);
        CCDelayTime ccDelayTime = CCDelayTime.action(5);
        CCCallFunc ccCallFunc2 = CCCallFunc.action(this,"removeSun");
        CCSequence ccSequence = CCSequence.actions(ccJumpTo  , ccDelayTime,ccCallFunc2);
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
