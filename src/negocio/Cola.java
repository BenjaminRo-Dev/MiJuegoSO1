package negocio;

import java.util.LinkedList;
import java.util.Queue;

/* @author BenRo*/
public class Cola {
    private Queue<PCB> queue;
    private final int MAX = 200;
    
    
    public Cola() {
        queue = new LinkedList<>();
    }
    
    public void iniciar(){
        queue.clear();      //Limpiar la cola
    }
    
    public int length(){
        return queue.size();    //Devuelve la longitud de la cola
    }
    
    public void meter(PCB p) throws Exception {
        if(queue.size() == MAX){
            throw new Exception("Cola llena");
        }
        queue.add(p);
    }
    
    public PCB sacar() throws Exception {
        if(queue.isEmpty()){
            throw new Exception("Cola vacía");
        }
        return queue.poll();
    }
    
    
}
