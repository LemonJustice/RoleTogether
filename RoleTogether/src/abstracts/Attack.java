package abstracts;

import main.Game;

//Attacks within batttle
public abstract class Attack {
  
  protected int mod;        //The amount a stat is modified for an attack
  protected int stat;        //which stat is modified: str = 1, intl = 2, def = 3, res = 4, spd = 5
  protected int statDebuff;       //Long-term debuff to an enemy's specified stat: look up for key
  protected int effectApplied;        // what status is applied: insta-kill = 0, poison = 1
  protected int hitRate;          // how likely is it to hit
  
  protected Attack attack;
  
  public Attack(int stat, int mod, int statDebuff, int effectApplied, int hitRate,){
    this.stat = stat;
    this.mod = mod;
    this.statDebuff = statDebuff;
    this.effectApplied = effectApplied;
    this.hitRate = hitRate;
    
  }
  
 public abstract void render(graphics g);
  
 public abstract void update();
  
 public abstract void target1(Attack attack);
  
 public abstract void target2(Attack attack)
    
 public abstract void target3(Attack attack)
    
 public abstract void target4(Attack attack)
  
}
