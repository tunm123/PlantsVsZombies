package top.tunm.xmut.tunmpvz;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;

import top.tunm.xmut.tunmpvz.layer.CheckPoint;
import top.tunm.xmut.tunmpvz.layer.CombatLayer;
import top.tunm.xmut.tunmpvz.layer.CombatLine;
import top.tunm.xmut.tunmpvz.plant.ShooterPlant;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/6.
 */

public class ToolsSet {
    public static ArrayList<CombatLine> combatLines;

    public static ArrayList<CombatLine> getCombatLines() {
        return combatLines;
    }

    public static ArrayList<ArrayList<ShooterPlant>> shooterPlansArrays;

    public static ArrayList<ArrayList<Zombie>> zombieArrays;

    public static CombatLayer currtCombatLayer;

    public static SoundEngine bgm;

    public static boolean isEffectSound = true;

    public static boolean isBGMSound = true;

    public static boolean isNight = false;

    public static boolean isIsNight() {
        return isNight;
    }

    public static void setIsNight(boolean isNight) {
        ToolsSet.isNight = isNight;
    }

    public static void effectSound(int id){
        if (isEffectSound) {
            SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), id);
        }
    }

    public static void bgmSound(int id){
        if (isBGMSound){
            ToolsSet.bgm.playSound(CCDirector.sharedDirector().getActivity(), id,true);
        }
    }

    public static void preloadSound(){
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.bomb);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.bomb1);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.dight);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.fly);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.tu);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.fight);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.ele);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.click);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.boom3);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.newcard);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.up);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.get);
    }

    public static ArrayList<ArrayList<ShooterPlant>> getShooterPlansArrays() {
        return shooterPlansArrays;
    }

    public static void setShooterPlansArrays(ArrayList<ArrayList<ShooterPlant>> shooterPlansArrays) {
        ToolsSet.shooterPlansArrays = shooterPlansArrays;
    }

    public static void setCombatLines(ArrayList<CombatLine> combatLines) {
        ToolsSet.combatLines = combatLines;
    }

    public static CGPoint setColRowToPoint(int col,int row){
        float x = 105 * (col + 200);
        float y = 200 * (row + 40);
        CGPoint point = new CGPoint();
        point.set(x,y);
        return point;
    }

    // 僵尸的路径存放

    // 基本僵尸 100
    public static String zombieStand = "zombies/zombies_1/walk/Frame%02d.png";
    public static String zombieStands = "zombies/zombies_1/walk/Frame00.png";
    public static int zombiStandInt = 22;
    public static String zombieMove = "zombies/zombies_1/walk/Frame%02d.png";
    public static int zombieMoveInt = 22;
    public static String zombieAttack = "zombies/zombies_1/attack/Frame%02d.png";
    public static int zombieAttackInt = 21;

    // 水桶僵尸 400
    public static String bucketheadZombieStand = "zombies/zombies_1/BucketheadZombie/stand%02d.png";
    public static int bucketheadZombieStandInt = 6;
    public static String bucketheadZombieMove = "zombies/zombies_1/BucketheadZombie/Frame%02d.png";
    public static int bucketheadZombieInt = 15;
    public static String bucketheadZombieAttack = "zombies/zombies_1/BucketheadZombieAttack/Frame%02d.png";
    public static int bucketheadZombieAttackInt = 11;

    // 路障僵尸 200 coneheadZombie
    public static String coneheadZombieStand = "zombies/zombies_1/ConeheadZombie/Frame%02d.png";
    public static int coneheadZombieStandInt = 8;
    public static String coneheadZombieMove = "zombies/zombies_1/ConeheadZombie/Frame%02d.png";
    public static int coneheadZombieInt = 21;
    public static String coneheadZombieAttack = "zombies/zombies_1/ConeheadZombieAttack/Frame%02d.png";
    public static int coneheadZombieAttackInt = 11;


    // 旗帜僵尸 190 flagZombie
    public static String flagZombieStand = "zombies/zombies_1/FlagZombie/Frame%02d.png";
    public static int flagZombieStandInt = 12;
    public static String flagZombieMove = "zombies/zombies_1/FlagZombie/Frame%02d.png";
    public static int flagZombieInt = 12;
    public static String flagZombieAttack = "zombies/zombies_1/FlagZombieAttack/Frame%02d.png";
    public static int flagZombieAttackInt = 11;


    // 读报僵尸-读报模式 500
    public static String newspaperZombieStandB = "zombies/zombies_1/NewspaperZombie/stand%02d.png";
    public  static int newspaperZombieStandBInt = 19;
    public static String newspaperZombieMoveB = "zombies/zombies_1/NewspaperZombie/walkb%02d.png";
    public static int newspaperZombieIntB = 19;
    public static String newspaperZombieAttackB = "zombies/zombies_1/NewspaperZombie/attackb%02d.png";
    public static int newspaperZombieAttackIntB = 8;

    // 读报僵尸-丢报模式 200
    public static String newspaperZombieStandA = "zombies/zombies_1/NewspaperZombie/walka00.png";
    public static String newspaperZombieMoveA = "zombies/zombies_1/NewspaperZombie/walka%02d.png";
    public static int newspaperZombieIntA = 14;
    public static String newspaperZombieAttackA = "zombies/zombies_1/NewspaperZombie/attacka%02d.png";
    public static int newspaperZombieAttackIntA = 7;


    // 撑杆跳僵尸 400
    public static String poleVaultingZombieStand = "zombies/zombies_1/PoleVaultingZombie/stand%02d.png";
    public static int poleVaultingZombieStandInt = 9;
    public static String poleVaultingZombieMoveA = "zombies/zombies_1/PoleVaultingZombie/walka%02d.png";
    public static int poleVaultingZombieIntA = 10;
    public static String poleVaultingZombieMoveB = "zombies/zombies_1/PoleVaultingZombie/walk%02d.png";
    public static int poleVaultingZombieIntB = 25;
    public static String poleVaultingZombieAttack = "zombies/zombies_1/PoleVaultingZombie/attack%02d.png";
    public static int poleVaultingZombieAttackInt = 14;

    // 橄榄球僵尸 600
    public static String footballZombieStand = "zombies/zombies_1/FootballZombie/stand%02d.png";
    public static int footballZombieStandInt = 15;
    public static String footballZombieMoveA = "zombies/zombies_1/FootballZombie/walka%02d.png";
    public static int footballZombieIntA = 11;
    public static String footballZombieMoveB = "zombies/zombies_1/FootballZombie/walkb%02d.png";
    public static int footballZombieIntB = 11;
    public static String footballZombieAttackA = "zombies/zombies_1/FootballZombie/attacka%02d.png";
    public static int footballZombieAttackIntA = 10;
    public static String footballZombieAttackB = "zombies/zombies_1/FootballZombie/attackb%02d.png";
    public static int footballZombieAttackIntB = 11;

    // 小丑僵尸
    public static String jokerZombieStadn = "zombies/zombies_1/JokerZombie/stand00.png";
    public static String jokerZombieMove = "zombies/zombies_1/JokerZombie/walk%02d.png";
    public static int jokerZombieInt = 8;
    public static String jokerZombieAttack = "zombies/zombies_1/JokerZombie/attack%02d.png";
    public static int jokerZombieAttackInt = 7;


    // 设置植物卡片图鉴效果
    public static String[] cardPath = {
            "plant/Peashooter/Frame%02d.png",
            "plant/SunFlower/Frame%02d.png",
            "plant/CherryBomb/Frame%02d.png",
            "plant/WallNut/high/Frame%02d.png",
            "plant/PotatoMine/Frame%02d.png",
            "plant/SnowPea/Frame%02d.png",
            "plant/ChomperAttack/Frame%02d.png",
            "plant/Repeater/Frame%02d.png",
            "plant/Torchwood/Frame%02d.png",
            "plant/Squash/Frame%02d.png",
            "plant/Jalapeno/Frame%02d.png",
            "plant/Threepeater/Frame%02d.png",
            "plant/GatlingPea/frame%02d.png",
            "plant/TwinSunflower/Frame%02d.png",
            "plant/DoomShroom/Frame%02d.png",
            "plant/TallNut/high/Frame%02d.png",
            "plant/Spikeweed/Frame%02d.png",
            "plant/Spikerock/Frame%02d.png",
            "plant/Starfruit/Frame%02d.png",
            "plant/SplitPea/Frame%02d.png",
            "plant/Garlic/Frame%02d.png",
            "plant/CabbagePult/Frame%02d.png",
            "plant/KernelPult/Frame%02d.png",
            "plant/MelonPult/Frame%02d.png",
            "plant/Cactus/Frame%02d.png",
            "plant/Plantern/Frame%02d.png",
            "plant/en%02d.png",
    };

    public static int[] cardInt = {
            13,
            18,
            7,
            16,
            8,
            15,
            9,
            15,
            9,
            17,
            8,
            16,
            13,
            20,
            13,
            14,
            19,
            8,
            13,
            14,
            12,
            18,
            9,
            9,
            11,
            19,
            1
    };

    public static String[] cardPathZombie = {
        "zombies/zombies_1/walk/Frame%02d.png",
        "zombies/zombies_1/ConeheadZombie/Frame%02d.png",
        "zombies/zombies_1/BucketheadZombie/Frame%02d.png",
            "zombies/zombies_1/NewspaperZombie/walkb%02d.png",
            "zombies/zombies_1/PoleVaultingZombie/walka%02d.png",
            "zombies/zombies_1/FootballZombie/walka%02d.png"
    };

    public static int[] cardZombieInt = {
      22,21,15,8,10,11
    };

    public static String[] cardZimbieName = {
           "穷逼僵尸","路障僵尸","铁桶僵尸","读报僵尸","撑杆跳僵尸","橄榄球僵尸"
    };

    public static String[] name = {
        "豌豆射手","向阳花","樱桃炸弹","坚果墙","土豆雷","寒冰射手","食人花","双枪射杀","火炬树桩"
            ,"窝瓜","火爆辣椒","三管射手","机枪射手","双生向阳花","毁灭蘑菇","巨型坚果墙","地刺",
            "地刺王","杨桃","精分射手","大蒜","卷心菜投手","玉米投手","西瓜投手","仙人掌","强化路灯","应援卡"
    };
//
//    public static String[] text1 = {
//        "耐久300点，攻击正前方直线的敌人，伤害20",0,射手
//        "耐久300点，每隔24秒产生一个阳光(25)。开局必须种植的植物",1,向日葵
//            "安放后马上爆炸，会对小范围的敌人造成毁灭性的伤害",2,樱桃炸弹
//            "防御植物，拥有600点的耐力，可以抵挡僵尸的入侵，起到拖延敌人的作用",3,坚果墙
//            "需要15秒破土时间，破土后对触碰僵尸爆炸，会对敌人进行小范围毁灭性的伤害",4,土豆雷
//            "耐久300点，攻击正前方直线的敌人，伤害20，冰冻减速敌人10s",5,寒冰
//            "耐久300点，会吃掉遇到的僵尸，需要咀嚼10秒钟的时间，才能继续吃下一只僵尸",6,食人花
//            "耐久300点，每次发射2发子弹，攻击正前方直线的敌人，每发子弹20伤害",7,双射
//            "耐久300点，首次碰到僵尸会进行火焰攻击，伤害为100，触碰到的子弹会自动化为火焰弹，火焰弹伤害为40",8,火炬
//            "触碰到第一个僵尸时会对范围内的僵尸进行跳跃毁灭性伤害。"9
//            "安放后爆炸，对当前一排的僵尸进行毁灭性打击",10,
//            "耐久300，连续对三行敌人发射子弹，伤害20",11
//            "耐久300点，连续对同一行敌人进行四连发子弹攻击，每发子弹20点伤害",12
//            "耐久300点，每隔24秒产生两个阳光(25)。开局必须种植的植物",13
//            "安放后马上毁灭，选取小范围敌人进行毁灭性的攻击"14
//    };

    public static String text = "text/t";


    public static CheckPoint[] checkPoints = {
            new CheckPoint(1,10,1.0f,0,0,0,8,0),
            new CheckPoint(2,12,0.6f,0.4f,0,0,10,1),
            new CheckPoint(3,14,0.4f,0.31f,0.29f,0,11,2),
            new CheckPoint(4,16,0.35f,0.3f,0.18f,0.17f,12,3),
            new CheckPoint(5,18,0f,0.4f,0.3f,0.16f,0.14f,12,4),
            new CheckPoint(6,21,0f,0.3f,0.2f,0.2f,0.19f,0.11f,12,5)
    };

    public static String bars[] = {
            "interface/FlagMeter13.png",
            "interface/FlagMeter12.png",
            "interface/FlagMeter11.png",
            "interface/FlagMeter10.png",
            "interface/FlagMeter09.png",
            "interface/FlagMeter08.png",
            "interface/FlagMeter07.png",
            "interface/FlagMeter06.png",
            "interface/FlagMeter05.png",
            "interface/FlagMeter04.png",
            "interface/FlagMeter03.png",
            "interface/FlagMeter02.png",
            "interface/FlagMeter01.png",
    };


    public static float getAng(float ang){
        return (float) (ang * (180/Math.PI));
    }

}
