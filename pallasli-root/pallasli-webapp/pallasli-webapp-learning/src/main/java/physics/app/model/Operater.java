package physics.app.model;

import com.lyt.pallas.jdbc.model.Entity;

public class Operater extends Entity {
	private static final long serialVersionUID = 1L;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column OPERATER.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	private Long id;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column OPERATER.CHINESE_NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	private String chineseName;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column OPERATER.ENGLISH_NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	private String englishName;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column OPERATER.SYMBOL
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	private String symbol;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column OPERATER.COMMENTS
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	private String comments;

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column OPERATER.ID
	 * 
	 * @return the value of OPERATER.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column OPERATER.ID
	 * 
	 * @param id
	 *            the value for OPERATER.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column OPERATER.CHINESE_NAME
	 * 
	 * @return the value of OPERATER.CHINESE_NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column OPERATER.CHINESE_NAME
	 * 
	 * @param chineseName
	 *            the value for OPERATER.CHINESE_NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column OPERATER.ENGLISH_NAME
	 * 
	 * @return the value of OPERATER.ENGLISH_NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column OPERATER.ENGLISH_NAME
	 * 
	 * @param englishName
	 *            the value for OPERATER.ENGLISH_NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column OPERATER.SYMBOL
	 * 
	 * @return the value of OPERATER.SYMBOL
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column OPERATER.SYMBOL
	 * 
	 * @param symbol
	 *            the value for OPERATER.SYMBOL
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column OPERATER.COMMENTS
	 * 
	 * @return the value of OPERATER.COMMENTS
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column OPERATER.COMMENTS
	 * 
	 * @param comments
	 *            the value for OPERATER.COMMENTS
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method
	 * corresponds to the database table OPERATER
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof Operater)) {
			return false;
		}
		Operater other = (Operater) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getChineseName() == null ? other.getChineseName() == null
						: this.getChineseName().equals(other.getChineseName()))
				&& (this.getEnglishName() == null ? other.getEnglishName() == null
						: this.getEnglishName().equals(other.getEnglishName()))
				&& (this.getSymbol() == null ? other.getSymbol() == null : this
						.getSymbol().equals(other.getSymbol()))
				&& (this.getComments() == null ? other.getComments() == null
						: this.getComments().equals(other.getComments()));
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method
	 * corresponds to the database table OPERATER
	 * 
	 * @ibatorgenerated Wed Jul 21 13:28:25 CST 2010
	 */
	
	public int hashCode() {
		int hash = 23;
		if (getId() != null) {
			hash *= getId().hashCode();
		}
		if (getChineseName() != null) {
			hash *= getChineseName().hashCode();
		}
		if (getEnglishName() != null) {
			hash *= getEnglishName().hashCode();
		}
		if (getSymbol() != null) {
			hash *= getSymbol().hashCode();
		}
		if (getComments() != null) {
			hash *= getComments().hashCode();
		}
		return hash;
	}
}