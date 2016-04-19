package com.acme.adapter;

import com.ventoelectrics.waterheater.VentoThermometer;

public class ThermometerThermoregulatorAdapter implements ThermometerDevice {
	private VentoThermometer thermometer;

	public ThermometerThermoregulatorAdapter(VentoThermometer thermometer) {
		this.thermometer = thermometer;
	}

	@Override
	public Integer getTemperature() {
		return this.thermometer.getTemperature();
	}

}
