package pme.ai.fhe.de.studybuddy.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pme.ai.fhe.de.studybuddy.administration.daos.CityDao;
import pme.ai.fhe.de.studybuddy.administration.daos.UniversityDao;
import pme.ai.fhe.de.studybuddy.model.Category;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.Semester;
import pme.ai.fhe.de.studybuddy.model.University;

/**
 * This class provides all the data necessary for creating the database.
 * Since the async task can not handle ArrayLists, all the data is generated with normal arrays.
 */
public class DataProvider {

    public DataProvider() {

    }

    /**
     * Creates a city array
     * @return Array of cities to be inserted into database
     */
    public City[] generateCities() {
        City[] allCities = new City[3];
        City city = new City("Erfurt");
        allCities[0] = city;
        city = new City("Jena");
        allCities[1] = city;
        city = new City("Weimar");
        allCities[2] = city;
        return allCities;
    }

    /**
     * Creates a university array
     * @param cityDao to determine the city id in which the university is
     * @return Array of universities to be inserted into database
     */
    public University[] generateUniversities(CityDao cityDao) {
        University[] allUniversities = new University[4];
        University university = new University("FH Erfurt", cityDao.getCityIdByName("Erfurt"));
        allUniversities[0] = university;
        university = new University("Universität Erfurt", cityDao.getCityIdByName("Erfurt"));
        allUniversities[1] = university;
        university = new University("FH Jena", cityDao.getCityIdByName("Jena"));
        allUniversities[2] = university;
        university = new University("Bauhausuniversität Weimar", cityDao.getCityIdByName("Weimar"));
        allUniversities[3] = university;
        return allUniversities;
    }

    /**
     * Creates a course of studies array
     * @param universityDao to determine the university id the course belongs to
     * @return array of course of studies to be inserted into database
     */
    public CourseOfStudies[] generateCourseOfStudies(UniversityDao universityDao) {
        CourseOfStudies[] allCourses = new CourseOfStudies[5];
        CourseOfStudies course = new CourseOfStudies("Angewandte Informatik", 6, 180, "Gebäudetechnik und Informatik", universityDao.getUniversityIdByName("FH Erfurt"));
        allCourses[0] = course;
        course = new CourseOfStudies("Architektur", 6, 180, "Architektur und Stadtplanung", universityDao.getUniversityIdByName("FH Erfurt"));
        allCourses[1] = course;
        course = new CourseOfStudies("Soziale Arbeit", 6, 180, "Angewandte Sozialwissenschaften", universityDao.getUniversityIdByName("FH Erfurt"));
        allCourses[2] = course;
        course = new CourseOfStudies("Medieninformatik", 6, 180, "Medien", universityDao.getUniversityIdByName("Bauhausuniversität Weimar"));
        allCourses[3] = course;
        course = new CourseOfStudies("Laser- und Optotechnologien", 6, 180, "Elektrotechnik und Informationstechnik", universityDao.getUniversityIdByName("FH Jena"));
        allCourses[4] = course;

        return allCourses;
    }

    /**
     * Creates a module array
     * @return array of modules to be inserted into database
     */
    public Module[] generateModules() {
        Module[] allmodules = new Module[28];
        Module module = new Module(1,"Mathematik", 14, "1010");
        allmodules[0] = module;
        module = new Module(1,"Theoretische Informatik", 10, "1020");
        allmodules[1] = module;
        module = new Module(1,"Technische Informatik", 6, "1030");
        allmodules[2] = module;
        module = new Module(1,"Programmierung", 17, "1040");
        allmodules[3] = module;
        module = new Module(1,"Multimedia", 2, "1050");
        allmodules[4] = module;
        module = new Module(1,"Betriebswirtschaftslehre", 2, "1060");
        allmodules[5] = module;
        module = new Module(1,"Englisch", 2, "1070");
        allmodules[6] = module;
        module = new Module(1,"Betriebssysteme", 6, "1080");
        allmodules[7] = module;
        module = new Module(1,"Datenbanken", 7, "1090");
        allmodules[8] = module;
        module = new Module(1,"Softwaretechnik", 8, "1110");
        allmodules[9] = module;
        module = new Module(1,"Netze", 6, "1120");
        allmodules[10] = module;
        module = new Module(1,"Grafische Datenverarbeitung", 4, "1130");
        allmodules[11] = module;
        module = new Module(1,"IT-Kolloquium", 2, "1140");
        allmodules[12] = module;
        module = new Module(1,"IT-Sicherheit", 2, "1150");
        allmodules[13] = module;
        module = new Module(1,"IT-Recht", 2, "1160");
        allmodules[14] = module;
        module = new Module(1,"Praxisprojekt", 4, "1170");
        allmodules[15] = module;
        module = new Module(1,"Praxismodul", 22, "1180");
        allmodules[16] = module;
        module = new Module(1,"Bachelorarbeit", 10, "1190");
        allmodules[17] = module;
        module = new Module(1,"Medientechnik", 2, "2010");
        allmodules[18] = module;
        module = new Module(1,"Digitale Medien", 22, "2020");
        allmodules[19] = module;
        module = new Module(1,"Multimediaproduktion", 4, "2030");
        allmodules[20] = module;
        module = new Module(1,"Medienrecht", 4, "2040");
        allmodules[21] = module;
        module = new Module(1,"Existenzgründung", 2, "5280");
        allmodules[22] = module;
        module = new Module(1,"Zivilrecht", 4, "5270");
        allmodules[23] = module;
        module = new Module(1,"Marketing", 4, "5260");
        allmodules[24] = module;
        module = new Module(1,"Operative Anwendungssysteme", 4, "5250");
        allmodules[25] = module;
        module = new Module(1,"Programmierung Mobiler Endgeräte", 4, "5240");
        allmodules[26] = module;
        module = new Module(1,"Dynamische Webprogrammierung", 4, "5230");
        allmodules[27] = module;

        return allmodules;
    }

    /**
     * Creates a lecture array
     * @return array of lectures to be inserted into database
     */
    public Lecture[] generateLectures() {
        Lecture[] allLectures = new Lecture[41];

        Lecture lecture = new Lecture(1, 1, "Mathematik 1", 6, true, 5, "deutsch");
        allLectures[0] = lecture;
        lecture = new Lecture(1, 1, "Mathematik 2", 6, true, 5, "deutsch");
        allLectures[1] = lecture;
        lecture = new Lecture(1, 1, "Mathematik 3", 2, true, 5, "deutsch");
        allLectures[2] = lecture;
        lecture = new Lecture(1, 2, "Theoretische Informatik 1", 6, true, 5, "deutsch");
        allLectures[3] = lecture;
        lecture = new Lecture(1, 2, "Theoretische Informatik 2", 4, true, 5, "deutsch");
        allLectures[4] = lecture;
        lecture = new Lecture(1, 3, "Digitaltechnik", 3, true, 5, "deutsch");
        allLectures[5] = lecture;
        lecture = new Lecture(1, 3, "Rechnerarchitektur", 3, true, 5, "deutsch");
        allLectures[6] = lecture;
        lecture = new Lecture(1, 4, "Programmieren 1", 6, true, 1, "deutsch");
        allLectures[7] = lecture;
        lecture = new Lecture(1, 4, "Programmieren 2", 4, true, 1, "deutsch");
        allLectures[8] = lecture;
        lecture = new Lecture(1, 4, "Programmieren 3", 3, true, 1, "deutsch");
        allLectures[9] = lecture;
        lecture = new Lecture(1, 4, "Programmieren 4", 4, true, 1, "deutsch");
        allLectures[10] = lecture;
        lecture = new Lecture(1, 5, "Multimedia", 2, true, 7, "deutsch");
        allLectures[11] = lecture;
        lecture = new Lecture(1, 6, "Betriebswirtschaftslehre", 2, true, 7, "deutsch");
        allLectures[12] = lecture;
        lecture = new Lecture(1, 7, "Englisch", 2, true, 2, "deutsch");
        allLectures[13] = lecture;
        lecture = new Lecture(1, 8, "Betriebssysteme 1", 4, true, 7, "deutsch");
        allLectures[14] = lecture;
        lecture = new Lecture(1, 8, "Betriebssysteme 2", 2, true, 7, "deutsch");
        allLectures[15] = lecture;
        lecture = new Lecture(1, 9, "Datenbanken 1", 4, true, 1, "deutsch");
        allLectures[16] = lecture;
        lecture = new Lecture(1, 9, "Datenbanken 2", 3, true, 1, "deutsch");
        allLectures[17] = lecture;
        lecture = new Lecture(1, 10, "Softwaretechnik 1", 4, true, 1, "deutsch");
        allLectures[18] = lecture;
        lecture = new Lecture(1, 10, "Softwaretechnik 2", 4, true, 1, "deutsch");
        allLectures[19] = lecture;
        lecture = new Lecture(1, 11, "Netze 1", 4, true, 6, "deutsch");
        allLectures[20] = lecture;
        lecture = new Lecture(1, 11, "Netze 2", 2, true, 6, "deutsch");
        allLectures[21] = lecture;
        lecture = new Lecture(1, 12, "Grafische Datenverarbeitung", 4, true, 1, "deutsch");
        allLectures[22] = lecture;
        lecture = new Lecture(1, 13, "It-Kolloquium", 2, true, 7, "deutsch");
        allLectures[23] = lecture;
        lecture = new Lecture(1, 14, "It-Sicherheit", 2, true, 7, "deutsch");
        allLectures[24] = lecture;
        lecture = new Lecture(1, 15, "It-Recht", 2, true, 7, "deutsch");
        allLectures[25] = lecture;
        lecture = new Lecture(1, 16, "Praxisprojekt", 4, true, 1, "deutsch");
        allLectures[26] = lecture;
        lecture = new Lecture(1, 17, "Praxismodul", 22, true, 7, "deutsch");
        allLectures[27] = lecture;
        lecture = new Lecture(1, 18, "Bachelorarbeit", 10, true, 3, "deutsch");
        allLectures[28] = lecture;
        lecture = new Lecture(1, 19, "Medientechnik", 2, true, 4, "deutsch");
        allLectures[29] = lecture;
        lecture = new Lecture(1, 20, "Digitale Medien 1", 8, true, 4, "deutsch");
        allLectures[30] = lecture;
        lecture = new Lecture(1, 20, "Digitale Medien 2", 6, true, 4, "deutsch");
        allLectures[31] = lecture;
        lecture = new Lecture(1, 20, "Digitale Medien 3", 8, true, 4, "deutsch");
        allLectures[32] = lecture;
        lecture = new Lecture(1, 21, "Multimediaproduktion", 4, true, 4, "deutsch");
        allLectures[33] = lecture;
        lecture = new Lecture(1, 22, "Medienrecht 1", 2, true, 4, "deutsch");
        allLectures[34] = lecture;
        lecture = new Lecture(1, 22, "Medienrecht 2", 2, true, 4, "deutsch");
        allLectures[35] = lecture;
        lecture = new Lecture(1, 23, "Existenzgründung", 2, false, 7, "deutsch");
        allLectures[36] = lecture;
        lecture = new Lecture(1, 24, "Zivilrecht", 4, false, 7, "deutsch");
        allLectures[36] = lecture;
        lecture = new Lecture(1, 25, "Marketing", 4, false, 7, "deutsch");
        allLectures[37] = lecture;
        lecture = new Lecture(1, 26, "Operative Anwendungssysteme", 4, false, 7, "deutsch");
        allLectures[38] = lecture;
        lecture = new Lecture(1, 27, "Programmierung Mobiler Endgeräte", 4, false, 1, "deutsch");
        allLectures[39] = lecture;
        lecture = new Lecture(1, 28, "Dynamische Webprogrammierung", 4, false, 1, "deutsch");
        allLectures[40] = lecture;

        return allLectures;
    }

    /**
     * Creates a category array
     * @return array of categories to be inserted into database
     */
    public Category[] generateCategories() {
        Category[] allCategories = new Category[7];

        Category category = new Category("Programmieren");
        allCategories[0] = category;
        category = new Category("Sprachen");
        allCategories[1] = category;
        category = new Category("Bachelorarbeit");
        allCategories[2] = category;
        category = new Category("Vertiefungsrichtung");
        allCategories[3] = category;
        category = new Category("Algebra");
        allCategories[4] = category;
        category = new Category("Netzwerk");
        allCategories[5] = category;
        category = new Category("Allgemein");
        allCategories[6] = category;


        return allCategories;
    }

    /**
     * Creates a semester array with the help of the current date. It generates an array with
     * 7 Semester backwards from the current date.
     * @return array of semester to be inserted into the database
     */
    public Semester[] generateSemester() {
        Semester[] allSemester = new Semester[16];
        List<Semester> semesterList = new ArrayList<>();

        Calendar now = Calendar.getInstance();

        int year = now.get(Calendar.YEAR);
        year -= 6;

        for(int i = 0; i <= 7; i++) {
            String yearString = Integer.toString(year).substring(2,4);
            String yearStringPlus = Integer.toString(year+1).substring(2,4);
            semesterList.add(new Semester("SS-" + yearString));
            semesterList.add( new Semester("WS-" + yearString + "/" + yearStringPlus));
            year++;
        }

        for(int j = 0; j < semesterList.size(); j++) {
            allSemester[j] = semesterList.get(j);
        }

        return allSemester;
    }
}
