package boot.camp.repository;

import java.lang.reflect.Type;

public class RepositoryFactory {

	public IRepository createRepository(Type repositoryClass,
			String[] parameters) throws RepositoryException {
		try {
			if (repositoryClass.equals(H2Repository.class)) {
				if (parameters.length == 2) {
					return new H2Repository(parameters[0], parameters[1], "");
				} else if (parameters.length == 3) {
					return new H2Repository(parameters[0], parameters[1],
							parameters[2]);
				} else {
					throw new RepositoryException(
							"Invalid number of parameters.");
				}
			} else if (repositoryClass.equals(CSVRepository.class)) {
				return new CSVRepository(parameters[0]);
			} else {
				throw new RepositoryException("Invalid class specified.");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new RepositoryException(e);
		}
	}
}
