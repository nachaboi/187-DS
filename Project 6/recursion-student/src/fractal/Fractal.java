package fractal;

import fractal.Turtle;

import java.awt.*;
import java.awt.geom.*;

/**
 * A class that implements some basic fractal drawing methods using recursion.
 * These methods include the Koch curve, tree, Sierpinski triangle and carpet.
 */
public class Fractal {
	private Graphics2D g2d = null;				// a Graphics2D object as canvas for drawing
	private int width=0, height=0;				// image width and height
	private int max_recursion_level = 0;		// maximum recursion level
	private String fractal_type = "Koch Curve";	// type of fractal
	private Color color = Color.green;			// draw color
	
	public void setGraphics(Graphics g, int w, int h)	{
		g2d = (Graphics2D)g; width = w; height = h;
	}
	public void setFractalType(String t)	{ fractal_type = t; }
	public void setColor(Color c)			{ color = c; }
	public void setMaxRecursion(int n)		{ max_recursion_level = n; }

	// Recursive method for drawing the Koch curve given two points and the recursion level
	private void drawKochCurve(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
		// TODO
		// Koch subdivision rule: ___ ->  _/\_
		
		double xD = p2.getX() - p1.getX();
		double yD = p2.getY() - p1.getY();
		
		double distance = Math.sqrt((xD*xD) + (yD*yD));
//		
//		Point2D.Double first = new Point2D.Double(xD/3.0, yD/3.0);
//		Point2D.Double second = new Point2D.Double(xD/2.0, (yD + Math.sqrt((xD*xD)/12)));
//		Point2D.Double third = new Point2D.Double((xD*2.0)/3.0, (yD*2.0)/3.0);
//		
//		drawKochCurve(p1, first, level-1);
//		drawKochCurve(first, second, level-1);
//		drawKochCurve(second, third, level-1);
//		drawKochCurve(third, p2, level-1);
		
		Turtle crawler = new Turtle(p1, p2);
		
		//first
		Point2D point1 = p1;
		crawler.move(distance/3.0);
		Point2D point2 = crawler.getPosition();
		drawKochCurve(point1, point2, level-1);
		//second
		point1 = crawler.getPosition();
		crawler.turnLeft(60);
		crawler.move(distance/3.0);
		point2 = crawler.getPosition();
		drawKochCurve(point1, point2, level-1);
		//third
		point1 = crawler.getPosition();
		crawler.turnRight(120);
		crawler.move(distance/3.0);
		point2 = crawler.getPosition();
		drawKochCurve(point1, point2, level-1);
		//fourth
		point1 = crawler.getPosition();
		point2 = p2;
		drawKochCurve(point1, point2, level-1);
	}
	
	// Recursive method for drawing a fractal Tree given two points and the recursion level
	private void drawTree(Point2D p1, Point2D p2, int level) {
		if(level==0) {	// base case
			drawLine(p1, p2);
			return;
		}
		// TODO
		
		double xD = p2.getX() - p1.getX();
		double yD = p2.getY() - p1.getY();
		
		double distance = Math.sqrt((xD*xD) + (yD*yD));
		
		Turtle crawler = new Turtle(p1, p2);
		
		//first
		Point2D point1 = crawler.getPosition();
		crawler.move(distance/3.0);
		Point2D point2 = crawler.getPosition();
		drawLine(point1, point2);
		//second
		point1 = crawler.getPosition();
		crawler.turnLeft(60.0);
		crawler.move((2*distance)/3.0);
		point2 = crawler.getPosition();
		drawTree(point1, point2, level-1);
		//third
		crawler = new Turtle(p1, p2);
		crawler.move(distance/3.0);
		point1 = crawler.getPosition();
		crawler.turnRight(15.0);
		crawler.move((2*distance)/3.0);
		point2 = crawler.getPosition();
		drawTree(point1, point2, level-1);
		
		
	}
	
	// Recursive method for drawing the Sierpinski Triangle given the three points
	// that define the triangle and the recursion level
	private void drawSierpinskiTriangle(Point2D p1, Point2D p2, Point2D p3, int level) {
		if(level==0) {	// base case
			drawTriangle(p1, p2, p3);
			return;
		}
		// TODO
		
		Point2D pointA = new Point2D.Double((p1.getX() + p3.getX())/2.0, (p1.getY() + p3.getY())/2.0);
		Point2D pointB = new Point2D.Double((p1.getX() + p2.getX())/2.0, (p1.getY() + p2.getY())/2.0);
		Point2D pointC = new Point2D.Double((p2.getX() + p3.getX())/2.0, (p2.getY() + p3.getY())/2.0);
		drawSierpinskiTriangle(p1, pointA, pointB, level-1);
		drawSierpinskiTriangle(p2, pointB, pointC, level-1);
		drawSierpinskiTriangle(p3, pointA, pointC, level-1);
//		drawSierpinskiTriangle(pointC, pointA, pointB, level-1);


		
	}
	
	// Recursive method for drawing the Sierpinski Carpet given the lower-left corner
	// of the square (p), the side length (a) of the square, and the recursion level
	private void drawSierpinskiCarpet(Point2D p, double a, int level) {
		if(level==0) {	// base case
			drawRectangle(p, a, a);
			return;
		}
		// TODO
		
		
		double pX = p.getX();
		double pY = p.getY();
		double newSideLength = a/3.0;
		
		Point2D pointE = new Point2D.Double(pX, pY+((2.0*a)/3.0));
		Point2D pointF = new Point2D.Double(pX+(a/3.0), pY+(2.0*a)/3.0);
		Point2D pointG = new Point2D.Double(pX+(2.0*a)/3.0, pY+(2.0*a)/3.0);
		Point2D pointI = new Point2D.Double(pX, pY+(a)/3.0);
		Point2D pointK = new Point2D.Double(pX+(2.0*a)/3.0, pY+(a)/3.0);
		Point2D pointM = p;
		Point2D pointN = new Point2D.Double(pX+(a)/3.0, pY);
		Point2D pointO = new Point2D.Double(pX+(2.0*a)/3.0, pY);
		
		drawSierpinskiCarpet(pointE, newSideLength, level-1);
		drawSierpinskiCarpet(pointF, newSideLength, level-1);
		drawSierpinskiCarpet(pointG, newSideLength, level-1);
		drawSierpinskiCarpet(pointI, newSideLength, level-1);
		drawSierpinskiCarpet(pointK, newSideLength, level-1);
		drawSierpinskiCarpet(pointM, newSideLength, level-1);
		drawSierpinskiCarpet(pointN, newSideLength, level-1);
		drawSierpinskiCarpet(pointO, newSideLength, level-1);
		
//		double x = (double)(2.0*pX*a)/3.0;
//		double y = (double)(2.0*pY*a)/3.0;
//		
//		double testdis = a/3.0;
//		
//		Point2D point = new Point2D.Double(.5,.5);
//		
//		drawSierpinskiCarpet(point, testdis, level-1);

		
		
		
	}
	
	// This method is left for you to experiment with creative fractals
	// designed by yourself. You will NOT be graded on this method 
	void drawMyFractal(/* other parameters that you may need */ int level) {
		if(level==0) {	// base case
			return;
		}
		/* Your creative fractal shape */
	}
	
	/** The code below provides utility methods.
	 *  You should NOT need to modify any code below.
	 */
	public void draw() {
		if(g2d==null || width==0 || height==0) return;
		g2d.setBackground(Color.black);
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(color);
		if(fractal_type.equalsIgnoreCase("Koch Curve")) {
			drawKochCurve(new Point2D.Double(0.0, 0.4), new Point2D.Double(1.0, 0.4), max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Snowflake")) {
			double r = 0.5;
			Point2D p1 = new Point2D.Double(r*Math.cos(Math.toRadians(150))+0.5,
											r*Math.sin(Math.toRadians(150))+0.5);
			Point2D p2 = new Point2D.Double(r*Math.cos(Math.toRadians(30))+0.5,
											r*Math.sin(Math.toRadians(30))+0.5);
			Point2D p3 = new Point2D.Double(r*Math.cos(Math.toRadians(-90))+0.5,
											r*Math.sin(Math.toRadians(-90))+0.5);
			// Snowflake is made of three Koch curves segments
			//  p1____p2
			//    \  /
			//     \/
			//     p3
			drawKochCurve(p1, p2, max_recursion_level);
			drawKochCurve(p2, p3, max_recursion_level);
			drawKochCurve(p3, p1, max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Tree")) {
			// double the maximum recursion level because tree subdivides very slowly
			drawTree(new Point2D.Double(0.6, 0.1), new Point2D.Double(0.6, 0.9),
					max_recursion_level*2);
		} else if(fractal_type.equalsIgnoreCase("Sp Triangle")) {
			double r = 0.5;
			Point2D p1 = new Point2D.Double(r*Math.cos(Math.toRadians(90))+0.5,
											r*Math.sin(Math.toRadians(90))+0.5);
			Point2D p2 = new Point2D.Double(r*Math.cos(Math.toRadians(-150))+0.5,
											r*Math.sin(Math.toRadians(-150))+0.5);
			Point2D p3 = new Point2D.Double(r*Math.cos(Math.toRadians(-30))+0.5,
											r*Math.sin(Math.toRadians(-30))+0.5);
			drawSierpinskiTriangle(p1, p2, p3, max_recursion_level);
		} else if(fractal_type.equalsIgnoreCase("Sp Carpet")) {
			drawSierpinskiCarpet(new Point2D.Double(0, 0), 1, max_recursion_level);
		} else {
			drawMyFractal(/* other parameters that you may need */ max_recursion_level);
		}
	}
	/** Draw a straight line between two points P1 and P2.
	 * The given x and y values of p1 and p2 are assumed to be within [0, 1] (i.e. normalized).
	 * This allows our fractals to be resolution-independent. The method below converts the normalized
	 * coordinates to integer coordinates based on the actual image resolution. 
	 */
	private void drawLine(Point2D p1, Point2D p2) {
		int x1 = (int)(p1.getX()*width);
		int x2 = (int)(p2.getX()*width);
		// flip the Y coordinate
		// because screen Y axis is flipped from mathematical Y axis
		int y1 = (int)((1.0-p1.getY())*height);
		int y2 = (int)((1.0-p2.getY())*height);
		g2d.drawLine(x1, y1, x2, y2);
	}
	
	// Draw a solid rectangle given its lower left corner and its size
	private void drawRectangle(Point2D p, double w, double h) {
		int x0 = (int)(p.getX()*width);
		int y0 = (int)((1.0-p.getY())*height);
		int x1 = (int)((p.getX()+w)*width);;
		int y1 = (int)((1.0-(p.getY()+h))*height);
		int xpoints[] = {x0, x0, x1, x1};
		int ypoints[] = {y0, y1, y1, y0};
		g2d.fillPolygon(xpoints, ypoints, 4);
	}
	
	// Draw a solid triangle given its three vertices
	private void drawTriangle(Point2D p1, Point2D p2, Point2D p3) {
		int xpoints[] = {(int)(p1.getX()*width),
						 (int)(p2.getX()*width),
						 (int)(p3.getX()*width)};
		int ypoints[] = {(int)((1.0-p1.getY())*height),
						 (int)((1.0-p2.getY())*height),
						 (int)((1.0-p3.getY())*height)};
		g2d.fillPolygon(xpoints, ypoints, 3);
	}
}
