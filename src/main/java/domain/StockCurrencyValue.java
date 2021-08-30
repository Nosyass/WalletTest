package domain;

import java.util.Objects;

/**
 * @author yassine.sahli
 * to define  a currency value for both materials  ( Gold , Petroleum .. ) and currency stocks
 */
public class StockCurrencyValue extends Value{

    protected final CurrencyReferential currency;


    public StockCurrencyValue(double amount,CurrencyReferential currency) {
    	super(amount);
        this.currency = currency;
		
    }

    /**
     *Is the identity  used for accumulator of StockCurrencyValue
     */
    public  static StockCurrencyValue initEmptyValue (CurrencyReferential currency) {
      return new StockCurrencyValue (0.0,currency); 
    }


    public static StockCurrencyValue of(double amount,CurrencyReferential currency) {
        return new StockCurrencyValue(amount,currency);
    }


    public StockCurrencyValue sum(StockCurrencyValue value) {
        return new StockCurrencyValue(this.amount + value.amount,this.currency);
    }

    
	/*Equals and hashCode was needed for unit test*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(currency);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockCurrencyValue other = (StockCurrencyValue) obj;
		return currency == other.currency;
	}
	

    
}
