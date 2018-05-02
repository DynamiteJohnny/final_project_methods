public class Window{
    int id;
    boolean busy = false;

    public Window(int id){
        this.id = id;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized boolean isBusy() {
        return busy;
    }

    public synchronized void setBusy(boolean busy) {
        this.busy = busy;
    }
}
