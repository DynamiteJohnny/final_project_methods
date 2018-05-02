import java.util.Random;

public class Person extends Thread {

    int id;
    Window w;


    public Person(int id, Window w){
        this.id = id;
        this.w = w;
    }

    public void setW(Window w) {
        this.w = w;
    }

    @Override
    public void run() {

        Random random = new Random();

        int randomTime = random.nextInt(3000 - 1000 + 1) + 1000;

        try {
            System.out.println("Person with id: " + this.id + " getting atteded at window: " + w.getId());
            Thread.sleep(randomTime);
            w.setBusy(false);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
