import java.util.Arrays;
import java.util.stream.Collectors;

public class LaunchSequence {

    private final int[] sequence;

    public LaunchSequence(int [] sequence){
        this.sequence = sequence;
    }

    public int getProcedure(int i){
        return this.sequence[i];
    }

    public int size(){
        return this.sequence.length;
    }

    public int[] getSequence(){
        return this.sequence;
    }
    @Override
    public String toString(){
       return Arrays.stream(this.sequence).mapToObj(String:: valueOf).collect(Collectors.joining(" "));
    }
}
