package com.yourcompany.math.vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Vector2 class.
 */
public class Vector2Test {

    private static final double EPSILON = 1e-10;

    @Test
    @DisplayName("Constructor with x and y components")
    void testConstructor() {
        Vector2 v = new Vector2(1.0, 2.0);
        assertEquals(1.0, v.getX(), EPSILON);
        assertEquals(2.0, v.getY(), EPSILON);
    }

    @Test
    @DisplayName("Copy constructor")
    void testCopyConstructor() {
        Vector2 original = new Vector2(3.0, 4.0);
        Vector2 copy = new Vector2(original);
        assertEquals(original.getX(), copy.getX(), EPSILON);
        assertEquals(original.getY(), copy.getY(), EPSILON);
        assertNotSame(original, copy);
    }

    @Test
    @DisplayName("Copy constructor with null throws exception")
    void testCopyConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector2((Vector2) null));
    }

    @Test
    @DisplayName("Add two vectors")
    void testAdd() {
        Vector2 v1 = new Vector2(1.0, 2.0);
        Vector2 v2 = new Vector2(3.0, 4.0);
        Vector2 result = v1.add(v2);
        assertEquals(4.0, result.getX(), EPSILON);
        assertEquals(6.0, result.getY(), EPSILON);
        // Original vectors should not be modified
        assertEquals(1.0, v1.getX(), EPSILON);
        assertEquals(2.0, v1.getY(), EPSILON);
    }

    @Test
    @DisplayName("Add with null vector throws exception")
    void testAddNull() {
        Vector2 v = new Vector2(1.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> v.add(null));
    }

    @Test
    @DisplayName("Subtract two vectors")
    void testSubtract() {
        Vector2 v1 = new Vector2(5.0, 6.0);
        Vector2 v2 = new Vector2(2.0, 3.0);
        Vector2 result = v1.subtract(v2);
        assertEquals(3.0, result.getX(), EPSILON);
        assertEquals(3.0, result.getY(), EPSILON);
    }

    @Test
    @DisplayName("Subtract with null vector throws exception")
    void testSubtractNull() {
        Vector2 v = new Vector2(1.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> v.subtract(null));
    }

    @Test
    @DisplayName("Multiply vector by scalar")
    void testMultiply() {
        Vector2 v = new Vector2(2.0, 3.0);
        Vector2 result = v.multiply(2.0);
        assertEquals(4.0, result.getX(), EPSILON);
        assertEquals(6.0, result.getY(), EPSILON);
    }

    @Test
    @DisplayName("Multiply vector by zero")
    void testMultiplyByZero() {
        Vector2 v = new Vector2(2.0, 3.0);
        Vector2 result = v.multiply(0.0);
        assertEquals(0.0, result.getX(), EPSILON);
        assertEquals(0.0, result.getY(), EPSILON);
    }

    @Test
    @DisplayName("Multiply vector by negative scalar")
    void testMultiplyByNegative() {
        Vector2 v = new Vector2(2.0, 3.0);
        Vector2 result = v.multiply(-1.0);
        assertEquals(-2.0, result.getX(), EPSILON);
        assertEquals(-3.0, result.getY(), EPSILON);
    }

    @Test
    @DisplayName("Divide vector by scalar")
    void testDivide() {
        Vector2 v = new Vector2(4.0, 6.0);
        Vector2 result = v.divide(2.0);
        assertEquals(2.0, result.getX(), EPSILON);
        assertEquals(3.0, result.getY(), EPSILON);
    }

    @Test
    @DisplayName("Divide by zero throws exception")
    void testDivideByZero() {
        Vector2 v = new Vector2(1.0, 2.0);
        assertThrows(ArithmeticException.class, () -> v.divide(0.0));
    }

    @Test
    @DisplayName("Calculate vector length")
    void testLength() {
        Vector2 v = new Vector2(3.0, 4.0);
        assertEquals(5.0, v.length(), EPSILON);
    }

    @Test
    @DisplayName("Calculate length of zero vector")
    void testLengthZero() {
        Vector2 v = new Vector2(0.0, 0.0);
        assertEquals(0.0, v.length(), EPSILON);
    }

    @Test
    @DisplayName("Calculate length of negative vector")
    void testLengthNegative() {
        Vector2 v = new Vector2(-3.0, -4.0);
        assertEquals(5.0, v.length(), EPSILON);
    }

    @Test
    @DisplayName("Normalize vector")
    void testNormalize() {
        Vector2 v = new Vector2(3.0, 4.0);
        Vector2 normalized = v.normalize();
        assertEquals(1.0, normalized.length(), EPSILON);
        assertEquals(0.6, normalized.getX(), EPSILON);
        assertEquals(0.8, normalized.getY(), EPSILON);
    }

    @Test
    @DisplayName("Normalize zero vector throws exception")
    void testNormalizeZero() {
        Vector2 v = new Vector2(0.0, 0.0);
        assertThrows(ArithmeticException.class, () -> v.normalize());
    }

    @Test
    @DisplayName("Dot product of two vectors")
    void testDotProduct() {
        Vector2 v1 = new Vector2(1.0, 2.0);
        Vector2 v2 = new Vector2(3.0, 4.0);
        double result = v1.dotProduct(v2);
        assertEquals(11.0, result, EPSILON); // 1*3 + 2*4 = 11
    }

    @Test
    @DisplayName("Dot product with perpendicular vectors")
    void testDotProductPerpendicular() {
        Vector2 v1 = new Vector2(1.0, 0.0);
        Vector2 v2 = new Vector2(0.0, 1.0);
        double result = v1.dotProduct(v2);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    @DisplayName("Dot product with null vector throws exception")
    void testDotProductNull() {
        Vector2 v = new Vector2(1.0, 2.0);
        assertThrows(IllegalArgumentException.class, () -> v.dotProduct(null));
    }

    @Test
    @DisplayName("Equals method with same values")
    void testEquals() {
        Vector2 v1 = new Vector2(1.0, 2.0);
        Vector2 v2 = new Vector2(1.0, 2.0);
        assertEquals(v1, v2);
    }

    @Test
    @DisplayName("Equals method with different values")
    void testNotEquals() {
        Vector2 v1 = new Vector2(1.0, 2.0);
        Vector2 v2 = new Vector2(1.0, 3.0);
        assertNotEquals(v1, v2);
    }

    @Test
    @DisplayName("HashCode consistency")
    void testHashCode() {
        Vector2 v1 = new Vector2(1.0, 2.0);
        Vector2 v2 = new Vector2(1.0, 2.0);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    @DisplayName("ToString method")
    void testToString() {
        Vector2 v = new Vector2(1.5, 2.7);
        String str = v.toString();
        assertTrue(str.contains("Vector2"), 
            "String should contain 'Vector2': " + str);
        assertTrue((str.contains("1.5") || str.contains("1,5") || 
                   str.contains("1.5000") || str.contains("1,5000")),
            "String should contain x component (1.5): " + str);
        assertTrue((str.contains("2.7") || str.contains("2,7") || 
                   str.contains("2.7000") || str.contains("2,7000")),
            "String should contain y component (2.7): " + str);
    }

    @Test
    @DisplayName("Immutability: operations do not modify original")
    void testImmutability() {
        Vector2 original = new Vector2(1.0, 2.0);
        Vector2 added = original.add(new Vector2(1.0, 1.0));
        Vector2 multiplied = original.multiply(2.0);
        
        assertEquals(1.0, original.getX(), EPSILON);
        assertEquals(2.0, original.getY(), EPSILON);
        assertNotSame(original, added);
        assertNotSame(original, multiplied);
    }
}

