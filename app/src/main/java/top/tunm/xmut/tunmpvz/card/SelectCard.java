package top.tunm.xmut.tunmpvz.card;

import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.ccColor3B;

import top.tunm.xmut.tunmpvz.ToolsSet;

/**
 * Created by jingyuyan on 2018/12/12.
 */

public class SelectCard {

    private int id;
    private CCSprite back;
    private CCLabel checkNum;

    public SelectCard(int id){
        this.id = id;
        if (ToolsSet.isIsNight()) {
            back = CCSprite.sprite("interface/check.png");
        }else {
            back = CCSprite.sprite("interface/check0.png");
        }
        checkNum = CCLabel.makeLabel((id+1)+"","hkbd.ttf",25);
        back.setScale(2f);
        checkNum.setColor(ccColor3B.ccBLACK);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CCSprite getBack() {
        return back;
    }

    public void setBack(CCSprite back) {
        this.back = back;
    }

    public CCLabel getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(CCLabel checkNum) {
        this.checkNum = checkNum;
    }
}
