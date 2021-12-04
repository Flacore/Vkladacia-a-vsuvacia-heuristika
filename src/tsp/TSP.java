/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;


public class TSP {
    
    public int M;              // pocet vrcholov 
    public int data[][];       // matica vzdialenosti
    public int[] x;            // riesenie
            
    
   public int getM() {
       return M;
   }
   
   public int[][] getdata()  {
        return data;
   }
   
   public void ries() {
       if(((M) == data.length && (M) == data[0].length)|| M < 0){
           if(M <= 3){
               this.h_najblizsiSused();
           }else{
               this.h_vsuvacia();
           }       
       }else{
           System.err.println("Načítané dáta sú chybné");
           System.exit(-1);
       }
   }
   
   public void h_najblizsiSused(){
        
        x[0] = 1;
        
        for(int i=1; i<M; i++){
            int index = - 1;
            int vzdialenost = Integer.MAX_VALUE;
            
            for(int j=0; j<M; j++){
                if(!contain(j + 1)){
                    if(vzdialenost > data[x[i-1] - 1][j]){
                        vzdialenost = data[x[i-1] - 1][j];
                        index = j + 1;
                    }
                }
            }
            
            x[i] = index;
        }
        
        x[M]=x[0];
   } 
   
   public void h_vsuvacia(){
        int size = 0;
        
        x[0]=1;
        size++;
        
        for(int i=1; i<M; i++){
            int index = vyberany_vrchol();
            
            int pozicia = 0;
            int vzdialenost = Integer.MAX_VALUE;
            
            for(int j=0; j<size; j++){
                int odhad_trasy = predlzenie_trasy(j, j+1, index, size);
                
                if(vzdialenost > odhad_trasy){
                    vzdialenost = odhad_trasy;
                    pozicia = j;
                }                
            }           
            
            vloz_medzi(pozicia, pozicia + 1, index,size);
            size++;
        }
          
        x[M]=x[0];
   }
      
   public int vyberany_vrchol(){
       int vrchol = 0;
       do{
           vrchol = (int)(Math.random()*(M)+1);
       }while(contain(vrchol));
       return vrchol;
   }
   
   public int predlzenie_trasy(int index_pred, int index_po, int vrchol, int size){
       int counter = 1;
       int dlzka = 0;
       int vrchol_pred = x[0];
       
       while(counter<=index_pred){
            dlzka +=data[vrchol_pred - 1][x[counter]-1];
            vrchol_pred = x[counter];
            counter++;
       };
       
       //Prvok
       dlzka +=data[vrchol_pred - 1][vrchol-1];
       vrchol_pred=vrchol;
       
       while(counter<size){
            dlzka +=data[vrchol_pred - 1][x[counter]-1];
            vrchol_pred = x[counter];
            counter++;
       };
       
       dlzka +=data[vrchol_pred-1][x[0]-1];
       
       return dlzka;
   }
   
   public void vloz_medzi(int index_pred, int index_po, int vrchol, int size){
       for(int i=size-1; i>=index_po; i--){
           x[i+1] = x[i];
       }
       x[index_po]=vrchol;
   }
   
   
   
   public boolean contain(int vrchol){
       for(int i=0; i<M; i++){
           if(x[i] == vrchol){
               return true;
           }
       }       
       return false;   
   }
            
    public void read_file(File file) {
        try {
    
            BufferedReader bfr = new BufferedReader(new FileReader(file));            
            String line = "";
            int line_index = 0;
            int row = 0, col = 0;
            while ((line = bfr.readLine()) != null) {
                if (line_index == 0) {
                    M = Integer.parseInt(line);
                    data = new int [M][M];
                    x    = new int[M + 1];
                    for( int i=0; i <= M; i++) {
                        x[i]=0;
                    }    
                } else {
                    StringTokenizer st = new StringTokenizer(line, " ");
                    col = 0;
                    while (st.hasMoreTokens()) {
                        data[row][col] =   Integer.parseInt(st.nextToken());
                        System.out.println("number["+ row + "]["+ col + "]:" +data[row][col] );
                        col++;
                    }
                    row++;
                }
                line_index = line_index + 1;
            }    
            bfr.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }
}
