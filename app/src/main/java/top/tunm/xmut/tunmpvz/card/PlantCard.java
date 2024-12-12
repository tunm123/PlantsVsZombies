package top.tunm.xmut.tunmpvz.card;

import org.cocos2d.nodes.CCSprite;
import java.io.IOException;
import java.util.Locale;
import top.tunm.xmut.tunmpvz.plant.Plant;

public class PlantCard {
    private int id;
    private CCSprite light;
    private CCSprite dark;

    // 第一个构造函数示例修改
    public PlantCard(int id) {
        this.id = id;
        try {
            light = loadSprite(String.format(Locale.CHINA, "choose/p%02d.png", id));
            dark = loadSprite(String.format(Locale.CHINA, "choose/p%02d.png", id));
            dark.setOpacity(100);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("构造植物卡片时加载图片出现错误，id: " + id);
        }
    }

    // 辅助方法用于加载图片并处理可能的加载失败情况
    private CCSprite loadSprite(String fileName) {
        CCSprite sprite = CCSprite.sprite(fileName);
        if (sprite == null) {
            throw new RuntimeException("图片加载失败，文件名: " + fileName);
        }
        return sprite;
    }

    // 其他代码保持不变，比如第二个构造函数等

    public int getId() {
        return id;
    }

    public CCSprite getLight() {
        return light;
    }

    public CCSprite getDark() {
        return dark;
    }
}