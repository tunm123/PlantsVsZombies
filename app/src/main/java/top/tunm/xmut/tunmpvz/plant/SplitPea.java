package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.bullet.PeaBullet;


public class SplitPea extends ShooterPlant{
    public SplitPea() {
        super("plant/SplitPea/Frame%02d.png", 14);
        setPrice(125);
    }

    @Override
    public void createBullet(float t) {
        PeaBullet peaBullet=new PeaBullet(this);
        PeaBullet peaBulletLeft=new PeaBullet(this,"bullet/bulletLeft.png",true);
    }


}
