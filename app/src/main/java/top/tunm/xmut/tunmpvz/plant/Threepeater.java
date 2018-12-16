package top.tunm.xmut.tunmpvz.plant;

import org.cocos2d.types.CGPoint;

import java.util.ArrayList;

import top.tunm.xmut.tunmpvz.layer.CombatLine;
import top.tunm.xmut.tunmpvz.ToolsSet;
import top.tunm.xmut.tunmpvz.bullet.ThreeBullet;

/**
 * Created by jingyuyan on 2018/12/7.
 */

public class Threepeater extends ShooterPlant {

    private int col;
    private int row;
    private ArrayList<CombatLine> combatLines;



    private CGPoint pointUP;
    private CGPoint pointDown;
    private boolean isf;

    private float ang;

    public Threepeater() {
        super("plant/Threepeater/Frame%02d.png", 16);
        setPrice(175);
    }

    public void setAng(int col,int row){
        this.col = col;
        this.row = row;
        System.out.println("意图点-正前方："+col+":"+row);
        if (row==4){
            System.out.println("意图点-上：无上界");
            System.out.println("意图点-下："+(col+1)+":"+(row-1));

            ang = 60;
            ang = ang - 90;
            float x = (float) (getPosition().x + 150 * Math.cos(ang*(Math.PI/180)));
            float y = (float) (getPosition().y + 150 * Math.sin(ang*(Math.PI/180)));
            pointDown = new CGPoint();
            pointDown.set(x,y);
            ToolsSet.shooterPlansArrays.get(row-1).add(this);

        }else if(row==0){
            ang = 60;
            float x = (float) (getPosition().x + 180 * Math.cos(ang*(Math.PI/180)));
            float y = (float) (getPosition().y + 180 * Math.sin(ang*(Math.PI/180)));


            pointUP = new CGPoint();
            pointUP.set(x,y);
            ToolsSet.shooterPlansArrays.get(row+1).add(this);
        }else {
            System.out.println("意图点-上："+(col+1)+":"+(row+1));
            System.out.println("意图点-下："+(col+1)+":"+(row-1));

            ang = 60;
            float x = (float) (getPosition().x + 180 * Math.cos(ang*(Math.PI/180)));
            float y = (float) (getPosition().y + 180 * Math.sin(ang*(Math.PI/180)));


            pointUP = new CGPoint();
            pointUP.set(x,y);

            ang = ang - 90;
            x = (float) (getPosition().x + 150 * Math.cos(ang*(Math.PI/180)));
            y = (float) (getPosition().y + 150 * Math.sin(ang*(Math.PI/180)));
            pointDown = new CGPoint();
            pointDown.set(x,y);

            ToolsSet.shooterPlansArrays.get(row+1).add(this);
            ToolsSet.shooterPlansArrays.get(row-1).add(this);

        }
        isf = true;

    }


    @Override
    public void createBullet(float t) {
        System.out.println("创建子弹中.....");
        if (isf){
            System.out.println("意图 创建子弹");
            ThreeBullet threeBullet = new ThreeBullet(this,row);
            if (pointUP !=null){
                System.out.println("意图 创建上子弹 "+(row+1));
                ThreeBullet threeBulletdowb = new ThreeBullet(this, pointUP,row+1);
            }
            if (pointDown !=null){
                System.out.println("意图 创建下子弹 "+(row-1));
                ThreeBullet threeBulletup = new ThreeBullet(this, pointDown,row-1);
            }
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
