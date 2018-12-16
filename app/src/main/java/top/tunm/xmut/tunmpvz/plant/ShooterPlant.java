package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.actions.CCScheduler;

import java.util.ArrayList;

import top.tunm.xmut.tunmpvz.bullet.Bullet;


public abstract class ShooterPlant extends Plant {

    private ArrayList<Bullet> bullets;
    private boolean isAttack;

    public ShooterPlant(String format, int number) {
        super(format, number);
        bullets=new ArrayList<>();
    }

    public void attackZombie(){
        if (!isAttack){
            System.out.println("攻击：发射");
            CCScheduler.sharedScheduler().schedule("createBullet",this,
                    5,false);
            isAttack=true;
        }
    }

    public void stopAttackZombie(){
        if (isAttack){
            System.out.println("攻击：停止发射");
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
