package ar.com.mati.business;

import ar.com.mati.business.exception.*;
import ar.com.mati.model.DnaDTO;

/**
 * This class is the mutant interface to determine MutantBusiness behavior
 * @author: Matias Augusto Manzanelli
 * @see <a href = "https://github.com/matiasslpknt/BoxExam.git" />
 */

public interface IMutantBusiness {

    public boolean isMutant(DnaDTO dna) throws BusinessException;
}
