public class Noeud<T> {
	private T elem;
	private Noeud<T> next = null;
	
	public Noeud(T elem){
		this.elem = elem;
	}
	public T get(){
		return elem;
	}
	public boolean hasNext(){
		return next!=null;
	}
	public Noeud<T> getNext(){
		return next;
	}
	public void setNext(Noeud<T> next){
		this.next = next;
	}

}
