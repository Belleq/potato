package org.usfirst.frc.team972.robot;

import edu.wpi.first.wpilibj.*;
import com.ctre.*;

public class Robot extends IterativeRobot {
	// drive
	CANTalon leftMotor = new CANTalon(1);
	CANTalon rightMotor = new CANTalon(2);

	// hooks
	CANTalon hookMotor = new CANTalon(3); // BAG Motor

	DigitalInput upperLimit = new DigitalInput(0);
	DigitalInput lowerLimit = new DigitalInput(1);

	boolean locked = false;
	boolean lastLocked = false;

	// intake
	CANTalon intakeMotor = new CANTalon(4);

	// joysticks
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);

	// reverse drive + squared inputs
	boolean reversed = false;
	boolean squared = false;

	boolean lastSquared = false;

	double comp(double x) {
		x = ((Math.min(0.0, (Math.abs(x) - 0.05)) * x) / Math.abs(x)) / 0.95; // 5%
																				// buffer
		if (reversed) {
			x *= -1;
		}
		if (squared) {
			x *= Math.abs(x);
		}
		return x;
	}

	public void autonomousInit() {
		// go straight
		leftMotor.set(1);
		rightMotor.set(1);

		if (Constants.USING_ENCODER) {
			while (isAutonomous() && isEnabled() && leftMotor.get() < Constants.ENCODER_STEP_ONE);
		} else {
			long current = System.currentTimeMillis();
			while (isAutonomous() && isEnabled() && (current + Constants.TIME_STEP_ONE) > System.currentTimeMillis());
		}

		// turn left
		leftMotor.set(-1);
		rightMotor.set(1);

		if (Constants.USING_ENCODER) {
			while (isAutonomous() && isEnabled() && leftMotor.get() < Constants.ENCODER_STEP_TWO);
		} else {
			long current = System.currentTimeMillis();
			while (isAutonomous() && isEnabled() && (current + Constants.TIME_STEP_TWO) > System.currentTimeMillis());
		}

		// go straight
		leftMotor.set(1);
		rightMotor.set(1);

		if (Constants.USING_ENCODER) {
			while (isAutonomous() && isEnabled() && leftMotor.get() < Constants.ENCODER_STEP_THREE);
		} else {
			long current = System.currentTimeMillis();
			while (isAutonomous() && isEnabled() && (current + Constants.TIME_STEP_THREE) > System.currentTimeMillis());
		}

		// turn around
		leftMotor.set(-1);
		rightMotor.set(1);

		if (Constants.USING_ENCODER) {
			while (isAutonomous() && isEnabled() && leftMotor.get() < Constants.ENCODER_STEP_FOUR);
		} else {
			long current = System.currentTimeMillis();
			while (isAutonomous() && isEnabled() && (current + Constants.TIME_STEP_FOUR) > System.currentTimeMillis());
		}

		// pick up left basket
		hookMotor.set(0.25);
		while (isAutonomous() && isEnabled() && !lowerLimit.get());
		hookMotor.set(0);
		locked = true;

	}

	public void autonomousPeriodic() {

	}

	public void teleopPeriodic() {
		reversed = leftJoy.getRawButton(Constants.REVERSE_BUTTON_LEFTJOY);
		boolean currSqu = leftJoy.getRawButton(Constants.SQUARE_TOGGLE_BUTTON_LEFTJOY);

		if (currSqu && !lastSquared) {
			squared = !squared;
		}
		lastSquared = currSqu;

		leftMotor.set(comp(leftJoy.getY()));
		rightMotor.set(comp(rightJoy.getY()));

		boolean currLocked = leftJoy.getRawButton(Constants.CLAW_TOGGLE_BUTTON_LEFTJOY);

		if (currLocked && !lastLocked) {
			locked = !locked;
		}
		lastLocked = currLocked;
		
		// TODO: Check directions
		if (locked) {
			if (lowerLimit.get()) {
				hookMotor.set(0.25);
			} else {
				hookMotor.set(0);
			}
		} else {
			if (upperLimit.get()) {
				hookMotor.set(-0.25);
			} else {
				hookMotor.set(0);
			}
		}
		
		if (leftJoy.getRawButton(Constants.INTAKE_BUTTON_LEFTJOY)) {
			intakeMotor.set(0.2);
		} else {
			intakeMotor.set(0);
		}
	}

}
