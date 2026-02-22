package com.yourcompany.math.vector;

/**
 * Immutable class representing a 4D vector with x, y, z, and w components.
 * All operations return new instances without modifying the original vector.
 */
public final class Vector4 {
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    /**
     * Constructs a new Vector4 with the specified x, y, z, and w components.
     *
     * @param x the x component
     * @param y the y component
     * @param z the z component
     * @param w the w component
     */
    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Copy constructor. Creates a new Vector4 with the same components as the given vector.
     *
     * @param v the vector to copy
     * @throws IllegalArgumentException if v is null
     */
    public Vector4(Vector4 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
    }

    /**
     * Returns the x component of this vector.
     *
     * @return the x component
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y component of this vector.
     *
     * @return the y component
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the z component of this vector.
     *
     * @return the z component
     */
    public double getZ() {
        return z;
    }

    /**
     * Returns the w component of this vector.
     *
     * @return the w component
     */
    public double getW() {
        return w;
    }

    /**
     * Adds another Vector4 to this vector and returns a new Vector4.
     *
     * @param v the vector to add
     * @return a new Vector4 representing the sum
     * @throws IllegalArgumentException if v is null
     */
    public Vector4 add(Vector4 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector4(this.x + v.x, this.y + v.y, this.z + v.z, this.w + v.w);
    }

    /**
     * Subtracts another Vector4 from this vector and returns a new Vector4.
     *
     * @param v the vector to subtract
     * @return a new Vector4 representing the difference
     * @throws IllegalArgumentException if v is null
     */
    public Vector4 subtract(Vector4 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector4(this.x - v.x, this.y - v.y, this.z - v.z, this.w - v.w);
    }

    /**
     * Multiplies this vector by a scalar and returns a new Vector4.
     *
     * @param scalar the scalar to multiply by
     * @return a new Vector4 representing the scaled vector
     */
    public Vector4 multiply(double scalar) {
        return new Vector4(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    /**
     * Divides this vector by a scalar and returns a new Vector4.
     *
     * @param scalar the scalar to divide by
     * @return a new Vector4 representing the scaled vector
     * @throws ArithmeticException if scalar is zero
     */
    public Vector4 divide(double scalar) {
        if (scalar == 0.0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Vector4(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }

    /**
     * Calculates the length (magnitude) of this vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Normalizes this vector to have a length of 1 and returns a new Vector4.
     *
     * @return a new normalized Vector4
     * @throws ArithmeticException if this vector is a zero vector
     */
    public Vector4 normalize() {
        double len = length();
        if (len == 0.0) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        return new Vector4(this.x / len, this.y / len, this.z / len, this.w / len);
    }

    /**
     * Calculates the dot product of this vector with another Vector4.
     *
     * @param v the other vector
     * @return the dot product
     * @throws IllegalArgumentException if v is null
     */
    public double dotProduct(Vector4 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector4 vector4 = (Vector4) obj;
        return Double.compare(vector4.x, x) == 0 
            && Double.compare(vector4.y, y) == 0 
            && Double.compare(vector4.z, z) == 0 
            && Double.compare(vector4.w, w) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y) * 17 
            + Double.hashCode(z) * 11 + Double.hashCode(w);
    }

    @Override
    public String toString() {
        return String.format("Vector4(%.4f, %.4f, %.4f, %.4f)", x, y, z, w);
    }
}

