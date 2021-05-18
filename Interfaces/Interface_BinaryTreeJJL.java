/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Implementation.LinkedBinaryTreeJJL;
import Implementation.NodeBinaryTreeJJL;
import java.util.NoSuchElementException;

/**
 *
 * @author lucas
 */
public interface Interface_BinaryTreeJJL<E> {
    
    public boolean isEmpty();
    
    public E getRaizInfo () throws NoSuchElementException;
    
    public NodeBinaryTreeJJL getRaiz () throws NoSuchElementException;
    
    public E setRaiz(E elem) throws NoSuchElementException;
    
    public LinkedBinaryTreeJJL getHijoIzq() throws NoSuchElementException;
    
    public LinkedBinaryTreeJJL getHijoDer() throws NoSuchElementException;
    
    public void insertarHijoIzq(E elem) throws UnsupportedOperationException;
    
    public void insertarHijoDer(E elem) throws UnsupportedOperationException;
    
    // Comprubea si el elemento está en el árbol
    public boolean pertenece(E elem);
    
    // Devuelve el subarbol cuya raiz es elem
    public LinkedBinaryTreeJJL buscarElemento(E elem);
    
    // Si la raiz tiene hijos no nulos o es null, lanza excepcion
    public E borrarRaiz() throws UnsupportedOperationException;
    
    public boolean esHoja();
    
    public String inOrderString();
    
    public String preOrderString();
    
    public String postOrderString();
}
