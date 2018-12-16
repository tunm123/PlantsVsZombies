package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;

import java.util.ArrayList;

import top.tunm.xmut.tunmpvz.bullet.StarBullet;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/15.
 */

public class StarFruit extends Plant {

    private float angUp = 90;
    private float angFrontUp = 35;
    private float angFrontDown = -50;
    private float angBackDown = -130;
    private float angBack = 170;

    private boolean isFire;
    private ArrayList<StarBullet> starBullets;

    public StarFruit() {
        super("plant/Starfruit/Frame%02d.png", 13);
        starBullets = new ArrayList<>();
        setPrice(125);
    }

    public void goFire(){
        if (!isFire){
            isFire = true;
            System.out.println("杨桃发射！");
            StarBullet starFruit0 = new StarBullet(this,angUp);
            StarBullet starFruit1 = new StarBullet(this,angFrontUp);
            StarBullet starFruit2 = new StarBullet(this,angFrontDown);
            StarBullet starFruit3 = new StarBullet(this,angBackDown);
            StarBullet starFruit4 = new StarBullet(this,angBack);
            CCDelayTime ccDelayTime = CCDelayTime.action(2);
            CCCallFunc ccCallFunc = CCCallFunc.action(this,"ready");
            CCSequence ccSequence = CCSequence.actions(ccDelayTime,ccCallFunc);
            runAction(ccSequence);
        }else {
            System.out.println("杨桃没准备好啊！");
        }
    }

    public void ready(){
        System.out.println("杨桃准备完成！");
        isFire = false;
    }



    public float getAngUp() {
        return angUp;
    }

    public void setAngUp(float angUp) {
        this.angUp = angUp;
    }

    public float getAngFrontUp() {
        return angFrontUp;
    }

    public void setAngFrontUp(float angFrontUp) {
        this.angFrontUp = angFrontUp;
    }

    public float getAngFrontDown() {
        return angFrontDown;
    }

    public void setAngFrontDown(float angFrontDown) {
        this.angFrontDown = angFrontDown;
    }

    public float getAngBackDown() {
        return angBackDown;
    }

    public void setAngBackDown(float angBackDown) {
        this.angBackDown = angBackDown;
    }

    public float getAngBack() {
        return angBack;
    }

    public void setAngBack(float angBack) {
        this.angBack = angBack;
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        this.isFire = fire;
    }

    public static boolean angleBais(float ang1,float ang2){
        float bias = Math.abs(ang1 - ang2);
        if (bias <= 10){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<StarBullet> getStarBullets() {
        return starBullets;
    }

    public void setStarBullets(ArrayList<StarBullet> starBullets) {
        this.starBullets = starBullets;
    }
}
