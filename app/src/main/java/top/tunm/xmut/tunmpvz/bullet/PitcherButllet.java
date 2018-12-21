package top.tunm.xmut.tunmpvz.bullet;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSprite;

/**
 * Created by jingyuyan on 2018/12/20.
 */

public class PitcherButllet extends CCSprite {

    public PitcherButllet(String path , float t){
        super(path);
        CCDelayTime ccDelayTime = CCDelayTime.action(t);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"selfRemove");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

    }

    public void selfRemove(){
        removeSelf();
    }

}
