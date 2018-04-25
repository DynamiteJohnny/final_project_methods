public class Counter {
    int peopleIn = 0;

    public synchronized void addOne(){
        peopleIn++;
    }

    public synchronized void substractOne(){
        peopleIn--;
    }

    public synchronized int getPeopleIn() {
        return peopleIn;
    }
}
