import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection conn = DatabaseConnection.getConnection();
            //this way to instantiate the statement make possible to re-read, from the beginin the resultset.
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
               ResultSet.CONCUR_READ_ONLY 
         );
            
            // Query to retrieve 100 records
            String query = "SELECT id, name, age FROM Students LIMIT 100";
            ResultSet rs = stmt.executeQuery(query);

             // Populate Array
             Student[] array = new Student[100];
             int count=0;
             long startArrayInsert = System.nanoTime();
             while (rs.next()) {
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 int age = rs.getInt("age");
                 array[count]=new Student(id, name, age);
                 count++;
             }
             long endArrayInsert = System.nanoTime();
             System.out.println("Array Insertion Time: " + (endArrayInsert - startArrayInsert) + " ns");

            // Populate ArrayList
            rs.beforeFirst(); // Reset the ResultSet cursor
            ArrayList<Student> arrayList = new ArrayList<>();
            long startArrayListInsert = System.nanoTime();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                arrayList.add(new Student(id, name, age));
            }
            long endArrayListInsert = System.nanoTime();
            System.out.println("ArrayList Insertion Time: " + (endArrayListInsert - startArrayListInsert) + " ns");

            // Populate LinkedList
            rs.beforeFirst(); // Reset the ResultSet cursor
            LinkedList<Student> linkedList = new LinkedList<>();
            long startLinkedListInsert = System.nanoTime();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                linkedList.add(new Student(id, name, age));
            }
            long endLinkedListInsert = System.nanoTime();
            System.out.println("LinkedList Insertion Time: " + (endLinkedListInsert - startLinkedListInsert) + " ns");

            // Populate HashMap
            rs.beforeFirst(); // Reset the ResultSet cursor
            HashMap<Integer, Student> hashMap = new HashMap<>();
            long startHashMapInsert = System.nanoTime();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                hashMap.put(id, new Student(id, name, age));
            }
            long endHashMapInsert = System.nanoTime();
            System.out.println("HashMap Insertion Time: " + (endHashMapInsert - startHashMapInsert) + " ns");

            // Search in ArrayList
            long startArrayListSearch = System.nanoTime();
            for (Student student : arrayList) {
                if (student.getId() == 50) { // Example search for id 50
                    break;
                }
            }
            long endArrayListSearch = System.nanoTime();
            System.out.println("ArrayList Search Time: " + (endArrayListSearch - startArrayListSearch) + " ns");

            // Search in LinkedList
            long startLinkedListSearch = System.nanoTime();
            for (Student student : linkedList) {
                if (student.getId() == 50) {
                    break;
                }
            }
            long endLinkedListSearch = System.nanoTime();
            System.out.println("LinkedList Search Time: " + (endLinkedListSearch - startLinkedListSearch) + " ns");

            // Search in HashMap
            long startHashMapSearch = System.nanoTime();
            if (hashMap.containsKey(50)) { // Example search for id 50
                // Found
            }
            long endHashMapSearch = System.nanoTime();
            System.out.println("HashMap Search Time: " + (endHashMapSearch - startHashMapSearch) + " ns");

            // Delete from ArrayList
            long startArrayListDelete = System.nanoTime();
            arrayList.removeIf(student -> student.getId() == 50); // Remove student with id 50
            long endArrayListDelete = System.nanoTime();
            System.out.println("ArrayList Deletion Time: " + (endArrayListDelete - startArrayListDelete) + " ns");

            // Delete from LinkedList
            long startLinkedListDelete = System.nanoTime();
            linkedList.removeIf(student -> student.getId() == 50); // Remove student with id 50
            long endLinkedListDelete = System.nanoTime();
            System.out.println("LinkedList Deletion Time: " + (endLinkedListDelete - startLinkedListDelete) + " ns");

            // Delete from HashMap
            long startHashMapDelete = System.nanoTime();
            hashMap.remove(50); // Remove student with id 50
            long endHashMapDelete = System.nanoTime();
            System.out.println("HashMap Deletion Time: " + (endHashMapDelete - startHashMapDelete) + " ns");

            // Close connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

