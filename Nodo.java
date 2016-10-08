package tron;

/**
 * Esta clase define el nodo con sus atributos y metodos
 * @author Fabian,Adonnis,Erick,Victor
 */
public class Nodo {
	private int _dato;
	// Variable para enlazar nodos
	private Nodo _siguiente;
	// Variable que guarda el elemento de la cola
	private String elemento;

	public Nodo() {
		_dato = 0;
		_siguiente = null;
	}

	/**
	 * Constructor del nodo con el mismp nombre de la clase
	 * 
	 * @param El parametro o define el nuevo nodo.
	 */
	public Nodo(String _poder) {
		this.set_elemento(_poder);
		this._siguiente = null;
	}
	
	/**
	 * Metodo que ingresa un dato en el nodo
	 * @param dato
	 */
	public Nodo(int dato) {
		this._dato = dato;
	}

	/**
	 * Metodo que asigna el dato a un nodo
	 * @param dato
	 */
	public void set_dato(int dato) {
		this._dato = dato;
	}
	
	public int get_dato() {
		return _dato;
	}

	public void set_siguiente(Nodo nodo) {
		this._siguiente = nodo;
	}

	public Nodo get_siguiente() {
		return _siguiente;
	}

	public String get_elemento() {
		return elemento;
	}

	public void set_elemento(String elemento) {
		this.elemento = elemento;
	}
}