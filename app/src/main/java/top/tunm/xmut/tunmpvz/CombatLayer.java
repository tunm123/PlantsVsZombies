package top.tunm.xmut.tunmpvz;

import android.view.MotionEvent;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
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
import org.cocos2d.transitions.CCFlipXTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import top.tunm.xmut.tunmpvz.effect.AEffect;


class CombatLayer extends CCLayer {

    private CGSize winSize;
    private CCSprite ccSprite_SeedBank;
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
    private int currentSunNuber = 5000;
    private ArrayList<Sun> suns;
    private AEffect aEffect;

    public CombatLayer() {
        loadMap();
    }

    private void loadMap() {
        cctmxTiledMap = CCTMXTiledMap.tiledMap("combat/map1.tmx");
        addChild(cctmxTiledMap);
        CCTMXObjectGroup objectGroup_show = cctmxTiledMap.objectGroupNamed("show");
        ArrayList<HashMap<String, String>> objects = objectGroup_show.objects;
        ccSprites_show = new ArrayList<>();
        for (HashMap<String, String> object : objects) {
            float x = Float.parseFloat(object.get("x"));
            float y = Float.parseFloat(object.get("y"));
            CCSprite ccSprite_shake = CCSprite.sprite("zombies/zombies_1/shake/Frame00.png");
            ccSprite_shake.setPosition(x, y);
            cctmxTiledMap.addChild(ccSprite_shake);
            ccSprites_show.add(ccSprite_shake);
            ArrayList<CCSpriteFrame> frames = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                        "zombies/zombies_1/shake/Frame%02d.png", i)).displayedFrame();
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
    }

    public void loadChoose() {
        ccSprite_SeedBank = CCSprite.sprite("choose/SeedBank.png");
        ccSprite_SeedBank.setAnchorPoint(0, 1);
        ccSprite_SeedBank.setPosition(0, winSize.getHeight());
        addChild(ccSprite_SeedBank);

        ccLabel = CCLabel.makeLabel("50", "", 20);
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

        plantCards = new ArrayList<>();
        selectPlantCards = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            PlantCard plantCard = new PlantCard(i);
            plantCards.add(plantCard);
            plantCard.getDark().setPosition(50 + 60 * i, 590);
            ccSprite_SeedChoooser.addChild(plantCard.getDark());
            plantCard.getLight().setPosition(50 + 60 * i, 590);
            ccSprite_SeedChoooser.addChild(plantCard.getLight());

        }
        setIsTouchEnabled(true);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint cgPoint = convertTouchToNodeSpace(event);
        if (isStart) {
            if (CGRect.containsPoint(ccSprite_SeedBank.getBoundingBox(), cgPoint)) {
                if (selectCard != null) {
                    selectCard.getLight().setOpacity(255);
                    selectCard = null;
                }
                for (PlantCard plantCard : plantCards) {
                    if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                        selectCard = plantCard;
                        selectCard.getLight().setOpacity(100);
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
                            default:
                                break;
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
                            aEffect = new AEffect("eff/SetEff/Frame%02d.png", 4);
//                        combatLine.addPlant(col,aEffect);
                            aEffect.setPosition(cgPoints_towers.get(row).get(col));
                            cctmxTiledMap.addChild(aEffect);
                        }


                        selectPlant = null;
//                        selectCard.getLight().setOpacity(255);
                        selectCard = null;
                    }
                }
            } else {
                for (Sun sun : suns) {
                    if (CGRect.containsPoint(sun.getBoundingBox(), cgPoint)) {
                        sun.collect();
                    }
                }
            }
        } else {
            if (CGRect.containsPoint(ccSprite_SeedChoooser.getBoundingBox(),
                    cgPoint)) {
                if (selectPlantCards.size() < 6) {
                    for (PlantCard plantCard : plantCards) {
                        if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                            if (!selectPlantCards.contains(plantCard)) {
                                selectPlantCards.add(plantCard);
                                CCMoveTo ccMoveTo = CCMoveTo.action(0.1f,
                                        ccp(50 + 60 * selectPlantCards.size(), 725));
                                plantCard.getLight().runAction(ccMoveTo);
                                if (selectPlantCards.size() == 6) {
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
    }

    public void start() {
        ccSprite_startReady.removeSelf();
        setIsTouchEnabled(true);
        isStart = true;

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
        for (int i = 0; i < 5; i++) {
            CombatLine combatLine = new CombatLine();
            combatLine.setCurrt(i);
            combatLines.add(combatLine);
        }

        LineSet.setCombatLines(combatLines);

        cgPoints_path = new ArrayList<>();
        CCTMXObjectGroup objectGroup_path = cctmxTiledMap.objectGroupNamed("path");
        ArrayList<HashMap<String, String>> objects = objectGroup_path.objects;
        for (HashMap<String, String> object : objects) {
            float x = Float.parseFloat(object.get("x"));
            float y = Float.parseFloat(object.get("y"));
            cgPoints_path.add(ccp(x, y));
        }
        random = new Random();
        CCScheduler.sharedScheduler().schedule("addZombie", this, 30, false);
        suns = new ArrayList<>();

        update();
        CCDelayTime ccDelayTime1 = CCDelayTime.action(1);
        CCCallFunc ccCallFunc1 = CCCallFunc.action(this, "startAddZombie1");
        CCDelayTime ccDelayTime2 = CCDelayTime.action(40);
        CCCallFunc ccCallFunc2 = CCCallFunc.action(this, "startAddZombie2");
        CCDelayTime ccDelayTime3 = CCDelayTime.action(60);
        CCCallFunc ccCallFunc3 = CCCallFunc.action(this, "startAddZombie3");
        CCSequence ccSequence = CCSequence.actions(ccDelayTime1, ccCallFunc1, ccDelayTime2,
                ccCallFunc2, ccDelayTime3, ccCallFunc3);
        runAction(ccSequence);
    }

    public void startAddZombie1() {
        CCScheduler.sharedScheduler().schedule("AddZombie", this, 20, false);
    }

    public void startAddZombie2() {
        CCScheduler.sharedScheduler().schedule("AddZombie", this, 10, false);
    }

    public void startAddZombie3() {
        CCScheduler.sharedScheduler().schedule("AddZombie", this, 5, false);
    }

    public void addZombie(float t) {
        int i = random.nextInt(5);
        Zombie zombie = new Zombie(this, cgPoints_path.get(2 * i),
                cgPoints_path.get(2 * i + 1));
        cctmxTiledMap.addChild(zombie, 5 - i);
        combatLines.get(i).addZombie(zombie);
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
                    price = 20;
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

    public void addSun(Sun sun) {
        suns.add(sun);
    }

    public void removeSun(Sun sun) {
        suns.remove(sun);
    }


}








