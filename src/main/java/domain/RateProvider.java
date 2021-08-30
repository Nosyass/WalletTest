package domain;
/**
 * @author yassine.sahli
 */
public interface RateProvider {
    Rate rate(StockType stockType, CurrencyReferential toCurrency);

}

