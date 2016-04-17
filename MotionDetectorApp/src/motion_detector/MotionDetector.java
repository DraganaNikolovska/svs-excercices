package motion_detector;

public class MotionDetector {

	private ImageCapturingDevice icDevice;
	private AlarmingDevice aDevice;

	public MotionDetector(ImageCapturingDevice icDevice, AlarmingDevice aDevice) {
		super();
		this.icDevice = icDevice;
		this.aDevice = aDevice;
	}

	public void start() {
		String previousImage = icDevice.captureImage();
		String nextImage;
		while ((nextImage = icDevice.captureImage()) != null) {
			if (!nextImage.equals(previousImage)) {
				this.aDevice.soundAlarm();
			}
			previousImage = nextImage;
		}

	}
}
