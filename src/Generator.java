import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {
    private String filePath;
    private int numIntegers;
    private int minRange;
    private int maxRange;

    public Generator(String filePath, int numIntegers, int minRange, int maxRange) {
        this.filePath = filePath;
        this.numIntegers = numIntegers;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
    public Generator(String filePath, int numIntegers) {
        this.filePath = filePath;
        this.numIntegers = numIntegers;
        this.minRange = Integer.MIN_VALUE;
        this.maxRange = Integer.MAX_VALUE;
    }
    public void generateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Random random = new Random();

            for (int i = 0; i < numIntegers; i++) {
                int value = random.nextInt(maxRange - minRange + 1) + minRange;
                writer.write(String.valueOf(value));

                if (i != numIntegers - 1) {
                    writer.write(",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String positiveArrays[] = {"src\\positive1.txt", "src\\positive10.txt", "src\\positive100.txt", "src\\positive1000.txt"};
        String arrays[] = {"src\\array1.txt", "src\\array10.txt", "src\\array100.txt", "src\\array1000.txt"};
        Generator positiveGenerators[] = new Generator[4];
        Generator generators[] = new Generator[4];
        for(int i = 0; i < 4; i++){
            positiveGenerators[i] = new Generator(positiveArrays[i].replace("src\\", "src\\tests\\"),  (int)(Math.pow(10,i+1)*1000),0,100000);
            positiveGenerators[i].generateFile();
        }
        for(int i = 0; i < 4; i++){
            generators[i] = new Generator(arrays[i].replace("src\\", "src\\tests\\"),  (int)(Math.pow(10,i+1)*1000),-100000,100000);
            generators[i].generateFile();
        }
    }
}