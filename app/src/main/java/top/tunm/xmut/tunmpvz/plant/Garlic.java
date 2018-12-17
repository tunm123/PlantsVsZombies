package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Random;

import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/16.
 */

public class Garlic extends Plant {

    // 是否散发出味道
    private boolean isSmelled;

    public Garlic(){
        super("plant/Garlic/Frame%02d.png",12);
        setPrice(50);
    }

    public void smells(Zombie zombie, int row,int col, ArrayList<Zombie> zombies){
        isSmelled = true;
        Random random = new Random();
        CGPoint intentPoint;
        int index;
        System.out.println("当前行："+row);
        // 意图点0：为上，1为下
        int intent = random.nextInt(2);
        if (row == 4){
            System.out.println("无上界");
            intent = 1;
        }else if (row == 0){
            System.out.println("无下界");
            intent = 0;
        }
        if(intent == 1){
//            intentPoint = CGPoint.ccp(zombie.getPosition().x,zombie.getPosition().y + 100);
            index = row - 1;
        }else {
            index = row + 1;
        }
        intentPoint = ToolsSet.currtCombatLayer.getCgPoints_towers().get(index).get(col);
        CGPoint intentEnd = ToolsSet.currtCombatLayer.getCgPoints_path().get(2 * index + 1);
        zombies.remove(zombie);
        ToolsSet.zombieArrays.get(index).add(zombie);
        CCMoveTo ccMoveTo = CCMoveTo.action(1f,intentPoint);
        zombie.runAction(ccMoveTo);
        zombie.move(intentPoint,intentEnd);

        AEffect aEffect = new AEffect("eff/wocao/eff%02d.png", 4,0.6f,0.15f);
        aEffect.setPosition(ccp(zombie.getPosition().x - 20, zombie.getPosition().y+50));
        getParent().addChild(aEffect, 6);

        AEffect aEffect2 = new AEffect("eff/du2/eff%02d.png", 14,1f,0.074f);
        aEffect2.setPosition(ccp(getPosition().x, getPosition().y - 20));
        getParent().addChild(aEffect2, 6);

        CCDelayTime ccDelayTime = CCDelayTime.action(6);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"setSmell");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }

    public void setSmell(){
        isSmelled = false;
    }

    public boolean isSmelled() {
        return isSmelled;
    }

    public void setSmelled(boolean smelled) {
        isSmelled = smelled;
    }
}
