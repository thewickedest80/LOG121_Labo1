
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
		
		String[] tab = new String[6];
		String[] elems = s.split(" ");
		
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
}
