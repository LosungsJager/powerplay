package org.firstinspires.ftc.robotcontroller.internal.testesCalibragens;
//E0 esq_t
//E1 dir_f

//C0 dir_t
//C1 esq_f
//C2 base_garra

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class SegurarMotor extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
        DcMotor baseGarra = hardwareMap.dcMotor.get("base_garra");

    @Override
    public void runOpMode() throws InterruptedException {
        baseGarra.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        baseGarra.setTargetPosition(5000);

        waitForStart();
        baseGarra.setPower(0.3);
    }
}

