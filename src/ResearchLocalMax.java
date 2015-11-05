public class ResearchLocalMax {
    float localMax;
    float currentValue;
    float score;
    float oldValue;
    float oldScore;
    float increment;
    int gamesPlayed;

    public ResearchLocalMax(){
        currentValue = (float) Math.random();
    }

    public ResearchLocalMax(float localMaxFromFile){
        localMax = localMaxFromFile;
        oldScore = score;
        oldValue = currentValue;
        score = 0;
        currentValue = currentValue + increment;
    }

    public void researchVal(){
        if(score<oldScore){

        }
    }
}
