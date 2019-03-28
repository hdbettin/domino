/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * @author Estudiante
 */
public class Jugador {
    private ArrayList<Ficha>mano;
    private String nombre;

    public Jugador(String nombre_jugador) {
        this.mano = new ArrayList<Ficha>();
        this.nombre = nombre_jugador;
    }

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }
    
    public boolean comprobar_mano(Pinta cara_izquierda, Pinta cara_derecha){
        int contador = 0;
        for(Ficha ficha : this.getMano()){
            if(cara_izquierda == ficha.getCara1() || cara_izquierda == ficha.getCara2()){
                contador+=1;
            }
            if(cara_derecha == ficha.getCara1() || cara_derecha == ficha.getCara2()){
                contador+=1;
            }
        }
        if(contador != 0){
            return true;
        }else{
           return false; 
        }
        
    }
    
    public void mostrar_mano(){
        int i=1;
        for(Ficha ficha: this.mano){
            System.out.print(i+".["+ficha.getCara1()+", "+ficha.getCara2()+"]");
            i++;
        }
        System.out.println("");
    }
    
    public boolean eliminar_ficha(Ficha eliminada){
        boolean retorno = false;
        for(Ficha ficha : this.getMano()){
            if(this.comparar(ficha, eliminada)){
                eliminada = ficha;
                System.out.println("la ficha fue eliminada");
                retorno = true;
            }
        }
        this.getMano().remove(eliminada);
        return retorno;
    }
    
    public boolean comparar(Ficha ficha1, Ficha ficha2){
        
        if((ficha1.getCara1() == ficha2.getCara1()) && (ficha1.getCara2() == ficha2.getCara2())){
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean poner(Deque<Ficha> juego, Ficha ficha, String posicion){
        boolean retorno = false;
        if(posicion == "derecha"){
            if(juego.getLast().getCara2() == ficha.getCara1()){
                juego.addLast(ficha);
                this.eliminar_ficha(ficha);
                retorno = true;
            }else if(juego.getLast().getCara2() == ficha.getCara2()){
                juego.addLast(new Ficha(ficha.getCara2(),ficha.getCara1()));
                this.eliminar_ficha(ficha);
                retorno = true;
            }else{
                System.out.println("Esta ficha no puede puede ir en esa posicion");
                retorno = false;
            }
        }else if(posicion == "izquierda"){
            if(juego.getFirst().getCara1() == ficha.getCara1()){
                juego.addFirst(new Ficha(ficha.getCara2(),ficha.getCara1()));
                this.eliminar_ficha(ficha);
                retorno = true;
            }else if(juego.getFirst().getCara1() == ficha.getCara2()){
                juego.addFirst(ficha);
                this.eliminar_ficha(ficha);
                retorno = true;
            }else{
                System.out.println("Esta ficha no puede puede ir en esa posicion");
                retorno = false;
            }
        }
        return retorno;
    }
    
}