package main.app;

public class ReadingScores {

    int student_id;
    int MCAP;
    int i_ready;
    int dibels;

    public ReadingScores(int[] scores){

        this.student_id = scores[0];
        this.MCAP = scores[1];
        this.i_ready = scores[2];
        this.dibels = scores[3];

    }

    public int getStudent_id(){
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getMCAP(){
        return MCAP;
    }

    public void setMCAP(int MCAP) {
        this.MCAP = MCAP;
    }

    public int getI_ready() {
        return i_ready;
    }

    public void setDibels(int dibels) {
        this.dibels = dibels;
    }

    public int getDibels() {
        return dibels;
    }
}
