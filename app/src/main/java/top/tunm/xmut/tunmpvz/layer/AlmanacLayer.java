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

import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.card.PlantCard;

/**
 * Created by jingyuyan on 2018/12/10.
 */

public class AlmanacLayer extends CCLayer {

    private CCSprite cardBack;
    private CCSprite background;
    private CGSize winSize;
    private PlantCard selectCard;
    private ArrayList<PlantCard> plantCards;
    private ArrayList<PlantCard> selectPlantCards;
    private int cards = 27;
    private CCSprite almanac;
    private CCSprite showPlan;
    private ArrayList<CCSpriteFrame> showFrame;
    private CCLabel showLabel;
    private CCSprite showText;
    private CCMenu exitMenu;
    private CCLayer preLayer;
    private CCSprite light;

    public AlmanacLayer(CCLayer ccLayer) {
        this.preLayer = ccLayer;
        load();
    }


    public void load() {
        showFrame = new ArrayList<>();
        winSize = CCDirector.sharedDirector().getWinSize();

        background = CCSprite.sprite("interface/background5.jpg");
        background.setAnchorPoint(0, 1);
        background.setPosition(0, winSize.height);
        background.setScale(1.5);
        addChild(background);

        cardBack = CCSprite.sprite("interface/Almanac_PlantBack.jpg");
        cardBack.setAnchorPoint(0, 0);
        cardBack.setPosition(winSize.width/5, winSize.height-cardBack.getContentSize().getHeight());
        addChild(cardBack);



        // 退出按键
        exitMenu = CCMenu.menu();
        CCSprite exit_default=
                CCSprite.sprite("menu/exit.png");
        CCSprite exit_press=
                CCSprite.sprite("menu/exited.png");
        CCMenuItemSprite ccMenuItemSprite= CCMenuItemSprite.item(exit_default,
                exit_press,this,"exit");
        ccMenuItemSprite.setPosition(-500,-300);
        ccMenuItemSprite.setScale(0.6f);
        exitMenu.addChild(ccMenuItemSprite);
        addChild(exitMenu);

        setIsTouchEnabled(true);

        loadChoose();
    }

    public void loadChoose() {

        // 设置选择卡片
        plantCards = new ArrayList<>();
        selectPlantCards = new ArrayList<>();
        float biasX = 308;
        float biasY = 50;
        int bias;
        for (int i = 0; i < cards; i++) {
            bias = i;
            PlantCard plantCard = new PlantCard(i);
            plantCards.add(plantCard);
//            plantCard.getDark().setPosition(biasX + 52 * (i % 8), biasY + 590 - (bias / 8) * 80);
//            cardBack.addChild(plantCard.getDark());
            plantCard.getLight().setPosition(biasX + 52 * (i % 8), biasY + 590 - (bias / 8) * 80);
            addChild(plantCard.getLight());
        }



        light = CCSprite.sprite("eff/cardlight/taqing_round_anim_00.png");
        ArrayList<CCSpriteFrame> frames2 = new ArrayList<>();
        light.setPosition(plantCards.get(0).getLight().getPosition().x, plantCards.get(0).getLight().getPosition().y);
        addChild(light);
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

        setAlmanacCard();
        setIsTouchEnabled(true);

    }


    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint cgPoint = convertTouchToNodeSpace(event);
//        if (CGRect.containsPoint(cardBack.getBoundingBox(), cgPoint)) {
            System.out.println("界面点击");
            for (PlantCard plantCard : plantCards) {
                if (CGRect.containsPoint(plantCard.getLight().getBoundingBox(), cgPoint)) {
                    System.out.println("m按下了" + plantCards.indexOf(plantCard));
                    showPlantAct(plantCards.indexOf(plantCard));
                }
//            }
        }
        return super.ccTouchesBegan(event);
    }

    public void setAlmanacCard() {
        almanac = CCSprite.sprite("choose/Almanac_PlantCard.png");
        almanac.setAnchorPoint(0, 1);
        almanac.setPosition(cardBack.getContentSize().getWidth()-85, winSize.getHeight() - 85);
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

        showPlantAct(0);
    }


    public void showPlantAct(int pushID) {
        showFrame.clear();
        for (int i = 0; i < ToolsSet.cardInt[pushID]; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    ToolsSet.cardPath[pushID], i)).displayedFrame();
            showFrame.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(showFrame, 0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);


        switch (pushID) {
            case 9:
                showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - -45);
                break;
            case 6:
                showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 100);
                break;
            case 15:
                showPlan.setPosition(almanac.getPosition().x + 100, winSize.getHeight() - 90);
                break;
            case 21:
                // 卷心菜
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
        showPlan.runAction(ccRepeatForever);

        showLabel.setString(ToolsSet.name[pushID]);
        showText.removeSelf();
        String path = ToolsSet.text + String.format(Locale.CHINA,"%02d.png",pushID);
        showText = CCSprite.sprite(path);
        showText.setPosition(showPlan.getPosition().x+60,winSize.getHeight() - 420);
        addChild(showText);

        light.setPosition(plantCards.get(pushID).getLight().getPosition().x,
                plantCards.get(pushID).getLight().getPosition().y);
    }


    public void exit(Object item){
        if (preLayer instanceof MenuLayer){
            CCScene ccScene = CCScene.node();
            ccScene.addChild(new MenuLayer());
            CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2,ccScene);
            CCDirector.sharedDirector().runWithScene(ccFadeTransition);
        }else if(preLayer instanceof CombatLayer){
            preLayer.onEnter();
            removeSelf();
        }
    }

}

