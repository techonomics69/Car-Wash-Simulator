public class BoundedQueue<T> implements BoundedQueueInterface<T> {
	protected T[] queue; 				 	 
	protected int numElements = 0; 	 	 
	protected int front       = 0;		
	protected int back; 						  

	public BoundedQueue(int size) {
		queue = (T[]) new Object[size];
		back  = size - 1;
	}

	public void enqueue(T element) {
		if (isFull())
			throw new QueueOverflowException("Enqueue attempted on a full queue.");
		else {
			back = (back + 1) % queue.length;
			queue[back] = element;
			numElements = numElements + 1;
		}
	}

	public T dequeue() {
		if (isEmpty())
			throw new QueueUnderflowException("Tried to dequeue on an empty queue");
		else {
			T type = queue[front];
			queue[front] = null;
			front = (front + 1) % queue.length;
			numElements = numElements - 1;
			return type;
		}
	}

	public boolean isEmpty() {
		return (numElements == 0);
	}

	public boolean isFull() {
		return (numElements == queue.length);
	}
}
