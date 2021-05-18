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
public class LinkedListJJL<E> implements Interface_ListJJL<E> {

    // ATRIBUTOS
    private NodeJJL head;
    private int cont; // numero de elementos en la lista
    
    // CONSTRUCTOR
    public LinkedListJJL (){
        this.head = null;
        this.cont = 0;
    }
    
    // GETTERS AND SETTERS
    public NodeJJL getHead() {
        return head;
    }

    public void setHead(NodeJJL head) {
        this.head = head;
    }

    public int getCont() {
        return cont;
    }
  
    // MÉTODOS  
    @Override
    public int size() {
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }
    
    @Override
    public E get(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Error en get() LinkedListJJL "
                    + " LISTA VACIA");
        } else if (index < 0 || index >= cont) {
            throw new IndexOutOfBoundsException("Error en get() LinkedListJJL "
                    + " INDICE NO VALIDO");
        } else {
            NodeJJL nodePointer = head;
            for (int i = 0; i < index; i++) {
                nodePointer = nodePointer.getNext();
            }
            return (E) nodePointer.getInfo();
        }
    }

    @Override
    public E set(int index, E elem) throws IndexOutOfBoundsException {
        NodeJJL nodeNew = new NodeJJL (elem);
        if (isEmpty()) {
            head = nodeNew;
            cont++;
            return null;
        } else if (index < 0 || index > cont) {
            throw new IndexOutOfBoundsException("Error en set() LinkedListJJL "
                    + " INDICE NO VALIDO");
        } else {
            if (index == 0) { // si queremos cambiar head
                E aux = (E) head.getInfo();
                nodeNew.next = head.next;
                head = nodeNew;
                return aux;
            } else if (index == cont) {
                NodeJJL nodePointer = head;
                for (int i = 0; i < index-1; i++) {
                    nodePointer = nodePointer.getNext();
                }
                E aux = (E) nodePointer.getInfo();
                nodePointer.next = nodeNew;
                return aux;
            } else {
                NodeJJL nodePointer = head;
                NodeJJL nodePrevious = null;
                for (int i = 0; i < index; i++) {
                    nodePrevious = nodePointer;
                    nodePointer = nodePointer.getNext();
                }
                E aux = (E) nodePointer.getInfo();
                nodePrevious.next = nodeNew;
                nodeNew.next = nodePointer.next;
                nodePointer.next = null;
                return aux;
            }
        }
    }

    @Override
    public void add(int index, E elem) throws IndexOutOfBoundsException {
        NodeJJL nodeNew = new NodeJJL(elem);
        if (isEmpty()){
            head = nodeNew;
            cont++;
        } else if( index < 0 || index > cont){
            throw new IndexOutOfBoundsException("Error en add() LinkedListJJL "
                    + " INDICE NO VALIDO");
        } else {
            if(index == 0){
                nodeNew.next = head;
                head = nodeNew;
                cont++;
            } else if (index == cont){
                NodeJJL nodePointer = head;
                while (nodePointer.next != null){
                    nodePointer = nodePointer.next;
                }
                nodePointer.next = nodeNew;
                cont++;
            } else {
                NodeJJL nodePointer = head;
                NodeJJL nodePrevious = null;
                for (int i = 0; i < index; i++) {
                    nodePrevious = nodePointer;
                    nodePointer = nodePointer.next;
                }
                nodePrevious.next = nodeNew;
                nodeNew.next = nodePointer;
                cont++;
            }
        }
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IllegalStateException("Error en remove() LinkedListJJL "
                    + " LISTA VACIA");
        } else if (index < 0 || index >= cont) {
            throw new IndexOutOfBoundsException("Error en remove() LinkedListJJL "
                    + " INDICE NO VALIDO");
        } else {
            if (index == 0) {
                E aux = (E) head.getInfo();
                head = head.next;
                cont--;
                return aux;
            } else if (index == cont - 1) {
                NodeJJL nodePointer = head;
                NodeJJL nodePrevious = null;
                for (int i = 0; i < index; i++) {
                    nodePrevious = nodePointer;
                    nodePointer = nodePointer.next;
                }
                E aux = (E) nodePointer.getInfo();
                nodePrevious.next = null;
                cont--;
                return aux;
            } else {
                NodeJJL nodePointer = head;
                NodeJJL nodePrevious = null;

                for (int i = 0; i < index; i++) {
                    nodePrevious = nodePointer;
                    nodePointer = nodePointer.next;
                }
                E aux = (E) nodePointer.getInfo();
                nodePrevious.next = nodePointer.next;
                nodePointer.next = null;
                cont--;
                return aux;
            }
        }
    }
    
    /*
    public Iterator_LinkedList createIterator() {
        return new Iterator_LinkedList(head);
    }
    */
    
}
