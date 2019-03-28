/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Estudiante
 */
public class Conjunto {
    private ArrayList<Ficha> conjunto_fichas;

    public Conjunto() {
        this.conjunto_fichas = new ArrayList<Ficha>();
        this.llenar_fichas();
    }
    
    public void llenar_fichas(){
        int contador;
        this.conjunto_fichas.add(new Ficha(Pinta.CERO, Pinta.CERO));
        for(Pinta cara1 : Pinta.values()){
            for(Pinta cara2 : Pinta.values()){
                Ficha ficha_temporal_1 = new Ficha(cara1, cara2);
                Ficha ficha_temporal_2 = new Ficha(cara2, cara1);
                contador = 0;
                for(Ficha iteracion : this.conjunto_fichas){
                    if(this.comparar(ficha_temporal_1, iteracion) || this.comparar(ficha_temporal_2, iteracion)){
                        contador+=1;
                    }
                }
                if(contador == 0){
                    this.conjunto_fichas.add(ficha_temporal_1);
                }
            }
            
        }
    }
    
    public boolean comparar(Ficha ficha1, Ficha ficha2){
        
        if((ficha1.getCara1() == ficha2.getCara1()) && (ficha1.getCara2() == ficha2.getCara2())){
            //System.out.println("true");
            return true;
        }else{
            //System.out.println("false");
            return false;
        }
        
    }
    
    
    public void imprimir_fichas(){
        int i = 0;
        for( Ficha ficha : this.conjunto_fichas){
            System.out.println(i + "[" +ficha.getCara1()+", " + ficha.getCara2() + "]");
            i++;
        }
    }
    
    public void repartir(Jugador jugador_1, Jugador jugador_2){
        int i = 0;
        Collections.shuffle(this.conjunto_fichas);
        for(Ficha ficha : this.conjunto_fichas){
            if(i<14){
                jugador_1.getMano().add(ficha);
            }else{
                jugador_2.getMano().add(ficha);
            }
            i++;
        }
    }
    
    
}
