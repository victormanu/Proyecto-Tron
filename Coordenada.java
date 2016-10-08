package tron;

/**
 * Esta clase define la coordenada de cada celda del mapa
 * @author Fabian,Adonnis,Erick,Victor
 */
public class Coordenada {
	int X;
	int Y;

	/**
	 * Constructor de la la clase coordenada que represenata las posiciones de
	 * la moto en el mapa
	 * @param X
	 * @param Y
	 */
	public Coordenada(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * Metodo que devuelve la coordenada X
	 * @return La coordenda X
	 */
	public int getX() {
		return X;
	}

	/**
	 * Metodo que devuelve la coordenda Y
	 * @return La coordenada Y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Metodo que asigna la coordenada X
	 * @param X
	 */
	public void setX(int X) {
		this.X = X;
	}

	/**
	 * Metodo que asigna la coordenda Y
	 * @param Y
	 */
	public void setY(int Y) {
		this.Y = Y;
	}
}