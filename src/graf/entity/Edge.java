package graf.entity;

public class Edge {
	private int value;
	private Node fNode;
	private Node sNode;
	
	public Edge(){
	}
	
	public Edge(Node f, Node s, int val){
		this.value=val;
		this.fNode=f;
		this.sNode=s;
	}
	
	public void setValue(int v){
		this.value=v;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setFNode(Node node){
		this.fNode=node;
	}
	
	public Node getFNode(){
		return fNode;
	}
	
	public void setSNode(Node node){
		this.sNode=node;
	}
	
	public Node getSNode(){
		return sNode;
	}
}
