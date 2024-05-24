package domain;

public class QuoriPOOBException extends Exception{
	public static final String TAMANO_MINIMO_TABLERO = "Tamaño tablero invalido, los menores a 3x3 son invalidos";
	public static final String NUMERO_CASILLAS = "El numero de casillas especiales es mayor al numero de casillas del tablero ";
	public static final String NUMERO_PAREDES = "El numero de paredes es superior a la regla de tamaño tablero + 1";
	public static final String TAMANO_PAR_TABLERO = "El tamaño del tablero debe ser impar";

	public QuoriPOOBException(String message) {
		super(message);
	}
}
