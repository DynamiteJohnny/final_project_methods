import java.util.Random;

public class Person extends Thread {

    int id;

    public Person(int id){
        this.id = id;
    }

    @Override
    public void run() {

        Random random = new Random();

        int randomTime = random.nextInt(3000 - 1000 + 1) + 1000;
        int randOutDoor;

        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
