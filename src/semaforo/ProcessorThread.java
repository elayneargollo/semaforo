package semaforo;

import java.util.concurrent.Semaphore;

public class ProcessorThread extends Thread{
	
	private int idThread;
    private Semaphore semaphore;
 
    public ProcessorThread(int id, Semaphore semaphore) {
        this.idThread = id;
        this.semaphore = semaphore;
    }
    
    private void process() {
        try {
            System.out.println("Thread #" + idThread + " processing");
            Thread.sleep((long) (Math.random() * 10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void enterNonCriticalRegion() {
        System.out.println("Thread #" + idThread + " in non-critical region");
        process();
    }
    
    private void enterCriticalRegion() {
        System.out.println("Thread #" + idThread + " entering critical region");
        process();
        System.out.println("Thread #" + idThread + " leaving the critical region");
    }
    
    public void run() {
        enterNonCriticalRegion();
        
        try {
            semaphore.acquire();
            enterCriticalRegion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
    
}
