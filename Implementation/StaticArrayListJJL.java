/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interfaces.Interface_ListJJL;

/**
 *
 * @author lucas
 */
public class StaticArrayListJJL<E> implements Interface_ListJJL<E> {

    // ATRIBUTOS
    private E[] list;
    private final int SIZE = 8;
    private int pos;

    // CONSTRUCTORES
    @SuppressWarnings("unchecked")
    public StaticArrayListJJL() {
        this.list = (E[]) new Object[SIZE];
        this.pos = -1;
    }
    
    // MÉTODOS AUXILIARES
    public boolean isFull() {
        return (size() == 8);
    }

    // MÉTODOS PÚBLICOS
    @Override
    public int size() {
        return pos + 1;
    }

    @Override
    public boolean isEmpty() {
        return (pos == -1);
    }
    
    @Override
    public E get(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (index < 0 || index > pos) {
            throw new IndexOutOfBoundsException("Error en get() de StaticArrayListJJL "
                    + " INDICE INTRODUCIDO NO VÁLIDO");
        } else if (isEmpty()){
            throw new IllegalStateException("Error en get() StaticArrayListJJL "
                    + " ARRAY VACIO");
        } else {
            return list[index];
        }
    }

    @Override
    public E set(int index, E elem) throws IndexOutOfBoundsException {
        if (index < 0 || index > pos + 1 || (isFull() && index > pos)) {
            throw new IndexOutOfBoundsException("Error en set() StaticArrayListJJL "
                    + " INDICE INTRODUCIDO NO VÁLIDO");
        } else {
            if (index == pos + 1) {
                pos++;
                list[pos] = elem;
                return null;
            } else {
                E aux = list[pos];
                list[pos] = elem;
                return aux;
            }
        }
    }

    @Override
    public void add(int index, E elem) throws IndexOutOfBoundsException, IllegalStateException {
        if (index < 0 || index > pos + 1) {
            throw new IndexOutOfBoundsException("Error en add() StaticArrayListJJL "
                    + " INDICE INTRODUCIDO NO VÁLIDO");
        } else if (isFull()){
            throw new IllegalStateException("Error en add() StaticArrayListJJL "
                    + " ARRAY COMPLETO");
        } else {
            if (index == pos) {
                E aux = list[pos];
                list[pos] = elem;
                pos++;
                list[pos] = aux;
            } else if (index < pos) {
                pos++;
                for (int i = pos; i > index; i--) {
                    list[i] = list[i - 1];
                }
                list[index] = elem;
            } else if (index > pos) {
                pos++;
                list[pos] = elem;
            }
        }
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (index < 0 || index > pos) {
            throw new IndexOutOfBoundsException("Error en remove() StaticArrayListJJL "
                    + " INDICE INTRODUCIDO NO VÁLIDO");
        } else if (isEmpty()){
            throw new IllegalStateException("Error en remove() StaticArrayListJJL "
                    + " ARRAY VACIO");
        } else {
            if (index == pos) {
                E aux = list[index];
                list[index] = null;
                pos--;
                return aux;
            } else {
                E aux = list[index];
                for (int i = index; i < pos; i++) {
                    list[i] = list[i + 1];
                }
                list[pos] = null;
                pos--;
                return aux;
            }
        }
    }
}
