package com.streams.practicestreams.model;

public class Employee {
    private String education;
    private String joiningYear;
    private String city;
    private int paymentTier;
    private int age;
    private String gender;
    private String everBenched;
    private int experienceInCurrentDomain;
    private boolean leaveOrNot;
    

    public Employee(String education, String joiningYear,String city, int paymentTier, int age, String gender, String everBenched, int experienceInCurrentDomain, boolean leaveOrNot) {
        this.education = education;
        this.joiningYear = joiningYear;
        this.city = city;
        this.paymentTier = paymentTier;
        this.age = age;
        this.gender = gender;
        this.everBenched = everBenched;
        this.experienceInCurrentDomain = experienceInCurrentDomain;
        this.leaveOrNot = leaveOrNot;
    }

    // Getters and Setters
    public String getEducation() { return education; }
    public String getJoiningYear() { return joiningYear; }
    public String getCity() { return city; }
    public int getPaymentTier() { return paymentTier; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getEverBenched() { return everBenched; }
    public int getExperienceInCurrentDomain() { return experienceInCurrentDomain; }
    public boolean getLeaveOrNot() { return leaveOrNot; }

    @Override
    public String toString() {
        return "Employee{education=" + education + ", joiningYear='" + joiningYear + "', city=" + city + ", paymentTier='" + paymentTier + "', age=" + age + "', gender=" + gender + "', everBenched=" + everBenched + "', experienceInCurrentDomain=" + experienceInCurrentDomain + "', leaveOrNot=" + leaveOrNot + '}';                                  
    }
}
