package core.sys;

import javax.naming.OperationNotSupportedException;

public class Clock {

    private long startTime=-1;
    private long endTime=-1;
    public Clock(){ }

    public void start(){
        this.endTime = -1;
        this.startTime= System.nanoTime();
    }
    public void stop(){
        if(this.startTime == -1) throw new UnsupportedOperationException("cannot stop clock");
        this.endTime = System.nanoTime();
    }

    public double getClockMill(){
        return (double)getClockNano() / 1_000_000D;
    }

    public long getClockNano(){
        if(startTime == -1 || endTime ==-1) throw  new UnsupportedOperationException("clock not started/stopped");
        return endTime - startTime;
    }

    public void end(){
        stop();
        System.out.println("Clock time: "+getClockMill()+" ms");
    }

}
