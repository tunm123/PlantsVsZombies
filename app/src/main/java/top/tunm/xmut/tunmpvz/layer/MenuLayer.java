package top.tunm.xmut.tunmpvz.layer;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCFadeTransition;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;


class MenuLayer extends CCLayer {
    public MenuLayer(){
        ToolsSet.bgmSound(R.raw.bgm);
        CCSprite ccSprite_menu= CCSprite.sprite("menu/main_menu_bg.png");
        ccSprite_menu.setAnchorPoint(0,0);
        addChild(ccSprite_menu);

        CCMenu ccMenu= CCMenu.menu();
        CCSprite ccSprite_start_adventure_default=
                CCSprite.sprite("menu/start_adventure_default.png");
        CCSprite ccSprite_start_adventure_press=
                CCSprite.sprite("menu/start_adventure_press.png");
        CCMenuItemSprite ccMenuItemSprite= CCMenuItemSprite.item(ccSprite_start_adventure_default,
                ccSprite_start_adventure_press,this,"start");
        ccMenuItemSprite.setPosition(270,160);
        ccMenu.addChild(ccMenuItemSprite);
        addChild(ccMenu);

        //

        CCMenu ccMenu2= CCMenu.menu();
        CCSprite ccSprite_start_adventure_default2=
                CCSprite.sprite("menu/look.png");
        CCSprite ccSprite_start_adventure_press2=
                CCSprite.sprite("menu/looked.png");
        CCMenuItemSprite ccMenuItemSprite2= CCMenuItemSprite.item(ccSprite_start_adventure_default2,
                ccSprite_start_adventure_press2,this,"alamanac");
        ccMenuItemSprite2.setPosition(250,-10);
        ccMenuItemSprite2.setScale(1.5f);
        ccMenu.addChild(ccMenuItemSprite2);
        addChild(ccMenu2);

        // 说明

        CCSprite shanzai = CCSprite.sprite("interface/sanzhai.png");
        shanzai.setAnchorPoint(0,0);
        shanzai.setPosition(0,400);
        shanzai.setScale(0.7);
        addChild(shanzai);

        // 退出按键
        // 退出按键
        CCMenu exitMenu = CCMenu.menu();
        CCSprite exit_default=
                CCSprite.sprite("menu/exit.png");
        CCSprite exit_press=
                CCSprite.sprite("menu/exited.png");
        CCMenuItemSprite ccMenuItemSprite1= CCMenuItemSprite.item(exit_default,
                exit_press,this,"exit");
        ccMenuItemSprite1.setAnchorPoint(0,0);
        ccMenuItemSprite1.setPosition(-580,-300);
        ccMenuItemSprite1.setScale(0.6f);
        exitMenu.addChild(ccMenuItemSprite1);
        addChild(exitMenu);
    }

    public void exit(Object item){
        System.exit(0);
    }

    public void start(Object item){
        ToolsSet.effectSound(R.raw.dight);
        CCScene ccScene= CCScene.node();
//        ccScene.addChild(new CombatLayer(ToolsSet.checkPoints[0]));
        ccScene.addChild(new CheckSelectorLayer());
        CCFadeTransition ccFadeTransition= CCFadeTransition.transition(2,ccScene);
        CCDirector.sharedDirector().runWithScene(ccFadeTransition);

    }


    public void alamanac(Object item){
        ToolsSet.effectSound(R.raw.dight);
        CCScene ccScene = CCScene.node();
        ccScene.addChild(new AlmanacLayer(this));
        CCFadeTransition ccFadeTransition = CCFadeTransition.transition(2,ccScene);
        CCDirector.sharedDirector().runWithScene(ccFadeTransition);
    }

}