package tron;

/**
 * Esta clase define el objeto moto con sus atributos y metodos
 * @author Fabian,Adonnis,Erick,Victor
 */
public class Moto extends Thread {
	// Muestran en que direccion se encuentra la moto
	private boolean _direccionAbajo;
	private boolean _direccionArriba;
	private boolean _direccionDerecha;
	private boolean _direccionIzquierda;
	private Coordenada _coordenada;
	private Coordenada _coordenadaInicial;
	private Coordenada _coordenadaFinal;
	private Coordenada _coordenadaMovimiento;
	private Coordenada[] _coordenadas;
	//Muestra si la moto se detruyo
	protected boolean _destruida;
	// Atributo que permite aumentar la estela de la moto
	private int _cantEstelaAumentar;
	// Atributo de el combutible
	private int _combustible;
	// Con este contador se disminuye el combustible cada 5 celdas
	private int _contadorCombustible;
	private int _finX;
	private int _finY;
	// Atributo de el numero de jugador
	protected int _jugador;
	private int _posFinalX;
	private int _posFinalY;
	private int _posInicialX;
	private int _posInicialY;
	// Tamanio actual de la estela
	private int _tamanoEstela;
	private int _temp;
	// Atributo de velocidad de la moto
	private int _velocidad;
	// Lista de coordenads
	private Lista _cordenadas;
	// Lista que representa la estela
	private Lista _estela;
	// Lista de cordenadas en X
	protected Lista _posX;
	// Lista de cordenadas en Y
	protected Lista _posY;
	private Mapa _mapa;
	private Mapa _mapa2;
	// Se represeta la etela en el mapa
	private String _nickEstela;
	// Se representa la moto en el mapa
	private String _nickMoto;
	private String _direccionActual;
	private Pila _pilaPoderes;
	private Cola _colaItems;
	
	private boolean _escudo;
	private boolean _hvelocidad;
	
	/**
	 * Constructor de la clase moto
	 * @param _jugador
	 *            Representa el numero de jugador que es y apartir de el se crea
	 *            la etiquetas de la moto y la estela
	 */
	public Moto(int _jugador) {
		// Se crea la estela de la moto
		this._estela = new Lista();
		this._colaItems = new Cola();
		this._pilaPoderes = new Pila();
		this._jugador = _jugador;
		this._escudo = false;
		this._hvelocidad = false;
		if (this._jugador == 1) {
			this._nickEstela = "E1";
			this._nickMoto = "M1";
			//this._escudo = true;
			this._direccionActual = "aba";
		}
		if (this._jugador == 2) {
			this._nickEstela = "E2";
			this._nickMoto = "M2";
			//this._escudo = true;
			this._direccionActual = "aba";
		}
		if (this._jugador == 3) {
			this._nickEstela = "E3";
			this._nickMoto = "M3";
			this._direccionActual = "arr";
		}
		if (this._jugador == 4) {
			this._nickEstela = "E4";
			this._nickMoto = "M4";
			this._direccionActual = "arr";
		}
	
		_temp = 4;
		// Se inicializa la estelas con 4 espacios fijos
		while (_temp != 0) {
			this._estela.aumentarEstela(_jugador);
			// Se pone un 1 ya que que es la cantidad de veces que debe repetir
			// la funcion
			_temp--;
		}
		// Se inicializa el valor de combustible en 100
		this._combustible = 100;
		// El valor inicial de la estela siempre es 3
		this._tamanoEstela = 3;
		// Se crean dos listas que representan las coordenadas de la moto en el
		// mapa
		this._posX = new Lista();
		this._posY = new Lista();
		// Atributo con el cual se aumenta la estela en caso de que la moto tome
		// el item de aumentar cantidad de estela
		this._cantEstelaAumentar = 0;
		// Lista con cordenadas
		this._cordenadas = new Lista();
		// Atributo con el cual se verifica si la moto sta destruida
		this._destruida = false;
		// Contador de combustible que permite disminur su combustible cada 5
		// celdes recorridas
		this._contadorCombustible = 5;
		// Configuracion de direcciones dependiendo la posicione en el mapa que
		// tengan al iniciar
		
		if (this._jugador == 1) {
			this._direccionAbajo = true;
			this._direccionArriba = false;
			this._direccionDerecha = false;
			this._direccionIzquierda = false;
		} else if (this._jugador == 2) {
			this._direccionAbajo = true;
			this._direccionArriba = false;
			this._direccionDerecha = false;
			this._direccionIzquierda = false;
		} else if (this._jugador == 3) {
			this._direccionAbajo = false;
			this._direccionArriba = true;
			this._direccionDerecha = false;
			this._direccionIzquierda = false;
		} else if (this._jugador == 4) {
			this._direccionAbajo = false;
			this._direccionArriba = true;
			this._direccionDerecha = false;
			this._direccionIzquierda = false;
		}
		
	}

	/**
	 * Modifica la cantidad de estela o la reinicia para que siempre cada 5
	 * espacios disminuya su combustible
	 * @param _cantEstelaAumentar
	 * 				El parametro _cantEstelaAumentar define la cantidad de estela a aumentar
	 * 				en la moto.
	 */
	public void setCantEstelaAumentar(int _cantEstelaAumentar) {
		this._cantEstelaAumentar = _cantEstelaAumentar;
	}
	

	/**
	 * Metodo que disminuye el combustible de la moto por cada 5 espacios de
	 * estela que recorra
	 */
	public void autoBajarCombustible() {
		// El contador de combustible se disminuye
		this._contadorCombustible--;
		// Si el contador de combustible es igual a 0 se le disminuye el
		// combustible a la moto y se reinicia el contador
		if (this._contadorCombustible == 0) {
			this._combustible -= 1;
			this._contadorCombustible = 5;
		}
		// Si el combustible de la moto es 0 se destruye
		else if (this._combustible == 0) {
			this.destruirMoto();
		}
	}

	/**
	 * Metodo que aumenta la cantidad de estela de la moto dependiendo de cuanta
	 * cantidad le entre como parametro
	 * @param _cantVeces
	 * 				El parametro _cantVeces define la cantidad de estela a aumentar
	 */
	public void aumentarEstela(int _cantVeces) {
		// Aumenta la cantidad de veces la cantidad que le entra como parametro
		this.setCantEstelaAumentar(_cantVeces);
		// Aumenta la estela de la moto
		while (_cantVeces != 0) {
			this._estela.aumentarEstela(this._jugador);
			_cantVeces--;
		}
	}

	/**
	 * Metodo que aumenta la cantidad de combustible de la moto dependiendo de cuanta
	 * cantidad le entra como parametro.
	 * @param _cantidad
	 * 				El parametro _cantidad define la cantidad de combustible a aumentar
	 */
	public void aumentarCombustible(int _cantidad) {
		this._combustible += _cantidad;
		if (this._combustible > 100) {
			this._combustible = 100;
			this._contadorCombustible = 5;
		}
	}

	/**
	 * Metodo que disminute la cantidad de combustible
	 */
	public void disminuirCombustible() {
		this._combustible--;
	}

	/**
	 * Metodo que devuelve el tamanio de la estela de la moto
	 * @return El tamanioo de la estela de la moto
	 */
	public int getTamanoEstela() {
		return _tamanoEstela;
	}

	/**
	 * Metodo que obtiene la posicion de una estela
	 * @param _pos
	 * 			El paramatro _pos es la posicion que se quiere obtener
	 * 			de la estela.
	 * @return El nodo de acuerdo a la posicion de la estela
	 */
	public int obtenerEstela(int _pos) {
		return this._estela.ObtenerNodo(_pos);
	}

	/**
	 * Metodo que devuelve el numero del jugador
	 * @return El numero del jugador
	 */
	public int getJugador() {
		return _jugador;
	}

	/**
	 * Metodo que obtiene la coordenada final y la elimina
	 * @return De la lista de posiciones saca el ultimo que representa el ultimo
	 *         espacio de estela de la moto y lo elimina
	 */
	public Coordenada ObtenerCoordenadaFinalYEliminar() {
		// Las posiciones finales son el ultimo dato de la lista en X y en Y
		_posFinalX = this._posX.obtenerUltimo();
		_posFinalY = this._posY.obtenerUltimo();
		// Se crea una coordena con ambos valores
		_coordenada = new Coordenada(_posFinalX, _posFinalY);
		// Se elimina los datos de las coordenadas
		this._posX.eliminarUltimo();
		this._posY.eliminarUltimo();
		// Se retorna la coordenada
		return _coordenada;
	}

	/**
	 * Metodo que devuelve la coordenada sin elminarla
	 * @return La coordenada sin eliminarla.
	 */
	public Coordenada ObtenerCordenadaFinalSinEliminar() {
		int posFinalX = this._posX.obtenerUltimo();
		int posFinalY = this._posY.obtenerUltimo();
		_coordenada = new Coordenada(posFinalX, posFinalY);
		return _coordenada;
	}

	/**
	 * Metodo que a partir de una direccion la moto se mueve en esa direccion
	 * @param _direccion
	 * 				El parametro _direccion define la direccion en la que se
	 * 				mueve la moto.
	 */
	public void moverMoto(String _direccion) {
		// Se obtiene las ultimas cordenadas para para verificar que no se salga
		// de los bordes
		_finX = this._posX.obtenerUltimo();
		_finY = this._posY.obtenerUltimo();
		// Se crea un arreglo de 3 espacios para meterles las cordenadas
		_coordenadas = new Coordenada[3];
		// Se obtiene las cordenadas iniciales para crear una instacia de
		// cordenada con estos valores y se agrega en la primera posicion del
		// arreglo
		_posInicialX = this._posX.ObtenerNodo(0);
		_posInicialY = this._posY.ObtenerNodo(0);
		_coordenadaInicial = new Coordenada(_posInicialX, _posInicialY);
		_coordenadas[0] = _coordenadaInicial;
		// Si no se tiene que aumentar la estela entra y los ultimos valores de
		// coordenada los retorna y los elimina
		if (this._cantEstelaAumentar == 0) {
			_coordenadaFinal = this.ObtenerCoordenadaFinalYEliminar();
			_coordenadas[2] = _coordenadaFinal;
		}
		// En este otro caso retorna los valores de coordenada y los elimina
		else {
			_coordenadaFinal = this.ObtenerCordenadaFinalSinEliminar();
			_coordenadas[2] = _coordenadaFinal;
			this._cantEstelaAumentar--;
		}
		// Se crea una instancia del mapa
		_mapa = Mapa.getMapa();
		// En caso de que la direccion sea abajo se aumenta el valor de fila
		if (_direccion == "aba") {
			_posInicialX++;
			// Si la posicion inicial es mayor a 29 la moto se debe de destruir
			// ya que ese valor no existe en el mapa
			if (_posInicialX > 29) {
				// actualiza la posiciones en el mapa
				_mapa.reiniciarMoto(_finX, _finY);
				this.destruirMoto();
			}
			// En caso de que no haya problema se insertan al inicio las
			// posiciones X y Y al inicio de las lista de coordenadas
			else {
				this._posX.insertarAlInicio(_posInicialX);
				this._posY.insertarAlInicio(_posInicialY);
				// Se crea una nueva coordenada y se agrega al el arreglo
				_coordenadaMovimiento = new Coordenada(_posInicialX, _posInicialY);
				_coordenadas[1] = _coordenadaMovimiento;
				// Envia a el mapa las coordenadas para que lo ubique
				_mapa.moverMoto(_coordenadas, _jugador, this, this._escudo);
			}
		}
		// En caso de que la direccion sea arriba
		if (_direccion == "arr") {
			// Se disminuye la posicion de la fila
			_posInicialX--;
			// Si es menor que cero la moto se destruye
			if (_posInicialX < 0) {
				_mapa.reiniciarMoto(_finX, _finY);
				this.destruirMoto();
			}
			// Si no pasa lo anterior la moto
			else {
				// Se insertan las posiciones y se mandan al mapa para que se
				// actualice
				this._posY.insertarAlInicio(_posInicialY);
				this._posX.insertarAlInicio(_posInicialX);
				_coordenadaMovimiento = new Coordenada(_posInicialX, _posInicialY);
				_coordenadas[1] = _coordenadaMovimiento;
				_mapa.moverMoto(_coordenadas, _jugador, this, this._escudo);
			}
		}
		//La misma logica que las anteriores
		if (_direccion == "der") {
			_posInicialY += 1;
			if (_posInicialY > 29) {
				_mapa.reiniciarMoto(_finX, _finY);
				this.destruirMoto();
			} else {
				this._posX.insertarAlInicio(_posInicialX);
				this._posY.insertarAlInicio(_posInicialY);
				Coordenada _coordenadaMovimiento = new Coordenada(_posInicialX, _posInicialY);
				_coordenadas[1] = _coordenadaMovimiento;
				_mapa.moverMoto(_coordenadas, _jugador, this, this._escudo);
			}
			// return _coordenadas;
		}
		if (_direccion == "izq") {
			_posInicialY -= 1;
			if (_posInicialY < 0) {
				_mapa.reiniciarMoto(_finX, _finY);
				this.destruirMoto();
			} else {
				this._posX.insertarAlInicio(_posInicialX);
				this._posY.insertarAlInicio(_posInicialY);
				_coordenadaMovimiento = new Coordenada(_posInicialX, _posInicialY);
				_coordenadas[1] = _coordenadaMovimiento;
				_mapa.moverMoto(_coordenadas, _jugador, this, this._escudo);
			}
		}
	}

	/**
	 * Metodo que destruye la moto y envia las coordenadas al mapa para que
	 * este se actualice
	 */
	public void destruirMoto() {
		// Se instancia el mapa
		_mapa = Mapa.getMapa();
		// Se envian la coordenadas y para reiniciarlas en el mapa
		_mapa.reiniciarPos(this._posX, this._posY);
		// Se activa la bandera de que esta destruida para cortar los ciclos
		this._destruida = true;
		_temp = this._tamanoEstela + 1;
		// Se elmina la estela
		while (_temp != 0) {
			this._posX.eliminarPrimero();
			this._posY.eliminarPrimero();
			this._estela.eliminarPrimero();
			_temp--;
		}
	}

	/**
	 * Metodo que devuelve como se representa la estela en el mapa
	 * @return La variable de como se representa la estela en el mapa
	 */
	public String getnEstela() {
		return _nickEstela;
	}

	/**
	 * Metodo que devuelve como se representa la moto en el mapa
	 * @return La variable de como se representa la moto en el mapa
	 */
	public String getnMoto() {
		return _nickMoto;
	}

	/**
	 * Metodo que cambia como se representa la estela en el mapa
	 * @param _nickEstela
	 * 				El parametro _nickEstela define la variable de
	 * 				como se representa la estela en el mapa.
	 */
	public void setnEstela(String _nickEstela) {
		this._nickEstela = _nickEstela;
	}

	/**
	 * Metodo que cambia como se representa la moto en el mapa
	 * @param _nickMoto
	 * 				El parametro de _nickMoto define la variable de
	 * 				como se representa la estela en el mapa.
	 */
	public void setnMoto(String _nickMoto) {
		this._nickMoto = _nickMoto;
	}

	/**
	 * Metodo que cambia las direcciones de la moto, de modo que se activa la
	 * bandera de la direccion actual y se desactivan las demas direcciones
	 * @param _direccion
	 * 				El parametro _direccion define la direccion de la moto que
	 * 				se va a cambiar
	 */
	public void cambiarDirecciones(String _direccion) {
		if (_direccion == "aba") {
			if (this._direccionActual != "arr") {
				this._direccionAbajo = true;
				this._direccionArriba = false;
				this._direccionDerecha = false;
				this._direccionIzquierda = false;
			}
		}
		if (_direccion == "arr") {
			if (this._direccionActual != "aba") {
				this._direccionAbajo = false;
				this._direccionArriba = true;
				this._direccionDerecha = false;
				this._direccionIzquierda = false;
			}
		}
		if (_direccion == "der") {
			if (_direccionActual != "izq") {
				this._direccionAbajo = false;
				this._direccionArriba = false;
				this._direccionDerecha = true;
				this._direccionIzquierda = false;
			}
		}
		if (_direccion == "izq") {
			if (_direccionActual != "der") {
				this._direccionAbajo = false;
				this._direccionArriba = false;
				this._direccionDerecha = false;
				this._direccionIzquierda = true;
			}
		}
	}

	/**
	 * Metodo que genera el movimiento de la moto
	 */
	public void autoMoverse() {
		_mapa2 = Mapa.getMapa();
		int temp = 6;
		while (temp != 0) {
			// Si la moto esta destruida se corta el ciclo
			if (this._destruida == true) {
				break;
			}
			// En caso de que ingrese una direccion se envia los datos a mover
			// moto y se actualiza el bajar combustible
			else if (this._direccionAbajo == true) {
				this.moverMoto("aba");
				this.autoBajarCombustible();
			} else if (this._direccionArriba == true) {
				this.moverMoto("arr");
				this.autoBajarCombustible();
			} else if (this._direccionDerecha == true) {
				this.moverMoto("der");
				this.autoBajarCombustible();
			} else if (this._direccionIzquierda == true) {
				this.moverMoto("izq");
				this.autoBajarCombustible();
			}					
			//_mapa2.imprimirMapa();
			System.out.print("\n\n\n\n\n");
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			
			}
			temp--;
		}
	}
	
	/**
	 * Metodos get y set de la clase moto y sus atributos
	 */
	public int getVelocidad() {
		return _velocidad;
	}
	public Lista getEstela() {
		return _estela;
	}
	public int getCombustible() {
		return _combustible;
	}
	public Lista getPosX() {
		return _posX;
	}
	public Lista getPosY() {
		return _posY;
	}
	public Lista getCordenadas() {
		return _cordenadas;
	}
	public int getCantEstelaAumentar() {
		return _cantEstelaAumentar;
	}
	public boolean isDireccionDerecha() {
		return _direccionDerecha;
	}
	public boolean isDireccionIzquierda() {
		return _direccionIzquierda;
	}
	public boolean isDireccionArriba() {
		return _direccionArriba;
	}
	public boolean isDireccionAbajo() {
		return _direccionAbajo;
	}
	public boolean isDestruida() {
		return _destruida;
	}
	public int getContadorCombustible() {
		return _contadorCombustible;
	}
	public int get_jugador() {
		return _jugador;
	}
	public void set_jugador(int _jugador) {
		this._jugador = _jugador;
	}
	public String get_nickEstela() {
		return _nickEstela;
	}
	public void set_nickEstela(String _nickEstela) {
		this._nickEstela = _nickEstela;
	}
	public String get_nickMoto() {
		return _nickMoto;
	}
	public void set_nickMoto(String _nickMoto) {
		this._nickMoto = _nickMoto;
	}
	public boolean is_destruida() {
		return _destruida;
	}
	public String get_direccionActual() {
		return _direccionActual;
	}
	public void set_direccionActual(String _direccionActual) {
		this._direccionActual = _direccionActual;
	}
	public void set_destruida(boolean _destruida) {
		this._destruida = _destruida;
	}
	public void apilar_Poder(String _poder) {
		this._pilaPoderes.apilar(_poder);
	}
	public Pila get_pilaPoderes() {
		return _pilaPoderes;
	}
	public void set_pilaPoderes(Pila _pilaPoderes) {
		this._pilaPoderes = _pilaPoderes;
	}
	
	/**
	 * Metodo para encolar items en la cola de items
	 * @param _item
	 * 			El parametro _item es el el item a encolar en la cola
	 */
	public void encolar_Items(String _item){
		this._colaItems.encolar(_item);
	}
	
	/**
	 * Metodo que aplica el item y lo desencola
	 * @return El item que se desencola de la cola de items
	 */
	public String aplicar_Items(){
		 return this._colaItems.desencolar();
	}
	
	/**
	 * Metodo para que el usuario escoja el poder a utilizar
	 * @param _poder
	 * 			El parametro _poder es el poder que se escoge
	 */
	public void escoger_Poder(String _poder){
		if (_poder != _pilaPoderes.top()){
			this._pilaPoderes.intercambiar();
			this._pilaPoderes.desapilar();
		} else {
			this._pilaPoderes.desapilar();
		}
	}
	
	/**
	 * Metodo que ejecuta el poder del escudo cuando se escoge
	 */
	public void aplicar_Escudo(){
	  this._escudo = true;
	  tiempoEscudo();
	  
	}
	
	/**
	 * Metodo que ejecuta el poder de hipervelocidad cuando se escoge
	 */
	public void aplicar_Velocidad(){
		this._hvelocidad = true;
		tiempoVelocidad();
	}
	
	/**
	 * Metodo que verifica el tiempo de ejecucion del poder escudo
	 */
	public void tiempoEscudo(){
		new Thread(new Runnable(){
			public void run(){
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
				} finally {
					_escudo = false;
				}
			}
		}).start();
	}
	
	/**
	 * Metodo que verifica el tiempo de ejecucion del poder 
	 */
	public void tiempoVelocidad(){
		new Thread(new Runnable(){
			public void run(){
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
				} finally {
					_hvelocidad = false;
				}
			}
		}).start();
	}
}