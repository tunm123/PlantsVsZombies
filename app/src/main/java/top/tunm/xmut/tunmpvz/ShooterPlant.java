package top.tunm.xmut.tunmpvz;

import org.cocos2d.actions.CCScheduler;

import java.util.ArrayList;


public abstract class ShooterPlant extends Plant {

    private ArrayList<Bullet> bullets;
    private boolean isAttack;

    public ShooterPlant(String format, int number) {
        super(format, number);
        bullets=new ArrayList<>();
    }

    public void attackZombie(){
        if (!isAttack){
            CCScheduler.sharedScheduler().schedule("createBullet",this,
                    5,false);
            isAttack=true;
        }
    }

    public void stopAttackZombie(){
        if (isAttack){
            CCScheduler.sharedScheduler().unschedule("createBullet",this);
            isAttack=false;
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public abstract void createBullet(float t);
}
