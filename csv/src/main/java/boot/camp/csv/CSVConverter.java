package boot.camp.csv;

import java.util.List;

public abstract class CSVConverter <T> {
	public abstract List<String> convert(T a);
	
	public abstract T convertBack(List<String> properties) throws CSVConverterException;

}
