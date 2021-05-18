/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Implementation.LinkedListJJL;

/**
 *
 * @author lucas
 */
public class Fighter {
    
    // ATRIBUTOS
    private String name;
    private Integer id;
    private Integer life;
    private LinkedListJJL<Combo> comboList;
    
    // CONSTRUCTOR
    public Fighter(String name, Integer id, Integer life, LinkedListJJL<Combo> comboList) {
        this.name = name;
        this.id = id;
        this.life = life;
        this.comboList = comboList;
    }
    
    
    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public LinkedListJJL<Combo> getComboList() {
        return comboList;
    }

    public void setComboList(LinkedListJJL<Combo> comboList) {
        this.comboList = comboList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
