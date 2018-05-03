import java.util.*;
import java.util.concurrent.TimeUnit;

public class Queue{
    int secondsOpen;
    int windowNumber;

    int miu;

    List<Window> winList = new ArrayList<Window>();

    java.util.Queue<Person> clientQueue = new LinkedList<>();

    int clientsToday = 0;

    public Queue(int secondsOpen, int windowNumber, int miu){
        this.secondsOpen = secondsOpen;
        this.windowNumber = windowNumber;
        this.miu = miu;

        for(int i = 0; i<windowNumber; i++){
            Window window = new Window(i);
            winList.add(window);
        }
    }

    public void run() throws InterruptedException {

        long t= System.currentTimeMillis();
        long end = t+(secondsOpen*1000);

        while(System.currentTimeMillis() < end) {
            Random random = new Random();

            float low = (float) (miu-(miu*0.30));
            float high = (float) (miu+(miu*0.30));

            long rand = (long) (low + random.nextFloat() * (high - low));

            TimeUnit.SECONDS.sleep(rand);

            Person person = new Person(clientsToday, null, System.currentTimeMillis());
            clientsToday++;
            clientQueue.add(person);

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

    public static void main(String args[]) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce miu(segundos) : ");
        int miu = sc.nextInt();

        Queue service = new Queue(15, 4, miu);
        service.run();
    }
}