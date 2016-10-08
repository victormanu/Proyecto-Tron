package tron;

/**
* Esta clase define la malla del mapa y su enlace
* @author Fabian,Adonnis,Erick,Victor
*/
public class Malla {
	//Atributos de la clase malla
	private Malla _derecho;
	private Malla _izquierdo;
	private Malla _arriba;
	private Malla _abajo;
	Object _info;

	/**
	 * Constructor de la clase nodo donde se crea las referencias a los
	 * siguientes nodos, asi como el dato que debe guardar.
	 * @param info
	 */
	public Malla(Object info){
		_derecho = null;
		_izquierdo = null;
		_arriba = null;
		_abajo = null;
		this._info = info;
	}

	/**
	 * Metodo que devuelve el nodo derecho
	 * @return El nodo derecho
	 */

	public Malla getDerecho() {
		return _derecho;
	}

	/**
	 * Agrega o modifica el nodo derecho
	 * @param derecho
	 */
	public void setDerecho(Malla derecho) {
		_derecho = derecho;
	}

	/**
	 * Metodo que devuelve el nodo izquierdo
	 * @return El nodo izquierdo
	 */
	public Malla getIzquierdo() {
		return _izquierdo;
	}

	/**
	 * Agrega o modifica el nodo izquierdo
	 * @param izquierdo
	 */
	public void setIzquierdo(Malla izquierdo) {
		_izquierdo = izquierdo;
	}

	/**
	 * Metodo que devuelve el nodo de arriba
	 * @return El nodo de arriba
	 */
	public Malla getArriba() {
		return _arriba;
	}

	/**
	 * Agrega o modifica el nodo de arriba
	 * @param arriba
	 */
	public void setArriba(Malla arriba) {
		_arriba = arriba;
	}

	/**
	 * Metodo que devuelve el nodo de abajo
	 * @return El nodo de abajo
	 */
	public Malla getAbajo() {
		return _abajo;
	}

	/**
	 * Agrega o modifica el nodo de abajo
	 * @param abajo
	 */
	public void setAbajo(Malla abajo) {
		_abajo = abajo;
	}

	/**
	 * Metodo que devuele la informacion que contiene el nodo
	 * @return La informacion del nodo
	 */
	public Object getInfo() {
		return _info;
	}

	/**
	 * Agrega la informacion del nodo
	 * @param info
	 */
	public void setInfo(Object info) {
		this._info = info;
	}
}