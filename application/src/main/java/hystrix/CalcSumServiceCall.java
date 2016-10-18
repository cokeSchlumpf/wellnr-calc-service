package hystrix;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import model.CalculationResult;
import model.SimpleCalculationRequest;
import util.Logger;

public class CalcSumServiceCall extends HystrixCommand<CalculationResult> {

	public final SimpleCalculationRequest request;

	public CalcSumServiceCall(SimpleCalculationRequest request) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.request = request;
	}

	@Override
	protected CalculationResult run() throws Exception {
		HttpResponse<String> response = null;

		try {
			response = Unirest //
					.post("https://wellnr-calc-sum-service.mybluemix.net/wellnr-calc-sum-service/api/calculation/add") //
					.header("Content-Type", "application/json") //
					.header("Accept", "application/json") //
					.body(request.toString()) //
					.asString();

			if (response.getStatus() >= 200 && response.getStatus() <= 300) {
				ObjectMapper m = new ObjectMapper();
				CalculationResult result = m.readerFor(CalculationResult.class).readValue(response.getBody());
				Logger.DEFAULT.debug("Result from Servcie", result);
				return result;
			} else {
				String msg = "Received non-successful status code from upstream system";

				Logger.DEFAULT.warn(msg, //
						response.getStatus(), //
						response.getStatusText(), //
						response.getBody());

				throw new Exception(msg);
			}
		} catch (Exception e) {
			Logger.DEFAULT.debug("Error during Hystrix Command", e.getMessage());
			throw e;
		}
	}

	@Override
	protected CalculationResult getFallback() {
		return new CalculationResult(0);
	}

}
