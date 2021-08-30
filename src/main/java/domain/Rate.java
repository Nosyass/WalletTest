package domain;


/**
 * @author yassine.sahli
 */
public class Rate {
    private final double rate;

    private Rate(double rate) {
        this.rate = rate;
    }

    public static Rate of(double amount) {
        return new Rate(amount);
    }

    public StockCurrencyValue applyRate(StockCurrencyValue stockCurrencyValue) {
        return StockCurrencyValue.of(stockCurrencyValue.amount * rate,stockCurrencyValue.currency);
    }
}
