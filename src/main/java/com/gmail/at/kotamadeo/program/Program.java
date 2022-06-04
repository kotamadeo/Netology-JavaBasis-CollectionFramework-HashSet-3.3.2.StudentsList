package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.student.Student;
import com.gmail.at.kotamadeo.utils.Utils;

import java.util.*;

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
