package org.example.Dto;

public class EMPSDDTO {
    private String deptcode;
    private int seq;
    private int hireYear;

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    @Override
    public String toString() {
        return "EMPSDDTO{" +
                "deptcode='" + deptcode + '\'' +
                ", seq=" + seq +
                ", hireYear=" + hireYear +
                '}';
    }
}
