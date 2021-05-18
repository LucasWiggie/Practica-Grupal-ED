/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Implementation.LinkedQueueJJL;

/**
 *
 * @author lucas
 */
public class Combo {
    
    // ATRIBUTOS
    private String name;
    private Integer id;
    private LinkedQueueJJL<GamePad> movements;
    
    //CONSTRUCTOR
    public Combo(String name, Integer id, LinkedQueueJJL<GamePad> movements) {
        this.name = name;
        this.id = id;
        this.movements = movements;
    }
    
    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkedQueueJJL<GamePad> getMovements() {
        return movements;
    }

    public void setMovements(LinkedQueueJJL<GamePad> movements) {
        this.movements = movements;
    }
    
    
}
