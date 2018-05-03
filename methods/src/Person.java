import java.util.Random;

public class Person extends Thread {

    int id;
    Window w;

    long arrive_time = 0;
    long in_window_time = 0;
    long leave_time = 0;
    long leave_queue = 0;

    int lambda;

    public Person(int id, Window w, long arrive_time, int lambda){
        this.id = id;
        this.w = w;
        this.arrive_time = arrive_time;
        this.lambda = lambda;
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

        float low = (float) (lambda-(lambda*0.30));
        float high = (float) (lambda+(lambda*0.30));

        long rand = (long) (low + random.nextFloat() * (high - low));

        try {
            System.out.println("Person with id: " + this.id + " getting atteded at window: " + w.getId());
            Thread.sleep(rand);
            w.setBusy(false);

            leave_queue = this.arrive_time - this.in_window_time;
            leave_time = System.currentTimeMillis() - this.arrive_time;

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
