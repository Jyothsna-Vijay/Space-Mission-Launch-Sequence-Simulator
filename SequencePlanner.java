import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class SequencePlanner{
    private final LaunchSequence solution;
    private SequenceList<ProposedSequence> sequenceList;

    public SequencePlanner(LaunchSequence solution){
        this.solution = solution;
        this.sequenceList = new SequenceList<>();
    }

    public SequencePlanner(){
        this.sequenceList = new SequenceList<>();
        Random random = new Random();
        Set<Integer> linkedRandomNumber = new LinkedHashSet<Integer>();
        while(linkedRandomNumber.size() <= 5){
            Integer next = random.nextInt(9) +1;
            linkedRandomNumber.add(next);
        }
        int[] sequence = linkedRandomNumber.stream().mapToInt(Number::intValue).toArray();
        this.solution = new LaunchSequence(sequence);
    }

    public boolean checkProposedSequence(LaunchSequence sequence){
        ProposedSequence proposedSequence = new ProposedSequence(sequence, this.solution);
        sequenceList.add(proposedSequence);
        return proposedSequence.isCorrect();
    }

    public SequenceList<ProposedSequence> getPreviousSuggestions(){
        if(this.sequenceList.size == 0){
            return null;
        }
        else{
            return this.sequenceList;
        }
    }

    public LaunchSequence getSolution(){
        return this.solution;
    }
}

