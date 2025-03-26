//package cz.cvut.fel.attendance.service;
//
//import cz.cvut.fel.attendance.service.model.Admin;
//import cz.cvut.fel.attendance.service.model.Child;
//import cz.cvut.fel.attendance.service.model.Event;
//import cz.cvut.fel.attendance.service.model.Parent;
//import cz.cvut.fel.attendance.service.model.School;
//import cz.cvut.fel.attendance.service.model.Trainer;
//import cz.cvut.fel.attendance.service.model.Training;
//import cz.cvut.fel.attendance.service.model.TrainingUnit;
//import cz.cvut.fel.attendance.service.repository.ChildRepository;
//import cz.cvut.fel.attendance.service.repository.EventRepository;
//import cz.cvut.fel.attendance.service.repository.SchoolRepository;
//import cz.cvut.fel.attendance.service.repository.TrainingRepository;
//import cz.cvut.fel.attendance.service.repository.TrainingUnitRepository;
//import cz.cvut.fel.attendance.service.repository.UserRepository;
//import cz.cvut.fel.attendance.service.service.TrainingUnitService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DBinit {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private TrainingRepository trainingRepository;
//
//    @Autowired
//    private SchoolRepository schoolRepository;
//
//    @Autowired
//    private ChildRepository childRepository;
//
//    @Autowired
//    private TrainingUnitService trainingUnitService;
//
//    @Autowired
//    private TrainingUnitRepository trainingUnitRepository;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//
//    @Bean
//    @Transactional
//    public CommandLineRunner initDatabase() {
//        return args -> {
//            System.out.println("\n================== DATABASE INITIALIZATION STARTED ==================\n");
//
//            List<Event> events = List.of(
//                    new Event(
//                            "Jarní judo kemp",
//                            LocalDate.of(2025, 4, 5),
//                            LocalDate.of(2025, 4, 10),
//                            "Jesenická 31, Jesenice pod Kempem",
//                            null,
//                            null,
//                            20,
//                            1500,
//                            "Jarní judo kemp nabízí dětem a mládeži možnost zažít týden plný zábavy, pohybu a rozvoje judistických dovedností. Kemp je určen pro začátečníky i pokročilé a kombinuje technický a fyzický trénink s herními aktivitami a týmovými výzvami. Každý den začíná ranním rozcvičením, následovaným tematickými bloky zaměřenými na pády, chvaty a bojové situace. Děti si také užijí přednášky o fair-play, zdravém životním stylu a historii juda. Večery patří společenským hrám, táborákům a soutěžím o medaile. Celý program je veden zkušenými trenéry a pedagogy, kteří dbají na bezpečnost, přátelskou atmosféru a individuální rozvoj každého účastníka."
//                    ),
//                    new Event(
//                            "Letní judo camp Šumava",
//                            LocalDate.of(2025, 7, 15),
//                            LocalDate.of(2025, 7, 22),
//                            "Zbrojova 43, Šumava",
//                            null,
//                            null,
//                            25,
//                            2500,
//                            "Letní judo camp v krásném prostředí Šumavy spojuje intenzivní sportovní přípravu s letním dobrodružstvím. Účastníky čekají tréninky zaměřené na techniku, fyzickou kondici, reakční rychlost i bojové strategie. Program je doplněn o turistiku, koupání, hry v přírodě a večerní posezení u ohně. Camp vytváří ideální podmínky pro rozvoj týmového ducha, sebedůvěry a sportovní vytrvalosti. Děti budou rozděleny do skupin podle výkonnosti, aby si každý mohl trénink maximálně užít. Kromě sportovních aktivit bude prostor i pro relaxaci, workshopy o správné výživě a sdílení zkušeností mezi účastníky a trenéry. Ubytování a strava jsou zajištěny v areálu sportovního centra."
//                    ),
//                    new Event(
//                            "Ukázkový trénink pro nováčky",
//                            LocalDate.of(2025, 3, 10),
//                            null,
//                            "Judo SG Plzeň",
//                            LocalTime.of(17, 0),
//                            LocalTime.of(18, 30),
//                            15,
//                            0,
//                            "Ukázkový trénink je skvělou příležitostí pro všechny, kteří si chtějí judo poprvé vyzkoušet bez závazků. Během 90 minut se účastníci pod vedením zkušených trenérů naučí základy pohybu na tatami, bezpečné padání a jednoduché techniky. Důraz je kladen na hravou formu tréninku, zábavu a vytvoření pozitivního vztahu ke sportu. Rodiče mají možnost trénink sledovat z galerie a získat informace o fungování klubu, možnostech zápisu a organizaci tréninků. Trénink je vhodný pro děti od 6 do 12 let, bez ohledu na předchozí sportovní zkušenosti. Všichni účastníci obdrží drobný dárek a informace o dalších krocích, pokud se rozhodnou v tréninku pokračovat."
//                    ),
//                    new Event(
//                            "Zimní přípravný turnaj",
//                            LocalDate.of(2025, 12, 1),
//                            null,
//                            "Sportovní hala Blovice",
//                            LocalTime.of(9, 0),
//                            LocalTime.of(13, 0),
//                            30,
//                            500,
//                            "Zimní přípravný turnaj je přátelské klání pro mladší žáky, které jim umožňuje získat první zkušenosti se soutěžní atmosférou v bezpečném a povzbuzujícím prostředí. Turnaj je rozdělen podle váhových kategorií a úrovní zkušeností, aby byla zajištěna férovost a rovnost šancí. Každý účastník odehraje minimálně tři zápasy, bez ohledu na výsledek, a obdrží medaili, diplom a drobný dárek. Kromě sportovních výkonů se turnaj zaměřuje také na etiku juda, respekt k soupeři a spolupráci s rozhodčími. Turnaj probíhá v městské hale s občerstvením a zázemím pro rodiče. Doporučujeme včasnou registraci z důvodu omezené kapacity."
//                    ),
//                    new Event(
//                            "Podzimní víkendový kurz",
//                            LocalDate.of(2025, 10, 18),
//                            LocalDate.of(2025, 10, 20),
//                            "Hala Vodova Ústí nad Labem",
//                            null,
//                            null,
//                            18,
//                            1200,
//                            "Podzimní víkendový kurz je ideální příležitostí pro judisty všech úrovní, kteří si chtějí zdokonalit své dovednosti, zažít intenzivní tréninky a zároveň si užít přátelskou atmosféru mimo běžné tréninkové prostředí. Během tří dnů absolvují účastníci sérii technických, kondičních a taktických bloků vedených zkušenými trenéry s důrazem na individuální přístup. Součástí kurzu jsou také interaktivní workshopy o správném stravování sportovců, mentální přípravě před soutěžemi a prevenci zranění. Večerní program zahrnuje společenské aktivity, hry a diskuze o historii a filozofii juda. Kurz se koná v klidném přírodním prostředí."
//                    )
//            );
//
//            School school = new School(
//                    "ZŠ Štěnovice", "Školní 28", "Štěnovice",
//                    "Pavel Novák", "123456789", "Enter via the side door"
//            );
//            schoolRepository.save(school);
//
//            List<Training> trainings = List.of(
//                    new Training("Začátečnící", DayOfWeek.MONDAY, LocalTime.of(17, 30),
//                            LocalTime.of(19, 0), "2024/2025", 2300, 30),
//                    new Training("Pokročilí", DayOfWeek.TUESDAY, LocalTime.of(16, 00),
//                            LocalTime.of(17, 30), "2024/2025", 2500, 25)
//            );
//
//            trainings.forEach(training -> training.setSchool(school));
//            trainingRepository.saveAll(trainings);
//
//            List<Child> children = List.of(
//                    new Child("Teddy", "Riner", LocalDate.of(2015, 3, 10), "123 Maple St", "Paris", 75000, "123456/7890", trainings.get(0).getId()),
//                    new Child("Shohei", "Ono", LocalDate.of(2014, 7, 22), "456 Oak Ave", "Tokyo", 100000, "234567/8901", trainings.get(0).getId()),
//                    new Child("Clarisse", "Agbegnenou", LocalDate.of(2016, 1, 15), "789 Pine Rd", "Paris", 75001, "345678/9012", trainings.get(0).getId()),
//                    new Child("Lukas", "Krpalek", LocalDate.of(2013, 5, 19), "321 Birch Ln", "Prague", 11000, "456789/0123", trainings.get(1).getId()),
//                    new Child("Daria", "Bilodid", LocalDate.of(2012, 3, 29), "542 Boulevard", "Kyiv", 01001, "321312/2131", trainings.get(1).getId()),
//                    new Child("Rafaela", "Silva", LocalDate.of(2017, 8, 25), "555 Cedar Blvd", "Rio de Janeiro", 20000, "567890/1234", trainings.get(1).getId())
//            );
//
//            events.get(0).addChild(children.get(0));
//            events.get(0).addChild(children.get(1));
//            events.get(0).addChild(children.get(2));
//
//            events.get(2).addChild(children.get(3));
//            events.get(2).addChild(children.get(4));
//            events.get(2).addChild(children.get(5));
//
//            eventRepository.saveAll(events);
//
//            Parent parent = new Parent("Jigoro", "Kano", "jigorokano@email.com", "602123543", passwordEncoder.encode("jigorokano@email.com"));
//
//            children.forEach(child -> {
//                Training training = trainings.stream().filter(t -> t.getId().equals(child.getRequestedTrainingId())).findFirst().orElse(null);
//                if (training != null) {
//                    child.setTraining(training);
//                    training.getChildren().add(child);
//                }
//                parent.addChild(child);
//            });
//
//            userRepository.save(parent);
//            childRepository.saveAll(children);
//            trainingRepository.saveAll(trainings);
//
//            Trainer trainer = new Trainer("Kosei", "Inoue", "kosei@inoue.com", "+420 789 456 123", passwordEncoder.encode("kosei@inoue.com"));
//            userRepository.save(trainer);
//            trainings.get(0).addTrainer(trainer);
//            trainings.get(1).addTrainer(trainer);
//            trainer.addTraining(trainings.get(0));
//            trainer.addTraining(trainings.get(1));
//
//            trainingRepository.saveAll(trainings);
//
//            userRepository.save(new Admin("Karel", "Novak", "admin@gmail.com", "999", passwordEncoder.encode("123456")));
//
//            // Training One
//            Training firstTraining = trainings.get(0);
//            TrainingUnit upcomingTrainingUnit = new TrainingUnit();
//            upcomingTrainingUnit.setDate(LocalDate.of(2026,4,10));
//            upcomingTrainingUnit.setCurrent(true);
//            upcomingTrainingUnit.setTraining(firstTraining);
//            firstTraining.addTrainingUnit(upcomingTrainingUnit);
//            trainingUnitRepository.save(upcomingTrainingUnit);
//
//            for (int i=0; i<5; i++) {
//                TrainingUnit trainingUnit = new TrainingUnit();
//                trainingUnit.setDate(LocalDate.now().minusDays(i + 1));
//                trainingUnit.setCurrent(false);
//                trainingUnit.setTraining(firstTraining);
//                trainingUnit.setDescription("Nic");
//                firstTraining.addTrainingUnit(trainingUnit);
//                trainingUnitRepository.save(trainingUnit);
//            }
//
//            // Training Two
//            Training secondTraining = trainings.get(1);
//            TrainingUnit upcomingTrainingUnitTwo = new TrainingUnit();
//            upcomingTrainingUnitTwo.setDate(LocalDate.of(2026,4,11));
//            upcomingTrainingUnitTwo.setCurrent(true);
//            upcomingTrainingUnitTwo.setTraining(secondTraining);
//            secondTraining.addTrainingUnit(upcomingTrainingUnitTwo);
//            trainingUnitRepository.save(upcomingTrainingUnitTwo);
//
//            for (int i=0; i<5; i++) {
//                TrainingUnit trainingUnit = new TrainingUnit();
//                trainingUnit.setDate(LocalDate.now().minusDays(i));
//                trainingUnit.setCurrent(false);
//                trainingUnit.setTraining(secondTraining);
//                trainingUnit.setDescription("Lezeli");
//                secondTraining.addTrainingUnit(trainingUnit);
//                trainingUnitRepository.save(trainingUnit);
//            }
//
//            trainingUnitService.createWeeklyTrainingUnits();
//
//            System.out.println("\n================== DATABASE INITIALIZATION COMPLETED ==================\n");
//
//        };
//    }
//}
//
