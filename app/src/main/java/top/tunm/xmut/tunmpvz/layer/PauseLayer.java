package top.tunm.xmut.tunmpvz.layer;

import android.view.MotionEvent;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import top.tunm.xmut.tunmpvz.ToolsSet;

/**
 * Created by jingyuyan on 2018/12/10.
 */

public class PauseLayer extends CCLayer {

    private CCSprite back;
    private CGSize winSize;
    private CombatLayer combatLayer;
    private CCMenu comeBackMenu;
    private CCMenu comeMainMenu;
    private CCMenu viewMenu;
    private CCSprite bgmBtn;
    private CCSprite effBtn;

    public PauseLayer(CombatLayer combatLayer){
        this.combatLayer = combatLayer;
        back = CCSprite.sprite("menu/OptionsMenuback32.png");
        winSize = CCDirector.sharedDirector().getWinSize();
        back.setPosition(winSize.width/2,winSize.height/2);
        addChild(back);

        // 回到游戏按钮
        comeBackMenu= CCMenu.menu();
        CCSprite pushDefault=
                CCSprite.sprite("menu/push.png");
        CCSprite pushPress=
                CCSprite.sprite("menu/pushed.png");
        CCMenuItemSprite ccMenuItemSprite= CCMenuItemSprite.item(pushDefault,
                pushPress,this,"comeBack");
        ccMenuItemSprite.setPosition(getPosition().x,getPosition().y-190);
        comeBackMenu.addChild(ccMenuItemSprite);


        // 回到主菜单按钮
        comeMainMenu = CCMenu.menu();
        pushDefault = CCSprite.sprite("menu/remain.png");
        pushPress = CCSprite.sprite("menu/remained.png");
        CCMenuItemSprite remainMenuItemSprite= CCMenuItemSprite.item(pushDefault,
                pushPress,this,"remain");
        remainMenuItemSprite.setPosition(getPosition().x,getPosition().y-100);
        remainMenuItemSprite.setScale(0.5f);
        comeMainMenu.addChild(remainMenuItemSprite);


        // 图鉴菜单
        viewMenu = CCMenu.menu();
        pushDefault = CCSprite.sprite("menu/view.png");
        pushPress = CCSprite.sprite("menu/viewed.png");
        CCMenuItemSprite remainMenuItemSprite1= CCMenuItemSprite.item(pushDefault,
                pushPress,this,"view");
        remainMenuItemSprite1.setPosition(getPosition().x,getPosition().y-50);
        remainMenuItemSprite1.setScale(0.5f);
        viewMenu.addChild(remainMenuItemSprite1);

        // 重新开始本关

        addChild(comeBackMenu);
        addChild(comeMainMenu);
        addChild(viewMenu);
        setIsTouchEnabled(true);


        CCLabel bgmLabel = CCLabel.makeLabel("背景音乐：","hkbd.ttf",20);
        bgmLabel.setColor(ccColor3B.ccWHITE);
        bgmLabel.setPosition(620,480);
        addChild(bgmLabel);

        CCLabel effLabel = CCLabel.makeLabel("游戏音效：","hkbd.ttf",20);
        effLabel.setColor(ccColor3B.ccWHITE);
        effLabel.setPosition(620,400);
        addChild(effLabel);

        // 音量和音效选择键
        if (ToolsSet.isBGMSound) {
            bgmBtn = CCSprite.sprite("choose/soundp.png");
            bgmBtn.setScale(0.8);
            bgmBtn.setPosition(700 , 480 );
            addChild(bgmBtn);
        }
        else {
            bgmBtn = CCSprite.sprite("choose/sound.png");
            bgmBtn.setScale(0.8);
            bgmBtn.setPosition(700 , 480 );
            addChild(bgmBtn);
        }

        if (ToolsSet.isEffectSound) {
            effBtn = CCSprite.sprite("choose/soundp.png");
            effBtn.setScale(0.8);
            effBtn.setPosition(700 , 400 );
            addChild(effBtn);
        }else {
            effBtn = CCSprite.sprite("choose/sound.png");
            effBtn.setScale(0.8);
            effBtn.setPosition(700 , 400 );
            addChild(effBtn);
        }
    }

    public void remain(Object item){
        System.out.println("回到主菜单");
        combatLayer.onEnter();
        combatLayer.end();
        removeSelf();
    }

    public void comeBack(Object item){
        System.out.println("回到游戏");
        combatLayer.onEnter();
        System.out.println("继续游戏");
        removeSelf();
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        System.out.println("暂停层点击");
        CGPoint cgPoint = convertTouchToNodeSpace(event);
        if (CGRect.containsPoint(bgmBtn.getBoundingBox(), cgPoint)){
            if (ToolsSet.isBGMSound){
                ToolsSet.bgm.pauseSound();
                ToolsSet.isBGMSound = false;
                bgmBtn.removeSelf();
                bgmBtn = CCSprite.sprite("choose/sound.png");
                bgmBtn.setScale(0.8);
                bgmBtn.setPosition(700 , 480 );
                addChild(bgmBtn);
            }else {
                ToolsSet.bgm.resumeSound();
                ToolsSet.isBGMSound = true;
                bgmBtn.removeSelf();
                bgmBtn = CCSprite.sprite("choose/soundp.png");
                bgmBtn.setScale(0.8);
                bgmBtn.setPosition(700 , 480 );
                addChild(bgmBtn);
            }
        }else if (CGRect.containsPoint(effBtn.getBoundingBox(), cgPoint)){
            if (ToolsSet.isEffectSound){
                ToolsSet.isEffectSound = false;
                effBtn.removeSelf();
                effBtn = CCSprite.sprite("choose/sound.png");
                effBtn.setScale(0.8);
                effBtn.setPosition(700 , 400 );
                addChild(effBtn);
            }else {
                ToolsSet.isEffectSound = true;
                effBtn.removeSelf();
                effBtn = CCSprite.sprite("choose/soundp.png");
                effBtn.setScale(0.8);
                effBtn.setPosition(700 , 400 );
                addChild(effBtn);
            }
        }

        return super.ccTouchesEnded(event);
    }

    public void view(Object item){
        System.out.println("打开图鉴");
        combatLayer.onExit();
        removeSelf();
        combatLayer.getParent().addChild(new AlmanacLayer(combatLayer));
    }
}
