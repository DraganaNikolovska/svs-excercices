package app;

import motion.alarm.Alarm;
import motion.camera.Camera;
import motion_detector.AlarmingDevice;
import motion_detector.ImageCapturingDevice;
import motion_detector.MotionDetector;

public class MotionDetectorApp {

	public static void main(String[] args) {
		ImageCapturingDevice icDevice = new Camera();
		AlarmingDevice aDevice = new Alarm();
		MotionDetector motionDetector = new MotionDetector(icDevice, aDevice);
		motionDetector.start();
	}
}
