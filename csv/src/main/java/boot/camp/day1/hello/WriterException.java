package boot.camp.day1.hello;

public class WriterException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorName;

    WriterException() {
    }

    WriterException(String errorName) {
	this.errorName = errorName;
    }

    public String getErrorName() {
	return errorName;
    }

    public void setErrorName(String errorName) {
	this.errorName = errorName;
    }
}