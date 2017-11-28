package abstracts;

import main.Game;

//Attacks within batttle
public abstract class Attack {
  
  protected int mod;        //The amount a stat is modified by
  protected int statMod;        //which stat is modified: str = 1, intl = 2, def = 3, res = 4, spd = 5
  proteted boolean isStatEffect;      //Does it apply a stat effect
  protected int effectApplied;        // what status is applied: poison = 1...
  
  public Attack(int statMod, int mod, boolean isStatEffect, int effectApplied){
  
  }
  
}
