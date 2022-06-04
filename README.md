# **Задача № 2 Список студентов**

## **Цель**:
1. Создать систему учета студентов. Для каждого студента нужно хранить следующие данные – ФИО, номер группы, номер студенческого билета. Уникальным идентификатором является номер студенческого билета.

### *Пример*:
``` Пример 1
Выберите действие:
1. Добавить задачу
2. Вывести список задач
3. Удалить задачу
0. Выход
1 <enter>
Введите задачу для планирования:
Почитать про ArrayList

1 <enter>
Введите задачу для планирования:
Повторить примитивные типы данных

2 <enter>
Список задач:
1. Почитать про ArrayList
2. Повторить примитивные типы данных

0 <enter>
```

### **Моя реализация**:
1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - класс, отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных ```void``` методов: 
  * *printMenu()* - выводит меню команд программы на экран;
  * *addStudentInList()* - добавляет студента в список;
  * *printStudentsList()* - выводит список студентов на экран;
  * *deleteStudentFromList()* - удаляет студента из списка;
  * *demonstration()* - демонстрация работы программы. 

#### Класс **Program**:
``` java
public class Program {
    private final Scanner scanner = new Scanner(System.in);
    private final Set<Student> students = new HashSet<>();

    public void start() {
        String input;
        String[] allInput;
        while (true) {
            try {
                printMenu();
                input = scanner.nextLine();
                if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                    scanner.close();
                    break;
                } else {
                    var operationNumber = Integer.parseInt(input);
                    switch (operationNumber) {
                        case 1:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы добавить студента в список " +
                                    "введите через пробел: фамилию, имя, группу и идентификатор.");
                            allInput = scanner.nextLine().split(" ");
                            addStudentInList(new Student(allInput[0], allInput[1], allInput[2], allInput[3]));
                            break;
                        case 2:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы удалить студента из списка введите номер:" +
                                    Utils.ANSI_RESET);
                            printStudentsList(students);
                            input = scanner.nextLine();
                            deleteStudentFromList(Integer.parseInt(input));
                            break;
                        case 3:
                            printStudentsList(students);
                            break;
                        case 4:
                            demonstration();
                            break;
                        default:
                            System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" + Utils.ANSI_RESET);
                    }
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(Utils.ANSI_RED + "Ошибка ввода!" + Utils.ANSI_RESET);
            }
        }
    }

    private void addStudentInList(Student student) {
        var studentTemp = student;
        if (!students.contains(studentTemp)) {
            students.add(student);
            System.out.printf("%sСтудент - %s %s успешно добавлен в список!%s%n",
                    Utils.ANSI_GREEN, student.getSurname(), student.getName(), Utils.ANSI_RESET);
        } else {
            System.out.printf("%sОшибка! Нельзя добавить %s %s, так как в списке уже есть " +
                            "студент с идентификатором - %s!%s%n",
                    Utils.ANSI_RED, student.getSurname(), student.getName(), student.getStudentId(), Utils.ANSI_RESET);
        }
    }

    private static void printStudentsList(Set<Student> students) {
        if (!students.isEmpty()) {
            var counter = 0;
            for (Student student : students) {
                counter++;
                System.out.printf("%s%s. %s.%s%n", Utils.ANSI_CYAN, counter, student, Utils.ANSI_RESET);
            }
            System.out.println();
        } else {
            System.out.println(Utils.ANSI_RED + "Список студентов пуст!" + Utils.ANSI_RESET);
        }
    }

    public void deleteStudentFromList(int index) {
        if (!students.isEmpty()) {
            List<Student> studentsArray = new ArrayList<>();
            studentsArray.addAll(students);
            students.clear();
            studentsArray.remove(index - 1);
            students.addAll(studentsArray);
        }
    }

    private void demonstration() {
        addStudentInList(new Student("Ivanov", "Ivan", "Netology", "123"));
        addStudentInList(new Student("Kirillov", "Sergey", "Netology", "123"));
        addStudentInList(new Student("Ivanov", "Ivan", "Netology", "124"));
        addStudentInList(new Student("Ivanov", "Dmitry", "Netology", "125"));
        addStudentInList(new Student("Eric", "Temnitsky", "Kata-522", "129"));
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Эта программа эмулирует работу деканата!" + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: выход из программы.");
        System.out.println("1: добавить студента в список.");
        System.out.println("2: удалить студента из списка.");
        System.out.println("3: вывести на экран список студентов.");
        System.out.println("4: демонстрация.");
    }
}
```

* **Student** - класс, описывающий cтудента. имеет переопреденный *toString()* и геттер-методы для получения доступа к полям: surname, name, group, studentId.

#### Класс **Student**:
``` java   
public class Student {
    private String surname;
    private String name;
    private String group;
    private String studentId;

    public Student(String surname, String name, String group, String studentId) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        var student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public String toString() {
        return Utils.ANSI_PURPLE + "Студент " + group + "\nФИО: " + surname + " " + name + "\nИдентификатор: " +
                studentId + Utils.ANSI_RESET;
    }
}
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Эта программа эмулирует работу деканата!
Возможные команды программы:
0 или выход: выход из программы.
1: добавить студента в список.
2: удалить студента из списка.
3: вывести на экран список студентов.
4: демонстрация.
```