package cola;

/**
 *
 * @author futfl
 */
public interface ColaInterface {

    boolean estaVacia();

    void encolar(Object elemento);

    Object desencolar();

    Object frente();
}
