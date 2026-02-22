package com.yourcompany.math.vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Vector4 class.
 */
public class Vector4Test {

    private static final double EPSILON = 1e-10;

    @Test
    @DisplayName("Constructor with x, y, z, w components")
    void testConstructor() {
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertEquals(1.0, v.getX(), EPSILON);
        assertEquals(2.0, v.getY(), EPSILON);
        assertEquals(3.0, v.getZ(), EPSILON);
        assertEquals(4.0, v.getW(), EPSILON);
    }

    @Test
    @DisplayName("Copy constructor")
    void testCopyConstructor() {
        Vector4 original = new Vector4(3.0, 4.0, 5.0, 6.0);
        Vector4 copy = new Vector4(original);
        assertEquals(original.getX(), copy.getX(), EPSILON);
        assertEquals(original.getY(), copy.getY(), EPSILON);
        assertEquals(original.getZ(), copy.getZ(), EPSILON);
        assertEquals(original.getW(), copy.getW(), EPSILON);
        assertNotSame(original, copy);
    }

    @Test
    @DisplayName("Copy constructor with null throws exception")
    void testCopyConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector4((Vector4) null));
    }

    @Test
    @DisplayName("Add two vectors")
    void testAdd() {
        Vector4 v1 = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 v2 = new Vector4(5.0, 6.0, 7.0, 8.0);
        Vector4 result = v1.add(v2);
        assertEquals(6.0, result.getX(), EPSILON);
        assertEquals(8.0, result.getY(), EPSILON);
        assertEquals(10.0, result.getZ(), EPSILON);
        assertEquals(12.0, result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Add with null vector throws exception")
    void testAddNull() {
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> v.add(null));
    }

    @Test
    @DisplayName("Subtract two vectors")
    void testSubtract() {
        Vector4 v1 = new Vector4(5.0, 6.0, 7.0, 8.0);
        Vector4 v2 = new Vector4(2.0, 3.0, 4.0, 5.0);
        Vector4 result = v1.subtract(v2);
        assertEquals(3.0, result.getX(), EPSILON);
        assertEquals(3.0, result.getY(), EPSILON);
        assertEquals(3.0, result.getZ(), EPSILON);
        assertEquals(3.0, result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Subtract with null vector throws exception")
    void testSubtractNull() {
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> v.subtract(null));
    }

    @Test
    @DisplayName("Multiply vector by scalar")
    void testMultiply() {
        Vector4 v = new Vector4(2.0, 3.0, 4.0, 5.0);
        Vector4 result = v.multiply(2.0);
        assertEquals(4.0, result.getX(), EPSILON);
        assertEquals(6.0, result.getY(), EPSILON);
        assertEquals(8.0, result.getZ(), EPSILON);
        assertEquals(10.0, result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Divide vector by scalar")
    void testDivide() {
        Vector4 v = new Vector4(4.0, 6.0, 8.0, 10.0);
        Vector4 result = v.divide(2.0);
        assertEquals(2.0, result.getX(), EPSILON);
        assertEquals(3.0, result.getY(), EPSILON);
        assertEquals(4.0, result.getZ(), EPSILON);
        assertEquals(5.0, result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Divide by zero throws exception")
    void testDivideByZero() {
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertThrows(ArithmeticException.class, () -> v.divide(0.0));
    }

    @Test
    @DisplayName("Calculate vector length")
    void testLength() {
        Vector4 v = new Vector4(2.0, 0.0, 0.0, 0.0);
        assertEquals(2.0, v.length(), EPSILON);
    }

    @Test
    @DisplayName("Calculate length of zero vector")
    void testLengthZero() {
        Vector4 v = new Vector4(0.0, 0.0, 0.0, 0.0);
        assertEquals(0.0, v.length(), EPSILON);
    }

    @Test
    @DisplayName("Normalize vector")
    void testNormalize() {
        Vector4 v = new Vector4(2.0, 0.0, 0.0, 0.0);
        Vector4 normalized = v.normalize();
        assertEquals(1.0, normalized.length(), EPSILON);
        assertEquals(1.0, normalized.getX(), EPSILON);
        assertEquals(0.0, normalized.getY(), EPSILON);
        assertEquals(0.0, normalized.getZ(), EPSILON);
        assertEquals(0.0, normalized.getW(), EPSILON);
    }

    @Test
    @DisplayName("Normalize zero vector throws exception")
    void testNormalizeZero() {
        Vector4 v = new Vector4(0.0, 0.0, 0.0, 0.0);
        assertThrows(ArithmeticException.class, () -> v.normalize());
    }

    @Test
    @DisplayName("Dot product of two vectors")
    void testDotProduct() {
        Vector4 v1 = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 v2 = new Vector4(5.0, 6.0, 7.0, 8.0);
        double result = v1.dotProduct(v2);
        assertEquals(70.0, result, EPSILON); // 1*5 + 2*6 + 3*7 + 4*8 = 70
    }

    @Test
    @DisplayName("Dot product with perpendicular vectors")
    void testDotProductPerpendicular() {
        Vector4 v1 = new Vector4(1.0, 0.0, 0.0, 0.0);
        Vector4 v2 = new Vector4(0.0, 1.0, 0.0, 0.0);
        double result = v1.dotProduct(v2);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    @DisplayName("Dot product with null vector throws exception")
    void testDotProductNull() {
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> v.dotProduct(null));
    }

    @Test
    @DisplayName("Equals method")
    void testEquals() {
        Vector4 v1 = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 v2 = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertEquals(v1, v2);
    }

    @Test
    @DisplayName("HashCode consistency")
    void testHashCode() {
        Vector4 v1 = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 v2 = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    @DisplayName("ToString method")
    void testToString() {
        Vector4 v = new Vector4(1.5, 2.7, 3.9, 4.1);
        String str = v.toString();
        assertTrue(str.contains("Vector4"));
    }

    @Test
    @DisplayName("Immutability: operations do not modify original")
    void testImmutability() {
        Vector4 original = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 added = original.add(new Vector4(1.0, 1.0, 1.0, 1.0));
        Vector4 multiplied = original.multiply(2.0);
        
        assertEquals(1.0, original.getX(), EPSILON);
        assertEquals(2.0, original.getY(), EPSILON);
        assertEquals(3.0, original.getZ(), EPSILON);
        assertEquals(4.0, original.getW(), EPSILON);
        assertNotSame(original, added);
        assertNotSame(original, multiplied);
    }
}

