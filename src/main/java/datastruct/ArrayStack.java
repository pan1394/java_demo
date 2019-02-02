package datastruct;

public class ArrayStack {

	private int top;
	private int size;
	private int nItems;
	private long[] stack;

	ArrayStack(int maxSize) {
		this.size = maxSize;
		stack = new long[maxSize];
		top = -1;
	}

	public boolean isFull() {
		return nItems == size;
	}

	public boolean isEmpty() {
		return nItems == 0;
	}

	public void display() {
		int t = top;
		for(int i=0; i<nItems; i++){
			System.out.print(stack[t] + " ");
			t --;
		}
		System.out.println();
	}

	public void populate(long item){
		if(isFull()){
			throw new RuntimeException("stack is full!");
		}
		nItems ++;
		stack[++top] = item;
	}

	public long pop(){
		if(isEmpty()){
			throw new RuntimeException("stack is empty!");
		}
		nItems --;
		long ret = stack[top];
		top --;
		return ret;
	}

	public long peek() {
		if(isEmpty()){
			return 0;
		}
		return stack[top];
	}

	public static void main(String[] args) {
		ArrayStack s = new ArrayStack(5);
		s.populate(1);
		s.populate(2);
		s.populate(3);
		s.populate(4);
		s.display();
		System.out.println(s.pop());
		System.out.println(s.peek());
		s.display();
	}
}
