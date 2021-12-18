package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 implements Task {

	// !!! Редактируйте этот метод !!!

	// в класс Person добавлен метод compareTo(Person person), который сортирует в требуемом порядке,
	// поэтому можно просто воспользоваться оператором sorted()
	private List<Person> sort(Collection<Person> persons) {
    List<Person> sortedPersons = new ArrayList<>(persons.stream()
            .sorted()
            .collect(Collectors.toList()));
    return sortedPersons;

	// update: обнаружила метод thenComparing(), он наверно и имелся
	// вами в виду при составлении задания. но я уже не стала удалять из класса Person метод compareTo....
	// иначе зачем он имплементит интерфейс Comparable?...........
// Вариант 2
//		List<Person> sortedPersons = persons.stream()
//				.sorted(Comparator.comparing(Person::getSecondName)
//						.thenComparing(Person::getFirstName)
//						.thenComparing(Person::getCreatedAt))
//				.collect(Collectors.toList());
//		return sortedPersons;
	}

	@Override
	public boolean check() {
		Instant time = Instant.now();
		List<Person> persons = List.of(
				new Person(1, "Oleg", "Ivanov", time),
				new Person(2, "Vasya", "Petrov", time),
				new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
				new Person(4, "Oleg", "Ivanov", time.plusSeconds(1))
		);
		List<Person> sortedPersons = List.of(
				new Person(1, "Oleg", "Ivanov", time),
				new Person(4, "Oleg", "Ivanov", time.plusSeconds(1)),
				new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
				new Person(2, "Vasya", "Petrov", time)
		);
		return sortedPersons.equals(sort(persons));
	}
}
