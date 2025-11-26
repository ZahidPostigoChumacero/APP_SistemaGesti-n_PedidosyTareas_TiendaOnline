package pila;

/**
 *
 * @author futfl
 */
public interface PilaInterface {

    boolean estaVacia();

    void push(Object elemento);

    Object pop();

    Object cima();
} 
