package ec.com.ecotrackapp.tda;

import java.io.Serializable;
import java.util.EmptyStackException;

/**
 * Implementación propia de una pila (LIFO) para el centro de reciclaje
 */
public class PilaReciclaje<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private class Nodo implements Serializable {
        private static final long serialVersionUID = 1L;
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo tope;
    private int tamanio;

    public PilaReciclaje() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void apilar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamanio++;
    }

    public T desapilar() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    public T verTope() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void limpiar() {
        tope = null;
        tamanio = 0;
    }

    public boolean contiene(T dato) {
        Nodo actual = tope;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return "Pila vacía";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Tope -> ");
        Nodo actual = tope;
        while (actual != null) {
            sb.append("[").append(actual.dato).append("]");
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }
        return sb.toString();
    }
}

