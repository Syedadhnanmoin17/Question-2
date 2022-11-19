import java.util.LinkedList;
import java.util.Scanner;

public class Q2 {
    static class Edge{
        String src;
        String dest;
        int w;
        Edge(String s,String d,int weight)
        {
            this.src=s;
            this.dest=d;
            this.w=weight;
        }
    }
    static class Graph{
        int v;
        LinkedList<Edge>[] adjarr;
        int p;
        int totaldist=0;
        Graph(int vert)
        {
            this.v=vert;
            adjarr=new LinkedList[vert];
            for(int i=0;i<vert;i++) adjarr[i]=new LinkedList<>();
        }
        public void addEdge(String s,String d,int weight)
        {
            Edge edg1=new Edge(s,d,weight);
            Edge edg2=new Edge(d,s,weight);
            int pos1=(int)(s.charAt(0))-65;
            int pos2=(int)(d.charAt(0))-65;
            adjarr[pos1].addFirst(edg1);
            adjarr[pos2].addFirst(edg2);
        }
        public double calcAvg(String x,String y)
        {
            boolean vis[]=new boolean[v];
            for(int i=0;i<v;i++)
                vis[i]=false;
            p=0;
            totaldist=0;
            vis[(int)(x.charAt(0))-65]=true;
            calculate(x,y,vis,0);
            return ((double)totaldist/(double) p);
        }
        public void calculate(String x,String y,boolean visited[],int dist)
        {
            if(x.equals(y))
            {
                p++;
                totaldist+=dist;
                return;
            }
            int pos=(int)(x.charAt(0))-65;
            for(int i=0;i<adjarr[pos].size();i++)
            {
                String t=adjarr[pos].get(i).dest;
                int posdest=(int)(t.charAt(0))-65;
                if(visited[posdest]==false)
                {
                    visited[posdest]=true;
                    calculate(t,y, visited, dist+adjarr[pos].get(i).w);
                    visited[posdest]=false;
                }
            }
            return;
        }
    }
    public static void main(String[] args)
    {
        Graph g=new Graph(5);
        g.addEdge("A","B",12);
        g.addEdge("A","C",13);
        g.addEdge("A","D",11);
        g.addEdge("A","E",8);
        g.addEdge("B","C",3);
        g.addEdge("C","E",4);
        g.addEdge("D","E",7);
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter Node One");
        String x=scan.next();
        System.out.println("Enter Node Two");
        String y= scan.next();
        double avgd=g.calcAvg(x,y);
        System.out.println("Average Distance between them is "+avgd);
    }
}