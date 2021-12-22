package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

	private long count;

	//Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
	public List<String> getNames(List<Person> persons) {
		if (persons.size() == 0) {
			return Collections.emptyList();
		}
		return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
		// не будем удалять, просто скипнем
		// так лучше, потому что позволяет сохранить без изменений исходный список персон
	}

	//ну и различные имена тоже хочется
	public Set<String> getDifferentNames(List<Person> persons) {
		return new HashSet<>(getNames(persons));
	}

	//Для фронтов выдадим полное имя, а то сами не могут
	public String convertPersonToFullName(Person person) {  // функция переименована
		if (person == null) // а какой баг имелся в виду? типа что может упасть, если вместо персоны null передать?
			return "";		// больше я вроде ниче не заметила багового..
		return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName()) // ну теперь красиво?)
				.filter(Objects::nonNull).collect(Collectors.joining(" "));
	}

	// словарь id персоны -> ее имя
	public Map<Integer, String> getPersonNames(Collection<Person> persons) {
		return persons.stream().collect(Collectors.toMap(Person::getId,
										this::convertPersonToFullName,
										(previous, next) -> previous));
	}

	// есть ли совпадающие в двух коллекциях персоны?
	public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
		return !Collections.disjoint(persons1, persons2); // метод disjoint() вернет true,
	}	 											 // если в коллекциях нет совпадающих объектов

	//...
	// метод вроде и до правок функционировал верно, но так проще для восприятия
	public long countEven(Stream<Integer> numbers) {
		count = numbers.filter(num -> num % 2 == 0).count();
		return count;
	}

	@Override
	public boolean check() {
		System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
		boolean codeSmellsGood = true;
		boolean reviewerDrunk = false;
		return codeSmellsGood||reviewerDrunk;
	}
}
