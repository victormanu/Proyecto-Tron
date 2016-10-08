package tron;

/**
 * Esta clase define la estructura de datos lista
 * @author Adonnis, Fabian, Victor, Erick
 */
public class Lista {
	//Atributos de la clase lista
	private int _tamanio;
	private Nodo _cabeza;
	private Nodo _fin;
	private Nodo _nuevoNodo;
	private Nodo _temp;
	private Nodo _temp2;

	/**
	 * Constructor de la clase lista donde se inicializan las variables cabeza,
	 * fin, tamanio. La cabeza representa el primer nodo de la lista fin
	 * representa el ultimo nodo de la lista
	 */
	public Lista() {
		this._cabeza = null;
		this._fin = null;
		this._tamanio = 0;
	}

	/**
	 * Metodo que devuelve el dato del ultimo nodo
	 * @return El dato del ultimo nodo
	 */
	public int obtenerUltimo() {
		return this._fin.get_dato();
	}

	/**
	 * Metodo en la cual se recibe un dato y se se inserta al inicio de la lista
	 * @param _dato
	 */
	public void insertarAlInicio(int _dato) {
		// Variable temporal que representa la cabeza
		_temp = this._cabeza;
		// Nodo con el dato a insertar
		_temp2 = new Nodo(_dato);
		// Se inserta al el nuevo nodo la cabeza y este mismo pasa a ser la
		// cabeza
		_temp2.set_siguiente(_temp);
		this._cabeza = _temp2;
		// Se aumenta el tamaño de la lista
		this._tamanio++;
	}

	/**
	 * Si la cabeza es nula quiere decir que la lista esta vacia, este metodo
	 * sirve para control a la hora de utilizar los demas metodos.
	 * @return Un booleano
	 */
	public boolean estaVacia() {
		// Si la funcion esa vacia retorna true
		if (this._cabeza == null) {
			return true;
		}
		// Si no esta vacia retorna false
		else {
			return false;
		}
	}
	/*
	 * Metodo que elimina el primer elimento de la lista
	 */

	public void eliminarPrimero() {
		// Nodo temporal que representa a la cabeza
		_temp = this._cabeza;
		// Si la lista no esta vacia
		if (this.estaVacia() == false) {
			// Se crea otro no temporal que es el siguiente de la cabeza y se le
			// asigna el valor de la cabeza
			_temp2 = _temp.get_siguiente();
			// A la cabeza se le quita la referencia al siguiente nodo que en
			// este caso va a ser la nueva cabeza
			_temp.set_siguiente(null);
			this._cabeza = _temp2;
			this._tamanio--;
		}
	}

	/**
	 * Metodo que elimina el ultimo nodo de una lista
	 */
	public void eliminarUltimo() {
		// Temporal que representa la cabeza
		_temp = this._cabeza;
		// Entero que representa la posicion que esta en el antes del ultimo
		int pos = this._tamanio - 2;
		// Ciclo que actualiza la temporal hasta estar en el nodo antes del
		// ultimo
		while (pos != 0) {
			_temp = _temp.get_siguiente();
			pos--;
		}
		// La posicion que esta antes del ultimo se le asigna el siguiente valor
		// como nulo
		_temp.set_siguiente(null);
		// Se le asigna el valor de ultimo nodo a la temporal
		this._fin = _temp;
		this._tamanio--;
	}

	/**
	 * Metodo que inserta un nuevo nodo
	 * @param _datos
	 */
	public void insertarNodo(int _datos) {
		// En caso de que la lista este vacia al nodo le agrega el valor de
		// cabeza y de final
		if (this.estaVacia() == true) {
			_temp = new Nodo(_datos);
			this._cabeza = _temp;
			this._fin = _temp;
			this._tamanio++;
		}
		// Si la lista no esta vacia
		else {
			// Se crea un nuevo nodo y con el dato que entro como parametro en
			// el metodo
			_nuevoNodo = new Nodo(_datos);
			// A el ultimo nodo se le agrega el nuevo nodo
			this._fin.set_siguiente(_nuevoNodo);
			// Se crea un nuevo nodo con el ultimo nodo para asignarle el
			// atributo de fianl
			_temp = this._fin.get_siguiente();
			this._fin = _temp;
			this._tamanio++;
		}
	}

	/**
	 * Metodo que aumenta la estela del jugador
	 * @param _jugador
	 */
	public void aumentarEstela(int _jugador) {
		// En caso de que este vacia la lista se crea el valor que correponde a
		// la moto y se le asigna el numero de jugado
		if (this.estaVacia() == true) {
			// Se crea un nuevo nodo con el numero del jugador
			_nuevoNodo = new Nodo(_jugador);
			// Se le asigna el valor de cabeza y el valor de fin
			this._cabeza = _nuevoNodo;
			this._fin = _nuevoNodo;
			this._tamanio++;
		}
		// Si la lista no esta vacia
		else {
			// Se le asigna los valores correpondientes a la estela
			// Se crea un nuevo nodo con el el valor del jugador y se le suma
			// 0.1 para diferenciar la estela de la moto
			_nuevoNodo = new Nodo((int) (_jugador + 0.1));
			// A el ultimo nodo se le asigana como ultimo el nuevo nodo
			this._fin.set_siguiente(_nuevoNodo);
			// Se crea una temporal igual al nuevo nodo
			_temp = this._fin.get_siguiente();
			// se le asigna el valor de final
			this._fin = _temp;
			this._tamanio++;
		}
	}

	/**
	 * Metodo que retorna el dato que contiene un nodo en una lista
	 * @param _pos
	 * @return
	 */
	public int ObtenerNodo(int _pos) {
		// Se crea una temporal que es igual a la cabeza
		_temp = this._cabeza;
		// En caso de que la lista no estevacia
		if (this.estaVacia() != true) {
			// Mientras la posicion del nodo que se quiera obtener no sea 0
			// se cambia el valor de la temporal por el nodo siguiente
			while (_pos != 0) {
				_temp = _temp.get_siguiente();
				_pos--;
			}
			return _temp.get_dato();
		}
		// Retorna el dato de la temporal
		return _temp.get_dato();
	}
	
	/**
	 * Metodo que devuelve el tamanio de la lista
	 * @return El tamanio de la lista
	 */
	public int get_tamano() {
		return _tamanio;
	}
	
	/**
	 * Metodo que le asigna el tamanio a la lista
	 * @param _tamanio
	 */
	public void set_tamano(int _tamanio) {
		this._tamanio = _tamanio;
	}
}