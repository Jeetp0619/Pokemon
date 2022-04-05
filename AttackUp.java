public class AttackUp extends PokemonDecorator{
  /** Increases the attack of the Pokemon 
    * @param p - the Pokemon that's getting passed it
    */
  public AttackUp(Pokemon p){
    super(p, "+ATK", 0);
  }
  /** Increases the damage of the Pokemon 
    * @param atkType - The attack type that was choosen by the user
    */
  @Override
  public int getAttackBonus(int atkType){
    return super.getAttackBonus(atkType) + (int)((Math.random()* 2)+ 1);
  }
}