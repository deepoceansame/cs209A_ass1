package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class OnlineCoursesAnalyzer {


    public static class Course {
        private String institution;
        private String courseNumber;
        private Date lauchDate;
        private String courseTitle;
        private List<String> instructors;
        private List<String> courseSubjects;
        private String courseSubjectWhole;
        private int year;
        private int honorCode;
        private long participants;
        private long audited;
        private long certified;
        private double auditedPercent;
        private double certifiedPercent;
        private double certifiedCondi50MoreCoverPercent;
        private double playVideoPercent;
        private double postForumPercent;
        private double grade0MorePercent;
        private double totalCourseHours;
        private double medianHours;
        private double medianAge;
        private double malePercent;
        private double femalePercent;
        private double bachelorOrHigherPercent;

        private boolean inputValid;


        public Course(String line) {
            try{
                //MITx,
                // 6.002x,
                // 09/05/2012,
                // Circuits and Electronics,
                // Khurram Afridi,
                // "Science, Technology, Engineering, and Mathematics",
                // 1,
                // 1,
                // 36105,
                // 5431,
                // 3003,15.04,8.32,54.98,83.2,8.17,28.97,418.94,64.45,26,88.28,11.72,60.68
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                institution = tokens[0];
                courseNumber = tokens[1];
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                lauchDate = simpleDateFormat.parse(tokens[2]);
                if (tokens[3].charAt(0) == '\"') {
                    courseTitle = tokens[3].substring(1, tokens[3].length() - 1);
                } else {
                    courseTitle = tokens[3];
                }

                if (tokens[4].charAt(0) == '\"') {
                    instructors = Arrays.stream(tokens[4].substring(1, tokens[4].length() - 1)
                            .split(",")).map(s -> s.strip()).toList();
                } else {
                    instructors = List.of(tokens[4]);
                }
                if (tokens[5].charAt(0) == '\"') {
                    courseSubjects = Arrays.stream(tokens[5].substring(1, tokens[5].length()-1).split(",\\s*and\\s*|\\s*,\\s*" , -1))
                            .map(s -> s.strip()).toList();
                    courseSubjectWhole = tokens[5].substring(1, tokens[5].length() - 1);
                } else {
                    courseSubjects = List.of(tokens[5]);
                    courseSubjectWhole = tokens[5];
                }
                year = Integer.parseInt(tokens[6]);
                honorCode = Integer.parseInt(tokens[7]);
                participants = Long.parseLong(tokens[8]);
                audited = Long.parseLong(tokens[9]);
                certified = Long.parseLong(tokens[10]);
                auditedPercent = Double.parseDouble(tokens[11]);
                certifiedPercent = Double.parseDouble(tokens[12]);
                certifiedCondi50MoreCoverPercent = Double.parseDouble(tokens[13]);
                playVideoPercent = Double.parseDouble(tokens[14]);
                postForumPercent = Double.parseDouble(tokens[15]);
                grade0MorePercent = Double.parseDouble(tokens[16]);
                totalCourseHours = Double.parseDouble(tokens[17]);
                medianHours = Double.parseDouble(tokens[18]);
                medianAge = Double.parseDouble(tokens[19]);
                malePercent = Double.parseDouble(tokens[20]);
                femalePercent = Double.parseDouble(tokens[21]);
                bachelorOrHigherPercent = Double.parseDouble(tokens[22]);
                inputValid = true;
            }
            catch (Exception e){
                e.printStackTrace();
                inputValid = false;
            }

        }

        public Course() {

        }

        public String getInstitution() {
            return institution;
        }

        public void setInstitution(String institution) {
            this.institution = institution;
        }

        public String getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
        }

        public Date getLauchDate() {
            return lauchDate;
        }

        public void setLauchDate(Date lauchDate) {
            this.lauchDate = lauchDate;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public List<String> getInstructors() {
            return instructors;
        }

        public void setInstructors(List<String> instructors) {
            this.instructors = instructors;
        }

        public List<String> getCourseSubjects() {
            return courseSubjects;
        }

        public void setCourseSubjects(List<String> courseSubjects) {
            this.courseSubjects = courseSubjects;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getHonorCode() {
            return honorCode;
        }

        public void setHonorCode(int honorCode) {
            this.honorCode = honorCode;
        }

        public long getParticipants() {
            return participants;
        }

        public void setParticipants(long participants) {
            this.participants = participants;
        }

        public long getAudited() {
            return audited;
        }

        public void setAudited(long audited) {
            this.audited = audited;
        }

        public long getCertified() {
            return certified;
        }

        public void setCertified(long certified) {
            this.certified = certified;
        }

        public double getAuditedPercent() {
            return auditedPercent;
        }

        public void setAuditedPercent(double auditedPercent) {
            this.auditedPercent = auditedPercent;
        }

        public double getCertifiedPercent() {
            return certifiedPercent;
        }

        public void setCertifiedPercent(double certifiedPercent) {
            this.certifiedPercent = certifiedPercent;
        }

        public double getCertifiedCondi50MoreCoverPercent() {
            return certifiedCondi50MoreCoverPercent;
        }

        public void setCertifiedCondi50MoreCoverPercent(double certifiedCondi50MoreCoverPercent) {
            this.certifiedCondi50MoreCoverPercent = certifiedCondi50MoreCoverPercent;
        }

        public double getPlayVideoPercent() {
            return playVideoPercent;
        }

        public void setPlayVideoPercent(double playVideoPercent) {
            this.playVideoPercent = playVideoPercent;
        }

        public double getPostForumPercent() {
            return postForumPercent;
        }

        public void setPostForumPercent(double postForumPercent) {
            this.postForumPercent = postForumPercent;
        }

        public double getGrade0MorePercent() {
            return grade0MorePercent;
        }

        public void setGrade0MorePercent(double grade0MorePercent) {
            this.grade0MorePercent = grade0MorePercent;
        }

        public double getTotalCourseHours() {
            return totalCourseHours;
        }

        public void setTotalCourseHours(double totalCourseHours) {
            this.totalCourseHours = totalCourseHours;
        }

        public double getMedianHours() {
            return medianHours;
        }

        public void setMedianHours(double medianHours) {
            this.medianHours = medianHours;
        }

        public double getMedianAge() {
            return medianAge;
        }

        public void setMedianAge(double medianAge) {
            this.medianAge = medianAge;
        }

        public double getMalePercent() {
            return malePercent;
        }

        public void setMalePercent(double malePercent) {
            this.malePercent = malePercent;
        }

        public double getFemalePercent() {
            return femalePercent;
        }

        public void setFemalePercent(double femalePercent) {
            this.femalePercent = femalePercent;
        }

        public double getBachelorOrHigherPercent() {
            return bachelorOrHigherPercent;
        }

        public void setBachelorOrHigherPercent(double bachelorOrHigherPercent) {
            this.bachelorOrHigherPercent = bachelorOrHigherPercent;
        }

        public boolean isInputValid() {
            return inputValid;
        }

        public void setInputValid(boolean inputValid) {
            this.inputValid = inputValid;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "institution='" + institution + '\'' +
                    ", courseNumber='" + courseNumber + '\'' +
                    ", lauchDate=" + lauchDate +
                    ", courseTitle='" + courseTitle + '\'' +
                    ", instructors=" + instructors +
                    ", courseSubjects=" + courseSubjects +
                    ", year=" + year +
                    ", honorCode=" + honorCode +
                    ", participants=" + participants +
                    ", audited=" + audited +
                    ", certified=" + certified +
                    ", auditedPercent=" + auditedPercent +
                    ", certifiedPercent=" + certifiedPercent +
                    ", certifiedCondi50MoreCoverPercent=" + certifiedCondi50MoreCoverPercent +
                    ", playVideoPercent=" + playVideoPercent +
                    ", postForumPercent=" + postForumPercent +
                    ", grade0MorePercent=" + grade0MorePercent +
                    ", totalCourseHours=" + totalCourseHours +
                    ", medianHours=" + medianHours +
                    ", medianAge=" + medianAge +
                    ", malePercent=" + malePercent +
                    ", femalePercent=" + femalePercent +
                    ", bachelorOrHigherPercent=" + bachelorOrHigherPercent +
                    ", inputValid=" + inputValid +
                    '}';
        }
    }

//    public static Stream<Course> readCities(String filename) throws IOException {
//        return Files.lines(Paths.get(filename))
//                .map(l -> l.split(","))
//                .map(a -> new Course());
//    }

    private String datasetPath;
    public List<Course> courses;

    public Set<String> institutions;
    public Set<String> instructors;

    public Set<String> institution_courseSubject;

    public OnlineCoursesAnalyzer(String datasetPath) throws IOException {
        datasetPath = datasetPath;
        courses = Files.lines(Paths.get(datasetPath), StandardCharsets.UTF_8)
                .skip(1)
                .map(Course::new)
                .filter(course -> course.isInputValid()).toList();
        instructors = new TreeSet<>();
        institutions = new TreeSet<>();
        institution_courseSubject = new TreeSet<>();
        for(Course course : courses){
            institutions.add(course.institution);
            for (String courseSubject : course.courseSubjects)
                institution_courseSubject.add(course.institution+"-"+courseSubject);
            instructors.addAll(course.instructors);
        }
    }

    public static void playRegex() {

        String line2 = "Addd, dsdsdsd    rrrrr,and ffff";
        String[] tokens2 = line2.split("(\\s+)?[,\\s+](\\s)?(and\\s)?", -1);
        for (String s : tokens2) {
            System.out.println(s.trim());
        }

        String line3 = "Addd, dsdsdsd  ,  rrrrr,and ffff";
        String[] tokens3 = line3.split(",\\s*and\\s*|\\s*,\\s*", -1);
        for (String s : tokens3) {
            System.out.println(s.trim());
        }


        String str6 = ",and ";
        String pat2 = ",\\s*and\\s*";
        System.out.println(Pattern.matches(pat2, str6));
    }

    public static void playRegex2() {
        String[] tokens = new String[5];
        tokens[4] = "\"Eric Grimson, John Guttag, Chris Terman\"";
        List<String> inss = Arrays.stream(tokens[4].substring(1, tokens[4].length() - 1)
                .split(",")).map(s -> s.strip()).toList();
        System.out.println(inss);
    }

    public Map<String, Integer> getPtcpCountByInst() {
        Map<String, Integer> result = courses.stream().collect(
                Collectors.groupingBy(Course::getInstitution,
                        Collectors.summingInt(course -> (int) course.getParticipants())
                )
        );

        result = result.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new)
                );

        return result;
    }

    public Map<String, Integer> getPtcpCountByInstAndSubject(){
        Map<String, Integer> result = courses.stream().collect(
                Collectors.groupingBy(course -> course.institution + "-" + course.courseSubjectWhole,
                            Collectors.summingInt(course -> (int) course.getParticipants())
                )
        );
        result = result.entrySet().stream()
                .sorted(
                    (e1, e2) -> {
                        if (e1.getValue().equals(e2.getValue())) {
                            return e1.getKey().compareTo(e2.getKey());
                        } else {
                            return e2.getValue() - e1.getValue();
                        }
                    }
                )
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new)
                );
        return result;
    }

    public Map<String, List<List<String>>> getCourseListOfInstructor() {
        Map<String, List<List<String>>> result = new TreeMap<>();
        Map<String, List<List<String>>> finalResult = result;
        courses.forEach(
                course -> {
                    for (String instructor : course.instructors) {
                        if (!finalResult.containsKey(instructor)) {
                            List<String> independent = new ArrayList<>();
                            List<String> cooperate = new ArrayList<>();
                            List<List<String>>twoLists = List.of(independent, cooperate);
                            finalResult.put(instructor, twoLists);
                        }
                        if (course.instructors.size() == 1) {
                            List<String> independent = finalResult.get(instructor).get(0);
                            if (!independent.contains(course.courseTitle)) {
                                independent.add(course.courseTitle);
                            }
                        }
                        else {
                            List<String> cooperate = finalResult.get(instructor).get(1);
                            if (!cooperate.contains(course.courseTitle)) {
                                cooperate.add(course.courseTitle);
                            }
                        }
                    }
                }
        );
        result = result.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .peek(entry -> {
                        List<List<String>> twoLists = entry.getValue();
                        List<String> dependent = twoLists.get(0);
                        List<String> cooperate = twoLists.get(1);
                        Collections.sort(dependent);
                        Collections.sort(cooperate);
                        }
                )
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new)
                );
        return result;
    }


    public List<String> getCourses2(int topK, String by){
        List<String> result = null;
        if (by.equals("hours")) {
            result = courses.stream()
                    .collect(Collectors.groupingBy(
                                    course -> course.courseTitle,
                                    Collectors.summingDouble(Course::getTotalCourseHours)
                            )
                    )
                    .entrySet()
                    .stream()
                    .sorted(
                            (e1, e2) -> {
                                if (Objects.equals(e2.getValue(), e1.getValue())){
                                    return e1.getKey().compareTo(e2.getKey());
                                } else {
                                    return (e2.getValue() - e1.getValue())>0?1:-1;
                                }
                            }
                    )
                    .limit(topK)
                    .map(Map.Entry::getKey)
                    .toList();
        } else {
            result = courses.stream()
                    .collect(Collectors.groupingBy(
                                course -> course.courseTitle,
                                Collectors.summingLong(Course::getParticipants)
                            )
                    )
                    .entrySet()
                    .stream()
                    .sorted(
                            (e1, e2) -> {
                                if (Objects.equals(e2.getValue(), e1.getValue())){
                                    return e1.getKey().compareTo(e2.getKey());
                                } else {
                                    return (e2.getValue() - e1.getValue())>0?1:-1;
                                }
                            }
                    )
                    .limit(topK)
                    .map(Map.Entry::getKey)
                    .toList();
        }


        return result;
    }

    public List<String> getCourses(int topK, String by){
        List<String> result = null;
        result = courses.stream()
                .sorted(
                        (c1, c2) -> {
                            if (by.equals("hours")){
                                if(c1.totalCourseHours==c2.totalCourseHours){
                                    return c1.courseTitle.compareTo(c2.courseTitle);
                                } else{
                                    return (c2.totalCourseHours-c1.totalCourseHours)>0?1:-1;
                                }
                            } else{
                                if(c1.participants==c2.participants){
                                    return c1.courseTitle.compareTo(c2.courseTitle);
                                } else{
                                    return (c2.participants-c1.participants)>0?1:-1;
                                }
                            }
                        }
                )
                .map(Course::getCourseTitle)
                .toList();
        List<String> finalRes = new ArrayList<>();
        for(String s:result){
            if (!finalRes.contains(s)){
                finalRes.add(s);
            }
            if (finalRes.size()==topK){
                break;
            }
        }
        return finalRes;
    }

    public List<String> searchCourses(String courseSubject, double percentAudited, double totalCourseHours){
        List<String> result = courses.stream().filter(course -> {
                return Pattern.compile(Pattern.quote(courseSubject), Pattern.CASE_INSENSITIVE)
                        .matcher(course.courseSubjectWhole).find()
                        && course.auditedPercent>=percentAudited
                        && course.totalCourseHours<=totalCourseHours;
                    }
                )
                .map(course -> course.courseTitle)
                .toList();
        List<String> finalRes = new ArrayList<>();
        for(String s:result){
            if (!finalRes.contains(s)){
                finalRes.add(s);
            }
        }
        Collections.sort(finalRes);
        return finalRes;
    }


    public List<String> recommendCourses(int age, int gender, int isBachelorOrHigher){
        Map<String, List<Course>> gpByCourNum =
                courses.stream().collect(Collectors.groupingBy(Course::getCourseNumber));
        gpByCourNum = gpByCourNum.entrySet().stream()
                .peek(
                    entry -> {
                        String courseTitleForTheCourseNum = "";
                        Date latestLaunchDate = null;
                        for (Course course:entry.getValue()){
                            if (latestLaunchDate==null){
                                latestLaunchDate = course.lauchDate;
                                courseTitleForTheCourseNum = course.courseTitle;
                            } else {
                                if(latestLaunchDate.compareTo(course.lauchDate)<0){
                                    latestLaunchDate = course.lauchDate;
                                    courseTitleForTheCourseNum = course.courseTitle;
                                }
                            }
                        }

                        for (Course course:entry.getValue()){
                            course.courseTitle = courseTitleForTheCourseNum;
                        }
                    }
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        List<String> courseTitles = gpByCourNum.entrySet().stream()
                .map(entry->{
                    String courseTitle = entry.getValue().get(0).courseTitle;
                    Double averageMedianAge = 0d;
                    Double averageMalePercent = 0d;
                    Double averageIsBachelorPercent = 0d;
                    for (Course course: entry.getValue()){
                        averageMedianAge += course.medianAge;
                        averageMalePercent += course.malePercent;
                        averageIsBachelorPercent += course.bachelorOrHigherPercent;
                    }
                    averageMalePercent = averageMalePercent/entry.getValue().size();
                    averageMedianAge = averageMedianAge/entry.getValue().size();
                    averageIsBachelorPercent = averageIsBachelorPercent/entry.getValue().size();
                    double similarity = Math.pow((age - averageMedianAge), 2) +
                                        Math.pow((gender*100 - averageMalePercent), 2) +
                                        Math.pow((isBachelorOrHigher*100 - averageIsBachelorPercent), 2);
                    return new AbstractMap.SimpleEntry<String, Double>(courseTitle, similarity);
                })
                .sorted((e1, e2)->{
                    if (Objects.equals(e1.getValue(), e2.getValue())){
                        return  e1.getKey().compareTo(e2.getKey());
                    } else{
                        return e1.getValue() - e2.getValue()>0?1:-1;
                    }
                })
                .map(AbstractMap.SimpleEntry::getKey)
                .toList();
        List<String> result = new ArrayList<>();
        for (String s:courseTitles){
            if (!result.contains(s)){
                result.add(s);
            }
            if (result.size()==10){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        OnlineCoursesAnalyzer analyzer = new OnlineCoursesAnalyzer("./resources/local.csv");
//        for (Course course:analyzer.courses){
//            System.out.println(course);
//        }
        System.out.println(analyzer.institution_courseSubject);
        System.out.println(analyzer.institutions);
        System.out.println(analyzer.instructors);
    }
}

// Year,
// Honor Code Certificates,
// Participants (CourseBF Content Accessed),
// Audited (> 50% CourseBF Content Accessed),
// Certified,
// % Audited,
// % Certified,
// % Certified of > 50% CourseBF Content Accessed,
// % Played Video,
// % Posted in Forum,
// % Grade Higher Than Zero,
// Total CourseBF Hours (Thousands),
// Median Hours for Certification,
// Median Age,
// % Male,
// % Female,
// % Bachelor's Degree or Higher

// 1, year
// 1, honor code
// 62709, participants
// 8949, audited
// 5783, certified vote
// 14.27, audited
// 9.22, certified
// 64.05,  certified > 50%
// 89.14, playedVideo
// 14.38, postForum
// 39.5,  gradeHigherThanZero
// 884.04, hour
// 78.53, median hour
// 28, age
// 83.5, male
// 16.5, female
// 63.04 bachelor

//MITx,
// 6.002x,
// 09/05/2012,
// Circuits and Electronics,
// Khurram Afridi,
// "Science, Technology, Engineering, and Mathematics",
// 1,
// 1,
// 36105,
// 5431,
// 3003,15.04,8.32,54.98,83.2,8.17,28.97,418.94,64.45,26,88.28,11.72,60.68


//MITx,
// 6.00x,
// 09/26/2012,
// Introduction to Computer Science and Programming,
// "Eric Grimson, John Guttag, Chris Terman",
// Computer Science,
// 1,1,62709,8949,5783,14.27,9.22,64.05,89.14,14.38,39.5,884.04,78.53,28,83.5,16.5,63.04
//MITx,3.091x,10/09/2012,Introduction to Solid State Chemistry,Michael Cima,"Science, Technology, Engineering, and Mathematics",1,1,16663,2855,2082,17.13,12.49,72.85,87.49,14.42,34.89,227.55,61.28,27,70.32,29.68,58.76
