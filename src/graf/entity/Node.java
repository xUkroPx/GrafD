package graf.entity;

import java.util.HashSet;
import java.util.Set;

public class Node {
	private int value;
	private String data;
	private Set<Edge> edges;
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Node(){
		this.value=-1;
	}
	
	public Node(int value){
		this.value=value;
		edges = new HashSet<Edge>();
	}
	
	public Node(int value, Set<Edge> edges){
		this.value=value;
		this.edges=edges;
	}
	
	public Node(int value, int x, int y){
		this.value=value;
		this.x=x;
		this.y=y;
		edges = new HashSet<Edge>();
	}
	
	public void setValue(int value){
		this.value=value;
		return;
	}
	
	public void setData(String data){
		this.data=data;
		return;
	}
	
	public void addEdge(Edge edge){
		this.edges.add(edge);
		return;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getData(){
		return data;
	}
	
	public Set<Edge> getEdges(){
		return edges;
	}
	
}
