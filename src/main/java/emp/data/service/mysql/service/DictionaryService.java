package emp.data.service.mysql.service;

import emp.data.service.mysql.dao.DictionaryDAO;
import emp.data.service.mysql.model.DictionaryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    @Autowired
    DictionaryDAO dictionaryDao;

    public void addDictionary(DictionaryRequest dictionaryRequest) {

        dictionaryDao.addDictionary(dictionaryRequest);

    }


}
