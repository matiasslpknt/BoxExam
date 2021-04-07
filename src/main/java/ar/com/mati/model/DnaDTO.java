package ar.com.mati.model;

import java.io.Serializable;

/**
 * This class is to map json request with a DNA matrix
 * @author: Matias Augusto Manzanelli
 * @see <a href = "https://github.com/matiasslpknt/BoxExam.git" />
 */

public class DnaDTO implements Serializable {


    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
