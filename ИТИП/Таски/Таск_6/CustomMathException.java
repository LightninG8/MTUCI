public class CustomMathException extends Exception {

    // Виды ошибок в мат выражениях:
    // 1) Неправильная скобочная последовательность                      +
    // 2) Пустота внутри скобок                                          +
    // 3) Две подряд идущие мат операции                                 +
    // 4) Два подряд идущих числа без мат операции между ними            +
    // 5) После закрытой скобки идет число(без операции между ними)      +
    // 6) Перед открывающей скобкой идет число без операции между ними   +
    // 7) Вообще не корректные символы                                   +
    // 8) Пустая строка                                                  +
    // 9) Деление на ноль                                                +


    private String message = "Not a valid math operation. ";

    public CustomMathException(){}
    public CustomMathException (String subtext) {
        this.message += subtext;
    }

    public String getMessage() {return this.message;}
}
