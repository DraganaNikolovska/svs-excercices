package motion.camera;

import java.util.Scanner;

import motion_detector.ImageCapturingDevice;

public class Camera implements ImageCapturingDevice {

	private Scanner scanner;

	public Camera() {
		scanner = new Scanner(System.in);
	}

	@Override
	public String captureImage() {
		String image = scanner.nextLine();
		return (image);
	}

}
