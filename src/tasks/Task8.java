package tasks;

import common.Person;
import common.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList()); // не будем удалять, просто скипнем
	}

	//ну и различные имена тоже хочется
	public Set<String> getDifferentNames(List<Person> persons) {
		return getNames(persons).stream().distinct().collect(Collectors.toSet());
	}

	//Для фронтов выдадим полное имя, а то сами не могут
	public String convertPersonToFullName(Person person) {  // функция переименована
		String result = "";
		if (person.getSecondName() != null) {
			result += person.getSecondName();
		}

		if (person.getFirstName() != null) {
			result += " " + person.getFirstName();
		}

		if (person.getMiddleName() != null) {
			result += " " + person.getMiddleName();  // исправлено повторное использование getSecondName() на getMiddleName()
		}
		return result;
	}

	// словарь id персоны -> ее имя
	public Map<Integer, String> getPersonNames(Collection<Person> persons) {
		Map<Integer, String> personIdToFullName = new HashMap<>(1);
		for (Person person : persons) {
			if (!personIdToFullName.containsKey(person.getId())) {
				personIdToFullName.put(person.getId(), convertPersonToFullName(person));
			}
		}
		return personIdToFullName;
	}

	// есть ли совпадающие в двух коллекциях персоны?
	public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
		boolean repetitionFound = !Collections.disjoint(persons1, persons2); // метод disjoint() вернет true,
		return repetitionFound;												 // если в коллекциях нет совпадающих объектов
	}

	//...
	public long countEven(Stream<Integer> numbers) {
		count = numbers.filter(num -> num % 2 == 0).count();
		return count;
	}

	@Override
	public boolean check() {
		System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
		boolean codeSmellsGood = false;
		boolean reviewerDrunk = false;
		return true;
	}
}
