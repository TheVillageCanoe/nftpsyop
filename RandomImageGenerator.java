/*
 * Author: Chris Ron
 * Created: 9-14-23
 * Description: reads config.txt and generates given number of images, layer by layer
 */

import java.io.File;
import java.util.*;

public class RandomImageGenerator {

    public static void main(String[] args) throws Exception {

        File completedDir = new File("Completed");

        for (File file : Objects.requireNonNull(completedDir.listFiles())) {
            file.delete();
        }

        int numImages;
        int numLayers;

        File config = new File("config.txt");
        Scanner sc = new Scanner(config);

        String genNumString = sc.nextLine();
        genNumString = genNumString.substring(30);
        numImages = Integer.parseInt(genNumString);


        String numLayersString = sc.nextLine();
        numLayersString = numLayersString.substring(18);
        numLayers = Integer.parseInt(numLayersString);
        
        ArrayList<ArrayList<Branch>> layers = layerTreeGen(numLayers, sc);

        printListList(layers);


        sc.close();
    }

    public static ArrayList<ArrayList<Branch>> layerTreeGen(int size, Scanner sc) 
    {
        ArrayList<ArrayList<Branch>> layers = new ArrayList<ArrayList<Branch>>();

        for(int i=0; i<size; i++)
        {
            ArrayList<Branch> currentLayer = new ArrayList<Branch>();

            String layerLine = sc.nextLine();
            int lineSize = layerLine.length();

            int layerStartIndex = 0;

            for(int j=0; j<lineSize; j++)
            {
                char currentChar = layerLine.charAt(j);

                if(currentChar==':'){
                    layerStartIndex = j+2;
                    break;
                }
            }

            

            String[] layerData = layerLine.substring(layerStartIndex).split(" ");

            int dataSize = layerData.length;

            Branch currentBranch = new Branch();

            String currentName = "init";
            double currentChance = 0.0;

            for(int j=0; j<dataSize; j++)
            {
                if(j%2==0)
                {
                    currentName = layerData[j];
                }
                else
                {
                    currentChance = Double.parseDouble(layerData[j]);
                    currentBranch = new Branch(currentName, currentChance);
                    currentLayer.add(currentBranch);
                }
            }

            layers.add(currentLayer);
        }

        return layers;
    }

    public static void printListList(ArrayList<ArrayList<Branch>> layers)
    {
        int count = 1;
        for(ArrayList<Branch> layer : layers)
        {
            System.out.println("Layer " + count + ": ");
            for(Branch branch : layer)
            {
                System.out.println(branch);
            }
            System.out.println();
            count++;
        }
    }
}
