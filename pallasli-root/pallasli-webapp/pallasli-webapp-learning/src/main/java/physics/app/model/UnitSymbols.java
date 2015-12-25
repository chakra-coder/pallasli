package physics.app.model;

import com.lyt.pallas.jdbc.model.Entity;

public class UnitSymbols extends Entity {
	private static final long serialVersionUID = 1L;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column UNIT_SYMBOLS.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	private Long id;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column UNIT_SYMBOLS.NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	private String name;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column UNIT_SYMBOLS.CHINESENAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	private String chinesename;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column UNIT_SYMBOLS.SYMBOL
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	private String symbol;

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column UNIT_SYMBOLS.ID
	 * 
	 * @return the value of UNIT_SYMBOLS.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column UNIT_SYMBOLS.ID
	 * 
	 * @param id
	 *            the value for UNIT_SYMBOLS.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column UNIT_SYMBOLS.NAME
	 * 
	 * @return the value of UNIT_SYMBOLS.NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column UNIT_SYMBOLS.NAME
	 * 
	 * @param name
	 *            the value for UNIT_SYMBOLS.NAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column UNIT_SYMBOLS.CHINESENAME
	 * 
	 * @return the value of UNIT_SYMBOLS.CHINESENAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public String getChinesename() {
		return chinesename;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column UNIT_SYMBOLS.CHINESENAME
	 * 
	 * @param chinesename
	 *            the value for UNIT_SYMBOLS.CHINESENAME
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column UNIT_SYMBOLS.SYMBOL
	 * 
	 * @return the value of UNIT_SYMBOLS.SYMBOL
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column UNIT_SYMBOLS.SYMBOL
	 * 
	 * @param symbol
	 *            the value for UNIT_SYMBOLS.SYMBOL
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method
	 * corresponds to the database table UNIT_SYMBOLS
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof UnitSymbols)) {
			return false;
		}
		UnitSymbols other = (UnitSymbols) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getName() == null ? other.getName() == null : this
						.getName().equals(other.getName()))
				&& (this.getChinesename() == null ? other.getChinesename() == null
						: this.getChinesename().equals(other.getChinesename()))
				&& (this.getSymbol() == null ? other.getSymbol() == null : this
						.getSymbol().equals(other.getSymbol()));
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method
	 * corresponds to the database table UNIT_SYMBOLS
	 * 
	 * @ibatorgenerated Wed Jul 21 13:38:55 CST 2010
	 */
	
	public int hashCode() {
		int hash = 23;
		if (getId() != null) {
			hash *= getId().hashCode();
		}
		if (getName() != null) {
			hash *= getName().hashCode();
		}
		if (getChinesename() != null) {
			hash *= getChinesename().hashCode();
		}
		if (getSymbol() != null) {
			hash *= getSymbol().hashCode();
		}
		return hash;
	}
}