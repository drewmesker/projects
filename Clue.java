// Drew Mesker
// CS 290 MP2
// Clue
import java.io.FileNotFoundException;
import java.util.*;

public class Clue
{
    public static void main(String[] args) throws FileNotFoundException {
        // main function with intro and engine that runs game
        intro();
        // random winners (killer and weapon) picked
        winners();
        gameengine();
    }

    public static void gameengine() throws FileNotFoundException {


        // place 5 of the weapons in unique rooms
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=1; i<10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<6; i++) {
            obpl[i] = places[list.get(i)];
        }

        // main engine that runs the game
        // get input from user on next move while game has not been won
        while(!gameWon(characters[randchar],objects[randweapon],"NULL","NULL"))
        {
            System.out.println("What would you like to do next?");
            String nextMove;
            Scanner input = new Scanner(System.in);
            nextMove = input.nextLine();
            // if its guess, current, or help then no need to split string - go to output
                switch (nextMove) {
                    case "guess" -> {
                        System.out.println("I'm glad you have finished investigating. I hope you are right about this.\nWho do you think killed Dr. Black?");
                        String winningkiller = input.nextLine();
                        System.out.println("With what weapon was Dr. Black killed?");
                        String winningweapon = input.nextLine();
                        if (gameWon(characters[randchar], objects[randweapon], winningkiller, winningweapon)) {
                            System.out.println("Congratulations. You have solved the mystery around Dr. Black's tragic death.");
                        } else {
                            System.out.println("Unfortunately, that is not correct. The murderer was " + characters[randchar] + " with the " + objects[randweapon] + ". Thank you for your time during this investigation.");
                        }
                        System.exit(1);
                    }
                    case "help" -> System.out.println("""
                            Hello, Detective. Welcome to the town of Birmingham, England.\s
                            We have brought you here to investigate the murder of Dr. Black.I have a list of suspects to give to you.\s
                            I hope you can solve this terrible crime.""");
                    case "current" ->
                            {
                                if(current.equals(""))
                                {
                                    System.out.println("You are currently standing outside the Manor. Enter a location to begin your investigation.");
                                }
                                else {
                                    System.out.println("You are currently at the " + current + ".");
                                }
                            }
                }
                // not guess, current, or help - split string based on spacing (2 new input array)
                String[] m = nextMove.split(" ");

                switch (m[0]) {
                    case "see":
                        if (m[1].equals("notes")) {
                            notes();
                        }
                        if (m[1].equals("commands")) {
                            commands();
                        }
                        // run for loop for each object
                        for (int i = 0; i < 6; i++) {
                            if (m[1].equals(objects[i])) {
                                if (obpl[i].equals(current)) // make sure the object is in the current room
                                {
                                    System.out.println("You take a good look at the " + objects[i] + ". This weapon was not used to murder Dr. Black. You add this information to your notes.");
                                    if (objectcheck[i] == 0) {
                                        lines.add(objects[i]);
                                    }
                                    objectcheck[i]++;
                                } else {
                                    System.out.println("Hmm. I'm afraid the " + m[1] +" is not in this room.");
                                }
                            }
                        }

                        break;
                    case "enter":
                        for (int i = 0; i < 10; i++) { // running for loop for each room
                            if (m[1].equals(places[i])) {
                                if (m[1].equals(current)) {
                                    System.out.println("You are already in the " + m[1] + ".");
                                } else {
                                    System.out.println("You are now in the " + places[i] + ".");
                                    if (p[i] == 0) { // first time
                                        System.out.println(pd[i]);
                                        p[i]++;
                                        lines.add(places[i]);
                                    }
                                    wir(i);
                                    personinroom();
                                    current = places[i];
                                }
                            }
                        }
                        break;
                    case "talk":
                        for (int i = 0; i < 6; i++) {
                            if (m[1].equals(characters[i])) {
                                if (m[1].equals(currentp)) {

                                    System.out.println(quotes[i]);
                                    if (personcheck[i] == 0) {
                                        lines.add(characters[i]);
                                    }
                                    personcheck[i]++;
                                } else {
                                    System.out.println("The person you are trying to speak to is not in the room.");
                                }
                            }
                        }
                        break;
                    default:
                }
            }
    }

    public static void intro() throws FileNotFoundException {
        // starting info
        System.out.println("""
                                
                                     
                Hello, Detective. Welcome to the town of Birmingham, England.\s
                We have brought you here to investigate the murder of Dr. Black.I have a list of suspects to give to you.\s
                I hope you can solve this terrible crime.""");


        String[] s = null;
        filereader.main(s);

        for(int i = 0; i < 6; i++)
        {
            characters[i] = filereader.character(i);
            objects[i] = filereader.object(i);
            quotes[i] = filereader.quote(i);
        }
        for(int i = 0; i < 10; i++)
        {
            places[i] = filereader.place(i);
            pd[i] = filereader.descript(i);

        }


        System.out.println("\nHere are the list of suspects:");
        // print list of names
        for (int i = 0; i < 6; i++) {
            System.out.println(characters[i]);
        }
        // print list of rooms
        System.out.println("\nI want you to find them and speak to them. Here's where you might find them:");
        for (int i = 0; i < 10; i++) {

            System.out.println(places[i]);
        }
        // read in objects
        System.out.println("\nThere are also some possible weapons used that I want you to take a look at: ");
        for (int i = 0; i < 6; i++) {
            System.out.println(objects[i]);
        }
        // print list of commands
        System.out.println("\nA list of commands is available here:");
        commands();
    }

    // needed variables in this class
    public static String[] characters = new String[6];
    public static String[] places = new String[10];
    public static String[] objects = new String[6];
    public static String[] obpl = new String[6];
    public static int[] p = new int[10];
    private static int randweapon;
    private static int randchar;
    public static List<String> lines = new ArrayList<>();
    public static final int[] objectcheck = new int[6];
    public static String current = "";
    private static final int[] personcheck = new int[6];
    public static String currentp = "";
    private static final int[] inv = new int[6];
    public static String[] quotes = new String[6];
    public static String[] pd = new String[10];



    // randomly pick a winner for weapon and character
    public static void winners()
    {
       Random rand = new Random();
       randweapon = rand.nextInt(6);
       randchar = rand.nextInt(6);
    }

    // return true if game won - else return false
    private static boolean gameWon(String killer, String weapon, String winningkiller, String winningweapon)
    {
        return killer.equals(winningkiller) && weapon.equals(winningweapon);
    }


    // print out list of commands
    public static void commands() throws FileNotFoundException {
        String s = filereader.commands();
        System.out.println(s);
    }

    // print message for weapon in room
    public static void wir(int r)
    {

        for(int i = 0; i < 6; i++)
        {
            if(!objects[i].equals(objects[randweapon])) // making sure that object isn't the random object picked
            {
                if (obpl[i].equals(places[r])) {
                    if(inv[i]==0 && objectcheck[i] == 0)
                    {
                        System.out.println("In the " + places[r] + ", you can find the " + objects[i] + ".");
                        inv[i]++;

                    }

                }
            }
        }
    }

    // characters move around - 50/50 chance of you seeing a character in current room
    public static void personinroom () {

        Random rand = new Random();
        int x = rand.nextInt(2);
        if (x == 1) {
            // find a person
            int y = rand.nextInt(6);
            for (int i = 0; i < 6; i++) {
                if (y == i) {
                    if (!characters[y].equals(characters[randchar])) {
                        System.out.println("You have found " + characters[y] + " lurking around.");
                        currentp = characters[y];
                    }
                }
            }

        }

    }

    // gives info on who what and where
    public static void notes()
    {
        System.out.println("Here are your current notes:");
        System.out.println(lines.toString());
    }
}
