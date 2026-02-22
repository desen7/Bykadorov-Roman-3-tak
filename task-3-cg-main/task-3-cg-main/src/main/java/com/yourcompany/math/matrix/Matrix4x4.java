package com.yourcompany.math.matrix;

import com.yourcompany.math.vector.Vector4;

/**
 * Immutable class representing a 4x4 matrix.
 * All operations return new instances without modifying the original matrix.
 */
public final class Matrix4x4 {
    private final double[][] data;

    /**
     * Constructs a new Matrix4x4 from a 2D array.
     * The array should be row-major order (array of rows).
     *
     * @param data a 4x4 array of matrix elements
     * @throws IllegalArgumentException if data is null or has incorrect dimensions
     */
    public Matrix4x4(double[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("Matrix data cannot be null");
        }
        if (data.length != 4) {
            throw new IllegalArgumentException("Matrix must have 4 rows");
        }
        for (int i = 0; i < 4; i++) {
            if (data[i] == null || data[i].length != 4) {
                throw new IllegalArgumentException("Matrix must have 4 columns in each row");
            }
        }
        // Create a defensive copy
        this.data = new double[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, 4);
        }
    }

    /**
     * Creates and returns an identity matrix (1s on diagonal, 0s elsewhere).
     *
     * @return a new identity Matrix4x4
     */
    public static Matrix4x4 identity() {
        double[][] identity = new double[4][4];
        for (int i = 0; i < 4; i++) {
            identity[i][i] = 1.0;
        }
        return new Matrix4x4(identity);
    }

    /**
     * Creates and returns a zero matrix (all elements are 0).
     *
     * @return a new zero Matrix4x4
     */
    public static Matrix4x4 zero() {
        return new Matrix4x4(new double[4][4]);
    }

    /**
     * Gets the element at the specified row and column.
     *
     * @param row the row index (0-3)
     * @param col the column index (0-3)
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if row or col is out of bounds
     */
    public double get(int row, int col) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4) {
            throw new IndexOutOfBoundsException("Row and column indices must be between 0 and 3");
        }
        return data[row][col];
    }

    /**
     * Adds another Matrix4x4 to this matrix and returns a new Matrix4x4.
     *
     * @param m the matrix to add
     * @return a new Matrix4x4 representing the sum
     * @throws IllegalArgumentException if m is null
     */
    public Matrix4x4 add(Matrix4x4 m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.data[i][j] + m.data[i][j];
            }
        }
        return new Matrix4x4(result);
    }

    /**
     * Subtracts another Matrix4x4 from this matrix and returns a new Matrix4x4.
     *
     * @param m the matrix to subtract
     * @return a new Matrix4x4 representing the difference
     * @throws IllegalArgumentException if m is null
     */
    public Matrix4x4 subtract(Matrix4x4 m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.data[i][j] - m.data[i][j];
            }
        }
        return new Matrix4x4(result);
    }

    /**
     * Multiplies this matrix by another Matrix4x4 and returns a new Matrix4x4.
     *
     * @param m the matrix to multiply by
     * @return a new Matrix4x4 representing the product
     * @throws IllegalArgumentException if m is null
     */
    public Matrix4x4 multiply(Matrix4x4 m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.data[i][k] * m.data[k][j];
                }
                result[i][j] = sum;
            }
        }
        return new Matrix4x4(result);
    }

    /**
     * Multiplies this matrix by a Vector4 (column vector) and returns a new Vector4.
     *
     * @param v the vector to multiply by
     * @return a new Vector4 representing the product
     * @throws IllegalArgumentException if v is null
     */
    public Vector4 multiply(Vector4 v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        double x = data[0][0] * v.getX() + data[0][1] * v.getY() + data[0][2] * v.getZ() + data[0][3] * v.getW();
        double y = data[1][0] * v.getX() + data[1][1] * v.getY() + data[1][2] * v.getZ() + data[1][3] * v.getW();
        double z = data[2][0] * v.getX() + data[2][1] * v.getY() + data[2][2] * v.getZ() + data[2][3] * v.getW();
        double w = data[3][0] * v.getX() + data[3][1] * v.getY() + data[3][2] * v.getZ() + data[3][3] * v.getW();
        return new Vector4(x, y, z, w);
    }

    /**
     * Transposes this matrix and returns a new Matrix4x4.
     *
     * @return a new transposed Matrix4x4
     */
    public Matrix4x4 transpose() {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.data[j][i];
            }
        }
        return new Matrix4x4(result);
    }

    /**
     * Calculates the determinant of this matrix using Laplace expansion.
     *
     * @return the determinant
     */
    public double determinant() {
        // Use cofactor expansion along the first row
        double det = 0.0;
        for (int j = 0; j < 4; j++) {
            det += data[0][j] * cofactor(0, j);
        }
        return det;
    }

    /**
     * Calculates the cofactor of the element at the specified row and column.
     *
     * @param row the row index
     * @param col the column index
     * @return the cofactor
     */
    private double cofactor(int row, int col) {
        return Math.pow(-1, row + col) * minor(row, col);
    }

    /**
     * Calculates the minor (determinant of 3x3 submatrix) at the specified row and column.
     *
     * @param row the row index
     * @param col the column index
     * @return the minor
     */
    private double minor(int row, int col) {
        double[][] submatrix = new double[3][3];
        int subRow = 0;
        for (int i = 0; i < 4; i++) {
            if (i == row) continue;
            int subCol = 0;
            for (int j = 0; j < 4; j++) {
                if (j == col) continue;
                submatrix[subRow][subCol] = data[i][j];
                subCol++;
            }
            subRow++;
        }
        // Calculate 3x3 determinant
        return submatrix[0][0] * (submatrix[1][1] * submatrix[2][2] - submatrix[1][2] * submatrix[2][1])
             - submatrix[0][1] * (submatrix[1][0] * submatrix[2][2] - submatrix[1][2] * submatrix[2][0])
             + submatrix[0][2] * (submatrix[1][0] * submatrix[2][1] - submatrix[1][1] * submatrix[2][0]);
    }

    /**
     * Calculates the inverse of this matrix using the adjugate method.
     *
     * @return a new Matrix4x4 representing the inverse
     * @throws ArithmeticException if the matrix is singular (determinant is zero)
     */
    public Matrix4x4 inverse() {
        double det = determinant();
        if (Math.abs(det) < 1e-10) {
            throw new ArithmeticException("Matrix is singular (determinant is zero), cannot compute inverse");
        }

        // Calculate adjugate matrix (transpose of cofactor matrix)
        double[][] adjugate = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                adjugate[j][i] = cofactor(i, j); // Transpose during assignment
            }
        }

        // Multiply by 1/determinant
        double invDet = 1.0 / det;
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = adjugate[i][j] * invDet;
            }
        }

        return new Matrix4x4(result);
    }

    /**
     * Solves the system of linear equations A * x = b using Gaussian elimination.
     *
     * @param A the coefficient matrix
     * @param b the constant vector
     * @return a Vector4 representing the solution x
     * @throws IllegalArgumentException if A or b is null
     * @throws ArithmeticException if the system has no unique solution
     */
    public static Vector4 solveSystem(Matrix4x4 A, Vector4 b) {
        if (A == null) {
            throw new IllegalArgumentException("Matrix A cannot be null");
        }
        if (b == null) {
            throw new IllegalArgumentException("Vector b cannot be null");
        }

        // Create augmented matrix [A|b]
        double[][] augmented = new double[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                augmented[i][j] = A.data[i][j];
            }
        }
        augmented[0][4] = b.getX();
        augmented[1][4] = b.getY();
        augmented[2][4] = b.getZ();
        augmented[3][4] = b.getW();

        // Gaussian elimination with partial pivoting
        int n = 4;
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

        return new Vector4(x[0], x[1], x[2], x[3]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix4x4 matrix4x4 = (Matrix4x4) obj;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Math.abs(data[i][j] - matrix4x4.data[i][j]) > 1e-10) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result = 31 * result + Double.hashCode(data[i][j]);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix4x4(\n");
        for (int i = 0; i < 4; i++) {
            sb.append("  [");
            for (int j = 0; j < 4; j++) {
                sb.append(String.format("%.4f", data[i][j]));
                if (j < 3) sb.append(", ");
            }
            sb.append("]");
            if (i < 3) sb.append("\n");
        }
        sb.append("\n)");
        return sb.toString();
    }
}

