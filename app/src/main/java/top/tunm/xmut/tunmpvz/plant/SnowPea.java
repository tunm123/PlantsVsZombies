package top.tunm.xmut.tunmpvz.plant;


import top.tunm.xmut.tunmpvz.bullet.SnowBullet;

public class SnowPea extends ShooterPlant{
    public SnowPea() {
        super("plant/SnowPea/Frame%02d.png", 15);
        setPrice(175);
    }

    @Override
    public void createBullet(float t) {
        SnowBullet snowBullet=new SnowBullet(this);
    }
}
