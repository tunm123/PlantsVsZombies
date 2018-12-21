package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;

/**
 * Created by jingyuyan on 2018/12/17.
 */

public class MelonPult extends PitcherPlant{


    public MelonPult() {
        super("plant/MelonPult/Frame%02d.png", 9,"plant/MelonPult/Attack%02d.png",4,"bullet/melon.png");
        setHurt(80);
        setPrice(300);
        setRotate(true);
    }


    @Override
    public void launchEnd(){
        getBullet().removeSelf();
        CCDelayTime ccDelayTime = CCDelayTime.action(3f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"noAtt");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

        AEffect head = new AEffect("eff/boom3/effect_f01_%02d.png",9,9*0.1f,0.1f);
        head.setPosition(getIntentPoint());
        getParent().addChild(head,6);
        ToolsSet.effectSound(R.raw.bomb1);
        getZombie().hurtCompute(getHurt());
        if (getZombie().getHP() == 0) {
            getZombie().death(0);
            getZombie().removeSelf();
        }else {
            getZombie().stop("none",1);
        }



    }



}
