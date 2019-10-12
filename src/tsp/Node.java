/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author soneya
 */
public class Node {
    
    int id;
    Node [] children=new Node[10];
    public Node(int n)
    {
        id=n;
        for(int i=0;i<10;i++)
        {
           children[i]= null;
        }
       
        
    }
    
    
}
