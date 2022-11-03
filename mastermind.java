import java.util.*;

public class mastermind {
    public static void main(String[] args) {
        char[] couleurs = saisieCouleur(); 
    }

    public static char[] saisieCouleur(){
        Scanner input = new Scanner(System.in);
        System.out.println("saisir le nombre de couleurs possibles");
        int nbCoul = input.nextInt();
        char[] tabCoul = new char[nbCoul];
        for (int i =0 ; i< (tabCoul.length);i++){
            String coul = input.next();
            tabCoul[i]= coul.charAt(0);
        }
        for (int i =0 ; i< (tabCoul.length);i++){
            System.out.print(tabCoul[i]+" ");
        }
        input.close();
        return tabCoul;
    }
}
