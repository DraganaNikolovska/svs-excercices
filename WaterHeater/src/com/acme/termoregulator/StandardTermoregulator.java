package com.acme.termoregulator;

import com.acme.adapter.HeaterDevice;
import com.acme.adapter.ThermometerDevice;

public class StandardTermoregulator extends Termoregulator {

	public StandardTermoregulator(HeaterDevice heaterDevice, ThermometerDevice thermometerDevice) {
		super(heaterDevice, thermometerDevice);
		sleepTime = 1000;
	}

}
