package tron;

//Importa la libreria random de Java
import java.util.Random;

/**
 * Esta clase define el mapa con sus atributos y metodos
 * @author Fabian,Adonnis,Erick,Victor
 */
public class Mapa extends Thread {
	//Atributos de la clase mapa
	private Malla _esquinaIzquieraArriba;
	private int _tamanio;
	private static Mapa _miMapa;
	private Object _objeto;
	private int _coordenadaX;
	private int _coordenadaY;
	private static Random _random = new Random();

	/**
	 * Metodo para instanciar el mapa de manera que se cree un singlenton
	 * @return La instancia del mapa
	 */
	public static Mapa getMapa() {
		if (_miMapa == null) {
			_miMapa = new Mapa();
		}
		return _miMapa;
	}

	/**
	 * Constructor privado porque solo se va a crear una unica instancia de la
	 * clase
	 */
	private Mapa() {
		this._esquinaIzquieraArriba = new Malla(0);
		this._tamanio = 30;
		this.crearMapa();
	}
	
	/**
	 * Metodo que devuelve si el espacio esta vacio.
	 * @return Un booleano verdadero o falso de acuerdo a la condicion.
	 */
	/**private boolean estaVacia() {
		boolean _vacia = false;
		if (this._esquinaIzquieraArriba == null) {
			_vacia = true;
		}
		return _vacia;
	}

	/**
	 * Metodo que enlaza dos nodos para crear una lista doblemente enlazada, en
	 * este caso horizontal
	 * @param _nodoA
	 * @param _nodoB
	 */
	private void horizontal(Malla _nodoA, Malla _nodoB) {
		_nodoA.setDerecho(_nodoB);
		_nodoB.setIzquierdo(_nodoA);
	}

	/**
	 * Metodo que enlaza dos nodos para crear un lista doblemente enlazada
	 * verticalmente
	 * @param _nodoA
	 * @param _nodoB
	 */
	private void Vertical(Malla _nodoA, Malla _nodoB) {
		_nodoA.setAbajo(_nodoB);
		_nodoB.setArriba(_nodoA);
	}

	/**
	 * Metodo que crea la malla que representa el mapa de el juego
	 */
	public void crearMapa() {
		// Se crea una variable temporal con la cantidad de de columnas que va a
		// tener
		int _cantidadColumnas = this._tamanio;
		// Se crea un nodo temporal
		Malla _temporal = this._esquinaIzquieraArriba;
		// Se crea un lista doblemente enlazada horizontal
		while (_cantidadColumnas != 0) {
			Malla _nuevo = new Malla(0);
			this.horizontal(_temporal, _nuevo);
			_temporal = _temporal.getDerecho();
			_cantidadColumnas--;

		}
		// Se repite el mismo proceso que se hizo con las columnas solo que en
		// este caso con ls filas y se enlazan verticalmente
		int _cantidadFilas = this._tamanio;
		Malla _temporal2 = this._esquinaIzquieraArriba;
		while (_cantidadFilas != 0) {
			Malla _nuevo = new Malla(0);
			this.Vertical(_temporal2, _nuevo);
			_temporal2 = _temporal2.getAbajo();
			_cantidadFilas--;
		}
		this.enlazarMapa();
	}

	/**
	 * Metodo que enlaza el mapa creando las columnas y con el metodo obtener
	 * nodo las enlaza las filas
	 */
	public void enlazarMapa() {
		Malla _temp = this._esquinaIzquieraArriba.getDerecho();

		int _cantColumnas = this._tamanio - 1;
		int _Y = 0;
		while (_cantColumnas != 0) {
			// Mientras la cantidad de columnas sea diferente de 0
			int X = 1;
			Malla _temp2 = _temp;
			int cantFilas = this._tamanio;
			while (cantFilas != 0) {
				// Se crea un nuevo nodo
				Malla nuevo = new Malla(0);
				// Se enlaza con el temporal numero dos
				this.Vertical(_temp2, nuevo);
				// El temporal numero dos baja
				_temp2 = _temp2.getAbajo();
				// El temporal 3 es el que esta en la misma fila a la izquierda
				Malla temp3 = this.buscarPorCordenadaParaEnlazar(_Y, X);
				// Enlaza a el temporal tres con el temporal dos
				this.horizontal(temp3, _temp2);
				cantFilas--;
				X++;
			}
			// El temporal pasa a ser el siguiente de la columna
			_temp = _temp.getDerecho();
			_cantColumnas--;
			_Y++;
		}

	}

	/**
	 * Metodo que avanza por fila y coulmnas buscando los nodos para poder
	 * enlazarlos
	 * @param Y
	 * @param X
	 * @return La variable temporal a enlazar
	 */
	public Malla buscarPorCordenadaParaEnlazar(int Y, int X) {
		Malla _temp = this._esquinaIzquieraArriba;
		int _cantX = 0;
		int _cantY = 0;
		while (_cantY != Y) {
			_temp = _temp.getDerecho();
			_cantY++;
		}
		while (_cantX != X) {
			_temp = _temp.getAbajo();
			_cantX++;
		}
		return _temp;
	}
	
	/**
	 * Metodo que modifica el dato en el mapa segun en las coordenadas
	 * @param X
	 * @param Y
	 * @param _dato
	 */
	public void modificarDato(int X, int Y, Object _dato) {
		Malla _temp = this._esquinaIzquieraArriba;
		int _cantX = 0;
		int _cantY = 0;
		while (_cantY != Y) {
			_temp = _temp.getDerecho();
			_cantY++;
		}
		while (_cantX != X) {
			_temp = _temp.getAbajo();
			_cantX++;
		}
		_temp.setInfo(_dato);

	}

	/**
	 * Metodo que avanza por filas y columnas para obtener un dato
	 * @param X
	 * @param Y
	 * @return
	 */
	public Object obtenerDato(int X, int Y) {
		// La variable temp representa la esquina izquierda de la matriz
		Malla _temp = this._esquinaIzquieraArriba;
		int _cantX = 0;
		int _cantY = 0;
		// Avanza la cantidad de filas que el usuario indique
		while (_cantX != X) {
			_temp = _temp.getAbajo();
			_cantX++;
		}
		// Avanza la cantidad de columnas que el usuario indique
		while (_cantY != Y) {
			_temp = _temp.getDerecho();
			_cantY++;
		}
		// Retorna la informacion del dato
		return _temp._info;
	}
	
	/**
	 * Metodo que imprime el mapa
	 */
	public void run() {
		while(true){
		int X = 0;
		int Y = 0;
		while (X != this._tamanio) {
			while (Y != this._tamanio) {
				System.out.print(this.obtenerDato(X, Y) + " ");
				Y++;
			}
			System.out.print(" \n");

			Y = 0;
			X++;
			
		}
		System.out.print("\n\n\n\n\n");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
		}
		this.ubicarObjetos();
		}
	}
	


	

	/**
	 * Metodo que ubica las las motos en el mapa de acuerdo a su numero de
	 * jugador
	 * @param _moto
	 */
	public void ubicarMoto(Moto _moto) {
		int _temp = 4;
		int X = 0;
		int Y = 0;
		int _direccionInicial = 0;
		// Dependiendo el jugador que sea ya se tiene definas sus respetivas
		// coordenadas de salida
		if (_moto.getJugador() == 1) {
			X = 3;
			Y = 0;
			_direccionInicial = 0;
		}
		if (_moto.getJugador() == 2) {
			X = 3;
			Y = 29;
			_direccionInicial = 0;
		}

		if (_moto.getJugador() == 2) {
			X = 3;
			Y = 29;
			_direccionInicial = 0;
		}
		if (_moto.getJugador() == 3) {
			X = 26;
			Y = 0;
			_direccionInicial = 1;
		}
		if (_moto.getJugador() == 4) {
			X = 26;
			Y = 29;
			_direccionInicial = 1;
		}
		// Agrega a la lista de coordanadas la posiciones de la moto donde
		// respectivamente van
		int posLista = 0;
		int temp2 = 4;
		while (_temp != 0) {
			// En caso de ser el primer dato a insertar a la lista se guarda con
			// la etiqueta de moto
			if (temp2 == 4) {
				this.modificarDato(X, Y, _moto.getnMoto());
				Lista listaposX = _moto.getPosX();
				Lista listaposY = _moto.getPosY();
				listaposX.insertarNodo(X);
				listaposY.insertarNodo(Y);
				if (_direccionInicial == 0) {
					X--;
				} else {
					X++;
				}
			}
			// En caso de no ser el primero se guarda con la etiqueta de estela
			else {
				this.modificarDato(X, Y, _moto.getnEstela());
				Lista listaposX = _moto.getPosX();
				listaposX.insertarNodo(X);
				Lista listaposY = _moto.getPosY();
				listaposY.insertarNodo(Y);
				if (_direccionInicial == 0) {
					X--;
				} else {
					X++;
				}
			}
			posLista++;
			_temp--;
			temp2--;
		}
	}

	/**
	 * Metodo que mueve la motos en el mapa y actualiza las posiciones en el
	 * mismo
	 * @param _coordenadas
	 * @param _jugador
	 * @param _moto
	 */
	public void moverMoto(Coordenada[] _coordenadas, int _jugador, Moto _moto, boolean _escudo) {
		// La cordenada inicial esta en la posicion 0 y guarda los datos en
		// coordenadas
		Coordenada _coordenadaInicial = _coordenadas[0];
		int IX = _coordenadaInicial.getX();
		int IY = _coordenadaInicial.getY();
		// Las coordenadas finales estan en la posicion 2 y las guarda en variable
		Coordenada _coordenadaFinal = _coordenadas[2];
		int UX = _coordenadaFinal.getX();
		int UY = _coordenadaFinal.getY();
		// La coordenada donde se va a mover se ubica en la posicion 1 y las
		// guarda en variable
		Coordenada _coordenadaSiguiente = _coordenadas[1];
		int AX = _coordenadaSiguiente.getX();
		int AY = _coordenadaSiguiente.getY();
		// Modifica las posiciones en el mapa
		this.modificarDato(UX, UY, 0);
		this.modificarDato(IX, IY, _moto.get_nickEstela());
		Object celda = this.obtenerDato(AX, AY);
		if(_escudo == false){
			if (celda == "B" || celda == "M1" || celda == "M2" || celda == "M3" || celda == "M4" || celda == "E1" || celda == "E2" || celda == "E3" || celda == "E4") {
				_moto.encolar_Items("B");
				_moto.destruirMoto();
				_moto.aplicar_Items();
			} else if (celda == "S") {
				CrecimientoEstela estela = new CrecimientoEstela();
				_moto.encolar_Items("S");
				_moto.aumentarEstela(estela.getValor());
				_moto.aplicar_Items();
				this.modificarDato(AX, AY, _moto.getnMoto());
			} else if (celda == "C"){
				CeldaCombustible combustible = new CeldaCombustible();
				_moto.encolar_Items("C");
				_moto.aumentarCombustible(combustible.getValor());
				_moto.aplicar_Items();
				this.modificarDato(AX, AY, _moto.getnMoto());
			} else if (celda == "V"){
				_moto.apilar_Poder("V");
				this.modificarDato(AX, AY, _moto.getnMoto());
			} else if (celda == "E"){
				_moto.apilar_Poder("E");
				this.modificarDato(AX, AY, _moto.getnMoto());
			} else {
				this.modificarDato(AX, AY, _moto.get_nickMoto());
			}
		} else if (celda == "S") {
			CrecimientoEstela estela = new CrecimientoEstela();
			_moto.encolar_Items("S");
			_moto.aumentarEstela(estela.getValor());
			_moto.aplicar_Items();
			this.modificarDato(AX, AY, _moto.getnMoto());
		} else if (celda == "C"){
			CeldaCombustible combustible = new CeldaCombustible();
			_moto.encolar_Items("C");
			_moto.aumentarCombustible(combustible.getValor());
			_moto.aplicar_Items();
			this.modificarDato(AX, AY, _moto.getnMoto());
		} else if (celda == "V"){
			_moto.apilar_Poder("V");
			this.modificarDato(AX, AY, _moto.getnMoto());
		} else if (celda == "E"){
			_moto.apilar_Poder("E");
			this.modificarDato(AX, AY, _moto.getnMoto());
		} else {
			this.modificarDato(AX, AY, _moto.get_nickMoto());
		}
	}

	/**
	 * Reinicia la posicion de la moto
	 * @param X
	 * @param Y
	 */
	public void reiniciarMoto(int X, int Y) {
		this.modificarDato(X, Y, 0);
	}

	/**
	 * Metodo que reinicia las posiciones de la moto en el mapa
	 * @param _posX
	 * @param _posY
	 */
	public void reiniciarPos(Lista _posX, Lista _posY) {
		int cant = _posX.get_tamano();
		int temp = 0;
		// En el ciclo recorre la listas reiniciando las posiciones donde estan
		// las motos
		while (cant != 0) {
			int X = _posX.ObtenerNodo(temp);
			int Y = _posY.ObtenerNodo(temp);
			this.modificarDato(X, Y, 0);
			temp++;
			cant--;
		}
	}
	/**
	 * Metodo que ubica los poderes e items en el mapa
	 */
	public void ubicarObjetos()
    {
    	String _nombres[] = {"C","S","B","E","V"};
    	_objeto = _nombres[_random.nextInt(_nombres.length)];
    	int ejeX[] = new int[30];
    	_coordenadaX= _random.nextInt(ejeX.length);
    	int ejeY[] = new int[30];
    	_coordenadaY = _random.nextInt(ejeY.length);
    	modificarDato(_coordenadaX,_coordenadaY,_objeto);
    }
}