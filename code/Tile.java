/*
    Description:
        The Parent for the Starting Tile and Squares
*/
public class Tile {
    //variables
    private Terrains terrain;

    //init
    public Tile(Terrains terrain)
    {
        this.terrain = terrain;
    }

    //setters and getters
    public Terrains GetTerrain(){ return terrain; }
}
