package net.minecraft.util;

public class Vector3d
{
    public double x;
    public double y;
    public double z;

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public Vector3d()
    {
        x = y = z = 0.0D;
    }
    public Vector3d(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
