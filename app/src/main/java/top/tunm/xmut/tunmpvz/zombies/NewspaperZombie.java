package top.tunm.xmut.tunmpvz.zombies;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.layer.CombatLayer;

/**
 * Created by jingyuyan on 2018/12/13.
 */

// 读报僵尸

public class NewspaperZombie extends Zombie {

    private boolean isLast;
    private boolean isLastShow;


    public NewspaperZombie(CombatLayer combatLayer, CGPoint start, CGPoint end) {
        super(combatLayer, start, end);
        setMoveInt(ToolsSet.newspaperZombieIntB);
        setMove(ToolsSet.newspaperZombieMoveB);
        setAttackInt(ToolsSet.newspaperZombieAttackIntB);
        setAttacka(ToolsSet.newspaperZombieAttackB);
        setHP(500);
        setAttack(15);
        setSpeed(15);
        move();
    }

    @Override
    public void hurtCompute(int hurt){
        setHP(getHP()-hurt);
        if (getHP()<0){
            setHP(0);
        }
        if (getHP()<=200){
            if (!isLast){
                isLast = true;
                ArrayList<CCSpriteFrame> frames=new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                            "zombies/zombies_1/NewspaperZombie/last%02d.png",i)).displayedFrame();
                    frames.add(ccSpriteFrame);
                }
                setMoveInt(ToolsSet.newspaperZombieIntA);
                setMove(ToolsSet.newspaperZombieMoveA);
                setAttackInt(ToolsSet.newspaperZombieAttackIntA);
                setAttacka(ToolsSet.newspaperZombieAttackA);

                CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,0.1f);
                CCAnimate ccAnimate= CCAnimate.action(ccAnimation,true);
                CCRepeatForever ccRepeatForever= CCRepeatForever.action(ccAnimate);
                runAction(ccRepeatForever);

                setSpeed(15);

                CCDelayTime ccDelayTime = CCDelayTime.action(1);
                CCCallFunc ccCallFunc = CCCallFunc.action(this,"move");
                CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
                runAction(ccSequence);
            }
        }

    }

    @Override
    // 僵尸死亡效果
    public void death(int dieState){
        switch (dieState){
            // 默认死法，头掉倒地
            case 0:
                AEffect aEffect = new AEffect("zombies/zombies_1/NewspaperZombie/die%02d.png",11,2,0.181f);
                aEffect.setPosition(ccp(getPosition().x,getPosition().y));
                getParent().addChild(aEffect,6);

                // 头掉了
                AEffect head = new AEffect("zombies/zombies_1/NewspaperZombie/head%02d.png",10,1.2f,0.12f);
                head.setPosition(ccp(getPosition().x+20,getPosition().y-30));
                getParent().addChild(head,6);

                break;

            // 被烧焦了
            case 1:
                AEffect aEffect2 = new AEffect("zombies/zombies_1/BoomDie/Frame%02d.png",20,4,0.2f);
                aEffect2.setPosition(ccp(getPosition().x,getPosition().y));
                getParent().addChild(aEffect2,6);

            default:
                break;
        }

//        switch (dieState){
//            // 普通死法
//            case 0:
//
//                break;
//            default:
//                break;
//        }
    }
}
