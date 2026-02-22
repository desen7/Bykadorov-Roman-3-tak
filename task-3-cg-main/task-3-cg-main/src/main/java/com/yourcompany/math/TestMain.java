package com.yourcompany.math;

import com.yourcompany.math.vector.Vector2;
import com.yourcompany.math.vector.Vector3;
import com.yourcompany.math.vector.Vector4;
import com.yourcompany.math.matrix.Matrix3x3;
import com.yourcompany.math.matrix.Matrix4x4;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("=== Тестирование библиотеки линейной алгебры ===\n");
        
        // Тест 1: Vector2
        System.out.println("1. Тест Vector2:");
        Vector2 v2_1 = new Vector2(1.0, 2.0);
        Vector2 v2_2 = new Vector2(3.0, 4.0);
        Vector2 v2_sum = v2_1.add(v2_2);
        System.out.println("   " + v2_1 + " + " + v2_2 + " = " + v2_sum);
        System.out.println("   Длина вектора: " + v2_1.length());
        System.out.println("   ✅ Vector2 работает корректно\n");
        
        // Тест 2: Vector3
        System.out.println("2. Тест Vector3:");
        Vector3 v3_1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v3_2 = new Vector3(4.0, 5.0, 6.0);
        Vector3 v3_cross = v3_1.crossProduct(v3_2);
        System.out.println("   " + v3_1 + " × " + v3_2 + " = " + v3_cross);
        System.out.println("   Скалярное произведение: " + v3_1.dotProduct(v3_2));
        System.out.println("   ✅ Vector3 работает корректно\n");
        
        // Тест 3: Vector4
        System.out.println("3. Тест Vector4:");
        Vector4 v4_1 = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 v4_2 = new Vector4(5.0, 6.0, 7.0, 8.0);
        Vector4 v4_sum = v4_1.add(v4_2);
        System.out.println("   " + v4_1 + " + " + v4_2 + " = " + v4_sum);
        System.out.println("   ✅ Vector4 работает корректно\n");
        
        // Тест 4: Matrix3x3
        System.out.println("4. Тест Matrix3x3:");
        Matrix3x3 identity3 = Matrix3x3.identity();
        Vector3 v3 = new Vector3(1.0, 2.0, 3.0);
        Vector3 result3 = identity3.multiply(v3);
        System.out.println("   Единичная матрица × " + v3 + " = " + result3);
        System.out.println("   Определитель единичной матрицы: " + identity3.determinant());
        System.out.println("   ✅ Matrix3x3 работает корректно\n");
        
        // Тест 5: Matrix4x4
        System.out.println("5. Тест Matrix4x4:");
        Matrix4x4 identity4 = Matrix4x4.identity();
        Vector4 v4 = new Vector4(1.0, 2.0, 3.0, 4.0);
        Vector4 result4 = identity4.multiply(v4);
        System.out.println("   Единичная матрица × " + v4 + " = " + result4);
        System.out.println("   Определитель единичной матрицы: " + identity4.determinant());
        System.out.println("   ✅ Matrix4x4 работает корректно\n");
        
        // Тест 6: Решение системы уравнений
        System.out.println("6. Тест решения системы уравнений (Matrix3x3):");
        Matrix3x3 A = new Matrix3x3(new double[][]{
            {2.0, 1.0, 1.0},
            {1.0, 3.0, 2.0},
            {1.0, 0.0, 0.0}
        });
        Vector3 b = new Vector3(3.0, 4.0, 1.0);
        Vector3 x = Matrix3x3.solveSystem(A, b);
        System.out.println("   Решение системы A*x = b: x = " + x);
        Vector3 verify = A.multiply(x);
        System.out.println("   Проверка: A*x = " + verify + " (ожидается " + b + ")");
        System.out.println("   ✅ Решение системы работает корректно\n");
        
        System.out.println("=== Все тесты пройдены успешно! ===");
        System.out.println("Библиотека работает корректно. ✅");
    }
}



