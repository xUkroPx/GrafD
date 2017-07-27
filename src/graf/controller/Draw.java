package graf.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

import graf.entity.Edge;
import graf.entity.Node;
import graf.entity.Reversed;

public class Draw extends JComponent{
	
	public static int x;
	public static int y;
	public static boolean flag = false;        // global flag on drawing on canvas
	public static boolean dragNode = false;    // trigger for dragging node
	public static boolean hoverCirc = false;
	public static Node hoveredNode;
	public static boolean creatingEdge=false;
	public static boolean creatingNode = false;
	public static boolean drawEdge = false;   // flag, that we starting to draw our edge
	// --------------- Points for dynamic edge ---------
	public static int x1;
	public static int x2;
	public static int y1;
	public static int y2;
		
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	public static ArrayList<Edge> edges = new ArrayList<Edge>();
	public static Edge edge;
	
//	private Reversed reversed = new Reversed(nodes);
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//Drawing Edges
		if(!edges.isEmpty()&&flag){
			for(Edge e: edges){
			g2d.setColor(Color.blue);
			g2d.drawLine(e.getFNode().getX(), e.getFNode().getY(), e.getSNode().getX(), e.getSNode().getY());
			//------------- drawing arrow -----------
			
			double x = e.getSNode().getX()-20*Math.cos(Math.atan2((e.getSNode().getY()-e.getFNode().getY()), (e.getSNode().getX()-e.getFNode().getX()))+Math.PI/6);
			double y = e.getSNode().getY()-20*Math.sin(Math.atan2((e.getSNode().getY()-e.getFNode().getY()), (e.getSNode().getX()-e.getFNode().getX()))+Math.PI/6);
			g2d.draw(new Line2D.Double(e.getSNode().getX(), e.getSNode().getY(), x, y));
			x = e.getSNode().getX()-20*Math.cos(Math.atan2((e.getSNode().getY()-e.getFNode().getY()), (e.getSNode().getX()-e.getFNode().getX()))-Math.PI/6);
			y = e.getSNode().getY()-20*Math.sin(Math.atan2((e.getSNode().getY()-e.getFNode().getY()), (e.getSNode().getX()-e.getFNode().getX()))-Math.PI/6);
			g2d.draw(new Line2D.Double(e.getSNode().getX(), e.getSNode().getY(), x, y));
			}
		}
		if(drawEdge){
			g2d.setColor(new Color(244, 200, 66));
			g2d.drawLine(x1, y1, x2, y2);
		}
		if(flag){
			//Drawing all Nodes
			for(Node n: nodes){
				g2d.setColor(Color.blue);
				g2d.fillOval(n.getX()-13, n.getY()-13, 26, 26);
				g2d.setColor(Color.white);
				g2d.drawString(Integer.toString(n.getValue()), n.getX()-5, n.getY()+4);
			}
			//Making a red(?) hover effect
			if(hoverCirc&&!creatingEdge){
				//g2d.setColor(new Color(255, 51, 153));
				g2d.setColor(Color.black);
				g2d.drawOval(hoveredNode.getX()-13, hoveredNode.getY()-13, 26, 26);
				g2d.setColor(Color.white);
				g2d.drawString(Integer.toString(hoveredNode.getValue()), hoveredNode.getX()-5, hoveredNode.getY()+4);
			}
			//Making orange hover when choosing Nodes for Edge
			if(hoverCirc&&creatingEdge){
				g2d.setColor(new Color(244, 200, 66));
				g2d.fillOval(hoveredNode.getX()-13, hoveredNode.getY()-13, 26, 26);
				g2d.setColor(Color.white);
				g2d.drawString(Integer.toString(hoveredNode.getValue()), hoveredNode.getX()-5, hoveredNode.getY()+4);
			}
		
		}
	}
}