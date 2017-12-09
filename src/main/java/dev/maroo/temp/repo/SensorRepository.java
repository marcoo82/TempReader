package dev.maroo.temp.repo;

import dev.maroo.temp.api.Sensor;
import java.util.List;

/**
 *
 * @author Maroo
 */
public interface SensorRepository {
	
	List<Sensor> sensors();
	Sensor sensor(int id);
	
}
