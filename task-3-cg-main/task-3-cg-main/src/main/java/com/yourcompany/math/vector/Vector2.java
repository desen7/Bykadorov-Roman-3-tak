package com.yourcompany.math.vector;

/**
 * Immutable class representing a 2D vector with x and y components.
 * All operations return new instances without modifying the original vector.
 */
public final class Vector2 {
    private final double x;
    private final double y;

    /**
     * Constructs a new Vector2 with the specified x and y components.
     *
     * @param x the x component
     * @param y the y component
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor. Creates a new Vector2 with the same components as the given vector.
     *
     * @param v the vector to copy
     * @throws IllegalArgumentException if v is null
     */
    public Vector2(Vector2 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        this.x = v.x;
        this.y = v.y;
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
     * Adds another Vector2 to this vector and returns a new Vector2.
     *
     * @param v the vector to add
     * @return a new Vector2 representing the sum
     * @throws IllegalArgumentException if v is null
     */
    public Vector2 add(Vector2 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    /**
     * Subtracts another Vector2 from this vector and returns a new Vector2.
     *
     * @param v the vector to subtract
     * @return a new Vector2 representing the difference
     * @throws IllegalArgumentException if v is null
     */
    public Vector2 subtract(Vector2 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    /**
     * Multiplies this vector by a scalar and returns a new Vector2.
     *
     * @param scalar the scalar to multiply by
     * @return a new Vector2 representing the scaled vector
     */
    public Vector2 multiply(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    /**
     * Divides this vector by a scalar and returns a new Vector2.
     *
     * @param scalar the scalar to divide by
     * @return a new Vector2 representing the scaled vector
     * @throws ArithmeticException if scalar is zero
     */
    public Vector2 divide(double scalar) {
        if (scalar == 0.0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    /**
     * Calculates the length (magnitude) of this vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes this vector to have a length of 1 and returns a new Vector2.
     *
     * @return a new normalized Vector2
     * @throws ArithmeticException if this vector is a zero vector
     */
    public Vector2 normalize() {
        double len = length();
        if (len == 0.0) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        return new Vector2(this.x / len, this.y / len);
    }

    /**
     * Calculates the dot product of this vector with another Vector2.
     *
     * @param v the other vector
     * @return the dot product
     * @throws IllegalArgumentException if v is null
     */
    public double dotProduct(Vector2 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return this.x * v.x + this.y * v.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector2 vector2 = (Vector2) obj;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y);
    }

    @Override
    public String toString() {
        return String.format("Vector2(%.4f, %.4f)", x, y);
    }
}

