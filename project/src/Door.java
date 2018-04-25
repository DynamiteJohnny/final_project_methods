public class Door extends Thread{
    int noPeopleIn = 0;
    int peopleOut = 0;
    String name;

    Counter counter;

    public Door(String name, Counter counter){
        this.name = name;
        this.counter = counter;
    }

    public void run(){
        counter.addOne();
        noPeopleIn++;
    }

    public String getDoorName() {
        return name;
    }

    public synchronized void personLeave(){
        counter.substractOne();
        peopleOut++;
    }

    public synchronized int getPeopleOut() {
        return peopleOut;
    }

    public synchronized int getNoPeopleIn() {
        return noPeopleIn;
    }

}
