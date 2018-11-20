package fcup;

public class BinaryTree 
{
	private class Node
	{
		public Node left, right;
		public int value;
		
		Node(int data)
		{
			value = data;
			left = null; 
			right = null;
		}

		// prints a tree in a cool way
		public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
			if(right!=null) {
		        right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
		    }
		    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value).append("\n");
		    if(left!=null) {
		        left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		    }
		    return sb;
		}
	}
	
	public Node root;	
	
	BinaryTree()
	{
		root = null;		
	}
	
	// constructs the tree
	public boolean add(int value) 
	{
		if(contains(value)) return false;
		root = add(root, value);
		return true;
	}
	
	
	private Node add(Node n, int data) 
	{
		if(n == null)
			return new Node(data);
		else if(data < n.value)
			n.left = add(n.left, data);
		else if(data > n.value)
			n.right = add(n.right, data);
		return n;
	}	
	
	// it only makes sense if the node's data are numeric
	public Node findMin(Node n) 
	{
		if(n == null) return null;
		
		if(n.left != null) 
			return findMin(n.left);
		else
			return n;		
	}
	
	
	public Node findMax(Node n) 
	{
		if(n == null) return null;
		
		if(n.right != null) 
			return findMax(n.right);
		else
			return n;		
	}
	
	public Node find(Node n, int value) 
	{
		if(n == null) return null;
		
		if(value < n.value)
			return find(n.left, value);
		else if(value > n.value)
			return find(n.right, value);
		else
			return n;
	}
	
	public boolean contains(int value) 
	{
		return contains(root, value);
	}
	
	
	private boolean contains(Node n, int value) 
	{
		if(n == null) return false;		
		if(n.value == value) return true;
		
		return value < n.value ? contains(n.left, value) : contains(n.right, value);
	}
	
	public boolean remove(int value) 
	{
		if(!contains(value)) return false;
		root = remove(root, value);
		return true;
	}
	
	private Node remove(Node n, int value) 
	{
		if(n == null) 
			return null;		
		else if(value < n.value)		
			n.left = remove(n.left, value);
		else if(value > n.value) 
			n.right = remove(n.right, value);
		else if(n.left == null)			
			n = n.right;
		else if(n.right == null) 
			n = n.left;
		else 
		{
			Node max = n.left;
			while(max.right != null) max = max.right;
			n.value = max.value;
			n.left  = remove(n.left, max.value);			
		}
		
		return n;		
	}
	
	public void printAll() {
		printAll(root);
	}
	
	private void printAll(Node n) 
	{
		if(n == null) return;
		System.out.print(n.value+"\t");
		printAll(n.left);
		System.out.println();
		printAll(n.right);
	}
	
	
	/** Number of nodes **/	
	public int numberNodes() {
		return numberNodes(root);
	}
	
	private int numberNodes(Node n) 
	{		
		if(n == null) return 0;				
		return 1 + numberNodes(n.left) + numberNodes(n.right);
	}
	
	private int[] result = new int[2];
	private int[] balancing(Node n, int val) {
		if(n.right == null && n.left == null) { result[0] = -1; result[1] = -1; return result;}
		else{
			result[0] = numberNodes(n.left);
			result[1] = numberNodes(n.right);
		}
		return result;
	}
	
	// counts the left child nodes and right child nodes of a node that has as data the given value
	public int[] balancing(int val) {
		Node n = find(root, val);
		return balancing(n, val);
	}
	
	/*********************/
	
	// returns the depth of the tree
	public int depth() 
	{
		return depth(root);
	}
	
	private int depth(Node n) 
	{
		if(n == null) return 0;
		return 1 + Math.max(depth(n.left), depth(n.right));
	}
	
	
	public int numberLeafs() 
	{
		return numberLeafs(root);
	}
	
	private int numberLeafs(Node n) 
	{
		if(n == null) return 0;
		if(n.left == null && n.right == null) return 1;
		
		return numberLeafs(n.left) + numberLeafs(n.right);
	}
	
	// is it a strict BTS (Binary Search Tree)
	public boolean strict() 
	{
		return strict(root);
	}
	
	private boolean strict(Node n) 
	{
		if(n == null) return false;
		
		if(n.left != null && n.right == null || n.left == null && n.right != null)
			return false;
		else if(n.left == null && n.right == null)
			return true;
		else if(n.left != null && n.right == null)
			return strict(n.left);
		else 
			return strict(n.right);
		
	}
	
	// Navigate through the tree
	public int path(String s) 
	{
		return path(root, s);
	}
	
	
	private int path(Node n, String s) 
	{						
		if(n.left == null && n.right == null)
			return n.value;		
		if(s.length() > 0) 
		{
			if(s.charAt(0) == 'E')			
				return path(n.left, s.substring(1));			
			else if(s.charAt(0) == 'D') 			
				return path(n.right, s.substring(1));
			else if(s.charAt(0) == 'R')
				return n.value;
		}else {
			return n.value;
		}	
		
		return -1;		
	}
	
	// level of a given node
	public int nodesLevel(int k) 
	{
		return nodesLevel(root, 0, k);
	}
	
	private int nodesLevel(Node n, int current, int desired) 
	{
		if(n == null) return 0;		
		if(current == desired) return 1;
		
		return nodesLevel(n.left, current+1, desired) + nodesLevel(n.right, current+1, desired);
	}
	
	public int numberOfNonLeafNodes() 
	{
		return numberOfNonLeafNodes(root);
	}
	
	private int numberOfNonLeafNodes(Node n) 
	{	
		if(n == null || (n.left == null && n.right == null))
			return 0;		
			
		return 1 + numberOfNonLeafNodes(n.left) + numberOfNonLeafNodes(n.right);		
	}
	
	public int leafLeastDepth() 
	{
		return leafLeastDepth(root, 0);
	}
	
	private int leafLeastDepth(Node n, int currentLevel) 
	{
		if(n == null) return -1;
		
		if(n.left == null && n.right == null)
			return n.value;
				
		return leafLeastDepth(n.left, currentLevel+1) + leafLeastDepth(n.right, currentLevel+1);
	}
	
	
	public void replaceValueInTree(int value, int newValue) 
	{
		replaceValueInTree(root, value, newValue);
	}
	
	private void replaceValueInTree(Node n, int value, int newValue) 
	{
		if(!contains(value)) return;		
		Node temp = find(n, value);		
		temp.value = newValue;
	}
	

	@Override
	public String toString() {
		return root.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}
	
	
	public int countBetween(int a, int b) 
	{
		return countBetween(root, a, b);
	}
	
	private int countBetween(Node n, int a, int b) 
	{
		if(n == null) return 0;
		
		if(n.value >= a && n.value <= b) 
		{
			return 1 + countBetween(n.left, a, b) + countBetween(n.right, a, b);
		}else if(n.value < a) {
			return countBetween(n.right, a, b);
		}else
			return countBetween(n.left, a, b);
	}
	
	
	public boolean isBST() {
		return isBST(root);
	}
	
	private boolean isBST(Node n) 
	{
		if(n == null) return false;
		
		if((n.left != null && n.left.value < n.value) || (n.right != null && n.right.value > n.value))					
			return true;	
		
		return isBST(n.left) || isBST(n.right);
	}
	
	public int countEven() {
		return countEven(root);
	}
	
	private int countEven(Node n) 
	{
		if(n == null) return 0;	
		
		if(n.value % 2 == 0)
			return 1 + countEven(n.left) + countEven(n.right);
		else
			return countEven(n.left) + countEven(n.right);
	}
	
	public int sumByLevel(int level) {		
		return sumByLevel(root, level);
	}
	
	private int sumByLevel(Node n, int level) 
	{
		if(level > depth() || n == null) return -1;		
		if(level == 0) return n.value; 
			
		return sumByLevel(n.left, level-1) + sumByLevel(n.right, level-1);		
	}	
	
}
