import java.util.HashMap;
import java.util.ArrayList;
public class Territories_identify{

public static HashMap territory_check(Grid grid1)
{
    HashMap<Integer,ArrayList<Tile>> territories = new HashMap<Integer, ArrayList<Tile>>();
    territories.put(1,new ArrayList<Tile>());
    territories.put(2,new ArrayList<Tile>());
    territories.put(3,new ArrayList<Tile>());
    territories.put(4,new ArrayList<Tile>());
    territories.put(5,new ArrayList<Tile>());
    territories.put(6,new ArrayList<Tile>());
    territories.put(7,new ArrayList<Tile>());
    territories.put(8,new ArrayList<Tile>());
    territories.put(9,new ArrayList<Tile>());
    territories.put(10,new ArrayList<Tile>());
    territories.put(11,new ArrayList<Tile>());
    territories.put(12,new ArrayList<Tile>());
    territories.put(13,new ArrayList<Tile>());
    territories.put(14,new ArrayList<Tile>());
    territories.put(15,new ArrayList<Tile>());
    territories.put(16,new ArrayList<Tile>());
    territories.put(17,new ArrayList<Tile>());
    territories.put(18,new ArrayList<Tile>());
    // ArrayList<ArrayList> total = new ArrayList<ArrayList>();
    // ArrayList<ArrayList> possible = new ArrayList<ArrayList>();
    int count = 0;
    int current = 0;
    boolean exists = true;
    
    for(int a=0;a<11;a++)
    {
        for(int b=0;b<11;b++)
        {

            if (grid1.getSquareTile(a,b)!=null)
           {
            Tile tileLeft= new Tile(Terrains.fromInteger(1));
            Tile tileRight= new Tile(Terrains.fromInteger(1));
            Tile tileUp= new Tile(Terrains.fromInteger(1));
            Tile tileDown= new Tile(Terrains.fromInteger(1));
            Tile tile1=grid1.getSquareTile(a,b);
            
            boolean checkLeft = true;
            boolean checkRight = true;
            boolean checkUp = true;
            boolean checkDown = true;

            Terrains terrainMain= tile1.GetTerrain();
            
            Terrains terrainLeft= Terrains.fromInteger(1);
            Terrains terrainRight= Terrains.fromInteger(1);
            Terrains terrainUp= Terrains.fromInteger(1);
            Terrains terrainDown= Terrains.fromInteger(1);
            
            if(a-1>-1){
                if(!(grid1.getSquareTile(a-1,b) == null )){
                    tileLeft = grid1.getSquareTile(a-1,b);
                    terrainLeft= tileLeft.GetTerrain();

            }   
                else{
                    checkLeft = false;
                }
            
            }
            else{
                terrainLeft= Terrains.fromInteger(1);
                checkLeft = false;
            }
            
            if(a+1<11){
                if(!(grid1.getSquareTile(a+1,b) == null )){
                    tileRight= grid1.getSquareTile(a+1,b);
                    terrainRight= tileRight.GetTerrain();
            }
                else{
                    checkRight = false;
                }
            }
            else{
                terrainRight= Terrains.fromInteger(1);
                checkRight = false;
            }
        
            if(b-1>-1){
                if(!(grid1.getSquareTile(a,b-1) == null )){
                    tileUp=grid1.getSquareTile(a,b-1);
                    terrainUp= tileUp.GetTerrain();
                }
            
                else{
                    checkUp = false;
                }
            }
            else{
                terrainUp= Terrains.fromInteger(1);
                checkUp = false;
            }
        
            if(b+1<11){
                if(!(grid1.getSquareTile(a,b+1) == null )){
                    tileDown=grid1.getSquareTile(a,b+1);
                    terrainDown= tileDown.GetTerrain();
                
            }
                else{
                    checkDown = false;
                }
            }
            else{
                terrainDown = Terrains.fromInteger(1);
                checkDown = false;
            }

            if(count == 0){
                count += 1;

                ArrayList <Tile> main = territories.get(count);
                main.add(tile1);

                if ( checkLeft && (terrainMain == terrainLeft))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileLeft)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(count);
                        territory.add(tileLeft);
                    
                    }
                    
                }


                if ( checkRight && (terrainMain == terrainRight))
                {

                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileRight)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(count);
                        territory.add(tileRight);
                    }


                    
                }	

                if ( checkUp && (terrainMain == terrainUp))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileUp)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(count);
                        territory.add(tileUp);
                    }
                    
                    
                    
                    
                }

                if ( checkDown && (terrainMain == terrainDown))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileDown)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(count);
                        territory.add(tileDown);
                    }


                    
                
                }
            }
            else{
                for(Integer key: territories.keySet()){
                    ArrayList <Tile> check = territories.get(key);
                    if(key<18){
                        if(check.contains(tile1)){
                            current = key;
                            break;
                        }
                    }
                    else if(key == 18){
                        if(check.contains(tile1)){
                            current = key;

                    }
                        else{
                            count+=1;
                            current = count;
                            ArrayList <Tile> territory = territories.get(count);
                            territory.add(tile1);
                        }

                }
            }
                if ( checkLeft && (terrainMain == terrainLeft))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileLeft)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(current);
                        territory.add(tileLeft);
                    }
                }

                if ( checkRight && (terrainMain == terrainRight))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileRight)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(current);
                        territory.add(tileRight);
                    }
                }

                if ( checkUp && (terrainMain == terrainUp))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileUp)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(current);
                        territory.add(tileUp);
                    }
                }

                if ( checkDown && (terrainMain == terrainDown))
                {
                    boolean fix = true;
                    for(Integer key: territories.keySet()){
                        ArrayList <Tile> check = territories.get(key);
                        if(check.contains(tileDown)){
                            fix = false;
                            break;
                }
                }
                    if(fix == true){
                        ArrayList <Tile> territory = territories.get(current);
                        territory.add(tileDown);
                    }
                    
                }

            }
            checkLeft = true;
            checkDown = true;
            checkRight = true;
            checkUp = true;
            }
        
        }

    }
    return territories;
 }
 
}




