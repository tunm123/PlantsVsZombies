package top.tunm.xmut.tunmpvz.layer;

import android.util.SparseArray;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.bullet.Bullet;
import top.tunm.xmut.tunmpvz.bullet.StarBullet;
import top.tunm.xmut.tunmpvz.bullet.ThreeBullet;
import top.tunm.xmut.tunmpvz.card.PlantCard;
import top.tunm.xmut.tunmpvz.plant.CabbagePult;
import top.tunm.xmut.tunmpvz.plant.Cactus;
import top.tunm.xmut.tunmpvz.plant.CherryBomb;
import top.tunm.xmut.tunmpvz.plant.Chomper;
import top.tunm.xmut.tunmpvz.plant.DoomShroom;
import top.tunm.xmut.tunmpvz.plant.Gaara;
import top.tunm.xmut.tunmpvz.plant.Garlic;
import top.tunm.xmut.tunmpvz.plant.Jalapeno;
import top.tunm.xmut.tunmpvz.plant.Kahu;
import top.tunm.xmut.tunmpvz.plant.Kakashi;
import top.tunm.xmut.tunmpvz.plant.KernelPult;
import top.tunm.xmut.tunmpvz.plant.MelonPult;
import top.tunm.xmut.tunmpvz.plant.Plant;
import top.tunm.xmut.tunmpvz.plant.Plantern;
import top.tunm.xmut.tunmpvz.plant.PotatoMine;
import top.tunm.xmut.tunmpvz.plant.RockLee;
import top.tunm.xmut.tunmpvz.plant.Sasuke;
import top.tunm.xmut.tunmpvz.plant.ShooterPlant;
import top.tunm.xmut.tunmpvz.plant.Spikeweed;
import top.tunm.xmut.tunmpvz.plant.Squash;
import top.tunm.xmut.tunmpvz.plant.StarFruit;
import top.tunm.xmut.tunmpvz.plant.Threepeater;
import top.tunm.xmut.tunmpvz.plant.Torchwood;
import top.tunm.xmut.tunmpvz.zombies.PoleVaultingZombie;
import top.tunm.xmut.tunmpvz.zombies.Zombie;


public class CombatLine {

    private SparseArray<Plant> plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<ShooterPlant> shooterPlants;
    private ArrayList<CCSprite> pitcherButllet;
    private ArrayList<CherryBomb> cherryBombs;
    private ArrayList<Jalapeno> jalapenos;
    private ArrayList<Chomper> chompers;
    private ArrayList<Torchwood> torchwoods;
    private ArrayList<Squash> squashes;
    private ArrayList<DoomShroom> doomShrooms;
    private ArrayList<StarFruit> starFruits;
    private ArrayList<CabbagePult> cabbagePults;
    private ArrayList<KernelPult> kernelPults;
    private ArrayList<MelonPult> melonPults;
    private ArrayList<Plantern> planterns;
    private ArrayList<Kakashi> kakashis;
    private ArrayList<Gaara> gaaras;
    private ArrayList<RockLee> rockLees;
    private ArrayList<Sasuke> sasukes;
    private ArrayList<Kahu> kahus;
    private float range = 200;
    private int currt;
    private boolean newCardIsShow = false;
    private PlantCard newCard;
    private CheckPoint checkPoint;
    private CCSprite down;
    private CCSprite lawnMower;
    private CGPoint lawnMowerPoint;
    private boolean isLawnMowerAct;
    private boolean isLawnMowerEnd;
    private CCSprite light;

    public CGPoint getLawnMowerPoint() {
        return lawnMowerPoint;
    }

    public void setLawnMowerPoint(CGPoint lawnMowerPoint) {
        this.lawnMowerPoint = lawnMowerPoint;
    }

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

    public CombatLine() {
        plants = new SparseArray<>();
        zombies = new ArrayList<>();
        shooterPlants = new ArrayList<>();
        pitcherButllet = new ArrayList<>();
        cherryBombs = new ArrayList<>();
        jalapenos = new ArrayList<>();
        chompers = new ArrayList<>();
        torchwoods = new ArrayList<>();
        squashes = new ArrayList<>();
        doomShrooms = new ArrayList<>();
        starFruits = new ArrayList<>();
        cabbagePults = new ArrayList<>();
        kernelPults = new ArrayList<>();
        melonPults = new ArrayList<>();
        planterns = new ArrayList<>();
        kakashis = new ArrayList<>();
        gaaras = new ArrayList<>();
        rockLees = new ArrayList<>();
        sasukes = new ArrayList<>();
        kahus = new ArrayList<>();
        ToolsSet.shooterPlansArrays.add(shooterPlants);
        ToolsSet.zombieArrays.add(zombies);
        CCScheduler.sharedScheduler().schedule("attackPlant", this, 1,
                false);
        CCScheduler.sharedScheduler().schedule("attackZombie", this, 1,
                false);
        CCScheduler.sharedScheduler().schedule("bulletHurtCompute", this,
                0.05f, false);

    }

    public SparseArray<Plant> getPlants() {
        return plants;
    }

    public void addPlant(int col, Plant plant) {
        plants.put(col, plant);
        // 自身记录
        plant.setCurrerCol(col);
        if (plant instanceof ShooterPlant) {
            shooterPlants.add((ShooterPlant) plant);
        }

        if (plant instanceof CherryBomb) {
            cherryBombs.add((CherryBomb) plant);
            System.out.println("sb boom!");
        }

        if (plant instanceof Torchwood) {
            torchwoods.add((Torchwood) plant);
        }

        if (plant instanceof Squash) {
            squashes.add((Squash) plant);
        }

        if (plant instanceof Jalapeno) {
            jalapenos.add((Jalapeno) plant);
        }

        if (plant instanceof DoomShroom) {
            doomShrooms.add((DoomShroom) plant);
        }

        if (plant instanceof StarFruit) {
            starFruits.add((StarFruit) plant);
        }

        if (plant instanceof CabbagePult) {
            cabbagePults.add((CabbagePult) plant);
        }

        if (plant instanceof KernelPult) {
            kernelPults.add((KernelPult) plant);
        }

        if (plant instanceof MelonPult) {
            melonPults.add((MelonPult) plant);
        }

        if (plant instanceof Plantern) {
            planterns.add((Plantern) plant);
        }

        if (plant instanceof Kakashi) {
            kakashis.add((Kakashi) plant);
        }
        if (plant instanceof Gaara) {
            gaaras.add((Gaara) plant);
        }
        if (plant instanceof RockLee) {
            rockLees.add((RockLee) plant);
        }
        if (plant instanceof Sasuke) {
            sasukes.add((Sasuke) plant);
        }
        if (plant instanceof Kahu) {
            kahus.add((Kahu) plant);
        }
    }

    public CCSprite getLawnMower() {
        return lawnMower;
    }

    public void setLawnMower(CCSprite lawnMower) {
        this.lawnMower = lawnMower;
    }

    public boolean isContainPlant(int col) {
        if (plants.get(col) != null) {
            return true;
        }
        return false;
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void attackPlant(float t) {
        if (zombies.size() != 0 && plants.size() != 0) {
            for (Zombie zombie : zombies) {
                int col = (int) (zombie.getPosition().x - 280) / 105;
                if (isContainPlant(col)) {
                    if (!(plants.get(col).isDontAttack())) {
                        switch (zombie.getState()) {
                            case MOVE:
                                zombie.stopAllActions();
                                zombie.attack();
                                break;
                            case ATTACK:
                                Plant plant = plants.get(col);
                                plant.hurtCompute(zombie.getAttack());
                                if (plant.getHP() == 0) {
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
                    } else {
                        Spikeweed spikeweed = (Spikeweed) plants.get(col);
                        spikeweed.att(zombie);
                    }
                    // 当僵尸碰到食人花时
                    if (isContainPlant(col)) {
                        Plant plant = plants.get(col);
                        if (plant instanceof Chomper) {
                            Chomper chomper = (Chomper) plant;
                            if (!chomper.isEating() && !zombie.isSp()) {
                                System.out.println("遇到食人花了。他没在吃东西！");
                                chomper.readyAttack(zombie);
                            }
                        }
                    }

                    // 当僵尸碰到土豆雷时
                    if (isContainPlant(col)) {
                        Plant plant = plants.get(col);
                        if (plant instanceof PotatoMine) {
                            PotatoMine potatoMine = (PotatoMine) plant;
                            if (potatoMine.isUp()) {
                                if (zombie instanceof PoleVaultingZombie) {
                                    if (((PoleVaultingZombie) zombie).isJump()) {
                                        potatoMine.bomb(zombies);
                                        System.out.println("遇到土豆雷了。爆炸了！！");


                                        plants.remove(potatoMine.getCurrerCol());
                                    }
                                } else {
                                    potatoMine.bomb(zombies);
                                    System.out.println("遇到土豆雷了。爆炸了！！");

                                    plants.remove(potatoMine.getCurrerCol());
                                }
                            } else {
                                System.out.println("遇到土豆雷了。他还没长出来！！");
                            }
                        }
                    }

                    // 碰到大蒜
                    if (isContainPlant(col)) {
                        Plant plant = plants.get(col);
                        if (plant instanceof Garlic) {
                            if (!((Garlic) plant).isSmelled() && !zombie.isSm() && !zombie.isSp()) {
                                ((Garlic) plant).smells(zombie, currt, col, zombies);
                            }
                        }
                    }

                    // 当僵尸遇到冰菇时
//                    if (isContainPlant(col)){
//                        Plant plant = plants.get(col);
//                        if (plant instanceof IceShroom){
//                            IceShroom iceShroom = (IceShroom) plant;
//                            System.out.println("碰到冰菇了");
//                            if (!iceShroom.isBoom()){
//                                // 存放所有的僵尸
//                                plants.remove(plant.getCurrerCol());
//                                ArrayList<Zombie> zombiesALL = new ArrayList<>();
//                                // test : 测试获取到其他层上的所有僵尸
//                                for (CombatLine combatLine : ToolsSet.getCombatLines()) {
//                                    zombiesALL.addAll(combatLine.getZombies());
//                                }
//
//                                iceShroom.boom(zombiesALL);
//                            }
//                        }
//                    }

                    // 撑杆跳僵尸
                    if (zombie instanceof PoleVaultingZombie) {
                        if (!((PoleVaultingZombie) zombie).isJump()) {
                            Plant plant = plants.get(col);
                            CGPoint cgPoint = CGPoint.ccp(plant.getPosition().x - 100, plant.getPosition().y);
                            ((PoleVaultingZombie) zombie).jump(cgPoint, plant);
                        }
                    }

//                    if (!isLawnMowerEnd){
//                        if (isLawnMowerAct){

//                        }
//                    }

                } else if (zombie.getState() == Zombie.State.ATTACK) {
                    zombie.stopAllActions();
                    zombie.move();
                }


//                // 小推车
//                if (CGPoint.ccpDistance(zombie.getPosition(), lawnMower.getPosition()) <= 80 &&
//                        zombie.getHP() != 0) {
//                    zombie.hurtCompute(9999);
//                    if (zombie.getHP() == 0) {
//                        zombie.death(0);
//                        zombie.removeSelf();
//                        zombies.remove(zombie);
//                    }
//                }

            }

        }
    }

    public void attackZombie(float t) {
        if (!shooterPlants.isEmpty()) {
            for (ShooterPlant shooterPlant : shooterPlants) {
                if (shooterPlant instanceof Threepeater) {
                    Threepeater threepeater = (Threepeater) shooterPlant;
                    if (ToolsSet.combatLines.contains(this)) {
                        currt = ToolsSet.combatLines.indexOf(this);
//                        System.out.println("存在:"+currt);
                    }
                    boolean isAtt = false;
                    if (currt == 0) {
                        isAtt = zombies.isEmpty() && ToolsSet.shooterPlansArrays.get(currt + 1).isEmpty();
                    } else if (currt == 4) {
                        isAtt = zombies.isEmpty() && ToolsSet.shooterPlansArrays.get(currt - 1).isEmpty();
                    } else {
                        isAtt = zombies.isEmpty() &&
                                ToolsSet.shooterPlansArrays.get(currt + 1).isEmpty() &&
                                ToolsSet.shooterPlansArrays.get(currt - 1).isEmpty();
                    }
                    if (isAtt) {
                        shooterPlant.stopAttackZombie();
                    } else {
                        shooterPlant.attackZombie();
                    }
                } else {
                    if (zombies.isEmpty()) {
                        shooterPlant.stopAttackZombie();
                        System.out.println("不可攻击：" + shooterPlants.size());
                    } else {
                        shooterPlant.attackZombie();
                        System.out.println("可攻击：" + shooterPlants.size());
                    }
                }
            }
        }
    }

    public void bulletHurtCompute(float t) {
        if (!shooterPlants.isEmpty() && !zombies.isEmpty()) {
            for (ShooterPlant shooterPlant : shooterPlants) {
                for (Bullet bullet : shooterPlant.getBullets()) {

//                    // 判断是否经过火盆
//                    int col=(int)(bullet.getPosition().x)/105;
//                    System.out.println("Bullt Col : "+col);
//                    if (isContainPlant(col)){
//                        Plant plant = plants.get(col);
//                        System.out.println("plant in :"+plant.getCurrerCol());
//                        float dis = getDisBetweenTwoUnit(plant.getPosition().x,plant.getPosition().y,
//                                bullet.getPosition().x,bullet.getPosition().y) / 105;
//                        System.out.println("dis : "+dis);
//                        if (plant instanceof Torchwood &&
//                                dis <2.8){
//                            System.out.println("Torchwood Get:"+dis);
//                        }
//                    }else {
//                        System.out.println("no plant");
//                    }


                    // 子弹伤害僵尸
                    Iterator<Zombie> iterator = zombies.iterator();

                    // 三射手子弹处理
                    if (ToolsSet.combatLines.contains(this)) {
                        currt = ToolsSet.combatLines.indexOf(this);
//                        System.out.println("存在:"+currt);
                    }
                    while (iterator.hasNext()) {
                        Zombie zombie = iterator.next();
                        if (bullet.getVisible() && bullet.getPosition().x > zombie.getPosition().x - 20
                                && bullet.getPosition().x < zombie.getPosition().x + 20) {
                            if (bullet instanceof ThreeBullet) {
                                ThreeBullet threeBullet = (ThreeBullet) bullet;
                                System.out.println("子弹所在:" + threeBullet.getAttRow() + "- 当前行:" + currt);
                                if (currt == threeBullet.getAttRow()) {
                                    bullet.showBulletBlast(zombie);
                                    bullet.setVisible(false);
                                    zombie.hurtCompute(bullet.getAttack());
                                    if (zombie.getHP() == 0) {
                                        zombie.death(0);
                                        zombie.removeSelf();
                                        iterator.remove();
                                    }
                                }
                            } else {
                                bullet.showBulletBlast(zombie);
                                bullet.setVisible(false);
                                zombie.hurtCompute(bullet.getAttack());
                                if (zombie.getHP() == 0) {
                                    zombie.death(0);
                                    zombie.removeSelf();
                                    iterator.remove();
                                }
                            }
                        }

                    }
                }
            }

        }

        // 樱桃炸弹
        if (!cherryBombs.isEmpty()) {
            Iterator<CherryBomb> iterator = cherryBombs.iterator();
            while (iterator.hasNext()) {
                CherryBomb cherryBomb = iterator.next();
                cherryBomb.bomb();
                plants.remove(cherryBomb.getCurrerCol());


                // 存放所有的僵尸
                ArrayList<Zombie> zombiesALL = new ArrayList<>();
                // test : 测试获取到其他层上的所有僵尸
                for (CombatLine combatLine : ToolsSet.getCombatLines()) {
                    zombiesALL.addAll(combatLine.getZombies());
                }

                // 对僵尸进行伤害 选取距离range内
                Iterator<Zombie> zombieIterator = zombiesALL.iterator();
                while (zombieIterator.hasNext()) {
                    Zombie zombie = zombieIterator.next();
                    float dis = getDisBetweenTwoUnit(zombie.getPosition().x, zombie.getPosition().y,
                            cherryBomb.getPosition().x, cherryBomb.getPosition().y);
                    System.out.println("tunmtest dis : " + dis);
                    if (dis <= range) {
                        cherryBomb.getZombies().add(zombie);
                    }
                }

                System.out.println("tunmtest all zombies : " + zombiesALL.size());
                cherryBombs.remove(cherryBomb);
            }


        }

        // 毒蘑菇
        if (!doomShrooms.isEmpty()) {
            Iterator<DoomShroom> iterator = doomShrooms.iterator();
            while (iterator.hasNext()) {
                DoomShroom doomShroom = iterator.next();
                doomShroom.bomb();
                plants.remove(doomShroom.getCurrerCol());


                // 存放所有的僵尸
                ArrayList<Zombie> zombiesALL = new ArrayList<>();
                // test : 测试获取到其他层上的所有僵尸
                for (CombatLine combatLine : ToolsSet.getCombatLines()) {
                    zombiesALL.addAll(combatLine.getZombies());
                }

                // 对僵尸进行伤害 选取距离range内
                Iterator<Zombie> zombieIterator = zombiesALL.iterator();
                while (zombieIterator.hasNext()) {
                    Zombie zombie = zombieIterator.next();
                    float dis = getDisBetweenTwoUnit(zombie.getPosition().x, zombie.getPosition().y,
                            doomShroom.getPosition().x, doomShroom.getPosition().y);
                    System.out.println("tunmtest dis : " + dis);
                    if (dis <= range + 30) {
                        doomShroom.getZombies().add(zombie);
                    }
                }

                System.out.println("tunmtest all zombies : " + zombiesALL.size());
                doomShrooms.remove(doomShroom);
            }


        }

        // 辣椒
        if (!jalapenos.isEmpty()) {
            Iterator<Jalapeno> iterator = jalapenos.iterator();
            while (iterator.hasNext()) {
                Jalapeno jalapeno = iterator.next();
                jalapeno.bomb(zombies);
                plants.remove(jalapeno.getCurrerCol());
                jalapenos.remove(jalapeno);
            }


        }

        // 火盆
        if (!torchwoods.isEmpty()) {
            if (!shooterPlants.isEmpty()) {
                for (Torchwood torchwood : torchwoods) {
                    for (ShooterPlant shooterPlant : shooterPlants) {
                        if (!(shooterPlant instanceof Cactus)) {
                            for (Bullet bullet : shooterPlant.getBullets()) {
                                float dis = getDisBetweenTwoUnit(torchwood.getPosition().x, torchwood.getPosition().y,
                                        bullet.getPosition().x, bullet.getPosition().y) / 105;
                                if (dis < 0.6) {
                                    System.out.println("diss : " + dis);
                                    if (!bullet.isFire()) {
                                        bullet.fire();
                                    }
                                }
                            }
                        }
                    }
                    if (torchwood.getHP() == 0 || torchwood.isRemove()) {
                        torchwoods.remove(torchwood);
                    }
                }

            }
            // 第一次喷火
            ArrayList<Zombie> rangeUnit = new ArrayList<>();
            for (Torchwood torchwood : torchwoods) {
                if (!torchwood.isFire()) {
                    Iterator<Zombie> zombieIterator = zombies.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        if (CGPointUtil.distance(zombie.getPosition(), torchwood.getPosition()) <= 120) {
                            rangeUnit.add(zombie);
                        }
                    }
                    if (rangeUnit.size() > 0) {
                        torchwood.fire(rangeUnit);
                    }
                }
            }
        }


        // 窝瓜砸人
        if (!squashes.isEmpty()) {
            if (!zombies.isEmpty()) {
                float rangedis = 90;
                Iterator<Squash> squashIterator = squashes.iterator();
                while (squashIterator.hasNext()) {
                    Squash squash = squashIterator.next();
                    if (!squash.isAttack()) {
                        Iterator<Zombie> zombieIterator = zombies.iterator();
                        while (zombieIterator.hasNext()) {
                            Zombie zombie = zombieIterator.next();
                            float dis = CGPointUtil.distance(squash.getPosition(), zombie.getPosition());
                            if (dis <= rangedis) {
                                squash.attack(zombie, zombies);
                                squashes.remove(squash);
                                plants.remove(squash.getCurrerCol());
                            }
                        }
                    }
                    if (squash.getHP() == 0) {
                        squashes.remove(squash);
                    }
                }

            }
        }

        Zombie taget = null;
        float tagetDis = 1000;
        //卷心菜
        if (!cabbagePults.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (CabbagePult cabbagePult : cabbagePults) {
                    if (!cabbagePult.isNoAttack()) {
                        Iterator<Zombie> zombieIterator = zombies.iterator();
                        taget = zombies.get(0);
                        while (zombieIterator.hasNext()) {
                            Zombie zombie = zombieIterator.next();
                            float dis = CGPointUtil.distance(cabbagePult.getPosition(), zombie.getPosition());
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                        }
                        if (tagetDis >= 150 && tagetDis <= 900 && taget.getPosition().x > cabbagePult.getPosition().x) {
                            cabbagePult.ready(taget);
                        }

                    }
                    if (cabbagePult.getHP() == 0) {
                        cabbagePults.remove(cabbagePult);
                    }
                }
            }
        }

        // 玉米投手
        if (!kernelPults.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (KernelPult kernelPult : kernelPults) {
                    if (!kernelPult.isNoAttack()) {
                        Iterator<Zombie> zombieIterator = zombies.iterator();
                        taget = zombies.get(0);
                        while (zombieIterator.hasNext()) {
                            Zombie zombie = zombieIterator.next();
                            float dis = CGPointUtil.distance(kernelPult.getPosition(), zombie.getPosition());
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                        }
                        if (tagetDis >= 150 && tagetDis <= 900 && taget.getPosition().x > kernelPult.getPosition().x) {
                            Random random = new Random();
                            int i = random.nextInt(10);
                            if (i <= 3) {
                                kernelPult.setYellow(true);
                                kernelPult.setBuPath("bullet/yellow.png");
                            }
                            kernelPult.ready(taget);
                        }
                    }
                    if (kernelPult.getHP() == 0) {
                        kernelPults.remove(kernelPult);
                    }
                }
            }
        }

        // 西瓜投手
        if (!melonPults.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (MelonPult me : melonPults) {
                    if (!me.isNoAttack()) {
                        Iterator<Zombie> zombieIterator = zombies.iterator();
                        while (zombieIterator.hasNext()) {
                            Zombie zombie = zombieIterator.next();
                            taget = zombies.get(0);
                            float dis = CGPointUtil.distance(me.getPosition(), zombie.getPosition());
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                        }
                        if (tagetDis >= 150 && tagetDis <= 900 && taget.getPosition().x > me.getPosition().x) {
                            me.ready(taget);
                        }
                    }
                    if (me.getHP() == 0) {
                        melonPults.remove(me);
                    }
                }
            }
        }

        // 强化路灯
        if (!planterns.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (Plantern plantern : planterns) {
                    if (!plantern.isAtt()) {
                        Iterator<Zombie> zombieIterator = zombies.iterator();
                        while (zombieIterator.hasNext()) {
                            Zombie zombie = zombieIterator.next();
                            float dis = CGPointUtil.distance(plantern.getPosition(), zombie.getPosition());
                            if (dis <= 300) {
                                plantern.launch(zombies);
                            }
                        }
                    }
                    if (plantern.getHP() == 0) {
                        planterns.remove(plantern);
                    }
                }
            }
        }

        // 旗木五五开
        if (!kakashis.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (Kakashi kakashi : kakashis) {
                    Iterator<Zombie> zombieIterator = zombies.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        float dis = CGPointUtil.distance(kakashi.getPosition(), zombie.getPosition());
                        if (dis <= 700) {
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                            kakashis.remove(kakashi);
                            kakashi.start(taget,zombies);
                            plants.remove(kakashi.getCurrerCol());
                        }

                    }
                    if (kakashi.getHP() == 0) {
                        kakashis.remove(kakashi);
                    }
                }
            }
        }

        // 我爱罗
        if (!gaaras.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (Gaara gaara : gaaras) {
                    Iterator<Zombie> zombieIterator = zombies.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        float dis = CGPointUtil.distance(gaara.getPosition(), zombie.getPosition());
                        if (dis <= 700) {
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                            gaaras.remove(gaara);
                            gaara.start(taget);
                            plants.remove(gaara.getCurrerCol());
                        }

                    }
                    if (gaara.getHP() == 0) {
                        kakashis.remove(gaara);
                    }
                }
            }
        }

        // 洛克李
        if (!rockLees.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (RockLee rockLee : rockLees) {
                    Iterator<Zombie> zombieIterator = zombies.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        float dis = CGPointUtil.distance(rockLee.getPosition(), zombie.getPosition());
                        if (dis <= 700) {
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                            rockLees.remove(rockLee);
                            rockLee.start(taget);
                            plants.remove(rockLee.getCurrerCol());
                        }
                    }
                    if (rockLee.getHP() == 0) {
                        kakashis.remove(rockLee);
                    }
                }
            }
        }

        // 佐助
        if (!sasukes.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (Sasuke hero : sasukes) {
                    Iterator<Zombie> zombieIterator = zombies.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        float dis = CGPointUtil.distance(hero.getPosition(), zombie.getPosition());
                        if (dis <= 500) {
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                            hero.start(zombies);
                            sasukes.remove(hero);
                            plants.remove(hero.getCurrerCol());
                        }
                    }
                    if (hero.getHP() == 0) {
                        sasukes.remove(hero);
                    }
                }
            }
        }

        // 扉间
        if (!kahus.isEmpty()) {
            if (!zombies.isEmpty()) {
                for (Kahu hero : kahus) {
                    Iterator<Zombie> zombieIterator = zombies.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        float dis = CGPointUtil.distance(hero.getPosition(), zombie.getPosition());
                        if (dis <= 400) {
                            if (dis <= tagetDis) {
                                tagetDis = dis;
                                taget = zombie;
                            }
                            hero.start(zombie,zombies);
                            kahus.remove(hero);
                            plants.remove(hero.getCurrerCol());
                        }
                    }
                    if (hero.getHP() == 0) {
                        kahus.remove(hero);
                    }
                }
            }
        }

        // 清理僵尸
        if (!zombies.isEmpty()) {
            Iterator<Zombie> iterator = zombies.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = iterator.next();
                if (zombie.getHP() == 0) {
                    ToolsSet.currtCombatLayer.getZombies().remove(zombie);
                    iterator.remove();
                }
            }
        }

        // 清理僵尸
        if (!ToolsSet.currtCombatLayer.getZombies().isEmpty()) {
            Iterator<Zombie> iterator = zombies.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = iterator.next();
                if (zombie.getHP() == 0) {
//                    ToolsSet.currtCombatLayer.getZombies().remove(zombie);
                    iterator.remove();
                }
            }
        }
//
//        System.out.println("僵尸进度："+ToolsSet.currtCombatLayer.getZombiesAll()+"/"+ToolsSet.currtCombatLayer.getCheckPoint().getZombiesCount());
//        System.out.println("僵尸数量："+ToolsSet.currtCombatLayer.getZombies().size());
        if (ToolsSet.currtCombatLayer.getZombiesAll()>=ToolsSet.currtCombatLayer.getCheckPoint().getZombiesCount()) {
            System.out.println("僵尸安放完毕");
//            System.out.println("出现最后一只");
            if (ToolsSet.currtCombatLayer.getZombies().size() == 1) {
//                System.out.println("最后一只僵尸凉了");
//                ToolsSet.currtCombatLayer.next();
                if (!ToolsSet.currtCombatLayer.isNewCardShow()) {
                    ToolsSet.currtCombatLayer.setNewCardShow(true);
                    getNewCard();
                }
            }
        }

        if (!isLawnMowerAct) {
//            System.out.println("小推车距离");
            if (!zombies.isEmpty()) {
                Iterator<Zombie> iterator = zombies.iterator();
                while (iterator.hasNext()) {
                    Zombie zombie = iterator.next();
//                    System.out.println("小推车所在行僵尸的距离：" + CGPoint.ccpDistance(lawnMower.getPosition(), zombies.get(0).getPosition()));
                    if (CGPoint.ccpDistance(lawnMower.getPosition(), zombie.getPosition()) <= 180) {
                        isLawnMowerAct = true;
//                        System.out.println("触发小推车");
                        CCMoveTo ccMoveTo = CCMoveTo.action(0.8f, CGPoint.ccp(lawnMower.getPosition().x + 1600, lawnMower.getPosition().y));
                        lawnMower.runAction(ccMoveTo);
                    }
                }
            }
        }

        if (!isLawnMowerEnd) {
            if (isLawnMowerAct) {
                if (CGPoint.ccpDistance(lawnMowerPoint, lawnMower.getPosition()) >= 1400) {
                    isLawnMowerEnd = true;
                    lawnMower.removeSelf();
                    for (Zombie zombie : zombies) {
                        zombie.hurtCompute(9999);
                        if (zombie.getHP() == 0) {
                            zombie.death(0);
                            zombie.removeSelf();
                        }
                    }
                }
            }
        }
        // 星星
        if (!starFruits.isEmpty()) {
//            System.out.println("有杨桃:"+starFruits.size());
            ArrayList<Zombie> zombiesALL = new ArrayList<>();
            // test : 测试获取到其他层上的所有僵尸
            for (CombatLine combatLine : ToolsSet.getCombatLines()) {
                zombiesALL.addAll(combatLine.getZombies());
            }
            if (!zombiesALL.isEmpty()) {
//                System.out.println("有杨桃和僵尸");
                Iterator<StarFruit> starFruitIterator = starFruits.iterator();
                while (starFruitIterator.hasNext()) {
                    StarFruit starFruit = starFruitIterator.next();
                    // 存放所有的僵尸

//                    System.out.println("所有僵尸的数量"+zombiesALL.size());
                    // 对僵尸进行伤害 选取距离range内
                    Iterator<Zombie> zombieIterator = zombiesALL.iterator();
                    while (zombieIterator.hasNext()) {
                        Zombie zombie = zombieIterator.next();
                        float ang = (float) Math.atan2(zombie.getPosition().y - starFruit.getPosition().y,
                                zombie.getPosition().x - starFruit.getPosition().x);
                        ang = (float) ((float) ang * (180 / Math.PI));
                        if (!starFruit.isFire()) {
                            if (StarFruit.angleBais(ang, starFruit.getAngUp())) {
                                System.out.println("有僵尸出现在正上方");
                                starFruit.goFire();
                            } else if (StarFruit.angleBais(ang, starFruit.getAngFrontUp())) {
                                System.out.println("有僵尸出现在前上方");
                                starFruit.goFire();
                            } else if (StarFruit.angleBais(ang, starFruit.getAngFrontDown())) {
                                System.out.println("有僵尸出现在前下方");
                                starFruit.goFire();
                            } else if (StarFruit.angleBais(ang, starFruit.getAngBackDown())) {
                                System.out.println("有僵尸出现在后下方");
                                starFruit.goFire();
                            } else if (StarFruit.angleBais(ang, starFruit.getAngBack())) {
                                System.out.println("有僵尸出现在正后方");
                                starFruit.goFire();
                            }
                        }
                    }


                    if (starFruit.getHP() == 0 || starFruit.isRemove()) {
                        starFruits.remove(starFruit);
                    }
                }
            }
        }

//        ArrayList<Zombie> zombiesALL = new ArrayList<>();
//        // test : 测试获取到其他层上的所有僵尸
//        for (CombatLine combatLine : ToolsSet.getCombatLines()) {
//            zombiesALL.addAll(combatLine.getZombies());
//        }

        Iterator<Zombie> zombieIterator = ToolsSet.currtCombatLayer.getZombies().iterator();
        for (StarFruit starFruit : starFruits) {
            for (StarBullet starBullet : starFruit.getStarBullets()) {
                System.out.println("获取杨桃子弹" + "：" + starBullet.getPosition().x + ":" + starBullet.getPosition().y);
                for (Zombie zombie : ToolsSet.currtCombatLayer.getZombies()) {
                    System.out.println("获取杨桃子弹锁定的僵尸:" + getDisBetweenTwoUnit(zombie.getPosition().x, zombie.getPosition().y,
                            starBullet.getPosition().x, starBullet.getPosition().y));
//                    if (getDisBetweenTwoUnit(zombie.getPosition().x,zombie.getPosition().y,
//                            starBullet.getPosition().x,starBullet.getPosition().y)<=25) {
//                    if (getDisBetweenTwoUnit(zombie.getPosition().x, zombie.getPosition().y,
//                            starBullet.getPosition().x, starBullet.getPosition().y)<=50
//                            && zombie.getHP() != 0) {
                    if (CGRect.containsPoint(zombie.getBoundingBox(), starBullet.getPosition()) && zombie.getHP() != 0) {
                        starBullet.showBulletBlast(zombie);
                        starBullet.setVisible(false);
                        zombie.hurtCompute(starBullet.getAttack());
                        if (zombie.getHP() == 0) {
                            zombie.death(0);
                            zombie.removeSelf();
                            zombieIterator.remove();
                        }
                        starFruit.getStarBullets().remove(starBullet);
                        starBullet.removeSelf();
                    }
                }
            }
        }
    }


    // 创建新的卡片
    public void getNewCard() {
        checkPoint = ToolsSet.currtCombatLayer.getCheckPoint();
        System.out.println("获得新的卡牌：" + checkPoint.getNewCardID());
        newCard = new PlantCard(checkPoint.getNewCardID());
        newCard.getLight().setPosition(ToolsSet.currtCombatLayer.getWinSize().getWidth() / 2,
                ToolsSet.currtCombatLayer.getWinSize().getHeight());
        ToolsSet.currtCombatLayer.addChild(newCard.getLight());
        CCMoveTo ccMoveTo = CCMoveTo.action(3, CGPoint.ccp(
                ToolsSet.currtCombatLayer.getWinSize().getWidth() / 2, ToolsSet.currtCombatLayer.getWinSize().getHeight() / 2));
        newCard.getLight().runAction(ccMoveTo);
        CCDelayTime ccDelayTime = CCDelayTime.action(3);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "createPointDown");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        newCard.getLight().runAction(ccSequence);
        ToolsSet.effectSound(R.raw.up);


    }

    public void createPointDown() {
        down = CCSprite.sprite("interface/PointerDown/Frame00.png");
        down.setPosition(newCard.getLight().getPosition().x, newCard.getLight().getPosition().y + 50);
        ToolsSet.currtCombatLayer.addChild(down);
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    "interface/PointerDown/Frame%02d.png", i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        down.runAction(ccRepeatForever);
        ToolsSet.currtCombatLayer.setNewCard(newCard);
        ToolsSet.currtCombatLayer.setDown(down);
        System.out.println("创建箭头");

        light = CCSprite.sprite("eff/cardlight/taqing_round_anim_00.png");
        ToolsSet.currtCombatLayer.setLight(light);
        ArrayList<CCSpriteFrame> frames2 = new ArrayList<>();
        light.setPosition(newCard.getLight().getPosition().x, newCard.getLight().getPosition().y);
        ToolsSet.currtCombatLayer.addChild(light);
        light.setScaleX(0.7f);
        for (int i = 0; i < 10; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    "eff/cardlight/taqing_round_anim_%02d.png", i)).displayedFrame();
            frames2.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation2 = CCAnimation.animationWithFrames(frames2, 0.1f);
        CCAnimate ccAnimate2 = CCAnimate.action(ccAnimation2, true);
        CCRepeatForever ccRepeatForever2 = CCRepeatForever.action(ccAnimate2);
        light.runAction(ccRepeatForever2);

    }

    public float getDisBetweenTwoUnit(float x1, float y1, float x2, float y2) {
        float dis = (float) Math.sqrt(
                Math.pow(x1 - x2, 2) +
                        Math.pow(y1 - y2, 2));
        return dis;
    }

    public void setPlants(SparseArray<Plant> plants) {
        this.plants = plants;
    }
}
