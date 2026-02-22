# Библиотека линейной алгебры

Объектно-ориентированная библиотека для выполнения операций линейной алгебры на языке Java. Проект предоставляет удобные классы для работы с векторами (2D, 3D, 4D) и матрицами (3x3, 4x4), реализуя основные математические операции, необходимые для компьютерной графики, машинного обучения и инженерных расчетов.



text

## Использование

### Векторы

#### Vector2 (2D-вектор)

```java
import com.yourcompany.math.vector.Vector2;

// Создание вектора
Vector2 v1 = new Vector2(1.0, 2.0);
Vector2 v2 = new Vector2(3.0, 4.0);

// Арифметические операции
Vector2 sum = v1.add(v2);
Vector2 difference = v1.subtract(v2);
Vector2 scaled = v1.multiply(2.0);
Vector2 divided = v1.divide(2.0);

// Векторные операции
double length = v1.length();
Vector2 normalized = v1.normalize();
double dotProduct = v1.dotProduct(v2);
Vector3 (3D-вектор)
java
import com.yourcompany.math.vector.Vector3;

Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
Vector3 v2 = new Vector3(4.0, 5.0, 6.0);

// Все операции из Vector2, плюс:
Vector3 cross = v1.crossProduct(v2); // Векторное произведение
Vector4 (4D-вектор)
java
import com.yourcompany.math.vector.Vector4;

Vector4 v1 = new Vector4(1.0, 2.0, 3.0, 4.0);
Vector4 v2 = new Vector4(5.0, 6.0, 7.0, 8.0);

// Поддерживаются все базовые операции (сложение, вычитание, умножение на скаляр, нормализация и т.д.)
Матрицы
Matrix3x3 (Матрица 3x3)
java
import com.yourcompany.math.matrix.Matrix3x3;
import com.yourcompany.math.vector.Vector3;

// Создание матрицы из двумерного массива
double[][] data = {
    {1.0, 2.0, 3.0},
    {4.0, 5.0, 6.0},
    {7.0, 8.0, 9.0}
};
Matrix3x3 m = new Matrix3x3(data);
Matrix3x3 m2 = new Matrix3x3(otherData);

// Фабричные методы
Matrix3x3 identity = Matrix3x3.identity();
Matrix3x3 zero = Matrix3x3.zeros();

// Матричные операции
Matrix3x3 sum = m.add(m2);
Matrix3x3 difference = m.subtract(m2);
Matrix3x3 product = m.multiply(m2);
Matrix3x3 transposed = m.transpose();

// Умножение на вектор
Vector3 v = new Vector3(1.0, 2.0, 3.0);
Vector3 result = m.multiply(v);

// Дополнительные операции
double determinant = m.determinant();
Matrix3x3 inverse = m.inverse();
Matrix4x4 (Матрица 4x4)
java
import com.yourcompany.math.matrix.Matrix4x4;
import com.yourcompany.math.vector.Vector4;

// Аналогично Matrix3x3, но для работы с 4D-пространством
double[][] data = {
    {1.0, 2.0, 3.0, 4.0},
    {5.0, 6.0, 7.0, 8.0},
    {9.0, 10.0, 11.0, 12.0},
    {13.0, 14.0, 15.0, 16.0}
};

Matrix4x4 m = new Matrix4x4(data);
Matrix4x4 identity = Matrix4x4.identity();

Vector4 v = new Vector4(1.0, 2.0, 3.0, 4.0);
Vector4 result = m.multiply(v);
