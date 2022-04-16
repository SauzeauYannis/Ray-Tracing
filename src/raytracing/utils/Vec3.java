package raytracing.utils;

public class Vec3 {

	public double x;
	public double y;
	public double z;

	public Vec3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3() {
		this(0.0D, 0.0D, 0.0D);
	}

	public Vec3 add(Vec3 v) {
		return new Vec3(this.x + v.x, this.y + v.y, this.z + v.z);
	}

	public Vec3 sub(Vec3 c) {
		return new Vec3(this.x - c.x, this.y - c.y, this.z - c.z);
	}

	public Vec3 mul(double scale) {
		return new Vec3(this.x * scale, this.y * scale, this.z * scale);
	}

	public Vec3 div(double dotProduct) {
       return new Vec3(this.x / dotProduct, this.y / dotProduct, this.z / dotProduct);
    }

	public double dotProduct(Vec3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

    public void normalize() {
		double length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
		this.x /= length;
		this.y /= length;
		this.z /= length;
	}

}
