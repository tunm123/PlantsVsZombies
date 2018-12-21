package top.tunm.xmut.tunmpvz.plant;

import top.tunm.xmut.tunmpvz.bullet.CactusButtlet;

/**
 * Created by jingyuyan on 2018/12/17.
 */

public class Cactus extends ShooterPlant{

    public Cactus(){
        super("plant/Cactus/Frame%02d.png",11);
        setPrice(120);
    }

    @Override
    public void createBullet(float t) {
        CactusButtlet cabbagePult = new CactusButtlet("bullet/CB.png",this);
    }
}
