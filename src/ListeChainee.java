public class ListeChainee<T> {
	
	private Noeud<T> tete;
	private int tailleMax;
	
	public ListeChainee(int tailleMax){
		this.tailleMax = tailleMax;
	}
	/**
	 * Reçoit un nouvel élément et l'ajoute à la liste
	 * 
	 * @param nElem
	 */
	public void add(T nElem){
		Noeud<T> elem = new Noeud<T>(nElem);
		elem.setNext(tete);
		tete = elem;
		
		trim();
	}
	private void trim(){
		Noeud<T> elem = tete;
		if(tailleMax<getLength()){
			for(int i = 0; i<tailleMax-1; i++){
				elem = elem.getNext();
			}
			elem.setNext(null);
		}
	}
	/**
	 * retourne l'élément à l'index spécifié en paramètre
	 * 
	 * @param index
	 * @return
	 */
	public T getAt(int index){
		Noeud<T> elem = null;
		if(index<getLength()){
			elem = tete;
			for(int i = 0; i<index; i++){
				elem = elem.getNext();
			}
		}
		return elem.get();
	}
	/**
	 * retourne le nombre d'éléments dans la liste
	 * 
	 * @return length
	 */
	public int getLength(){
		int length = 0;
		if(!isEmpty()){
			length++;
			Noeud<T> curseur = tete;
			while(curseur.hasNext()){
				curseur = curseur.getNext();
				length++;
			}
		}
		return length;
	}
	/**
	 * retourne un boolean, vrai si la liste est vide
	 * 
	 * @return b
	 */
	public boolean isEmpty(){
		return tete==null;
	}
}