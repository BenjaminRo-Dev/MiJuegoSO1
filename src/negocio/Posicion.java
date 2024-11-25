package negocio;

public class Posicion {
    
    int x;  //Ancho
    int y;  //Alto

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //Devuelve la posición porcentual de x
    public int x(int pos){
        return (x/100) * pos;
    }
    
    //Devuelve la posición porcentual de y
    public int y(int pos){
        return (y/100) * pos;
    }
        
    
    
    
}
