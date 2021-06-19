package com.wcc.locationservice.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Component
public class DistanceRequestLoggingFilter extends AbstractRequestLoggingFilter {

    public DistanceRequestLoggingFilter() {
        setBeforeMessagePrefix("CalculateDistance ");
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String msg) {
        logger.info(msg);
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String msg) {

    }

    @Override
    protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
        String uri = request.getRequestURI() + "?" + request.getQueryString();
        MultiValueMap<String, String> parameters = UriComponentsBuilder.fromUriString(uri).build().getQueryParams();
        String postcode1 = parameters.get("postcode1") != null ? parameters.get("postcode1").get(0) : "null";
        String postcode2 = parameters.get("postcode2") != null ? parameters.get("postcode2").get(0) : "null";

        return String.format("%s %s %s", prefix, postcode1, postcode2);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return request.getRequestURI().contains("/api/distance");
    }
}
