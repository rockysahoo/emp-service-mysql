package emp.data.service.mysql.controller;


import emp.data.service.mysql.model.DictionaryRequest;
import emp.data.service.mysql.service.DictionaryService;
import emp.data.service.mysql.service.DictionaryServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/audit/v1/dictionary", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DictionaryController {

    @Autowired
    DictionaryServiceJpa dictionaryServiceJpa;

    @Autowired
    DictionaryService dictionaryService;

    @PostMapping("/createDictionary")
    public ResponseEntity<Object> createDictionary(@RequestBody DictionaryRequest dictionaryRequest){


        dictionaryService.addDictionary(dictionaryRequest);

       // dictionaryServiceJpa.addDictionaryJpa(dictionaryRequest);

        return new ResponseEntity<Object>("Successfully Created", HttpStatus.CREATED);

    }

}
