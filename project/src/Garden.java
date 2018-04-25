import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Garden extends Thread{
    int visitors = 0;
    int secondsOpen;

    int maxPeopleInside = 30;
    //Doors of the garden
    List<Door> doors = new ArrayList<Door>();
    //Child threads
    private List<Thread> children = new ArrayList<Thread>();

    public Garden(int secondsOpen){
        this.secondsOpen = secondsOpen;
    }

        @Override
        public void run() {

        Counter counter = new Counter();

        //My program can have more than 2 gates.
        Door west = new Door("west", counter);
        Door east = new Door("east", counter);

        doors.add(west);
        doors.add(east);

        long t= System.currentTimeMillis();
        long end = t+(secondsOpen*1000);

        while(System.currentTimeMillis() < end) {

            //Receive visitors only if the maximum numbers of visitors inside hasn't reached the limit
            if(counter.getPeopleIn() < maxPeopleInside){
                Random random = new Random();
                int rand = random.nextInt(20 - 1 + 1) + 1;
                //5% chances of receiving visitors each iterations
                if(rand == 1){
                    Person person = new Person(visitors, west, east);
                    visitors++;
                    Thread t1 = new Thread(person);
                    children.add(t1);
                    t1.start();
                }
            }
        }
        System.out.println("\nCLOSING TIME...\n\n");

        System.out.println("People still inside: " + counter.getPeopleIn());
        System.out.println("Visitors today: " + visitors + "\n");

        //Interrupt visitors when it's closing time
        for (Thread child : children) {
            child.interrupt();
            try {
                //Wait until every visitors leave
                child.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\nPeople that got in from the east door: " + east.getNoPeopleIn());
        System.out.println("People that got out from the east door: " + east.getPeopleOut() + " \n");

        System.out.println("People that got in from the west door: " + west.getNoPeopleIn());
        System.out.println("People that got out from the west door: " + west.getPeopleOut() + "\n");

        System.out.println("\nPeople still inside: " + counter.getPeopleIn() + "\n");
        System.out.println("Visitors today: " + visitors + "\n");

    }

    public static void main(String args[]){


        Garden garden = new Garden(10);

        //I can run many gardens at the same time...
        Thread father = new Thread(garden);

        father.start();
    }

}

