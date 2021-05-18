/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interfaces.Interface_SetJJL;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class LinkedSetJJL<E> implements Interface_SetJJL<E>{

    //ATRIBUTOS
    private NodeJJL head;
    private Integer size = 0; //numero de elementos en el conjunto

    //CONSTRUCTOR 
    public LinkedSetJJL() {
        head = null;
    }

    //MÉTODOS
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public boolean contains(E elem) {
        NodeJJL nodePointer = head;
        boolean contains = false;
        if(isEmpty()){
            return false;
        } else {
            while(contains == false && nodePointer != null){
                E aux = (E) nodePointer.info;
                if(aux.equals(elem)){
                    contains = true;                    
                }
                nodePointer = nodePointer.next;
            }
            return contains;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public boolean equalSet(LinkedSetJJL<E> s) {
        if (isEmpty()) {
            if (s.isEmpty()) {
                System.out.println("Ambos conjuntos son vacíos");
                return true;
            } else {
                System.out.println("Uno de los conjuntos es vacío, el otro no");
                return false; 
            }
        } else {
            NodeJJL nodePointer = head;
            boolean equal = true;

            while (equal == true && nodePointer != null) {
                E aux = (E) nodePointer.info;

                if ((s.contains(aux))) { //si s contiene el elemento aux
                    nodePointer = nodePointer.next;
                } else {
                    equal = false;
                }
            }
            return equal;
        }
    }

    @Override
    public boolean insert(E e) {
        if (contains(e)) { //si el conjunto ya contiene e, no lo añadimos puesto que no puede haber elementos repetidos
            return false;
        } else {
            if (isEmpty()) { //si está vacío
                NodeJJL nodeNew = new NodeJJL(e);
                head = nodeNew;
                size++;
                return true;
            } else { //no importa el orden, así que lo colocamos al principio para no tener que recorrer la lista
                NodeJJL nodeNew = new NodeJJL(e);
                nodeNew.next = head;
                head = nodeNew;
                size++;
                return true;
            }
        }
    }

    @Override
    public E extract(E e) {
        if (contains(e)) {
            NodeJJL nodePointer = head;
            NodeJJL nodePrevious = null;

            while (nodePointer.info.equals(e) == false) {
                nodePrevious = nodePointer;
                nodePointer = nodePointer.next;
            }
            E aux = (E) nodePointer.info;
            nodePrevious.next = nodePointer.next;
            size--;
            return aux;
        } else {
            return null;
        }
    }

    @Override
    public E choose() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Error en choose() de "
                    + "StaticArrayListJJL "
                    + " CONJUNTO VACÍO");
        } else {
            Random r = new Random();
            int i = Math.abs(r.nextInt() % size);
            NodeJJL nodePointer = head;
            NodeJJL nodePrevious = null;
            
            for (int j = 0; j < i; j++) {
                nodePrevious = nodePointer;
                nodePointer = nodePointer.next;
            }
            if (nodePointer.next == null) {
                if (nodePrevious == null) { // solo queda uno
                    head = null;
                } else {
                    nodePrevious.next = null;
                }
            } else if (nodePrevious == null) {
                head = nodePointer.next;
            } else {
                nodePrevious.next = nodePointer.next;
            }
            size--;
            return (E) nodePointer.info;
        }
    }

    @Override
    public LinkedSetJJL<E> union(LinkedSetJJL<E> s) {
        LinkedSetJJL<E> newSet = new LinkedSetJJL<>();

        newSet = s;

        if (isEmpty()) {
            return newSet;
        } else {
            NodeJJL nodePointer = head;

            while (nodePointer != null) {
                E aux = (E) nodePointer.info;
                if (newSet.contains(aux) == false) { //si no está ya en el nuevo conjunto
                    newSet.insert(aux);
                    nodePointer = nodePointer.next;
                } else { //si ya está en el nuevo conjunto
                    nodePointer = nodePointer.next;
                }
            }

            return newSet;
        }
    }

    @Override
    public LinkedSetJJL<E> intersect(LinkedSetJJL<E> s) {
        LinkedSetJJL<E> newSet = new LinkedSetJJL<>();

        NodeJJL nodePointer = head;

        while (nodePointer != null) {
            E aux = (E) nodePointer.info;
            if (s.contains(aux)) {
                newSet.insert(aux);
            }
            nodePointer = nodePointer.next;
        }

        return newSet;
    }

}
