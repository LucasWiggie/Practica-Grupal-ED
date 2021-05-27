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
import Implementation.BinaryTreeLinkedJJL;
import Implementation.HashMapJJL;
import Implementation.IteratorLinkedListJJL;
import Implementation.IteratorLinkedQueueJJL;
import Implementation.ListLinkedJJL;
import Implementation.QueueLinkedJJL;
import Implementation.SetLinkedJJL;
import Implementation.ListStaticArrayJJL;
import java.security.InvalidKeyException;
import java.util.Scanner;

/**
 *
 * @author Javier Álvarez, Javier de las Peñas, Lucas Rubio
 */
public class PracticaEvaluable_JJL {

    // Método para crear la cola de botones que le corresponderá a cada combo
    public static QueueLinkedJJL createQueueCombo(GamePad button1, GamePad button2, GamePad button3, GamePad button4) {
        QueueLinkedJJL<GamePad> queueCombo = new QueueLinkedJJL<>();
        queueCombo.add(button1);
        queueCombo.add(button2);
        queueCombo.add(button3);
        queueCombo.add(button4);

        return queueCombo;
    }

    // Método para crear la lista de combos que cada personaje tendrá disponible
    public static ListLinkedJJL createFighterComboList(Combo comboOne, Combo comboTwo, Combo comboThree) {
        ListLinkedJJL<Combo> listFighter = new ListLinkedJJL<>();
        listFighter.add(0, comboOne);
        listFighter.add(1, comboTwo);
        listFighter.add(2, comboThree);

        return listFighter;
    }

    // Método para crear un jugador, sin introducir todavía su personaje
    public static Player createPlayer(int numJugador) {
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

        Player playerAux = new Player(name, surname, nickname, null, numJugador - 1, 0, 0);
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

    // Con el siguiente método comprobamos si el String que ha introducido el usuario corresponde con
    // alguno de los combos disponibles de dicho jugador, iterando sobre la lista de combos y asegurando
    // si se encuentra en esa lista
    public static boolean comboIsValid(String insertedCombo, ListLinkedJJL<Combo> PlayerCombos) {
        IteratorLinkedListJJL<Combo> it = new IteratorLinkedListJJL<>(PlayerCombos.getHead());
        boolean found = false;

        while (found == false && it.hasNext()) {
            String checkCombo = ((it.next()).getMovements()).queueToString();
            if (checkCombo.equalsIgnoreCase(insertedCombo)) {
                found = true;
            }
        }

        return found;
    }

    public static Player manualCombat(ListStaticArrayJJL<ListLinkedJJL<Combo>> listOfPlayerCombos, Player p1, Player p2, HashMapJJL map, Scanner input) {
        /*
        Obtenemos el id de cada jugador, que será la posición de la lista estática 
        en la que se encuentra su lista enlazada de combos
         */
        int id1 = p1.getId();
        int id2 = p2.getId();
        /*
        Obtenemos la lista enlazada de combos de cada jugador y creamos un iterador sobre esas listas
         */
        ListLinkedJJL<Combo> listCombosP1 = listOfPlayerCombos.get(id1);
        ListLinkedJJL<Combo> listCombosP2 = listOfPlayerCombos.get(id2);

        // COMBATE
        boolean endOfCombat = false;
        boolean repete;
        int turno = 1;
        
        // Inicializamos las variables winner y looser a unos jugadores arbitrarios. Tras el combate,
        // adquirirán sus valores respectivos y correctos
        Player winner = p1;
        Player looser = p2;
        // Bucle que acabará cuando uno de los jugadores sea derrotado
        do {
            // Turnos impares
            if (turno % 2 != 0) {
                /*
                Si el jugador introduce un combo no válido, se le volverá a pedir
                */
                do {
                    System.out.println(p1.getName() + ", introduzca el ataque que desea realizar: ");
                    String insertedComboButtons = input.nextLine();

                    if (comboIsValid(insertedComboButtons, listCombosP1)) {
                        repete = false;
                        // Daño que va a recibir el oponente
                        int attack = map.get(insertedComboButtons);
                        // Vida original del oponente
                        int life = (p2.getCharacter()).getLife();
                        // Se realiza el ataque
                        (p2.getCharacter()).setLife(life - attack);

                        if ((p2.getCharacter()).getLife() <= 0) {
                            endOfCombat = true;
                            winner = p1;
                            looser = p2;
                        }
                    } else {
                        System.out.println("!) El combo introducido no es válido (!)"
                                + "[Asegurese de que la secuencia de botones es un combo de su personaje en el juego]"
                                + " \n");
                        repete = true;
                    }
                } while (repete == true);

                // Turnos pares
            } else {
                /*
                Si el jugador introduce un combo no válido, se le volverá a pedir
                */
                do {
                    System.out.println(p2.getName() + ", introduzca el ataque que desea realizar: ");
                    String insertedComboButtons = input.nextLine();

                    if (comboIsValid(insertedComboButtons, listCombosP2)) {
                        repete = false;
                        // Daño que va a recibir el oponente
                        int attack = map.get(insertedComboButtons);
                        // Vida original del oponente
                        int life = (p1.getCharacter()).getLife();
                        // Se realiza el ataque
                        (p1.getCharacter()).setLife(life - attack);

                        if ((p1.getCharacter()).getLife() <= 0) {
                            endOfCombat = true;
                            winner = p2;
                            looser = p1;
                        }
                    } else {
                        System.out.println("!) El combo introducido no es válido (!)"
                                + "[Asegurese de que la secuencia de botones es un combo de su personaje en el juego]"
                                + " \n");
                        repete = true;
                    }
                } while (repete == true);
            }
            turno++;
        } while (endOfCombat == false);

        // Una vez se ha terminado el combate
        System.out.println(looser.getName() + " ha sido derrotado, el GANADOR DEL COMBATE ES " + winner.getName());
        return winner;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // ---------- DECLARACIÓN DE VARIABLES ----------
        
        // COLAS DE ENUMS GAMEPAD (Combos)
        QueueLinkedJJL<GamePad> queueCombo1 = createQueueCombo(GamePad.R, GamePad.S, GamePad.S, GamePad.X);
        QueueLinkedJJL<GamePad> queueCombo2 = createQueueCombo(GamePad.X, GamePad.S, GamePad.S, GamePad.T);
        QueueLinkedJJL<GamePad> queueCombo3 = createQueueCombo(GamePad.T, GamePad.R, GamePad.T, GamePad.S);
        QueueLinkedJJL<GamePad> queueCombo4 = createQueueCombo(GamePad.X, GamePad.X, GamePad.S, GamePad.R);
        QueueLinkedJJL<GamePad> queueCombo5 = createQueueCombo(GamePad.T, GamePad.S, GamePad.R, GamePad.X);
        QueueLinkedJJL<GamePad> queueCombo6 = createQueueCombo(GamePad.R, GamePad.R, GamePad.T, GamePad.X);
        QueueLinkedJJL<GamePad> queueCombo7 = createQueueCombo(GamePad.S, GamePad.S, GamePad.X, GamePad.T);
        QueueLinkedJJL<GamePad> queueCombo8 = createQueueCombo(GamePad.X, GamePad.R, GamePad.T, GamePad.X);
        QueueLinkedJJL<GamePad> queueCombo9 = createQueueCombo(GamePad.S, GamePad.T, GamePad.R, GamePad.X);
                
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
        ListLinkedJJL<Combo> listBlanka = createFighterComboList(combo1, combo2, combo3);
        ListLinkedJJL<Combo> listAkuma = createFighterComboList(combo4, combo5, combo6);
        ListLinkedJJL<Combo> listViper = createFighterComboList(combo7, combo8, combo9);
        
        // LUCHADORES
        Fighter blanka = new Fighter("Blanka", 0, 100, listBlanka);
        Fighter akuma = new Fighter("Akuma", 1, 100, listAkuma);
        Fighter viper = new Fighter("Viper", 2, 100, listViper);
        
        // CONJUNTO EN EL QUE SE IRÁN METIENDO LOS JUGADORES UNA VEZ SE DEN DE ALTA
        SetLinkedJJL<Player> setPlayers = new SetLinkedJJL();
        
        // ÁRBOL DEL TORNEO
        //Creamos un personaje solo para rellenar el árbol con objetos no nulos
        //Una vez se vaya avanzando en el torneo, se irá sustituyendo el personaje por los ganadores
        Player emptyPlayer = new Player();
        
        //Cuartos de final
        BinaryTreeLinkedJJL<Player> quarter1 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter2 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter3 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter4 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter5 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter6 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter7 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        BinaryTreeLinkedJJL<Player> quarter8 = new BinaryTreeLinkedJJL<>(emptyPlayer);
        
        // Semifinal 
        BinaryTreeLinkedJJL<Player> semiFinal_1 = new BinaryTreeLinkedJJL<>(emptyPlayer, quarter1, quarter2);
        BinaryTreeLinkedJJL<Player> semiFinal_2 = new BinaryTreeLinkedJJL<>(emptyPlayer, quarter3, quarter4);
        BinaryTreeLinkedJJL<Player> semiFinal_3 = new BinaryTreeLinkedJJL<>(emptyPlayer, quarter5, quarter6);
        BinaryTreeLinkedJJL<Player> semiFinal_4 = new BinaryTreeLinkedJJL<>(emptyPlayer, quarter7, quarter8);
                
        // Final 
        BinaryTreeLinkedJJL<Player> final_1 = new BinaryTreeLinkedJJL<>(emptyPlayer, semiFinal_1, semiFinal_2);
        BinaryTreeLinkedJJL<Player> final_2 = new BinaryTreeLinkedJJL<>(emptyPlayer, semiFinal_3, semiFinal_4);
                
        // Ganador y actualizar padres de finales
        BinaryTreeLinkedJJL<Player> winner = new BinaryTreeLinkedJJL<>(emptyPlayer, final_1, final_2);
        
        // HASH MAP
        HashMapJJL mapCombos = new HashMapJJL(11);
        mapCombos.put("RSSX", 15);
        mapCombos.put("XSST", 30);
        mapCombos.put("TRTS", 40);
        mapCombos.put("XXSR", 15);
        mapCombos.put("TSRX", 30);
        mapCombos.put("RRTX", 40);
        mapCombos.put("SSXT", 15);
        mapCombos.put("XRTX", 30);
        mapCombos.put("STRX", 40);
        
        // Lista Estática de las listas enlazadas de los combos de cada personaje
        //  Cada posición le corresponde a un jugador
        ListStaticArrayJJL<ListLinkedJJL<Combo>> listOfCombos_perPlayer = new ListStaticArrayJJL<>();
        
        // VARIABLES VARIAS
        int option;
        Scanner input = new Scanner(System.in);

        // Controlar qué aprtes del menú se han llevado a cabo
        boolean playersCreationDone= false;
        boolean matchmakingDone = false;
        
        // Controlar qué partes del árbol de torneo ya se han completado
        boolean quarterFinalsDone = false;
        boolean semiFinalsDone = false;
        boolean finalsDone = false;
        /*
        Cada posición de los siguientes arrays le corresponde a uno de los combates en los cuartos de final y en semifinales.
        Se usará cada posición como 'flag' para señalar si se ha realizado ya dicho combate dicho combate.
        */
        boolean[] quarterFinalsCombats = new boolean[4];
        for (int i = 0; i < quarterFinalsCombats.length; i++) {
            quarterFinalsCombats[i] = false;
        }
        
        boolean[] semiFinalsCombats = new boolean[2];
        for (int i = 0; i < semiFinalsCombats.length; i++) {
            semiFinalsCombats[i] =false;
        }
        
        

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
            System.out.println("-----------------------------------------");
            System.out.println(" ");
            System.out.println("Introduzca a continuación una de las opciones que se muestran en el menú:");
            option = input.nextInt();
            System.out.println("\n");

            switch (option) {
                case 1:
                    System.out.println("-----DAR DE ALTA A JUGADORES-----");
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
                        //Añadimos el jugador al conjunto y la lista de combos de su personaje a la lista estática
                        setPlayers.insert(player[i]);
                        listOfCombos_perPlayer.add(i, (player[i].getCharacter()).getComboList());
                    }
                    playersCreationDone = true;
                    System.out.println("\n");
                    break;

                case 2:
                    if (setPlayers.isEmpty()) {
                        System.out.println("No se pueden realizar emparejamientos si los jugadores no están dados de alta");
                        System.out.println("            Por favor, SELECCIONE LA OPCIÓN 1 DEL MENÚ");
                        System.out.println("\n");
                    } else {
                        System.out.println("-----GENERAR EMPAREJAMIENTOS-----");
                        QueueLinkedJJL<Player> queueQuarters = new QueueLinkedJJL<>();
                        for (int i = 0; i < 8; i++) {
                            Player chosenPlayer = setPlayers.choose();
                            queueQuarters.add(chosenPlayer);
                        }
                        (quarter1.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter2.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter3.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter4.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter5.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter6.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter7.getRaiz()).setInfo(queueQuarters.remove());
                        (quarter8.getRaiz()).setInfo(queueQuarters.remove());
                        matchmakingDone = true;
                    }
                    System.out.println("\n");
                    break;

                case 3:
                    if (playersCreationDone == false) {
                        System.out.println("No se pueden realizar combates si los jugadores no están dados de alta");
                        System.out.println("            Por favor, SELECCIONE LA OPCIÓN 1 DEL MENÚ");
                        System.out.println("\n");
                    } else if (matchmakingDone == false) {
                        System.out.println("No se pueden realizar combates si no se han organizado los emparejamientos");
                        System.out.println("                Por favor, SELECCIONE LA OPCIÓN 2 DEL MENÚ");
                        System.out.println("\n");
                    } else if (finalsDone == true) {
                        System.out.println("                    El torneo ya ha sido realizado");
                        System.out.println("                     Por favor, REINICIE EL JUEGO");
                        System.out.println("\n");
                    } else {
                        System.out.println("------------COMBATES------------");
                        
                        if (quarterFinalsDone == false) {
                            // Si todavía no se han completado todos los combates de los cuartos de final
                            int combateElegido;
                            boolean repete = true; //para ver si se ha de repetir la sección de elegir el combate

                            do {
                                do {
                                    System.out.println("Seleccione qué combate de Cuartos de Final desea realizar:");
                                    System.out.println("0: " + ((Player) (quarter1.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter2.getRaiz()).getInfo()).getName());
                                    System.out.println("1: " + ((Player) (quarter3.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter4.getRaiz()).getInfo()).getName());
                                    System.out.println("2: " + ((Player) (quarter5.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter6.getRaiz()).getInfo()).getName());
                                    System.out.println("3: " + ((Player) (quarter7.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter8.getRaiz()).getInfo()).getName());
                                    System.out.println("\n");
                                    combateElegido = input.nextInt();
                                } while (combateElegido != 0 && combateElegido != 1 && combateElegido != 2 && combateElegido != 3);

                                if (combateElegido == 0 && quarterFinalsCombats[0] == false) {
                                    //Se debe realizar el primer emparejamiento de los cuartos de final
                                    System.out.println(((Player) (quarter1.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter2.getRaiz()).getInfo()).getName());
                                    System.out.println(" ");
                                    System.out.println("                      FIGHT! " + "\n");
                                    
                                    Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((quarter1.getRaiz()).getInfo()), ((quarter2.getRaiz()).getInfo()), mapCombos, input);
                                    (semiFinal_1.getRaiz()).setInfo(auxPlayer);
                                    quarterFinalsCombats[0] = true;
                                    repete = false;
                                } else if (combateElegido == 1 && quarterFinalsCombats[1] == false) {
                                    //Se debe realizar el segundo emparejamiento de los cuartos de final
                                    System.out.println(((Player) (quarter3.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter4.getRaiz()).getInfo()).getName());
                                    System.out.println(" ");
                                    System.out.println("                      FIGHT! " + "\n");
                                    
                                    Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((quarter3.getRaiz()).getInfo()), ((quarter4.getRaiz()).getInfo()), mapCombos, input);
                                    (semiFinal_2.getRaiz()).setInfo(auxPlayer);
                                    quarterFinalsCombats[1] = true;
                                    repete = false;
                                } else if (combateElegido == 2 && quarterFinalsCombats[2] == false) {
                                    //Se debe realizar el tercer emparejamiento de los cuartos de final
                                    System.out.println(((Player) (quarter5.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter6.getRaiz()).getInfo()).getName());
                                    System.out.println(" ");
                                    System.out.println("                      FIGHT! " + "\n");
                                    
                                    Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((quarter5.getRaiz()).getInfo()), ((quarter6.getRaiz()).getInfo()), mapCombos, input);
                                    (semiFinal_3.getRaiz()).setInfo(auxPlayer);
                                    quarterFinalsCombats[2] = true;
                                    repete = false;
                                } else if (combateElegido == 3 && quarterFinalsCombats[3] == false) {
                                    //Se debe realizar el cuarto emparejamiento de los cuartos de final
                                    System.out.println(((Player) (quarter7.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter8.getRaiz()).getInfo()).getName());
                                    System.out.println(" ");
                                    System.out.println("                      FIGHT! " + "\n");
                                    
                                    Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((quarter7.getRaiz()).getInfo()), ((quarter8.getRaiz()).getInfo()), mapCombos, input);
                                    (semiFinal_4.getRaiz()).setInfo(auxPlayer);
                                    quarterFinalsCombats[3] = true;
                                    repete = false;
                                } else {
                                    // Se ha seleccionado un combate ya realizado, activamos el "flag" para que se vuelva a pedir la selección
                                    // de combates
                                    System.out.println("(!) El combate seleccionado ya ha sido realizado (!) ");
                                    System.out.println("        Por favor, seleccione otro combate");
                                    repete = true;
                                }
                            } while (repete = true);

                        } else if (semiFinalsDone == false) {
                            // Si todavía no se han completado todos los combates de las semifinales
                            // Si todavía no se han completado todos los combates de los cuartos de final
                            int combateElegido;
                            boolean repete = true; //para ver si se ha de repetir la sección de elegir el combate

                            do {
                                do {
                                    System.out.println("Seleccione qué combate de Cuartos de Final desea realizar:");
                                    System.out.println("0: " + ((Player) (quarter1.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter2.getRaiz()).getInfo()).getName());
                                    System.out.println("1: " + ((Player) (quarter3.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (quarter4.getRaiz()).getInfo()).getName());
                                    System.out.println("\n");
                                    combateElegido = input.nextInt();
                                    
                                } while (combateElegido != 0 && combateElegido != 1);

                                if (combateElegido == 0 && quarterFinalsCombats[0] == false) {
                                    //Se debe realizar el primer emparejamiento de las semifinales
                                    System.out.println(((Player) (semiFinal_1.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (semiFinal_2.getRaiz()).getInfo()).getName());
                                    System.out.println(" ");
                                    System.out.println("                      FIGHT! " + "\n");
                                    
                                    Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((semiFinal_1.getRaiz()).getInfo()), ((semiFinal_2.getRaiz()).getInfo()), mapCombos, input);
                                    (final_1.getRaiz()).setInfo(auxPlayer);
                                    semiFinalsCombats[0] = true;
                                    repete = false;
                                } else if (combateElegido == 1 && quarterFinalsCombats[1] == false) {
                                    //Se debe realizar el segundo emparejamiento de los cuartos de final
                                    System.out.println(((Player) (semiFinal_3.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (semiFinal_4.getRaiz()).getInfo()).getName());
                                    System.out.println(" ");
                                    System.out.println("                      FIGHT! " + "\n");
                                    
                                    Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((semiFinal_3.getRaiz()).getInfo()), ((semiFinal_4.getRaiz()).getInfo()), mapCombos, input);
                                    (final_2.getRaiz()).setInfo(auxPlayer);
                                    semiFinalsCombats[1] = true;
                                    repete = false;
                                } else {
                                    // Se ha seleccionado un combate ya realizado, activamos el "flag" para que se vuelva a pedir la selección
                                    // de combates
                                    System.out.println("(!) El combate seleccionado ya ha sido realizado (!) ");
                                    System.out.println("        Por favor, seleccione otro combate");
                                    repete = true;
                                }
                            } while (repete = true);
                        } else if (finalsDone == false) {
                            // Si todavía no se han completado la final

                            System.out.println("FINAL");
                            System.out.println(((Player) (final_1.getRaiz()).getInfo()).getName() + " /VS/ " + ((Player) (final_2.getRaiz()).getInfo()).getName());
                            System.out.println("\n");

                            Player auxPlayer = manualCombat(listOfCombos_perPlayer, ((final_1.getRaiz()).getInfo()), ((final_2.getRaiz()).getInfo()), mapCombos, input);
                            (winner.getRaiz()).setInfo(auxPlayer);
                            finalsDone = true;

                        } else {
                            // Ya se han realizado todos los combates del torneo
                            System.out.println("                    El torneo ya ha sido realizado");
                            System.out.println((winner.getRaizInfo()).getName() + " ha sido el vencedor de STREET FIGHTER URJC");
                            System.out.println("        Por favor, REINICIE EL JUEGO si quiere realizar otro torneo");
                            System.out.println("\n");
                        }
                    }
                    break;

                case 4:
                    break;
                case 5:
                    break;
            }
        } while (option != 6);
        {

        }

    }

}
