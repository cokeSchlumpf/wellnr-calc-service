package ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import calls.CalcSumServiceCall;
import exceptions.ServiceError;
import exceptions.WebApplicationException;
import io.swagger.annotations.Api;
import mgmt.MgmtBean;
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

	@EJB
	MgmtBean mgmtBean;

	@POST
	public CalculationResult calculate(CalculationTerm request) {
		try {
			return new CalculationResult(calculateTerm(request));
		} catch (Exception e) {
			throw new WebApplicationException(e, ServiceError.CalculationError(request));
		}
	}

	private int calculateTerm(CalculationTerm request) {
		if (request.operation != null) {
			int n1 = calculateTerm(request.left);
			int n2 = calculateTerm(request.right);
			CalculationResult result;

			if (request.operation.equals(CalculationOperation.ADD)) {
				result = new CalcSumServiceCall( //
						mgmtBean.getServiceDisoveryCredentials(), //
						new SimpleCalculationRequest(n1, n2)) //
								.execute();
			} else {
				result = new CalcSumServiceCall( //
						mgmtBean.getServiceDisoveryCredentials(), //
						new SimpleCalculationRequest(n1, n2)) //
								.execute();
			}

			return result.result;
		} else {
			return request.value.intValue();
		}
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