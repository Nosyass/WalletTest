package domain;

import java.util.Arrays;
import java.util.List;


/**
 * @author yassine.sahli
 *
 */
public class Wallet {

	private List<Stock> stocks;

	private Wallet(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public static Wallet refind(Stock... stocks) {
		return new Wallet(Arrays.asList(stocks));
	}
	
	public static Wallet initEmptyWallet() {
        return new Wallet(null);
    }
	
	/**
	 *  @param toCurrency {@link CurrencyReferential} :The currency to be used to evaluate the global value of the wallet 
	 * 
	 */
    public Value computeValue(CurrencyReferential toCurrency, RateProvider rateProvider) {
        if (stocks == null) return StockCurrencyValue.initEmptyValue(toCurrency);
    	 return stocks.parallelStream().reduce(StockCurrencyValue.initEmptyValue(toCurrency),
                 (firstValue, stock) -> {
                     return rateProvider.rate(stock.getStockType(), toCurrency).applyRate(stock.value(toCurrency));
                 },
                 (firstValue, secondValue) -> firstValue.sum(secondValue));
    }

}
