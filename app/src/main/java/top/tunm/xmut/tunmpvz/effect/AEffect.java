package top.tunm.xmut.tunmpvz.effect;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;

/**
 * Created by jingyuyan on 2018/12/5.
 */

public class AEffect extends Effect {

    private float removetime = 1;

    public AEffect() {
        super("eff/Aeff/Frame%02d.png", 5);
        setPrice(0);
    }

    public AEffect(String filepath,int i){
        super(filepath,i);
        removemine();
        setPrice(0);
    }

    public AEffect(String filepath,int i,int s){
        super(filepath,i);
        removetime = s;
        removemine();
        setPrice(0);

    }

    public AEffect(String filepath,int i,int s,float delay){
        super(filepath,i,delay);
        removetime = s;
        removemine();
        setPrice(0);
    }

    public AEffect(String filepath,int i,float s,float delay){
        super(filepath,i,delay);
        removetime = s;
        removemine();
        setPrice(0);
    }


    public void removemine(){
        CCDelayTime ccDelayTime = CCDelayTime.action(removetime);
        CCCallFunc ccCallFunc = CCCallFunc.action(this,"remove");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

//        CCScheduler.sharedScheduler().schedule("attackPlant",this,1,
//                false);
    }

    public void remove(){

        removeSelf();
    }

}
