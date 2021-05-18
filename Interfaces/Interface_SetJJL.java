/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Implementation.LinkedSetJJL;
import java.util.NoSuchElementException;

/**
 *
 * @author lucas
 */

public interface Interface_SetJJL<E> {
    
    public boolean isEmpty();
    
    public boolean contains(E elem);
    
    public int size();
    
    //Insertar
    public boolean insert(E e);
    
    //Extraer
    public E extract(E e);
    
    //Extraer uno cualquiera
    public E choose() throws NoSuchElementException;
    
    //Unión (devuelve el conjunto unión)
    public LinkedSetJJL<E> union(LinkedSetJJL<E> s);
    
    //Intersección (devuelve el conjunto intersección)
    public LinkedSetJJL<E> intersect(LinkedSetJJL<E> s);
    
}
