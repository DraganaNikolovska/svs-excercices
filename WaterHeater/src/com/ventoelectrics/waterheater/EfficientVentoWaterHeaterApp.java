package com.ventoelectrics.waterheater;

import com.acme.termoregulator.EfficientTermoregulator;
import com.acme.adapter.*;

public class EfficientVentoWaterHeaterApp {

	public static void main(String[] args) throws Exception {

		final VentoThermometer ventoThermometer = new VentoThermometer();
		final VentoHeater ventoHeater = new VentoHeater();
		final VentoPowerSwitch ventoPowerSwitch = new VentoPowerSwitch();
		
		final HeaterThermoregulatorAdapter heaterAdapter = new HeaterThermoregulatorAdapter(ventoHeater);
		final ThermometerThermoregulatorAdapter thermometarAdapter = new ThermometerThermoregulatorAdapter(ventoThermometer);
		final VentoThermoregulator ventoThermoregulator = new EfficientTermoregulator(heaterAdapter, thermometarAdapter);

		ventoPowerSwitch.controlPowerFor(ventoThermoregulator);
		ventoPowerSwitch.controlPowerFor(ventoHeater);
		ventoPowerSwitch.controlPowerFor(ventoThermometer);

		VentoWaterHeaterApp.run(ventoThermoregulator, ventoPowerSwitch);
	}
}
