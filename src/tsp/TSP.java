/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author soneya
 */
public class TSP {

    int numOfpoints;
    int [][] coordinate;
     double[][] weight;
     int []parent;
    double [] key;
    Node []nodes;
    int [] solution;
    int index=0;
    double cost=0;

    public TSP(int N, Scanner input){
        numOfpoints=N;
        weight= new double [N][N];
        coordinate=new int [N][2];
        key=new double [N];
        parent=new int [N];
        nodes=new Node[N];
        solution= new int[N+1];
      
        
        input(input);
    }
   private void input(Scanner input)
   {
       String in;
       
       for (int i=-1;i<numOfpoints+1;i++)
       {
            in = input.nextLine();
       
            StringTokenizer strToken = new StringTokenizer(in);
            int count = strToken.countTokens();
         
            for(int x = 0;x < count;x++){
                  coordinate[i][x] = Integer.parseInt((String)strToken.nextElement());
                  
            }
         
       }
   }
    void calculate_distance()
    {
        for (int i=0;i<numOfpoints;i++)
        {
            for(int j=0;j<numOfpoints;j++)
            {
                if(i==j) {
                    weight[i][j]=0;
                }
                else 
                {
                    int x=(coordinate[i][0]-coordinate[j][0]);
                    int y=(coordinate[i][1]-coordinate[j][1]);
                    int d= (x*x)+(y*y);
                    double c =Math.sqrt(d);
                    c=TSP.round(c, 2);
                    weight[i][j]=c;
                }
            }
        }
    }
    public static double round(double value, int places) {
    if (places < 0) {
            throw new IllegalArgumentException();
        }

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
    void MST_prims()
    {
            for (int j = 0; j < numOfpoints; j++) 
            {
                key[j]=1000000000;
                parent[j]=-1;

            }
            key[0]=0;
           
            

            for (int j = 0; j < numOfpoints; j++) 
            {
               int node=min_priority_queue();
              
               for (int k = 0; k < numOfpoints; k++) 
               {
                  if((key[k]!=-10) && (weight[node][k]<key[k]))
                  {
                     
                      parent[k]=node;
                      key[k]=weight[node][k];
                  }
               }
               
            }
    }
    void print_key()
    {
       for (int j = 0; j < numOfpoints; j++) 
       { 
           System.out.println(key[j]);
       } 
    }
    void print_parent()
    {
        for (int j = 0; j < numOfpoints; j++) 
       {
           System.out.println(parent[j]);
       }
    }
    int min_priority_queue()
    {
            int min_index=-1;
            double min_cost=1000000;
            for (int j = 0; j < numOfpoints; j++) 
            {
                
                if((key[j]<min_cost) && (key[j]>=0)) {
                  
                    min_index=j;
                    min_cost=key[j];
                }
            }
            
            key[min_index]=-10;
            return min_index;
    }
  public void print_array()
 { 
     
     for (int i = 0; i < numOfpoints; i++) 
     {
            
            for (int j = 0; j < numOfpoints; j++) 
            {
                System.out.print(weight[i][j]+" ");

            }
            System.out.println(" ");
     }
                 
 }
  
    void createTree()
    {
       
            for (int j = 0; j < numOfpoints; j++) 
            {
                nodes[j]=new Node(j);
                int sequence=0;
                
                for (int k = 0; k < numOfpoints; k++) 
                {
                    if(parent[k]==j)
                    { 
                        Node Node1=new Node(k);
                        nodes[j].children[sequence]=Node1;
                        
                        sequence++;
                    }
                   
                }
                
            
            }
        System.out.print("solution: ");
        preOrderWalk(nodes[0]);
        //System.out.println(nodes[0].id);
        solution[index]=0;
    }
    void preOrderWalk(Node root)
    {
        if(root != null) {  
           
        //System.out.print(root.id+" ");
        solution[index++]=root.id;
        int i=0;
        while(root.children[i]!=null)
        {
            preOrderWalk(nodes[root.children[i].id]);
            i++;
        }
        
    }  


    }
    
     void print_solution()
    {
        for(int j=0;j<solution.length;j++)
        {
           System.out.print(solution[j]+" ");
        }
    }
     void calculate_cost()
     { double cost=0;
         for(int i=0;i <solution.length-1 ; i++){
          cost=cost+weight[solution[i]][solution[i+1]];
      }
       System.out.println("cost:  "+cost); 
       this.cost=cost;
     }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        final int sta_Time =(int) System.currentTimeMillis();

        Scanner input = new Scanner(new File("input_7.txt"));
        int data_size = input.nextInt();
     
        
        TSP instance= new TSP(data_size,input);
        instance.calculate_distance();
        instance.print_array();
        instance.MST_prims();
        instance.print_parent();
        instance.createTree();
        instance.print_solution();
        instance.calculate_cost();
        graph solution_cycle = new graph(data_size,instance.solution,instance.coordinate,8);
        graph MST_tree = new graph(data_size,instance.coordinate,instance.parent,8);
        graph graph = new graph(data_size,instance.coordinate,8);
        final int end_Time =(int) System.currentTimeMillis();
        int duration=end_Time-sta_Time;
        
        System.out.println("exectution time : "+duration+" millisecond");  
        File dir=new File(".");
        File dir1=new File(".");
        String location= dir.getCanonicalPath()+File.separator+ "approx_1005105.txt";
        String location1= dir1.getCanonicalPath()+File.separator+ "approx_cost_1005105.txt";

        FileWriter f=new FileWriter(location,true);
        FileWriter f1=new FileWriter(location1,true);
        BufferedWriter b= new BufferedWriter(f);
        BufferedWriter b1= new BufferedWriter(f1);
        b.write(Integer.toString(data_size));
        b.write("  ");
        b1.write(Integer.toString(data_size));
        b1.write("  ");
        b.write(Integer.toString(duration));
        b1.write(Double.toString(instance.cost));
        b.newLine();
        b1.newLine();
        b.close();
        b1.close();
        
       
       
    }
}
