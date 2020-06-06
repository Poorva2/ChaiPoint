package test.helpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Testing the coffee machine is tricky because the hardware functions are void. So I have tested the logs that are
 * coming out. I created an expected output from input file and compared it with actual output.
 */

public class FileParsing {
    private static final String makeRecipeInputSplit = "1";
    private static final String adddIngredientInputSplit = "2";
    private static final String makeRecipeOutputSplit = " is ";
    private static final String adddIngredientOutputSplit = " ml of ";

    public static List<List<String>> parseInputString(String inputString) {
        String[] split = inputString.split("\n");
        List<List<String>> parsedInputString = new ArrayList<>();
        List<String> makeRecipe = new ArrayList<>();
        List<String> addIngredient = new ArrayList<>();

        int start, end=split.length;
        for(start=0; start<end; start++) {
            if(split[start].equals(makeRecipeInputSplit)) {
                makeRecipe.add(split[start+1]);
                start++;
            }

            if(split[start].equals(adddIngredientInputSplit)){
                addIngredient.add(split[start+1] + split[start+2]);
            }
        }
        makeRecipe.sort(Comparator.naturalOrder());
        parsedInputString.add(makeRecipe);

        addIngredient.sort(Comparator.naturalOrder());
        parsedInputString.add(addIngredient);

        return parsedInputString;
    }

    public static List<List<String>> parseOutputString(String outputString) {
        List<List<String>> parsedOutput = new ArrayList<>();
        String[] split = outputString.split("\n");
        List<String> makeRecipe = new ArrayList<>();
        List<String> addIngredient = new ArrayList<>();

        int start, end=split.length;
        for(start=0; start<end; start++) {
            if(split[start].contains(makeRecipeOutputSplit)) {
                String[] split2 = split[start].split(makeRecipeOutputSplit);
                makeRecipe.add(split2[0]);
            }
            if(split[start].contains(adddIngredientOutputSplit)) {
                String[] split2 = split[start].split(adddIngredientOutputSplit);
                String word = split2[1];
                word += split2[0].split(" Added ")[1];
                addIngredient.add(word);
            }
        }

        makeRecipe.sort(Comparator.naturalOrder());
        parsedOutput.add(makeRecipe);

        addIngredient.sort(Comparator.naturalOrder());
        parsedOutput.add(addIngredient);

        return parsedOutput;
    }
}
