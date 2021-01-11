import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            );
        }

        Long minors = persons.stream()
                .filter(per -> per.getAge() < 18)
                .count();

        List<String> familiesOfConscripts = persons.stream()
                .filter(per -> per.getAge() >= 18 && per.getAge() <= 27)
                .map(per -> per.getFamily())
                .collect(Collectors.toList());

        List<Person> ableBodiedPeople = persons.stream()
                .filter(per -> per.getEducation() == Education.HIGHER)
                .filter(per -> per.getAge() >= 18 && per.getAge() <= 60 && per.getSex() == Sex.WOMEN)
                .filter(per -> per.getAge() >= 18 && per.getAge() <= 65 && per.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

    }
}
