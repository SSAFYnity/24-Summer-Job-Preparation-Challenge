import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		Node root = new Node(Integer.parseInt(st.nextToken()));
		for(int i=1;i<N;i++) {
			Node temp = new Node(Integer.parseInt(st.nextToken()));
			Node start = root;
			while(true) {
				if(temp.compareTo(start)>0) {
					Node check = start.getRight();
					if(check==null) {
						start.setRight(temp);
						temp.setParent(start);
						break;
					}
					start = check;
				}
				else {
					Node check = start.getLeft();
					if(check==null) {
						start.setLeft(temp);
						temp.setParent(start);
						break;
					}
					start = check;
				}
			}
		}
		Node minNode = root;
		while(true) {
			Node check = minNode.getLeft();
			if(check==null) {
				break;
			}
			minNode = check;
		}
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int current = Integer.parseInt(st.nextToken());
				if(current>minNode.getData()) {
					Node currentNode = new Node(current);
					Node left = minNode.getLeft();
					Node right = minNode.getRight();
					Node parent = minNode.getParent();
					if(left==null && right == null) {
						if(parent!=null) {
							if(parent.getLeft()==minNode) parent.setLeft(null);
							if(parent.getRight()==minNode) parent.setRight(null);
						}
						else {
							root = null;
						}
					}
					else if(left==null) {
						right.setParent(parent);
						if(parent!=null) {
							if(parent.getLeft()==minNode) {parent.setLeft(right);}
							if(parent.getRight()==minNode) {parent.setRight(right);}
						}
						else {
							root = right;
						}
					}
					else if(right==null) {
						left.setParent(parent);
						if(parent!=null) {
							if(parent.getLeft()==minNode) {parent.setLeft(left);}
							if(parent.getRight()==minNode) {parent.setRight(left);}
						}
						else {
							root = left;
						}
					}
					else {
						right.setParent(parent);
						if(parent!=null) {
							if(parent.getLeft()==minNode) {parent.setLeft(right);}
							if(parent.getRight()==minNode) {parent.setRight(right);}
						}
						else {
							root = right;
						}
						while(true) {
							Node check = right.getLeft();
							if(check==null) {
								break;
							}
							right = check;
						}
						right.setLeft(left);
						left.setParent(right);
					}
					Node start = root;
					while(true) {
						if(currentNode.compareTo(start)>0) {
							Node check = start.getRight();
							if(check==null) {
								start.setRight(currentNode);
								currentNode.setParent(start);
								break;
							}
							start = check;
						}
						else {
							Node check = start.getLeft();
							if(check==null) {
								start.setLeft(currentNode);
								currentNode.setParent(start);
								break;
							}
							start = check;
						}
					}
					minNode = root;
					while(true) {
						Node check = minNode.getLeft();
						if(check==null) {
							break;
						}
						minNode = check;
					}
				}
			}
		}
		System.out.println(minNode.getData());
	}
}
class Node implements Comparable<Node>{
	int data = 0;
	Node parent = null;
	Node left = null;
	Node right = null;
	public Node(int data) {this.data = data;}
	public int getData() {return data;}
	public Node getLeft() {return left;}
	public Node getRight() {return right;}
	public void setLeft(Node n) {left = n;}
	public void setRight(Node n) {right = n;}
	public Node getParent() {return parent;}
	public void setParent(Node n) {parent = n;}
	@Override
	public int compareTo(Node n) {
		return data-n.data;
	}
}