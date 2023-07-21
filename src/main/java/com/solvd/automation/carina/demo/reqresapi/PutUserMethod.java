package com.solvd.automation.carina.demo.reqresapi;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/api/users/2", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/reqres_users/_put/rq.json")
@ResponseTemplatePath(path = "api/reqres_users/_put/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PutUserMethod extends AbstractApiMethodV2 {
	
	public PutUserMethod() {
		replaceUrlPlaceholder("base_url", Configuration.getRequired("reqres_api_url"));
	}
}
