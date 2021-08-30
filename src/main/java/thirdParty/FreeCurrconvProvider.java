package thirdParty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.CurrencyReferential;
import domain.Rate;
import domain.RateProvider;
import domain.StockType;
import execption.ProviderException;

/**
 * @author yassine.sahli
 * May be the provider server can be down !
 *{@link  https://www.currencyconverterapi.com/docs}
 */
public class FreeCurrconvProvider implements RateProvider {
	
	/**
	* Compute a rate from {@link  StockType} /  to {@link CurrencyReferential}. 
	* @param  stock  a object of stock with amount and stockType
	* @param  toCurrency the currency in which we would to do the rate
	* @return   Rate  object of the computed rate value
	* @see      Rate
	* @throws ProviderException   if a exception was handled from third party
	*/
	@Override
	public Rate rate(StockType stockType, CurrencyReferential toCurrency) {
		Double rate = null;
		String FROM_TO_PATTERN = "%s_%s";
		String convertURLApiService = "https://free.currconv.com/api/v7/convert?q=%s&apiKey=00d939ae218fd9df9484&compact=ultra";
		try {

			String params = String.format(FROM_TO_PATTERN,stockType.toString(), toCurrency);
			URL url = new URL(String.format(convertURLApiService, params));
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();

			if (line.length() > 0) {
				JSONParser parser = new JSONParser();
				JSONObject newJObject = null;
				try {
					newJObject = (JSONObject) parser.parse(line);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				rate = (Double) newJObject.get(params);
			}
			reader.close();
		} catch (Exception e) {
          throw new ProviderException ("A Exception was handled from third party with this information : "+e.getMessage() );
		}
		return Rate.of(rate);

	}
}