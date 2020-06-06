package main.services;

import main.model.Outlet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to manage the outlets. It maintains a queue of all free outlets.
 */
public class OutletService {

    int outletCount;
    Queue<Outlet> outletsQueue;

    public OutletService(int outletCount) {
        this.outletCount = outletCount;
        outletsQueue = new LinkedList<>();
        for(int i=0; i<outletCount; i++) {
            outletsQueue.add(new Outlet(i));
        }
    }

    /**
     *
     * @return free outlet
     */
    public synchronized Outlet getFreeOutlet() {
        return outletsQueue.remove();
    }

    /**
     * Adds a free outlet to the queue
     * @param outlet
     */
    public synchronized void addFreeOutlet(Outlet outlet) {
        outletsQueue.add(outlet);
    }

    public int getOutletCount() {
        return outletCount;
    }
}
