
import java.util.*;

public class mastermind { // à renommer en main pour online compiler
	
    // Fonctions Pratiques à réutiliser ///////////////////////////////////////////////////////////
    
    public static void clearConsole() 
    {
        // Action : efface la console (le terminal)
        System.out.print("\033[H\033[2J");
    }
   
    // Met en pause le programme
    public static void pause (int timeMilli) {
	// Action : suspend le processus courant pendant timeMilli millisecondes
	try {
	    Thread.sleep(timeMilli); 
	} catch(InterruptedException ex) {
	    ex.printStackTrace();
	}
    }
    
    public static void sautLigne()
    {System.out.println("");}
    
    public static void DoubleSautLigne() 
    {sautLigne();sautLigne();}
    
    // ////////////////////////////////////////////////////////////////////////////////////////////
    
	//____________________________________________________________
	 /** pré-requis : nb >= 0
	résultat : un tableau de nb entiers égaux à val
    */
    public static int[] initTab(int nb, int val) 
    {   // nb : nombre d'entiers | val : valeur dans tableau
        int tab[];
        tab = new int[nb]; 
		
		// attribue longueur lorsque nb est correct
        if (nb >=0) 
        {
            for (int i=0; i<tab.length;i++)
            {
                tab[i]=val;
            }
        }
        return tab;
    }	
    //____________________________________________________________
    /** pré-requis : aucun
	résultat : une copie de tab
    */
    public static int[] copieTab(int[] tab)
    {
	    int tabCopie[];
	    tabCopie = new int [tab.length];
	    
	    for (int i=0;i<tab.length;i++)
	    {
	        tabCopie[i]=tab[i];
	    }
	    return tabCopie;
    }
	//____________________________________________________________
		/** pré-requis : aucun
	résultat : la liste des éléments de t entre parenthèses et séparés par des virgules */
    public static String listElem(char[] t)
	{	
		String listElem=""; // liste vide
		
		for (int i=0;i<t.length;i++)
		{
			listElem+="("+t[i]+"),";
		}
		System.out.print(listElem);
		return listElem;
    }    
    //____________________________________________________________
    /** pré-requis : aucun
	résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon*/
    public static int plusGrandIndice(char[] t, char c)
    {
        int plusGrandIndice=0;
    
        for (int i=0; i<t.length;i++)
        {
            if(t[i]==c) // c est elem de t[]
            {
                plusGrandIndice=i;
                i=t.length; // Sort de la boucle comme l'élément est trouvé pour éviter de modif plusGrandIndice
            }
            else
            {
                plusGrandIndice= -1; // c n'est pas élément
            }
        }
        return plusGrandIndice;
    }
	
	//____________________________________________________________
    /** pré-requis : aucun
	résultat : vrai ssi c est un élément de t
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean estPresent(char[] t, char c)
    {
        boolean estPresent=false; // valeur à retourner
        
        if (plusGrandIndice(t,c)>=0) // c est un élément de t
        {
            estPresent=true;
        }
        else // c n'est pas un élément de t
        {
            estPresent=false;
        }
        return estPresent;
    }
    //____________________________________________________________
    // action: Affiche si c est élement de t[] et à quel indice
    public static boolean estPresentAff(char[] t, char c)
    {
        boolean estPresent=false; // valeur à retourner
        int indiceElem=0; // affichage de l'indice de l'elem
        
        if (plusGrandIndice(t,c)>=0) // c est un élément de t
        {
            estPresent=true;
            indiceElem=plusGrandIndice(t,c);
            System.out.println(c+" est present dans les couleurs possible à l'indice "+indiceElem+".");
        }
        else // c n'est pas un élément de t
        {
            estPresent=false;
            System.out.println(c+" n'est pas present dans les couleurs possibles.");
        }
        return estPresent;
    }

	//____________________________________________________________
    /** pré-requis : aucun
	action : affiche un doublon et 2 de ses indices dans t s'il en existe
	résultat : vrai ssi les éléments de t sont différents
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean elemDiff(char[] t)
    {
        boolean sontDiff=false;

        int indiceSecDoublon=0; // remplace j comme sa valeur affiche est hors de la boucle for

        for (int i=0; i<t.length-1;i++) // teste pour chaque var i
        {
            for (int j=i+1;j<t.length;j++) // teste toute la ligne pour la var i
            {
                if (plusGrandIndice(t, t[j])==i) // est un doublon de t 
                {
                    sontDiff=false;   
                    indiceSecDoublon=j;
                    j=t.length; // fait sortir de la boucle for car doublon trouve            
                }
                else // n'est pas un doublon
                {
                    sontDiff=true;             
                }
            }
            if (sontDiff==false) // est un doublon de t 
            { 
                System.out.println(t[i]+" est un DOUBLON à la case "+i+" et "+indiceSecDoublon+".");
            }
            else
            {
                System.out.println(t[i]+" n'est PAS un DOUBLON.");
            }
        }
        return sontDiff;
    }
	//____________________________________________________________
	    /** pré-requis : t1.length = t2.length
	résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
    */
    public static boolean sontEgaux(int[] t1, int[] t2){
        boolean egaux=true;
        for(int i=0; i<(t1.length);i++){
            if(t1[i]!=t2[i])
            {
                egaux = false;
            }
        }
        return egaux;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
	résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
    */
    public static int[] codeAleat(int lgCode, int nbCouleurs)
    {
        int[] t;
        Random random = new Random ();
        t = initTab(lgCode,(nbCouleurs-1));
        
        for (int i=0;i<lgCode;i++)
        {
            t[i]= random.nextInt((nbCouleurs-1) +1);
        }

        return t;
    }      
    //____________________________________________________________
    /** pré-requis : aucun
	action : si codMot n'est pas correct, affiche pourquoi
	résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
    */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){
        boolean correct=true;
        if (codMot.length()!=lgCode){
            correct=false;
            System.out.println("la longueur de codMot et de lgCode n'est pas égale");
        }
        for (int i=0; i<(tabCouleurs.length);i++){
            if (tabCouleurs[i]!=codMot.charAt(i))
            {   // checker chaque celule du tableau pour chaque c du string
                correct=false;
                System.out.println("le code n'est pas correct car le "+i+"caractere n'est pas egal à celui du tableau des couleurs");
            }
        }
        return correct;
    }
	
	//____________________________________________________________
	    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
	résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
    */

    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {		
        int[] t = new int[codMot.length()];
        for(int i=0;i<(codMot.length());i++){
            for (int j=0;j<(tabCouleurs.length);j++){
                if (codMot.charAt(i)==tabCouleurs[j]){
                    t[i]=j;
                }
            }
        }
	return t;
    }
    
	//____________________________________________________________
	
        /** pré-requis : aucun
	action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce 
	qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
	résultat : le code saisi sous forme de tableau d'entiers
    */
    
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs)
    {
        Scanner scanner = new Scanner (System.in); 
        char saisie;
        
        int[] propCodeHumain;
        propCodeHumain = new int [lgCode];
        int indiceAff=1; // affichage de l'indice sans i=0
        
        for (int i=0; i<lgCode;i++)
        {   
            System.out.println("Saisissez proposition n°"+nbCoups+" de code");
            System.out.println("Couleur n°"+ indiceAff);
            saisie = scanner.next().charAt(0);
            
            while (estPresent(tabCouleurs,saisie) != true) // saisie incorrect 
            {
                estPresentAff(tabCouleurs,saisie);

                System.out.println("Proposition n°"+nbCoups+" de code");
                System.out.println("Veillez resaisir couleur n°"+ indiceAff);
                saisie = scanner.next().charAt(0);
            }
            propCodeHumain[i]= saisie;
            indiceAff++;
        }    
        sautLigne();
        System.out.print("Code Proposé :  ");
         
        for (int i=0; i<lgCode;i++)
        {
            System.out.print(propCodeHumain[i] + " ");
        }
        sautLigne();
        return propCodeHumain;
    }  
    
	//____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length
	résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3) //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    */
    public static int nbBienPlaces(int[] cod1,int[] cod2)
	{
		int compt=0;		
		for (int i=0; i< cod1.length; i++)
		{
			if (cod1[i]==cod2[i]) // un nombre en commun
			{compt++;}
		}
		System.out.println("Nombre(s) bien placé(s) : "+compt);
		return compt;
    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
	Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
    */
  public static int[] tabFrequence(int[] cod, int nbCouleurs)
    {
        int[] tabFrequence;
        tabFrequence= new int[nbCouleurs];

        int compt=0;

        for (int i=0; i<tabFrequence.length;i++)
        {
            for (int j=0; j<cod.length;j++)
            {
                if (cod[j]==j)
                {
                    compt++;
                }
            }
            tabFrequence[i]=compt;
        }
        System.out.println(tabFrequence);
        return tabFrequence;
    }
    //____________________________________________________________
    
    /** pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1") //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    */
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){
        return 0;
    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
	se trouvant  (resp. ne se trouvant pas) au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3)  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	et 2 mal placés (1 "0" et 1 "1") 
    */
    public static int/*[]*/ nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){
        return 0;
    }
     //____________________________________________________________


    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................


  /** pré-requis : numMache >= 1
	action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet), //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais  
            - sinon le nombre de codes proposés par le joueur humain          
    */
    //public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax){
  
    //}

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
	résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
    */
    //public static String entiersVersMot(int[] cod, char[] tabCouleurs){ //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        
    //}

    //___________________________________________________________________
    
    /** pré-requis : rep.length = 2
	action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont 
	         les nombres de bien et mal placés resp.
	résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
    */
    //public static boolean repCorrecte(int[] rep, int lgCode){ //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    //}

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir les nombres de bien et mal placés, 
    avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : les réponses du joueur humain dans un tableau à 2 entiers
    
    public static int[] reponseHumain(int lgCode){ //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        return
    }
    */
    //___________________________________________________________________

     /**CHANGE : action si le code suivant n'existe pas
     *************************************************
        pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
	action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
    */
//    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs){ //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

  //  }

    //___________________________________________________________________

     /**CHANGE : ajout du paramètre cod1 et modification des spécifications 
     *********************************************************************  
        pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                    nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
	résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
             c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
            propositions de cod seraient les nbCoups premières réponses de rep resp.
   */
  public static boolean estCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){ //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    return true;
  }

    //___________________________________________________________________

     /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat, 
                 ajout du paramètre cod1 et modification des spécifications 
     **************************************************************************      
            pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                    nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
	    action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
      cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
      sinon met dans cod1 le code ne contenant que des "0" et retourne faux
   */
     public static boolean passeCodeSuivantLexicoCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
        return true;
    }

    //___________________________________________________________________
    
    // manche Ordinateur

    /** pré-requis : numManche >= 2
	action : effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - 0 si le programme détecte une erreur dans les réponses du joueur humain
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par l'ordinateur
    */
    public static int mancheOrdinateur(int lgCode,char[] tabCouleurs, int numManche, int nbEssaisMax) {
        return 0;
    }

    //___________________________________________________________________


    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................	
	
	//____________________________________________________________    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier strictement positif, 
    avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier strictement positif saisi
    */
    public static int saisirEntierPositif() 
    {
        Scanner scanner = new Scanner (System.in);
        int saisie=0;
        
        System.out.println("Saisissez un entier positif");
        saisie = scanner.nextInt();
        
        if (saisie <=0) // si impaire ou pas inferieur ou égale à 0
        {
            System.out.println("Saisissez un entier positif");
            saisie = scanner.nextInt();
        }
        ;
        return saisie;	
    }


    //____________________________________________________________
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier pair strictement positif, 
    avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier pair strictement positif saisi*/


    public static int saisirEntierPairPositif()
    {
        Scanner input = new Scanner (System.in);
        int saisie=0;
        
        System.out.println("Saisissez un entier pair positif");
        saisie = input.nextInt();
            
        // si impaire ou pas inferieur ou égale à 0
        if (saisie %2!=0 || saisie <=0) // recommancer saisie
        {
            System.out.println("Saisissez un entier pair positif");
            saisie = input.nextInt();
        }
        return saisie;
    }
	//____________________________________________________________
		
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier strictement positif, 
    avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier strictement positif saisi
    */	
	public static char[] saisieCouleur()
    {
        Scanner input = new Scanner(System.in);
		
        System.out.println("Saisissez le nombre de couleurs possibles");
        int nbCouleurs = input.nextInt();
        char[] tabCoul = new char[nbCouleurs];
		
        String coul;
        
        System.out.println("Lors du choix de couleurs, chacune doit avoir une initiale différente, ex: Rouge Vert Bleu => R, V, B. Rose ne peut plus etre pris");
        for (int i=0; i<(tabCoul.length);i++) // Saisie tableau couleurs
        {
            System.out.println("Saisissez une couleur souhaitée");
            coul = input.next();
            while (estPresent(tabCoul,coul.charAt(0)) == true) // saisie incorrect (doublon)
            {
                System.out.println("Initiale déjà prise, saisissez une couleur qui n'a pas comme première lettre "+"'" +coul.charAt(0)+"'");
                coul = input.next(); 
            }  
            tabCoul[i]= coul.charAt(0);
        }
        System.out.print("Couleurs choisis: ");       
        for (int i =0 ; i<(tabCoul.length);i++) // Affichage tableau couleurs
        {
            System.out.print(tabCoul[i]+" ");
        }
        return tabCoul;
    }
	//____________________________________________________________
	
	//.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................
    
//____________________________________________________________
    /** action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, 
           nbManches, nbEssaisMax), 
	   effectue la partie et affiche le résultat (identité du gagnant ou match nul).
	   La longueur d'un code et le nombre de couleurs doivent être strictement positifs. 
	   Le nombre de manches doit être un nombre pair strictement positif.
	   Les initiales des noms de couleurs doivent être différentes. 
	   Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.*/
//____________________________________________________________
	   
    public static void main(String[] args) 
    { 
        masterMind();
    }

    public static void masterMind ()
    {
        sautLigne();
        System.out.println("Bienvenue dans le Master Mind"); sautLigne();
        
        System.out.println("Nombre de pions du code secret");
        int lgCode = saisirEntierPositif(); 
        sautLigne();
        
        char[] tabCouleurs = saisieCouleur();
        sautLigne();
        
        System.out.println("Nombre de manches");
        int nbManches = saisirEntierPairPositif();
        sautLigne();
        
        System.out.println("Nombre d'essais");
        int nbEssaisMax = saisirEntierPositif(); 
        sautLigne();
        
        System.out.println("Initialisation terminée");
        sautLigne();

        int[]codePropHum=propositionCodeHumain(1, lgCode, tabCouleurs);
	}  
}
