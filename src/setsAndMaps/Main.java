package setsAndMaps;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Fores");
        robinHood.addEmail("Sherwood Fores");
        System.out.println(robinHood);

        Set<Contact> unionAb = new HashSet<>();
        unionAb.addAll(emailContacts);
        unionAb.addAll(phoneContacts);
        printData("Unions ", unionAb);

        Set<Contact> intersetAb = new HashSet<>(emailContacts);
        intersetAb.retainAll(phoneContacts);
        printData("Intersect a-b ", intersetAb);

        Set<Contact> intersetBa = new HashSet<>(phoneContacts);
        intersetBa.retainAll(emailContacts);
        printData("Intersect b-a ", intersetBa);

        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("emails minus phones ", AMinusB);

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("Phones minus emails ", BMinusA);

        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric ", symmetricDiff);

        Set<Contact> symmetricDiff2 = new HashSet<>(unionAb);
        symmetricDiff2.removeAll(intersetAb);
        printData("Symmetric differnce ", symmetricDiff2);

    }

    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("-----------");
        System.out.println(header);
        System.out.println("-----------");
        contacts.forEach(System.out::println);
    }

}
