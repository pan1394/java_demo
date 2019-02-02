package datastruct;

public class PiorityQueue {

	private int size;
	private int nItems;
	private long[] a;

	public PiorityQueue(int maxSize){
		this.size = maxSize;
		a = new long[maxSize];
		nItems = 0;
	}

	public void add(long item){
		if(isFull()){
			throw new RuntimeException("队列满了.");
		}
		a[nItems] = item;
		nItems++;
		long v = item;
		for(int i=0; i< nItems; i++){
			Math.min(v, a[i]);
		}
	}

	public long remove(){
		if(isEmpty()){
			throw new RuntimeException("队列为空.");
		}
		return a[--nItems];
	}



	public boolean isFull() {
		return nItems == size;
	}

	public boolean isEmpty() {
		return nItems == 0;
	}

	public void display() {
		for(int i=0; i<nItems; i++){
			System.out.print(a[i]+ " ");
		}
		System.out.println();
	}
}
