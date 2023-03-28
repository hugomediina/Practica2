package uji.al415648.algoritmos;

public class TooMuchGroupsException extends Exception{
    public TooMuchGroupsException(){
        super("El número de grupos es mayor que el de datos");
    }
}
