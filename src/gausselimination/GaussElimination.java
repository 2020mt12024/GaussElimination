/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gausselimination;

import java.util.Arrays;

/**
 *
 * @author SSMOHANTA
 */
public class GaussElimination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[][] aAug = {
            {0, 8, 2, -7},
            {3, 5, 2, 8},
            {6, 2, 8, 26}
        }; 
        //System.out.println(Arrays.deepToString(aAug));        
        double[] sol = gaussElim(aAug);
        System.out.println("\nBacksubstituted Solution: x");
        System.out.println(Arrays.toString(sol));
    }
    
    public static double[] gaussElim(double[][] aAgmt){        
        int n = aAgmt.length;
        double[] x = new double[n];
        double[] temp = new double[n+1];
        double backsubsum = 0;
        double rowminus = 0;
        System.out.println("Augmentaed Matrix");
        for(int a=0;a<n;++a){
            System.out.println(Arrays.toString(aAgmt[a]));
        }
        System.out.println("");
        for(int k = 1; k < n; ++k){
            int m = k;//assume pivot at beginning
            
            //Look up for pivot
            for(int j = k+1; j <= n; ++j){
                if(Math.abs(aAgmt[m-1][k-1]) < Math.abs(aAgmt[j-1][k-1])) m = j;
            }
            
            if(aAgmt[m-1][k-1] == 0){
                System.out.println("No Unique Solution");
                return x;
            }else{
                temp = aAgmt[k-1];
                aAgmt[k-1] = aAgmt[m-1];
                aAgmt[m-1] = temp;
                
                System.out.println("Row #"+k+" partial pivoted");
                for(int a=0;a<n;++a){
                    System.out.println(Arrays.toString(aAgmt[a]));
                }
            }
            
            if(aAgmt[n-1][n-1] == 0){
                System.out.println("No Unique Solution");
                return x;
            }else{
                for(int j=k+1;j<=n;++j){
                    double tval = aAgmt[j-1][k-1];
                    for(int p=1;p<=n+1;++p){
                        rowminus = (tval/aAgmt[k-1][k-1])*aAgmt[k-1][p-1];
                        aAgmt[j-1][p-1] -= rowminus;
                    }
                }
                System.out.println("After Row Operation");
                for(int a=0;a<n;++a){
                    System.out.println(Arrays.toString(aAgmt[a]));
                }
                System.out.println("");
            }
            x[n-1] = aAgmt[n-1][n+1-1]/aAgmt[n-1][n-1];
            for(int i=n-1;i>=1;--i){
                backsubsum = 0;
                for(int f=i+1;f<=n;++f){
                    backsubsum += aAgmt[i-1][f-1]*x[f-1];
                }
                x[i-1] = (aAgmt[i-1][n+1-1] - backsubsum)/aAgmt[i-1][i-1];
            }
        }
        return x;
    }
    
}
