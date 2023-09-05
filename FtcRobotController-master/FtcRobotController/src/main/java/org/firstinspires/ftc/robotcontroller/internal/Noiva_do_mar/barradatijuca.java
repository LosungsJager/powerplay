package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class barradatijuca extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");

        waitForStart();

        /*motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double lim = 0.8;
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.right_stick_y * 1.1;

            //ler controles
            double aey = -gamepad1.left_stick_y;
            double aex = gamepad1.left_stick_x;

            double adx = - gamepad1.right_stick_x;
            double ady = gamepad1.right_stick_y;

            } if (gamepad1.dpad_up) {
            motorFrontLeft.setPower(-1);
            motorFrontRight.setPower(1); // aumentar porcentagem
            motorBackLeft.setPower(-1);
            motorBackRight.setPower(1);
        }
    }
}
