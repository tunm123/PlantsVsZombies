package top.tunm.xmut.tunmpvz;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.base.CCSpeed;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCTintTo;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import top.tunm.xmut.tunmpvz.effect.AEffect;


// 僵尸类
// 包含普通僵尸、路障僵尸和水桶僵尸

public class Zombie extends CCSprite {
    private final CGPoint end;
    private final CombatLayer combatLayer;
    private float speed=20;
    private int attack=10;
    private int HP=100;
    private State state;
    private boolean isSlow;

    private String stand = ToolsSet.zombieStand;
    private String move = ToolsSet.zombieMove;
    private int moveInt = ToolsSet.zombieMoveInt;
    private String attacka = ToolsSet.zombieAttack;
    private int attackInt = ToolsSet.zombieAttackInt;


    // 变成普通僵尸的标志
    private boolean isN = true;

    public enum State{
        MOVE,ATTACK
    }
    public Zombie (CombatLayer combatLayer, CGPoint start, CGPoint end) {
        super(ToolsSet.zombieStand);
        setAnchorPoint(0.5f, 0);
        setPosition(start);
        this.combatLayer = combatLayer;
        this.end = end;
        move();
    }

    public Zombie (CombatLayer combatLayer, CGPoint start, CGPoint end,int HP) {
        super(ToolsSet.zombieStand);
        setAnchorPoint(0.5f, 0);
        setPosition(start);
        this.combatLayer = combatLayer;
        this.end = end;
        // 水桶僵尸
        if (HP==600){
            isN = false;
            setHP(600);
            move = ToolsSet.bucketheadZombieMove;
            attacka = ToolsSet.bucketheadZombieAttack;
            moveInt = ToolsSet.bucketheadZombieInt;
            attackInt = ToolsSet.bucketheadZombieAttackInt;
        }else {
            isN = false;
            setHP(300);
            move = ToolsSet.coneheadZombieMove;
            attacka = ToolsSet.coneheadZombieAttack;
            moveInt = ToolsSet.coneheadZombieInt;
            attackInt = ToolsSet.coneheadZombieAttackInt;
        }
        move();
    }


    public void move(){
        float t= CGPointUtil.distance(getPosition(),end)/speed;
        CCMoveTo ccMoveTo= CCMoveTo.action(t,end);
        CCCallFunc ccCallFunc= CCCallFunc.action(combatLayer,"end");
        CCSequence ccSequence= CCSequence.actions(ccMoveTo,ccCallFunc);
        if (isSlow){
            CCSpeed ccSpeed=CCSpeed.action(ccSequence,0.2f);
            runAction(ccSpeed);
        }else {
            runAction(ccSequence);
        }
        ArrayList<CCSpriteFrame> frames=new ArrayList<>();
        for (int i = 0; i < moveInt; i++) {
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    move,i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate= CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever= CCRepeatForever.action(ccAnimate);
        if (isSlow){
            CCSpeed ccSpeed=CCSpeed.action(ccRepeatForever,0.2f);
            runAction(ccSpeed);
        }else {
            runAction(ccRepeatForever);
        }
        setState(State.MOVE);
    }

    public void attack(){
        ArrayList<CCSpriteFrame> frames=new ArrayList<>();
        for (int i = 0; i < attackInt; i++) {
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    attacka,i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,0.1f);
        CCAnimate ccAnimate= CCAnimate.action(ccAnimation,true);
        CCRepeatForever ccRepeatForever= CCRepeatForever.action(ccAnimate);
        if (isSlow){
            CCSpeed ccSpeed=CCSpeed.action(ccRepeatForever,0.2f);
            runAction(ccSpeed);
        }else {
            runAction(ccRepeatForever);
        }
        setState(State.ATTACK);
    }


    // 僵尸死亡效果
    public void death(int dieState){
        switch (dieState){
            // 默认死法，头掉倒地
            case 0:
                AEffect aEffect = new AEffect("zombies/zombies_1/ZombieDie/Frame%02d.png",10,2);
                aEffect.setPosition(ccp(getPosition().x,getPosition().y));
                getParent().addChild(aEffect,6);

                // 头掉了
                AEffect head = new AEffect("zombies/zombies_1/ZombieHead/Frame%02d.png",12,1.2f,0.1f);
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


    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state=state;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void hurtCompute(int hurt){
        HP -= hurt;
        if (HP<0){
            HP=0;
        }

        if (!isN){
            if (HP<=100){
                move = ToolsSet.zombieMove;
                attacka = ToolsSet.zombieAttack;
                moveInt = ToolsSet.zombieMoveInt;
                attackInt = ToolsSet.zombieAttackInt;
                move();
            }
        }

    }

    public void slow(){
        isSlow=true;
        setAttack(2);
        stopAllActions();
        switch (getState()){
            case MOVE:
                move();
                break;
            case ATTACK:
                attack();
                break;
            default:
                break;
        }
        CCTintTo ccTintTo1=CCTintTo.action(0.1f,ccc3(150,150,255));
        CCDelayTime ccDelayTime=CCDelayTime.action(3);
        CCTintTo ccTintTo2=CCTintTo.action(0.1f,ccc3(255,255,255));
        CCCallFunc ccCallFunc=CCCallFunc.action(this,"normal");
        CCSequence ccSequence=CCSequence.actions(ccTintTo1,ccDelayTime,ccTintTo2,ccCallFunc);
        runAction(ccSequence);


    }

    public void slow(float time){
        isSlow=true;
        setAttack(2);
        stopAllActions();
        switch (getState()){
            case MOVE:
                move();
                break;
            case ATTACK:
                attack();
                break;
            default:
                break;
        }

    }

    public void noemal() {
        isSlow = false;
        setAttack(10);
        stopAllActions();
        switch (getState()) {
            case MOVE:
                move();
                break;
            case ATTACK:
                attack();
                break;
            default:
                break;
        }

    }

    public void noemal(float t){
        ccc3(255,255,255);
        isSlow = false;
        setAttack(10);
        stopAllActions();
        switch (getState()) {
            case MOVE:
                move();
                break;
            case ATTACK:
                attack();
                break;
            default:
                break;
        }
    }

    public boolean isSlow() {
        return isSlow;
    }

    public void setSlow(boolean slow) {
        isSlow = slow;
    }
}
