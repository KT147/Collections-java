package treeset;

import setsAndMaps.Contact;
import setsAndMaps.ContactData;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        Comparator<Contact> mySort = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(mySort);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);

        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("---------------");
        fullList.forEach(System.out::println);

        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());

        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.println("---------------");
        System.out.printf("%s, %s %n", min.getName(), first.getName());

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println(copiedSet.pollFirst());

        NavigableSet<Contact> decendingSet = fullSet.descendingSet();
        decendingSet.forEach(System.out::println);
        System.out.println("---------------");

        Contact lastContact = decendingSet.pollLast();
        System.out.println("Removed " + lastContact);
        decendingSet.forEach(System.out::println);
        System.out.println("---------------");
        fullSet.forEach(System.out::println);
        System.out.println("---------------");

        Contact marion = new Contact("Maid Marion");
        var headSet = fullSet.headSet(marion, true);
        headSet.forEach(System.out::println);
        System.out.println("---------------");

        var tailSet = fullSet.tailSet(marion, false);
        tailSet.forEach(System.out::println);
        System.out.println("---------------");

        Contact linus = new Contact("Linus Van Pelt");
        var subset = fullSet.subSet(linus, marion);
        subset.forEach(System.out::println);
    }
}
