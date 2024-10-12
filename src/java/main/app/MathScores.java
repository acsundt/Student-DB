package main.app;

public class MathScores {


    int student_id;
    int MCAP;
    int i_ready;

    public MathScores(int[] scores){

        this.student_id = scores[0];
        this.MCAP = scores[1];
        this.i_ready = scores[2];
    }


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setMCAP(int MCAP) {
        this.MCAP = MCAP;
    }

    public int getMCAP() {
        return MCAP;
    }

    public int getI_ready() {
        return i_ready;
    }

    public void setI_ready(int i_ready) {
        this.i_ready = i_ready;
    }


}


