package m.k.android.baseproject;

import android.support.annotation.NonNull;

/**
 * Created by kishimotomasashi on 2015/04/11.
 */
public class Utils {

    /**
     * 直線((0,0)-(x,y)) と y軸の角度を計算する
     * 右回転を正の値とする
     * (x,y) = (0,0) の場合は、180 を返却する
     * @param x x座標
     * @param y y座標
     * @return -90 - 270
     */
    public static double calculateAngle(float x, float y) {
        if(x == 0 && y == 0) {
            return 180;
        }

        return -(Math.atan2(y, x) * 180/Math.PI - 90);
    }

    /**
     * 重力センサーの値から端末の傾きを計算する
     * @param gravityValues
     * @return
     */
    public static double calculateDeviceSlope(@NonNull float[] gravityValues) {
        return calculateAngle(gravityValues[0], -gravityValues[1]);
    }
}
