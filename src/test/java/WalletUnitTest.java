
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import domain.CurrencyReferential;
import domain.Rate;
import domain.RateProvider;
import domain.Stock;
import domain.StockCurrencyValue;
import domain.StockType;
import domain.Wallet;


public class WalletUnitTest {
 
	  @Test
	    public void should_return_0_value_when_no_stocks_refinded() {
	        Wallet wallet = Wallet.initEmptyWallet();
	        RateProvider rateProvider = mock(RateProvider.class);
	        assertThat(wallet.computeValue(CurrencyReferential.EUR, rateProvider)).isEqualTo(StockCurrencyValue.initEmptyValue(CurrencyReferential.EUR));
	    }

    @Test
    public void should_return_one_stock_value_with_rate_of_2_In_USD() {
    	
  	  Wallet wallet = Wallet.refind(new Stock(8.0, StockType.USD));
        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(StockType.class), any(CurrencyReferential.class))).thenReturn(Rate.of(2.0));
        assertThat(wallet.computeValue(CurrencyReferential.USD, rateProvider)).isEqualTo(StockCurrencyValue.of(16.0, CurrencyReferential.USD));
    }
    
    @Test
    public void should_return_one_stock_reduced_value_with_rate_of_0_2_In_USD() {
    	
  	  Wallet wallet = Wallet.refind(new Stock(8.0, StockType.USD));
        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(StockType.class), any(CurrencyReferential.class))).thenReturn(Rate.of(0.2));
        assertThat(wallet.computeValue(CurrencyReferential.USD, rateProvider)).isEqualTo(StockCurrencyValue.of(1.6, CurrencyReferential.USD));
    }
    
    
    @Test
    public void should_compute_value_of_a_list_stocks_In_USD() {
    	 Wallet wallet = Wallet.refind(
	               new Stock(10.0, StockType.EUR),
	               new Stock(20.0, StockType.EUR));
        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(StockType.class), any(CurrencyReferential.class))).thenReturn(Rate.of(4.0));
        assertThat(wallet.computeValue(CurrencyReferential.USD, rateProvider)).isEqualTo(StockCurrencyValue.of(120.0, CurrencyReferential.USD));
    }
    
    @Test
    public void should_compute_value_of_a_list_of_diff_type_currency_stocks_In_EUR() {
    	 Wallet wallet = Wallet.refind(
	               new Stock(10.0, StockType.EUR),
	               new Stock(20.0, StockType.EUR),
	               new Stock(5,StockType.TND));
        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(StockType.class), any(CurrencyReferential.class))).thenReturn(Rate.of(4.0));
        assertThat(wallet.computeValue(CurrencyReferential.EUR, rateProvider)).isEqualTo(StockCurrencyValue.of(140.0, CurrencyReferential.EUR));
    }

  
    @Test
    public void should_compute_value_of_a_list_of_mixed_type__stocks_In_EUR() {
    	 Wallet wallet = Wallet.refind(
	               new Stock(10.0, StockType.EUR),
	               new Stock(20.0, StockType.EUR),
	               new Stock(5,StockType.TND),
	               new Stock(3,StockType.PETROLEUM),
    	           new Stock(3,StockType.GOLD));
        RateProvider rateProvider = mock(RateProvider.class);
        when(rateProvider.rate(any(StockType.class), any(CurrencyReferential.class))).thenReturn(Rate.of(4.0));
        assertThat(wallet.computeValue(CurrencyReferential.EUR, rateProvider)).isEqualTo(StockCurrencyValue.of(164.0, CurrencyReferential.EUR));
    }
    

   
}