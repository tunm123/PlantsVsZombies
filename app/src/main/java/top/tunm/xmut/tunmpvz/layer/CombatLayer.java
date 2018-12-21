package top.tunm.xmut.tunmpvz.layer;

import android.view.MotionEvent;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.instant.CCShow;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCFlipXTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.bullet.Sun;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.card.PlantCard;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.plant.CabbagePult;
import top.tunm.xmut.tunmpvz.plant.Cactus;
import top.tunm.xmut.tunmpvz.plant.CherryBomb;
import top.tunm.xmut.tunmpvz.plant.Chomper;
import top.tunm.xmut.tunmpvz.plant.DoomShroom;
import top.tunm.xmut.tunmpvz.plant.Gaara;
import top.tunm.xmut.tunmpvz.plant.Garlic;
import top.tunm.xmut.tunmpvz.plant.GatlingPea;
import top.tunm.xmut.tunmpvz.plant.Jalapeno;
import top.tunm.xmut.tunmpvz.plant.Kahu;
import top.tunm.xmut.tunmpvz.plant.Kakashi;
import top.tunm.xmut.tunmpvz.plant.KernelPult;
import top.tunm.xmut.tunmpvz.plant.MelonPult;
import top.tunm.xmut.tunmpvz.plant.Peashooter;
import top.tunm.xmut.tunmpvz.plant.Plant;
import top.tunm.xmut.tunmpvz.plant.Plantern;
import top.tunm.xmut.tunmpvz.plant.PotatoMine;
import top.tunm.xmut.tunmpvz.plant.Repeater;
import top.tunm.xmut.tunmpvz.plant.RockLee;
import top.tunm.xmut.tunmpvz.plant.Sasuke;
import top.tunm.xmut.tunmpvz.plant.SnowPea;
import top.tunm.xmut.tunmpvz.plant.Spikerock;
import top.tunm.xmut.tunmpvz.plant.Spikeweed;
import top.tunm.xmut.tunmpvz.plant.SplitPea;
import top.tunm.xmut.tunmpvz.plant.Squash;
import top.tunm.xmut.tunmpvz.plant.StarFruit;
import top.tunm.xmut.tunmpvz.plant.SunFlower;
import top.tunm.xmut.tunmpvz.plant.TallNut;
import top.tunm.xmut.tunmpvz.plant.Threepeater;
import top.tunm.xmut.tunmpvz.plant.Torchwood;
import top.tunm.xmut.tunmpvz.plant.TwinSunflower;
import top.tunm.xmut.tunmpvz.plant.WallNut;
import top.tunm.xmut.tunmpvz.zombies.FootballZombie;
import top.tunm.xmut.tunmpvz.zombies.JokerZombie;
import top.tunm.xmut.tunmpvz.zombies.NewspaperZombie;
import top.tunm.xmut.tunmpvz.zombies.PoleVaultingZombie;
import top.tunm.xmut.tunmpvz.zombies.Zombie;


public class CombatLayer extends CCLayer {

    private CGSize winSize;
    private CCSprite ccSprite_SeedBank;
    private CCSprite shovelBack;
    private CCSprite shovel;
    private CCLabel ccLabel;
    private CCSprite ccSprite_SeedChoooser;
    private ArrayList<PlantCard> plantCards;
    private ArrayList<PlantCard> selectPlantCards;
    private boolean isMove;
    private CCSprite ccSprite_SeedChooser_Button;
    private CCTMXTiledMap cctmxTiledMap;
    private ArrayList<CCSprite> ccSprites_show;
    private CCSprite ccSprite_startReady;
    private boolean isStart;
    private PlantCard selectCard;
    private Plant selectPlant;
    private ArrayList<ArrayList<CGPoint>> cgPoints_towers;
    private ArrayList<CombatLine> combatLines;
    private ArrayList<CGPoint> cgPoints_path;
    private Random random;
    private int currentSunNuber = 9999;
    private ArrayList<Sun> suns;
    private AEffect aEffect;
    private int cards = 27;
    private boolean isShovel;
    private CCSprite almanac;
    private CCSprite showPlan;
    private ArrayList<CCSpriteFrame> showFrame;
    private CCLabel showLabel;
    private CCSprite showText;
    private CCSprite mainMenu;
    private boolean isPause;
    private CCDirector ccDirector;
    private int zombiesAll = 1;
    private CheckPoint checkPoint;
    private int death;
    private Zombie lastZombie;
    private PlantCard newCard;
    private boolean newCardIsTouch = false;
    private boolean newCardShow;
    private CCSprite down;
    private CCSprite newCardLight;
    private CCSprite light;
    private ArrayList<Zombie> zombies;
    private int help = 0;
    private int helpMax = 4;
//    private CCProgress

    public CombatLayer(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
        ToolsSet.currtCombatLayer = this;
        loadMap();
    }

    private void loadMap() {
        ToolsSet.bgmSound(R.raw.bgmfight);
        if (ToolsSet.isIsNight()) {
            cctmxTiledMap = CCTMXTiledMap.tiledMap("combat/map2.tmx");
        } else {
            cctmxTiledMap = CCTMXTiledMap.tiledMap("combat/map1.tmx");
        }
        addChild(cctmxTiledMap);
        CCTMXObjectGroup objectGroup_show = cctmxTiledMap.objectGroupNamed("show");
        ArrayList<HashMap<String, String>> objects = objectGroup_show.objects;
        ccSprites_show = new ArrayList<>();

        float range5 = checkPoint.getZombies5Rate();
        float range4 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate();
        float range3 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate();
        float range2 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate() + checkPoint.getZombies2Rate();
        float range1 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate() + checkPoint.getZombies2Rate() + checkPoint.getZombies1Rate();
        float range0 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate() + checkPoint.getZombies2Rate() + checkPoint.getZombies1Rate() + checkPoint.getZombies0Rate();

        for (HashMap<String, String> object : objects) {
            float x = Float.parseFloat(object.get("x"));
            float y = Float.parseFloat(object.get("y"));
            CCSprite ccSprite_shake = CCSprite.sprite("zombies/zombies_1/shake/Frame00.png");
            ccSprite_shake.setPosition(x, y);
            cctmxTiledMap.addChild(ccSprite_shake);
            ccSprites_show.add(ccSprite_shake);
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            Random random = new Random();
            float range = random.nextFloat();
            int index = 0;
            String path = ToolsSet.zombieStand;
            int num = 2;
            // 概率
            if (range > 0 && range <= range5) {
                index = 5;
                path = ToolsSet.footballZombieStand;
                num = ToolsSet.footballZombieStandInt;
            } else if (range > range5 && range <= range4) {
                index = 4;
                path = ToolsSet.poleVaultingZombieStand;
                num = ToolsSet.poleVaultingZombieStandInt;
            } else if (range > range4 && range <= range3) {
                index = 3;
                path = ToolsSet.newspaperZombieStandB;
                num = ToolsSet.newspaperZombieStandBInt;
            } else if (range > range3 && range <= range2) {
                index = 2;
                path = ToolsSet.bucketheadZombieStand;
                num = ToolsSet.bucketheadZombieStandInt;
            } else if (range > range2 && range <= range1) {
                index = 1;
                path = ToolsSet.coneheadZombieStand;
                num = ToolsSet.coneheadZombieStandInt;
            } else if (range > range1 && range <= range0) {
                index = 0;
                path = ToolsSet.zombieStand;
                num = 2;
            }
            for (int i = 0; i < num; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        path, i)).displayedFrame();
                frames.add(ccSpriteFrame);
            }
            CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.2f);
            CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
            CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
            ccSprite_shake.runAction(ccRepeatForever);
        }
        winSize = CCDirector.sharedDirector().winSize();
        CCDelayTime ccDelayTime = CCDelayTime.action(2);
        CCMoveBy ccMoveBy = CCMoveBy.action(2,
                ccp(winSize.getWidth() - cctmxTiledMap.getContentSize().getWidth(), 0));
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "loadChoose");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccMoveBy, ccCallFunc);
        cctmxTiledMap.runAction(ccSequence);

        zombiesAll = 1;
        death = checkPoint.getZombiesCount();
        // 主菜单按钮

        // 创建射手列表的列表
        ToolsSet.shooterPlansArrays = new ArrayList<>();
        // 创建僵尸列表的列表
        ToolsSet.zombieArrays = new ArrayList<>();
        zombies = new ArrayList<>();

        help = 0;

    }

    public void loadChoose() {
        ccSprite_SeedBank = CCSprite.sprite("choose/SeedBank.png");
        ccSprite_SeedBank.setAnchorPoint(0, 1);
        ccSprite_SeedBank.setPosition(0, winSize.getHeight());
        addChild(ccSprite_SeedBank);

        ccLabel = CCLabel.makeLabel(currentSunNuber + "", "", 20);
        ccLabel.setColor(ccColor3B.ccBLACK);
        ccLabel.setPosition(40, 695);
        addChild(ccLabel);

        ccSprite_SeedChoooser = CCSprite.sprite("choose/SeedChooser.png");
        ccSprite_SeedChoooser.setAnchorPoint(0, 0);
        addChild(ccSprite_SeedChoooser);

        CCSprite ccSprite_SeedChooser_Button_Disabled = CCSprite.
                sprite("choose/SeedChooser_Button_Disabled.png");
        ccSprite_SeedChooser_Button_Disabled.setPosition(ccSprite_SeedChoooser.getContentSize().getWidth() / 2, 80);
        ccSprite_SeedChoooser.addChild(ccSprite_SeedChooser_Button_Disabled);

        ccSprite_SeedChooser_Button = CCSprite.sprite("choose/SeedChooser_Button.png");
        ccSprite_SeedChooser_Button.
                setPosition(ccSprite_SeedChoooser.getContentSize().getWidth() / 2, 80);
        ccSprite_SeedChoooser.addChild(ccSprite_SeedChooser_Button);
        ccSprite_SeedChooser_Button.setVisible(false);

        // 设置选择卡片
        plantCards = new ArrayList<>();
        selectPlantCards = new ArrayList<>();
        int bias;
        for (int i = 0; i < cards; i++) {
            bias = i;
            PlantCard plantCard = new PlantCard(i);
            plantCards.add(plantCard);
            plantCard.getDark().setPosition(50 + 60 * (i % 9), 590 - (bias / 9) * 100);
            ccSprite_SeedChoooser.addChild(plantCard.getDark());
            plantCard.getLight().setPosition(50 + 60 * (i % 9), 590 - (bias / 9) * 100);
            ccSprite_SeedChoooser.addChild(plantCard.getLight());
        }

        // 设置图鉴说明
        mainMenu = CCSprite.sprite("choose/mainMenu.png");
        mainMenu.setAnchorPoint(0, 1);
        mainMenu.setPosition(winSize.getWidth() - 150, winSize.getHeight());
//        mainMenu.setScale(0.5);
        mainMenu.setScale(1.3);
        addChild(mainMenu);
        isPause = false;

        setShovel();
        setAlmanacCard();
        setIsTouchEnabled(true);
    }

//
//    // 应援卡片栏
//    private CCSprite leftBox;
//    private boolean leftBoxShow;
//    private ArrayList<PlantCard> helpCard;
//    public void setHelpCards(){
//        helpCard = new ArrayList<>();
//        leftBox = CCSprite.sprite("choose/LeftSeedBank08.png");
//        leftBox.setAnchorPoint(0,1);
//        leftBox.setPosition(0,winSize.getHeight() - 100);
//        addChild(leftBox);
//
//        int bias = 100;
//        for (int i = 0;i < 3;i++){
//            PlantCard plantCard = new PlantCard(i,true);
//            helpCard.add(plantCard);
//            plantCard.getDark().setPosition((leftBox.getPosition().x + 10) , leftBox.getPosition().y+ i * bias);
//            leftBox.addChild(plantCard.getDark());
//            plantCard.getDark().setPosition((leftBox.getPosition().x + 10) , leftBox.getPosition().y+ i * bias);
//            leftBox.addChild(plantCard.getLight());
//
//        }
//
//
//    }

    public void setAlmanacCard() {
        almanac = CCSprite.sprite("choose/Almanac_PlantCard.png");
        almanac.setAnchorPoint(0, 1);
        almanac.setPosition(ccSprite_SeedChoooser.getContentSize().getWidth(), winSize.getHeight() - 85);
        addChild(almanac);

        showPlan = CCSprite.sprite("plant/Repeater/Frame00.png");
        showPlan.setAnchorPoint(0, 1);
        showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 140);
        showPlan.setScale(1.3f);
        addChild(showPlan);

        showLabel = CCLabel.makeLabel("HHHHH", "hkbd.ttf", 25);
        showLabel.setColor(ccColor3B.ccBLACK);
        showLabel.setPosition(showPlan.getPosition().x + 65, winSize.getHeight() - 280);
        addChild(showLabel);

        showText = CCSprite.sprite("text/t00.png");
        showText.setAnchorPoint(0, 1);

        addChild(showText);

        CCHide ccHide = CCHide.action();
        almanac.runAction(ccHide);
        showPlan.runAction(ccHide);
        showLabel.runAction(ccHide);
        showText.runAction(ccHide);
        showFrame = new ArrayList<>();
    }


    public void setShovel() {
        // 铲子背景
        isShovel = false;
        shovelBack = CCSprite.sprite("choose/ShovelBack.png");
        shovelBack.setAnchorPoint(0, 1);
        shovelBack.setPosition(580, winSize.getHeight());
        addChild(shovelBack);
        shovelBack.setScaleX(1.3f);
        shovelBack.setScaleY(2.4f);

        // 铲子
        shovel = CCSprite.sprite("choose/Shovel.png");
        shovel.setAnchorPoint(0, 1);
        shovel.setPosition(590, winSize.getHeight() - 55);
        CCRotateBy ccRotateBy = CCRotateBy.action(0.01f, -45);
        shovel.runAction(ccRotateBy);
        addChild(shovel);
//        shovel.setScale(1);

    }

    // 设置初始化点击
    private float tS = 0;
    private boolean longTouch = false;
    private int pushID;
    private boolean flag = false;
    private CGPoint longPoint;


    private boolean setClicked;

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint cgPoint = convertTouchToNodeSpace(event);
        longPoint = cgPoint;
        if (!isStart) {
            if (!longTouch) {
                if (CGRect.containsPoint(ccSprite_SeedChoooser.getBoundingBox(), cgPoint)) {
                    for (PlantCard plantCard : plantCards) {
                        if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                            CCScheduler.sharedScheduler().schedule("longTouchCallBack", this, 0.1f, false);
                            System.out.println("b按下了" + plantCards.indexOf(plantCard));
                            pushID = plantCards.indexOf(plantCard);
                            longTouch = true;
                            flag = true;
                        }
                    }
                }
            }

        }
        // 触发新卡片点击
        if (!newCardIsTouch) {
            if (newCard != null) {
                System.out.println("卡片存在啊");
                if (CGRect.containsPoint(newCard.getLight().getBoundingBox(), cgPoint)) {
                    newCardIsTouch = true;
                    System.out.println("卡片点中了");
                    light.removeSelf();
                    if (down != null) {
                        CCHide ccHide = CCHide.action();
                        down.runAction(ccHide);
                        down.removeSelf();
                        System.out.println("箭头存在啊");
                    } else {
                        System.out.println("箭头不存在啊");
                    }
                    newCard.getLight().setVertexZ(10);

                    // 光晕
                    ToolsSet.effectSound(R.raw.newcard);
//                    ToolsSet.bgm.pauseSound();
                    ArrayList<CCSpriteFrame> frames = new ArrayList<>();
                    newCardLight = CCSprite.sprite("eff/newcard/Frame00.png");
                    newCardLight.setPosition(newCard.getLight().getPosition());
                    addChild(newCardLight);
                    for (int i = 0; i < 10; i++) {
                        CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                                "eff/newcard/Frame%02d.png", i)).displayedFrame();
                        frames.add(ccSpriteFrame);
                    }
                    CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.5f);
                    CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
                    CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
                    newCardLight.runAction(ccRepeatForever);


                    CCScheduler.sharedScheduler().schedule("toBig", this, 0.06f, false);
                    CCDelayTime ccDelayTime
                            = CCDelayTime.action(2f);
                    CCCallFunc ccCallFunc = CCCallFunc.action(this, "cardLight");
                    CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
                    runAction(ccSequence);
                }
            }

            // 阴影单位
            if (!setClicked) {
                if (selectPlant != null && selectCard != null) {
                    setClicked = true;
                    int col = (int) (cgPoint.x - 220) / 105;
                    int row = (int) (cgPoint.y - 40) / 120;

                    int colx = (int) (cgPoint.x - 220 - 10) / 105;
                    int rowy = (int) (cgPoint.y - 40 - 50) / 120;

                    if (col >= 0 && col < 9 && row >= 0 && row < 5) {
                        CombatLine combatLine = combatLines.get(row);
                        combatLine.setCurrt(row);
                        if (!combatLine.isContainPlant(col)) {
                            switch (selectCard.getId()) {
                                case 0:
                                    showadPlant = new Peashooter();
                                    break;
                                case 1:
                                    showadPlant = new SunFlower();
                                    break;
                                case 2:
                                    showadPlant = new CherryBomb();
                                    break;
                                case 3:
                                    showadPlant = new WallNut();
                                    break;
                                case 4:
                                    showadPlant = new PotatoMine();
                                    break;
                                case 5:
                                    showadPlant = new SnowPea();
                                    break;
                                case 6:
                                    showadPlant = new Chomper();
                                    break;
                                case 7:
                                    showadPlant = new Repeater();
                                    break;
                                case 8:
                                    showadPlant = new Torchwood();
                                    break;
                                case 9:
                                    showadPlant = new Squash();
                                    break;
                                case 10:
                                    showadPlant = new Jalapeno();
                                    break;
                                case 11:
                                    showadPlant = new Threepeater();
                                    break;
                                case 12:
                                    showadPlant = new GatlingPea();
                                    break;
                                case 13:
                                    showadPlant = new TwinSunflower();
                                    break;
                                case 14:
                                    showadPlant = new DoomShroom();
                                    break;
                                case 15:
                                    showadPlant = new TallNut();
                                    break;
                                case 16:
                                    showadPlant = new Spikeweed();
                                    break;
                                case 17:
                                    showadPlant = new Spikerock();
                                    break;
                                case 18:
                                    showadPlant = new StarFruit();
                                    break;
                                case 19:
                                    showadPlant = new SplitPea();
                                    break;
                                case 20:
                                    showadPlant = new Garlic();
                                    break;
                                case 21:
                                    showadPlant = new CabbagePult();
                                    break;
                                case 22:
                                    showadPlant = new KernelPult();
                                    break;
                                case 23:
                                    showadPlant = new MelonPult();
                                    break;
                                case 24:
                                    showadPlant = new Cactus();
                                    break;
                                case 25:
                                    showadPlant = new Plantern();
                                    break;
                                case 26:
                                    if (help == 0){
                                        showadPlant = new Kakashi();
                                    }else if (help == 1){
                                        showadPlant = new Gaara();
                                    }else if (help == 2) {
                                        showadPlant = new RockLee();
                                    }else if(help == 3){
                                        showadPlant = new Sasuke();
                                    }else if(help == 4){
                                        showadPlant = new Kahu();
                                    }
                                    break;
                                default:
                                    break;
                            }
                            if (showadPlant!=null) {
                                showadPlant.setPosition(cgPoints_towers.get(row).get(col));
                                showadPlant.setOpacity(130);
                                cctmxTiledMap.addChild(showadPlant,6);
                            }
                            // 安置前影子
                            colShowad.setOpacity(255);
                            rowShowad.setOpacity(255);
                            colShowad.setPosition(cgPoints_towers.get(row).get(col).x-100,winSize.getHeight()/2);
                            rowShowad.setPosition(winSize.getWidth()/2,cgPoints_towers.get(row).get(col).y + 40);
                        }
                    }
                }
            }
        }
        return super.ccTouchesBegan(event);
    }

    private float big = 1;

    public void toBig(float t) {
        big += 0.1;
        if (big <= 3) {
            newCard.getLight().setScale(big);
            newCardLight.setScale(big * 6);
        }
    }

    // 跳转到一个图鉴单独介绍层
    public void cardLight() {
        ToolsSet.effectSound(R.raw.dight);
        CCScene ccScene = CCScene.node();
        ccScene.addChild(new AlongAlanLayer(checkPoint));
        CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2, ccScene);
        CCDirector.sharedDirector().runWithScene(ccFadeTransition);

    }


    private Plant showadPlant;
    private CCSprite rowShowad;
    private CCSprite colShowad;

    // 移动改变图鉴
    @Override
    public boolean ccTouchesMoved(MotionEvent event) {
        CGPoint cgPoint = convertTouchToNodeSpace(event);
        if (CGPointUtil.distance(longPoint, cgPoint) >= 10) {
            if (longTouch) {
                if (CGRect.containsPoint(ccSprite_SeedChoooser.getBoundingBox(), cgPoint)) {
                    for (PlantCard plantCard : plantCards) {
                        if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                            CCScheduler.sharedScheduler().schedule("longTouchCallBack", this, 0.1f, false);
                            System.out.println("m按下了" + plantCards.indexOf(plantCard));
                            pushID = plantCards.indexOf(plantCard);
                            longTouch = true;
                            flag = true;
                            longPoint = cgPoint;
                            ToolsSet.effectSound(R.raw.click);
                        }
                    }
                }
            }
        }


        if (setClicked) {
            if (selectPlant != null && selectCard != null) {

                int col = (int) (cgPoint.x - 220) / 105;
                int row = (int) (cgPoint.y - 40) / 120;

                int colx = (int) (cgPoint.x - 220 - 10) / 105;
                int rowy = (int) (cgPoint.y - 40 - 50) / 120;

                if (col >= 0 && col < 9 && row >= 0 && row < 5) {
                    CombatLine combatLine = combatLines.get(row);
                    combatLine.setCurrt(row);

                    // 安置移动影子
                    colShowad.setPosition(cgPoints_towers.get(row).get(col).x-100,winSize.getHeight()/2);
                    rowShowad.setPosition(winSize.getWidth()/2,cgPoints_towers.get(row).get(col).y + 40);
                    if (!combatLine.isContainPlant(col)) {
                        if (showadPlant!=null) {
                            showadPlant.setPosition(cgPoints_towers.get(row).get(col));
                            showadPlant.setOpacity(130);
                        }
                        colShowad.setColor(ccc3(255,255,255));
                        rowShowad.setColor(ccc3(255,255,255));
                    }else {
                        if (showadPlant!=null){
                            showadPlant.setOpacity(0);

                        }
                        colShowad.setColor(ccColor3B.ccRED);
                        rowShowad.setColor(ccColor3B.ccRED);
                    }
                }
            }

        }

        return super.ccTouchesMoved(event);
    }

    // 长按触发后执行事件
    public void longTouchCallBack(float t) {
        tS += 0.1f;
        if (tS >= 0.2) {
            CCScheduler.sharedScheduler().unschedule("longTouchCallBack", this);
            System.out.println("长按激活");
            CCShow ccShow = CCShow.action();

            String path = ToolsSet.text + String.format(Locale.CHINA, "%02d.png", pushID);
            System.out.println("地址：" + path);
            showLabel.setString(ToolsSet.name[pushID]);
            showText.removeSelf();
            showText = CCSprite.sprite(path);
            showText.setPosition(showPlan.getPosition().x + 60, winSize.getHeight() - 420);
            addChild(showText);


            // 特殊植物调整
            switch (pushID) {
                case 9:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - -45);
                    break;
                case 6:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 100);
                    break;
                case 15:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 70);
                    break;
                case 21:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 90);
                    break;
                case 22:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 50);
                    break;
                case 23:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 42);
                    break;
                default:
                    showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 130);
                    break;
            }
            if (flag) {
                showPlantAct();
                flag = false;
            }
            tS = 0;
            showLabel.runAction(ccShow);
            almanac.runAction(ccShow);
            showPlan.runAction(ccShow);
            showText.runAction(ccShow);
        }
        if (!longTouch) {
            CCScheduler.sharedScheduler().unschedule("longTouchCallBack", this);
            System.out.println("释放");
            CCHide ccHide = CCHide.action();
            almanac.runAction(ccHide);
            showPlan.runAction(ccHide);
            showLabel.runAction(ccHide);
            showText.runAction(ccHide);
        }
    }


    public void showPlantAct() {
        showFrame.clear();
        for (int i = 0; i < ToolsSet.cardInt[pushID]; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    ToolsSet.cardPath[pushID], i)).displayedFrame();
            showFrame.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(showFrame, 0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        showPlan.runAction(ccRepeatForever);
    }

    @Override
    public boolean ccTouchesEnded(MotionEvent event) {
        longTouch = false;
        tS = 0;
        CCHide ccHide = CCHide.action();
        almanac.runAction(ccHide);
        showPlan.runAction(ccHide);
        showLabel.runAction(ccHide);
        showText.runAction(ccHide);
        CGPoint cgPoint = convertTouchToNodeSpace(event);

        // 影子不可见
        if (showadPlant != null) {
            showadPlant.removeSelf();
            showadPlant = null;
        }
        if (colShowad!=null && rowShowad!=null){
            colShowad.setOpacity(0);
            rowShowad.setOpacity(0);
        }

        setClicked = false;
        // 暂停
        if (CGRect.containsPoint(mainMenu.getBoundingBox(), cgPoint)) {
            if (!isPause) {
//                CCActionManager.sharedManager().pause(this);
                this.onExit();
                this.getParent().addChild(new PauseLayer(this));
                ToolsSet.effectSound(R.raw.dight);
            }
        }

        if (isStart) {
            if (CGRect.containsPoint(ccSprite_SeedBank.getBoundingBox(), cgPoint)) {
                if (!isShovel) {
                    if (selectCard != null) {
                        selectCard.getLight().setOpacity(255);
                        selectCard = null;
                    }
                    for (PlantCard plantCard : plantCards) {
                        if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                            if (plantCard.getLight().getOpacity() == 255) {
                                selectCard = plantCard;
                                selectCard.getLight().setOpacity(100);
                                ToolsSet.effectSound(R.raw.click);
                                switch (selectCard.getId()) {
                                    case 0:
                                        selectPlant = new Peashooter();
                                        break;
                                    case 1:
                                        selectPlant = new SunFlower();
                                        break;
                                    case 2:
                                        selectPlant = new CherryBomb();
                                        break;
                                    case 3:
                                        selectPlant = new WallNut();
                                        break;
                                    case 4:
                                        selectPlant = new PotatoMine();
                                        break;
                                    case 5:
                                        selectPlant = new SnowPea();
                                        break;
                                    case 6:
                                        selectPlant = new Chomper();
                                        break;
                                    case 7:
                                        selectPlant = new Repeater();
                                        break;
                                    case 8:
                                        selectPlant = new Torchwood();
                                        break;
                                    case 9:
                                        selectPlant = new Squash();
                                        break;
                                    case 10:
                                        selectPlant = new Jalapeno();
                                        break;
                                    case 11:
                                        selectPlant = new Threepeater();
                                        break;
                                    case 12:
                                        selectPlant = new GatlingPea();
                                        break;
                                    case 13:
                                        selectPlant = new TwinSunflower();
                                        break;
                                    case 14:
                                        selectPlant = new DoomShroom();
                                        break;
                                    case 15:
                                        selectPlant = new TallNut();
                                        break;
                                    case 16:
                                        selectPlant = new Spikeweed();
                                        break;
                                    case 17:
                                        selectPlant = new Spikerock();
                                        break;
                                    case 18:
                                        selectPlant = new StarFruit();
                                        break;
                                    case 19:
                                        selectPlant = new SplitPea();
                                        break;
                                    case 20:
                                        selectPlant = new Garlic();
                                        break;
                                    case 21:
                                        selectPlant = new CabbagePult();
                                        break;
                                    case 22:
                                        selectPlant = new KernelPult();
                                        break;
                                    case 23:
                                        selectPlant = new MelonPult();
                                        break;
                                    case 24:
                                        selectPlant = new Cactus();
                                        break;
                                    case 25:
                                        selectPlant = new Plantern();
                                        break;
                                    case 26:
                                        help++;
                                        if(help>helpMax){
                                            help=0;
                                        }
                                        if (help == 0){
                                            selectPlant = new Kakashi();
                                        }else if (help == 1){
                                            selectPlant = new Gaara();
                                        }else if (help == 2) {
                                            selectPlant = new RockLee();
                                        }else if(help == 3){
                                            selectPlant = new Sasuke();
                                        }else if(help == 4){
                                            selectPlant = new Kahu();
                                        }

                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
            } else if (selectPlant != null && selectCard != null) {

                int col = (int) (cgPoint.x - 220) / 105;
                int row = (int) (cgPoint.y - 40) / 120;

                int colx = (int) (cgPoint.x - 220 - 10) / 105;
                int rowy = (int) (cgPoint.y - 40 - 50) / 120;

                if (col >= 0 && col < 9 && row >= 0 && row < 5) {
                    CombatLine combatLine = combatLines.get(row);
                    combatLine.setCurrt(row);
                    if (!combatLine.isContainPlant(col)) {



                        // 安放植物
                        combatLine.addPlant(col, selectPlant);
                        selectPlant.setPosition(cgPoints_towers.get(row).get(col));
                        cctmxTiledMap.addChild(selectPlant);
                        addSunNumber(-selectPlant.getPrice());

                        // 安放特效
                        if (selectCard.getId() == 3) {
                            aEffect = new AEffect("eff/cap/Frame%02d.png", 10);
//                        combatLine.addPlant(col,aEffect);
                            aEffect.setPosition(cgPoints_towers.get(row).get(col));
                            cctmxTiledMap.addChild(aEffect);
                        } else {
                            aEffect = new AEffect("eff/show/Frame%02d.png", 5);
//                        combatLine.addPlant(col,aEffect);
                            aEffect.setPosition(cgPoints_towers.get(row).get(col));
                            cctmxTiledMap.addChild(aEffect);
                        }

                        ToolsSet.effectSound(R.raw.click);

                        if (selectCard.getId() == 11) {
                            Threepeater threepeater = (Threepeater) selectPlant;
                            threepeater.setCurrerCol(col);
                            threepeater.setAng(col, row);
                        }

                        selectPlant = null;
//                        SelectCard.getLight().setOpacity(255);
                        selectCard = null;
                    }
                }
            } else if (isShovel) {
                int col = (int) (cgPoint.x - 220) / 105;
                int row = (int) (cgPoint.y - 40) / 120;
                if (col >= 0 && col < 9 && row >= 0 && row < 5) {
                    CombatLine combatLine = combatLines.get(row);
                    combatLine.setCurrt(row);
                    if (combatLine.isContainPlant(col)) {
                        Plant plant = combatLine.getPlants().get(col);
                        plant.safe(combatLine.getZombies());
                        plant.setHP(0);
                        combatLine.getPlants().remove(col);
                        plant.setRemove(true);
                        plant.removeSelf();
                        // 铲除特效
                        aEffect = new AEffect("eff/show/Frame%02d.png", 5);
                        aEffect.setPosition(cgPoints_towers.get(row).get(col));
                        cctmxTiledMap.addChild(aEffect);
                        System.out.println("这个区域可以铲除，植物具体位置为：" + plant.getCurrerCol());
                        isShovel = false;
                        ToolsSet.effectSound(R.raw.click);


                    }
                }

                if (CGRect.containsPoint(shovelBack.getBoundingBox(), cgPoint) || !isShovel) {
                    isShovel = false;
                    shovel.setOpacity(255);
                    System.out.println("铲子被举起了，放下");
                }

            } else if (CGRect.containsPoint(shovelBack.getBoundingBox(), cgPoint)) {
                // 选择铲除植物
                System.out.println("点击铲子");
                if (selectCard == null && selectCard == null) {
                    isShovel = true;
                    shovel.setOpacity(100);
                    System.out.println("铲子没举起，举起");

                }
            } else {
                for (Sun sun : suns) {
                    if (CGRect.containsPoint(sun.getBoundingBox(), cgPoint)) {
                        sun.collect();
                        System.out.println("SUM");
                    }
                    System.out.println("太阳位置:" + sun.getBoundingBox());
                }
            }
        } else {
            if (CGRect.containsPoint(ccSprite_SeedChoooser.getBoundingBox(),
                    cgPoint)) {
                if (selectPlantCards.size() < 8) {
                    for (PlantCard plantCard : plantCards) {
                        if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                            if (!selectPlantCards.contains(plantCard)) {
                                selectPlantCards.add(plantCard);
                                CCMoveTo ccMoveTo = CCMoveTo.action(0.1f,
                                        ccp(50 + 60 * selectPlantCards.size(), 725));
                                plantCard.getLight().runAction(ccMoveTo);
                                if (selectPlantCards.size() == 8) {
                                    ccSprite_SeedChooser_Button.setVisible(true);
                                }
                            }
                        }
                    }
                }
            }
            if (CGRect.containsPoint(ccSprite_SeedBank.getBoundingBox(),
                    cgPoint)) {
                isMove = false;
                for (PlantCard plantCard : plantCards) {
                    if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                        CCMoveTo ccMoveTo = CCMoveTo.action(0.1f,
                                plantCard.getDark().getPosition());
                        plantCard.getLight().runAction(ccMoveTo);
                        selectPlantCards.remove(plantCard);
                        ccSprite_SeedChooser_Button.setVisible(false);
                        isMove = true;
                        break;
                    }
                }
            }
            if (isMove) {
                for (int i = 0; i < selectPlantCards.size(); i++) {
                    PlantCard plantCard = selectPlantCards.get(i);
                    CCMoveTo ccMoveTo = CCMoveTo.action(0.1f, ccp(110 + 60 * i, 725));
                    plantCard.getLight().runAction(ccMoveTo);
                }
            }
            if (ccSprite_SeedChooser_Button.getVisible()) {
                if (CGRect.containsPoint(ccSprite_SeedChooser_Button.getBoundingBox(), cgPoint)) {
                    for (PlantCard plantCard : selectPlantCards) {
                        addChild(plantCard.getLight());
                    }
                    ccSprite_SeedChoooser.removeSelf();
                    CCMoveTo ccMoveTo = CCMoveTo.action(2, ccp(-100, 0));
                    CCCallFunc ccCallFunc = CCCallFunc.action(this, "startReady");
                    CCSequence ccSequence = CCSequence.actions(ccMoveTo, ccCallFunc);
                    cctmxTiledMap.runAction(ccSequence);
                }
            }


        }
        return super.ccTouchesBegan(event);
    }

    public void startReady() {
        for (CCSprite ccSprite : ccSprites_show) {
            ccSprite.removeSelf();
        }
        setIsKeyEnabled(false);
        ccSprite_startReady = CCSprite.sprite("startready/startReady_00.png");
        ccSprite_startReady.setPosition(winSize.getWidth() / 2, winSize.getHeight() / 2);
        addChild(ccSprite_startReady);
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    "startready/startReady_%02d.png", i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(frames, 0.2f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, false);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "start");
        CCSequence ccSequence = CCSequence.actions(ccAnimate, ccCallFunc);
        ccSprite_startReady.runAction(ccSequence);
        setIsTouchEnabled(false);
    }

    ArrayList<CCSprite> ccSprites;
    private int tX = 0;

    // 安放小推车
    public void createLawnMower(CombatLine combatLine, int i) {
        CCSprite lawnMower = CCSprite.sprite("plant/LawnMower.png");
        lawnMower.setScale(1.5);
        lawnMower.setPosition(i * 5, i * ((winSize.getHeight() - 80) / 5) + 80);
        addChild(lawnMower);
        combatLine.setLawnMower(lawnMower);
        ccSprites.add(lawnMower);
        combatLine.setLawnMowerPoint(lawnMower.getPosition());
    }

    public void lawnMowerAct(float t) {
        if (tX < 5) {
            CCMoveTo ccMoveTo = CCMoveTo.action(0.5f, CGPoint.ccp(ccSprites.get(tX).getPosition().x + 150, ccSprites.get(tX).getPosition().y));
            ccSprites.get(tX).runAction(ccMoveTo);
        } else {
            CCScheduler.sharedScheduler().unschedule("lawnMowerAct", this);
        }
        tX++;
    }

    public void start() {
        setIsTouchEnabled(true);
        CCHide ccHide = CCHide.action();
        ccSprite_startReady.runAction(ccHide);
        setIsTouchEnabled(true);
        isStart = true;

        ccSprite_startReady.removeSelf();
        // 推车出现
        CCScheduler.sharedScheduler().schedule("lawnMowerAct", this, 0.05f, false);

        cgPoints_towers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<CGPoint> cgPoints_tower = new ArrayList<>();
            CCTMXObjectGroup objectGroup_tower = cctmxTiledMap.objectGroupNamed("tower" + i);
            ArrayList<HashMap<String, String>> objects = objectGroup_tower.objects;
            for (HashMap<String, String> object : objects) {
                float x = Float.parseFloat(object.get("x"));
                float y = Float.parseFloat(object.get("y"));
                cgPoints_tower.add(ccp(x, y));
            }
            cgPoints_towers.add(cgPoints_tower);
        }
        combatLines = new ArrayList<>();
        ccSprites = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CombatLine combatLine = new CombatLine();
            combatLine.setCurrt(i);
            combatLines.add(combatLine);
            createLawnMower(combatLine, i);
        }

        ToolsSet.setCombatLines(combatLines);

        cgPoints_path = new ArrayList<>();
        CCTMXObjectGroup objectGroup_path = cctmxTiledMap.objectGroupNamed("path");
        ArrayList<HashMap<String, String>> objects = objectGroup_path.objects;
        for (HashMap<String, String> object : objects) {
            float x = Float.parseFloat(object.get("x"));
            float y = Float.parseFloat(object.get("y"));
            cgPoints_path.add(ccp(x, y));
        }
        random = new Random();
        CCScheduler.sharedScheduler().schedule("addZombie", this, 10, false);
        suns = new ArrayList<>();
        update();
        startZombie();
//        initBar();
        progress();


        // 影子
        rowShowad = CCSprite.sprite("choose/row.png");
        CGPoint cgPoint = getCgPoints_towers().get(0).get(0);
        rowShowad.setPosition(winSize.getWidth()/2,cgPoint.y);
        addChild(rowShowad);

        colShowad = CCSprite.sprite("choose/col.png");
        colShowad.setPosition(cgPoint.x,winSize.getHeight()/2);
        addChild(colShowad);

        colShowad.setOpacity(0);
        rowShowad.setOpacity(0);

//        localC = CCSprite.sprite("choose/colWaring.png");
//        localR = CCSprite.sprite("choose/rowWaring.png");
//        setHelpCards();
    }

    private CCSprite localC;
    private CCSprite localR;



    public void startZombie() {
        // 向阳花
        CCDelayTime ccDelayTime = CCDelayTime.action(1);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "startAddSun");

        // 僵尸
        CCDelayTime ccDelayTime1 = CCDelayTime.action(60);
        CCCallFunc ccCallFunc1 = CCCallFunc.action(this, "startAddZombie1");
        CCDelayTime ccDelayTime2 = CCDelayTime.action(80);
        CCCallFunc ccCallFunc2 = CCCallFunc.action(this, "startAddZombie2");
        CCDelayTime ccDelayTime3 = CCDelayTime.action(100);
        CCCallFunc ccCallFunc3 = CCCallFunc.action(this, "startAddZombie3");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc, ccDelayTime1, ccCallFunc1, ccDelayTime2,
                ccCallFunc2, ccDelayTime3, ccCallFunc3);
        runAction(ccSequence);
    }

    public void randomSun(float t) {
        System.out.println("生产太阳中....");
        Random random = new Random();
        int row = random.nextInt(5);
        int col = random.nextInt(9);
        CGPoint location = cgPoints_towers.get(row).get(col);
        CGPoint standLocation = new CGPoint();
        standLocation.set(location.x, 800);

        Sun sun = new Sun(suns);
        suns.add(sun);
        sun.setPosition(standLocation.x, standLocation.y);
        cctmxTiledMap.getParent().addChild(sun, 7);
        CCMoveTo ccMoveTo = CCMoveTo.action(6f, location);
        sun.runAction(ccMoveTo);

        System.out.println("随机生成太阳：" + sun.getPosition().x + ":" + sun.getPosition().y);
        System.out.println("当前太阳总数:" + suns.size());
    }

    public void startAddSun() {
        // 随机产生太阳
        System.out.println("开始生成太阳");

        CCScheduler.sharedScheduler().schedule("randomSun", this, 15, false);
    }

    public void startAddZombie1() {
        CCScheduler.sharedScheduler().schedule("AddZombie", this, 5, false);
    }

    public void startAddZombie2() {
        CCScheduler.sharedScheduler().schedule("AddZombie", this, 12, false);
    }

    public void startAddZombie3() {
        CCScheduler.sharedScheduler().schedule("AddZombie", this, 17, false);
    }

    // 暂停僵尸
    public void stopAddZombie1() {
        CCScheduler.sharedScheduler().unschedule("AddZombie", this);
    }

    public void stopAddZombie2() {
        CCScheduler.sharedScheduler().unschedule("AddZombie", this);
    }

    public void stopAddZombie3() {
        CCScheduler.sharedScheduler().unschedule("AddZombie", this);
    }


    public void addZombie(float t) {
        if (zombiesAll <= checkPoint.getZombiesCount()) {
            float range5 = checkPoint.getZombies5Rate();
            float range4 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate();
            float range3 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate();
            float range2 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate() + checkPoint.getZombies2Rate();
            float range1 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate() + checkPoint.getZombies2Rate() + checkPoint.getZombies1Rate();
            float range0 = checkPoint.getZombies5Rate() + checkPoint.getZombies4Rate() + checkPoint.getZombies3Rate() + checkPoint.getZombies2Rate() + checkPoint.getZombies1Rate() + checkPoint.getZombies0Rate();
            if (zombiesAll == 1) {
                int i = random.nextInt(5);
                Zombie zombie = new Zombie(this, cgPoints_path.get(2 * i),
                        cgPoints_path.get(2 * i + 1), 190);
                cctmxTiledMap.addChild(zombie, 5 - i);
                combatLines.get(i).addZombie(zombie);
                zombies.add(zombie);

                if (zombiesAll == death) {
                    lastZombie = zombie;
                    zombie.setLast(true);
                }
                zombies.add(zombie);

//                Zombie zombie1 = new JokerZombie(this, cgPoints_path.get(2 * i),
//                        cgPoints_path.get(2 * i + 1));
//                cctmxTiledMap.addChild(zombie1, 5 - i);
//                combatLines.get(i).addZombie(zombie1);
//                zombies.add(zombie1);

            }
            if (zombiesAll <= checkPoint.getZombiesCount()) {
                int i = random.nextInt(5);
                float range = random.nextFloat();

                // 概率
                if (range > 0 && range <= range5) {
                    Zombie zombie = new FootballZombie(this, cgPoints_path.get(2 * i),
                            cgPoints_path.get(2 * i + 1));
                    cctmxTiledMap.addChild(zombie, 5 - i);
                    combatLines.get(i).addZombie(zombie);
                    System.out.println("橄榄球僵尸概率：" + range);
                    if (zombiesAll == death) {
                        lastZombie = zombie;
                        zombie.setLast(true);
                    }
                    zombies.add(zombie);
                } else if (range > range5 && range <= range4) {
                    Zombie zombie = new PoleVaultingZombie(this, cgPoints_path.get(2 * i),
                            cgPoints_path.get(2 * i + 1));
                    cctmxTiledMap.addChild(zombie, 5 - i);
                    combatLines.get(i).addZombie(zombie);
                    System.out.println("撑杆跳僵尸概率：" + range);
                    if (zombiesAll == death) {
                        lastZombie = zombie;
                        zombie.setLast(true);
                    }
                    zombies.add(zombie);
                } else if (range > range4 && range <= range3) {
                    Zombie zombie = new NewspaperZombie(this, cgPoints_path.get(2 * i),
                            cgPoints_path.get(2 * i + 1));
                    cctmxTiledMap.addChild(zombie, 5 - i);
                    combatLines.get(i).addZombie(zombie);
                    System.out.println("读报僵尸概率：" + range);
                    if (zombiesAll == death) {
                        lastZombie = zombie;
                        zombie.setLast(true);
                    }
                    zombies.add(zombie);
                } else if (range > range3 && range <= range2) {
                    Zombie zombie = new Zombie(this, cgPoints_path.get(2 * i),
                            cgPoints_path.get(2 * i + 1), 600);
                    cctmxTiledMap.addChild(zombie, 5 - i);
                    combatLines.get(i).addZombie(zombie);
                    System.out.println("铁桶僵尸概率：" + range);
                    if (zombiesAll == death) {
                        lastZombie = zombie;
                        zombie.setLast(true);
                    }
                    zombies.add(zombie);
                } else if (range > range2 && range <= range1) {
                    Zombie zombie = new Zombie(this, cgPoints_path.get(2 * i),
                            cgPoints_path.get(2 * i + 1), 300);
                    cctmxTiledMap.addChild(zombie, 5 - i);
                    combatLines.get(i).addZombie(zombie);
                    System.out.println("路障僵尸概率：" + range);
                    if (zombiesAll == death) {
                        lastZombie = zombie;
                        zombie.setLast(true);
                    }
                    zombies.add(zombie);
                } else if (range > range1 && range <= range0) {
                    Zombie zombie = new Zombie(this, cgPoints_path.get(2 * i),
                            cgPoints_path.get(2 * i + 1));
                    cctmxTiledMap.addChild(zombie, 5 - i);
                    combatLines.get(i).addZombie(zombie);
                    System.out.println("普通僵尸概率：" + range);
                    if (zombiesAll == death) {
                        lastZombie = zombie;
                        zombie.setLast(true);
                    }
                    zombies.add(zombie);
                }


            }
//        setBar(zombiesAll);
            zombiesAll++;
            progressTimer.setPercentage(zombiesAll * (100 / checkPoint.getZombiesCount()));
        }

    }

    public void end() {
        setIsTouchEnabled(false);
        CCScheduler.sharedScheduler().unschedule("addZombie", this);
        for (CCNode ccNode : cctmxTiledMap.getChildren()) {
            ccNode.stopAllActions();
            ccNode.unscheduleAllSelectors();
        }
        for (CCNode ccNode : getChildren()) {
            ccNode.stopAllActions();
            ccNode.unscheduleAllSelectors();
        }
        CCSprite ccSprite_ZombiesWon = CCSprite.sprite("zombieswon/ZombiesWon.png");
        ccSprite_ZombiesWon.setPosition(winSize.getWidth() / 2, winSize.getHeight() / 2);
        addChild(ccSprite_ZombiesWon);
        CCDelayTime ccDelayTime = CCDelayTime.action(2);
        CCCallFunc ccCallFunc = CCCallFunc.action(this, "restart");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime, ccCallFunc);
        ccSprite_ZombiesWon.runAction(ccSequence);
    }

    public void restart() {
        CCScene ccScene = CCScene.node();
        ccScene.addChild(new MenuLayer());
        CCFlipXTransition ccFlipXTransition = CCFlipXTransition.transition(2, ccScene, 1);
        CCDirector.sharedDirector().replaceScene(ccFlipXTransition);
    }


    public void addSunNumber(int sunNumber) {
        currentSunNuber += sunNumber;
        ccLabel.setString(currentSunNuber + "");
        update();
    }

    private void update() {
        for (PlantCard plantCard : selectPlantCards) {
            int price = 0;
            switch (plantCard.getId()) {
                case 0:
                    price = 100;
                    break;
                case 1:
                    price = 50;
                    break;
                case 2:
                    price = 150;
                    break;
                case 3:
                    price = 50;
                    break;
                case 4:
                    price = 25;
                    break;
                case 5:
                    price = 175;
                    break;
                case 6:
                    price = 150;
                    break;
                case 7:
                    price = 200;
                    break;
                case 8:
                    price = 175;
                    break;
                case 9:
                    price = 50;
                    break;
                case 10:
                    price = 125;
                    break;
                case 11:
                    price = 125;
                    break;
                case 12:
                    price = 250;
                    break;
                case 13:
                    price = 150;
                    break;
                case 14:
                    price = 125;
                    break;
                case 15:
                    price = 125;
                    break;
                case 16:
                    price = 100;
                    break;
                case 17:
                    price = 125;
                    break;
                case 18:
                    price = 125;
                    break;
                case 19:
                    price = 125;
                    break;
                case 20:
                    price = 50;
                    break;
                case 21:
                    price = 100;
                    break;
                case 22:
                    price = 100;
                    break;
                case 23:
                    price = 300;
                    break;
                case 24:
                    price = 120;
                    break;
                case 25:
                    price = 120;
                    break;
                case 26:
                    price = 20;
                    break;
                case 27:
                    price = 20;
                    break;
                case 28:
                    price = 20;
                    break;
                default:
                    break;
            }
            if (currentSunNuber >= price) {
                plantCard.getLight().setOpacity(255);
            } else {
                plantCard.getLight().setOpacity(100);
            }
        }
    }


    // 新 - 进度条
    private CCProgressTimer progressTimer;

    private void progress() {
        CCSprite back = CCSprite.sprite("interface/FlagMeterEmpty.png");
        addChild(back);
        back.setPosition(winSize.getWidth() - 380, winSize.getHeight() - 20);
        back.setScaleX(2.5f);
        back.setScaleY(1.5f);

        progressTimer = CCProgressTimer.progressWithFile("interface/FlagMeterFull1.png");
        progressTimer.setPosition(winSize.getWidth() - 380, winSize.getHeight() - 20);
        addChild(progressTimer);
        progressTimer.setScaleX(2.5f);
        progressTimer.setScaleY(1.5f);
        progressTimer.setPercentage(0);
        progressTimer.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarLR);

        CCSprite sprite = CCSprite.sprite("interface/FlagMeterEmpty1.png");
        sprite.setPosition(winSize.getWidth() - 380, winSize.getHeight() - 20);
        addChild(sprite);
        sprite.setScaleX(2.5f);
        sprite.setScaleY(1.5f);

        CCSprite name = CCSprite.sprite("interface/FlagMeterLevelProgress.png");
        addChild(name);
        name.setPosition(winSize.getWidth() - 380, winSize.getHeight() - 35);
        name.setScale(1.6f);
    }

    // 进度条相关（暂时弃用）
    private CCSprite myBar;
    private CCSprite myHeadFlag;
    private CCSprite zombieHead;
    private CGPoint startPoint;
    private CGPoint flgPoint;
    private float barY;

    private int currtBar;
    private float zombieHeadStep;
    private float countStep;

    public void initBar() {
        myBar = CCSprite.sprite(ToolsSet.bars[0]);
        barY = 35;
        myBar.setPosition(winSize.getWidth() - myBar.getTextureRect().size.width - 100, barY);
        myBar.setScale(1.6);
        addChild(myBar);

        flgPoint = CGPoint.ccp(myBar.getPosition().x - myBar.getTextureRect().size.width / 2 - 35, barY + 20);
        myHeadFlag = CCSprite.sprite("interface/FlagMeterParts2.png");
        myHeadFlag.setPosition(flgPoint);
        addChild(myHeadFlag);

        startPoint = CGPoint.ccp(myBar.getPosition().x + myBar.getTextureRect().size.width / 2 + 35, barY + 25);
        zombieHead = CCSprite.sprite("interface/FlagMeterParts1.png");
        zombieHead.setPosition(startPoint);
        addChild(zombieHead);

        currtBar = 0;
        zombieHeadStep = (myBar.getTextureRect().size.width + 70) / 13;

        countStep = checkPoint.getZombiesCount() / ToolsSet.bars.length;

        CCSprite label = CCSprite.sprite("interface/FlagMeterLevelProgress.png");
        label.setPosition(myBar.getPosition().x, myBar.getPosition().y - 25);
        addChild(label);
        label.setScale(1.6);

    }

    public void setBar(int currZombieCount) {
        if (currZombieCount >= currtBar * countStep && currtBar <= 11) {
            currtBar++;
            CGPoint point = myBar.getPosition();


            zombieHead.setPosition(zombieHead.getPosition().x - zombieHeadStep, zombieHead.getPosition().y);

            myBar.removeSelf();
            myBar = CCSprite.sprite(ToolsSet.bars[currtBar]);
            myBar.setPosition(point);
            addChild(myBar);
            myBar.setScale(1.6f);
            System.out.println("段数：" + currtBar);
            System.out.println("段字:" + ToolsSet.bars[currtBar]);
        }

    }

    public void addSun(Sun sun) {
        suns.add(sun);
    }

    public void removeSun(Sun sun) {
        suns.remove(sun);
    }

    public Zombie getLastZombie() {
        return lastZombie;
    }

    public void setLastZombie(Zombie lastZombie) {
        this.lastZombie = lastZombie;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public CGSize getWinSize() {
        return winSize;
    }

    public void setWinSize(CGSize winSize) {
        this.winSize = winSize;
    }

    public PlantCard getNewCard() {
        return newCard;
    }

    public void setNewCard(PlantCard newCard) {
        this.newCard = newCard;
    }

    public CCSprite getDown() {
        return down;
    }

    public void setDown(CCSprite down) {
        this.down = down;
    }

    public boolean isNewCardShow() {
        return newCardShow;
    }

    public void setNewCardShow(boolean newCardShow) {
        this.newCardShow = newCardShow;
    }

    public CCSprite getLight() {
        return light;
    }

    public void setLight(CCSprite light) {
        this.light = light;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public ArrayList<ArrayList<CGPoint>> getCgPoints_towers() {
        return cgPoints_towers;
    }

    public void setCgPoints_towers(ArrayList<ArrayList<CGPoint>> cgPoints_towers) {
        this.cgPoints_towers = cgPoints_towers;
    }

    public ArrayList<CGPoint> getCgPoints_path() {
        return cgPoints_path;
    }

    public void setCgPoints_path(ArrayList<CGPoint> cgPoints_path) {
        this.cgPoints_path = cgPoints_path;
    }

    public ArrayList<CombatLine> getCombatLines() {
        return combatLines;
    }

    public void setCombatLines(ArrayList<CombatLine> combatLines) {
        this.combatLines = combatLines;
    }

    public int getZombiesAll() {
        return zombiesAll;
    }

    public void setZombiesAll(int zombiesAll) {
        this.zombiesAll = zombiesAll;
    }
}








