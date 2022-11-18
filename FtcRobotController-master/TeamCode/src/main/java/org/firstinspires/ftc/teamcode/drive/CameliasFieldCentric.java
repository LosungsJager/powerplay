package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "drive")
public class CameliasFieldCentric extends LinearOpMode {
    double modulo = 0;
    public double Hipotenusa(double a, double b){
        double catA = Math.pow(a, 2);
        double catB = Math.pow(b, 2);
        double s = catA + catB;
        double r = Math.sqrt(s);
        return(r);
    }
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();


            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            modulo = Hipotenusa(x, y);
            double angRad = Math.atan2(x, y);
            double angGr = Math.toDegrees(angRad);
            if(angGr < 0){
                angGr = 360 + angGr;
            }
            Pose2d poseEstimate = drive.getPoseEstimate();
            double angReal = angGr + poseEstimate.getHeading();
            if(angReal > 360){
                angReal = angReal % 360;
            }
            double angRobo = Math.toDegrees(poseEstimate.getHeading());
            double Xaj = modulo * Math.sin(angReal);
            double Yaj = modulo * Math.cos(angReal);
            telemetry.addData("Angulo controle", angGr);
            telemetry.addData("Angulo robo", angRobo);
            telemetry.addData("Angulo real", angReal);
            telemetry.addData("X ajustado", Xaj);
            telemetry.addData("Y ajustado", Yaj);
            telemetry.update();
        }
    }
}
