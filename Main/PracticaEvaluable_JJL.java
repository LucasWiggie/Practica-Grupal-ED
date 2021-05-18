/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaevaluable_jjl;

import Classes.Combo;
import Classes.Fighter;
import Classes.GamePad;
import Classes.Player;
import Implementation.LinkedBinaryTreeJJL;
import Implementation.LinkedListJJL;
import Implementation.LinkedQueueJJL;
import Implementation.LinkedSetJJL;
import Implementation.StaticArrayListJJL;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class PracticaEvaluable_JJL {
    
    // Método para crear la cola de botones que le corresponderá a cada combo
    public static LinkedQueueJJL createQueueCombo(GamePad button1, GamePad button2, GamePad button3, GamePad button4){
        LinkedQueueJJL<GamePad> queueCombo = new LinkedQueueJJL<>();
        queueCombo.add(button1);
        queueCombo.add(button2);
        queueCombo.add(button3);
        queueCombo.add(button4);
        
        return queueCombo;
    }
    
    // Método para crear la lista de combos que cada personaje tendrá disponible
    public static LinkedListJJL createFighterComboList(Combo comboOne, Combo comboTwo, Combo comboThree){
        LinkedListJJL<Combo> listFighter = new LinkedListJJL<>();
        listFighter.add(0, comboOne);
        listFighter.add(1, comboTwo);
        listFighter.add(2, comboThree);
        
        return listFighter;
    }
       
    // Método para crear un jugador, sin introducir todavía su personaje
    public static Player createPlayer(int numJugador){
        Scanner input = new Scanner(System.in);
        System.out.println("Introduzca los datos del Jugador " + numJugador + ": ");
        
        //Introducción de nombre y apellido
        System.out.println("Nombre: ");
        String name = input.nextLine();
        
        System.out.println("Apellido: ");
        String surname = input.nextLine();

        //Introducción de apodo del jugador
        System.out.println("Nickname: ");
        String nickname = input.nextLine();

        Player playerAux = new Player(name, surname, nickname, null, 0, 0);
        return playerAux;
    }

    // Método para introducir qué personaje ha seleccionado el jugador, 
    // pasando su id que en el main será traducido a uno de los 3 personajes
    public static int idFighter() {
        Scanner input = new Scanner(System.in);
        int id = -1;
        do {
            System.out.println("Introduzca el personaje:");
            System.out.println(" 0 - BLANKA");
            System.out.println(" 1 - AKUMA");
            System.out.println(" 2 - VIPER");
            id = input.nextInt();
        } while (id != 0 && id != 1 && id != 2);
        return id;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // ---------- DECLARACIÓN DE VARIABLES ----------
        
        // COLAS DE ENUMS GAMEPAD (Combos)
        LinkedQueueJJL<GamePad> queueCombo1 = createQueueCombo(GamePad.R, GamePad.S, GamePad.S, GamePad.X);
        LinkedQueueJJL<GamePad> queueCombo2 = createQueueCombo(GamePad.X, GamePad.S, GamePad.S, GamePad.T);
        LinkedQueueJJL<GamePad> queueCombo3 = createQueueCombo(GamePad.T, GamePad.R, GamePad.T, GamePad.S);
        LinkedQueueJJL<GamePad> queueCombo4 = createQueueCombo(GamePad.X, GamePad.X, GamePad.S, GamePad.R);
        LinkedQueueJJL<GamePad> queueCombo5 = createQueueCombo(GamePad.T, GamePad.S, GamePad.R, GamePad.X);
        LinkedQueueJJL<GamePad> queueCombo6 = createQueueCombo(GamePad.R, GamePad.R, GamePad.T, GamePad.X);
        LinkedQueueJJL<GamePad> queueCombo7 = createQueueCombo(GamePad.S, GamePad.S, GamePad.X, GamePad.T);
        LinkedQueueJJL<GamePad> queueCombo8 = createQueueCombo(GamePad.X, GamePad.R, GamePad.T, GamePad.X);
        LinkedQueueJJL<GamePad> queueCombo9 = createQueueCombo(GamePad.S, GamePad.T, GamePad.R, GamePad.X);
                
        // COMBOS
        Combo combo1 = new Combo("Colmillo Salvaje", 1, queueCombo1);
        Combo combo2 = new Combo("Ataque Rodante", 2, queueCombo2);
        Combo combo3 = new Combo("Trueno Eléctrico", 3, queueCombo3);
        Combo combo4 = new Combo("Gohadoken", 4, queueCombo4);
        Combo combo5 = new Combo("Zanku Hadoken", 5, queueCombo5);
        Combo combo6 = new Combo("Zenpou Tenshin", 6, queueCombo6);
        Combo combo7 = new Combo("Patada Ardiente", 7, queueCombo7);
        Combo combo8 = new Combo("Nudillos de Trueno", 8, queueCombo8);
        Combo combo9 = new Combo("Patada Doble", 9, queueCombo9);
        
        // LISTA ENLAZADA DE LOS COMBOS DE CADA PERSONAJE
        LinkedListJJL<Combo> listBlanka = createFighterComboList(combo1, combo2, combo3);
        LinkedListJJL<Combo> listAkuma = createFighterComboList(combo4, combo5, combo6);
        LinkedListJJL<Combo> listViper = createFighterComboList(combo7, combo8, combo9);
        
        // LUCHADORES
        Fighter blanka = new Fighter("Blanka", 0, 100, listBlanka);
        Fighter akuma = new Fighter("Akuma", 1, 100, listAkuma);
        Fighter viper = new Fighter("Viper", 2, 100, listViper);
        
        // CONJUNTO EN EL QUE SE IRÁN METIENDO LOS JUGADORES UNA VEZ SE DEN DE ALTA
        LinkedSetJJL<Player> setPlayers = new LinkedSetJJL();
        
        // ÁRBOL DEL TORNEO
        //Cuartos de final
        LinkedBinaryTreeJJL<Player> quarter1 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter2 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter3 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter4 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter5 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter6 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter7 = new LinkedBinaryTreeJJL<>();
        LinkedBinaryTreeJJL<Player> quarter8 = new LinkedBinaryTreeJJL<>();
        
        // Semifinal y actualizar padres de cuartos de final
        LinkedBinaryTreeJJL<Player> semiFinal_1 = new LinkedBinaryTreeJJL<>(null, quarter1, quarter2);
        LinkedBinaryTreeJJL<Player> semiFinal_2 = new LinkedBinaryTreeJJL<>(null, quarter3, quarter4);
        LinkedBinaryTreeJJL<Player> semiFinal_3 = new LinkedBinaryTreeJJL<>(null, quarter5, quarter6);
        LinkedBinaryTreeJJL<Player> semiFinal_4 = new LinkedBinaryTreeJJL<>(null, quarter7, quarter8);
        (quarter1.getRaiz()).setPadre(semiFinal_1);
        (quarter2.getRaiz()).setPadre(semiFinal_1);
        (quarter3.getRaiz()).setPadre(semiFinal_2);
        (quarter4.getRaiz()).setPadre(semiFinal_2);
        (quarter5.getRaiz()).setPadre(semiFinal_3);
        (quarter6.getRaiz()).setPadre(semiFinal_3);
        (quarter7.getRaiz()).setPadre(semiFinal_4);
        (quarter8.getRaiz()).setPadre(semiFinal_4);
        
        // Final y actualizar padres de semifinales
        LinkedBinaryTreeJJL<Player> final_1 = new LinkedBinaryTreeJJL<>(null, semiFinal_1, semiFinal_2);
        LinkedBinaryTreeJJL<Player> final_2 = new LinkedBinaryTreeJJL<>(null, semiFinal_3, semiFinal_4);
        (semiFinal_1.getRaiz()).setPadre(final_1);
        (semiFinal_2.getRaiz()).setPadre(final_1);
        (semiFinal_3.getRaiz()).setPadre(final_2);
        (semiFinal_4.getRaiz()).setPadre(final_2);
        
        // Ganador y actualizar padres de finales
        LinkedBinaryTreeJJL<Player> winner = new LinkedBinaryTreeJJL<>(null, final_1, final_2);
        (final_1.getRaiz()).setPadre(winner);
        (final_2.getRaiz()).setPadre(winner);
        
        // Lista Estática de las listas enlazadas de los combos de cada personaje
        //  Cada posición le corresponde a un jugador
        
        // VARIABLES VARIAS
        int option;
        Scanner input = new Scanner(System.in);
        
        
        // ---------- MENÚ DEL JUEGO ----------
        do {            
            System.out.println("========== STREET FIGHTER URJC ==========");
            System.out.println("\n");
            System.out.println(" 1. DAR DE ALTA JUGADORES");
            System.out.println(" 2. GENERAR EMPAREJAMIENTOS");
            System.out.println(" 3. COMBATE MANUAL");
            System.out.println(" 4. COMBATE AUTOMÁTICO");
            System.out.println(" 5. MOSTRAR CLASIFICACIÓN FINAL");
            System.out.println(" 6. SALIR");
            System.out.println(" ");
            System.out.println("Introduzca a continuación una de las opciones que se muestran en el menú:");
            option = input.nextInt();
            System.out.println("\n");

            switch (option) {
                case 1:
                    Player[] player = new Player[8];
                    // jugadorK = player[K-1]
                    //  EJ/ jugador1 = player[0], jugador4 = player[3]
                    for (int i = 0; i < player.length; i++) {
                        player[i] = createPlayer(i + 1);
                        int chosenFighter = idFighter();
                        if (chosenFighter == 0) {
                            player[i].setCharacter(blanka);
                        } else if (chosenFighter == 1) {
                            player[i].setCharacter(akuma);
                        } else {
                            player[i].setCharacter(viper);
                        }
                        setPlayers.insert(player[i]);
                    }
                    System.out.println("\n");
                    break;

                case 2:
                    if (setPlayers.isEmpty()) {
                        System.out.println("No se pueden realizar emparejamientos si los jugadores no están dados de alta");
                        System.out.println("            Por favor, SELECCIONE LA OPCIÓN 1 DEL MENÚ");
                        System.out.println("\n");
                    } else {
                        for (int i = 0; i < 9; i++) {
                            
                        }
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (option != 6);{
            
        }
        
    }

}
