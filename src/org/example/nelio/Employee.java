package org.example.nelio;

public class Employee {
    private final String employeeName;
    private double salary;
    private int rankingPosition;
    private Job job;
    private String rankingName;

    public Employee(String employeeName, Job job, int rankingPosition){
        this.rankingPosition = rankingPosition - 1;
        this.employeeName = employeeName;
        this.job = job;
        this.salary = job.getRankingSalary(rankingPosition);
        this.rankingName = job.getRankingName(rankingPosition);
    }

    public String getEmployeeName(){
        return this.employeeName;
    }

    public String getJobName(){
        return job.getJobName();
    }

    public String getJobRanking(){
        return rankingName;
    }

    public int getRankingPosition(){
        return rankingPosition;
    }

    public void setNewSalary(double salary){
        this.salary = salary;
    }

    public void setNewRaking(int rankingPosition){
        this.rankingPosition = rankingPosition -1;
        this.salary = job.getRankingSalary(this.rankingPosition);
        this.rankingName = job.getRankingName(this.rankingPosition);
    }
    public void setNewJob(Job job, int rakingPosition){
        this.job = job;
        this.rankingPosition = rakingPosition - 1;
        this.salary = job.getRankingSalary(this.rankingPosition);
        this.rankingName = job.getRankingName(this.rankingPosition);
    }

    public double getSalary(){
        return salary;
    }

    public double compareToSalary(Employee other){
        return this.salary - other.getSalary();
    }
}
