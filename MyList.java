
class MyList<E> implements ICollection<E>
{
	public Node<E> head = null;
	public Node<E> tail = null;
	public int size = 0;
	
	public static void main(String[] args) 
	{
		MyList<Integer> list = new MyList<Integer>();
		list.add(2);
		list.add(1);
		list.add(3);
		list.add(7);
		list.add(4);
		System.out.println(list.size());
		MyList.Node<Integer> iter = list.head;
		while(iter != null){
			System.out.println(iter.obj);
			iter = iter.next;
		}
		System.out.println();
	}

	public static class Node<E>
	{
		E obj;
		Node<E> prev, next;
		Node(E e){ obj=e; prev=null; next=null; }
	};
	

	@SuppressWarnings("unchecked")
	public boolean add( E e ){
		//empty list
		Node<E> node = new Node<E>(e);	
		if(this.size == 0){
			System.out.println("insert as first node");
			this.head = node;
			this.tail = node;
			++this.size;
			return true;
		}
		
		if ( e instanceof Comparable)
		{
			//System.out.println("Node is comparable");
			// small than head
			if(((Comparable<E>)e).compareTo(this.head.obj) < 0){
				System.out.println("insert as head");
				this.head.prev = node;
				node.next = this.head;
				this.head = node;
				this.size++;
				return true;
			}
			
			Node<E> iter = head.next;
			while(iter != null){
				if(((Comparable<E>)e).compareTo(iter.obj) >= 0){
					System.out.println(((Comparable<E>)e).compareTo(iter.obj));
					iter = iter.next;
				}else{
					System.out.println("insert at middle");
					node.prev = iter.prev;
					node.next = iter;
					iter.prev.next = node;
					iter.prev = node;
					this.size++;
					return true;
				}
			}
			System.out.println("append at tail");
			node.prev = this.tail;
			this.tail.next = node;
			this.tail = node;
			
		}else{ //append at tail
			node.prev = this.tail;
			this.tail.next = node;
			this.tail = node;
		}
		this.size++;
		return true;
	}
	public int size(){
		return this.size;
	}
	public boolean isEmpty(){
		return this.size == 0;
	}
	public void clear(){
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	//public boolean remove( E e ){
	//}
}

interface ICollection<E> {
	boolean add( E e );
	//boolean remove( E e);
	int size();
	boolean isEmpty();
	void clear();
	//boolean contains(E e);
	
}
