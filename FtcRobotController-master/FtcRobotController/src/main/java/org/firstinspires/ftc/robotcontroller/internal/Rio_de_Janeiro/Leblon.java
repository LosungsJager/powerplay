package org.firstinspires.ftc.robotcontroller.internal.Rio_de_Janeiro;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Leblon extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");
        DcMotor levantarGarra = hardwareMap.dcMotor.get("levantarGarra");
        Servo servogarra = hardwareMap.servo.get("servogarra");
        Servo servotrava = hardwareMap.servo.get("servotrava");

        waitForStart();

        /*motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double lim = 0.5;
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.right_stick_y * 1.1;

            //ler controles
            double aey = -gamepad1.left_stick_y;
            double aex = gamepad1.left_stick_x;

            double adx = gamepad1.right_stick_x;
            double ady = gamepad1.right_stick_y;

            if (aex < 0 || gamepad1.dpad_left) {
                motorFrontLeft.setPower(-lim);
                motorFrontRight.setPower(lim);
                motorBackLeft.setPower(lim * 0.9);
                motorBackRight.setPower(-lim);
                //esquerda
            } else if (aex > 0 || gamepad1.dpad_right) {
                motorFrontLeft.setPower(lim);
                motorFrontRight.setPower(-lim);
                motorBackLeft.setPower(-lim * 0.9);
                motorBackRight.setPower(lim);
                //direta
            } else if (aey < 0 || gamepad1.dpad_down) {
                motorFrontLeft.setPower(-lim);
                motorFrontRight.setPower(-lim);
                motorBackLeft.setPower(lim);
                motorBackRight.setPower(lim);
                //ré
            } else if (aey > 0 || gamepad1.dpad_up) {
                motorFrontLeft.setPower(lim);
                motorFrontRight.setPower(lim);
                motorBackLeft.setPower(-lim);
                motorBackRight.setPower(-lim);
                //frente
            } else if (adx == -1) {
                motorFrontLeft.setPower(-lim);
                motorFrontRight.setPower(lim);
                motorBackLeft.setPower(-lim);
                motorBackRight.setPower(lim);
                //reverso para esquerda
            } else if (adx == 1) {
                motorFrontLeft.setPower(lim);
                motorFrontRight.setPower(-lim);
                motorBackLeft.setPower(lim);
                motorBackRight.setPower(-lim);
                //reverso para direita
            } else {
                motorFrontLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
                //parado

            }
        }
    }
}
