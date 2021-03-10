// Drew Mesker
// CS 290 MP2
// File Reader
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class filereader
{
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/fullgame.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine())
        {
            String[] m = sc.nextLine().split("-");
            if (m[0].equals("character"))
            {
                characters[c[0]] = m[1];
                c[0]++;
            }
            if (m[0].equals("places"))
            {
                places[c[1]] = m[1];
                c[1]++;
            }
            if (m[0].equals("objects"))
            {
                objects[c[2]] = m[1];
                c[2]++;
            }
            if (m[0].equals("quotes"))
            {
                quotes[c[3]] = m[1];
                c[3]++;
            }
            if (m[0].equals("pd"))
            {
                pd[c[4]] = m[1];
                c[4]++;
            }
        }
    }

    public static String[] characters = new String[6];
    public static String[] places = new String[10];
    public static String[] objects = new String[6];
    public static String[] pd = new String[10];
    public static String[] quotes = new String[6];
    public static int[] c = new int[5];


    public static String character(int i)
    {
        return characters[i];
    }
    public static String place(int i)
    {
        return places[i];
    }
    public static String object(int i) { return objects[i]; }
    public static String descript(int i)
    {
        return pd[i];
    }
    public static String quote(int i)
    {
        return quotes[i];
    }
    public static String commands() throws FileNotFoundException {
        // commands
        File file = new File("src/commands.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine() + "\n");

        }
        return lines.toString();
    }


}