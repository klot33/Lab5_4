import kotlin.math.sqrt

// Класс для точки
data class Point(val x: Double, val y: Double)

// Класс для треугольника
class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    // Функция для вычисления центра описанной окружности
    fun circumcenter(): Point {
        val x1 = p1.x
        val y1 = p1.y
        val x2 = p2.x
        val y2 = p2.y
        val x3 = p3.x
        val y3 = p3.y

        val d = 2 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))

        val x = ((x1 * x1 + y1 * y1) * (y2 - y3) + (x2 * x2 + y2 * y2) * (y3 - y1) + (x3 * x3 + y3 * y3) * (y1 - y2)) / d
        val y = ((x1 * x1 + y1 * y1) * (x3 - x2) + (x2 * x2 + y2 * y2) * (x1 - x3) + (x3 * x3 + y3 * y3) * (x2 - x1)) / d

        return Point(x, y)
    }

    // Функция для вычисления радиуса описанной окружности
    fun circumradius(center: Point): Double {
        return sqrt((center.x - p1.x) * (center.x - p1.x) + (center.y - p1.y) * (center.y - p1.y))
    }
}

// Функция для безопасного ввода координат с обработкой ошибок
fun readPointCoordinate(prompt: String): Double {
    while (true) {
        try {
            print(prompt)
            return readLine()?.toDouble() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Ошибка: введите корректное число.")
        }
    }
}

fun main() {
    // Ввод координат точек треугольника
    println("Введите координаты вершин треугольника:")

    val x1 = readPointCoordinate("Точка A (x1): ")
    val y1 = readPointCoordinate("Точка A (y1): ")
    val x2 = readPointCoordinate("Точка B (x2): ")
    val y2 = readPointCoordinate("Точка B (y2): ")
    val x3 = readPointCoordinate("Точка C (x3): ")
    val y3 = readPointCoordinate("Точка C (y3): ")

    // Создаем объект треугольника
    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    // Находим центр описанной окружности
    val circumcenter = triangle.circumcenter()

    // Вычисляем радиус описанной окружности
    val circumradius = triangle.circumradius(circumcenter)

    // Вывод результатов
    println("Центр описанной окружности: (${circumcenter.x}, ${circumcenter.y})")
    println("Радиус описанной окружности: $circumradius")
}