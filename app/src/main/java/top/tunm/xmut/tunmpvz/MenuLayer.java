package top.tunm.xmut.tunmpvz;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;


class MenuLayer extends CCLayer {
    public MenuLayer(){
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
    }
    public void start(Object item){
        CCScene ccScene= CCScene.node();
        ccScene.addChild(new CombatLayer());
        CCFadeTransition ccFadeTransition= CCFadeTransition.transition(2,ccScene);
        CCDirector.sharedDirector().runWithScene(ccFadeTransition);
    }
}