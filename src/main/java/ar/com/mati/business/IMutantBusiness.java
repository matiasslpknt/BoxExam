package ar.com.mati.business;

import ar.com.mati.business.exception.*;
import ar.com.mati.model.DnaDTO;

public interface IMutantBusiness {

    public boolean isMutant(DnaDTO dna) throws BusinessException;
}
