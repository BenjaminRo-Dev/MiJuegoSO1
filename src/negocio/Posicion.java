package negocio;

public class Posicion {
    
    int x;  //Ancho
    int y;  //Alto

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //Se ingresa porcentual, devuelve real
    public int x(int pos){
        return (x/100) * pos;
    }
    
    //Se ingresa porcentual, devuelve real
    public int y(int pos){
        return (y/100) * pos;
    }
    
    
    
    
        
    
    
    
}
