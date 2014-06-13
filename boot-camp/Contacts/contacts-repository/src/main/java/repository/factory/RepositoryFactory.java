package repository.factory;

import repository.dbservice.*;
import repository.csvservice.*;

public class RepositoryFactory {

	public Repository getRepository(String param, String path) throws Exception {
		
		try {
			
			if (param.equalsIgnoreCase("H2"))
				return new PersonWebAppH2Repository();
			else if (param.equalsIgnoreCase("CSV"));
				return new PersonWebAppCsvRepository(path);
				
		} catch (Exception e) {
			throw new Exception("Błąd inicjalizacji repozytorum.", e);
		}
		
	}
}
