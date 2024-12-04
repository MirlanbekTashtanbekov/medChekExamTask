import dao.GenericServiceDao;
import database.GenerateId;
import enums.Gender;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import service.*;
import service.serviceImpl.DepartmentServiceImpl;
import service.serviceImpl.DoctorServiceImpl;
import service.serviceImpl.HospitalServiceImpl;
import service.serviceImpl.PatientServiceImpl;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        HospitalService hospitalService = new HospitalServiceImpl();

        DepartmentService departmentService = new DepartmentServiceImpl();
        GenericService<Department> departmentGenericService = new DepartmentServiceImpl();

        DoctorService doctorService = new DoctorServiceImpl();
        GenericService<Doctor> doctorGenericService = new DoctorServiceImpl();

        PatientService patientService = new PatientServiceImpl();
        GenericService<Patient> patientGenericService = new PatientServiceImpl();





        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Төмөндөгү баскыч сандары аркылуу тийиштүү аракетти тандаңыз! ");
            System.out.println("1. Жаны оорукана түзүү");
            System.out.println("2. Оорукананы ID номери аркылуу табуу");
            System.out.println("3. Оорукананы ID номери аркылуу өчүрүү");
            System.out.println("4. Ооруканалардын тизмесин көрсөтүү");
            System.out.println("5. Ооруканагы бөлүм кошуу");
            System.out.println("6. Бөлүмдү аты менен табуу");
            System.out.println("7. Ооруканага тийиштүү бөлүмдөрдү чыгаруу");
            System.out.println("8. Бөлүмгө врачтарды киргизүү");
            System.out.println("9. Бөлүмдүн врачтарын көрсөтүү");
            System.out.println("10. Оорукананын врачтарын көрсөтүү");
            System.out.println("11. Ооруканага бейтаптарды кошуу");
            System.out.println("12. Бейтапты ID номери аркылуу табуу");
            System.out.println("13. Бейтаптарды жашына жараша чыгаруу");
            System.out.println("14. Бейтаптарды жашына жараша сорттоо");
            System.out.println("15. Бөлүмдү номери аркылуу өчүрүү");
            System.out.println("16. Бейтаптарды номери аркылуу өчүрүү");
            System.out.println("17. Врачтарды номери аркылуу өчүрүү");
            System.out.println("18. Чыгуу");


            int choice = scanner.nextInt();

            switch (choice)  {
                case 1 -> {
                    System.out.print("Оорукананын атын жазыныз: ");
                    String name = scanner.nextLine();
                    System.out.print("Адреси: ");
                    String address = scanner.nextLine();

                    Hospital hospital = new Hospital();
                    hospital.setId(GenerateId.getHospitalId());
                    hospital.setHospitalNam(name);
                    hospital.setAddress(address);
                    hospital.setDepartments(new ArrayList<>());
                    hospital.setDoctors(new ArrayList<>());
                    hospital.setPatients(new ArrayList<>());

                    System.out.println(hospitalService.addHospital(hospital));
                }
                case 2 -> {
                    System.out.print("Оорукананын ID номерин жазыныз издөө үчүн: ");
                    Long id = scanner.nextLong();
                    Hospital hospital = hospitalService.findHospitalById(id);
                    if (hospital != null) {
                        System.out.println("Табылды: " + hospital.getHospitalNam() + ", " + hospital.getAddress());
                    } else {
                        System.out.println("Сиз жазган номер тизмеде жок");
                    }
                }
                case 3 -> {
                    System.out.print("Оорукананы өчүрүү үчүн ID номерин жазыныз : ");
                    Long id = scanner.nextLong();
                    System.out.println(hospitalService.deleteHospitalById(id));
                }
                case 4 -> {
                    List<Hospital> hospitals = hospitalService.getAllHospital();
                    if (hospitals.isEmpty()) {
                    } else {
                        hospitals.forEach(h -> System.out.println(h.getId() + ": " + h.getHospitalNam() + ", " + h.getAddress()));
                    }
                }
                case 5 -> {
                    System.out.print("Ооркананын ID номерин жазыныз : ");
                    Long hospitalId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Бөлүмдүн атын жазыныз: ");
                    String departmentName = scanner.nextLine();

                    Department department = new Department();
                    department.setId(GenerateId.getDepartmentId());
                    department.setDepartmentName(departmentName);
                    department.setDoctors(new ArrayList<>());

                    System.out.println(departmentGenericService.add(hospitalId, department));
                }
                case 6 -> {
                    System.out.print("Бөлүмдүн атын жазыныз: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    Department department = departmentService.findDepartmentByName(name);
                    if (department != null) {
                        System.out.println("Табылган бөлүм: " + department.getDepartmentName());
                    } else {
                        System.out.println("Сиз издеген бөлум табылган жок.");
                    }
                }
                case 7 -> {
                    System.out.print("Оорукананын ID номерин жазыныз: ");
                    Long hospitalId = scanner.nextLong();
                    List<Department> departments = departmentService.getAllDepartmentByHospital(hospitalId);
                    if (departments.isEmpty()) {
                    } else {
                        departments.forEach(d -> System.out.println(d.getId() + ": " + d.getDepartmentName()));
                    }
                }
                case 8 -> {
                    System.out.print("Бөлүмдүң ID номерин жазыныз : ");
                    Long departmentId = scanner.nextLong();
                    System.out.print("Канча врачты каттайсыз? ");
                    int count = scanner.nextInt();
                    List<Long> doctorIds = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        doctorIds.add(GenerateId.getDoctorId());
                        scanner.nextLine();
                        System.out.print("Врачтын аты: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Врачтын фамилиясы: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Жынысы (MALE/FEMALE): ");
                        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
                        System.out.print("Врачтын иштеген тажрыйбасы (жыл): ");
                        int experience = scanner.nextInt();

                        Doctor doctor = new Doctor();
                        doctor.setId(GenerateId.getDoctorId());
                        doctor.setFirstName(firstName);
                        doctor.setLastName(lastName);
                        doctor.setGender(gender);
                        doctor.setExperienceYear(experience);

                        System.out.println(doctorGenericService.add(GenerateId.getDoctorId(),doctor));
                    }
                    System.out.println(doctorService.assignDoctorToDepartment(departmentId, doctorIds));
                }
                case 9 -> {
                    System.out.print("Бөлүмдүн ID номерин жазыныз : ");
                    Long departmentId = scanner.nextLong();
                    List<Doctor> doctors = doctorService.getAllDoctorsByDepartmentId(departmentId);
                    if (doctors.isEmpty()) {
                    } else {
                        doctors.forEach(d -> System.out.println(d.getId() + ": " + d.getFirstName() + " " + d.getLastName()));
                    }
                }
                case 10 -> {
                    System.out.print("Оорукананын ID номерин жазыныз: ");
                    Long hospitalId = scanner.nextLong();
                    List<Doctor> doctors = doctorService.getAllDoctorsByHospitalId(hospitalId);
                    if (doctors.isEmpty()) {
                    } else {
                        doctors.forEach(d -> System.out.println(d.getId() + ": " + d.getFirstName() + " " + d.getLastName()));
                    }
                }
                case 11 -> {
                    System.out.print("Бейтапты кошуу үчүн оорукананын ID номерин жазыныз : ");
                    Long hospitalId = scanner.nextLong();
                    System.out.print("Канча бейтап кошосуз? ");
                    int count = scanner.nextInt();
                    scanner.nextLine();

                    List<Patient> patients = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                       // Long patientId = GenerateId.getPatientId();
                        System.out.print("Бейтаптын аты: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Бейтаптын фамилиюсын жазыныз: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Бейтаптын жашы: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Жынысы (MALE/FEMALE): ");
                        String genderStr = scanner.nextLine();
                        Gender gender = Gender.valueOf(genderStr.toUpperCase());

                        Patient patient = new Patient();
                        patient.setId(GenerateId.getPatientId());
                        patient.setFirstName(firstName);
                        patient.setLastName(lastName);
                        patient.setAge(age);
                        patient.setGender(gender);

                        patients.add(patient);
                    }

                    System.out.println(patientService.addPatientsToHospital(hospitalId, patients));
                }
                case 12 -> {
                    System.out.print("Бейтапты табуу үчүн ID номерин жазыныз : ");
                    Long patientId = scanner.nextLong();
                    Patient patient = patientService.getPatientById(patientId);
                    if (patient != null) {
                        System.out.println("Найден пациент: " + patient.getFirstName() + " " + patient.getLastName() +
                                " Жашы: " + patient.getAge() + ", жынызы: " + patient.getGender());
                    } else {
                        System.out.println("байтап ID номери " + patientId + " табылган жок.");
                    }
                }
                case 13 -> {
                    Map<Integer, Patient> patientsByAge = patientService.getPatientByAge();
                    if (patientsByAge.isEmpty()) {
                        System.out.println("Туура эмес");
                    } else {
                        System.out.println("Бейтаптардын жашына жараша топтому:");
                        for (Map.Entry<Integer, Patient> entry : patientsByAge.entrySet()) {
                            Patient patient = entry.getValue();
                            System.out.println("Жашы: " + entry.getKey() +
                                    " -> Бейтап: " + patient.getFirstName() + " " + patient.getLastName() +
                                    ", ID: " + patient.getId());
                        }
                    }
                }
                case 14 -> {
                    System.out.print("Сорттоо үчүн түрүн жазыныз (asc же desc): ");
                    scanner.nextLine();
                    String sortOrder = scanner.nextLine();
                    List<Patient> sortedPatients = patientService.sortPatientsByAge(sortOrder);

                    if (sortedPatients.isEmpty()) {
                        System.out.println("Топтом бош.");
                    } else {
                        System.out.println("Бейтаптар сорттолду (" + sortOrder + "):");
                        for (Patient patient : sortedPatients) {
                            System.out.println("ID: " + patient.getId() + ", " + patient.getFirstName() + " " + patient.getLastName() +
                                    ", жашы: " + patient.getAge() + ", жынысы: " + patient.getGender());
                        }
                    }
                }
                case 15 -> {
                    System.out.println("Id номерин жазыныз: ");
                    Long idDepartment = scanner.nextLong();
                    departmentGenericService.removeById(idDepartment);
                }
                case 16 ->{
                    System.out.println("Id номерин жазыныз: ");
                    Long idPatient = scanner.nextLong();
                    patientGenericService.removeById(idPatient);
                }
                case 17 -> {
                    System.out.println("Id номерин жазыныз: ");
                    Long idDoctor = scanner.nextLong();
                    doctorGenericService.removeById(idDoctor);
                }

                case 18 -> System.out.println("Аяктоо");

            }
        }





        //TODO проверка пробные


 //       Scanner scanner1 = new Scanner(System.in);
//        Scanner scanner2 = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("Төмөндөгү баскыч сандары аркылуу тийиштүү аракетти тандаңыз! ");
//            System.out.println("1. Add hospital: ");
//            System.out.println("2. Find hospital by Id : ");
//            System.out.println("3. Get all hospital: ");
//            System.out.println("4. Get hospital by Address : ");
//            System.out.println("5. Add department : ");
//            System.out.println("6. Get all department by hospital : ");
//            System.out.println("7. Find department by name : ");
//            System.out.println("8. Update department : ");
//
//
//            System.out.println("10. Get all patient from hospital : ");
//
//
//            System.out.println("11. Add doctor: ");
//            System.out.println("12. Find doctor by Id : ");
//            System.out.println("13. Get all doctors by hospital Id: ");
//            System.out.println("14. Get all doctors by department Id : ");
//            System.out.println("15. Assign doctor to department : ");
//            System.out.println("16. Update department : ");
//            System.out.println("17. Delete department : ");
//            System.out.println("18. Add patient : ");
//            System.out.println("19. Add patients to hospital : ");
//            System.out.println("20. Get patient by Id : ");
//            System.out.println("21. Get patient by age : ");
//            System.out.println("22. Sort patients by age : ");
//            System.out.println("23. Update patients: ");
//
//            System.out.println("24. Delete patients : ");
//            System.out.println("10. Delete department : ");
//            System.out.println("9. Delete hospital by Id : ");
//            System.out.println("25. Exit");
//            System.out.println(" ");
//
//            int choiceNumber = scanner1.nextInt();
//            switch (choiceNumber) {
//                case 1 -> {
//                    System.out.println("Жаны больницанын атын жазыныз: ");
//                    String nameHospital = scanner2.nextLine();
//                    System.out.println("Больницанын адресин жазыныз: ");
//                    String addressHospital = scanner2.nextLine();
//                    List<Department> departments = List.of();
//                    List<Doctor> doctors = List.of();
//                    List<Patient> patients = List.of();
//                    hospitalService.addHospital(new Hospital(GenerateId.getHospitalId(), nameHospital, addressHospital,departments, doctors,patients));
//                }
//                case 2 -> {
//                    System.out.println("Больницаны табуу үчүн Id номерин жазыныз: ");
//                    hospitalService.findHospitalById(scanner2.nextLong());
//                }
//                case 3 -> System.out.println(hospitalService.getAllHospital());
//                case 4 -> {
//                    System.out.println("Больницаны табуу үчүн адресин жазыныз: ");
//                    String addressHospital = scanner2.nextLine();
//                    System.out.println(hospitalService.getAllHospitalByAddress(addressHospital));
//                }
//                case 5 -> {
//                    System.out.println("Больницага жаны бөлүм кошуу үчүн Больницанын Id-син жазыныз: ");
//                    Long idHospital = scanner2.nextLong();
//                    scanner2.nextLine();
//                    System.out.println("Больницага жаны бөлүм кошуу үчүн атын жазыныз: ");
//                    String nameDepartment = scanner2.nextLine();
//                    System.out.println(departmentGenericService.add(idHospital, new Department(GenerateId.getDepartmentId(), nameDepartment)));
//                }
//                case 6 -> {
//                    System.out.println(departmentService.getAllDepartmentByHospital(scanner2.nextLong()));
//                }
//                case 7 -> {
//                    System.out.println("Бөлүмдүн атын жазыныз: ");
//                    String nameDepartment = scanner2.nextLine();
//                    System.out.println(departmentService.findDepartmentByName(nameDepartment));
//                }
//                case 8 -> {
//                    System.out.println("Id :");
//                    long idDepartment = scanner2.nextLong();
//                    System.out.println("Жаны аты: ");
//                    System.out.println(departmentGenericService.updateById(idDepartment, new Department()));
//                }
//            }
//        }




//
//            




//TODO Проверка методов

//        System.out.println("Новая больница");
//        System.out.println(hospitalService.addHospital(new Hospital(GenerateId.getHospitalId(), "National", "Str. Moskva")));
//        System.out.println(hospitalService.addHospital(new Hospital(GenerateId.getHospitalId(), "Rodilnyi dom", "Str. Soviet")));
//        System.out.println("Найти больницу по Id");
//        System.out.println(hospitalService.findHospitalById(1L));
//        System.out.println("Баардык больницаларды корсотуу");
//        System.out.println(hospitalService.getAllHospital());
//        System.out.println("Больницадагы пациенттерди табуу");
//        System.out.println(hospitalService.getAllPatientFromHospital(1L));
//        System.out.println("Больницаны Id номери аркылуу тизмеден очуруу");
//        System.out.println(hospitalService.deleteHospitalById(1L));
//        System.out.println("Больницаларды адреси аркылуу табуу: ");
//        System.out.println(hospitalService.getAllHospitalByAddress("Str. Bishkek"));
//        System.out.println("\nБольницага жаны болум кошуу");
//        System.out.println(genericService.add(1L, new Department(GenerateId.getDepartmentId(), "Хирургия")));
//        System.out.println(genericService.add(2L, new Department(GenerateId.getDepartmentId(), "Родильный")));
//        System.out.println("Больницадагы болумду корсотуу");
//        System.out.println(departmentService.getAllDepartmentByHospital(1L));
//        System.out.println("Бөлүмдүн аты аркылуу табуу:");
//        System.out.println(departmentService.findDepartmentByName("Хирургия"));
//        System.out.println("Бөлүмгө өзгөртүүлөрдү киргизүү");
//        System.out.println(genericService.updateById(1L,new Department(GenerateId.getDepartmentId(),"Родильный")));
//        System.out.println("Больницадагы болумду корсотуу");
//        System.out.println(departmentService.getAllDepartmentByHospital(1L));
//        System.out.println("Баардык больницаларды корсотуу");
//        System.out.println(hospitalService.getAllHospital());
//        System.out.println("Бөлүмдү өчүрүү");
//        genericService.removeById(1L);
//        System.out.println("Баардык больницаларды корсотуу");
//        System.out.println(hospitalService.getAllHospital());
//
//        System.out.println("Врачтардын методу, Жаны врач киргизуу");
//        genericServiceDoctor.add(1L,new Doctor(GenerateId.getDoctorId(),"Anvar","Anvarov", Gender.MALE,5));
//        genericServiceDoctor.add(1L,new Doctor(GenerateId.getDoctorId(),"Guli","Azamatova", Gender.FEMALE,7));
//        genericServiceDoctor.add(2L,new Doctor(GenerateId.getDoctorId(),"Roza","Amanova", Gender.FEMALE,6));
//        genericServiceDoctor.add(2L,new Doctor(GenerateId.getDoctorId(),"Azamat","Berov", Gender.MALE,9));
//
//        System.out.println("врачтардын тизмеси 1-Hospital");
//        System.out.println(doctorService.getAllDoctorsByDepartmentId(1L));
//        System.out.println("врачтардын тизмеси 2-Hospital");
//        doctorService.getAllDoctorsByDepartmentId(2L);
//        System.out.println("Департаменттеги врачтар");
//        doctorService.getAllDoctorsByHospitalId(1L);
//
//        System.out.print("Введите ID отделения: ");
//        Scanner scanner = new Scanner(System.in);
//        Long departmentId = scanner.nextLong();
//
//        System.out.print("Сколько врачей вы хотите назначить? ");
//        int numDoctors = scanner.nextInt();
//        List<Long> doctorIds = new ArrayList<>();
//        for (int i = 0; i < numDoctors; i++) {
//            System.out.print("Введите ID врача: ");
//            doctorIds.add(scanner.nextLong());
//        }
//        String result = doctorService.assignDoctorToDepartment(departmentId, doctorIds);
//        System.out.println(result);





    }
}