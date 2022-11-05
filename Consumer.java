/**
 * @author (HerbertWenisch)
 * @version (04.11.21)
 */
public class Consumer extends Thread{
    private int m; // Anzahl der zu entnehmenden Werkstücke
    private final Buffer buffer;
    private final long napTime;
    private int workpiece; 
    
    public Consumer(Buffer buffer, int m, long napTime) {
        this.buffer = buffer;
        this.m = m;
        this.napTime = napTime;
    }
    
    @Override
    public void run(){
       while (m > 0){
          workpiece = buffer.remove(); 
          if (workpiece == -1) Logger.reportC("no access to buffer: is empty");
          else {
              Logger.reportC("removed from buffer: " + workpiece);
              m--;
          }
          nap(napTime);
       }
    }
    
    private void nap(long ms){
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException e) {}
    }
}
