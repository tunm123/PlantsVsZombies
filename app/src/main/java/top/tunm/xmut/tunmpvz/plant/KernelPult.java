package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import java.util.Random;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;

/**
 * Created by jingyuyan on 2018/12/17.
 */

public class KernelPult extends PitcherPlant{

    private boolean isYellow;

    public KernelPult() {
        super("plant/KernelPult/Frame%02d.png", 9,"plant/KernelPult/Attack%02d.png",4,"bullet/kernelBullet.png");
        setPrice(100);
    }




    @Override
    public void launchEnd(){
        getBullet().removeSelf();
        CCDelayTime ccDelayTime = CCDelayTime.action(7f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"noAtt");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

        AEffect head = new AEffect("eff/boom3/effect_f01_%02d.png",9,9*0.1f,0.1f);
        head.setPosition(getIntentPoint());
        getParent().addChild(head,6);
        ToolsSet.effectSound(R.raw.bomb1);
        if (getZombie().getHP()!=0 && isYellow) {
            setHurt(80);
            getZombie().stop("bullet/yelloed.png", 4);
        }
        getZombie().hurtCompute(getHurt());
        if (getZombie().getHP() == 0) {
            getZombie().death(0);
            getZombie().removeSelf();
        }





    }

    @Override
    public void noAtt(){
        isYellow = false;
        setNoAttack(false);
    }

    public boolean isYellow() {
        return isYellow;
    }

    public void setYellow(boolean yellow) {
        isYellow = yellow;
    }
}
