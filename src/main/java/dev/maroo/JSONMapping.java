package dev.maroo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import javaslang.jackson.datatype.JavaslangModule;
import ratpack.exec.Promise;
import ratpack.jackson.Jackson;
import ratpack.jackson.JsonRender;

import java.util.concurrent.CompletionStage;

public class JSONMapping {

	private static final ObjectMapper MAPPER = configureMapping();

	private static ObjectMapper configureMapping() {
		return new ObjectMapper()
				.registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module())
				.registerModule(new JavaTimeModule())
				.registerModule(new JavaslangModule());
	}

	public static final ObjectMapper getJsonMapping() {
		return JSONMapping.MAPPER;
	}

	public static Promise<JsonRender> toJSONPromise(CompletionStage<?> future) {
		return Promise.async(
				d -> d.accept(future.thenApply(Jackson::json))
		);
	}
}
