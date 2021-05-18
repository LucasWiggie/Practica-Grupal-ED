/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.security.InvalidKeyException;

/**
 *
 * @author lucas
 */
public interface Interface_HashMap<K, V> {
    
    public int size();
    
    public boolean isEmpty();
    
    public V put(K key, V value) throws InvalidKeyException;
    
    public V get(K key) throws InvalidKeyException;
    
    public V remove(K key) throws InvalidKeyException;
}
