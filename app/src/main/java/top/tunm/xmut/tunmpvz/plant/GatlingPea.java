package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.bullet.PeaBullet;

/**
 * Created by jingyuyan on 2018/12/8.
 */

public class GatlingPea extends ShooterPlant {

    public GatlingPea() {
        super("plant/GatlingPea/frame%02d.png", 13);
        setPrice(250);
    }

    @Override
    public void createBullet(float t) {
        PeaBullet peaBullet = new PeaBullet(this);
        CCDelayTime time1 = CCDelayTime.action(0.3f);
        CCDelayTime time2 = CCDelayTime.action(0.3f);
        CCDelayTime time3 = CCDelayTime.action(0.3f);
        CCCallFunc func = CCCallFunc.action(this,"createBulletThree");
        CCSequence ccSequence = CCSequence.actions(time1,func,time2,func,time3,func);
        runAction(ccSequence);
    }

    public void createBulletThree(){
        PeaBullet peaBullet=new PeaBullet(this);
    }
}
