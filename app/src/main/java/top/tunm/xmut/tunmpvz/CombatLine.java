package top.tunm.xmut.tunmpvz;

import android.util.SparseArray;

import org.cocos2d.actions.CCScheduler;

import java.util.ArrayList;
import java.util.Iterator;

import top.tunm.xmut.tunmpvz.effect.Effect;


public class CombatLine {

    private SparseArray<Plant> plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<ShooterPlant> shooterPlants;
    private ArrayList<CherryBomb> cherryBombs;
    private float range = 150;
    private int currt;

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public int getCurrt() {
        return currt;
    }

    public void setCurrt(int currt) {
        this.currt = currt;
    }

    public CombatLine(){
        plants=new SparseArray<>();
        zombies=new ArrayList<>();
        shooterPlants=new ArrayList<>();
        cherryBombs = new ArrayList<>();
        CCScheduler.sharedScheduler().schedule("attackPlant",this,1,
                false);
        CCScheduler.sharedScheduler().schedule("attackZombie",this,1,
                false);
        CCScheduler.sharedScheduler().schedule("bulletHurtCompute",this,
                0.2f, false);
    }
    public void  addPlant(int col,Plant plant){
        plants.put(col,plant);
        // 自身记录
        plant.setCurrerCol(col);
        if (plant instanceof ShooterPlant){
            shooterPlants.add((ShooterPlant) plant);
        }

        if (plant instanceof CherryBomb){
            cherryBombs.add((CherryBomb) plant);
            System.out.println("sb boom!");
        }
    }



    public boolean isContainPlant(int col){
        if (plants.get(col)!=null){
            return true;
        }
        return false;
    }
    public void addZombie(Zombie zombie){
        zombies.add(zombie);
    }

    public void attackPlant(float t){
        if (zombies.size()!=0&&plants.size()!=0){
            for (Zombie zombie:zombies){
                int col=(int)(zombie.getPosition().x-280)/105;
                if (isContainPlant(col)){
                    switch (zombie.getState()){
                        case MOVE:
                            zombie.stopAllActions();
                            zombie.attack();
                            break;
                        case ATTACK:
                            Plant plant=plants.get(col);
                            plant.hurtCompute(zombie.getAttack());
                            if (plant.getHP()==0){
                                plant.death(zombie);
                                plants.remove(col);
                                plant.removeSelf();
                                zombie.stopAllActions();
                                zombie.move();
                            }
                            break;
                        default:
                            break;
                    }
                }else if (zombie.getState()==Zombie.State.ATTACK){
                    zombie.stopAllActions();
                    zombie.move();
                }
            }
        }
    }

    public void attackZombie(float t){
        if (!shooterPlants.isEmpty()){
            for (ShooterPlant shooterPlant:shooterPlants){
                if (zombies.isEmpty()){
                    shooterPlant.stopAttackZombie();
                }else {
                    shooterPlant.attackZombie();
                }
            }
        }
    }
    public void bulletHurtCompute(float t){
        if (!shooterPlants.isEmpty()&&!zombies.isEmpty()){
            for (ShooterPlant shooterPlant:shooterPlants){
                for (Bullet bullet:shooterPlant.getBullets()){
                    Iterator<Zombie> iterator=zombies.iterator();
                    while (iterator.hasNext()){
                        Zombie zombie=iterator.next();
                        if (bullet.getVisible()&&bullet.getPosition().x>zombie.getPosition().x-20
                                &&bullet.getPosition().x<zombie.getPosition().x+20){
                            bullet.showBulletBlast(zombie);
                            bullet.setVisible(false);
                            zombie.hurtCompute(bullet.getAttack());
                            if (zombie.getHP()==0){
                                zombie.death(0);
                                zombie.removeSelf();
                                iterator.remove();
                            }
                        }
                    }
                }
            }


        }
        if (!cherryBombs.isEmpty()){
            Iterator<CherryBomb> iterator = cherryBombs.iterator();
            while (iterator.hasNext()){
                CherryBomb cherryBomb = iterator.next();
                cherryBomb.bomb();
                plants.remove(cherryBomb.getCurrerCol());


                // 存放所有的僵尸
                ArrayList<Zombie> zombiesALL = new ArrayList<>();
                // test : 测试获取到其他层上的所有僵尸
                for (CombatLine combatLine : LineSet.getCombatLines()){
                    zombiesALL.addAll(combatLine.getZombies());
                }

                // 对僵尸进行伤害 选取距离range内
                Iterator<Zombie> zombieIterator = zombiesALL.iterator();
                while (zombieIterator.hasNext()){

                    Zombie zombie = zombieIterator.next();
                    float dis = getDisBetweenTwoUnit(zombie.getPosition().x,zombie.getPosition().y,
                            cherryBomb.getPosition().x,cherryBomb.getPosition().y);
                    System.out.println("tunmtest dis : " + dis);
                    if (dis<=range){
                        cherryBomb.getZombies().add(zombie);
                    }
                }

                System.out.println("tunmtest all zombies : "+zombiesALL.size());
                cherryBombs.remove(cherryBomb);
            }



        }


//        if (!zombies.isEmpty()){
//            Iterator<Zombie> iterator = zombies.iterator();
//            while (iterator.hasNext()){
//                Zombie zombie = iterator.next();
//                if (zombie.getHP()==0){
//
//                    zombie.death(0);
//                    zombie.removeSelf();
//                    iterator.remove();
//
//                }
//            }
//        }
    }


    public float getDisBetweenTwoUnit(float x1,float y1,float x2,float y2){
        float dis = (float) Math.sqrt(
                                    Math.pow(x1 - x2,2) +
                                    Math.pow(y1 - y2,2));
        return dis;
    }
}
