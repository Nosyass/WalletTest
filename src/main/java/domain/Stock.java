package domain;

/**
 * @author yassine.sahli
 * 
 */
public class Stock {
	
	 private final double amnout ;
	 private final StockType stockType;
	
	public Stock(double amnout, StockType stockType) {
		this.amnout = amnout;
		this.stockType = stockType;
	}

	public StockCurrencyValue value(CurrencyReferential toCurrency) {
        return StockCurrencyValue.of(amnout, toCurrency);
	}

	public StockType getStockType() {
		return stockType;
	}
	
	
	
	
  
}
