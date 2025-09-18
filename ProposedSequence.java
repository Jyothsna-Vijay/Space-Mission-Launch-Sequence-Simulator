import java.util.Arrays;

public class ProposedSequence{

    private final LaunchSequence suggestion;
    private final LaunchSequence solution;

    public ProposedSequence(LaunchSequence suggestion, LaunchSequence solution){
        this.suggestion = suggestion;
        this.solution = solution;
    }

    public LaunchSequence getSequence() {
        return this.suggestion;
    }

    public int getNumCorrect(){
        int correct = 0;
        for(int i = 0; i < suggestion.size(); i++){
            if(this.suggestion.getProcedure(i) == this.solution.getProcedure(i)){
                correct ++;
            }
        }
        return correct;
    }

    public int getNumWrongPosition(){
        int incorrect = 0;
        for(int i = 0; i < suggestion.size(); i++){
            int finalInd = i;
            boolean exists = Arrays.stream(solution.getSequence()).anyMatch(ind -> ind == this.suggestion.getProcedure(finalInd));
            if(exists && this.suggestion.getProcedure(i) != this.solution.getProcedure(i)){
                incorrect ++;
            }
        }
        return incorrect;
    }

    public boolean isCorrect(){
        for(int i = 0; i < suggestion.size(); i++){
            if(this.suggestion.getProcedure(i) != this.solution.getProcedure(i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return this.suggestion.toString() + " (Correct " + this.getNumCorrect() + ", Wrong position " + this.getNumWrongPosition() + ")";
    }

}
