package com.yourcompany.math.matrix;

import com.yourcompany.math.vector.Vector4;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Matrix4x4 class.
 */
public class Matrix4x4Test {

    private static final double EPSILON = 1e-10;

    @Test
    @DisplayName("Constructor with valid 4x4 array")
    void testConstructor() {
        double[][] data = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };
        Matrix4x4 m = new Matrix4x4(data);
        assertEquals(1.0, m.get(0, 0), EPSILON);
        assertEquals(6.0, m.get(1, 1), EPSILON);
        assertEquals(11.0, m.get(2, 2), EPSILON);
        assertEquals(16.0, m.get(3, 3), EPSILON);
    }

    @Test
    @DisplayName("Constructor with null throws exception")
    void testConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix4x4(null));
    }

    @Test
    @DisplayName("Constructor with wrong number of rows throws exception")
    void testConstructorWrongRows() {
        double[][] data = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0}
        };
        assertThrows(IllegalArgumentException.class, () -> new Matrix4x4(data));
    }

    @Test
    @DisplayName("Constructor with wrong number of columns throws exception")
    void testConstructorWrongColumns() {
        double[][] data = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0},
            {10.0, 11.0, 12.0}
        };
        assertThrows(IllegalArgumentException.class, () -> new Matrix4x4(data));
    }

    @Test
    @DisplayName("Identity matrix creation")
    void testIdentity() {
        Matrix4x4 identity = Matrix4x4.identity();
        assertEquals(1.0, identity.get(0, 0), EPSILON);
        assertEquals(1.0, identity.get(1, 1), EPSILON);
        assertEquals(1.0, identity.get(2, 2), EPSILON);
        assertEquals(1.0, identity.get(3, 3), EPSILON);
        assertEquals(0.0, identity.get(0, 1), EPSILON);
        assertEquals(0.0, identity.get(1, 0), EPSILON);
    }

    @Test
    @DisplayName("Zero matrix creation")
    void testZero() {
        Matrix4x4 zero = Matrix4x4.zero();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0.0, zero.get(i, j), EPSILON);
            }
        }
    }

    @Test
    @DisplayName("Get element at valid indices")
    void testGet() {
        double[][] data = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };
        Matrix4x4 m = new Matrix4x4(data);
        assertEquals(7.0, m.get(1, 2), EPSILON);
    }

    @Test
    @DisplayName("Get element at invalid indices throws exception")
    void testGetInvalidIndex() {
        Matrix4x4 m = Matrix4x4.identity();
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(4, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(-1, 0));
    }

    @Test
    @DisplayName("Add two matrices")
    void testAdd() {
        Matrix4x4 m1 = new Matrix4x4(new double[][]{
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        });
        Matrix4x4 m2 = Matrix4x4.identity();
        Matrix4x4 result = m1.add(m2);
        assertEquals(2.0, result.get(0, 0), EPSILON);
        assertEquals(7.0, result.get(1, 1), EPSILON);
    }

    @Test
    @DisplayName("Add with null matrix throws exception")
    void testAddNull() {
        Matrix4x4 m = Matrix4x4.identity();
        assertThrows(IllegalArgumentException.class, () -> m.add(null));
    }

    @Test
    @DisplayName("Subtract two matrices")
    void testSubtract() {
        Matrix4x4 m1 = Matrix4x4.identity();
        Matrix4x4 m2 = Matrix4x4.identity();
        Matrix4x4 result = m1.subtract(m2);
        assertEquals(Matrix4x4.zero(), result);
    }

    @Test
    @DisplayName("Subtract with null matrix throws exception")
    void testSubtractNull() {
        Matrix4x4 m = Matrix4x4.identity();
        assertThrows(IllegalArgumentException.class, () -> m.subtract(null));
    }

    @Test
    @DisplayName("Multiply two matrices")
    void testMultiply() {
        Matrix4x4 m1 = Matrix4x4.identity();
        Matrix4x4 m2 = Matrix4x4.identity();
        Matrix4x4 result = m1.multiply(m2);
        assertEquals(Matrix4x4.identity(), result);
    }

    @Test
    @DisplayName("Multiply with null matrix throws exception")
    void testMultiplyNull() {
        Matrix4x4 m = Matrix4x4.identity();
        assertThrows(IllegalArgumentException.class, () -> m.multiply((Matrix4x4) null));
    }

    @Test
    @DisplayName("Multiply matrix by vector")
    void testMultiplyVector() {
        Matrix4x4 m = Matrix4x4.identity();
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 result = m.multiply(v);
        assertEquals(1.0, result.getX(), EPSILON);
        assertEquals(2.0, result.getY(), EPSILON);
        assertEquals(3.0, result.getZ(), EPSILON);
        assertEquals(4.0, result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Multiply matrix by vector - non-identity")
    void testMultiplyVectorNonIdentity() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 2.0, 0.0, 0.0},
            {0.0, 0.0, 3.0, 0.0},
            {0.0, 0.0, 0.0, 4.0}
        });
        Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 result = m.multiply(v);
        assertEquals(1.0, result.getX(), EPSILON);
        assertEquals(4.0, result.getY(), EPSILON);
        assertEquals(9.0, result.getZ(), EPSILON);
        assertEquals(16.0, result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Multiply with null vector throws exception")
    void testMultiplyVectorNull() {
        Matrix4x4 m = Matrix4x4.identity();
        assertThrows(IllegalArgumentException.class, () -> m.multiply((Vector4) null));
    }

    @Test
    @DisplayName("Transpose matrix")
    void testTranspose() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        });
        Matrix4x4 transposed = m.transpose();
        assertEquals(m.get(0, 1), transposed.get(1, 0), EPSILON);
        assertEquals(m.get(1, 2), transposed.get(2, 1), EPSILON);
    }

    @Test
    @DisplayName("Transpose of transpose equals original")
    void testTransposeTwice() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        });
        assertEquals(m, m.transpose().transpose());
    }

    @Test
    @DisplayName("Calculate determinant of identity matrix")
    void testDeterminantIdentity() {
        Matrix4x4 identity = Matrix4x4.identity();
        assertEquals(1.0, identity.determinant(), EPSILON);
    }

    @Test
    @DisplayName("Calculate determinant of diagonal matrix")
    void testDeterminantDiagonal() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {2.0, 0.0, 0.0, 0.0},
            {0.0, 3.0, 0.0, 0.0},
            {0.0, 0.0, 4.0, 0.0},
            {0.0, 0.0, 0.0, 5.0}
        });
        assertEquals(120.0, m.determinant(), EPSILON); // 2 * 3 * 4 * 5
    }

    @Test
    @DisplayName("Calculate determinant of singular matrix")
    void testDeterminantSingular() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        });
        // This matrix has linearly dependent rows, so determinant should be 0
        assertEquals(0.0, m.determinant(), EPSILON);
    }

    @Test
    @DisplayName("Inverse of identity matrix")
    void testInverseIdentity() {
        Matrix4x4 identity = Matrix4x4.identity();
        Matrix4x4 inverse = identity.inverse();
        assertEquals(identity, inverse);
    }

    @Test
    @DisplayName("Inverse of diagonal matrix")
    void testInverseDiagonal() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {2.0, 0.0, 0.0, 0.0},
            {0.0, 3.0, 0.0, 0.0},
            {0.0, 0.0, 4.0, 0.0},
            {0.0, 0.0, 0.0, 5.0}
        });
        Matrix4x4 inverse = m.inverse();
        Matrix4x4 product = m.multiply(inverse);
        // Product should be identity matrix
        assertEquals(Matrix4x4.identity(), product);
    }

    @Test
    @DisplayName("Inverse of singular matrix throws exception")
    void testInverseSingular() {
        Matrix4x4 m = new Matrix4x4(new double[][]{
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        });
        assertThrows(ArithmeticException.class, () -> m.inverse());
    }

    @Test
    @DisplayName("Solve system of linear equations")
    void testSolveSystem() {
        Matrix4x4 A = new Matrix4x4(new double[][]{
            {2.0, 1.0, 0.0, 0.0},
            {1.0, 2.0, 1.0, 0.0},
            {0.0, 1.0, 2.0, 1.0},
            {0.0, 0.0, 1.0, 2.0}
        });
        Vector4 b = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 x = Matrix4x4.solveSystem(A, b);
        // Verify A * x = b
        Vector4 result = A.multiply(x);
        assertEquals(b.getX(), result.getX(), EPSILON);
        assertEquals(b.getY(), result.getY(), EPSILON);
        assertEquals(b.getZ(), result.getZ(), EPSILON);
        assertEquals(b.getW(), result.getW(), EPSILON);
    }

    @Test
    @DisplayName("Solve system with identity matrix")
    void testSolveSystemIdentity() {
        Matrix4x4 A = Matrix4x4.identity();
        Vector4 b = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 x = Matrix4x4.solveSystem(A, b);
        assertEquals(b, x);
    }

    @Test
    @DisplayName("Solve system with singular matrix throws exception")
    void testSolveSystemSingular() {
        Matrix4x4 A = new Matrix4x4(new double[][]{
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        });
        Vector4 b = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertThrows(ArithmeticException.class, () -> Matrix4x4.solveSystem(A, b));
    }

    @Test
    @DisplayName("Solve system with null matrix throws exception")
    void testSolveSystemNullMatrix() {
        Vector4 b = new Vector4(1.0, 2.0, 3.0, 4.0);
        assertThrows(IllegalArgumentException.class, () -> Matrix4x4.solveSystem(null, b));
    }

    @Test
    @DisplayName("Solve system with null vector throws exception")
    void testSolveSystemNullVector() {
        Matrix4x4 A = Matrix4x4.identity();
        assertThrows(IllegalArgumentException.class, () -> Matrix4x4.solveSystem(A, null));
    }

    @Test
    @DisplayName("Equals method")
    void testEquals() {
        Matrix4x4 m1 = Matrix4x4.identity();
        Matrix4x4 m2 = Matrix4x4.identity();
        assertEquals(m1, m2);
    }

    @Test
    @DisplayName("HashCode consistency")
    void testHashCode() {
        Matrix4x4 m1 = Matrix4x4.identity();
        Matrix4x4 m2 = Matrix4x4.identity();
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    @DisplayName("ToString method")
    void testToString() {
        Matrix4x4 m = Matrix4x4.identity();
        String str = m.toString();
        assertTrue(str.contains("Matrix4x4"));
    }

    @Test
    @DisplayName("Immutability: operations do not modify original")
    void testImmutability() {
        Matrix4x4 original = Matrix4x4.identity();
        Matrix4x4 added = original.add(original);
        Matrix4x4 multiplied = original.multiply(original);
        Matrix4x4 transposed = original.transpose();
        
        assertEquals(Matrix4x4.identity(), original);
        assertNotSame(original, added);
        assertNotSame(original, multiplied);
        assertNotSame(original, transposed);
    }

    @Test
    @DisplayName("Constructor creates defensive copy")
    void testDefensiveCopy() {
        double[][] data = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };
        Matrix4x4 m = new Matrix4x4(data);
        data[0][0] = 999.0; // Modify original array
        assertEquals(1.0, m.get(0, 0), EPSILON); // Matrix should be unchanged
    }
}

