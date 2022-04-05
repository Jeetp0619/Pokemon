public class Grass extends Pokemon{
  /** Creates a Grass pokemon
    * @param n sets the name of the Pokemon
    * @param h - the hp of the pokemon
    * @param m - the max hp of the pokemon
    */
  public Grass(String n, int h, int m){
    super(n,h,m);
  }
  /** Retrieve Pokemons attack menu
  * @return - returns basic attack menu for Pokemon
  */
  @Override
  public String getAttackMenu(int atkType){
    if (atkType == 1){
      return super.getAttackMenu(atkType);
    }
    else{
        return "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
    }
  }
  /** Retrieve the number of attack menu items
  * @param atkType - the user's choice of attack type
  * @return - returns the attack list of attacks
  */
  public int getNumAttackMenu(int atkType){
     return 3;
  }
  /** Retrieve the number of attack menu items
    * @param atkType - the user's choice of attack type
    * @param move - the move choosen by the user
    * @return - returns the number of attacks in the attack menu
    */
  @Override
  public String getAttackString(int atkType, int move){
    if (atkType == 1){
      return super.getAttackString(atkType,move);
    }
    else if (atkType == 2){
      switch(move){
        case 1: return "WHIPPED";
        case 2: return "RAZOR LEAF";
        case 3: return "SOLAR BEAM";
      }
    }
    return " ";
  }
  /** Retrieve total attack damage
    * @param atkType - the type of attack being done
    * @param move - the move selected by the trainer
    * @return - returns the generated damage based on the pokemon's type
    */
  @Override
  public int getAttackDamage(int atkType, int move){
    if (atkType == 1){
      return super.getAttackDamage(atkType, move);
    }
    else {
      switch (move){
        case 1: return (int)((Math.random() * 3) + 1);
        case 2: return (int)((Math.random() * 3) + 2);
        case 3: return (int)((Math.random() * 6));
      }
    }
    return 0;
  }
  /** Multiplies the attack of the Pokemon
    * @param p - pokemon that is getting passed in
    * @param atkType - the selected attack 1-3
    * @return - returns amount the attack is to be multiplied by
    */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    return super.getAttackMultiplier(p, atkType);
  }
}