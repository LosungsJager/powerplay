package org.firstinspires.ftc.robotcontroller.internal.Rio_de_Janeiro;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Ipanema extends LinearOpMode {
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

            if(gamepad1.a){
               servogarra.setPosition(0.75); //abrir garra
            }

            if(gamepad1.b){
                servogarra.setPosition(1); //fechar garra
            }

            baseGarra.setPower(gamepad1.right_trigger * 0.8);
            baseGarra.setPower(-gamepad1.left_trigger * 0.8);

            double lim = 0.4; //valor original = 1
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
                motorFrontLeft.setPower(0.8 * lim);
                motorFrontRight.setPower(0.8 * -lim);
                motorBackLeft.setPower(0.8 * lim);
                motorBackRight.setPower(0.8 * -lim);
                //reverso para esquerda
            } else if (adx == 1) {
                motorFrontLeft.setPower(0.8 * -lim);
                motorFrontRight.setPower(0.8 * lim);
                motorBackLeft.setPower(0.8 * -lim);
                motorBackRight.setPower(0.8 * lim);
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
