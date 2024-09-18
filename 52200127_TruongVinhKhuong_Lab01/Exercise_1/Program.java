import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Enter input: ");    
        Scanner sc = new Scanner(System.in);
        String scs = sc.nextLine();
        String[] new_sc = scs.split(" ");
                if (new_sc[1].equals("+"))
                    System.out.println(Integer.parseInt(new_sc[0]) + Integer.parseInt(new_sc[2]));
                else if (new_sc[1].equals("-"))
                    System.out.println(Integer.parseInt(new_sc[0]) -  Integer.parseInt(new_sc[2]));
                else if (new_sc[1].equals("x"))
                    System.out.println(Integer.parseInt(new_sc[0]) *  Integer.parseInt(new_sc[2]));
                else if (new_sc[1].equals("/"))
                    System.out.println(Integer.parseInt(new_sc[0]) /  Integer.parseInt(new_sc[2]));
                else if (new_sc[1].equals("^"))
                    System.out.println(Math.pow(Integer.parseInt(new_sc[0]),  Integer.parseInt(new_sc[2])));
    }
}   