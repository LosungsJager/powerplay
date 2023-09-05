package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Estrondosas extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");
        DcMotor baseGarra = hardwareMap.dcMotor.get("base_garra");
        Servo servogarra = hardwareMap.servo.get("servogarra");

        /*Thread.sleep(20);
        servotrava.setPosition(0.6);
        Thread.sleep(20);
        levantarGarra.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        levantarGarra.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        levantarGarra.setDirection(DcMotorSimple.Direction.REVERSE);
        levantarGarra.setPower(0.35);
        Thread.sleep(2000);
        levantarGarra.setPower(0);*/
        //servotrava.setPosition(0.3);

        /*motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


        }
    }
}
