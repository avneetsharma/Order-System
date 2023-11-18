package com.aks.order.mgmt.order.model.dto;

import org.springframework.http.HttpMethod;

import com.aks.order.mgmt.order.enums.EventType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ExchangeEvent {
	
	private String traceId;
	private EventType eventType;
	private String uri;
	private HttpMethod httpMethodType;
	private String requestedAt;
	private String executionDuration;
	private String executioncompletedAt;
	private Integer statusCode;
	private String errorMsg;
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public HttpMethod getHttpMethodType() {
		return httpMethodType;
	}
	public void setHttpMethodType(HttpMethod httpMethodType) {
		this.httpMethodType = httpMethodType;
	}
	public String getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(String requestedAt) {
		this.requestedAt = requestedAt;
	}
	public String getExecutionDuration() {
		return executionDuration;
	}
	public void setExecutionDuration(String executionDuration) {
		this.executionDuration = executionDuration;
	}
	public String getExecutioncompletedAt() {
		return executioncompletedAt;
	}
	public void setExecutioncompletedAt(String executioncompletedAt) {
		this.executioncompletedAt = executioncompletedAt;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
