package cc.easyandroid.easyclean.httpextend.easycore;

public class EasyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8840911902854021247L;

	public String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public EasyException() {
	}

	public EasyException(String detailMessage) {
		super(detailMessage);
	}

	public EasyException(String detailMessage, String errorCode) {
		super(detailMessage);
		this.errorCode = errorCode;
	}
}
