package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.interval.CCDelayTime;

import java.util.ArrayList;

import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/15.
 */

public class Spikeweed extends Plant{

    private ArrayList<Zombie> zombies;
    private int hurt;
    public Spikeweed(){
        super("plant/Spikeweed/Frame%02d.png", 18);
        setDontAttack(true);
        setPrice(100);
        zombies = new ArrayList<>();
        hurt = 20;
    }

    public Spikeweed(String formt,int number,int price,int hurt){
        super(formt, number);
        setDontAttack(true);
        setPrice(price);
        zombies = new ArrayList<>();
        this.hurt = hurt;
    }

    public void att(Zombie zombie){
        if(!zombies.contains(zombie)){
            AEffect aEffect2 = new AEffect("eff/ci/eff%02d.png" , 7,0.7f,0.1f);
            aEffect2.setPosition(ccp(zombie.getPosition().x,zombie.getPosition().y-20));
            getParent().addChild(aEffect2,6);
            zombies.add(zombie);
            zombie.hurtCompute(hurt);
            if (zombie.getHP() == 0) {
                zombie.death(0);
                zombie.removeSelf();
            }
        }
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public int getHurt() {
        return hurt;
    }

    public void setHurt(int hurt) {
        this.hurt = hurt;
    }
}

