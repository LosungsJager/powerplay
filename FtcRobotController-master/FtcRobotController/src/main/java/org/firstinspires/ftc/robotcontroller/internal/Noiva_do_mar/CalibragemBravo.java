package org.firstinspires.ftc.robotcontroller.internal.Noiva_do_mar;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class CalibragemBravo extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override

    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("esq_f");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("esq_t");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("dir_f");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("dir_t");

        double lim = 0.5;

        double potBR = 1.0;
        double potFR = 1.0;
        double potBl = 1.0;
        double potFL = 1.0;

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
        float Media = 0.0f;

        telemetry.addData("Frente direita", PFR);
        telemetry.addData("Frente esquerda", PFL);
        telemetry.addData("Trás direita", PBR);
        telemetry.addData("Trás esquerda", PBL);
        telemetry.update();
/*
        telemetry.addData("Frente direita", motorFrontRight.getCurrentPosition());
        telemetry.addData("Frente esquerda", motorFrontLeft.getCurrentPosition());
        telemetry.addData("Trás direita", motorBackRight.getCurrentPosition());
        telemetry.addData("Trás esquerda", motorBackLeft.getCurrentPosition());
        telemetry.update();
*/

        Thread.sleep(5000);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            PFL = motorFrontLeft.getCurrentPosition();
            PFR = motorFrontRight.getCurrentPosition();
            PBL = motorBackLeft.getCurrentPosition();
            PBR = motorBackRight.getCurrentPosition();

            faixaA = PFR / PFL;
            faixaB = PFL / PBR;
            faixaC = PBR / PBL;

            telemetry.addData("Faixa A", faixaA);
            telemetry.addData("Faixa B", faixaB);
            telemetry.addData("Faixa C", faixaC);

            while (faixaA > 1.1 || faixaA < 0.9 ||
                    faixaB > 1.1 || faixaB < 0.9 ||
                    faixaC > 1.1 || faixaC < 0.9){
                PFL = motorFrontLeft.getCurrentPosition();
                PFR = motorFrontRight.getCurrentPosition();
                PBL = motorBackLeft.getCurrentPosition();
                PBR = motorBackRight.getCurrentPosition();
                faixaA = PFR / PFL;
                faixaB = PFL / PBR;
                faixaC = PBR / PBL;
                media = (PFL + PBL + PFR + PBR) / 4;
            //Frente Direita
                if(PFR > media) potFR =- 0.1;
                if(PFR < media) potFR =+ 0.1;
            //Frente Esquerda
                if(PFL > media) potFL =- 0.1;
                if(PFL < media) potFL =+ 0.1;
            //Trás Direita
                if(PBR > media) potBR =- 0.1;
                if(PBR < media) potBR =+ 0.1;
            //Trás Esquerda
                if(PBL > media) potBL =- 0.1;
                if(PBL < media) potBL =+ 0.1;

                motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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
                telemetry.update();
        }
    }
}

