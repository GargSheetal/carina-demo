package com.solvd.automation.carina.demo.reqresapi;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/api/users", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/reqres_users/_post/rq.json")
@ResponseTemplatePath(path = "api/reqres_users/_post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostNewUserMethod extends AbstractApiMethodV2 {
	
	public PostNewUserMethod() {
		replaceUrlPlaceholder("base_url", Configuration.getRequired("reqres_api_url"));
	}
}
