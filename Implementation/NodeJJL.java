/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

/**
 *
 * @author lucas
 */
public class NodeJJL<E> {
    
    // ATRIBUTOS
    E info;
    NodeJJL next;
    
    // CONSTRUCTOR
    public NodeJJL(E elem){
        this.info = elem;
        this.next = null;
    }
    
    // GETTERS AND SETTERS
    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public NodeJJL getNext() {
        return next;
    }

    public void setNext(NodeJJL next) {
        this.next = next;
    }
    
}
