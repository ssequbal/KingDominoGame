/*
    Description:
        The Square object that has a terrain
*/
public class Square extends Tile {
    //children Starting Tile & Square
    //variables
    private int domino;
    private int crown;

    //init
    public Square(Terrains terrain, int domino, int crown)
    {
        super(terrain);
        this.domino = domino; //The int id of the domino the square belongs to
        this.crown = crown;
    }

    //setters and getters
    public int GetDomino(){ return domino; }
    public int GetCrown(){ return crown; }

    @Override
    public String toString() { 
        return "Square [terrain= "+GetTerrain()+ ",crown= "+GetCrown()+"]";
    }
}
