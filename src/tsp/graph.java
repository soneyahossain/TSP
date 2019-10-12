package tsp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class graph {
   
    int [][]x_y_coordinate;
    int N;
    int []solution;
    int []parent;

    public graph(int n,int [][]coordinat,int []paren,final int scale) {
        this.N=n;
        this.parent=paren;
        x_y_coordinate=new int [N][N];
        x_y_coordinate=coordinat;
        parent[0]=0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LineComponent lineComponent = new LineComponent(800,800);
                for (int i=0; i<N; i++) {
                    int x1=(x_y_coordinate[i][0]*scale)+300;
                    int y1=(x_y_coordinate[i][1]*scale)+300;
                    int x2=(x_y_coordinate[parent[i]][0]*scale)+300;
                    int y2=(x_y_coordinate[parent[i]][1]*scale)+300;
                  
                    lineComponent.addLine(x1,y1,x2,y2);
                }
                JOptionPane.showMessageDialog(null, lineComponent);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
    public graph(int n,int [][]coordinat,final int scale) {
        this.N=n;
        
        x_y_coordinate=new int [N][N];
        x_y_coordinate=coordinat;
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LineComponent lineComponent = new LineComponent(800,800);
                
               for(int k=0;k<N;k++)
               {
                    for (int i=0; i<N; i++) {
                        int x1=(x_y_coordinate[k][0]*scale)+300;
                        int y1=(x_y_coordinate[k][1]*scale)+300;
                        int x2=(x_y_coordinate[i][0]*scale)+300;
                        int y2=(x_y_coordinate[i][1]*scale)+300;

                        lineComponent.addLine(x1,y1,x2,y2);
                    }
            }
                JOptionPane.showMessageDialog(null, lineComponent);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
    public graph(int n,int []paren, int [][]coordinat,final int scale) {
        
        x_y_coordinate=new int [N][N];
        x_y_coordinate=coordinat;
        this.N=n;
        solution=new int[N];
        this.solution=paren;
   
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LineComponent lineComponent = new LineComponent(800,800);
                for (int i=0; i<N; i++) {
                    int x1=(x_y_coordinate[solution[i]][0]*scale)+300;
                    int y1=(x_y_coordinate[solution[i]][1]*scale)+300;
                    int x2=(x_y_coordinate[solution[i+1]][0]*scale)+300;
                    int y2=(x_y_coordinate[solution[i+1]][1]*scale)+300;
                    //System.out.print(x1);
                    //System.out.println(","+y1);
                    //System.out.print(x2);
                    //System.out.println(","+y2);
                    lineComponent.addLine(x1,y1,x2,y2);
                }
                JOptionPane.showMessageDialog(null, lineComponent);
            }
        };
        SwingUtilities.invokeLater(runnable);
    }    
}

class LineComponent extends JComponent {

    ArrayList<Line2D.Double> lines;
 

    LineComponent(int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Line2D.Double>();
       
    }

    public void addLine(int x,int y,int x1,int y1) {
       
        Line2D.Double line = new Line2D.Double(x,y,x1,y1);
           
        lines.add(line);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
      
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        Dimension d = getPreferredSize();
        g.setColor(Color.blue);
        g.drawLine(0,400,800,400);
         g.drawLine(400,0,400,800);
        for (Line2D.Double line : lines) {
            g.drawLine(
                (int)line.getX1(),
                (int)line.getY1(),
                (int)line.getX2(),
                (int)line.getY2());
              g.drawOval((int)line.getX1(),(int)line.getY1(), 10,10);
              g.drawOval((int)line.getX2(),(int)line.getY2(), 10,10);
              g.fillOval((int)line.getX1(),(int)line.getY1(), 10,10);
              g.fillOval((int)line.getX2(),(int)line.getY2(), 10,10);
             
             
        }
    }
}
