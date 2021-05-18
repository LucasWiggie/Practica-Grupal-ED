/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interfaces.Interface_QueueJJL;
import java.util.NoSuchElementException;

/**
 *
 * @author lucas
 */
public class LinkedQueueJJL<E> implements Interface_QueueJJL {

    // ATRIBUTOS
    private NodeJJL first;
    private NodeJJL last;
    private int size; // numero de elementos en la cola
    
    // CONSTRUCTOR
    public LinkedQueueJJL() {
        first = null;
        last = null;
        size = 0;
    }
    
    @Override
    public void add(Object elem) {
        NodeJJL nodeNew = new NodeJJL(elem);
        if (isEmpty()) {
            first = nodeNew;
            last = nodeNew;
        } else {
            last.next = nodeNew;
            last = nodeNew;
        }
        size++;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error en remove() LinkedQueueJJL "
                    + " COLA VACÍA");
        } else {
            E aux = (E) first.info;
            first = first.next;

            if (first == null) { // si al lista solo tenía un elemento, necesitamos actualizar el last
                last = null;
            }
            size--;
            return aux;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error en front() LinkedQueueJJL "
                    + " COLA VACÍA");
        } else {
            return((E) first.info);
        }
    }
    
}
