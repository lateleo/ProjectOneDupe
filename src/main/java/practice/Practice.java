package practice;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.Employee;
import models.Request;
import service.EmployeeService;
import service.RequestService;
import util.JSONConverter;

public class Practice {

	public static void main(String[] args) throws JsonProcessingException {
		List<Request> reqs = RequestService.pendingRequests(0);
		String json = JSONConverter.convert(reqs);
		System.out.println(json);

	}

}
