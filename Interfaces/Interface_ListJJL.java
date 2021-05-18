/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author lucas
 * @param <E>
 */
public interface Interface_ListJJL<E> {
    
    public int size();
    
    public boolean isEmpty();
    
    public E get(int index) throws IndexOutOfBoundsException, IllegalStateException;
    
    public E set(int index, E elem) throws IndexOutOfBoundsException;
    
    public void add(int index, E elem) throws IndexOutOfBoundsException, IllegalStateException;
    
    public E remove(int index) throws IndexOutOfBoundsException, IllegalStateException;
    
}
