package dev.maroo.temp.repo;

import dev.maroo.temp.api.Sensor;
import dev.maroo.temp.api.Temperature;
import dev.maroo.temp.api.TemperaturesStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Maroo
 */
public class TempRepositoryImpl implements TempRepository{
	
	private TemperatureSensorReader sensorReader;
	private SensorRepository sensorRepository;

	public TempRepositoryImpl() {
		sensorReader = new TemperatureSensorReader();
		sensorRepository = new SensorRepositoryImpl();
	}
	

	@Override
	public List<Temperature> temperatures() {
		return Arrays.asList(
		);
	}

	@Override
	public Temperature temperature(int sensorId) {
		try {
			String sensorCode = sensorReader.getSensorCode(sensorId);
			Sensor sensor = sensorRepository.sensor(sensorId);
			Double temperature = sensorReader.getTemperature(sensorCode);
			return Temperature.builder().sensor(sensor).value(temperature).createdDate(LocalDateTime.now()).build();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public TemperaturesStatus chceckError() {
		List<Sensor> sensors = sensorRepository.sensors();
		for(Sensor sensor : sensors) {
			try {
				Double temperature = sensorReader.getTemperature(sensor.getCode());
				if(temperature > new Double(sensor.getMaxTemp())) {
					return TemperaturesStatus.builder().isError(true).build();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return TemperaturesStatus.builder().isError(false).build();
	}
	
}
