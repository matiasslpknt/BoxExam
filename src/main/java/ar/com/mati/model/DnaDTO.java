package ar.com.mati.model;

import java.io.Serializable;


public class DnaDTO implements Serializable {


    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
