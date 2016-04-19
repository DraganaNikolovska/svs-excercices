package com.acme.adapter;

import com.ventoelectrics.waterheater.VentoThermoregulator;

public interface Thermoregulator extends VentoThermoregulator{

	public void setTemperature(Integer temperature);

	public void enablePower();

	public void disablePower();
}
