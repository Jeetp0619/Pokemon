public class AttackDown extends PokemonDecorator{
  /** Decreases the attack of the Pokemon 
    * @param p - the pokemon that's getting its hp decreased 
    */
  public AttackDown(Pokemon p){
    super(p, "-ATK", 0);
  }
  /** Decreases the damage of the Pokemon 
    * @param atkType - The attack type that was choosen by the user
    */
  @Override
  public int getAttackBonus(int atkType){
    return super.getAttackBonus(atkType) - (int)((Math.random()* 2)+ 1);
  }
}