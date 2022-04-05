/**
   CECS 277 Sec 01 
   Pan, Erwin
   Patel,Jeet
   Martinez, Eduardo
   Date: Nov 9, 2021

 * Description:
   * An text-based Pokemon game that allows to fight wild pokemon, have crazy encounters, and travel through new exotic areas, until you die or feel like quitting.
*/
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    System.out.print("Prof. Oak: Hello there new trainer, what is your name? ");
    String name = CheckInput.getString();
    System.out.printf("Great to Meet You, " + name + ".");
    System.out.println(" Choose Your First Pokemon\n1. Charmander\n2. Bulbasaur\n3. Squirtle");

    int choice = CheckInput.getIntRange(1, 3);
    PokemonGenerator generator = PokemonGenerator.getInstance();
    int count = 0;
    int lvl = 0;
    Map m = Map.getInstance();
    m.loadMap(1);
    //Call the intsance of map in the do while loop instead
    int user_choice;
    char direction = 's';
    Trainer t1; // initialized trainer 

    if (choice == 1) {
      t1 = new Trainer(name, generator.getPokemon("Charmander"));
    } else if (choice == 2) {
      t1 = new Trainer(name, generator.getPokemon("Bulbasaur"));
    } else {
      t1 = new Trainer(name, generator.getPokemon("Squirtle"));
    }
    /** Runs till the trainers health is zero or if he decides to quit */
    do {
      /**Makes the trainers move in whatever direction they choose.*/
      //Map m = Map.getInstance();
      System.out.println(t1);
      user_choice = mainMenu();
      switch (user_choice) {
        case 1:
          direction = t1.goNorth();
          break;
        case 2:
          direction = t1.goSouth();
          break;
        case 3:
          direction = t1.goEast();
          break;
        case 4:
          direction = t1.goWest();
          break;
        case 5:
          System.out.println("You are not the very best.\nYou could've caught them all.\nPlay again next time.");
          return;
      }
      /**The Trainer recieves a random item*/
      if (direction == 'i') {
        int random = (int) (Math.random() * 2) + 1;
        if (random == 1) {
          System.out.println("\nYou found a POKEBALL!\n");
          t1.receivePokeball();
          m.removeChartAtLoc(t1.getLocation());
        } else if (random == 2) {
          System.out.println("\nYou found a POTION!\n");
          t1.recievePotion();
          m.removeChartAtLoc(t1.getLocation());
        }
      }
      /**When the player encounters the finish of the area.*/
      if (direction == 'f') {
        Pokemon boss = generator.generateRandomPokemon(lvl + 2); // this should fix the problem
        System.out.println("Rock: To enter the next area you must first defeat me. \n" + "Rock's Pokemon is " + boss.getName());
        boolean fight = true;
        boolean check = false;
        while (fight){
            System.out.println(boss);
            System.out.println("What would you like to do\n 1. Fight\n 2. Use a Potion !");
            int userChoice = CheckInput.getIntRange(1,2);
            if (userChoice == 1){
              for(int i = 0; i <t1.getNumPokemon(); i++){
                if(t1.getPokemon(i).getHp() == 0){
                  fight = false; 
                }else{
                  fight = true;
                }
              }
              if (boss.getHp() == 0){
                check = true;
                fight = false;
                break;
              }
              if(fight){
                trainerAttack(t1,boss);
              }else{
                fight = false;
                check = true;
              }
              if (boss.getHp() == 0){
                check = true;
                fight = false;
                break;
              }
          }
          if (userChoice == 2){
            if (fight){
              if (t1.hasPotion()){
                System.out.println("Choose a Pokemon:\n" + t1.getPokemonList());
              int user_input = CheckInput.getIntRange(1,t1.getNumPokemon());

              t1.usePotion(user_input - 1);
              }
              else{
                System.out.println("You don't have any potions.");
              }
            }
          }
        }
        if (check){
          if (count == 0) {
          t1.buffAllPokemon();
          m.loadMap(2);
          m.findStart();

          System.out.println("\nCongrats! You have defeated the GYM LEADER.\nWelcome to the new area.");
          count += 1;
          lvl += 1;
          } 
          else if (count == 1) {
            t1.buffAllPokemon();
            m.loadMap(3);
            m.findStart();
            System.out.println("\nCongrats! You have defeated the GYM LEADER.\nWelcome to the new area.");
            lvl += 1;
            count += 1;
          } 
          else if (count == 2){
            t1.buffAllPokemon();
            m.loadMap(1);
            m.findStart();
            System.out.println("\nCongrats! You have defeated the GYM LEADER.\nWelcome to the new area.");
            count = 0;
            lvl +=1;
          }
        }
        if(fight == false && check == false){
          System.out.println("Rock: Try again once ur stronger");
        }
      }

      /**There is nothing located at that location.*/
      if (direction == 'n') {
        System.out.println("\nThere is nothing there ...\n");
        m.removeChartAtLoc(t1.getLocation());
      }
      /**When the trainer encounters a wild pokemon*/
      if (direction == 'w') {

        Pokemon wild = generator.generateRandomPokemon(lvl);
        System.out.println("\nYou ran into a WILD " + wild.getName() + "!");
        boolean check = true;
        while (check) {
          System.out.println(wild);
          System.out.println("What would you like to do?");
          System.out.println("1. Fight");
          System.out.println("2. Use Potion");
          System.out.println("3. Throw Poke Ball");
          System.out.println("4. Run Away");
          int choice1 = CheckInput.getIntRange(1, 4);
          boolean check2 = true;
          if (choice1 == 1) {
            for (int i = 0; i < t1.getNumPokemon(); i++) {
              if (t1.getPokemon(i).getHp() == 0) {
                check2 = false;
              } else {
                check2 = true;
              }
            }
            if (check2) {
              trainerAttack(t1, wild);
            } else {
              int rand = (int) ((Math.random() * 4) + 1);
              t1.takeDamage(rand);
              System.out.println("All of you Pokemon are dead.");
              System.out.println(wild.getName() + " attacked you for " + String.valueOf(rand) + " DAMAGE.\n");
              System.out.println("You can either run or use a Potion.");
            }
          } 
          /* Trainer uses a potion on pokemon */
          else if (choice1 == 2) {
            System.out.println("Choose a Pokemon:\n" + t1.getPokemonList());
            int user_input = CheckInput.getIntRange(1, t1.getNumPokemon());
            t1.usePotion(user_input - 1);
          }

          /* User tries to catch the pokemon */
          else if (choice1 == 3) {
            if (t1.getNumPokemon() <= 6) {
              if (t1.hasPokeball()) {
                if (t1.getNumPokemon() == 5 && t1.catchPokemon(wild)){
                  System.out.println("Choose Which Pokemon to remove:");
                  System.out.println(t1.getPokemonList());
                  choice = CheckInput.getIntRange(1, t1.getNumPokemon());

                  t1.removePokemon(choice - 1);
                  
                }
                System.out.println("Shake.. Shake... Shake...");
                if (t1.catchPokemon(wild)) {
                  
                  System.out.println("You caught " + wild.getName());
                  m.removeChartAtLoc(t1.getLocation());
                  check = false;
                } else {
                  System.out.println(wild.getName() + " escaped from the Poke Ball.");
                }
              } 
              else {
                System.out.println("You don't have any Poke Balls left!");
              } 
            }
          }
          if (choice1 == 4) {
            double rand = Math.random();
            if (rand <= 0.2) {
              System.out.println("\nYou got away successfully! \n");
              check = false;
            } else {
              System.out.println("You didn't successfully escape.");
            }
          }
          if (wild.getHp() == 0) {
            m.removeChartAtLoc(t1.getLocation());
            check = false;
          }
        }
      }
      /**When the trainer enters the city.*/
      if (direction == 'c') {
        int check = 0;

        System.out.println("\nYou've entered the city.\nWhere would you like to go ?");
        System.out.println("1. Store\n2. Pokemon Hospital");
        check = CheckInput.getIntRange(1, 2);
        if (check == 1) {
          store(t1);
        } else {
          System.out.println("\nHello! Welcome to the Pokemon Hospital.");
          System.out.println("I'll fix your poor pokemon up in a jiffy!");
          System.out.println("There you go! See you again soon.\n");
          t1.healAllPokemon();
        }
      }
      /** When the trainer encounters a player.*/
      if (direction == 'p') {
        int random = (int) ((Math.random() * 4) + 1);
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Press 'Enter' to continue.");
        switch (random) {
          case 1:
            System.out.println("Rock: Why did you steal my Gym Badge!!!");
            input = in.nextLine();
            System.out.println(t1.getName() + ": I didnt steal it.");
            input = in.nextLine();
            System.out.println("Rock: Oh my bad! It was in my pocket.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": It's not a problem.");
            input = in.nextLine();
            System.out.println("Rock: Here take this as a sincere apologize.");
            input = in.nextLine();
            System.out.println("You received 3 Money");
            t1.recieveMoney(3);
            input = in.nextLine();
            break;
            
          case 2:
            System.out.println("Misty: Have you seen my Togepi !!!");
            input = in.nextLine();
            System.out.print(t1.getName() + ": No, I haven't.");
            input = in.nextLine();
            System.out.println("Misty: Can you help me look around Togepi?");
            input = in.nextLine();
            System.out.println(t1.getName() + ": Yeah sure lets look around.");
            input = in.nextLine();
            System.out.print("     Searching... Searching... Searching...");
            input = in.nextLine();
            System.out.println("Misty: Togepi, where are you? Togepi, where are you?");
            input = in.nextLine();
            System.out.println(t1.getName() + ": Misty, I found Togepi. Look up there !!");
            input = in.nextLine();
            System.out.println("Misty: Oh no. Team Rocket stole Togepi.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": " + t1.getPokemon(0).getName() + " attack that ship!!");
            System.out.println("Team Rocket: We're blasting off again!!!");
            input = in.nextLine();
            System.out.println("Misty: Thank You, " + t1.getName() + ". Here take this as an award.");
            input = in.nextLine();
            System.out.println("You received " + String.valueOf(2) + " Pokeballs\n");
            t1.receivePokeball();
            t1.receivePokeball();
            input = in.nextLine();
            break;

          case 3:
            System.out.println("Officer Jenny: Look out!!!");
            t1.takeDamage(2);
            System.out.println(t1.getName() + ": Ow! What was that?");
            input = in.nextLine();
            System.out.println(t1.getName() + " took 2 damage.");
            input = in.nextLine();
            System.out.print("Officer Jenny: They're a wild Charizard fighting a Dragonite over there.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": I'll see if I can stop the fight.");
            input = in.nextLine();
            System.out.println("Officer Jenny: I'll try to assit you.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": " + t1.getPokemon(0).getName() + " help me stop this fight.");
            input = in.nextLine();
            System.out.println(
                t1.getName() + ": " + t1.getPokemon(0).getName() + " starts taking to the Dragonite and Charizard."
                    + t1.getPokemon(0).getName() + " solves the problem and stops the fight.");
            input = in.nextLine();
            System.out.println("Officer Misty: Thanks for all the help. Here take this as an award");
            input = in.nextLine();
            System.out.println("You received a Potion");
            t1.recievePotion();
            input = in.nextLine();
            break;

          case 4:
            System.out.println("Professor Oak: Look out!!!");
            t1.takeDamage(5);
            input = in.nextLine();
            System.out.println(t1.getName() + " takes 5 DAMAGE from MEW-TWO");
            input = in.nextLine();
            System.out.println(t1.getName() + ": What was that?");
            input = in.nextLine();
            System.out.println("Professor Oak: That was a clone of MEW scientifically made by scientists.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": Why is it going on a rampage?");
            input = in.nextLine();
            System.out.println("Professor Oak: MEW-TWO has never had any human interaction since he was created.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": Oh I see. I'll see if I can do anything to help.");
            input = in.nextLine();
            System.out.println("Professor Oak: That would be a huge help.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": Hey MEW-TWO, my name is " + t1.getName() + ".");
            input = in.nextLine();
            System.out.println("MEW-TWO: Who am I? Where is this place? What was I born for?");
            input = in.nextLine();
            System.out.println(t1.getName()
                + ": I don't know why you were created but what you're doing right now is hurting a bunch of people.");
            input = in.nextLine();
            System.out.println("MEW-TWO: Why should I listen to you?");
            input = in.nextLine();
            System.out.println(t1.getName() + ": I'm only trying to stop other people from getting hurt.");
            input = in.nextLine();
            System.out.println("MEW-TWO: I'll listen to you and stop going on a rampage.");
            input = in.nextLine();
            System.out.println(t1.getName() + ": Come MEW-TWO let's go back to Professor Oak.");
            input = in.nextLine();
            System.out.println("Professor Oak: Thanks for all the help calming down MEW-TWO.");
            input = in.nextLine();
            System.out.println("You received 2 Potions.");
            input = in.nextLine();
            t1.recievePotion();
            t1.recievePotion();
            break;
        }
        m.removeChartAtLoc(t1.getLocation());
      }
    } while (t1.getHp() != 0);

    if (t1.getHp() == 0) {
      System.out.println("You died. Better luck next time.");
    }
  }

  /**
   * Prints the Main Menu and returns the users
   * @return x - the users input for which directions they would like to go
   */
  public static int mainMenu() {
    System.out.println("Main Menu");
    System.out.println("1. Go North");
    System.out.println("2. Go South");
    System.out.println("3. Go East");
    System.out.println("4. Go West");
    System.out.println("5. Quit");

    int x = CheckInput.getIntRange(1, 5);
    return x;
  }
  
  /**
   * Allows the user to choose a Pokemon to fight the wild Pokemon with.
   * @param t1   - the trainer that is fighting the wild pokemon
   * @param wild - the wild pokemon that was encountered
   * @return wild - returns the wild pokemon
   */
  public static Pokemon trainerAttack(Trainer t1, Pokemon wild) {
    System.out.println("Choose a Pokemon: ");
    for (int i = 0; i < t1.getNumPokemon(); i++){
      if (t1.getPokemon(i).getHp() > 0){
        System.out.println(i+1 + ". " + t1.getPokemon(i).getName() + " Hp: " + t1.getPokemon(i).getHp()+ "/" + t1.getPokemon(i).getMaxHp());
      }
    }
    int x = CheckInput.getIntRange(1, t1.getNumPokemon());
    System.out.println(t1.getPokemon(x - 1).getName() + ", I choose you.");

    System.out.println(t1.getPokemon(x-1).getAttackTypeMenu());

    int atkType = CheckInput.getIntRange(1, t1.getPokemon(x-1).getNumAttackTypeMenuItems());
    int rand = (int) ((Math.random() * wild.getNumAttackTypeMenuItems()) + 1);
    int rand1 = (int)((Math.random() * wild.getNumAttackMenuItems(atkType)) + 1);
  
    System.out.println(t1.getPokemon(x - 1).getAttackMenu(atkType));

    int move = CheckInput.getIntRange(1, t1.getPokemon(x - 1).getNumAttackMenuItems(atkType));

    System.out.println(t1.getPokemon(x-1).attack(wild, atkType, move));
    
    if(Math.random() <= 0.1){
      t1.debuffPokemon(x-1);
    }

    PokemonGenerator gen = PokemonGenerator.getInstance();

    if(Math.random() <= 0.25){
      wild = gen.addRandomDebuff(wild);
    }
    if (wild.getHp() == 0){
      return wild;
    }
    System.out.println(wild.attack(t1.getPokemon(x-1), rand, rand1));

    return wild;
  }

  /**
   * The store located in the city. That allows you to buy potions and pokeballs
   * @param t - the trainer that entered the store
   */
  public static void store(Trainer t) {
    int x = 0;
    while (x != 3) {
      System.out.println("Hello " + t.getName() + "! What can I help you with?");
      System.out.println("1. Buy Potions - $5");
      System.out.println("2. Buy Poke Balls - $3");
      System.out.println("3. Exit");
      x = CheckInput.getIntRange(1, 3);
      switch (x) {
        case 1:
          if (t.spendMoney(5)) {
            t.recievePotion();
            System.out.println("Here's your potion");
          }
          break;
        case 2:
          if (t.spendMoney(3)) {
            t.receivePokeball();
            System.out.println("Here's your PokeBall");
          }
          break;
        case 3:
          break;
      }
    }
    System.out.println("\nThank You, Come Again Soon!\n");
  }
}