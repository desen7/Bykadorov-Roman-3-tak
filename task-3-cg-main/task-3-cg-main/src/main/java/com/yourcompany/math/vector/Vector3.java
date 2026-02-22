package com.yourcompany.math.vector;

/**
 * Immutable class representing a 3D vector with x, y, and z components.
 * All operations return new instances without modifying the original vector.
 */
public final class Vector3 {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Constructs a new Vector3 with the specified x, y, and z components.
     *
     * @param x the x component
     * @param y the y component
     * @param z the z component
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor. Creates a new Vector3 with the same components as the given vector.
     *
     * @param v the vector to copy
     * @throws IllegalArgumentException if v is null
     */
    public Vector3(Vector3 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
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
     * Adds another Vector3 to this vector and returns a new Vector3.
     *
     * @param v the vector to add
     * @return a new Vector3 representing the sum
     * @throws IllegalArgumentException if v is null
     */
    public Vector3 add(Vector3 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * Subtracts another Vector3 from this vector and returns a new Vector3.
     *
     * @param v the vector to subtract
     * @return a new Vector3 representing the difference
     * @throws IllegalArgumentException if v is null
     */
    public Vector3 subtract(Vector3 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    /**
     * Multiplies this vector by a scalar and returns a new Vector3.
     *
     * @param scalar the scalar to multiply by
     * @return a new Vector3 representing the scaled vector
     */
    public Vector3 multiply(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * Divides this vector by a scalar and returns a new Vector3.
     *
     * @param scalar the scalar to divide by
     * @return a new Vector3 representing the scaled vector
     * @throws ArithmeticException if scalar is zero
     */
    public Vector3 divide(double scalar) {
        if (scalar == 0.0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Vector3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    /**
     * Calculates the length (magnitude) of this vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Normalizes this vector to have a length of 1 and returns a new Vector3.
     *
     * @return a new normalized Vector3
     * @throws ArithmeticException if this vector is a zero vector
     */
    public Vector3 normalize() {
        double len = length();
        if (len == 0.0) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        return new Vector3(this.x / len, this.y / len, this.z / len);
    }

    /**
     * Calculates the dot product of this vector with another Vector3.
     *
     * @param v the other vector
     * @return the dot product
     * @throws IllegalArgumentException if v is null
     */
    public double dotProduct(Vector3 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    /**
     * Calculates the cross product of this vector with another Vector3.
     * The cross product returns a vector perpendicular to both input vectors.
     *
     * @param v the other vector
     * @return a new Vector3 representing the cross product
     * @throws IllegalArgumentException if v is null
     */
    public Vector3 crossProduct(Vector3 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector3(
            this.y * v.z - this.z * v.y,
            this.z * v.x - this.x * v.z,
            this.x * v.y - this.y * v.x
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3 vector3 = (Vector3) obj;
        return Double.compare(vector3.x, x) == 0 
            && Double.compare(vector3.y, y) == 0 
            && Double.compare(vector3.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y) * 17 + Double.hashCode(z);
    }

    @Override
    public String toString() {
        return String.format("Vector3(%.4f, %.4f, %.4f)", x, y, z);
    }
}

