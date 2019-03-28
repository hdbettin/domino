/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_.poo;

import clases.Conjunto;
import clases.Ficha;
import clases.Jugador;
import clases.Pinta;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class Proyecto_Poo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conjunto conjunto = new Conjunto();
        Jugador jugador1 = new Jugador("juan");
        Jugador jugador2 = new Jugador("pedro");
        ArrayList <Jugador> jugadores = new ArrayList<Jugador>();
        conjunto.repartir(jugador1, jugador2);
        Deque <Ficha> juego = new LinkedList<Ficha>();
        Pinta cara_izquierda;int contador_pasa_turno = 0;
        Pinta cara_derecha;
        Jugador ganador = null;
        
        if(jugador1.eliminar_ficha(new Ficha(Pinta.SEIS, Pinta.SEIS))){
            juego.add(new Ficha(Pinta.SEIS, Pinta.SEIS));
            //jugador1.eliminar_ficha(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugadores.add(jugador2);
            jugadores.add(jugador1);
        }else{
            juego.add(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugador2.eliminar_ficha(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugadores.add(jugador1);
            jugadores.add(jugador2);
        }
        
        while(true){
            for(Jugador jugador_actual : jugadores){
                if(ganador != null){
                    break;
                }
                cara_izquierda = juego.getFirst().getCara1();
                cara_derecha = juego.getLast().getCara2();
                int num_ficha = 0;
                int direccion = 0;
                System.out.println("turno del jugador " + jugador_actual.getNombre());
                System.out.println("Asi va el juego");
                int i=1;
                for(Ficha ficha: juego){
                    System.out.print("["+ficha.getCara1()+", "+ficha.getCara2()+"]");
                    i++;
                }
        System.out.println("");
                System.out.println("tu mano:");
                jugador_actual.mostrar_mano();
                if(jugador_actual.comprobar_mano(cara_izquierda, cara_derecha)){
                    contador_pasa_turno = 0;
                    Scanner entrada = new Scanner(System.in);
                    while(true){
                        System.out.println("escoja el numero de la ficha a poner");
                        try{
                            num_ficha = entrada.nextInt();
                            if(num_ficha > jugador_actual.getMano().size()){
                                System.out.println("Este numero es mayor al numero de fichas que tienes");
                                continue;
                            }
                        }catch(Exception e){
                            System.out.println("Por favor introduzca un numero");
                            entrada.nextLine();
                            continue;
                        }
                        System.out.println("presione 1 para poner la ficha a la izquierda y 2 para ponerlo a la derecha");
                        //if(direccion == (int) direccion){
                            try{
                                direccion = entrada.nextInt();
                            if(direccion == 1){
                                if(jugador_actual.poner(juego, jugador_actual.getMano().get(num_ficha-1), "izquierda")){
                                    if(jugador_actual.getMano().isEmpty()){
                                        ganador = jugador_actual;
                                    }
                                    break;
                                }
                            }else if(direccion == 2){
                                if(jugador_actual.poner(juego, jugador_actual.getMano().get(num_ficha-1), "derecha")){
                                    if(jugador_actual.getMano().isEmpty()){
                                        ganador = jugador_actual;
                                    }
                                    break;
                                }
                            }else{
                                System.out.println("solo se aceptan los numeros 1 o 2 por favor vuelva a hacerlo");
                            }
                        }catch(Exception e){
                            
                            System.out.println("Por favor introduzca un numero");
                            entrada.nextLine();
                        }
                    }
                }else{
                    System.out.println("el jugador " + jugador_actual.getNombre() + " pasa el turno");
                    contador_pasa_turno++;
                } 
                System.out.println("-----------------------------------------------------------------------------------------");
            }
            for(Ficha ficha : juego){
                System.out.println(ficha.getCara1());
            }
            if(contador_pasa_turno >= 2){
                System.out.println("Ambos jugadores pasaron turno por eso se declara que el juego está cerrado");
                System.out.println("mano final del jugador: " + jugador1.getNombre());
                jugador1.getMano();
                System.out.println("mano fi1nal del jugador: " + jugador2.getNombre());<
                jugador2.getMano();
                System.out.println("Juego finalizado");
                break;
            }
        }
    }
    
}
