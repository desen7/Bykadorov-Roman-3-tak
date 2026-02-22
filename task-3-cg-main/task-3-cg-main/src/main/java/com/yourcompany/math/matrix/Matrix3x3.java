package com.yourcompany.math.matrix;

import com.yourcompany.math.vector.Vector3;

/**
 * Immutable class representing a 3x3 matrix.
 * All operations return new instances without modifying the original matrix.
 */
public final class Matrix3x3 {
    private final double[][] data;

    /**
     * Constructs a new Matrix3x3 from a 2D array.
     * The array should be row-major order (array of rows).
     *
     * @param data a 3x3 array of matrix elements
     * @throws IllegalArgumentException if data is null or has incorrect dimensions
     */
    public Matrix3x3(double[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("Matrix data cannot be null");
        }
        if (data.length != 3) {
            throw new IllegalArgumentException("Matrix must have 3 rows");
        }
        for (int i = 0; i < 3; i++) {
            if (data[i] == null || data[i].length != 3) {
                throw new IllegalArgumentException("Matrix must have 3 columns in each row");
            }
        }
        // Create a defensive copy
        this.data = new double[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, 3);
        }
    }

    /**
     * Creates and returns an identity matrix (1s on diagonal, 0s elsewhere).
     *
     * @return a new identity Matrix3x3
     */
    public static Matrix3x3 identity() {
        double[][] identity = new double[3][3];
        for (int i = 0; i < 3; i++) {
            identity[i][i] = 1.0;
        }
        return new Matrix3x3(identity);
    }

    /**
     * Creates and returns a zero matrix (all elements are 0).
     *
     * @return a new zero Matrix3x3
     */
    public static Matrix3x3 zero() {
        return new Matrix3x3(new double[3][3]);
    }

    /**
     * Gets the element at the specified row and column.
     *
     * @param row the row index (0-2)
     * @param col the column index (0-2)
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if row or col is out of bounds
     */
    public double get(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IndexOutOfBoundsException("Row and column indices must be between 0 and 2");
        }
        return data[row][col];
    }

    /**
     * Adds another Matrix3x3 to this matrix and returns a new Matrix3x3.
     *
     * @param m the matrix to add
     * @return a new Matrix3x3 representing the sum
     * @throws IllegalArgumentException if m is null
     */
    public Matrix3x3 add(Matrix3x3 m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[i][j] + m.data[i][j];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Subtracts another Matrix3x3 from this matrix and returns a new Matrix3x3.
     *
     * @param m the matrix to subtract
     * @return a new Matrix3x3 representing the difference
     * @throws IllegalArgumentException if m is null
     */
    public Matrix3x3 subtract(Matrix3x3 m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[i][j] - m.data[i][j];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Multiplies this matrix by another Matrix3x3 and returns a new Matrix3x3.
     *
     * @param m the matrix to multiply by
     * @return a new Matrix3x3 representing the product
     * @throws IllegalArgumentException if m is null
     */
    public Matrix3x3 multiply(Matrix3x3 m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double sum = 0.0;
                for (int k = 0; k < 3; k++) {
                    sum += this.data[i][k] * m.data[k][j];
                }
                result[i][j] = sum;
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Multiplies this matrix by a Vector3 (column vector) and returns a new Vector3.
     *
     * @param v the vector to multiply by
     * @return a new Vector3 representing the product
     * @throws IllegalArgumentException if v is null
     */
    public Vector3 multiply(Vector3 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        double x = data[0][0] * v.getX() + data[0][1] * v.getY() + data[0][2] * v.getZ();
        double y = data[1][0] * v.getX() + data[1][1] * v.getY() + data[1][2] * v.getZ();
        double z = data[2][0] * v.getX() + data[2][1] * v.getY() + data[2][2] * v.getZ();
        return new Vector3(x, y, z);
    }

    /**
     * Transposes this matrix and returns a new Matrix3x3.
     *
     * @return a new transposed Matrix3x3
     */
    public Matrix3x3 transpose() {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.data[j][i];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Calculates the determinant of this matrix.
     *
     * @return the determinant
     */
    public double determinant() {
        return data[0][0] * (data[1][1] * data[2][2] - data[1][2] * data[2][1])
             - data[0][1] * (data[1][0] * data[2][2] - data[1][2] * data[2][0])
             + data[0][2] * (data[1][0] * data[2][1] - data[1][1] * data[2][0]);
    }

    /**
     * Calculates the inverse of this matrix and returns a new Matrix3x3.
     *
     * @return a new Matrix3x3 representing the inverse
     * @throws ArithmeticException if the matrix is singular (determinant is zero)
     */
    public Matrix3x3 inverse() {
        double det = determinant();
        if (Math.abs(det) < 1e-10) {
            throw new ArithmeticException("Matrix is singular (determinant is zero), cannot compute inverse");
        }

        double invDet = 1.0 / det;
        double[][] result = new double[3][3];

        result[0][0] = (data[1][1] * data[2][2] - data[1][2] * data[2][1]) * invDet;
        result[0][1] = (data[0][2] * data[2][1] - data[0][1] * data[2][2]) * invDet;
        result[0][2] = (data[0][1] * data[1][2] - data[0][2] * data[1][1]) * invDet;

        result[1][0] = (data[1][2] * data[2][0] - data[1][0] * data[2][2]) * invDet;
        result[1][1] = (data[0][0] * data[2][2] - data[0][2] * data[2][0]) * invDet;
        result[1][2] = (data[0][2] * data[1][0] - data[0][0] * data[1][2]) * invDet;

        result[2][0] = (data[1][0] * data[2][1] - data[1][1] * data[2][0]) * invDet;
        result[2][1] = (data[0][1] * data[2][0] - data[0][0] * data[2][1]) * invDet;
        result[2][2] = (data[0][0] * data[1][1] - data[0][1] * data[1][0]) * invDet;

        return new Matrix3x3(result);
    }

    /**
     * Solves the system of linear equations A * x = b using Gaussian elimination.
     *
     * @param A the coefficient matrix
     * @param b the constant vector
     * @return a Vector3 representing the solution x
     * @throws IllegalArgumentException if A or b is null
     * @throws ArithmeticException if the system has no unique solution
     */
    public static Vector3 solveSystem(Matrix3x3 A, Vector3 b) {
        if (A == null) {
            throw new IllegalArgumentException("Matrix A cannot be null");
        }
        if (b == null) {
            throw new IllegalArgumentException("Vector b cannot be null");
        }

        // Create augmented matrix [A|b]
        double[][] augmented = new double[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                augmented[i][j] = A.data[i][j];
            }
        }
        augmented[0][3] = b.getX();
        augmented[1][3] = b.getY();
        augmented[2][3] = b.getZ();

        // Gaussian elimination with partial pivoting
        int n = 3;
        for (int col = 0; col < n; col++) {
            // Find pivot
            int maxRow = col;
            for (int row = col + 1; row < n; row++) {
                if (Math.abs(augmented[row][col]) > Math.abs(augmented[maxRow][col])) {
                    maxRow = row;
                }
            }

            // Swap rows
            double[] temp = augmented[col];
            augmented[col] = augmented[maxRow];
            augmented[maxRow] = temp;

            // Check for singular matrix
            if (Math.abs(augmented[col][col]) < 1e-10) {
                throw new ArithmeticException("System has no unique solution (matrix is singular)");
            }

            // Eliminate
            for (int row = col + 1; row < n; row++) {
                double factor = augmented[row][col] / augmented[col][col];
                for (int k = col; k <= n; k++) {
                    augmented[row][k] -= factor * augmented[col][k];
                }
            }
        }

        // Back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = augmented[i][n];
            for (int j = i + 1; j < n; j++) {
                x[i] -= augmented[i][j] * x[j];
            }
            x[i] /= augmented[i][i];
        }

        return new Vector3(x[0], x[1], x[2]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix3x3 matrix3x3 = (Matrix3x3) obj;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Math.abs(data[i][j] - matrix3x3.data[i][j]) > 1e-10) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result = 31 * result + Double.hashCode(data[i][j]);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix3x3(\n");
        for (int i = 0; i < 3; i++) {
            sb.append("  [");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%.4f", data[i][j]));
                if (j < 2) sb.append(", ");
            }
            sb.append("]");
            if (i < 2) sb.append("\n");
        }
        sb.append("\n)");
        return sb.toString();
    }
}

