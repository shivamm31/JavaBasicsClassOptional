package com.knoldus.kup;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ClassRoom test cases.
 */
public class ClassRoomTest extends TestCase {
    
    /**
     * List for testing.
     */
    List<Student> studentList1, studentList2;
    ClassRoom classRoom1, classRoom2;

    @Override
    @Before
    public void setUp() throws Exception {
        Student student1 = new Student("Shivam", 1, Optional.of("Java"));
        Student student2 = new Student("Rishivant", 2, Optional.empty());
        Student student3 = new Student("Parth", 3, Optional.empty());
        Student student4 = new Student("Vineet", 4, Optional.of("Python"));

        Student student5 = new Student("Rishi", 5, Optional.of("JavaScript"));
        Student student6 = new Student("Atul", 6, Optional.of("Java"));
        Student student7 = new Student("Romil", 7, Optional.of("Python"));
        Student student10 = new Student("Prakhar", 10, Optional.of("Python"));
        Student student11 = new Student("Vivek", 11, Optional.of("Java"));

        studentList1 = Arrays.asList(student1, student2, student3, student4);
        studentList2 = Arrays.asList(student5, student6, student7, student10,student11);

        classRoom1 = new ClassRoom("abc", Optional.of(studentList1));
        classRoom2 = new ClassRoom("xyz", Optional.of(studentList2));

    }

    @Test
    public void testGetStudentsWithoutSubject_CorrectIfReturnList() {
        List<Student> expectedStudents = Arrays.asList(new Student("Rishivant", 2, Optional.empty()),
                new Student("Parth", 3, Optional.empty()));
        List<Student> actualStudents = classRoom1.getStudentsWithoutSubject();
        assertTrue(actualStudents.size() > 1);
        assertEquals(expectedStudents.get(0).getRollNumber(),actualStudents.get(0).getRollNumber());
    }

    @Test
    public void testGetDistinctSubjectsByRoomId_CorrectIfSubjectsFoundByRoomId() {
        List<Optional<String>> expectedSubjects = Arrays.asList(Optional.of("JavaScript"), Optional.of("Java"), Optional.of("Python"));
        List<Optional<String>> actualSubjects = classRoom2.getDistinctSubjectsByRoomId("xyz");
        assertTrue(actualSubjects.size() > 1);
        assertEquals(expectedSubjects,actualSubjects);
    }

    @Test
    public void testGetDistinctSubjectsByRoomId_ReturnNullIfSubjectsNotFoundByRoomId() {
        List<Optional<String>> actualSubjects = classRoom2.getDistinctSubjectsByRoomId("ba01");
        assertEquals(null,actualSubjects);
    }

    @Test
    public void testHasStudents_ReturnStringMessageIfHaveStudents() {
        String expectedResult = "Hello";
        String actualResutl = classRoom1.hasStudents();
        assertTrue(expectedResult.equals(actualResutl));
    }
    @Test
    public void testHasStudents_ReturnEmptyStringIfNotHaveStudents() {
        String expectedResult = "";
        classRoom1.setStudentList(Optional.empty());
        String actualResult = classRoom1.hasStudents();
        assertTrue(expectedResult.equals(actualResult));
    }

    @Test
    public void testGetRoomId_CorrectIfReturnRoomId(){
        String expectedRoomId = "abc";
        String actualRoomId = classRoom1.getRoomId();
        assertEquals(expectedRoomId,actualRoomId);
    }

    @Test
    public void testSetRoomId_CorrectIfSetRoomId(){
        classRoom1.setRoomId("B.tech");
        String expectedRoomId = "B.tech";
        String actualRoomId = classRoom1.getRoomId();
        assertEquals(expectedRoomId,actualRoomId);
    }

    @Test
    public void testgetStudentList_CorrectIfReturnStudentsList(){
        Optional<List<Student>> actualList = classRoom1.getStudentList();
        assertTrue(actualList.get().size()>2);
    }

    @Test
    public void testsetStudentList_CorrectIfSetStudentsList(){
        classRoom1.setStudentList(Optional.ofNullable(studentList2));
        Optional<List<Student>> expectedList = Optional.ofNullable(studentList2);
        Optional<List<Student>> actualList = classRoom1.getStudentList();
        assertTrue(actualList.get().size()>2);
        assertEquals( expectedList.get().get(0).getRollNumber(), actualList.get().get(0).getRollNumber());
    }

    @Test
    public void testToString() {
        String expected = "ClassRoom{" +
                "roomId='" + classRoom1.getRoomId() + '\'' +
                ", studentList=" + classRoom1.getStudentList() +
                '}';
        String actual = classRoom1.toString();
        assertEquals(expected,actual);
    }
}