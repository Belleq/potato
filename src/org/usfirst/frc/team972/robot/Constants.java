package org.usfirst.frc.team972.robot;

public class Constants {
	// teleop periodic
	public static final int REVERSE_BUTTON_LEFTJOY = 1;
	public static final int SQUARE_TOGGLE_BUTTON_LEFTJOY = 2;
	public static final int CLAW_TOGGLE_BUTTON_LEFTJOY = 3;
	public static final int INTAKE_BUTTON_LEFTJOY = 4;
	
	// autonomous periodic
	public static final boolean USING_ENCODER = false; // other option is time based
	
	/* the four steps:
	 * first go straight
	 * then turn left
	 * then go straight
	 * then turn around
	 * 
	 * obviously after it will pick it up but since the lowering of arm is 
	 * based on the limit switches no calibration is necessary
	 */
	
	// encoder variables
	public static final int ENCODER_STEP_ONE = 5000;
	public static final int ENCODER_STEP_TWO = 5000;
	public static final int ENCODER_STEP_THREE = 5000;
	public static final int ENCODER_STEP_FOUR = 5000;

	// time variables in milliseconds
	public static final int TIME_STEP_ONE = 5000;
	public static final int TIME_STEP_TWO = 5000;
	public static final int TIME_STEP_THREE = 5000;
	public static final int TIME_STEP_FOUR = 5000;
}
