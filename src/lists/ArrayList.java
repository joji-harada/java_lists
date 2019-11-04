package lists;
import java.util.NoSuchElementException;

import utility.Iterator;
import utility.List;

public class ArrayList<E> implements List<E>{
	private E[] data; //underlying array data structure
	private int size; //number of occupied positions
	
	
	public static final int DEFAULT_CAPACITY = 10;
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int minCapacity) {
		if(minCapacity > data.length) {
			int newLength = Math.max(2 * data.length + 1, minCapacity);
			
			E[] newArray = (E[]) new Object[newLength];
			
			
			/**************************************************
			 * copy: new array
			 *       loops n iterations, O(n).
			 *       previous array goes to garbage collection
			 * ************************************************/
			for(int i = 0; i < size; i++) {
				newArray[i] = data[i];
			}
			data = newArray;
		}
	}
	
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}
	
	public int indexOf(E element) {
		for (int i = 0; i < size; i++) {
			if(data[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
	
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	public E setIndex(int index, E element) {
		checkIndex(index);
		E replaced = data[index];
		data[index] = element;
		return replaced;
	}
	
	public int size() {
		return size;
	}
	
	public void add(int index, E element) {
		if(index != 0)
			checkIndex(index - 1);
		
		ensureCapacity(size + 1);
		
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;
	}
	
	public boolean add(E element) {
		int old_size = size;
		ensureCapacity(size + 1);
		data[size++] = element; //size++ is a post fixed operator so it operates after the line is executed 
		return size == old_size + 1;			
	}
	
	public E remove(int index) {
		checkIndex(index);
		E element = data[index];
		for (int i = index; i < size; i++) {
			data[i] = data[i+1];
		}
		data[--size] = null;
		return element;
	}
	
	public boolean remove(E element) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(element)) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(E value) {
		for(int i = 0; i <size; i++) {
			if (data[i].equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E set(int index, E element) {
		checkIndex(index);
		E item = data[index];
		data[index] = element;
		return item;
	}
	
	public void checkIndex(int index) {
		String message = "index: " + index + ", size: " + size;
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	public String toString() {
		if(size == 0) {
			return "[ ]";
		} else {
			String result = "[ " + data[0];
			for(int i = 1; i < size; i++) {
				result += ", " + data[i];
			}
			result += " ]";
			return result;
		}
	}

	private class ArrayIterator implements Iterator<E>{
		private int 		index;
		private boolean 	isRemovable;
		
		public ArrayIterator() {
			index 		= 0;
			isRemovable = false;
		}
		
		public boolean hasNext() {
			return index < size();
		}
		
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No elements left in list");
			}
			index++;
			isRemovable = true;
			return get(index - 1);
		}
		
		public void	remove() {
			if (!isRemovable) {
				throw new IllegalStateException("Unable to remove item");
			}
			ArrayList.this.remove(--index);
			isRemovable = false;
		}
	}
}
