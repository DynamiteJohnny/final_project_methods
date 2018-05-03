import java.util.*;

public class Queue{
    int secondsOpen;
    int windowNumber;


    List<Window> winList = new ArrayList<Window>();

    java.util.Queue<Person> clientQueue = new LinkedList<>();

    int clientsToday = 0;

    public Queue(int secondsOpen, int windowNumber){
        this.secondsOpen = secondsOpen;
        this.windowNumber = windowNumber;

        for(int i = 0; i<windowNumber; i++){
            Window window = new Window(i);
            winList.add(window);
        }
    }

    public void run() {

        long t= System.currentTimeMillis();
        long end = t+(secondsOpen*1000);

        while(System.currentTimeMillis() < end) {
            Random random = new Random();
            int rand = random.nextInt(20 - 1 + 1) + 1;

            if(rand == 1) {
                Person person = new Person(clientsToday, null, System.currentTimeMillis());
                clientsToday++;
                clientQueue.add(person);
            }

            long attention_startTime = System.currentTimeMillis();
            int freeWindow = consultFreeWindow();

            if(freeWindow != -1 && !clientQueue.isEmpty()){
                Person personInTurn = clientQueue.remove();
                Window w = winList.get(freeWindow);
                personInTurn.setW(w,attention_startTime);
                w.setBusy(true);

                Thread th = new Thread(personInTurn);
                th.start();

                long queue_time = personInTurn.getLeave_queue();
                long leave_time = personInTurn.getLeave_time();
            }

        }
    }

    public int consultFreeWindow(){

        Iterator<Window> itr = winList.iterator();
        while (itr.hasNext()) {
            Window w = itr.next();
            if(!w.isBusy()){
                return w.getId();
            }
        }

        return -1;
    }

    public static void main(String args[]){
        Queue service = new Queue(15, 4);
        service.run();
    }
}