package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/17.
 */

public class CabbagePult extends Plant{
    private CGPoint intentPoint;
    private boolean noAttack;
    private CCSprite bullet;

    public CabbagePult() {
        super("plant/CabbagePult/Frame%02d.png", 6);
        setPrice(100);
    }


    public void launch(){
        if (!noAttack){
            noAttack = true;
            bullet = CCSprite.sprite("bullet/cabbage.png");
//            bullet.setPosition();
        }
    }

    public CGPoint getIntentPoint() {
        return intentPoint;
    }

    public void setIntentPoint(CGPoint intentPoint) {
        this.intentPoint = intentPoint;
    }

    public boolean isNoAttack() {
        return noAttack;
    }

    public void setNoAttack(boolean noAttack) {
        this.noAttack = noAttack;
    }
}
