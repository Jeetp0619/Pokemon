/** Representation of an Entity */
public abstract class Entity{
  private String name;
  private int hp;
  private int maxHP;
  
  /** Retrieve the name and max hp of the Entity
    * @param n sets the name of the Entity
    * @param mHp sets the max hp of the Entity
    */
  public Entity(String n, int h,int mHp){
    name = n;
    maxHP = mHp;
    hp = h;
  }

  /** Grabs the hp
    * @return hp the health of the Entity
    */
  public int getHp(){
    return hp;
  }

  /** Grabs the Max HP
    * @return maxHP the health of the Entity
    */
  public int getMaxHp(){
    return maxHP;
  }

  /** Allows the Entity take damage
    * @param d the amount of damage the Entity takes
    */
  public void takeDamage(int d){
    if (this.hp - d > 0){
      this.hp -= d;
    }else{
      this.hp = 0;
    }
  }

  /** Heals the Entity back to full health */
  public void heal(){
    this.hp = this.maxHP;
  }

  /** Grabs the name of the Enity 
    * @return name the name given to the Enity.
    */
  public String getName(){
    return name;
  }
  
  /** String representation of a Trainer object
     *  @return string representation of Trainer
     */
  @Override
  public String toString(){
    // Name HP: hp/maxHp
    return name + " HP: " + String.valueOf(hp) + "/" + String.valueOf(maxHP); 
  }
}