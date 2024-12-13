package com.example.search.postal.services;

import com.example.search.postal.models.PostalAddress;
import com.example.search.postal.models.Role;
import com.example.search.postal.models.User;

import java.util.List;
import java.util.Optional;

public class MockDataUtils {
    public static final List<PostalAddress> POSTAL_ADDRESS = List.of(
            new PostalAddress("10115", "Invalidenstrasse", "12", "Berlin", "Berlin Region", "Mitte", "Berlin",
                    "Germany"),
            new PostalAddress("20095", "Spitalerstrasse", "45A", "Hamburg", "Hamburg Region", "Altstadt",
                    "Hamburg", "Germany"),
            new PostalAddress("80331", "Marienplatz", "3", "Munich", "Bavaria Region", "Altstadt-Lehel", "Bavaria"
                    , "Germany"),
            new PostalAddress("50667", "Hohe Strasse", "10", "Cologne", "North Rhine-Westphalia Region",
                    "Innenstadt", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("70173", "Konigstrasse", "5", "Stuttgart", "Baden-Wurttemberg Region", "Stuttgart" +
                    "-Mitte", "Baden-Wurttemberg", "Germany"),
            new PostalAddress("10117", "Friedrichstrasse", "43", "Berlin", "Berlin Region", "Mitte", "Berlin",
                    "Germany"),
            new PostalAddress("20148", "Rothenbaumchaussee", "112", "Hamburg", "Hamburg Region", "Rotherbaum",
                    "Hamburg", "Germany"),
            new PostalAddress("80335", "Arnulfstrasse", "27", "Munich", "Bavaria Region", "Maxvorstadt", "Bavaria"
                    , "Germany"),
            new PostalAddress("50672", "Friesenstrasse", "50", "Cologne", "North Rhine-Westphalia Region",
                    "Neustadt-Nord", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("70182", "Olgaeck", "14", "Stuttgart", "Baden-Wurttemberg Region", "Stuttgart-Ost",
                    "Baden-Wurttemberg", "Germany"),
            new PostalAddress("40210", "Oststrasse", "8", "Dusseldorf", "North Rhine-Westphalia Region",
                    "Stadtmitte", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("60313", "Zeil", "106", "Frankfurt", "Hesse Region", "Innenstadt", "Hesse",
                    "Germany"),
            new PostalAddress("49074", "Johannisstrasse", "82", "Osnabruck", "Lower Saxony Region", "Innenstadt",
                    "Lower Saxony", "Germany"),
            new PostalAddress("44137", "Kleppingstrasse", "28", "Dortmund", "North Rhine-Westphalia Region",
                    "Innenstadt-West", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("55116", "Flachsmarktstrasse", "3", "Mainz", "Rhineland-Palatinate Region",
                    "Altstadt", "Rhineland-Palatinate", "Germany"),
            new PostalAddress("76133", "Kaiserstrasse", "1", "Karlsruhe", "Baden-Wurttemberg Region", "Innenstadt" +
                    "-West", "Baden-Wurttemberg", "Germany"),
            new PostalAddress("86150", "Fuggerstrasse", "2", "Augsburg", "Bavaria Region", "Innenstadt", "Bavaria"
                    , "Germany"),
            new PostalAddress("30159", "Georgstrasse", "34", "Hannover", "Lower Saxony Region", "Mitte", "Lower " +
                    "Saxony", "Germany"),
            new PostalAddress("18055", "Kröpeliner Strasse", "54", "Rostock", "Mecklenburg-Vorpommern Region",
                    "Innenstadt", "Mecklenburg-Vorpommern", "Germany"),
            new PostalAddress("19053", "Schweriner Strasse", "18", "Schwerin", "Mecklenburg-Vorpommern Region",
                    "Altstadt", "Mecklenburg-Vorpommern", "Germany"),
            new PostalAddress("10119", "Brunnenstrasse", "29", "Berlin", "Berlin Region", "Mitte", "Berlin",
                    "Germany"),
            new PostalAddress("20249", "Eppendorfer Baum", "25", "Hamburg", "Hamburg Region", "Eppendorf",
                    "Hamburg", "Germany"),
            new PostalAddress("80336", "Lindwurmstrasse", "72", "Munich", "Bavaria Region", "Ludwigsvorstadt" +
                    "-Isarvorstadt", "Bavaria", "Germany"),
            new PostalAddress("50674", "Hohenstaufenring", "22", "Cologne", "North Rhine-Westphalia Region",
                    "Neustadt-Süd", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("70191", "Heilbronner Strasse", "21", "Stuttgart", "Baden-Wurttemberg Region",
                    "Nordbahnhof", "Baden-Wurttemberg", "Germany"),
            new PostalAddress("40211", "Schadowstrasse", "84", "Dusseldorf", "North Rhine-Westphalia Region",
                    "Stadtmitte", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("60322", "Grüneburgweg", "14", "Frankfurt", "Hesse Region", "Westend-Nord", "Hesse"
                    , "Germany"),
            new PostalAddress("49084", "Bremer Strasse", "134", "Osnabruck", "Lower Saxony Region", "Schinkel",
                    "Lower Saxony", "Germany"),
            new PostalAddress("44139", "Ostenhellweg", "35", "Dortmund", "North Rhine-Westphalia Region",
                    "Innenstadt-West", "North Rhine-Westphalia", "Germany"),
            new PostalAddress("55118", "Rheinallee", "19", "Mainz", "Rhineland-Palatinate Region", "Neustadt",
                    "Rhineland-Palatinate", "Germany"),
            new PostalAddress("76131", "Schlossplatz", "2", "Karlsruhe", "Baden-Wurttemberg Region", "Innenstadt" +
                    "-Ost", "Baden-Wurttemberg", "Germany"),
            new PostalAddress("86152", "Maximilianstrasse", "21", "Augsburg", "Bavaria Region", "Innenstadt",
                    "Bavaria", "Germany"),
            new PostalAddress("30175", "Aegidientorplatz", "7", "Hannover", "Lower Saxony Region", "Südstadt",
                    "Lower Saxony", "Germany"),
            new PostalAddress("18057", "Ulmenstrasse", "15", "Rostock", "Mecklenburg-Vorpommern Region",
                    "Hansaviertel", "Mecklenburg-Vorpommern", "Germany"),
            new PostalAddress("19055", "Schweriner Schloss", "1", "Schwerin", "Mecklenburg-Vorpommern Region",
                    "Altstadt", "Mecklenburg-Vorpommern", "Germany"),
            new PostalAddress("01067", "Altmarkt", "10", "Dresden", "Saxony Region", "Altstadt", "Saxony",
                    "Germany"),
            new PostalAddress("04109", "Augustusplatz", "1", "Leipzig", "Saxony Region", "Zentrum-Süd", "Saxony",
                    "Germany"),
            new PostalAddress("39104", "Breiter Weg", "5", "Magdeburg", "Saxony-Anhalt Region", "Altstadt",
                    "Saxony-Anhalt", "Germany"),
            new PostalAddress("14467", "Brandenburger Strasse", "21", "Potsdam", "Brandenburg Region", "Nördliche" +
                    " Innenstadt", "Brandenburg", "Germany"),
            new PostalAddress("37073", "Weender Strasse", "42", "Gottingen", "Lower Saxony Region", "Innenstadt",
                    "Lower Saxony", "Germany"),
            new PostalAddress("26122", "Lambertikirchplatz", "18", "Oldenburg", "Lower Saxony Region",
                    "Innenstadt", "Lower Saxony", "Germany"),
            new PostalAddress("99084", "Anger", "25", "Erfurt", "Thuringia Region", "Altstadt", "Thuringia",
                    "Germany"),
            new PostalAddress("23552", "Breite Strasse", "50", "Lubeck", "Schleswig-Holstein Region", "Innenstadt"
                    , "Schleswig-Holstein", "Germany"),
            new PostalAddress("17489", "Markt", "10", "Greifswald", "Mecklenburg-Vorpommern Region", "Innenstadt"
                    , "Mecklenburg-Vorpommern", "Germany"),
            new PostalAddress("27568", "Hafenstrasse", "12", "Bremerhaven", "Bremen Region", "Mitte", "Bremen",
                    "Germany"),
            new PostalAddress("67059", "Planken", "8", "Mannheim", "Baden-Wurttemberg Region", "Innenstadt",
                    "Baden-Wurttemberg", "Germany"),
            new PostalAddress("89073", "Hirschstrasse", "23", "Ulm", "Baden-Wurttemberg Region", "Innenstadt",
                    "Baden-Wurttemberg", "Germany"),
            new PostalAddress("24103", "Holstenstrasse", "14", "Kiel", "Schleswig-Holstein Region", "Vorstadt",
                    "Schleswig-Holstein", "Germany"),
            new PostalAddress("56068", "Löhrstrasse", "28", "Koblenz", "Rhineland-Palatinate Region", "Altstadt",
                    "Rhineland-Palatinate", "Germany")
    );

    public static final List<User> USERS = List.of(
            new User("admin01", "12345", Role.ADMIN),
            new User("user01", "0123", Role.USER),
            new User("emp01", "0123", Role.EMPLOYEE)
    );
}
