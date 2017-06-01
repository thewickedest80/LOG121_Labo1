import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decomposeur {
	/**
	 * Cette classe décompose la chaine reçue selon les attributs nécessaire à la création d'une forme.
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
	
	public String[] decomposerString(String s){
		/*
		 * tab[0] id
		 * tab[1] type
		 * tab[2] posX
		 * tab[3] posY
		 * tab[4] width
		 * tab[5] height
		 */
		
		String[] tab = new String[6];
		String[] elems = split(s, " ");
		
		id = elems[0];
		type = elems[1].substring(1, elems[1].length()-1);

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
	 * Sert à séparer une chaine selon une deuxième chaine séparatrice.
	 * Donne le même résultat que la méthode String.split(s)
	 * 
	 * Fonctionnement: L'expression régulière permet de trouver la
	 * première sous-chaine se terminant par le séparateur
	 * spécifié (e.g., un espace). On place ensuite le curseur à la
	 * fin de cette sous-chaine et on effectue la recherche à nouveau. À
	 * chaque itération, on ajoute la nouvelle sous-chaine dans
	 * un tableau qu'on allonge d'un élément. 
	 * 
	 * @param String à séparer
	 * @param String séparatrice
	 * @return String[] contenant les résultats
	 */
	private String[] split(String s, String d){
		
		//initialisation du tableau à retourner
		String[] t = new String[0];
		
		Pattern p = Pattern.compile("([^"+d+"]*)");
		Matcher m = p.matcher(s);
		
		int curseur = 0;
		
		while(curseur<s.length()){
			
			/*
			 * Comparer l'expression régulière à la chaîne à partir
			 * de l'index spécifié en paramètre (le curseur)
			 */
			m.find(curseur);
			
			/*
			 * créer un nouveau tableau avec un élément de plus,
			 * copier l'acien tableau dans le nouveau 
			 */
			String[] tmp = t;
			t = new String[tmp.length+1];
			for(int i = 0; i<tmp.length; i++){
				t[i] = tmp[i];
			}
			// ajouter la sous-chainee trouvée au tableau
			t[t.length-1] = m.group(1);
			/*
			 * pointer le curseur vers l'index correspondant à la
			 * fin de la sous-chaine
			 */
			curseur+=(m.group(1).length()+1);

		}
		
		return t;
	}
}
