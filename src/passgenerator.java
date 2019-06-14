/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
/**
 *
 * @author Benjamin Sasser
 */
import java.util.*;
import java.io.*;
public class passgenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        boolean run = true;
        Scanner input = new Scanner(System.in);
        LinkedList<PChars> pizza = new LinkedList<PChars>();
        while(run)
        {
            System.out.println("Please specify how long you want your password to be (using integers from 8-16).");
            String length = input.nextLine();
            int length1 = Integer.parseInt(length);
            
            System.out.println("Can your password contain lowercase letters? ('yes' or 'no')");
            String letters = input.nextLine();
            if(letters.equals("yes"))
            {
                Scanner file = new Scanner(new FileReader("C:\\Users\\Student\\Documents\\NetBeansProjects\\UI_Password Manager\\src\\p_letters.txt"));
                while(file.hasNextLine())
                {
                    String line = file.nextLine();
                    char crust = line.charAt(0);
                    
                    pizza.add(new PChars(crust));
                }
            }
            else if(letters.equals("no"))
            {
                System.out.println("okay");
            }
            
            System.out.println("Can capital letters be included? ('yes' or 'no')");
            String caps = input.nextLine();
            if(caps.equals("yes"))
            {
                Scanner file = new Scanner(new FileReader("C:\\Users\\Student\\Documents\\NetBeansProjects\\UI_Password Manager\\src\\p_caps.txt"));
                while(file.hasNextLine())
                {
                    String line = file.nextLine();
                    char cheese = line.charAt(0);
                    
                    pizza.add(new PChars(cheese));
                }
            }
            else if(letters.equals("no"))
            {
                System.out.println("okay");
            }
            
            System.out.println("Can numbers be included? ('yes' or 'no')");
            String nums = input.nextLine();
            if(nums.equals("yes"))
            {
                Scanner file = new Scanner(new FileReader("C:\\Users\\Student\\Documents\\NetBeansProjects\\UI_Password Manager\\src\\p_nums.txt"));
                while(file.hasNextLine())
                {
                    String line = file.nextLine();
                    char sauce = line.charAt(0);
                    
                    pizza.add(new PChars(sauce));
                }
            }
            else if(nums.equals("no"))
            {
                System.out.println("okay");
            }
            
            System.out.println("Can any special characters (such as '!' '#' '&' '$' etc.) be included? ('yes' or 'no')");
            String specs = input.nextLine();
            if(specs.equals("yes"))
            {
                Scanner file = new Scanner(new FileReader("C:\\Users\\Student\\Documents\\NetBeansProjects\\UI_Password Manager\\src\\p_specs.txt"));
                while(file.hasNextLine())
                {
                    String line = file.nextLine();
                    char toppings = line.charAt(0);
                    
                    pizza.add(new PChars(toppings));
                }
            }
            else if(specs.equals("no"))
            {
                System.out.println("okay");
            }
            
            if(pizza.size() < 1)
            {
                System.out.println("Couldn't make a password...");
            }
            else if(length1 < 8)
            {
                System.out.println("Your password's too short.");
            }
            else
            {
                System.out.println();
                System.out.println("Your password is: ");
                for(int i = 0; i < length1; i++)
                {
                    Random gen = new Random();
                    int gen1 = gen.nextInt(pizza.size() - 1);
                
                    System.out.print(pizza.get(gen1).getChar());
                }
                
                run = false;
            
            }
        }
        
    }
    
}

class PChars
{
    private char piizza;
    
    public PChars()
    {
        piizza = ' ';
    }
    
    public PChars(char piizza)
    {
        this.piizza = piizza;
    }
    
    public char getChar()
    {
        return piizza;
    }
}