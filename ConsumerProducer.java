/**
 * @author (HerbertWenisch)
 * @version (04.11.21)
 */
public class ConsumerProducer{
    private final int N = 6;
    private final int m = 4; // Produktionsumfang
    private final long napTimeC = 100;
    private final long napTimeP = 800; // langsamer Produzent
    
    private final Buffer buffer = new Buffer(N);
    private final Consumer consumer = new Consumer(buffer, m, napTimeC);
    private final Producer procucer = new Producer(buffer, m, napTimeP);
    
    public void test(){
         procucer.start();
         consumer.start();
    }
    
}