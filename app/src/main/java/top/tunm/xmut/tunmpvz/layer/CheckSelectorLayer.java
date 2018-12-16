package top.tunm.xmut.tunmpvz.layer;

import android.view.MotionEvent;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.card.SelectCard;

/**
 * Created by jingyuyan on 2018/12/12.
 */

public class CheckSelectorLayer extends CCLayer {

    private ArrayList<SelectCard> cards;
    private CGSize winSize;
    private CCSprite back;
    private int count;
    private ArrayList<CCSpriteFrame> showFrame;
    private int pushID;
    private CCSprite showZombie;
    private CheckPoint checkPoint;
    private CCLabel checkLabel;
    private CCLabel countLabel;
    private CCLabel bossLabel;
    private CCLabel plantLabel;
    private SelectCard selectCard;
    private CCMenu exitMenu;
    private CCMenu startMenu;
    private CCMenu dropMenu;

    public CheckSelectorLayer() {
        this.cards = new ArrayList<>();
        this.count = ToolsSet.checkPoints.length;
        showFrame = new ArrayList<>();
        System.out.println("共有" + count + "关");

        load();
    }

    public void load() {
        // 加载背景图片
        winSize = CCDirector.sharedDirector().getWinSize();
        if (ToolsSet.isIsNight()) {
            back = CCSprite.sprite("interface/selectCheck.png");
        } else {
            back = CCSprite.sprite("interface/checkpointLight.png");
        }
        back.setPosition(winSize.getWidth() / 2, winSize.getHeight() / 2);
        back.setScaleX(winSize.getWidth() / back.getTextureRect().size.getWidth());
        back.setScaleY(winSize.getHeight() / back.getTextureRect().size.getHeight());
        addChild(back, 0);

        int bias;
        float biasX = winSize.width / 2 - 210;
        float biasY = winSize.height / 2 + 150;
//        SelectCard c = new SelectCard(1);
//        c.getBack().setPosition(biasX,biasY);
//        addChild(c.getBack());
        // 加载关卡图标

        for (int i = 0; i < 20; i++) {
            bias = i;
            SelectCard selectCard = new SelectCard(i);
            selectCard.getCheckNum().setPosition(biasX + 100 * (i % 5), biasY - (bias / 5) * 115);
            selectCard.getBack().setPosition(biasX + 100 * (i % 5), biasY - (bias / 5) * 115);
            addChild(selectCard.getBack(), 1);
            addChild(selectCard.getCheckNum(), 2);
            if (i < count) {
                selectCard.getCheckNum().setColor(ccColor3B.ccWHITE);
                cards.add(selectCard);
            }
        }
        this.selectCard = cards.get(pushID);
        this.selectCard.getCheckNum().setColor(ccColor3B.ccGREEN);
        // 初始化
        showZombie = CCSprite.sprite("interface/check.png");
        showZombie.setPosition(winSize.width / 2 + 460, winSize.height / 2 + 100);
        showZombie.setScale(1.5);
        addChild(showZombie, 1);

        setIsTouchEnabled(true);
        pushID = 0;
        checkPoint = ToolsSet.checkPoints[pushID];
        System.out.println("关卡僵尸卡片" + checkPoint.getZombiesCard());


        checkLabel = CCLabel.makeLabel("第" + (checkPoint.getCheck()) + "关", "hkbd.ttf", 23);
        bossLabel = CCLabel.makeLabel("xx", "", 23);
        countLabel = CCLabel.makeLabel("xx", "", 23);
        plantLabel = CCLabel.makeLabel("xx", "", 23);
        showZombie();


        // 退出按键
        exitMenu = CCMenu.menu();
        CCSprite exit_default =
                CCSprite.sprite("menu/exit.png");
        CCSprite exit_press =
                CCSprite.sprite("menu/exited.png");
        CCMenuItemSprite ccMenuItemSprite = CCMenuItemSprite.item(exit_default,
                exit_press, this, "exit");
        ccMenuItemSprite.setPosition(-530, -300);
        ccMenuItemSprite.setScale(0.6f);
        exitMenu.addChild(ccMenuItemSprite);
        addChild(exitMenu, 1);

        // 标题
        CCLabel title = CCLabel.makeLabel("请选择关卡", "hkbd.ttf", 30);
        title.setColor(ccColor3B.ccBLACK);
        title.setPosition(winSize.width / 2, winSize.height / 2 + 345);
        addChild(title, 1);

        // 开始游戏按钮
        String btn1 = "menu/startt.png";
        String btn2 = "menu/startpress.png";
        if (!ToolsSet.isIsNight()) {
            btn1 = "menu/startBTND.png";
            btn2 = "menu/startBTNP.png";
        }
        startMenu = CCMenu.menu();
        CCSprite startD = CCSprite.sprite(btn1);
        CCSprite startP = CCSprite.sprite(btn2);
        CCMenuItemSprite ccMenuItemSprite1 = CCMenuItemSprite.item(startD, startP, this, "start");
        ccMenuItemSprite1.setPosition(0, -310);
        ccMenuItemSprite1.setScale(1.3f);
        startMenu.addChild(ccMenuItemSprite1);
        addChild(startMenu, 1);

        // 开关灯
        dropMenu = CCMenu.menu();
        CCSprite dorpD = CCSprite.sprite("choose/drop0.png");
        CCSprite dorpP = CCSprite.sprite("choose/drop.png");
        CCMenuItemSprite ccMenuItemSprite2 = CCMenuItemSprite.item(dorpD, dorpP, this, "drop");
        ccMenuItemSprite2.setPosition(-500, 300);
        ccMenuItemSprite2.setScale(1.5f);
        dropMenu.addChild(ccMenuItemSprite2);
        addChild(dropMenu, 1);

    }

    public void drop(Object item) {
        ToolsSet.effectSound(R.raw.click);
        if (!ToolsSet.isIsNight()) {
            ToolsSet.isNight = true;
        } else {
            ToolsSet.isNight = false;
        }
        getParent().addChild(new CheckSelectorLayer());
        removeSelf();
//        back.removeSelf();
//        if (!ToolsSet.isIsNight()) {
//            back = CCSprite.sprite("interface/selectCheck.png");
//            ToolsSet.isNight = true;
//        }else {
//            back = CCSprite.sprite("interface/checkpointLight.png");
//            ToolsSet.isNight = false;
//        }
//        back.setPosition(winSize.getWidth() / 2, winSize.getHeight() / 2);
//        back.setScaleX(winSize.getWidth() / back.getTextureRect().size.getWidth());
//        back.setScaleY(winSize.getHeight() / back.getTextureRect().size.getHeight());
//        addChild(back,0);
    }

    public void start(Object item) {
        ToolsSet.effectSound(R.raw.dight);
        CCScene ccScene = CCScene.node();
        ccScene.addChild(new CombatLayer(ToolsSet.checkPoints[checkPoint.getCheck() - 1]));
        CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2, ccScene);
        CCDirector.sharedDirector().runWithScene(ccFadeTransition);
    }

    public void exit(Object item) {
        ToolsSet.effectSound(R.raw.dight);
        CCScene ccScene = CCScene.node();
        ccScene.addChild(new MenuLayer());
        CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2, ccScene);
        CCDirector.sharedDirector().runWithScene(ccFadeTransition);

    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint cgPoint = convertTouchToNodeSpace(event);
        for (SelectCard selectCard : cards) {
            if (cards.indexOf(selectCard) != pushID) {
                if (CGRect.containsPoint(selectCard.getBack().getBoundingBox(), cgPoint)) {
                    ToolsSet.effectSound(R.raw.dight);
                    this.selectCard.getCheckNum().setColor(ccColor3B.ccWHITE);
                    pushID = cards.indexOf(selectCard);
                    checkPoint = ToolsSet.checkPoints[pushID];
                    showZombie();
                    selectCard.getCheckNum().setColor(ccColor3B.ccGREEN);
                    this.selectCard = selectCard;
                    System.out.println("按下第" + checkPoint.getCheck() + "关");
                }
            }
        }

//        System.out.println("选择第"+checkPoint.getCheck()+"关");
        return super.ccTouchesBegan(event);

    }

    public void showZombie() {

        showFrame.clear();
        for (int i = 0; i < ToolsSet.cardZombieInt[checkPoint.getZombiesCard()]; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    ToolsSet.cardPathZombie[checkPoint.getZombiesCard()], i)).displayedFrame();
            showFrame.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(showFrame, 0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);
        showZombie.runAction(ccRepeatForever);

        checkLabel.removeSelf();
        checkLabel = CCLabel.makeLabel("第" + (checkPoint.getCheck()) + "关", "hkbd.ttf", 27);
        checkLabel.setColor(ccColor3B.ccBLACK);
        checkLabel.setPosition(winSize.width / 2 + 460, winSize.height / 2 - 85);
        addChild(checkLabel, 1);

        bossLabel.removeSelf();
        bossLabel = CCLabel.makeLabel("本关新的僵尸：" + (ToolsSet.cardZimbieName[checkPoint.getZombiesCard()]), "hkbd.ttf", 23);
        bossLabel.setColor(ccColor3B.ccBLACK);
        bossLabel.setPosition(winSize.width / 2 + 470, winSize.height / 2 - 140);
        addChild(bossLabel, 1);

        countLabel.removeSelf();
        countLabel = CCLabel.makeLabel("本关需打倒僵尸数量：" + checkPoint.getZombiesCount(), "hkbd.ttf", 23);
        countLabel.setColor(ccColor3B.ccBLACK);
        countLabel.setPosition(winSize.width / 2 + 470, winSize.height / 2 - 200);
        addChild(countLabel, 1);

        plantLabel.removeSelf();
        plantLabel = CCLabel.makeLabel("可获得植物卡片：" + ToolsSet.name[checkPoint.getNewCardID()], "hkbd.ttf", 23);
        plantLabel.setColor(ccColor3B.ccBLACK);
        plantLabel.setPosition(winSize.width / 2 + 470, winSize.height / 2 - 260);
        addChild(plantLabel, 1);
    }
}
