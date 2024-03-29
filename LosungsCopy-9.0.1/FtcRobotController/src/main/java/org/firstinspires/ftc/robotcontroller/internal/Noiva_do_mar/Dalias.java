package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Dalias extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {

        // Declare our motors   roger pc   1
        // Make sure your ID's match your configuration
        //usar CHARLIE como configuração
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");
        DcMotor baseGarra = hardwareMap.dcMotor.get("base_garra");
        DcMotor levantarGarra = hardwareMap.dcMotor.get("levantarGarra");
        Servo servogarra = hardwareMap.servo.get("servogarra");
        Servo servotrava = hardwareMap.servo.get("servotrava");
        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests

        Thread.sleep(20);
        servotrava.setPosition(0.6);
        Thread.sleep(20);
        levantarGarra.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        levantarGarra.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        levantarGarra.setDirection(DcMotorSimple.Direction.REVERSE);
        levantarGarra.setPower(0.35);
        Thread.sleep(2000);
        levantarGarra.setPower(0);
        servotrava.setPosition(0.3);
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        double lim = 0.6;
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;


            if(gamepad1.dpad_up) {
                lim = lim + 0.1;
                Thread.sleep(1000);
            }
            if(gamepad1.dpad_down){
                lim = lim - 0.1;
                Thread.sleep(1000);
            }
//range servo é de 0 a 1
            if(gamepad1.a){
                //abrir servo
                servogarra.setPosition(0.75);

            }
            if(gamepad1.b){
                //fechar servo
                servogarra.setPosition(1);
            }

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = -((y + x + rx) / denominator)*lim;
            telemetry.addData("frente esquerda:", frontLeftPower);
            double backLeftPower = ((y - x + rx) / denominator)*lim;
            telemetry.addData("trás esquerda:", backLeftPower);
            double frontRightPower = ((y - x - rx) / denominator)*lim;
            telemetry.addData("frente direita:", frontRightPower);
            double backRightPower = ((y + x - rx) / denominator)*lim;
            telemetry.addData("trás direita:", backRightPower);

            telemetry.addData("velocidade:", lim);
            telemetry.update();

            baseGarra.setPower(gamepad1.right_trigger);
            baseGarra.setPower(-gamepad1.left_trigger);

            //motorFrontLeft.setPower(frontLeftPower * 0.78);
            //motorBackLeft.setPower(backLeftPower * 0.78);
            motorFrontLeft.setPower(frontLeftPower * 0.91);
            motorBackLeft.setPower(backLeftPower * 0.91);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
}