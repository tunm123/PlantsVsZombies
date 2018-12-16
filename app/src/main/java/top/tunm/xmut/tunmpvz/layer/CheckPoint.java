package top.tunm.xmut.tunmpvz.layer;

/**
 * Created by jingyuyan on 2018/12/10.
 */

public class CheckPoint {
    private int check;
    private int zombiesCount;
    private int newCardID;
    private int zombiesCard;
    private float zombies0Rate = 0;
    private float zombies1Rate = 0;
    private float zombies2Rate = 0;
    private float zombies3Rate = 0;
    private float zombies4Rate = 0;
    private float zombies5Rate = 0;

    public static int currCheck = 0;



    public CheckPoint next;

    public CheckPoint(int check, int zombiesCount,int newCardID) {
        this.check = check;
        this.zombiesCount = zombiesCount;
        this.newCardID = newCardID;
        currCheck ++;
    }

    public CheckPoint(int check, int zombiesCount,float z0,float z1 ,float z2,float z3,int newCardID,int zombiesCard) {
        this.check = check;
        this.zombiesCount = zombiesCount;
        this.newCardID = newCardID;
        this.zombiesCard = zombiesCard;
        this.zombies0Rate = z0;
        this.zombies1Rate = z1;
        this.zombies2Rate = z2;
        this.zombies3Rate = z3;
        currCheck ++;
    }

    public CheckPoint(int check, int zombiesCount,float z0,float z1 ,float z2,float z3,float z4,int newCardID,int zombiesCard) {
        this.check = check;
        this.zombiesCount = zombiesCount;
        this.newCardID = newCardID;
        this.zombiesCard = zombiesCard;
        this.zombies0Rate = z0;
        this.zombies1Rate = z1;
        this.zombies2Rate = z2;
        this.zombies3Rate = z3;
        this.zombies4Rate = z4;
        currCheck ++;
    }

    public CheckPoint(int check, int zombiesCount,float z0,float z1 ,float z2,float z3,float z4,float z5,int newCardID,int zombiesCard) {
        this.check = check;
        this.zombiesCount = zombiesCount;
        this.newCardID = newCardID;
        this.zombiesCard = zombiesCard;
        this.zombies0Rate = z0;
        this.zombies1Rate = z1;
        this.zombies2Rate = z2;
        this.zombies3Rate = z3;
        this.zombies4Rate = z4;
        this.zombies5Rate = z5;
        currCheck ++;
    }


    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getZombiesCount() {
        return zombiesCount;
    }

    public void setZombiesCount(int zombiesCount) {
        this.zombiesCount = zombiesCount;
    }

    public int getNewCardID() {
        return newCardID;
    }

    public void setNewCardID(int newCardID) {
        this.newCardID = newCardID;
    }

    public static int getCurrCheck() {
        return currCheck;
    }

    public static void setCurrCheck(int currCheck) {
        CheckPoint.currCheck = currCheck;
    }

    public CheckPoint getNext() {
        return next;
    }

    public void setNext(CheckPoint next) {
        this.next = next;
    }

    public int getZombiesCard() {
        return zombiesCard;
    }

    public void setZombiesCard(int zombiesCard) {
        this.zombiesCard = zombiesCard;
    }


    public float getZombies0Rate() {
        return zombies0Rate;
    }

    public void setZombies0Rate(float zombies0Rate) {
        this.zombies0Rate = zombies0Rate;
    }

    public float getZombies1Rate() {
        return zombies1Rate;
    }

    public void setZombies1Rate(float zombies1Rate) {
        this.zombies1Rate = zombies1Rate;
    }

    public float getZombies2Rate() {
        return zombies2Rate;
    }

    public void setZombies2Rate(float zombies2Rate) {
        this.zombies2Rate = zombies2Rate;
    }

    public float getZombies3Rate() {
        return zombies3Rate;
    }

    public void setZombies3Rate(float zombies3Rate) {
        this.zombies3Rate = zombies3Rate;
    }

    public float getZombies4Rate() {
        return zombies4Rate;
    }

    public float getZombies5Rate() {
        return zombies5Rate;
    }

    public void setZombies5Rate(float zombies5Rate) {
        this.zombies5Rate = zombies5Rate;
    }

    public void setZombies4Rate(float zombies4Rate) {
        this.zombies4Rate = zombies4Rate;
    }
}
