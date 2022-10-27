package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Disabled
@TeleOp
public class CalibragemCharlie extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override

    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");

        float lim = 0.5f;

        float potBR = 1.0f;
        float potFR = 1.0f;
        float potBl = 1.0f;
        float potFL = 1.0f;

        double faixaA = 0.0;
        double faixaB = 0.0;
        double faixaC = 0.0;

        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setTargetPosition(5000);
        motorBackLeft.setTargetPosition(5000);
        motorFrontRight.setTargetPosition(5000);
        motorBackRight.setTargetPosition(5000);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorFrontLeft.setPower(potFL);
        motorBackLeft.setPower(potBl);
        motorFrontRight.setPower(potFR);
        motorBackRight.setPower(potBR);

        Thread.sleep(1000);

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

        Thread.sleep(1000);
        float PFL = motorFrontLeft.getCurrentPosition();
        float PFR = motorFrontRight.getCurrentPosition();
        float PBL = motorBackLeft.getCurrentPosition();
        float PBR = motorBackRight.getCurrentPosition();
        float media = 0.0f;
        float subtracao = 0.05f;
        telemetry.addData("Frente direita", PFR);
        telemetry.addData("Frente esquerda", PFL);
        telemetry.addData("Trás direita", PBR);
        telemetry.addData("Trás esquerda", PBL);
        telemetry.update();

        Thread.sleep(2000);

        float motores[] = {PFL, PFR, PBL, PBR};
        int id_motor = 0;
        float valor_m = 0;
        for (int i = 0; i < 4; i++) { //check which motor spin the less
            if (valor_m > motores[i])
                valor_m = motores[i];
            id_motor = i + 1;
        }
        telemetry.addData("menor motor", id_motor);
        telemetry.update();
        Thread.sleep(2000);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            PFL = motorFrontLeft.getCurrentPosition(); //1
            PFR = motorFrontRight.getCurrentPosition(); //2
            PBL = motorBackLeft.getCurrentPosition(); //3
            PBR = motorBackRight.getCurrentPosition(); //4;

            if (PFL > 2500) potFL = potFL - subtracao;
            if (PFR > 2500) potFR = potFR - subtracao;
            if (PBL > 2500) potBl = potBl - subtracao;
            if (PBR > 2500) potBR = potBR - subtracao;

            motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            telemetry.addData("Trás direita", potFR); //back right
            telemetry.addData("Trás esquerda", potFL); //back left
            telemetry.addData("Frente direita", potBR); //front right
            telemetry.addData("Frente esquerda", potBl); //front left
            telemetry.update();

            motorFrontLeft.setPower(potFL);
            motorBackLeft.setPower(potBl);
            motorFrontRight.setPower(potFR);
            motorBackRight.setPower(potBR);

            Thread.sleep(1000);

            motorFrontLeft.setPower(0);
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);
            telemetry.clearAll();

            telemetry.addData("Trás direita", PFR); //back right
            telemetry.addData("Trás esquerda", PFL); //back left
            telemetry.addData("Frente direita", PBR); //front right
            telemetry.addData("Frente esquerda", PBL); //front left

            telemetry.update();

            Thread.sleep(2000);
        }
    }
}

