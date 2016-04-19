package com.acme.termoregulator;

import com.acme.adapter.*;
import com.ventoelectrics.waterheater.VentoThermoregulator;

public class StandardTermoregulator extends Termoregulator {


	public StandardTermoregulator(HeaterThermoregulatorAdapter heaterAdapter, ThermometerThermoregulatorAdapter thAdapter) {
		super(heaterAdapter, thAdapter);
		sleepTime = 1000;
	}



}
