package test;

import main.CoffeeMachine;
import main.services.DisplayService;
import main.services.InputService;
import main.services.OutletService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import test.helpers.FileParsing;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class CoffeeMachineTest {

    String inputPath = "src/test/test_cases.txt";
    String outputPath = "src/test/output.txt";

    @Test
    public void test_coffee_machine() throws InterruptedException, IOException {
        InputStream inputStream = getInputStream();
        DisplayService displayService = new DisplayService(getOutputStream());
        InputService inputService = new InputService(inputStream, displayService);
        OutletService outletService = new OutletService(3);
        CoffeeMachine coffeeMachine = new CoffeeMachine(inputService, outletService, displayService);
        coffeeMachine.runMachine();
        Assert.assertTrue(compare());
    }

    @After
    public void deleteResources() {
        File outputFile = new File(outputPath);
        outputFile.delete();
    }

    private InputStream getInputStream() throws FileNotFoundException {

        File input = new File(inputPath);
        return new FileInputStream(input);
    }

    private OutputStream getOutputStream() throws FileNotFoundException {

        File output = new File(outputPath);
        return new FileOutputStream(output, true);
    }

    private boolean compare() throws IOException {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);

        String inputString = Files.readString(inputFile.toPath());
        List<List<String>> parseInput= FileParsing.parseInputString(inputString);

        String outputString = Files.readString(outputFile.toPath());
        List<List<String>> parsedOutput= FileParsing.parseOutputString(outputString);

        int inputMakeRecipeSize = parseInput.get(0).size(), outputMakeRecipeSize = parsedOutput.get(0).size(),
                inputAddIngredientSize=parseInput.get(1).size(), outputAddIngredientSize=parsedOutput.get(1).size(), it;
        if(inputMakeRecipeSize != outputMakeRecipeSize) {
            return false;
        }
        if(inputAddIngredientSize != outputAddIngredientSize) {
            return false;
        }

        for(it = 0; it < inputMakeRecipeSize; it++) {
            if(!parseInput.get(0).get(it).equals(parsedOutput.get(0).get(it))) {
                return false;
            }
        }

        for(it = 0; it < inputAddIngredientSize; it++) {
            if(!parseInput.get(1).get(it).equals(parsedOutput.get(1).get(it))) {
                return false;
            }
        }
        return true;
    }
}