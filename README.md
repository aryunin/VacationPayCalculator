# VacationPayCalculator
Test task for NeoStudy

### Endpoints
- GET /calculate
- Request params:
salary (required) - средная заработная плата\
days (required) - количество дней отпуска\
startDate (optional) - дата начала отпуска (yyyy-mm-dd)
- Возвращает дробное число (x.xx) - количество отпускных. Если указана стартовая дата, то колчичество отпускных считается за период startDate + days - 1,
следовательно количество дней не может быть меньше 1. Также значение заработной платы не может быть отрицательным. Значения праздников учтены за 2023 и 2024 года.


### Examples
![image](https://github.com/aryunin/VacationPayCalculator/assets/37240301/54543269-b361-4414-a55e-1c204a919029)/
![image](https://github.com/aryunin/VacationPayCalculator/assets/37240301/81df8602-0ea2-42a2-aee4-16f1440f8ba3)

