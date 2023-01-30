package edu.caltech.cs2.datastructures;

import edu.caltech.cs2.interfaces.IFixedSizeQueue;

import java.util.Iterator;

public class CircularArrayFixedSizeQueue<E> implements IFixedSizeQueue<E> {

private E[] elementz;

private int size;
private int frontIndex;

    public CircularArrayFixedSizeQueue(int capacity) {
    this.elementz = (E[]) new Object[capacity];
    this.size= 0;      //prevent head from catching up with tail
    this.frontIndex = 0;
   // this.capacity = capacity;

    }

    @Override
    public boolean isFull() {
        if(size == elementz.length){
            return true;
        }
        return false;
    }

    @Override
    public int capacity() {
        return elementz.length;
    }

    @Override
    public boolean enqueue(E e) {
        if(isFull() == false){
            elementz[(frontIndex + size)  % elementz.length] = e;
            size = size + 1;
            return true;
        }
        return false;
    }

    @Override
    public E dequeue() {
        //
        if(size == 0){
             //   backIndex == -1 && frontIndex == 0)
            return null;
        }
        else{
            //get value at current front index, then increment to erase/set for new frontIndex
            E FrontIndexValue = elementz[frontIndex];
            frontIndex = (frontIndex + 1)  % elementz.length;
            size = size -1;
            return FrontIndexValue;

        }
    }

    @Override
    public E peek() {
        if(size == 0){
            return null;
        }
        else{
            //get value at current front index, but does not increment to erase/set for new frontIndex
            E FrontIndexValue = elementz[frontIndex % elementz.length];
            return FrontIndexValue;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        if(isFull() == false) {
            elementz[(frontIndex + size)  % elementz.length] = e;
            size = size + 1;
        }
    }

    @Override
    public void clear() {
        size = 0;
        frontIndex = 0;

    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iter = new Iterator<E>() {
            private int front = frontIndex;
            private int siz = size;

            @Override
            public E next() {
                E frontValue =  elementz[front];
                front = (front+ 1) % elementz.length;
                siz = siz - 1;

                return frontValue;

            }
            @Override
            public boolean hasNext() {
                if(siz == 0){
                    return false;
                }
                else{
                    return true;
                }


            }
        };
        return iter;
    }
    @Override
    public String toString()
    {
        if(size == 0){
            return "[]";
        }
        String result = "[";
        int index = frontIndex;
        int full = frontIndex + size;

        while(index < full) {
            result += elementz[index % elementz.length].toString() + ", ";
            index++;
        }

        result = result.substring(0, result.length() - 2);
        return result + "]";

    }

    }


