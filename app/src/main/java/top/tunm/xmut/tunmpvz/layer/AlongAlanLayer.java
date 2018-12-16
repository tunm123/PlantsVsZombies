package top.tunm.xmut.tunmpvz.layer;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.ToolsSet;

/**
 * Created by jingyuyan on 2018/12/11.
 */

public class AlongAlanLayer extends CCLayer {

    private CCSprite back;
    private CCSprite almanac;
    private CCSprite showPlan;
    private ArrayList<CCSpriteFrame> showFrame;
    private CCLabel showLabel;
    private CCSprite showText;
    private CheckPoint checkPoint;
    private CGSize winSize;
    private CCMenu nextButton;
    private CCLabel checkPointTitle;
    private boolean isLast;

    public AlongAlanLayer(CheckPoint checkPoint){
        this.checkPoint = checkPoint;
        load();
    }

    public void load(){
        showFrame = new ArrayList<>();
        winSize = CCDirector.sharedDirector().getWinSize();
        back = CCSprite.sprite("interface/AwardScreen_Back.jpg");
        back.setPosition(winSize.getWidth()/2,winSize.getHeight()/2);
        back.setScaleX(winSize.getWidth()/back.getTextureRect().size.getWidth());
        back.setScaleY(winSize.getHeight()/back.getTextureRect().size.getHeight());
        addChild(back);
        setIsTouchEnabled(true);
        if (checkPoint.getCheck()>=CheckPoint.currCheck){
            isLast = true;
            System.out.println("最后一关了");
        }else {
            System.out.println("关："+checkPoint.getCheck()+"/"+CheckPoint.currCheck);
        }
        setPlan();
    }

    public void setPlan(){
        showFrame = new ArrayList<>();
        showPlan = CCSprite.sprite("plant/Repeater/Frame00.png");
        showPlan.setPosition(winSize.getWidth()/2, winSize.getHeight()/2 + 250);
        showPlan.setScale(1.3f);
        addChild(showPlan);


        for (int i = 0; i < ToolsSet.cardInt[checkPoint.getNewCardID()]; i++) {
            CCSpriteFrame ccSpriteFrame = CCSprite.sprite(String.format(Locale.CHINA,
                    ToolsSet.cardPath[checkPoint.getNewCardID()], i)).displayedFrame();
            showFrame.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation = CCAnimation.animationWithFrames(showFrame, 0.1f);
        CCAnimate ccAnimate = CCAnimate.action(ccAnimation, true);
        CCRepeatForever ccRepeatForever = CCRepeatForever.action(ccAnimate);


        switch (checkPoint.getNewCardID()) {
            case 9:
                showPlan.setPosition(showPlan.getPosition().x, showPlan.getPosition().y - 20);
                break;
            case 6:
                showPlan.setPosition(showPlan.getPosition().x, showPlan.getPosition().y - 100);
                break;
            default:
                showPlan.setPosition(showPlan.getPosition().x, showPlan.getPosition().y - 130);
                break;
        }

        showPlan.runAction(ccRepeatForever);



        showLabel = CCLabel.makeLabel(ToolsSet.name[checkPoint.getNewCardID()], "hkbd.ttf", 25);
        showLabel.setColor(ccColor3B.ccBLACK);
        showLabel.setPosition(showPlan.getPosition().x, showPlan.getPosition().y - 140);
        showLabel.setScale(1.4);
        addChild(showLabel);

        String path = ToolsSet.text + String.format(Locale.CHINA,"%02d.png",checkPoint.getNewCardID());
        showText = CCSprite.sprite(path);
        showText.setPosition(showPlan.getPosition().x+10, showPlan.getPosition().y - 260);
        showText.setScale(0.9f);
        addChild(showText);

        String title;
        if (isLast) {
             title = "后面没有关卡了！";
             System.out.println(title);
        }else {
             title = "完成第" + checkPoint.getCheck() + "关，即将进入第" + (checkPoint.getCheck() + 1) + "关";
            System.out.println(title);
        }
        checkPointTitle = CCLabel.makeLabel(title,"hkbd.ttf",25);
        checkPointTitle.setColor(ccColor3B.ccBLACK);
        checkPointTitle.setPosition(showPlan.getPosition().x, showPlan.getPosition().y + 200);
        checkPointTitle.setScale(1.2);
        addChild(checkPointTitle);

        setNextButton();
    }

    public void setNextButton(){
        CCSprite nDefault;
        CCSprite nPress;
        nextButton = CCMenu.menu();
        if(isLast){
            nDefault = CCSprite.sprite("menu/reButton.png");
            nPress = CCSprite.sprite("menu/reButtonP.png");
        }else {
            nDefault = CCSprite.sprite("menu/nextButton.png");
            nPress = CCSprite.sprite("menu/nextButtonP.png");
        }
        CCMenuItemSprite ccMenuItemSprite = CCMenuItemSprite.item(nDefault,nPress,this,"next");
        ccMenuItemSprite.setPosition(0,-300);
        nextButton.addChild(ccMenuItemSprite);
//        ccMenuItemSprite.setScaleX(1.2f);
        ccMenuItemSprite.setScaleX(2f);
        addChild(nextButton);

    }


    public void next(Object item){
        if (!isLast) {
            CCScene ccScene = CCScene.node();
            ccScene.addChild(new CombatLayer(ToolsSet.checkPoints[checkPoint.getCheck()]));
//        ccScene.addChild(new CombatLayer(ToolsSet.checkPoints[checkPoint.getCheck()+1]));
            CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2, ccScene);
            CCDirector.sharedDirector().runWithScene(ccFadeTransition);
        }else {
            CCScene ccScene = CCScene.node();
            ccScene.addChild(new MenuLayer());
//        ccScene.addChild(new CombatLayer(ToolsSet.checkPoints[checkPoint.getCheck()+1]));
            CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2, ccScene);
            CCDirector.sharedDirector().runWithScene(ccFadeTransition);
        }
    }
}
