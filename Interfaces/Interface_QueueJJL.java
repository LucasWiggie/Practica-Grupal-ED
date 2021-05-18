/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author lucas
 */
public interface Interface_QueueJJL<E> {
    
    //Añadir elemento
    public void add(E elem);
    
    //Sacar el primero
    public E remove();
    
    //Número de elementos en la cola
    public int size();
    
    //Si está vacía
    public boolean isEmpty();
    
    //Obtener el primero de la cola
    public E front();
}
