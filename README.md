PROJECT TITLE:
<Your project name>

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
- Encapsulation
  Private access modifiers are used to ensure that the internal state of critical entities, like a pet's weight, an appointment's
  time slot or a user's password are hidden from outside interference using private access modifiers. All the public getter and
  setter methods have strict validation logic to ensure the data is kept in an integrity. For instance, when setting an age for a
  pet, the system will refuse to accept one that is negative, and will refuse to book an appointment outside of the clinic hours.

- Inheritance: <where/how>
- Polymorphism: <where/how>
- Abstraction: <where/how>

INITIAL CLASS IDEAS:
- ClassName1: <responsibility>
- ClassName2: <responsibility>
- ClassName3: <responsibility>

USER STORIES (Recommended):
- As a <user type>, I want to <action> so that <goal>.
- As a <user type>, I want to <action> so that <goal>.

CORE FEATURES (Recommended):
- <Feature 1>
- <Feature 2>
- <Feature 3>