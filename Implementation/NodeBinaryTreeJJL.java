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
public class NodeBinaryTreeJJL<E> {

    // ATRIBUTOS
    private E info;
    private LinkedBinaryTreeJJL padre;
    private LinkedBinaryTreeJJL hijoIzq;
    private LinkedBinaryTreeJJL hijoDer;
    
    // CONSTRUCTOR
    public NodeBinaryTreeJJL(E elem){
        info = elem;
        padre = new LinkedBinaryTreeJJL();
        hijoIzq = new LinkedBinaryTreeJJL();
        hijoDer = new LinkedBinaryTreeJJL();
    }
    
    // GETTERS & SETTERS
    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedBinaryTreeJJL getPadre() {
        return padre;
    }

    public void setPadre(LinkedBinaryTreeJJL padre) {
        this.padre = padre;
    }

    public LinkedBinaryTreeJJL getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(LinkedBinaryTreeJJL hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public LinkedBinaryTreeJJL getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(LinkedBinaryTreeJJL hijoDer) {
        this.hijoDer = hijoDer;
    }
    
}
