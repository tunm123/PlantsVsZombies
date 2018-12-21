package top.tunm.xmut.tunmpvz.zombies;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;
import java.util.Random;

import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.layer.CombatLayer;
import top.tunm.xmut.tunmpvz.layer.CombatLine;
import top.tunm.xmut.tunmpvz.plant.Plant;

/**
 * Created by jingyuyan on 2018/12/18.
 */

public class JokerZombie extends Zombie {

    private CGPoint cgPoint;

    public JokerZombie(CombatLayer combatLayer, CGPoint start, CGPoint end) {
        super(combatLayer, start, end);
        setMoveInt(ToolsSet.jokerZombieInt);
        setMove(ToolsSet.jokerZombieMove);
        setAttackInt(ToolsSet.jokerZombieAttackInt);
        setAttacka(ToolsSet.jokerZombieAttack);
        setHP(450);
        setAttack(10);
        setSpeed(30);
        move();
        CCScheduler.sharedScheduler().schedule("trigger", this, 0.5f, false);
    }


    public void bomb() {
        cgPoint = getPosition();
        AEffect aEffect = new AEffect("zombies/zombies_1/JokerZombie/bomb%02d.png", 7, 1.5f, 1.5f / 7f);
        aEffect.setPosition(cgPoint.x, cgPoint.y - 20);
        getParent().addChild(aEffect, 6);

        AEffect wocao = new AEffect("eff/wocao/eff%02d.png", 4, 1.5f, 1.5f / 4f);
        aEffect.setPosition(ccp(getPosition().x, getPosition().y + 50));
        getParent().addChild(wocao, 6);


        CCDelayTime ccDelayTime = CCDelayTime.action(1.3f);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "boom");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        runAction(ccSequence);

    }

    public void boom() {
        AEffect aEffect = new AEffect("eff/bomb2/Frame%02d.png", 5, 0.8f, 0.8f / 5);
        aEffect.setPosition(cgPoint.x, cgPoint.y);
        aEffect.setScale(3);
        getParent().addChild(aEffect, 6);

        int col = (int) (cgPoint.x - 220) / 105;
        int row = (int) (cgPoint.y - 40) / 120;
        int baseCol = col - 1;
        int baseRow = row - 1;
        int maxCol = baseCol + 3;
        int maxRow = baseRow + 3;
        System.out.println("小丑爆炸点：" + row + ":" + col);
        System.out.println("小丑爆炸基准点：" + baseRow + ":" + baseCol);
        for (int c = baseCol; c < maxCol; c++) {
            for (int r = baseRow; r < maxRow; r++) {
                if (c<9 && c>=0 && r<5 && r>=0){
                System.out.println("小丑爆炸涉及点:" + r + ":" + c);
                if (ToolsSet.currtCombatLayer.getCombatLines().get(r).isContainPlant(c)) {
                    System.out.println("小丑爆炸 有植物");
                    Plant plant = ToolsSet.currtCombatLayer.getCombatLines().get(r).getPlants().get(col);
                    if (plant != null) {
                        System.out.println("小丑爆炸锁定植物："+plant.getPosition().x+":"+plant.getPosition().y);
                        plant.setHP(0);
                        ToolsSet.currtCombatLayer.getCombatLines().get(r).getPlants().remove(col);
                        plant.setRemove(true);
                        plant.removeSelf();
                    }

                    }
                }
            }
        }
        setHP(0);
        removeSelf();

    }

    public void trigger(float t) {
        Random random = new Random();
        float dis = Math.abs(getEnd().x - getStart().x);
        float currDis = Math.abs(getEnd().x - getPosition().x);
        float tiggerRate = currDis / dis;
        float range = random.nextFloat();
        System.out.println("小丑爆炸概率" + (1 - tiggerRate));
        if ((1 - tiggerRate) >= range && (1 - tiggerRate) >= 0.2) {
            System.out.println("触发小丑爆炸！");
            bomb();
            CCScheduler.sharedScheduler().unschedule("trigger", this);
        }

    }

    @Override
    // 僵尸死亡效果
    public void death(int dieState) {
        switch (dieState) {
            // 默认死法，头掉倒地
            case 0:
                AEffect aEffect = new AEffect("zombies/zombies_1/JokerZombie/die%02d.png", 9, 1.5f, 1.5f / 9f);
                aEffect.setPosition(ccp(getPosition().x, getPosition().y));
                getParent().addChild(aEffect, 6);

                // 头掉了
                AEffect head = new AEffect("zombies/zombies_1/NewspaperZombie/head%02d.png", 10, 1.2f, 0.12f);
                head.setPosition(ccp(getPosition().x + 20, getPosition().y - 30));
                getParent().addChild(head, 6);

                break;

            // 被烧焦了
            case 1:
                AEffect aEffect2 = new AEffect("zombies/zombies_1/BoomDie/Frame%02d.png", 20, 4, 0.2f);
                aEffect2.setPosition(ccp(getPosition().x, getPosition().y));
                getParent().addChild(aEffect2, 6);

            default:
                break;
        }
    }


}
