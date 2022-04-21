package raytracing.utils;

public class Vec3 {

	private double x;
	private double y;
	private double z;

	public Vec3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3() {
		this(0.0D, 0.0D, 0.0D);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
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

	public double dotProduct(Vec3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

    public void normalize() {
		double lengthSquare = this.dotProduct(this);
		if (lengthSquare > 0.0D) {
			double length = Math.sqrt(lengthSquare);
			this.x /= length;
			this.y /= length;
			this.z /= length;
		}
	}

}
