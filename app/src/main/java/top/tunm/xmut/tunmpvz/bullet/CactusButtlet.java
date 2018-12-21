package top.tunm.xmut.tunmpvz.bullet;

import top.tunm.xmut.tunmpvz.R;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.plant.ShooterPlant;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/17.
 */

public class CactusButtlet extends Bullet {

    public CactusButtlet(String filepath, ShooterPlant shooterPlant) {
        super(filepath, shooterPlant);
    }

    @Override
    public void showBulletBlast(Zombie zombie) {
        AEffect aEffect = new AEffect("eff/CB/Frame%02d.png", 3,0.3f,0.1f);
        aEffect.setPosition(ccp(zombie.getPosition().x, zombie.getPosition().y-10));
        getParent().addChild(aEffect, 6);
        ToolsSet.effectSound(R.raw.fight);
    }

}
