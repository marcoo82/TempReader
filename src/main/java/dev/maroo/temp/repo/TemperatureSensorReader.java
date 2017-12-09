package dev.maroo.temp.repo;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maroo
 */
public class TemperatureSensorReader {

	private final Map<Integer, String> sensors = new HashMap();

	public TemperatureSensorReader() {
		initSensorList();
	}

	private void initSensorList() {
		sensors.put(1, "28-03146434ccff");
		sensors.put(2, "28-0414693a8dff");
		sensors.put(3, "28-03146422dcff");
		sensors.put(4, "28-031464463cff");
	}

	public String getSensorCode(Integer sensorId) {
		if (sensors.containsKey(sensorId)) {
			return sensors.get(sensorId);
		}
		return null;
	}

	public Collection<String> getSensorsCode() {
		return sensors.values();
	}

	public Double getTemperature(Integer sensorId) throws Exception {
		String sensor = getSensorCode(sensorId);
		return getTemperature(sensor);
	}

	public Double getTemperature(String sensor) throws Exception {
		W1Master w1Master = new W1Master();
		for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
			if (device.getName().trim().equals(sensor)) {
				return device.getTemperature();
			}
		}
		throw new Exception("Nie można odczytać temperatury z " + sensor);
	}

}
