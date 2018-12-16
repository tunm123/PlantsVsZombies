package top.tunm.xmut.tunmpvz.layer;

import android.view.MotionEvent;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCJumpZoomTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;
import java.util.Locale;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;


public class LogoLayer extends CCLayer {
    public LogoLayer(){
        logo1();

        ToolsSet.bgm = SoundEngine.sharedEngine();
        ToolsSet.bgm.playSound(CCDirector.sharedDirector().getActivity(), R.raw.bgm,true);
    }

    private void logo1() {
        CCSprite ccSprite_logo1= CCSprite.sprite("logo/logo1.png");
        ccSprite_logo1.setAnchorPoint(0,0);
        addChild(ccSprite_logo1);
        CCDelayTime ccDelayTime= CCDelayTime.action(2);
        CCHide ccHide= CCHide.action();
        CCCallFunc ccCallFunc= CCCallFunc.action(this,"logo2");
        CCSequence ccSequence= CCSequence.actions(ccDelayTime,ccHide,ccCallFunc);
        ccSprite_logo1.runAction(ccSequence);
    }
    public void logo2(){
        CCSprite ccSprite_logo2= CCSprite.sprite("logo/logo2.png");
        CGSize winSize= CCDirector.sharedDirector().winSize();
        ccSprite_logo2.setPosition(winSize.getWidth()/2,winSize.getHeight()/2);
        addChild(ccSprite_logo2);
        CCDelayTime ccDelayTime= CCDelayTime.action(2);
        CCHide ccHide= CCHide.action();
        CCCallFunc ccCallFunc= CCCallFunc.action(this,"cg");
        CCSequence ccSequence= CCSequence.actions(ccDelayTime,ccHide,ccCallFunc);
        ccSprite_logo2.runAction(ccSequence);
    }
    public void cg(){
        CCSprite ccSprite_cg= CCSprite.sprite("cg/bg.jpg");
        addChild(ccSprite_cg);
        CGSize winSize = CCDirector.sharedDirector().getWinSize();
        ccSprite_cg.setPosition(winSize.getWidth()/2,winSize.getHeight()/2);
        ccSprite_cg.setScaleX(winSize.getWidth()/ccSprite_cg.getTextureRect().size.getWidth());
        ccSprite_cg.setScaleY(winSize.getHeight()/ccSprite_cg.getTextureRect().size.getHeight());

        CCSprite loding = CCSprite.sprite("cg/loading_01.png");
        loding.setPosition(winSize.getWidth()/2,80);
        loding.setScale(1.9);
        addChild(loding);

        ArrayList<CCSpriteFrame> frames=new ArrayList<>();
        for (int i = 1; i <11 ; i++) {
            CCSpriteFrame ccSpriteFrame= CCSprite.sprite(String.format(Locale.CHINA,
                    "cg/loading_%02d.png",i)).displayedFrame();
            frames.add(ccSpriteFrame);
        }
        CCAnimation ccAnimation= CCAnimation.animationWithFrames(frames,0.2f);
        CCAnimate ccAnimate= CCAnimate.action(ccAnimation,false);
        CCCallFunc ccCallFunc= CCCallFunc.action(this,"setTouch");
        CCSequence ccSequence= CCSequence.actions(ccAnimate,ccCallFunc);
        loding.runAction(ccSequence);
    }
    public void setTouch(){
        setIsTouchEnabled(true);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint cgPoint=convertTouchToNodeSpace(event);
        CGRect cgRect= CGRect.make(390,30,490,60);
        if(CGRect.containsPoint(cgRect,cgPoint)){
            CCScene ccScene= CCScene.node();
//            ccScene.addChild(new AlongAlanLayer(ToolsSet.checkPoints[4]));
            ccScene.addChild(new MenuLayer());
            CCJumpZoomTransition ccJumpZoomTransition= CCJumpZoomTransition.transition(2,ccScene);
            CCDirector.sharedDirector().runWithScene(ccJumpZoomTransition);
        }
        return super.ccTouchesBegan(event);
    }
}
