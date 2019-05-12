package binarytree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {

	public static int distance = 30;
	private int xpx;
	private int ypx;//保存首节点位置
	ArrayList<Point> points = new ArrayList<>();
	JPanel contentPane;
	public MyFrame(String title) {// 初始化界面
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		this.setContentPane(contentPane);

		this.setBounds(100, 100, 1000, 600);
		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		xpx = this.getWidth()/2-10;
		ypx = 30;

	}
	public void setXpx(int xpx) {
		this.xpx = xpx;
	}

	public void setYpx(int ypx) {
		this.ypx = ypx;
	}
	public int getXpx() {
		return xpx;
	}

	public int getYpx() {
		return ypx;
	}
	public Point addPoints(Object value,
								int xScale,
								int yScale,
								Point parentPoint){
		int x_position = xpx + distance*xScale;
		int y_position = ypx + distance * yScale;

		Point<Integer> point = new Point<>(x_position,
				y_position,
				value,
				parentPoint);
		points.add(point);
		return point;
	}
	class Point<T> {
		int x;
		int y;
		Object value;
		Point<T> parent;
		public Point(int x,int y,T value,Point<T> parent){
			this.x = x;
			this.y = y;
			this.value = value;
			this.parent = parent;
		}
	}

	public void draw(){
		JScrollPane jScrollPane = new JScrollPane();
		JPanel jPanel = new JPanel(){
			@Override
			public void paint(Graphics g) {
				points.forEach(x ->{
					g.drawString(String.valueOf(x.value),x.x,x.y);
					if(x.parent!=null){
						g.drawLine(
								x.x,
								x.y,
								x.parent.x,
								x.parent.y);
					}
				});
			}
		};
		//设置jPanel为透明，不然会显示错误
		jPanel.setOpaque(false);
		//用最后一个y的值大概模拟下高度，
		int height = 0;
		for (int i = 0; i < points.size(); i++) {
			if(height< points.get(i).y)height=points.get(i).y;
		}
		jPanel.setPreferredSize(new Dimension(900,height+100));
		jScrollPane.setViewportView(jPanel);
		contentPane.add(jScrollPane,BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void setHorize(int depth,boolean b,Object value){
		setYpx(getYpx()+(depth+1)*distance);
		if(b){
			Point point = new Point(0,getYpx()-50,value,new Point(getWidth(),getYpx()-50,0,null));
			points.add(point);
		}
	}

	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame("test");// 创建实例,初始化界面
		BinaryTree binaryTree = new BinaryTree<Integer>();
		binaryTree.insert(2);
		binaryTree.insert(3);
		binaryTree.showValueInWindows(myFrame);

		myFrame.draw();
		myFrame.setVisible(true);
	}
}
