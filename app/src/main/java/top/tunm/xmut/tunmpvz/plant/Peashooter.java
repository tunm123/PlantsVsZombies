package top.tunm.xmut.tunmpvz.plant;


import top.tunm.xmut.tunmpvz.bullet.PeaBullet;

public class Peashooter extends ShooterPlant{
    public Peashooter() {
        super("plant/Peashooter/Frame%02d.png", 13);
        setPrice(100);
    }

    @Override
    public void createBullet(float t) {
        PeaBullet peaBullet=new PeaBullet(this);
    }
}
