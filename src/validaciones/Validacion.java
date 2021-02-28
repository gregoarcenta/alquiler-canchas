package validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    
    public boolean validateEmail(String email){
        Pattern pat = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$");
        Matcher mat = pat.matcher(email);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
    public boolean validateName(String name){
        Pattern pat = Pattern.compile("^[a-zA-ZÁ-ÿ\\s]{1,60}$");
        Matcher mat = pat.matcher(name);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
    public boolean validateCedula(String cedula){
        Pattern pat = Pattern.compile("^\\d{10,13}$");
        Matcher mat = pat.matcher(cedula);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
    public boolean validateCelular(String celular){
        Pattern pat = Pattern.compile("^\\d{10}$");
        Matcher mat = pat.matcher(celular);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
        
    public boolean validateConvencional(String convencional){
        Pattern pat = Pattern.compile("^\\d{7,9}$");
        Matcher mat = pat.matcher(convencional);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
    public boolean validateDireccion(String direccion){
        Pattern pat = Pattern.compile("^[a-zA-Z0-9\\s]{4,80}$");
        Matcher mat = pat.matcher(direccion);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
    public boolean validateCodigo(String convencional){
        Pattern pat = Pattern.compile("^\\d{4,9}$");
        Matcher mat = pat.matcher(convencional);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
    public boolean validatePrecio(String convencional){
        Pattern pat = Pattern.compile("^[0-9]+\\.[0-9]{1,2}$");
        Matcher mat = pat.matcher(convencional);                                                                           
        if(mat.matches()) {
            return true;
        }else{
            return false;    
       }
    }
    
}
