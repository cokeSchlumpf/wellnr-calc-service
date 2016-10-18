package ws;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hystrix.CalcSumServiceCall;
import io.swagger.annotations.Api;
import model.CalculationOperation;
import model.CalculationResult;
import model.CalculationTerm;
import model.SimpleCalculationRequest;

@Stateless
@Path("calculation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("calculation-service for adding numbers")
public class CalculationService extends AbstractService {

	@POST
	public CalculationResult calculate(CalculationTerm request) {
		LOG.debug("Request", request);

		try {
			CalcSumServiceCall call = new CalcSumServiceCall(new SimpleCalculationRequest(10, 20));
			CalculationResult result = call.execute();

			LOG.debug("Message from Bluemix", result.result);
		} catch (Exception e) {
			LOG.error("eeor during test", e.getMessage());
		}

		return new CalculationResult(10);
	}

	@GET
	@Path("example")
	public CalculationTerm example() {
		return new CalculationTerm( //
				CalculationOperation.ADD, //
				new CalculationTerm( //
						CalculationOperation.SUB, //
						new CalculationTerm(30), //
						new CalculationTerm(10)), //
				new CalculationTerm(50));
	}

}