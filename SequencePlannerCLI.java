import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SequencePlannerCLI{

    public static void runSimulation(SequencePlanner sequencePlanner){
            previousAttempts(sequencePlanner);
         int noAttempts = 0;
            int[] suggestion = userSequence();
            noAttempts++;
            while(hasDuplicate(suggestion)){
                System.out.println("Invalid sequence!");
                suggestion = userSequence();
                }
            while(!sequencePlanner.checkProposedSequence(new LaunchSequence(suggestion))){
                previousAttempts(sequencePlanner);
                System.out.println("Invalid sequence!");
                suggestion = userSequence();
                while(hasDuplicate(suggestion)){
                    suggestion = userSequence();
                }
                noAttempts++;
            }
            printSuccess(noAttempts, sequencePlanner.getSolution().getSequence());
    }

    private static boolean hasDuplicate(int[] suggestion){
        Integer[] sInt = new Integer[suggestion.length];
        for(int i = 0; i < suggestion.length; i++){
            sInt[i] = suggestion[i];
        }
        Set<Integer> suggestionSet = new HashSet<>(Arrays.asList(sInt));
        return suggestionSet.size() != suggestion.length;
    }

    private static void previousAttempts(SequencePlanner sequencePlanner){
        System.out.println("Previous attempts:");
        if(sequencePlanner.getPreviousSuggestions() == null){
                System.out.println("No previous attempts");
        }
        else{
            SequenceList<ProposedSequence> sequences = sequencePlanner.getPreviousSuggestions();
            SequenceList.Node<ProposedSequence> current = sequences.get();
            verifyPreviousAttempts(current);
        }

    }

    private static void verifyPreviousAttempts(SequenceList.Node<ProposedSequence> current){
        StringBuilder result = new StringBuilder();
        if(current.next == null){
            result.append(current.data);
            result.append("\n");
        }
        else{
            while(current.next != null){
            result.append(current.data);
            current = current.next;
            if(current.next != null){
                result.append("\n");
            }
            else{
                result.append("\n");
                result.append(current.data);
            }
        }
        }

        System.out.println(result.toString());
    }
    private static int[] userSequence(){
        System.out.println("Enter new sequence:");
        Scanner in = new Scanner(System.in);
        int[] suggestions = new int[5];
        for(int i = 0; i< suggestions.length; i++){
            suggestions[i] = in.nextInt();
        }
        return suggestions;
    }

    private static boolean validateEntry(int[] solution, int[] suggestion){
        for( int i = 0; i < solution.length; i++){
            if(solution[i] != suggestion[i]){
                System.out.println("Invalid Sequence!");
                return false;
            }
        }
        return true;
    }

    public static void printSuccess(int noAttempts, int[] solution){
        Map<Integer,String> stepsMap = new HashMap<>();
        stepsMap.put(1, "System Check");
        stepsMap.put(2, "Fuel Loading");
        stepsMap.put(3, "Navigation Setup");
        stepsMap.put(4, "Communication Test");
        stepsMap.put(5, "Engine Ignition");
        stepsMap.put(6, "Weather Verification");
        stepsMap.put(7, "Crew Boarding");
        stepsMap.put(8, "Safety Override");
        stepsMap.put(9, "Launch Pad Clear");
        StringBuilder sb = new StringBuilder();
        sb.append("Sequence verified!\n");
        sb.append("The final sequence is:\n");
        for(int i = 0; i < solution.length; i++){
            sb.append(stepsMap.get(solution[i]));
            sb.append("\n");
        }
        sb.append("You needed " + noAttempts + " attempts to find the sequence.");
        System.out.println(sb.toString());

    }

    public static void main(String[] args){
        runSimulation(new SequencePlanner());
    }
}
