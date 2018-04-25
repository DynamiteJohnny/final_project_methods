import java.util.Random;

public class Person extends Thread {

    int id;
    Door doorOne;
    Door doorTwo;
    
    public Person(int id, Door westDoor, Door eastDoor){
        this.id = id;
        this.doorOne = westDoor;
        this.doorTwo = eastDoor;
    }

    @Override
    public void run() {
        Random random = new Random();
        int randDoor = random.nextInt(2);

        if(randDoor == 0) {
            Thread t1 = new Thread(doorOne);
            t1.start();
            System.out.println("Person: " + id + " , got inside at: " + doorOne.getDoorName());
        }
        else{
            Thread t1 = new Thread(doorTwo);
            t1.start();
            System.out.println("Person: " + id + " , got inside at: " + doorTwo.getDoorName());
        }


        int randomTime = random.nextInt(3000 - 1000 + 1) + 1000;
        int randOutDoor;

        try {
            Thread.sleep(randomTime);
            randOutDoor = random.nextInt(2);
            if(randOutDoor == 0) {
                doorOne.personLeave();
                System.out.println("Person: " + id + " , left at: " + doorOne.getDoorName());
            } else {
                doorTwo.personLeave();
            }
        } catch (InterruptedException e) {
            randOutDoor = random.nextInt(2);
            if(randOutDoor == 0) {
                doorOne.personLeave();
                System.out.println("Person: " + id + " , leaving because of closing time at: " + doorOne.getDoorName());
            } else {
                doorTwo.personLeave();
                System.out.println("Person: " + id + " , leaving because of closing time at: " + doorTwo.getDoorName());
            }
        }
    }
}
