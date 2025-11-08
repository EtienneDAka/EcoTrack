package ec.com.ecotrackapp.tda;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación propia de una lista circular doblemente enlazada
 */
public class ListaCircularDoble<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private class Nodo implements Serializable {
        private static final long serialVersionUID = 1L;
        T dato;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = this;
            this.anterior = this;
        }
    }

    private Nodo cabeza;
    private int tamanio;

    public ListaCircularDoble() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void agregar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo ultimo = cabeza.anterior;
            ultimo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimo;
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
        }
        tamanio++;
    }

    public void agregarAlInicio(T dato) {
        agregar(dato);
        if (cabeza != null) {
            cabeza = cabeza.anterior;
        }
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;

        Nodo actual = cabeza;
        do {
            if (actual.dato.equals(dato)) {
                if (tamanio == 1) {
                    cabeza = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    if (actual == cabeza) {
                        cabeza = actual.siguiente;
                    }
                }
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        return false;
    }

    public T buscar(T dato) {
        if (cabeza == null) return null;

        Nodo actual = cabeza;
        do {
            if (actual.dato.equals(dato)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        return null;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorAdelante();
    }

    public Iterator<T> iteradorReversa() {
        return new IteradorAtras();
    }

    private class IteradorAdelante implements Iterator<T> {
        private Nodo actual;
        private int contador;

        IteradorAdelante() {
            this.actual = cabeza;
            this.contador = 0;
        }

        @Override
        public boolean hasNext() {
            return contador < tamanio;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T dato = actual.dato;
            actual = actual.siguiente;
            contador++;
            return dato;
        }
    }

    private class IteradorAtras implements Iterator<T> {
        private Nodo actual;
        private int contador;

        IteradorAtras() {
            this.actual = (cabeza != null) ? cabeza.anterior : null;
            this.contador = 0;
        }

        @Override
        public boolean hasNext() {
            return contador < tamanio;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T dato = actual.dato;
            actual = actual.anterior;
            contador++;
            return dato;
        }
    }

    public void limpiar() {
        cabeza = null;
        tamanio = 0;
    }
}
