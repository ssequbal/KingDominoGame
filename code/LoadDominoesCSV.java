/*
    Description:
       This to load CSV file holding the dominoes data 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class LoadDominoesCSV {
    
    public static void main(String... args){
        List<Domino> dominoes = readDominoesFromCSV("Dominoes.csv");

        for (Domino d : dominoes) { System.out.println(d); }

    }

    public static List<Domino> readDominoesFromCSV(String fileName){
        List<Domino> dominoes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = br.readLine();

            while(line != null){
                String[] attributes = line.split(",");

                var num = dominoes.size();
                Domino domino = createDomino(attributes,num);

                dominoes.add(domino);

                line = br.readLine();
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        
        return dominoes;
    }

    private static Domino createDomino(String[] metadata, int num){
        Terrains _terrain1 = Terrains.fromInteger(Integer.parseInt(metadata[0]));
        int _crown1 = Integer.parseInt(metadata[1]);
        Terrains _terrain2 = Terrains.fromInteger(Integer.parseInt(metadata[2]));
        int _crown2 = Integer.parseInt(metadata[3]);

        return new Domino(_terrain1, _crown1,_terrain2, _crown2, num);
    }
}
