package org.imsglobal.caliper.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.events.CaliperEvent;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public abstract class EventStoreRequestor {

	public abstract boolean send(CaliperEvent caliperEvent);

	public abstract boolean send(CaliperEntity caliperEntity);

	/**
	 * @param caliperEvent
	 * @param id
	 *            - OPTIONAL, unique Id for the event
	 * @param sendTime
	 *            - OPTIONAL, time to record the send of this event
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected StringEntity generatePayload(CaliperEvent caliperEvent,
			String id, DateTime sendTime) throws UnsupportedEncodingException {

		if (id == null) {
			id = "caliper-java_" + UUID.randomUUID().toString();
		}

		if (sendTime == null) {
			sendTime = DateTime.now();
		}

		String jsonPayload = getPayloadJson(caliperEvent, id, sendTime);
		StringEntity payLoad = new StringEntity(jsonPayload);
		payLoad.setContentType("application/json");

		return payLoad;
	}

	/**
	 * @param caliperEvent
	 * @param id
	 * @param sendTime
	 * @return
	 */
	protected String getPayloadJson(CaliperEvent caliperEvent, String id,
			DateTime sendTime) {

		List<EventStoreEnvelope> listPayload = Lists.newArrayList();

		EventStoreEnvelope envelope = new EventStoreEnvelope();
		envelope.setId(id);
		envelope.setType("caliperEvent");
		envelope.setTime(sendTime.toString());
		envelope.setData(caliperEvent);

		listPayload.add(envelope);

		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = null;
		try {
			jsonPayload = mapper.writeValueAsString(listPayload);
		} catch (JsonProcessingException e) {
			// LOG.error("Json Parse Exception", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonPayload;
	}

}
