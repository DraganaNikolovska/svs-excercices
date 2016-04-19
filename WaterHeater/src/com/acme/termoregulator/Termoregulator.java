package com.acme.termoregulator;

import com.acme.adapter.HeaterDevice;
import com.acme.adapter.HeaterThermoregulatorAdapter;
import com.acme.adapter.ThermometerDevice;
import com.acme.adapter.Thermoregulator;

public abstract class Termoregulator implements Thermoregulator, Runnable {

	protected HeaterDevice heaterDevice;
	protected ThermometerDevice thermometerDevice;
	protected boolean enabled;
	protected int configuredTemperature;
	protected int sleepTime;

	public Termoregulator(HeaterDevice heaterDevice, ThermometerDevice thermometerAdapter) {

		this.heaterDevice = heaterDevice;
		this.thermometerDevice = thermometerAdapter;
		this.enabled = false;

	}

	public void setTemperature(Integer temperature) {
		this.configuredTemperature = temperature;
		System.out.println("configured temperature " + temperature);
	}

	public void enablePower() {
		enabled = true;
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void disablePower() {
		enabled = false;

	}

	@Override
	public void run() {
		while (enabled) {
			try {
				int currentTemperature = thermometerDevice.getTemperature();

				System.out.println("current temperature " + currentTemperature);
				if (currentTemperature > this.configuredTemperature) {
					heaterDevice.disable();
				} else {
					heaterDevice.enable();
				}
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {

			}
		}

	}

}
