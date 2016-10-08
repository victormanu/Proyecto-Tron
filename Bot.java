package tron;

//Importa la libreria random de Java
import java.util.Random;

/**
 * Esta clase define el bot y su movimiento
 * @author Fabian,Adonnis,Erick,Victor
 */
public class Bot extends Moto {
	private int _X;
	private int _Y;
	// private Mapa _singletonMapa;
	private static Random _random = new Random();
	private String _direccion;
	
	private Mapa _singletonMapa;

	/**
	 * Constructor de el bot dependiendo de la posicion donde se ubique el bot
	 * le da una direccion actual
	 * 
	 * @param _jugador
	 */
	public Bot(int _jugador) {
		super(_jugador);
	}

	/**
	 * Metodo que mueve la moto por el mapa sin que se choque en los bordes
	 */
	public void run() {
		_singletonMapa = Mapa.getMapa();
		while (true) {
			// Si la bandera destruida esta activa la moto se destruye
			if (this._destruida == true) {
				break;
			}
			// Se obtienen las posiciones de la moto para verificar sus posibles
			// movimientos
			_X = this._posX.ObtenerNodo(0);
			_Y = this._posY.ObtenerNodo(0);
			// A partir de la direccion actual que tenga la moto se le va a dar
			// un ranogo de posibilidades a las cuales esta puede moverse
			if (this. get_direccionActual() == "aba") {
				if (_Y == 0 && _X == 29) {
					this.set_direccionActual("der");
					this.moverMoto("der");
				} else if (_Y == 29 && _X == 29) {
					this.set_direccionActual("izq");
					this.moverMoto("izq");
				} else if (_Y == 29) {
					String[] direcciones = { "aba", "izq" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_Y == 0) {
					String direcciones[] = { "aba", "der" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_X == 29) {
					String direcciones[] = { "der" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);

				} else {
					String direcciones[] = { "der", "izq", "aba" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				}
			} else if (this. get_direccionActual() == "der") {
				if (_Y == 29 && _X == 29) {
					this.set_direccionActual("arr");
					this.moverMoto("arr");
				} else if (_Y == 29 && _X == 0) {
					this.set_direccionActual("aba");
					this.moverMoto("aba");
				} else if (_X == 29) {
					String direcciones[] = { "der", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_X == 0) {
					String direcciones[] = { "der", "aba" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_Y == 29) {
					String direcciones[] = { "arr", "aba" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else {
					String direcciones[] = { "der", "aba", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				}
			} else if (this. get_direccionActual() == "arr") {
				if (_Y == 29 && _X == 0) {
					this.set_direccionActual("izq");
					this.moverMoto("izq");
				} else if (_Y == 0 && _X == 0) {
					this.set_direccionActual("der");
					this.moverMoto("der");
				} else if (_X == 0) {
					String direcciones[] = { "der", "izq" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_Y == 0) {
					String direcciones[] = { "der", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_Y == 29) {
					String direcciones[] = { "izq", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else {
					String direcciones[] = { "der", "izq", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				}
			} else if (this. get_direccionActual() == "izq") {
				if (_Y == 0 && _X == 29) {
					this.set_direccionActual("arr");
					this.moverMoto("arr");
				} else if (_Y == 0 && _X == 0) {
					this.set_direccionActual("aba");
					this.moverMoto("aba");
				} else if (_X == 0) {
					String direcciones[] = { "aba", "izq" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_Y == 29) {
					String direcciones[] = { "aba", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else if (_Y == 0) {
					String direcciones[] = { "aba", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				} else {
					String direcciones[] = { "izq", "izq", "arr" };
					_direccion = direcciones[_random.nextInt(direcciones.length)];
					this.set_direccionActual(_direccion);
					this.moverMoto(_direccion);
				}
			}
			// Se diminuye el combustible
			this.autoBajarCombustible();
			//_singletonMapa.imprimirMapa();
			//System.out.print("\n\n\n\n\n");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
			}
		}
	}
}