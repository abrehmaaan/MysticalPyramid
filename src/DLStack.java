public class DLStack<T> implements DLStackADT {
	private DoubleLinkedNode top;
	private int numItems;
	
	public DLStack() {
		top = null;
		numItems = 0;
	}
//	public void push(T dataItem) { 
//		
//		DoubleLinkedNode<T> temp = new DoubleLinkedNode<T>(dataItem);
//		if(top == null) {
//			temp.setNext(temp);
//			temp.setPrevious(null);
//			top = temp;
//		}
//		else {
//			temp.setPrevious(top);
//			temp.setNext(top.getNext());
//			temp.setNext(temp);
//			top = temp;
//		}
//		numItems++;
//	}
	public T pop() throws EmptyStackException{ 
		//Removes and returns the data item at the top of the stack. An EmptyStackException is thrown if the stack is empty.
		if(top==null) {
			throw new EmptyStackException("Stack is empty.");
		}
		else {
			T data = (T) top.getElement();
			DoubleLinkedNode<T> temp = top.getPrevious();
			if(temp != null) {
				temp.setNext(top.getNext());
			}
			top = temp;
			numItems--;
			return data;
		}
	}
	public T pop(int k) throws InvalidItemException{ 
		if(top!=null) {
			if(k>numItems || k<=0) {
				throw new InvalidItemException("Invalid Item");
			}
			else {
				DoubleLinkedNode<T> ptr = top;
				if(k==1) {
					return pop();
				}
				else {
					for(int i = 2;i<k;i++) {
						ptr = ptr.getPrevious();
					}
					T data = ptr.getPrevious().getElement();
					ptr.setPrevious(ptr.getPrevious().getPrevious());
					if(ptr.getPrevious()!=null) {
						ptr.getPrevious().setNext(ptr);
					}
					numItems--;
					return data;
				}
			}
		}
		else {
			return null;
		}
	}
	public T peek() throws EmptyStackException{
		if(top==null) {
			throw new EmptyStackException("Stack is empty.");
		}
		else {
			return (T) top.getElement();
		}
	}
	public boolean isEmpty() {
		return top==null;
	}
	public int size() {
		return numItems;
	}
	public DoubleLinkedNode getTop() {
		return top;
	}
	public String toString() {
		if(isEmpty()) {
			return "";
		}
		String data = "[ ";
		DoubleLinkedNode<T> ptr = top;
		for(int i = 1;i<=numItems;i++) {
			data += String.valueOf(ptr.getElement())+" ";
			ptr = ptr.getPrevious();
		}
		data += "]";
		return data;
	}
	@Override
	public void push(Object element) {
		DoubleLinkedNode<T> temp = new DoubleLinkedNode<T>((T)element);
		if(top == null) {
			temp.setNext(temp);
			temp.setPrevious(null);
			top = temp;
		}
		else {
			temp.setPrevious(top);
			temp.setNext(top.getNext());
			top.setNext(temp);
			top = temp;
		}
		numItems++;
		
	}
	public static void main(String[] args) {
		
	}
}
