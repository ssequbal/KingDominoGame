import java.awt.*;
public enum Terrains{
    Start,
	Farm,
	Forest,
	Water,
	Grass,
	Desert,
	Mine;

	public static Terrains fromInteger(int x) {
        switch(x) {
        case 0:
            return Start;
        case 1:
            return Farm;
		case 2:
			return Forest;
		case 3:
			return Water;
		case 4:
			return Grass;
		case 5:
			return Desert;
		case 6:
			return Mine;
        }
        return null;
    }

	public static Color GetColor(Terrains terrain)
	{
		switch(terrain)
		{
			case Start:
				return new Color(20, 20, 20);
			case Farm:
				return new Color(242, 211, 109);
			case Forest:
				return new Color(85, 130, 63);
			case Water:
				return new Color(53, 157, 231);
			case Grass:
				return new Color(178, 219, 121);
			case Desert:
				return new Color(208, 210, 199);
			case Mine:
				return new Color(102, 93, 97);
		}
		return new Color(0,0,0);
	}
};