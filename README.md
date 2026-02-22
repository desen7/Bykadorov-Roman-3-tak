Библиотека линейной алгебры
Объектно-ориентированная библиотека для операций линейной алгебры на языке Java. Библиотека предоставляет классы для работы с векторами (2D, 3D, 4D) и матрицами (3x3, 4x4).

Структура проекта
src/main/java/com/yourcompany/math/
    vector/
        Vector2.java
        Vector3.java
        Vector4.java
    matrix/
        Matrix3x3.java
        Matrix4x4.java
src/test/java/com/yourcompany/math/
    vector/
        Vector2Test.java
        Vector3Test.java
        Vector4Test.java
    matrix/
        Matrix3x3Test.java
        Matrix4x4Test.java
Использование
Векторы
Vector2 (2D-вектор)
com import.yourcompany.math.vector.Vector2;
// Создание вектора
Vector2 v1 = new Vector2(1.0, 2.0);
Vector2 v2 = new Vector2(3.0, 4.0);

// Операции
Vector2 sum = v1.add(v2);
Вектор2 разница = v1.вычтите(v2); 
Вектор2 масштабируется = v1.умножить(2.0); 
Вектор2 делится = v1.делим(2.0); 

// Векторные операции
double length = v1.length();
Вектор2 нормализованный = v1.normalize(); 
двойная точка = v1.Точечный продукт(v2);
Vector3 (3D-вектор)
com import.yourcompany.math.vector.Vector3;
Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
Vector3 v2 = new Vector3(4.0, 5.0, 6.0);
// Все операции с Vector2 плюс:
Vector3 cross = v1.crossProduct(v2); // Векторное произведение
Vector4 (4D-вектор)
com import.yourcompany.math.vector.Vector4;
Vector4 v1 = new Vector4(1.0, 2.0, 3.0, 4.0);
Vector4 v2 = new Vector4(5.0, 6.0, 7.0, 8.0);
// Аналогично для Vector2 и Vector3
Матрицы
Матрица 3x3
com импорт.ваша компания.математика.матрица.Матрица 3x3; 
импортируйте com.yourcompany.математический.вектор.Вектор3; 

// Создание матрицы
double[][] data = {
 {1.0, 2.0, 3.0},
 {4.0, 5.0, 6.0},
 {7.0, 8.0, 9.0}
};
Matrix3x3 m = new Matrix3x3(data);
// Фабричные методы
Matrix3x3 identity = Matrix3x3.identity();
Matrix3x3 zero = Matrix3x3.zero();

// Операции
Matrix3x3 sum = m1.add(m2);
Matrix3x3 diff = m1.subtract(m2);
Матрица 3x3 произведение = m1.умножить(m2); 
Матрица 3x3 транспонированная = m.транспонировать(); 

// Умножение на вектор
Vector3 v = new Vector3(1.0, 2.0, 3.0);
Вектор3 результат = m.умножить(v); 

// Дополнительные операции
double det = m.determinant();
Матрица 3x3 обратная = m.обратная(); 

// Решение системы уравнений A * x = b
Vector3 x = Matrix3x3.solveSystem(A, b);
Матрица X4x4
com импорт.yourcompany.математика.матрица.Matrix4x4; 
импортируйте com.yourcompany.математический.вектор.Вектор4; 

// Аналогично Matrix3x3, но для 4x4 матриц и Vector4
Matrix4x4 m = new Matrix4x4(data);
Vector4 v = новый Vector4(1.0, 2.0, 3.0, 4.0);
Vector4 result = m.умножить(v);# Bykadorov-Roman-3-tak
