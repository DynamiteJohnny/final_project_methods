import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread{
    int secondsOpen;
    int windowNumber;

    List<Window> winList = new ArrayList<Window>();

    public Queue(int secondsOpen, int windowNumber){
        this.secondsOpen = secondsOpen;
        this.windowNumber = windowNumber;

        for(int i = 0; i<windowNumber; i++){
            Window window = new Window(i);
            winList.add(window);
        }
    }

    @Override
    public void run() {

        long t= System.currentTimeMillis();
        long end = t+(secondsOpen*1000);

        while(System.currentTimeMillis() < end) {
            
        }
    }
}
