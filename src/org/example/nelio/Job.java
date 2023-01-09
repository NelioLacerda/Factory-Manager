package org.example.nelio;

public class Job {
    private  String jobName;
    private String[] rankingName;
    private double[] rankingSalary;
    private int rankingSize, peopleQuantity;

    public Job(){
        this.jobName = "CEO";
        this.rankingName = new String[5];
        this.rankingSize = 0;
        this.rankingName[rankingSize] = "CEO";
        this.rankingSalary = new double[5];
        this.rankingSalary[rankingSize++] = 100000;
        this.peopleQuantity = 1;
    }

    public Job(String jobName, String[] rankingNames, double[] rankingSalary){
        this.jobName = jobName;
        this.rankingName = rankingNames;
        this.rankingSalary = rankingSalary;
        this.peopleQuantity=0;
        this.rankingSize = 0;
    }
    public String getRankingName(int position){
        return rankingName[position];
    }

    public double getRankingSalary(int position){
        return rankingSalary[position];
    }

    public String getJobName(){
        return  jobName;
    }

    public void addPeopleToJob(){
        peopleQuantity++;
    }

    public void setJobName(String jobName){
        this.jobName = jobName;
    }

    public void addNewTierInRanking(String rankingName, int tierPosition, double rankingSalary){
        if (isFull()){
            grow();
        }
        for (int p = rankingSize -1; p>=tierPosition; p--){
            this.rankingName[p+1] = this.rankingName[p];
            this.rankingSalary[p+1] = this.rankingSalary[p];
        }
        this.rankingName[tierPosition-1] = rankingName;
        this.rankingSalary[tierPosition-1] = rankingSalary;
        rankingSize++;
    }

    public boolean isAnExistentRakingPosition(int rankingPosition){
        int rakingIndex = rankingPosition - 1;
        return rakingIndex >= 0 && rakingIndex < rankingSize;
    }

    public void setRankingSalary(double newSalary, int rankingPosition){
        rankingSalary[rankingPosition-1] = newSalary;
    }
    private boolean isFull(){
        return rankingSize == rankingName.length;
    }
    private void grow(){
        String[] auxRankingName = new String[2 * rankingSize];
        double[] auxRankingSalary = new double[2 * rankingSize];
        for (int p = 0; p< rankingSize; p++){
            auxRankingName[p] = rankingName[p];
            auxRankingSalary[p] = rankingSalary[p];
        }
        rankingName = auxRankingName;
        rankingSalary = auxRankingSalary;
    }

}
