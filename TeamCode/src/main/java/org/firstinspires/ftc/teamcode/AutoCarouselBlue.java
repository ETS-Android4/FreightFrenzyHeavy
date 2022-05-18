package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvWebcam;


// Just a start with the camera.

@Autonomous(name="Blue Carousel")
@Disabled
public class AutoCarouselBlue extends Hardware{
ParkingPosition parkingPosition = ParkingPosition.WAREHOUSE_TOWARDS_SHARED_HUB;

    @Override
    public void runOpMode(){



        hardwareSetup();
        imuSetup();
        waitForStart();
        encoderDrive(0.6,-25,-25);
        carousel.setPower(1);
        sleep(3000);
        carousel.setPower(0);
        encoderDriveAnd(0.7,3,3,3,3);
        rotateToPos(0,0.9);





    }
}
