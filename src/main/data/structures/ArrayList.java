package main.data.structures;

import java.util.Iterator;

/**
 * Created by alecsiikaluoma on 3.10.2018.
 */
public class ArrayList<T> implements Iterable<T> {

    // T is object type

    private int size = 0;
    private Object array[];

    public ArrayList() {
        array = new Object[20];
    }

    // Takes a parameter of type t
    public void add(T t) {
        if(size == array.length) {
            growArray();
        }
        array[size] = t;
        size = size + 1;
    }

    public void addAll(ArrayList<T> list) {
        list.forEach(x -> add(x));
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        if (i>= size || i <0) {
            throw new IndexOutOfBoundsException("Index does not exist.");
        }
        T out = (T) array[i];
        return out;
    }

    private void growArray() {
        int s = array.length * 2;
        Object newArray[] = new Object[s];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new NewIterator();
    }

    class NewIterator implements Iterator<T> {

        private int index = 0;

        public boolean hasNext() {
            return index < size();
        }

        public T next() {
            return get(index++);
        }

        public void remove() {
            throw new UnsupportedOperationException("");
        }
    }

}
