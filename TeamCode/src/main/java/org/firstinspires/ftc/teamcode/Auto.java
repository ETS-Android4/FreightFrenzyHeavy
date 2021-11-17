package org.firstinspires.ftc.teamcode;

public class Auto extends Hardware {
    // Fake values for now. Just showing how to call it
    //TensorflowDetector recongnizer = new TensorflowDetector(10,20, 1);

    @Override
    public void runOpMode(){
        //Prep for tensorflow:
        //int place = recongnizer.recognizeObjects();
        // Then move to the place.

        //Drive forward
        telemetry.addData("Status", "Driving Forward");
        telemetry.update();
        driveForward(0.5);
        sleep(1000);

        driveForward(0);
        sleep(200);

        telemetry.addData("Status","Driving Backward");
        telemetry.update();
        driveForward(-0.5);
        sleep(1000);
        driveForward(0);
    }

}
