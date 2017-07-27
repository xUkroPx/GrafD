package graf.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import graf.entity.Edge;
import graf.entity.Node;
import javafx.scene.layout.Border;


public class GUIform {
	
	private JFrame window;
	private JPanel container;
	private JPanel menuPanel;
	private JPanel grafPanel;
	private JButton erase;
	private JButton createNode;
	private JButton createRel;
	private JButton invoke;
	Graphics g;
	
	private int nodeForEdgeCount = 0;
	
	public GUIform(){
		prepareGUI();
	}

         // ----------- Here we preparing our future GUI --------------
	
	private void prepareGUI() {
		window = new JFrame("GUI");
		window.setSize(700, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = new JPanel(new BorderLayout());
	    
	    // ----------- creating menu panel ----------------------------
	    
		menuPanel = new JPanel(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
	    menuPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		grafPanel = new JPanel();
		grafPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		menuPanel.setLayout(new BorderLayout());
		grafPanel.setLayout(new BorderLayout());
		
	//  window.setLayout(new BorderLayout(1,1));
	//	window.setLocationRelativeTo(null);
		grafPanel.addMouseListener(new CustomMouseListener());
		grafPanel.addMouseMotionListener(new CustomMouseMotionListener());

		// ------------------ Action on createNode button ---------------------
		
		createNode = new JButton("create node");
		createNode.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Draw.creatingNode==false||Draw.creatingEdge==true){
					Draw.creatingNode=true;
				}else{
					Draw.creatingNode=false;
				}
				Draw.creatingEdge=false;
			}
		});
		
		invoke = new JButton("invoke");
		invoke.setAlignmentX(Component.LEFT_ALIGNMENT);
		invoke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		erase = new JButton(" Erase graf  ");
		erase.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// ------------------ Action on erase button ---------------------
		
		erase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Draw.flag=false;
				Draw.nodes.clear();
				Draw.edges.clear();
				Draw.creatingEdge=false;
				Draw.creatingNode=false;
				grafPanel.repaint();
			}
		});
		
		createRel = new JButton("Rel creation");
		createRel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// ------------------ Action on createRel button ---------------------
		
		createRel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Draw.edge=new Edge();
				if(Draw.creatingEdge==false){
					Draw.creatingEdge=true;
				} else {
					Draw.creatingEdge=false;
				}
			}
		});
		
	    // ----------------------- Creating a box ----------------------------
		
		Box box = Box.createVerticalBox();
		box.add(createNode);
		box.add(createRel);
		box.add(erase);
		menuPanel.add(box);
		menuPanel.setBackground(Color.CYAN);
//		menuPanel.add(erase, BorderLayout.NORTH);
//		menuPanel.add(createRel, BorderLayout.CENTER);
		container.add(menuPanel, BorderLayout.LINE_START);
		container.add(grafPanel, BorderLayout.CENTER);
		window.getContentPane().add(container);
		window.setLocationRelativeTo(null);
	}
	
	public void start() {
		Draw dr = new Draw();
		grafPanel.add(dr);
		window.setVisible(true);
		container.setVisible(true);
		menuPanel.setVisible(true);
		grafPanel.setVisible(true);
		
	}
	
    // ------------------ Our mouse motion listener ---------------------------
	
	class CustomMouseMotionListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(Draw.dragNode){
				Draw.nodes.get(Draw.nodes.indexOf(Draw.hoveredNode)).setX(e.getX());
				Draw.nodes.get(Draw.nodes.indexOf(Draw.hoveredNode)).setY(e.getY());
			}
			grafPanel.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(Draw.flag){
			int mX = e.getX();
			int mY = e.getY();
			for(int i=Draw.nodes.size()-1;i>=0;i--){
				if(isHoverCircle(Draw.nodes.get(i), mX, mY)){
					Draw.hoveredNode = Draw.nodes.get(i);
					Draw.hoverCirc = true;
					break;
				}else{
					Draw.hoverCirc = false;
				}
			}
			if(Draw.drawEdge){
				Draw.x2=mX;
				Draw.y2=mY;
			}
			grafPanel.repaint();	
		}
			}
		
	}

    // --------------------------- Our mouse listener -----------------------
	
	class CustomMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			return;
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			return;
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(Draw.creatingEdge){
				if(Draw.hoverCirc){
					switch (nodeForEdgeCount){
					case 0: Draw.edge=new Edge();
						    Draw.edge.setFNode(Draw.hoveredNode);
						    Draw.x1=Draw.hoveredNode.getX();
						    Draw.y1=Draw.hoveredNode.getY();
						    Draw.x2=Draw.hoveredNode.getX();
						    Draw.y2=Draw.hoveredNode.getY();
						    Draw.drawEdge=true;
					        nodeForEdgeCount++;					        
					        break;
					case 1: Draw.edge.setSNode(Draw.hoveredNode);
					//------------------ here we checking, if we already have such edge -----------------
					        for(int i=0; i<Draw.edges.size();i++){
					        	if((Draw.edges.get(i).getFNode()==Draw.edge.getFNode()&&
					        			Draw.edges.get(i).getSNode()==Draw.edge.getSNode())||
					        			(Draw.edges.get(i).getFNode()==Draw.edge.getSNode()&&
					        			Draw.edges.get(i).getSNode()==Draw.edge.getFNode())){
					        		 Draw.drawEdge=false;
								        nodeForEdgeCount=0;
								        System.out.println("This Edge is lready exists. Edges: "+Draw.edges.size());
								        return;
					        	}
					        }
					        Draw.edges.add(Draw.edge);
					        //------- adding the Edge to nodes -------
					        Draw.nodes.get(Draw.nodes.indexOf(Draw.edge.getFNode())).addEdge(Draw.edge);
					        Draw.nodes.get(Draw.nodes.indexOf(Draw.edge.getSNode())).addEdge(Draw.edge);
					        Draw.drawEdge=false;
					        nodeForEdgeCount=0;
					        break;
					}
				} else{
					Draw.drawEdge=false;
			        nodeForEdgeCount=0;
				}
			//------------------------ creating node -------------------------
			}else if(Draw.creatingNode){
			if(Draw.hoverCirc){          //----- checking: if we hover node, can't create new
				Draw.dragNode=true;
				return;              
			}
			int mx = e.getX();
			int my = e.getY();
			System.out.println("x=" + mx + ", y=" + my + ".");
			Node node = new Node(Draw.nodes.size() + 1, mx, my);
			Draw.nodes.add(node);
			Draw.flag = true;
			}
			grafPanel.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(Draw.dragNode){
				Draw.dragNode=false;
			}
			return;
			
		}
		
	}
	
	private boolean isHoverCircle(Node node, int x, int y){
		int x0 = node.getX();
		int y0 = node.getY();
		
		return (x-x0)*(x-x0)+(y-y0)*(y-y0)<=13*13;
	}

}
