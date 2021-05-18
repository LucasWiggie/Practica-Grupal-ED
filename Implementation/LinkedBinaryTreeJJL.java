/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interfaces.Interface_BinaryTreeJJL;
import java.util.NoSuchElementException;

/**
 *
 * @author lucas
 */
public class LinkedBinaryTreeJJL<E> implements Interface_BinaryTreeJJL<E>{
    
    // ATRIBUTOS
    private NodeBinaryTreeJJL raiz;
    
    // CONSTRUCTOR
    //Crear un árbol vacío
    public LinkedBinaryTreeJJL(){
        raiz = null;
    }
    
    //Crear un árbol a partir de una elemento (que sera su raiz) y su hijIzq y hijDer
    public LinkedBinaryTreeJJL(E elem, LinkedBinaryTreeJJL hijIzq, LinkedBinaryTreeJJL hijDer){
        this.raiz = new NodeBinaryTreeJJL(elem);
        this.raiz.setHijoDer(hijDer);
        ((raiz.getHijoDer()).raiz).setPadre(this);
        this.raiz.setHijoIzq(hijIzq);
        ((raiz.getHijoIzq()).raiz).setPadre(this);

    }
    
    //Crear un árbol con un unico nodo raiz y sin hijos
    public LinkedBinaryTreeJJL(E elem){
        raiz = new NodeBinaryTreeJJL(elem);
    }

    // MÉTODOS
    @Override
    public boolean isEmpty() {
        return (raiz == null);
    }

    @Override
    public E getRaizInfo() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException("Error en getRaizInfo() "
                    + "LinkedBinaryTreeJJL: "
                    + "ÁRBOL VACÍO");
        } else {
            return (E) raiz.getInfo();
        }
    }
    
    @Override
    public NodeBinaryTreeJJL getRaiz() {
        if (isEmpty()){
            throw new NoSuchElementException("Error en getRaiz() "
                    + "LinkedBinaryTreeJJL: "
                    + "ÁRBOL VACÍO");
        } else {
            return raiz;
        }
    }

    @Override
    public E setRaiz(E elem) throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("Error en setRaiz() "
                    + "LinkedBinaryTreeJJL: "
                    + "ÁRBOL VACÍO");
        } else  {
            E aux = (E) raiz.getInfo();
            raiz.setInfo(elem);
            return aux;
        }
    }

    @Override
    public LinkedBinaryTreeJJL getHijoIzq() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Error en getHijoIzq() "
                    + "LinkedBinaryTreeJJL: "
                    + "ÁRBOL VACÍO");
        } else {
            return raiz.getHijoIzq();
        }
    }

    @Override
    public LinkedBinaryTreeJJL getHijoDer() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("ÁRBOL VACÍO");
        } else {
            return raiz.getHijoDer();
        }
    }

    @Override
    public void insertarHijoIzq(E elem) throws UnsupportedOperationException {
        if(isEmpty()){
            throw new UnsupportedOperationException("Error en insertarHijoIzq() "
                    + "LinkedBinaryTreeJJL: "
                    + "ÁRBOL VACÍO");
        } else if(raiz.getHijoIzq().isEmpty() == false){
            throw new UnsupportedOperationException("Error en insertarHijoIzq() "
                    + "LinkedBinaryTreeJJL: "
                    + "EL ÁRBOL YA TIENE HIJO IZQUIERDO");
        } else {
            LinkedBinaryTreeJJL newTree = new LinkedBinaryTreeJJL(elem);
            raiz.setHijoIzq(newTree);
            (newTree.raiz).setPadre(this);
        }
    }
    
    @Override
    public void insertarHijoDer(E elem) throws UnsupportedOperationException {
        if(isEmpty()){
            throw new UnsupportedOperationException("Error en insertarHijoDer() "
                    + "LinkedBinaryTreeJJL: "
                    + "ÁRBOL VACÍO");
        } else if(raiz.getHijoDer().isEmpty() == false){
            throw new UnsupportedOperationException("Error en insertarHijoDer() "
                    + "LinkedBinaryTreeJJL: "
                    + "EL ÁRBOL YA TIENE HIJO DERECHO");
        } else {
            LinkedBinaryTreeJJL newTree = new LinkedBinaryTreeJJL(elem);
            raiz.setHijoDer(newTree);
            (newTree.raiz).setPadre(this);
        }
    }

    @Override
    public boolean pertenece(E elem) {
        return perteneceAux(this, elem);
    }

    @Override
    public LinkedBinaryTreeJJL buscarElemento(E elem) {
        return buscarElemAux(this, elem);
    }

    @Override
    public E borrarRaiz() throws UnsupportedOperationException {
        // Si la raiz tiene hijos no nulos o es null, lanza excepcion
        if (raiz == null || isEmpty()) {
            throw new UnsupportedOperationException("Error en borrarRaiz() "
                    + "LinkedBinaryTreeJJL: "
                    + "NO EXISTE LA RAIZ");
        } else if (raiz.getHijoIzq().isEmpty() == false || raiz.getHijoDer().isEmpty() == false) {
            throw new UnsupportedOperationException("Error en borrarRaiz()"
                    + " LinkedBinaryTreeJJL: "
                    + "LA RAIZ TIENE HIJOS");
        } else {
            E aux = (E) raiz.getInfo();
            raiz = null;
            return aux;
        }
    }

    @Override
    public boolean esHoja() {
        if(raiz.getHijoIzq().isEmpty() && raiz.getHijoDer().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String inOrderString() {
        return(inOrderStringAux(this));
    }

    @Override
    public String preOrderString() {
        return(preOrderStringAux(this));
    }

    @Override
    public String postOrderString() {
        return(postOrderStringAux(this));
    }
    
    //MÉTODOS PRIVADOS AUXILIARES
    private boolean perteneceAux (LinkedBinaryTreeJJL a, E e){
        if(a.isEmpty()){
            return false;
        } else if ((a.raiz.getInfo()).equals(e)){ // PARA UN ÁRBOL DE UN OBJETO IMPLEMENTADO POR NOSOTROS, NECESITAREMOS HACER EL MÉTODO EQUALS()
            return true;
        } else {
            return perteneceAux(a.getHijoIzq(), e) || perteneceAux(a.getHijoDer(), e);
        }
    }

    private LinkedBinaryTreeJJL buscarElemAux(LinkedBinaryTreeJJL a, E e) {
        if (a.isEmpty()) {
            return (new LinkedBinaryTreeJJL());
        } else if (!a.pertenece(e)) {
            return (new LinkedBinaryTreeJJL());
        } else {
            if((a.raiz).equals(e)){ //si es la raiz
                return a;
            } else { 
                if((a.getHijoIzq()).pertenece(e)){ //si esta en el hijo izquierdo, entramos a buscarlo
                    return buscarElemAux(a.getHijoIzq(), e);
                } else { //si no está en el hijo izq, tiene q estar en el derecho
                    return buscarElemAux(a.getHijoDer(), e);
                }
            }
        }
    }

    private String inOrderStringAux(LinkedBinaryTreeJJL arbol) {
        String cadena = "";
        if (!isEmpty()) {
            if (!arbol.getHijoIzq().isEmpty()) {
                cadena += inOrderStringAux(arbol.getHijoIzq());
            }
            cadena = cadena + arbol.getRaiz();
            if (!arbol.getHijoDer().isEmpty()) {
                cadena += inOrderStringAux(arbol.getHijoDer());
            }
        }
        return (cadena);
    }

    private String preOrderStringAux(LinkedBinaryTreeJJL arbol) {
        String cadena = "";
        if (!isEmpty()) {
            cadena += arbol.raiz.getInfo();
            if (!arbol.getHijoIzq().isEmpty()) {
                cadena += preOrderStringAux(arbol.getHijoIzq());
            }
            if (!arbol.getHijoDer().isEmpty()) {
                cadena += inOrderStringAux(arbol.getHijoDer());
            }
        }
        return (cadena);
    }
    
    private String postOrderStringAux(LinkedBinaryTreeJJL arbol){
        String cadena = "";
        if(!isEmpty()){
            if((arbol.getHijoIzq()).isEmpty() == false){
                cadena = cadena + postOrderStringAux(arbol.getHijoIzq());
            }
            if((arbol.getHijoDer()).isEmpty() == false){
                cadena = cadena + postOrderStringAux(arbol.getHijoDer());
            }
            cadena = cadena + arbol.getRaiz();
        }
        return (cadena);
    }

    

}
