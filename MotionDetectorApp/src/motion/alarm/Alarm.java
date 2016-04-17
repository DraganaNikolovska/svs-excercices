package motion.alarm;

import motion_detector.AlarmingDevice;

public class Alarm implements AlarmingDevice{

	@Override
	public void soundAlarm() {
		System.out.println("ALARM!!!");
		
	}

}
