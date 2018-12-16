package top.tunm.xmut.tunmpvz.plant;

import top.tunm.xmut.tunmpvz.effect.AEffect;
import top.tunm.xmut.tunmpvz.zombies.Zombie;

/**
 * Created by jingyuyan on 2018/12/15.
 */

public class Spikerock extends Spikeweed {

    public Spikerock() {
        super("plant/Spikerock/Frame%02d.png", 07,125,40);
    }

    @Override
    public void att(Zombie zombie){
        if(!getZombies().contains(zombie)){
            AEffect aEffect2 = new AEffect("eff/ci2/eff%02d.png" , 8,0.8f,0.1f);
            aEffect2.setPosition(ccp(zombie.getPosition().x,zombie.getPosition().y-20));
            getParent().addChild(aEffect2,6);
            getZombies().add(zombie);
            zombie.hurtCompute(getHurt());
            if (zombie.getHP() == 0) {
                zombie.death(0);
                zombie.removeSelf();
            }
        }
    }

}
