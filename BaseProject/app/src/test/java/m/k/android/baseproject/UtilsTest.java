package m.k.android.baseproject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.robolectric.annotation.Config;

/**
 * Created by kishimotomasashi on 2015/04/11.
 */
//@RunWith(CustomRobolectricRunner.class)
//@Config(emulateSdk = 21, reportSdk = 21, constants = BuildConfig.class)
public class UtilsTest {

    @Test
    public void test_calculateAngle() {
        // x,y 座標に傾きなし
        Assert.assertEquals("180", 180, Utils.calculateAngle(0, 0), 0.01);

        // x,y 座標のいずれかに傾いている
        Assert.assertEquals("0", 0, Utils.calculateAngle(0, 1), 0.01);
        Assert.assertEquals("90", 90, Utils.calculateAngle(1, 0), 0.01);
        Assert.assertEquals("180", 180, Utils.calculateAngle(0, -1), 0.01);
        Assert.assertEquals("270", -90, Utils.calculateAngle(-1, 0), 0.01);

        // x,y 座標どちらにも傾いている
        Assert.assertEquals("45", 45, Utils.calculateAngle(1, 1), 0.01);
        Assert.assertEquals("135", 135, Utils.calculateAngle(1, -1), 0.01);
        Assert.assertEquals("225", 225, Utils.calculateAngle(-1, -1), 0.01);
        Assert.assertEquals("315", -45, Utils.calculateAngle(-1, 1), 0.01);
    }

    @Test
    public void test_calculateDeviceSlope() {
        final float gravity = 9.8f;
        // x,y 座標に傾きなし
        Assert.assertEquals("180", 180, Utils.calculateDeviceSlope(new float[]{0, 0, 0}), 0.01);

        // x,y 座標のいずれかに傾いている
        Assert.assertEquals("0", 0, Utils.calculateDeviceSlope(new float[]{0, -gravity, 0}), 0.01);
        Assert.assertEquals("90", 90, Utils.calculateDeviceSlope(new float[]{gravity, 0, 0}), 0.01);
        Assert.assertEquals("180", 180, Utils.calculateDeviceSlope(new float[]{0, gravity, 0}), 0.01);
        Assert.assertEquals("270", 270, Utils.calculateDeviceSlope(new float[]{-gravity, 0, 0}), 0.01);

        // x,y 座標どちらにも傾いている
        Assert.assertEquals("45", 45, Utils.calculateDeviceSlope(new float[]{gravity/2, -gravity/2, 0}), 0.01);
        Assert.assertEquals("135", 135, Utils.calculateDeviceSlope(new float[]{gravity/2, gravity/2, 0}), 0.01);
        Assert.assertEquals("225", 225, Utils.calculateDeviceSlope(new float[]{-gravity/2, gravity/2, 0}), 0.01);
        Assert.assertEquals("315", -45, Utils.calculateDeviceSlope(new float[]{-gravity/2, -gravity/2, 0}), 0.01);
    }
}
