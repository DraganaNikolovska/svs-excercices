package com.acme.termoregulator;

import com.acme.adapter.HeaterDevice;
import com.acme.adapter.ThermometerDevice;

public class EfficientTermoregulator extends Termoregulator {

	public EfficientTermoregulator(HeaterDevice heaterDevice, ThermometerDevice thermometerDevice) {
		super(heaterDevice, thermometerDevice);
		sleepTime = 1000;
	}

}
