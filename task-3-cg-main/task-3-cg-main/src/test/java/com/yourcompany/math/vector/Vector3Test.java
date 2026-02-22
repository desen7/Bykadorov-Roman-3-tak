package com.yourcompany.math.vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Vector3 class.
 */
public class Vector3Test {

    private static final double EPSILON = 1e-10;

    @Test
    @DisplayName("Constructor with x, y, z components")
    void testConstructor() {
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        assertEquals(1.0, v.getX(), EPSILON);
        assertEquals(2.0, v.getY(), EPSILON);
        assertEquals(3.0, v.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Copy constructor")
    void testCopyConstructor() {
        Vector3 original = new Vector3(3.0, 4.0, 5.0);
        Vector3 copy = new Vector3(original);
        assertEquals(original.getX(), copy.getX(), EPSILON);
        assertEquals(original.getY(), copy.getY(), EPSILON);
        assertEquals(original.getZ(), copy.getZ(), EPSILON);
        assertNotSame(original, copy);
    }

    @Test
    @DisplayName("Copy constructor with null throws exception")
    void testCopyConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector3((Vector3) null));
    }

    @Test
    @DisplayName("Add two vectors")
    void testAdd() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(4.0, 5.0, 6.0);
        Vector3 result = v1.add(v2);
        assertEquals(5.0, result.getX(), EPSILON);
        assertEquals(7.0, result.getY(), EPSILON);
        assertEquals(9.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Add with null vector throws exception")
    void testAddNull() {
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        assertThrows(IllegalArgumentException.class, () -> v.add(null));
    }

    @Test
    @DisplayName("Subtract two vectors")
    void testSubtract() {
        Vector3 v1 = new Vector3(5.0, 6.0, 7.0);
        Vector3 v2 = new Vector3(2.0, 3.0, 4.0);
        Vector3 result = v1.subtract(v2);
        assertEquals(3.0, result.getX(), EPSILON);
        assertEquals(3.0, result.getY(), EPSILON);
        assertEquals(3.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Subtract with null vector throws exception")
    void testSubtractNull() {
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        assertThrows(IllegalArgumentException.class, () -> v.subtract(null));
    }

    @Test
    @DisplayName("Multiply vector by scalar")
    void testMultiply() {
        Vector3 v = new Vector3(2.0, 3.0, 4.0);
        Vector3 result = v.multiply(2.0);
        assertEquals(4.0, result.getX(), EPSILON);
        assertEquals(6.0, result.getY(), EPSILON);
        assertEquals(8.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Divide vector by scalar")
    void testDivide() {
        Vector3 v = new Vector3(4.0, 6.0, 8.0);
        Vector3 result = v.divide(2.0);
        assertEquals(2.0, result.getX(), EPSILON);
        assertEquals(3.0, result.getY(), EPSILON);
        assertEquals(4.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Divide by zero throws exception")
    void testDivideByZero() {
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        assertThrows(ArithmeticException.class, () -> v.divide(0.0));
    }

    @Test
    @DisplayName("Calculate vector length")
    void testLength() {
        Vector3 v = new Vector3(2.0, 3.0, 6.0);
        assertEquals(7.0, v.length(), EPSILON); // sqrt(4+9+36) = 7
    }

    @Test
    @DisplayName("Calculate length of zero vector")
    void testLengthZero() {
        Vector3 v = new Vector3(0.0, 0.0, 0.0);
        assertEquals(0.0, v.length(), EPSILON);
    }

    @Test
    @DisplayName("Normalize vector")
    void testNormalize() {
        Vector3 v = new Vector3(2.0, 0.0, 0.0);
        Vector3 normalized = v.normalize();
        assertEquals(1.0, normalized.length(), EPSILON);
        assertEquals(1.0, normalized.getX(), EPSILON);
        assertEquals(0.0, normalized.getY(), EPSILON);
        assertEquals(0.0, normalized.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Normalize zero vector throws exception")
    void testNormalizeZero() {
        Vector3 v = new Vector3(0.0, 0.0, 0.0);
        assertThrows(ArithmeticException.class, () -> v.normalize());
    }

    @Test
    @DisplayName("Dot product of two vectors")
    void testDotProduct() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(4.0, 5.0, 6.0);
        double result = v1.dotProduct(v2);
        assertEquals(32.0, result, EPSILON); // 1*4 + 2*5 + 3*6 = 32
    }

    @Test
    @DisplayName("Dot product with perpendicular vectors")
    void testDotProductPerpendicular() {
        Vector3 v1 = new Vector3(1.0, 0.0, 0.0);
        Vector3 v2 = new Vector3(0.0, 1.0, 0.0);
        double result = v1.dotProduct(v2);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    @DisplayName("Dot product with null vector throws exception")
    void testDotProductNull() {
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        assertThrows(IllegalArgumentException.class, () -> v.dotProduct(null));
    }

    @Test
    @DisplayName("Cross product of two vectors")
    void testCrossProduct() {
        Vector3 v1 = new Vector3(1.0, 0.0, 0.0);
        Vector3 v2 = new Vector3(0.0, 1.0, 0.0);
        Vector3 result = v1.crossProduct(v2);
        assertEquals(0.0, result.getX(), EPSILON);
        assertEquals(0.0, result.getY(), EPSILON);
        assertEquals(1.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Cross product is anti-commutative")
    void testCrossProductAntiCommutative() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(4.0, 5.0, 6.0);
        Vector3 cross1 = v1.crossProduct(v2);
        Vector3 cross2 = v2.crossProduct(v1);
        // cross1 should equal -cross2
        assertEquals(cross1.getX(), -cross2.getX(), EPSILON);
        assertEquals(cross1.getY(), -cross2.getY(), EPSILON);
        assertEquals(cross1.getZ(), -cross2.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Cross product of parallel vectors is zero")
    void testCrossProductParallel() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(2.0, 4.0, 6.0); // v2 = 2 * v1
        Vector3 result = v1.crossProduct(v2);
        assertEquals(0.0, result.getX(), EPSILON);
        assertEquals(0.0, result.getY(), EPSILON);
        assertEquals(0.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Cross product with null vector throws exception")
    void testCrossProductNull() {
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(null));
    }

    @Test
    @DisplayName("Cross product result is perpendicular to both vectors")
    void testCrossProductPerpendicular() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(4.0, 5.0, 6.0);
        Vector3 cross = v1.crossProduct(v2);
        // Cross product should be perpendicular to both
        assertEquals(0.0, v1.dotProduct(cross), EPSILON);
        assertEquals(0.0, v2.dotProduct(cross), EPSILON);
    }

    @Test
    @DisplayName("Equals method")
    void testEquals() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(1.0, 2.0, 3.0);
        assertEquals(v1, v2);
    }

    @Test
    @DisplayName("HashCode consistency")
    void testHashCode() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(1.0, 2.0, 3.0);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    @DisplayName("ToString method")
    void testToString() {
        Vector3 v = new Vector3(1.5, 2.7, 3.9);
        String str = v.toString();
        assertTrue(str.contains("Vector3"));
    }

    @Test
    @DisplayName("Immutability: operations do not modify original")
    void testImmutability() {
        Vector3 original = new Vector3(1.0, 2.0, 3.0);
        Vector3 added = original.add(new Vector3(1.0, 1.0, 1.0));
        Vector3 cross = original.crossProduct(new Vector3(1.0, 0.0, 0.0));
        
        assertEquals(1.0, original.getX(), EPSILON);
        assertEquals(2.0, original.getY(), EPSILON);
        assertEquals(3.0, original.getZ(), EPSILON);
        assertNotSame(original, added);
        assertNotSame(original, cross);
    }
}

