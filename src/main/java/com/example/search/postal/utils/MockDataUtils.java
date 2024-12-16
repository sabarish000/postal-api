package com.example.search.postal.utils;

import com.example.search.postal.models.City;
import com.example.search.postal.models.PostalAddress;
import com.example.search.postal.models.Role;
import com.example.search.postal.models.User;
import com.example.search.postal.security.PasswordUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockDataUtils {
    public static final List<City> cities = List.of(
            new City("BE001-BE-GE", "Berlin", "Berlin Region", "Mitte", "Berlin", "Germany"),
            new City("HA002-HH-GE", "Hamburg", "Hamburg Region", "Altstadt", "Hamburg", "Germany"),
            new City("MU003-BE-GE", "Munich", "Bavaria Region", "Altstadt-Lehel", "Bavaria", "Germany"),
            new City("CO004-NR-GE", "Cologne", "North Rhine-Westphalia Region", "Innenstadt", "North Rhine-Westphalia", "Germany"),
            new City("ST005-BA-GE", "Stuttgart", "Baden-Wurttemberg Region", "Stuttgart-Mitte", "Baden-Wurttemberg", "Germany"),
            new City("FR006-HE-GE", "Frankfurt", "Hesse Region", "Innenstadt", "Hesse", "Germany"),
            new City("OS007-LS-GE", "Osnabruck", "Lower Saxony Region", "Innenstadt", "Lower Saxony", "Germany"),
            new City("DO008-NR-GE", "Dortmund", "North Rhine-Westphalia Region", "Innenstadt-West", "North Rhine-Westphalia", "Germany"),
            new City("MA009-RH-GE", "Mainz", "Rhineland-Palatinate Region", "Altstadt", "Rhineland-Palatinate", "Germany"),
            new City("KA010-BA-GE", "Karlsruhe", "Baden-Wurttemberg Region", "Innenstadt-West", "Baden-Wurttemberg", "Germany"),
            new City("AU011-BA-GE", "Augsburg", "Bavaria Region", "Innenstadt", "Bavaria", "Germany"),
            new City("HA012-LS-GE", "Hannover", "Lower Saxony Region", "Mitte", "Lower Saxony", "Germany"),
            new City("RO013-MV-GE", "Rostock", "Mecklenburg-Vorpommern Region", "Innenstadt", "Mecklenburg-Vorpommern", "Germany"),
            new City("SC014-MV-GE", "Schwerin", "Mecklenburg-Vorpommern Region", "Altstadt", "Mecklenburg-Vorpommern", "Germany"),
            new City("DU015-NR-GE", "Dusseldorf", "North Rhine-Westphalia Region", "Stadtmitte", "North Rhine-Westphalia", "Germany"),
            new City("DR016-SA-GE", "Dresden", "Saxony Region", "Altstadt", "Saxony", "Germany"),
            new City("LE017-SA-GE", "Leipzig", "Saxony Region", "Zentrum-Süd", "Saxony", "Germany"),
            new City("MA018-SA-GE", "Magdeburg", "Saxony-Anhalt Region", "Altstadt", "Saxony-Anhalt", "Germany"),
            new City("PO019-BR-GE", "Potsdam", "Brandenburg Region", "Nördliche Innenstadt", "Brandenburg", "Germany"),
            new City("GO020-LS-GE", "Gottingen", "Lower Saxony Region", "Innenstadt", "Lower Saxony", "Germany"),
            new City("OL021-LS-GE", "Oldenburg", "Lower Saxony Region", "Innenstadt", "Lower Saxony", "Germany"),
            new City("ER022-TH-GE", "Erfurt", "Thuringia Region", "Altstadt", "Thuringia", "Germany"),
            new City("LU023-SH-GE", "Lubeck", "Schleswig-Holstein Region", "Innenstadt", "Schleswig-Holstein", "Germany"),
            new City("GR024-MV-GE", "Greifswald", "Mecklenburg-Vorpommern Region", "Innenstadt", "Mecklenburg-Vorpommern", "Germany"),
            new City("BR025-BR-GE", "Bremerhaven", "Bremen Region", "Mitte", "Bremen", "Germany"),
            new City("MA026-BA-GE", "Mannheim", "Baden-Wurttemberg Region", "Innenstadt", "Baden-Wurttemberg", "Germany"),
            new City("UL027-BA-GE", "Ulm", "Baden-Wurttemberg Region", "Innenstadt", "Baden-Wurttemberg", "Germany"),
            new City("KI028-SH-GE", "Kiel", "Schleswig-Holstein Region", "Vorstadt", "Schleswig-Holstein", "Germany"),
            new City("KO029-RH-GE", "Koblenz", "Rhineland-Palatinate Region", "Altstadt", "Rhineland-Palatinate", "Germany"),
            new City("WR030-MV-GE", "Wismar", "Mecklenburg-Vorpommern Region", "Altstadt", "Mecklenburg-Vorpommern", "Germany"),
            new City("NE031-NR-GE", "Neuss", "North Rhine-Westphalia Region", "Stadtmitte", "North Rhine-Westphalia", "Germany"),
            new City("RE032-NR-GE", "Recklinghausen", "North Rhine-Westphalia Region", "Innenstadt", "North Rhine-Westphalia", "Germany"),
            new City("BO033-NR-GE", "Bochum", "North Rhine-Westphalia Region", "Innenstadt", "North Rhine-Westphalia", "Germany"),
            new City("GE034-NR-GE", "Gelsenkirchen", "North Rhine-Westphalia Region", "Innenstadt", "North Rhine-Westphalia", "Germany"),
            new City("ME035-MV-GE", "Neubrandenburg", "Mecklenburg-Vorpommern Region", "Innenstadt", "Mecklenburg-Vorpommern", "Germany"),
            new City("RS036-NR-GE", "Remscheid", "North Rhine-Westphalia Region", "Innenstadt", "North Rhine-Westphalia", "Germany"),
            new City("WI037-HE-GE", "Wiesbaden", "Hesse Region", "Innenstadt", "Hesse", "Germany"),
            new City("DA038-HE-GE", "Darmstadt", "Hesse Region", "Innenstadt", "Hesse", "Germany")
    );


    public static final Map<String, City> citiesMap = cities.stream().collect(Collectors.toMap(City::getCode,
            city -> city));

    public static final List<PostalAddress> POSTAL_ADDRESS = List.of(
            new PostalAddress("10115", "Invalidenstrasse", "12", citiesMap.get("BE001-BE-GE")),
            new PostalAddress("20095", "Spitalerstrasse", "45A", citiesMap.get("HA002-HH-GE")),
            new PostalAddress("80331", "Marienplatz", "3", citiesMap.get("MU003-BE-GE")),
            new PostalAddress("50667", "Hohe Strasse", "10", citiesMap.get("CO004-NR-GE")),
            new PostalAddress("70173", "Konigstrasse", "5", citiesMap.get("ST005-BA-GE")),
            new PostalAddress("10117", "Friedrichstrasse", "43", citiesMap.get("BE001-BE-GE")),
            new PostalAddress("20148", "Rothenbaumchaussee", "112", citiesMap.get("HA002-HH-GE")),
            new PostalAddress("80335", "Arnulfstrasse", "27", citiesMap.get("MU003-BE-GE")),
            new PostalAddress("50672", "Friesenstrasse", "50", citiesMap.get("CO004-NR-GE")),
            new PostalAddress("70182", "Olgaeck", "14", citiesMap.get("ST005-BA-GE")),
            new PostalAddress("40210", "Oststrasse", "8", citiesMap.get("DU015-NR-GE")),
            new PostalAddress("60313", "Zeil", "106", citiesMap.get("FR006-HE-GE")),
            new PostalAddress("49074", "Johannisstrasse", "82", citiesMap.get("OS007-LS-GE")),
            new PostalAddress("44137", "Kleppingstrasse", "28", citiesMap.get("DO008-NR-GE")),
            new PostalAddress("55116", "Flachsmarktstrasse", "3", citiesMap.get("MA009-RH-GE")),
            new PostalAddress("76133", "Kaiserstrasse", "1", citiesMap.get("KA010-BA-GE")),
            new PostalAddress("86150", "Fuggerstrasse", "2", citiesMap.get("AU011-BA-GE")),
            new PostalAddress("30159", "Georgstrasse", "34", citiesMap.get("HA012-LS-GE")),
            new PostalAddress("18055", "Kröpeliner Strasse", "54", citiesMap.get("RO013-MV-GE")),
            new PostalAddress("19053", "Schweriner Strasse", "18", citiesMap.get("SC014-MV-GE")),
            new PostalAddress("10119", "Brunnenstrasse", "29", citiesMap.get("BE001-BE-GE")),
            new PostalAddress("20249", "Eppendorfer Baum", "25", citiesMap.get("HA002-HH-GE")),
            new PostalAddress("80336", "Lindwurmstrasse", "72", citiesMap.get("MU003-BE-GE")),
            new PostalAddress("50674", "Hohenstaufenring", "22", citiesMap.get("CO004-NR-GE")),
            new PostalAddress("70191", "Heilbronner Strasse", "21", citiesMap.get("ST005-BA-GE")),
            new PostalAddress("40211", "Schadowstrasse", "84", citiesMap.get("DU015-NR-GE")),
            new PostalAddress("60322", "Grüneburgweg", "14", citiesMap.get("FR006-HE-GE")),
            new PostalAddress("49084", "Bremer Strasse", "134", citiesMap.get("OS007-LS-GE")),
            new PostalAddress("44139", "Ostenhellweg", "35", citiesMap.get("DO008-NR-GE")),
            new PostalAddress("55118", "Rheinallee", "19", citiesMap.get("MA009-RH-GE")),
            new PostalAddress("76131", "Schlossplatz", "2", citiesMap.get("KA010-BA-GE")),
            new PostalAddress("86152", "Maximilianstrasse", "21", citiesMap.get("AU011-BA-GE")),
            new PostalAddress("30175", "Aegidientorplatz", "7", citiesMap.get("HA012-LS-GE")),
            new PostalAddress("18057", "Ulmenstrasse", "15", citiesMap.get("RO013-MV-GE")),
            new PostalAddress("19055", "Schweriner Schloss", "1", citiesMap.get("SC014-MV-GE")),
            new PostalAddress("01067", "Altmarkt", "10", citiesMap.get("DR016-SA-GE")),
            new PostalAddress("04109", "Augustusplatz", "1", citiesMap.get("LE017-SA-GE")),
            new PostalAddress("39104", "Breiter Weg", "5", citiesMap.get("MA018-SA-GE")),
            new PostalAddress("14467", "Brandenburger Strasse", "21", citiesMap.get("PO019-BR-GE")),
            new PostalAddress("37073", "Weender Strasse", "42", citiesMap.get("GO020-LS-GE")),
            new PostalAddress("26122", "Lambertikirchplatz", "18", citiesMap.get("OL021-LS-GE")),
            new PostalAddress("99084", "Anger", "25", citiesMap.get("ER022-TH-GE")),
            new PostalAddress("23552", "Breite Strasse", "50", citiesMap.get("LU023-SH-GE")),
            new PostalAddress("17489", "Markt", "10", citiesMap.get("GR024-MV-GE")),
            new PostalAddress("27568", "Hafenstrasse", "12", citiesMap.get("BR025-BR-GE")),
            new PostalAddress("67059", "Planken", "8", citiesMap.get("MA026-BA-GE")),
            new PostalAddress("89073", "Hirschstrasse", "23", citiesMap.get("UL027-BA-GE")),
            new PostalAddress("24103", "Holstenstrasse", "14", citiesMap.get("KI028-SH-GE")),
            new PostalAddress("56068", "Löhrstrasse", "28", citiesMap.get("KO029-RH-GE")),
            new PostalAddress("23966", "Lübsche Strasse", "50", citiesMap.get("WR030-MV-GE")),
            new PostalAddress("41460", "Krefelder Strasse", "30", citiesMap.get("NE031-NR-GE")),
            new PostalAddress("45657", "Herzogswall", "16", citiesMap.get("RE032-NR-GE")),
            new PostalAddress("44787", "Kortumstrasse", "22", citiesMap.get("BO033-NR-GE")),
            new PostalAddress("45879", "Bahnhofstrasse", "5", citiesMap.get("GE034-NR-GE")),
            new PostalAddress("17033", "Bergstrasse", "6", citiesMap.get("ME035-MV-GE")),
            new PostalAddress("42853", "Kölner Strasse", "18", citiesMap.get("RS036-NR-GE")),
            new PostalAddress("65183", "Kirchgasse", "1", citiesMap.get("WI037-HE-GE")),
            new PostalAddress("64283", "Luisenplatz", "2", citiesMap.get("DA038-HE-GE")),
            new PostalAddress("10179", "Stralauer Strasse", "15", citiesMap.get("BE001-BE-GE")),
            new PostalAddress("20354", "Jungfernstieg", "7", citiesMap.get("HA002-HH-GE")),
            new PostalAddress("80333", "Odeonsplatz", "4", citiesMap.get("MU003-BE-GE")),
            new PostalAddress("50676", "Neumarkt", "21", citiesMap.get("CO004-NR-GE")),
            new PostalAddress("70178", "Rotebühlplatz", "23", citiesMap.get("ST005-BA-GE")),
            new PostalAddress("60311", "Goetheplatz", "11", citiesMap.get("FR006-HE-GE")),
            new PostalAddress("49078", "Natruper Strasse", "77", citiesMap.get("OS007-LS-GE")),
            new PostalAddress("44135", "Kaiserstrasse", "40", citiesMap.get("DO008-NR-GE")),
            new PostalAddress("55116", "Ludwigsstrasse", "9", citiesMap.get("MA009-RH-GE")),
            new PostalAddress("76131", "Erbprinzenstrasse", "6", citiesMap.get("KA010-BA-GE")),
            new PostalAddress("86152", "Maximilianstrasse", "12", citiesMap.get("AU011-BA-GE")),
            new PostalAddress("30159", "Steintorstrasse", "38", citiesMap.get("HA012-LS-GE")),
            new PostalAddress("18057", "Waldemarstrasse", "19A", citiesMap.get("BE001-BE-GE"))
    );

    public static final List<User> USERS = List.of(
            new User("admin01", PasswordUtils.encode("12345"), Role.ADMIN),
            new User("user01", PasswordUtils.encode("0123"), Role.USER),
            new User("emp01", PasswordUtils.encode("0123"), Role.EMPLOYEE)
    );
}
