 public abstract class PokemonDecorator extends Pokemon{
  private Pokemon pokemon;

/** Constructor - creates Pokemondecorator for a pokemon object
  * @param p - pokemon that is getting passed in
  * @param extraName - displays buffs or debuffs 
  * @param extraHp - adds the increase/decrease in hp to the pokemon
  */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp){
   super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
   pokemon = p;
  }
/** Returns attack menu for the corresponding pokemon type
  * @param atkType - attack type the user's choice
  * @return - returns the attack menu of the type of pokemon
  */
  @Override
  public String getAttackMenu(int atkType){
    return pokemon.getAttackMenu(atkType);
  }
/** Returns the number of attack menu items for the corresponding pokemon type
  * @param atkType - attack type the user's choice
  * @return - returns the number of attack menu items of the type of pokemon
  */
  @Override
  public int getNumAttackMenuItems(int atkType){
    return pokemon.getNumAttackMenuItems(atkType);
  }
/** Returns the elemental attack string for the corresponding pokemon type
  * @param atkType - attack type the user's choice
  * @param move - The move of the user's choice
  * @return - returns elemental attack string of the type of pokemon
  */
  @Override
  public String getAttackString(int atkType, int move){
    return pokemon.getAttackString(atkType, move);
  }
/** Returns the amount of attack damage for the corresponding pokemon type
  * @param atkType - attack type the user's choice
  * @param move - The move of the user's choice
  * @return - returns the amount of attack damage of the type of pokemon
  */
  @Override
  public int getAttackDamage(int atkType, int move){
    return pokemon.getAttackDamage(atkType, move);
  }
/** Returns attack mulitiplier damage for the corresponding pokemon type
  * @param p - pokemon that is getting passed in
  * @param atkType - attack type the user's choice
  * @return - returns attack mulitiplier damage of the type of pokemon
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    return pokemon.getAttackMultiplier(p,atkType);
  }
/** Returns attack bonus damage for the corresponding pokemon type
  * @param atkType - attack type the user's choice
  * @return - returns attack bonus damage of the type of pokemon
  */
  @Override
  public int getAttackBonus(int atkType){
    return pokemon.getAttackBonus(atkType);
  }
/** Returns the pokemon type 
  * @return - returns the pokemon type
  */
  @Override
  public int getType(){
    return pokemon.getType();
  }
}