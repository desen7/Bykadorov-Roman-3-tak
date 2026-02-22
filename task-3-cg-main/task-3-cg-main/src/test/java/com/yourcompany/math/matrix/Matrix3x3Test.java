package com.yourcompany.math.matrix;

import com.yourcompany.math.vector.Vector3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Matrix3x3 class.
 */
public class Matrix3x3Test {

    private static final double EPSILON = 1e-10;

    @Test
    @DisplayName("Constructor with valid 3x3 array")
    void testConstructor() {
        double[][] data = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        Matrix3x3 m = new Matrix3x3(data);
        assertEquals(1.0, m.get(0, 0), EPSILON);
        assertEquals(5.0, m.get(1, 1), EPSILON);
        assertEquals(9.0, m.get(2, 2), EPSILON);
    }

    @Test
    @DisplayName("Constructor with null throws exception")
    void testConstructorNull() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix3x3(null));
    }

    @Test
    @DisplayName("Constructor with wrong number of rows throws exception")
    void testConstructorWrongRows() {
        double[][] data = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        assertThrows(IllegalArgumentException.class, () -> new Matrix3x3(data));
    }

    @Test
    @DisplayName("Constructor with wrong number of columns throws exception")
    void testConstructorWrongColumns() {
        double[][] data = {
            {1.0, 2.0},
            {3.0, 4.0},
            {5.0, 6.0}
        };
        assertThrows(IllegalArgumentException.class, () -> new Matrix3x3(data));
    }

    @Test
    @DisplayName("Identity matrix creation")
    void testIdentity() {
        Matrix3x3 identity = Matrix3x3.identity();
        assertEquals(1.0, identity.get(0, 0), EPSILON);
        assertEquals(1.0, identity.get(1, 1), EPSILON);
        assertEquals(1.0, identity.get(2, 2), EPSILON);
        assertEquals(0.0, identity.get(0, 1), EPSILON);
        assertEquals(0.0, identity.get(1, 0), EPSILON);
    }

    @Test
    @DisplayName("Zero matrix creation")
    void testZero() {
        Matrix3x3 zero = Matrix3x3.zero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, zero.get(i, j), EPSILON);
            }
        }
    }

    @Test
    @DisplayName("Get element at valid indices")
    void testGet() {
        double[][] data = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        Matrix3x3 m = new Matrix3x3(data);
        assertEquals(6.0, m.get(1, 2), EPSILON);
    }

    @Test
    @DisplayName("Get element at invalid indices throws exception")
    void testGetInvalidIndex() {
        Matrix3x3 m = Matrix3x3.identity();
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> m.get(-1, 0));
    }

    @Test
    @DisplayName("Add two matrices")
    void testAdd() {
        Matrix3x3 m1 = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        Matrix3x3 m2 = new Matrix3x3(new double[][]{
            {9.0, 8.0, 7.0},
            {6.0, 5.0, 4.0},
            {3.0, 2.0, 1.0}
        });
        Matrix3x3 result = m1.add(m2);
        assertEquals(10.0, result.get(0, 0), EPSILON);
        assertEquals(10.0, result.get(1, 1), EPSILON);
        assertEquals(10.0, result.get(2, 2), EPSILON);
    }

    @Test
    @DisplayName("Add with null matrix throws exception")
    void testAddNull() {
        Matrix3x3 m = Matrix3x3.identity();
        assertThrows(IllegalArgumentException.class, () -> m.add(null));
    }

    @Test
    @DisplayName("Subtract two matrices")
    void testSubtract() {
        Matrix3x3 m1 = new Matrix3x3(new double[][]{
            {5.0, 5.0, 5.0},
            {5.0, 5.0, 5.0},
            {5.0, 5.0, 5.0}
        });
        Matrix3x3 m2 = new Matrix3x3(new double[][]{
            {2.0, 2.0, 2.0},
            {2.0, 2.0, 2.0},
            {2.0, 2.0, 2.0}
        });
        Matrix3x3 result = m1.subtract(m2);
        assertEquals(3.0, result.get(0, 0), EPSILON);
    }

    @Test
    @DisplayName("Subtract with null matrix throws exception")
    void testSubtractNull() {
        Matrix3x3 m = Matrix3x3.identity();
        assertThrows(IllegalArgumentException.class, () -> m.subtract(null));
    }

    @Test
    @DisplayName("Multiply two matrices")
    void testMultiply() {
        Matrix3x3 m1 = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        Matrix3x3 m2 = Matrix3x3.identity();
        Matrix3x3 result = m1.multiply(m2);
        assertEquals(m1, result);
    }

    @Test
    @DisplayName("Multiply with null matrix throws exception")
    void testMultiplyNull() {
        Matrix3x3 m = Matrix3x3.identity();
        assertThrows(IllegalArgumentException.class, () -> m.multiply((Matrix3x3) null));
    }

    @Test
    @DisplayName("Multiply matrix by vector")
    void testMultiplyVector() {
        Matrix3x3 m = Matrix3x3.identity();
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        Vector3 result = m.multiply(v);
        assertEquals(1.0, result.getX(), EPSILON);
        assertEquals(2.0, result.getY(), EPSILON);
        assertEquals(3.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Multiply matrix by vector - non-identity")
    void testMultiplyVectorNonIdentity() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {1.0, 0.0, 0.0},
            {0.0, 2.0, 0.0},
            {0.0, 0.0, 3.0}
        });
        Vector3 v = new Vector3(1.0, 2.0, 3.0);
        Vector3 result = m.multiply(v);
        assertEquals(1.0, result.getX(), EPSILON);
        assertEquals(4.0, result.getY(), EPSILON);
        assertEquals(9.0, result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Multiply with null vector throws exception")
    void testMultiplyVectorNull() {
        Matrix3x3 m = Matrix3x3.identity();
        assertThrows(IllegalArgumentException.class, () -> m.multiply((Vector3) null));
    }

    @Test
    @DisplayName("Transpose matrix")
    void testTranspose() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        Matrix3x3 transposed = m.transpose();
        assertEquals(m.get(0, 1), transposed.get(1, 0), EPSILON);
        assertEquals(m.get(1, 2), transposed.get(2, 1), EPSILON);
    }

    @Test
    @DisplayName("Transpose of transpose equals original")
    void testTransposeTwice() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        assertEquals(m, m.transpose().transpose());
    }

    @Test
    @DisplayName("Calculate determinant")
    void testDeterminant() {
        Matrix3x3 identity = Matrix3x3.identity();
        assertEquals(1.0, identity.determinant(), EPSILON);
    }

    @Test
    @DisplayName("Calculate determinant of specific matrix")
    void testDeterminantSpecific() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        // Determinant should be 0 (rows are linearly dependent)
        assertEquals(0.0, m.determinant(), EPSILON);
    }

    @Test
    @DisplayName("Calculate determinant of triangular matrix")
    void testDeterminantTriangular() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {2.0, 0.0, 0.0},
            {0.0, 3.0, 0.0},
            {0.0, 0.0, 4.0}
        });
        assertEquals(24.0, m.determinant(), EPSILON); // 2 * 3 * 4
    }

    @Test
    @DisplayName("Inverse of identity matrix")
    void testInverseIdentity() {
        Matrix3x3 identity = Matrix3x3.identity();
        Matrix3x3 inverse = identity.inverse();
        assertEquals(identity, inverse);
    }

    @Test
    @DisplayName("Inverse of matrix")
    void testInverse() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {2.0, 0.0, 0.0},
            {0.0, 3.0, 0.0},
            {0.0, 0.0, 4.0}
        });
        Matrix3x3 inverse = m.inverse();
        Matrix3x3 product = m.multiply(inverse);
        // Product should be identity matrix
        assertEquals(Matrix3x3.identity(), product);
    }

    @Test
    @DisplayName("Inverse of singular matrix throws exception")
    void testInverseSingular() {
        Matrix3x3 m = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        assertThrows(ArithmeticException.class, () -> m.inverse());
    }

    @Test
    @DisplayName("Solve system of linear equations")
    void testSolveSystem() {
        Matrix3x3 A = new Matrix3x3(new double[][]{
            {2.0, 1.0, 1.0},
            {1.0, 3.0, 2.0},
            {1.0, 0.0, 0.0}
        });
        Vector3 b = new Vector3(3.0, 4.0, 1.0);
        Vector3 x = Matrix3x3.solveSystem(A, b);
        // Verify A * x = b
        Vector3 result = A.multiply(x);
        assertEquals(b.getX(), result.getX(), EPSILON);
        assertEquals(b.getY(), result.getY(), EPSILON);
        assertEquals(b.getZ(), result.getZ(), EPSILON);
    }

    @Test
    @DisplayName("Solve system with identity matrix")
    void testSolveSystemIdentity() {
        Matrix3x3 A = Matrix3x3.identity();
        Vector3 b = new Vector3(1.0, 2.0, 3.0);
        Vector3 x = Matrix3x3.solveSystem(A, b);
        assertEquals(b, x);
    }

    @Test
    @DisplayName("Solve system with singular matrix throws exception")
    void testSolveSystemSingular() {
        Matrix3x3 A = new Matrix3x3(new double[][]{
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        });
        Vector3 b = new Vector3(1.0, 2.0, 3.0);
        assertThrows(ArithmeticException.class, () -> Matrix3x3.solveSystem(A, b));
    }

    @Test
    @DisplayName("Solve system with null matrix throws exception")
    void testSolveSystemNullMatrix() {
        Vector3 b = new Vector3(1.0, 2.0, 3.0);
        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.solveSystem(null, b));
    }

    @Test
    @DisplayName("Solve system with null vector throws exception")
    void testSolveSystemNullVector() {
        Matrix3x3 A = Matrix3x3.identity();
        assertThrows(IllegalArgumentException.class, () -> Matrix3x3.solveSystem(A, null));
    }

    @Test
    @DisplayName("Equals method")
    void testEquals() {
        Matrix3x3 m1 = Matrix3x3.identity();
        Matrix3x3 m2 = Matrix3x3.identity();
        assertEquals(m1, m2);
    }

    @Test
    @DisplayName("HashCode consistency")
    void testHashCode() {
        Matrix3x3 m1 = Matrix3x3.identity();
        Matrix3x3 m2 = Matrix3x3.identity();
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    @DisplayName("ToString method")
    void testToString() {
        Matrix3x3 m = Matrix3x3.identity();
        String str = m.toString();
        assertTrue(str.contains("Matrix3x3"));
    }

    @Test
    @DisplayName("Immutability: operations do not modify original")
    void testImmutability() {
        Matrix3x3 original = Matrix3x3.identity();
        Matrix3x3 added = original.add(original);
        Matrix3x3 multiplied = original.multiply(original);
        Matrix3x3 transposed = original.transpose();
        
        assertEquals(Matrix3x3.identity(), original);
        assertNotSame(original, added);
        assertNotSame(original, multiplied);
        assertNotSame(original, transposed);
    }

    @Test
    @DisplayName("Constructor creates defensive copy")
    void testDefensiveCopy() {
        double[][] data = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        Matrix3x3 m = new Matrix3x3(data);
        data[0][0] = 999.0; // Modify original array
        assertEquals(1.0, m.get(0, 0), EPSILON); // Matrix should be unchanged
    }
}

