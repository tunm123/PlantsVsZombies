package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Iterator;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/18.
 */

public class Plantern extends Plant {

    private boolean isAtt;
    private int hurt;
    private CCSprite mj;
    private ArrayList<Zombie> zombies;
    private ArrayList<Zombie> removeZombies;
    private float xh;
    private boolean islu;

    public Plantern(){
        super("plant/Plantern/Frame%02d.png",19);
        setPrice(120);
        hurt = 50;
        removeZombies = new ArrayList<>();
        zombies = new ArrayList<>();
        xh = 2;

    }

    public void launch(ArrayList<Zombie> zombies){
        this.zombies = zombies;
        AEffect line = new AEffect("eff/lightLine/Frame%02d.png",9,1.2f,1.2f/9f);
        line.setPosition(getPosition().x + 140,getPosition().y - 60);
        getParent().addChild(line,6);
        isAtt = true;

        mj = CCSprite.sprite("bullet/bullet1.png");
        getParent().addChild(mj);
        mj.setOpacity(0);
        mj.setPosition(getPosition());
        CCMoveTo ccMoveTo = CCMoveTo.action(1.0f,ccp(getPosition().x + 340,getPosition().y));
        mj.runAction(ccMoveTo);

        xh = 0;
        islu = true;
        CCScheduler.sharedScheduler().schedule("range", this, 0.05f, false);
        CCDelayTime ccDelayTime = CCDelayTime.action(6);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"reAtt");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);
    }


    public void range(float t){
//        System.out.println("xh:"+xh);
        if (xh>=1.2){
//            if (islu) {
                mj.removeSelf();
                removeZombies.clear();
                islu = false;
            CCScheduler.sharedScheduler().unschedule("range", this);
//            }
        }else {
            for (Zombie zombie:zombies) {
                if (!removeZombies.contains(zombie)){
                    if (CGPoint.ccpDistance(mj.getPosition(),zombie.getPosition())<=20 && zombie.getPosition().x > getPosition().x) {
                        AEffect aEffect = new AEffect("eff/star/eff%02d.png", 5, 0.6f, 0.12f);
                        aEffect.setPosition(ccp(zombie.getPosition().x, zombie.getPosition().y - 40));
                        getParent().addChild(aEffect, 6);
                        ToolsSet.effectSound(R.raw.bomb1);
                        zombie.hurtCompute(getHurt());
                        if (zombie.getHP() == 0) {
                            zombie.death(0);
                            zombie.removeSelf();
                        }
                        removeZombies.add(zombie);
                    }
                }
            }
            xh += 0.05f;
        }
    }


    public void reAtt(){
        isAtt = false;
    }

    public boolean isAtt() {
        return isAtt;
    }

    public void setAtt(boolean att) {
        isAtt = att;
    }

    public int getHurt() {
        return hurt;
    }

    public void setHurt(int hurt) {
        this.hurt = hurt;
    }
}
