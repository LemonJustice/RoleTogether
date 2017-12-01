package abstracts;

import main.Game;
import java.awt.Graphics;

//Attacks within batttle
public abstract class Attack {
  
  private int mod;        //The amount a stat is modified for an attack
  private int stat;        //which stat is modified: str = 1, intl = 2, def = 3, res = 4, spd = 5
  private int statDebuff;       //Long-term debuff to an enemy's specified stat: look up for key
  private int effectApplied;        // what status is applied: insta-kill = 0, poison = 1
  private int hitRate;          // how likely is it to hit
  
  private Attack attack;
  
  public Attack(int stat, int mod, int statDebuff, int effectApplied, int hitRate){
    this.stat = stat;
    this.mod = mod;
    this.statDebuff = statDebuff;
    this.effectApplied = effectApplied;
    this.hitRate = hitRate;
    
  }
 
 public abstract void render(Graphics g);
  
 public abstract void update();
  
 public abstract void target1(Attack attack);
  
 public abstract void target2(Attack attack);
    
 public abstract void target3(Attack attack);
    
 public abstract void target4(Attack attack);
  
}
