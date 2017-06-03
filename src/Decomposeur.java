import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decomposeur {
	/**
	 * Cette classe d�compose la chaine re�ue selon les attributs n�cessaire � la cr�ation d'une forme.
	 * Elle retourne un tableau contenant les attributs.
	 * 
	 * @author Louis Raymond-Poirier
	 * @date 2017/05/31
	 */
	private String id;
	private String type;
	private int posX;
	private int posY;
	private int width;
	private int height;
	
	private final int NB_ATTRIBUTS = 6;
	
	public String[] decomposerString(String s){
		/*
		 * tab[0] id
		 * tab[1] type
		 * tab[2] posX
		 * tab[3] posY
		 * tab[4] width
		 * tab[5] height
		 */
		
		//Tableau qui sera retourn�
		String[] tab = new String[NB_ATTRIBUTS];
		//Tableau dans lequel on ajoute le contenu s�par� de la chaine re�ue en param�tre
		String[] elems = split(s, " ");
		
		id = elems[0];
		type = elems[1].substring(1, elems[1].length()-1);

		//Calcul des attributs de position et dimension selon le type de forme
		switch (type){
			case "CARRE": case "RECTANGLE":
				posX = Integer.parseInt(elems[2]);
				posY = Integer.parseInt(elems[3]);
				width = Integer.parseInt(elems[4])-posX;
				height = Integer.parseInt(elems[5])-posY;			
				break;
			case "CERCLE":
				int rayon = Integer.parseInt(elems[4]);
				posX = Integer.parseInt(elems[2]);
				posY = Integer.parseInt(elems[3]);
				width = rayon*2;
				height = rayon*2;
				break;
			case "OVALE":
				int rayonX = Integer.parseInt(elems[4]);
				int rayonY = Integer.parseInt(elems[5]);
				posX = Integer.parseInt(elems[2]);
				posY = Integer.parseInt(elems[3]);
				width = rayonX*2;
				height = rayonY*2;
				break;
		}
		
		tab[0] = id;
		tab[1] = type;
		
		//On assigne les valeurs dans le tableau qui sera retourn�. Si la forme est de type "Ligne", 
		//les attributs restent inchang�s.
		if(type=="LIGNE"){
			for(int i = 0;i<tab.length; i++){
				tab[i] = elems[i];
			}
		}else{
			tab[2] = Integer.toString(posX);
			tab[3] = Integer.toString(posY);
			tab[4] = Integer.toString(width);
			tab[5] = Integer.toString(height);
		}
		
		return tab;
	}
	/**
	 * Sert � s�parer une chaine selon une deuxi�me chaine s�paratrice.
	 * Donne le m�me r�sultat que la m�thode String.split(s)
	 * 
	 * Fonctionnement: L'expression r�guli�re permet de trouver la
	 * premi�re sous-chaine se terminant par le s�parateur sp�cifi�
	 * (e.g., un espace). On place ensuite le curseur � la fin de
	 * cette sous-chaine et on effectue la recherche � nouveau. �
	 * chaque it�ration, on ajoute la nouvelle sous-chaine dans un
	 * tableau qu'on allonge d'un �l�ment. 
	 * 
	 * @param String � s�parer
	 * @param String s�paratrice
	 * @return String[] contenant les r�sultats
	 */
	private String[] split(String s, String d){
		
		//initialisation du tableau � retourner
		String[] t = new String[0];
		
		Pattern p = Pattern.compile("([^"+d+"]*)");
		Matcher m = p.matcher(s);
		
		int curseur = 0;
		while(curseur<s.length()){
			
			/*
			 * Comparer l'expression r�guli�re � la cha�ne � partir
			 * de l'index sp�cifi� en param�tre (le curseur)
			 */
			m.find(curseur);
			
			/*
			 * cr�er un nouveau tableau avec un �l�ment de plus,
			 * copier l'acien tableau dans le nouveau 
			 */
			String[] tmp = t;
			t = new String[tmp.length+1];
			for(int i = 0; i<tmp.length; i++){
				t[i] = tmp[i];
			}
			// ajouter au tableau la sous-chainee trouv�e
			t[t.length-1] = m.group(1);
			/*
			 * pointer le curseur vers l'index correspondant � la
			 * fin de la sous-chaine
			 */
			curseur+=(m.group(1).length()+1);

		}
		
		return t;
	}
}
