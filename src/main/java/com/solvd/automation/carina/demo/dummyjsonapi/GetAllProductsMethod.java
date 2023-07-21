package com.solvd.automation.carina.demo.dummyjsonapi;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/products", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/products/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetAllProductsMethod extends AbstractApiMethodV2 {
	
	public GetAllProductsMethod() {
		replaceUrlPlaceholder("base_url", Configuration.getRequired("dummyjson_api_url"));
	}
}
