package com.ventoelectrics.waterheater;

import com.acme.adapter.*;
import com.acme.termoregulator.StandardTermoregulator;

public class StandardVentoWaterHeaterApp {

	public static void main(String[] args) throws Exception {

		final VentoThermometer ventoThermometer = new VentoThermometer();
		final VentoHeater ventoHeater = new VentoHeater();
		final VentoPowerSwitch ventoPowerSwitch = new VentoPowerSwitch();

		
		final HeaterThermoregulatorAdapter hAdapter = new HeaterThermoregulatorAdapter(ventoHeater);
		final ThermometerThermoregulatorAdapter tAdapter = new ThermometerThermoregulatorAdapter(ventoThermometer);
		
		final VentoThermoregulator ventoThermoregulator = new StandardTermoregulator(hAdapter, tAdapter);

		ventoPowerSwitch.controlPowerFor(ventoThermoregulator);
		ventoPowerSwitch.controlPowerFor(ventoHeater);
		ventoPowerSwitch.controlPowerFor(ventoThermometer);

		VentoWaterHeaterApp.run(ventoThermoregulator, ventoPowerSwitch);
	}
}
