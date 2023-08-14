package Entity.Weapon;

import Entity.Weapon.Bullets.GoldenBullet;

public class GoldenAK extends Gun{
    public GoldenAK() {
        super("Golden AK BABABABA" , 12);
        Magazine magazine = new Magazine("magazine" , 50 , 2);
        while(!magazine.isFull()){
            magazine.pushBullet(new GoldenBullet());
        }
        this.reload(magazine);
    }
}
