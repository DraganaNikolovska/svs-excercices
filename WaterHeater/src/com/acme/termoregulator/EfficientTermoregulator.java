package com.acme.termoregulator;

import com.acme.adapter.*;

public class EfficientTermoregulator extends Termoregulator {

	public EfficientTermoregulator(HeaterThermoregulatorAdapter heaterAdapter,
			ThermometerThermoregulatorAdapter thermometerAdapter) {
		super(heaterAdapter, thermometerAdapter);
		sleepTime = 1000;
	}

}
