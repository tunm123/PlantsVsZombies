package top.tunm.xmut.tunmpvz.zombies;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.base.CCSpeed;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBlink;
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
import java.util.Locale;

import top.tunm.xmut.tunmpvz.layer.CombatLayer;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;


// 僵尸类
// 包含普通僵尸、路障僵尸、水桶僵尸、旗帜僵尸

public class Zombie extends CCSprite {
    private  CGPoint end;
    private  CGPoint start;
    private final CombatLayer combatLayer;
    private float speed=15;
    private int attack=10;
    private int HP=100;
    private State state;
    private boolean isSlow;
    private boolean noHurt;

    private String stand = ToolsSet.zombieStand;
    private String move = ToolsSet.zombieMove;
    private int moveInt = ToolsSet.zombieMoveInt;
    private String attacka = ToolsSet.zombieAttack;
    private int attackInt = ToolsSet.zombieAttackInt;

    private boolean sp;

    public boolean isSp() {
        return sp;
    }

    public void setSp(boolean sp) {
        this.sp = sp;
    }

    // 是否进行特殊移动
    private boolean isSm;

    private boolean isNoAttack;

    private boolean isLast = false;

    // 变成普通僵尸的标志
    private boolean isN = true;

    public enum State{
        MOVE,ATTACK
    }
    public Zombie (CombatLayer combatLayer, CGPoint start, CGPoint end) {
        super(ToolsSet.zombieStands);
        setAnchorPoint(0.5f, 0);
        setPosition(start);
        this.combatLayer = combatLayer;
        this.end = end;
        this.start = start;
        move();
    }

    public Zombie (CombatLayer combatLayer, CGPoint start, CGPoint end,int HP) {
        super(ToolsSet.zombieStands);
        setAnchorPoint(0.5f, 0);
        setPosition(start);
        this.combatLayer = combatLayer;
        this.end = end;
        this.start = start;
        // 水桶僵尸
        if (HP==600){
            isN = false;
            setHP(400);
            setSpeed(15);
            move = ToolsSet.bucketheadZombieMove;
            attacka = ToolsSet.bucketheadZombieAttack;
            moveInt = ToolsSet.bucketheadZombieInt;
            attackInt = ToolsSet.bucketheadZombieAttackInt;
        }else if(HP==300) {
            isN = false;
            setHP(200);
            setSpeed(15);
            move = ToolsSet.coneheadZombieMove;
            attacka = ToolsSet.coneheadZombieAttack;
            moveInt = ToolsSet.coneheadZombieInt;
            attackInt = ToolsSet.coneheadZombieAttackInt;
        }else if(HP==190){
            isN = false;
            setHP(190);
            setSpeed(15);
            move = ToolsSet.flagZombieMove;
            attacka = ToolsSet.flagZombieAttack;
            moveInt = ToolsSet.flagZombieInt;
            attackInt = ToolsSet.flagZombieAttackInt;
        }
        move();
    }


    public void move(){
        if (!stop) {
            float t = CGPointUtil.distance(getPosition(), end) / speed;
            CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
            CCCallFunc ccCallFunc = CCCallFunc.action(combatLayer, "end");
            CCSequence ccSequence = CCSequence.actions(ccMoveTo, ccCallFunc);
            if (isSlow) {
                CCSpeed ccSpeed = CCSpeed.action(ccSequence, 0.2f);
                runAction(ccSpeed);
            } else {
                runAction(ccSequence);
            }
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < moveInt; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        move, i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.1f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            if (isSlow) {
                CCSpeed ccSpeed = CCSpeed.action(ccRepeatForever, 0.2f);
                runAction(ccSpeed);
            } else {
                runAction(ccRepeatForever);
            }
            setState(State.MOVE);
        }
    }

    // 特殊移动
    public void move(CGPoint cgPoint,CGPoint end){
        if (!stop) {
            float t = 0.7f;
            isSm = true;
            this.end = end;
            CCMoveTo ccMoveTo = CCMoveTo.action(t, cgPoint);
            runAction(ccMoveTo);
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < moveInt; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        move, i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.05f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            runAction(ccRepeatForever);
            setState(State.MOVE);
            CCDelayTime ccDelayTime = CCDelayTime.action(0.7f);
            CCCallFunc ccCallFunc = CCCallFunc.action(this, "move");
            CCDelayTime ccDelayTime1 = CCDelayTime.action(0.1f);
            CCCallFunc ccCallFunc1 = CCCallFunc.action(this, "nosm");
            CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc, ccDelayTime1, ccCallFunc1);
            runAction(ccSequence);
        }
    }

    public void nosm(){
        isSm = false;
    }

    public void attack() {
        if (!stop) {
            if (!isNoAttack) {
                ArrayList<CCSpriteFrame> frames = new ArrayList<>();
                for (int i = 0; i < attackInt; i++) {
                    CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                            attacka, i)).displayedFrame();
                    frames.add(ccSpriteFrame);
                }
                CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.1f);
                CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
                CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
                if (isSlow) {
                    CCSpeed ccSpeed = CCSpeed.action(ccRepeatForever, 0.2f);
                    runAction(ccSpeed);
                } else {
                    runAction(ccRepeatForever);
                }
                setState(State.ATTACK);
            }
        }
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
//        CCBlink ccBlink = CCBlink.action(0.3f,3);
//        runAction(ccBlink);

        if (!isN){
            if (HP<=100){
                move = ToolsSet.zombieMove;
                attacka = ToolsSet.zombieAttack;
                moveInt = ToolsSet.zombieMoveInt;
                attackInt = ToolsSet.zombieAttackInt;
                move();
            }
        }

//        CCBlink ccBlink = CCBlink.action(0.3f,3);
//        runAction(ccBlink);


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

    private boolean stop;
    public void stop(String path,int xh){
        int bias;
        if (this instanceof NewspaperZombie){
            bias = 20;
        }else if(this instanceof PoleVaultingZombie){
            bias = -40;
        }else {
            bias = -27;
        }

        if (noHurt){
            return;
        }

        stop = true;
        setAttack(0);
        stopAllActions();
        if(!"none".equals(path)) {
            AEffect head = new AEffect(path,1,xh);
            head.setPosition(getPosition().x + 10,getPosition().y + 60);
            getParent().addChild(head,6);
        }
        CCDelayTime ccDelayTime = CCDelayTime.action(xh);
        CCCallFunc ccCallFunc=CCCallFunc.action(this,"restart");
        CCSequence ccSequence=CCSequence.actions(ccDelayTime,ccCallFunc);
        runAction(ccSequence);

        AEffect aEffect = new AEffect("eff/yun/battleBufferEffect1_100%02d.png" , 4,xh,0.1f);
        aEffect.setPosition(ccp(getPosition().x,getPosition().y+80));
        getParent().addChild(aEffect,6);
    }

    public void restart(){
        stop = false;
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

    public void noemal() {
        isSlow = false;
        setAttack(attack);
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
        isSlow = false;
        ccc3(255,255,255);
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

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public CGPoint getEnd() {
        return end;
    }

    public CombatLayer getCombatLayer() {
        return combatLayer;
    }

    public float getSpeed() {
        return speed;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public int getMoveInt() {
        return moveInt;
    }

    public void setMoveInt(int moveInt) {
        this.moveInt = moveInt;
    }

    public String getAttacka() {
        return attacka;
    }

    public void setAttacka(String attacka) {
        this.attacka = attacka;
    }

    public int getAttackInt() {
        return attackInt;
    }

    public void setAttackInt(int attackInt) {
        this.attackInt = attackInt;
    }

    public boolean isN() {
        return isN;
    }

    public void setN(boolean n) {
        isN = n;
    }

    public boolean isNoAttack() {
        return isNoAttack;
    }

    public void setNoAttack(boolean noAttack) {
        isNoAttack = noAttack;
    }

    public boolean isSm() {
        return isSm;
    }

    public void setSm(boolean sm) {
        isSm = sm;
    }

    public void setEnd(CGPoint end) {
        this.end = end;
    }

    public CGPoint getStart() {
        return start;
    }

    public void setStart(CGPoint start) {
        this.start = start;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isNoHurt() {
        return noHurt;
    }

    public void setNoHurt(boolean noHurt) {
        this.noHurt = noHurt;
    }
}
