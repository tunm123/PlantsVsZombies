package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/17.
 */

public class CabbagePult extends PitcherPlant{

    public CabbagePult() {
        super("plant/CabbagePult/Frame%02d.png", 18,"plant/CabbagePult/Attack%02d.png",8,"bullet/cabbage.png");
        setPrice(100);
    }

    @Override
    public void launchEnd(){
        getBullet().removeSelf();
        CCDelayTime ccDelayTime = CCDelayTime.action(2.9f);
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
        }

    }
}
