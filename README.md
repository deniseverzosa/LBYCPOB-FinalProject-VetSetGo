PROJECT TITLE:
VetSetGo: An Object-Oriented Directory and Appointment Management System for Local Veterinary Clinics

TEAM MEMBERS:
Zachary Acosta - zachacosta3
Denise Verzosa - deniseverzosa
Catherine Xu  - XuCatherine1023

PROBLEM STATEMENT & GOALS:
Pet healthcare can be challenging for pet owners because they often have to keep track of everything in a physical booklet from medicine 
to vaccinations, appointments and pet medical records. This can result in missed treatments or forgotten booster shots, or even difficulties 
in finding crucial medical information in an emergency. The issue of overlapping appointments, slow patient check-in and manual record organisation 
is another struggle that independent veterinary clinics face. PetPulse solutions tackle these challenges with one digital solution that simplifies 
the workflow at the clinic and allows pet owners to manage their pet's health records and care easily. 

TARGET USER:
This system was created for two target user types. The first demographic are the independent veterinary clinic workers, like receptionists 
who haves to deal or manage with the flow of appointments without any overlap, and vets need to see or access a patient's medical 
history instantly when they come for a consultation. The second demographic is multi-pet owners 
that would benefit from an integrated, dependable digital platform to manage their pet's health records, 
due appointments, and treatment plans.

BRIEF DESCRIPTION:
The software product named PetPulse is designed as a desktop application characterized by a graphical user interface. 
The application utilizes a two-portal architecture whereby the system analyzes the user’s login credentials to determine 
the type of portal to open in the system. For instance, when a clinic staff logs in, an administrator's portal is opened 
to allow its user to manage the clinic's calendar, enter clinic exam data, and revise and approve or deny user bookings. 
When an ordinary customer logs into the system, a customer portal is opened to enable the user to manage pet profiles, 
submit requests for visits, and view previously recorded medical logs. The PetPulse application also ensures the integrity 
of sensitive data by providing persistent local storage of pet and appointment records.

CORE OOP CONCEPTS:
- Encapsulation:
  Private access modifiers are used to ensure that the internal state of critical entities, like a pet's weight, an appointment's
  time slot or a user's password are hidden from outside interference using private access modifiers. All the public getter and
  setter methods have strict validation logic to ensure the data is kept in an integrity. For instance, when setting an age for a
  pet, the system will refuse to accept one that is negative, and will refuse to book an appointment outside of the clinic hours.

- Inheritance:
  The application uses a hierarchical class structure that starts with an abstract User base class. This class defines common attributes 
  like account IDs, names, and login credentials. Specialized subclasses, Vet and PetOwner, inherit these main traits and add more features. 
  The PetOwner class includes a collection of registered pets, while the Vet class includes specific fields for medical licenses and shift 
  availability schedules.

- Polymorphism:
  The principal characteristic of polymorphism is seen in the application’s interface rendering and the behavior of different roles. 
  The abstract class User, defines the method displayUserPortal(). The behavior of this method will be defined by the type of object 
  created (the platform object will be either a Vet or a PetOwner) at runtime. Moreover, polymorphic method invocation can be used for 
  medical service prices and durations. The base class MedicalService will have different implementations for different medical services, 
  such as Vaccination and Surgery.

- Abstraction:
  In the system, abstraction is used to mask complicated backend processes from the application logic. The DataHandler interface means that 
  the core application just knows that it has to use either the save() or load() method, with all the complicated file input/output and 
  parsing algorithms used for the conversion to local JSON or CSV files kept hidden. In addition, the User class is abstracted to ensure that 
  the system works only with particular Vets and Owners but not with a general, unspecified user.

INITIAL CLASS IDEAS:
- User (abstract class): This is the base class for all of the accounts in the system. It is responsible for the basic operations of
  authentication, holds the user's ID and contact details, and determines the role-specific graphical interfaces to be launched.

- PetOwner: Inherits from User class and is the main data container for the clients. Main purpose is to handle and collect a list of Pet objects,
  so that one human account can be responsible for multiple animals.

- Appointment: Serves as the transactional link between the clinic and the client. It is responsible for associating a specific Vet, PetOwner and
  Pet to an allocated date and time block, and keeping track of its progression state (e.g. Pending, Confirmed, Completed).

- Vet: Inherits from the User class and is used to represent the clinical staff. It is responsible for handling the doctor's particular schedule, 
  keeping track of free hours, and keeping a record of the doctor's upcoming patient appointments.

- Pet: The central domain object in the application. It is accountable for holding fundamental physical properties, e.g., species, breed, age and 
  weight. It also aggregates a history of MedicalRecord objects.

- MedicalRecord: Responsible for recording well-defined, timestamped clinical data during the visit to the veterinarian clinic. It also stores 
  the veterinarian's notes regarding the diagnosis of the patient, dosages of medicines given to the patient and vital signs.

USER STORIES (Recommended):
- As a <user type>, I want to <action> so that <goal>.
- As a <user type>, I want to <action> so that <goal>.

CORE FEATURES (Recommended):
- <Feature 1>
- <Feature 2>
- <Feature 3>