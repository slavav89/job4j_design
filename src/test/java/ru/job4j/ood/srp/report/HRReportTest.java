package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HRReportTest {
    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerOne = new Employee("Ivan", now, now, 100);
        Employee workerTwo = new Employee("Sergey", now, now, 200);
        Employee workerThree = new Employee("Oleg", now, now, 300);
        store.add(workerOne);
        store.add(workerThree);
        store.add(workerTwo);
        Report engine = new HRReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerThree.getName()).append(" ")
                .append(workerThree.getSalary())
                .append(System.lineSeparator())
                .append(workerTwo.getName()).append(" ")
                .append(workerTwo.getSalary())
                .append(System.lineSeparator())
                .append(workerOne.getName()).append(" ")
                .append(workerOne.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}