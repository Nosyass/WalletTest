package domain;


/**
 * @author yassine.sahli
 */
public class Rate {
    private final double rateValue;

    private Rate(double rate) {
        this.rateValue = rate;
    }

    public static Rate of(double amount) {
        return new Rate(amount);
    }

    public StockCurrencyValue applyRate(StockCurrencyValue stockCurrencyValue) {
        return StockCurrencyValue.of(stockCurrencyValue.amount * rateValue,stockCurrencyValue.currency);
    }
}
