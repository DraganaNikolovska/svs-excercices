package com.acme.adapter;


import com.ventoelectrics.waterheater.VentoHeater;

public class HeaterThermoregulatorAdapter implements HeaterDevice {

	private VentoHeater ventoHeater;

	public HeaterThermoregulatorAdapter(VentoHeater ventoHeater) {
		this.ventoHeater = ventoHeater;
	}

	@Override
	public void enable() {
		ventoHeater.enable();

	}

	@Override
	public void disable() {
		ventoHeater.disable();

	}

}
