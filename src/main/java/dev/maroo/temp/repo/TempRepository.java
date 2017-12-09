package dev.maroo.temp.repo;

import dev.maroo.temp.api.Temperature;
import dev.maroo.temp.api.TemperaturesStatus;

import java.util.List;

/**
 *
 * @author Maroo
 */
public interface TempRepository {
	List<Temperature> temperatures();
	Temperature temperature(int sensorId);
	TemperaturesStatus chceckError();

}
