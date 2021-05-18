/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author lucas
 */
public class Player {
    
    // ATRIBUTOS
    private String name;
    private String surname;
    private String nickname;
    private Fighter character;
    private Integer victories;
    private Integer defeats;

    // CONSTRUCTOR
    public Player(){
        
    }
    
    public Player(String name, String surname, String nickname, Fighter character, Integer victories, Integer defeats) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.character = character;
        this.victories = victories;
        this.defeats = defeats;
    }
    
    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Fighter getCharacter() {
        return character;
    }

    public void setCharacter(Fighter character) {
        this.character = character;
    }

    public Integer getVictories() {
        return victories;
    }

    public void setVictories(Integer victories) {
        this.victories = victories;
    }

    public Integer getDefeats() {
        return defeats;
    }

    public void setDefeats(Integer defeats) {
        this.defeats = defeats;
    }
    
       
}
