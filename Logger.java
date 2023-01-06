/**
 * @author (HerbertWenisch)
 * @version (04.12.21)
 */
public class Logger{
        
// Producer:
    public static void reportP(String message){
        System.out.println("Producer: " + message);
    }
    
// Consumer: 
    public static void reportC(String message){
        System.out.println("Consumer: " + message);
    }
}
