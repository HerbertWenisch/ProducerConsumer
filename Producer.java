/**
 * @author (your name)
 * @version (a version number or a date)
 */
public class Producer extends Thread{
    private final Buffer buffer;
    private int m;  // Anzahl der zu produziereden Werkstücke
    private final long napTime;  // Ruhezeit nach dem Ablegeversuch
    private int workpiece;  // aktuelles Werkstück
    private boolean isInserted; // Werkstück ist abgelegt?

    public Producer(Buffer buffer, int m, long napTime) {
        this.buffer = buffer;
        this.m = m;
        this.napTime= napTime;
        workpiece = produce(); // erstes Werkstück
        isInserted = false; // noch nichts abgelegt
    }
    
    @Override
    public void run(){
       while (m > 0){
          if (isInserted) workpiece = produce(); // neues Werkstück
          isInserted = buffer.insert(workpiece);
          if(isInserted) Logger.reportP("insert into buffer: " + workpiece);
          else Logger.reportP("no access to buffer: is full");
          nap(napTime);
       }
    }
    
    private int produce(){
        int workpiece = (int) Math.ceil(Math.random()* 10000);
        Logger.reportP("produced: " + workpiece);
        m--;
        return workpiece;
    }
    
    private void nap(long ms){
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException e) {}
    }
}

