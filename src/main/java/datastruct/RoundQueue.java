package datastruct;

public class RoundQueue {

	private int front = 0;
	private int rear = -1;
	private int size = 0;   //数组尺寸
	private int nItems = 0; //实际大小
	private long[] queue;

	public RoundQueue(int maxSize){
		size = maxSize;
		queue = new long[maxSize];
		rear = -1;
		front = 0;
		nItems = 0;
	}

	public boolean isFull() {
		return nItems == size;
	}

	public boolean isEmpty() {
		return nItems == 0;
	}

	public long peek() {
		if(isEmpty()){
			return 0;
		}
		return queue[front];
	}

	public long add(long item) {
		if(isFull()) {
			throw new RuntimeException("队列满了.");
		}
		nItems++;
		rear = ++rear % size;
		queue[rear] =  item;
		return queue[rear];
	}

	public long remove() {
		if(isEmpty()){
			throw new RuntimeException("队列空了");
		}
		nItems--;
		long ret = queue[front];
		front = ++front % size;
		return ret;
	}

	public void display(){
		int item = front;
		for(int i=0; i<nItems; i++){
			System.out.print(queue[item % size] + " ");
			item ++;
		}
		System.out.println();
	}


	public static void main(String[] args){
		RoundQueue queue = new RoundQueue(5);
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.display();
		queue.remove();
		queue.display();
		queue.add(6);
		queue.display();
		queue.remove();
		queue.remove();
		queue.add(7);
		queue.add(8);
		queue.display();

		queue.remove();
		queue.remove();
		queue.remove();
		queue.remove();
		queue.remove();

	}
}
