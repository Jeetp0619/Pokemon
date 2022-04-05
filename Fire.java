public class Fire extends Pokemon{
  /** Creates a Fire pokemon
    * @param n sets the name of the Pokemon
    * @param h - the hp of the pokemon
    * @param m - the max hp of the pokemon
    */
  public Fire(String n, int h, int m){
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
    else if (atkType == 2){
        return "1. Ember\n2. Fire Blast\n3. Fire Punch";
    }
    return " ";
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
        case 1: return "EMBERS";
        case 2: return "FIRE BLASTED";
        case 3: return "FIRE PUNCHED";
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
        case 1: return (int)((Math.random() * 4));
        case 2: return (int)((Math.random() * 4) + 1);
        case 3: return (int)((Math.random() * 3) + 1);
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