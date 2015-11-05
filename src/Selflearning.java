import java.util.ArrayList;
import java.util.List;

public class Selflearning {

    List<ResearchLocalMax> listFoundLocals = new ArrayList<>();
    ResearchLocalMax researchLocalMax;

    public Selflearning(){
        // Deserialize
        localMaxList = listFromFile;
        researchLocalMax = new ResearchLocalMax(localMaxFromFile);
    }

    public float getGoalTestValue(){
        if(listFoundLocals.size()<20){
            return findLocalMax();
        }
        else{
            return bestGlobalVal();
        }
    }

    public float findLocalMax(){
        return researchLocalMax.findMax();
    }

    public float bestGlobalVal() {
        return (float) 0.1;
    }

}
