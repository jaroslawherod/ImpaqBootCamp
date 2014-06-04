package boot.camp.day1.hello;

public class ParserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public String errorName;
	
	ParserException(){}
	
	ParserException(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
}