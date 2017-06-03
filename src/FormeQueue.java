
public class FormeQueue{
	/**
	 * Cette classe ajoute des formes � un tableau � la mani�re d'une file.
	 * 
	 * @author Louis Raymond-Poirier
	 * @date 2017/05/31
	 */
	private int length;
	private Forme[] tab;
	
	public FormeQueue(int length){
		this.length = length;
		tab = new Forme[length];		
	}
	/**
	 * V�rifie si la file est vide
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		
		boolean b = false;
		if(tab[0]==null){
			b = true;
		}
		return b;
	}
	/**
	 * V�rifie si la file est pleine
	 * 
	 * @return boolean
	 */
	public boolean isFull(){
		
		boolean b = false;
		if(tab[length-1]!=null){
			b = true;
		}
		return b;
	}
	/**
	 * Ajoute une forme en t�te de file, � l'�l�ment 0. La derni�re forme � avoir �t� ajout�e en file est retir�e.
	 * 
	 * @param forme
	 */
	public void add(Forme forme){
		
		if(isEmpty()){
			tab[0] = forme;
		}else{
			
			for(int i = length-1;i>0;i--){
				tab[i] = tab[i-1];
			}
			tab[0] = forme;			
		}
	}
	/**
	 * Retourne la forme se trouvant � l'index sp�cifi� en param�tre
	 * 
	 * @param index
	 * @return Forme
	 */
	public Forme get(int index){
		return tab[index];
	}
	/**
	 * Retourne le nombre de formes dans la file
	 * 
	 * @return int
	 */
	public int getLength(){
		int nbFormes = 0;
		if(isFull()){
			nbFormes = length;
		}else{
			for(int i=0; i<length; i++){
				if(tab[i]!=null){
				nbFormes++;				
				}
			}
		}
		return nbFormes;
	}

}
