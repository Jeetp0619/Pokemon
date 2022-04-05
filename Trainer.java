/** Representation of Trainer, which is an Entity. */
import java.util.*;
import java.awt.Point;
public class Trainer extends Entity{
  private int money;
  private int potions;
  private int pokeballs;
  private Point loc;
  private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

  /** Overloaded Constructor – makes rectangle w/ parameters 
    *  @param n name of the trainer
    *  @param p the first pokemon the trainer chooses
    *  @param m initializes the map
    */
  public Trainer(String n, Pokemon p){
    super(n, 25, 25);
    pokemon.add(p);
    money = 25;
    potions = 1;
    pokeballs = 5;
    Map map = Map.getInstance();
    map.loadMap(1);
    loc = map.findStart();
  }

  /** Return the amount of money the Trainer has 
    * @return money the amount of money the Trainer has.
    */
  public int getMoney(){
    return money;
  }

  /** Increases the amount of money the Trainer has 
    * @param  amt   how much the trianer is trying to use
    * @return true  if the use has enough money
    * @return false if the user doesnt have enough money
    */
  public boolean spendMoney(int amt){
    if((getMoney() - amt) > 0){
      money -= amt;
      return true;
    }
    else{
      return false;
    }
  }

  /** Increases the amount of money the Trainer has 
    * @param amt how much the Trainer recieved 
    */
  public void recieveMoney(int amt){
    money += amt;
  }

  /** Check if the Trainer has enough potions 
    * @return true Trainer has enough potions
    * @return false Trainer doesn't have enough potions
    */
  public boolean hasPotion(){
    if (potions == 0){
      return false;
    }
    else{
      return true;
    }
  }
  
  /** Increases the amount of potions the Trainer has */
  public void recievePotion(){
    potions += 1;
  }

  /** Uses a potion to heal a pokemon 
    * @param pokeIndex used to get the pokemon from the ArrayList
    */
  public void usePotion(int pokeIndex){
    if (hasPotion()){
      Pokemon p = pokemon.get(pokeIndex);
      PokemonGenerator gen = PokemonGenerator.getInstance();
      potions -= 1;
      p.heal();
      p = gen.addPokemonBuff(p);
      pokemon.remove(pokeIndex);
      pokemon.add(pokeIndex, p);
    }
  }

  /** Check if the Trainer has enough Pokeballs
    * @return true has enough pokeballs
    * @return false doesnt have enough pokeballs
    */
  public boolean hasPokeball(){
    if (pokeballs == 0){
      return false;
    }
    else{
      return true;
    }
  }

  /** Increases the amount of pokeballs the Trainer has */
  public void receivePokeball(){
    pokeballs += 1;
  }

  /** Check to see if the pokemon has a high enough probabilty of being caught
    * @param p the pokemon that is trying to be caught
    * @return true if the probablity of it being caught is acheived
    * return false if the probablity is not acheived
    */
  public boolean catchPokemon(Pokemon p){
    // use getHp to establish a probability of being caught
    if (pokemon.size() <= 5){
      double percentage = p.getHp() / p.getMaxHp();
      double prob = 1.0 - percentage;
      pokeballs -= 1;
      if (Math.random() <= prob){
        pokemon.add(p);
        return true;
      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
  }

  /** Gets the locations that the player is at 
    * @return loc returns the location the player is at
    */
  public Point getLocation(){
    return loc;
  }

  /** Moves the player north on the map
    * @return toremove the char at the location that the player is moving to
    * @return 'x' if the player cant move that direction
    */
  public char goNorth(){
    // Done just to make sure we are within bounds
    if(loc.getY() != 0){
      Map map = Map.getInstance();
      loc.translate(0,-1);
      map.reveal(loc.getLocation());
      //We move if it's a valid now we have to account for where we were standing.
      char toremove = map.getCharAtLoc(loc.getLocation());
      return toremove;
    }
    //This is just in the event that we can't go north the char that was there remains the same.
    System.out.println("\nYou can't go that way !\n");
    return 'x';
  }

  /** Moves the player South on the map
    * @return toremove the char at the location that the player is moving to
    * @return 'x' if the player cant move that direction
    */
  public char goSouth(){
    //Makes sure we aren't at the "bottom" of the map and don't go out of bounds
    Map map = Map.getInstance();
    if(loc.getY() != 4){
      loc.translate(0,1);
      map.reveal(loc.getLocation());
      
      char toremove = map.getCharAtLoc(loc.getLocation());
      // System.out.println("\n" + toremove+"\n");
      return toremove;
    }
    System.out.println("\nYou can't go that way !\n");
    return'x';
  } 

  /** Moves the player East on the map
    * @return toremove the char at the location that the player is moving to
    * @return 'x' if the player cant move that direction
    */
  public char goEast(){
    //Just to check if we're on the edge of the map and not go out of bounds
    if(loc.getX() != 4){
      Map map = Map.getInstance();
      char fin = map.getCharAtLoc(loc.getLocation());
      if(fin == 'f'){
        loc = map.findStart();
      }
      loc.translate(1, 0);
      map.reveal(loc.getLocation());
      
      char toremove = map.getCharAtLoc(loc.getLocation());
      return toremove;
    }
    
    System.out.println("\nYou can't go that way !\n");
    return'x';
  }

  /** Moves the player West on the map
    * @return toremove the char at the location that the player is moving to
    * @return 'x' if the player cant move that direction
    */
  public char goWest(){
    //If we aren't on the left most edge move one to the left
    if(loc.getX()!= 0){
      Map map = Map.getInstance();
      loc.translate(-1,0);
      map.reveal(loc.getLocation());

      char toremove = map.getCharAtLoc(loc.getLocation());
      return toremove;
    }
    System.out.println("\nYou can't go that way !\n");
    return 'x';
  }

  /** Gets the number of pokemons in the Arraylist
    * @return pokemon.size() the size of the arraylist
    */
  public int getNumPokemon(){
    return pokemon.size();
  }
  
  /** Heals all the pokemon in the Arraylist */
  public void healAllPokemon(){
    for (int i = 0; i < pokemon.size(); i++){
      pokemon.get(i).heal();
    }
  }
  /** Buffs all the pokemon in the Arraylist */
  public void buffAllPokemon(){
    for (int i = 0; i < getNumPokemon(); i++){
      Pokemon p = pokemon.get(i);
      PokemonGenerator gen = PokemonGenerator.getInstance();

      p = gen.addPokemonBuff(p);
      removePokemon(i);
      pokemon.add(i, p);
    }
  }
  /** Debuffs all the pokemon in the Arraylist */
  public void debuffPokemon(int index){
    PokemonGenerator gen = PokemonGenerator.getInstance();

    Pokemon temp = pokemon.get(index);
    temp = gen.addRandomDebuff(temp);
    pokemon.remove(index);
    pokemon.add(index,temp);
  }
  /** Grabs the Pokemon 
    * @param index the index of the pokemon in the ArrayList
    * @return pokemon.get(index) returns the pokemon at the index that was choosen
    */
  public Pokemon getPokemon(int index){
    return pokemon.get(index);
  }

  /** Grabs the pokemons in the pokemon ArrayList 
    * @return pokemon_list the string holding all the Pokemon and their health
    */
  public String getPokemonList(){
      String pokemon_list = "";
      for (int i = 0; i < pokemon.size(); i++){
        pokemon_list += String.valueOf(i+1) + ". " + pokemon.get(i).getName() + "  HP: "+ pokemon.get(i).getHp() + "/" + pokemon.get(i).getMaxHp() +"\n";
    }
    return pokemon_list;
  }
  /** Removes the choosen pokemon from the ArrayList
    * @return - the pokemon being removed from the ArrayList
    */
  public Pokemon removePokemon(int index){
    return pokemon.remove(index);
  }
  
  /**  String representation of a Trainer object
    *  @return string representation of Trainer
    */
  @Override
  public String toString(){
    Map map = Map.getInstance();

    String s = super.toString()  + "\nMoney: " + String.valueOf(this.getMoney()) + "\nPotions: " + String.valueOf(this.potions) + "\nPoke Balls: " + String.valueOf(this.pokeballs) + "\nPokemon \n-----------\n";
    String fin = s + getPokemonList();
    return fin +"Map:\n" + map.mapToString(loc);
  }
}