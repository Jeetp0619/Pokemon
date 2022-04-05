public class HpDown extends PokemonDecorator{
  /** Decreases the hp of the Pokemon 
    * @param p - the pokemon that's getting its hp decreased 
    */
  public HpDown(Pokemon p){
    super(p, "-Hp", -1);
  }
}