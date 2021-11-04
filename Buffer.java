
/**
 * Write a description of class Buffer here.
 *
 * @author (HerbertWenisch)
 * @version (04.11.21)
 */
public class Buffer{
    private final int N;
    private final int[] buffer;
    private int low, high, count;

    public Buffer(int N) {
        this.N = N;
        buffer = new int[N];
    }
    
    public synchronized boolean insert(int workpiece){
        if (isFull()){
            
            try {wait();}
            catch (InterruptedException e) {}
            
            return false;
        }
        // not full:
        buffer[high] = workpiece;
        high = high++ % N;
        count++;
        if(count == 1) notifyAll(); // Buffer war zuvor leer
        return true;
    }
    
    public synchronized int remove(){
      int workpiece;
      if (isEmpty()){
          
          try {wait();}
          catch (InterruptedException e) {}
          
          return -1;
      }
      // not empty:
      workpiece = buffer[low];
      low = low++ % N;
      count--;
      if(count == N-1) notifyAll(); // Buffer war zuvor voll
      return workpiece;
    }
    
    private boolean isFull(){
        return count == N;
    }
    
    private boolean isEmpty(){
        return count == 0;
    }
    
}
    