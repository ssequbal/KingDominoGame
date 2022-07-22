/*
    Why?
    To make it easier to store data of PickDomino

*/

public class PickDominoData
{
    private int Num = -1; //Domino Num to track
    private boolean expose = false;
    private int selectedBY = -1; //Player Index who selected it
    Terrains[] terrain = {null,null};
    public boolean canBeSelected; //To distinguish the PickDominoes you can select and cannot


    public PickDominoData(PickDomino pick)
    {
        Num = pick.Num;
        expose = pick.expose;
        selectedBY = pick.selectedBY;
        terrain = pick.terrain;
        canBeSelected = pick.canBeSelected;
        
    }
}
