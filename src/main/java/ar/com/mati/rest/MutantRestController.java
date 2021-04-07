package ar.com.mati.rest;

import ar.com.mati.business.exception.BusinessException;
import ar.com.mati.business.IMutantBusiness;
import ar.com.mati.model.DnaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constantes.URL_MUTANTS)
public class MutantRestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMutantBusiness mutantBusiness;

    @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> add(@RequestBody DnaDTO dna) {
        try {
            return new ResponseEntity<Boolean>(mutantBusiness.isMutant(dna), HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
