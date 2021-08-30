
import org.junit.Assert;
import org.junit.Test;
import domain.CurrencyReferential;
import domain.Stock;
import domain.StockType;
import domain.Value;
import domain.Wallet;
import thirdParty.FreeCurrconvProvider;


public class WalletIntegTest {

	@Test
	public void should_return_a_value__rated_by_FreeCurrconvProvider() {
		Wallet wallet = Wallet.refind(new Stock(10.0, StockType.USD), new Stock(20.0, StockType.USD),
				new Stock(5, StockType.TND));
		Value result = wallet.computeValue(CurrencyReferential.EUR, new FreeCurrconvProvider());
        Assert.assertNotNull(result);
	    }
 
}