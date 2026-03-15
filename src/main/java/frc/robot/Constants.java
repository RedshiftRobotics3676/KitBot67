package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;

public final class Constants {

    public static final class CANConstants {
        public static final int FRONT_LEFT_MOTOR_ID  = 0;
        public static final int BACK_LEFT_MOTOR_ID   = 1;
        public static final int FRONT_RIGHT_MOTOR_ID = 2;
        public static final int BACK_RIGHT_MOTOR_ID  = 3;

        public static final int SHOOTER_MOTOR_ID = 4;
        public static final int INDEXER_MOTOR_ID = 5;
    }

    public static final class ShooterConstants {
        public static final double SHOOTER_FEED_TARGET_VELOCITY = 5; 
        public static final double INDEXER_INTAKE_TARGET_VELOCITY = 5; 
        public static final double INDEXER_SHOOT_TARGET_VELOCITY = -5; 

        public static final MotionMagicVelocityDutyCycle SHOOTER_FEED_DUTY_CYCLE =
                new MotionMagicVelocityDutyCycle(SHOOTER_FEED_TARGET_VELOCITY);
        public static final MotionMagicVelocityDutyCycle INDEXER_INTAKE_DUTY_CYCLE =
                new MotionMagicVelocityDutyCycle(INDEXER_INTAKE_TARGET_VELOCITY);
        public static final MotionMagicVelocityDutyCycle INDEXER_SHOOT_DUTY_CYCLE =
                new MotionMagicVelocityDutyCycle(INDEXER_SHOOT_TARGET_VELOCITY);
        public static final MotionMagicVelocityDutyCycle INACTIVE_DUTY_CYCLE =
                new MotionMagicVelocityDutyCycle(0);

        public static TalonFXConfiguration createShooterConfig() {
            TalonFXConfiguration config = new TalonFXConfiguration();

            config.Voltage.PeakForwardVoltage = 11.5;
            config.Voltage.PeakReverseVoltage = -11.5;

            config.Slot0.kP = 0.0;
            config.Slot0.kI = 0.0;
            config.Slot0.kD = 0.0;
            config.Slot0.kV = 0.11; // ~1 / max velocity (rps)
            config.Slot0.kS = 0.01; // static friction compensation

            config.MotionMagic.MotionMagicCruiseVelocity = 10;
            config.MotionMagic.MotionMagicAcceleration = 150;

            return config;
        }

        public static TalonFXConfiguration createIndexerConfig() {
            TalonFXConfiguration config = new TalonFXConfiguration();

            config.Voltage.PeakForwardVoltage = 11.5;
            config.Voltage.PeakReverseVoltage = -11.5;

            config.Slot0.kP = 0.0;
            config.Slot0.kI = 0.0;
            config.Slot0.kD = 0.0;
            config.Slot0.kV = 0.11;
            config.Slot0.kS = 0.01;

            config.MotionMagic.MotionMagicCruiseVelocity = 20;
            config.MotionMagic.MotionMagicAcceleration = 100;

            return config;
        }
    }
}