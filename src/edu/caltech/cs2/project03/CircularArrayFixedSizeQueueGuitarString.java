package edu.caltech.cs2.project03;

import edu.caltech.cs2.datastructures.CircularArrayFixedSizeQueue;
import edu.caltech.cs2.interfaces.IFixedSizeQueue;


import java.util.Random;


public class CircularArrayFixedSizeQueueGuitarString {
    private static final int SAMPLINGRATE = 44100;
    private IFixedSizeQueue string;
    private static final double EDF = 0.996;
    private static Random rand;
    public CircularArrayFixedSizeQueueGuitarString(double frequency) {
        this.rand = new Random();
        int capacity = (int) (SAMPLINGRATE/frequency);
        if(SAMPLINGRATE%frequency != 0){
           capacity = capacity + 1;
        }
        this.string = (IFixedSizeQueue) new CircularArrayFixedSizeQueue(capacity);
        for(int i = 0; i < capacity; i++) {
            string.enqueue(0.0);
        }
    }

    public int length() {
        return string.size();
    }

    public void pluck() {
    string.clear();
    for(int i = 0; i < string.capacity(); i++) {
        double randomValue = -0.5 + (0.5 + 0.5) * rand.nextDouble();
        string.enqueue(randomValue);
        }


    }

    public void tic() {
        double first = (double) string.dequeue();
        double val = (first + sample())/2.0;
        string.enqueue(val * EDF);
    }

    public double sample() {
        return (double) string.peek();
    }
}
