public class HpUp extends PokemonDecorator{
  /** Increases the hp of the Pokemon 
    * @param p - the pokemon that's getting its hp increased 
    */
  public HpUp(Pokemon p){
    super(p, "+HP", (int) (Math.random()* 2)+ 1);
  }
}