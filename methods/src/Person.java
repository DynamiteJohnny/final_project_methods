import java.util.Random;

public class Person extends Thread {

    int id;
    Window w;

    long arrive_time = 0;
    long in_window_time = 0;
    long leave_time = 0;
    long leave_queue = 0;


    public Person(int id, Window w, long arrive_time){
        this.id = id;
        this.w = w;
        this.arrive_time = arrive_time;
    }

    public void setW(Window w, long time_going_to_window) {

        this.w = w;
        this.in_window_time = time_going_to_window;
    }


    public long getLeave_time() {
        return leave_time;
    }

    public long getLeave_queue() {
        return leave_queue;
    }

    @Override
    public void run() {

        Random random = new Random();

        int randomTime = random.nextInt(3000 - 1000 + 1) + 1000;

        try {
            System.out.println("Person with id: " + this.id + " getting atteded at window: " + w.getId());
            Thread.sleep(randomTime);
            w.setBusy(false);

            leave_queue = this.arrive_time - this.in_window_time;
            leave_time = System.currentTimeMillis() - this.arrive_time;

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
