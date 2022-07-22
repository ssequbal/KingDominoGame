import java.util.HashMap;
import java.util.ArrayList;
public class CrownTotal {
    public static int total(Grid grid1){
        HashMap<Integer,ArrayList<Tile>> territories = Territories_identify.territory_check(grid1);
        int total = 0;
        int notiles = 0;
        int nocrowns = 0;
        for(ArrayList<Tile> i:territories.values()){
            notiles = i.size();
            nocrowns = 0;
            for(Tile j:i){
                
                if(!(j.GetTerrain() == Terrains.fromInteger(0))){
                    Square _square = (Square) j;
                    nocrowns += _square.GetCrown();
                }
            }
            total+=nocrowns * notiles;
        }







        return total;
    }
}
