package dev.maroo.temp.repo;

import dev.maroo.temp.api.Sensor;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Maroo
 */
public class SensorRepositoryImpl implements SensorRepository {

	final List<Sensor> sensors;

	public SensorRepositoryImpl() {
		sensors = Arrays.asList(
				Sensor.builder().id(1).code("28-03146434ccff").name("Temp. zewnętrzna").desc("").maxTemp(80).build(),
				Sensor.builder().id(2).code("28-0414693a8dff").name("Temp. woda wyjscie").desc("").maxTemp(80).build(),
				Sensor.builder().id(3).code("28-03146422dcff").name("Temp. woda bojler").desc("").maxTemp(80).build(),
				Sensor.builder().id(4).code("28-031464463cff").name("Temp. piec").desc("").maxTemp(80).build()
		);
	}

	@Override
	public List<Sensor> sensors() {
		return sensors;
	}

	@Override
	public Sensor sensor(int id) {
		return sensors.stream().filter(s -> s.getId() == id).findFirst().get();
	}

}
