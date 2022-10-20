package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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
        float media = 0.0f;

        telemetry.addData("Frente direita", PFR);
        telemetry.addData("Frente esquerda", PFL);
        telemetry.addData("Trás direita", PBR);
        telemetry.addData("Trás esquerda", PBL);
        telemetry.update();

        Thread.sleep(2000);

        float motores[]= {PFL,PFR,PBL,PBR};
        int id_motor = 0;
        float valor_m = 0;
        for( int i=0; i<4; i++){
            if(valor_m>motores[i])
                valor_m = motores[i];
            id_motor = i+1;
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
            PBR = motorBackRight.getCurrentPosition(); //4

            switch (id_motor){
                case 1:
                    if(PFR / PFL < 0.9) potFR =- 0.1;
                    if(PBL / PFL < 0.9) potBl =- 0.1;
                    if(PBR / PFL < 0.9) potBR =- 0.1;
                    break;
                case 2:
                    if(PFL / PFR < 0.9) potFL =- 0.1;
                    if(PBL / PFR < 0.9) potBl =- 0.1;
                    if(PBR / PFR < 0.9) potBR =- 0.1;
                    break;
                case 3:
                    if(PFL / PBL < 0.9) potFL =- 0.1;
                    if(PFR / PBL < 0.9) potFR =- 0.1;
                    if(PBR / PBL < 0.9) potBR =- 0.1;
                    break;
                case 4:
                    if(PFL / PBR < 0.9) potFL =- 0.1;
                    if(PFR / PBR < 0.9) potFR =- 0.1;
                    if(PBL / PBR < 0.9) potBl =- 0.1;
                    break;
            }
            //rotina movimentar os motores
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
            telemetry.clearAll();
            telemetry.addData("Frente direita", PFR);
            telemetry.addData("Frente esquerda", PFL);
            telemetry.addData("Trás direita", PBR);
            telemetry.addData("Trás esquerda", PBL);
            telemetry.update();
            Thread.sleep(1000);
        }
    }
}

